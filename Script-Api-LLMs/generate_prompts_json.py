import argparse
import json
from pathlib import Path
from urllib.parse import urlparse

import pandas as pd

from prompt_builder import PROMPT_VERSIONS, build_prompt, normalize_type


BASE_DIR = Path(__file__).resolve().parent
DEFAULT_SOURCE_FILE = BASE_DIR.parent / "Analise-base-MLCQ" / "MLCQ_ground_truth_completo.xlsx"
DEFAULT_OUTPUT_FILE = BASE_DIR / "prompts.json"

DOWNLOADS_ROOT = Path(r"C:\Users\Oscar Neto\Desktop\verificar links\downloads")

CLASS_SMELLS = ["blob", "data class"]
FUNCTION_SMELLS = ["long method", "feature envy"]


def normalize_text(value):
    return str(value).strip().lower().replace("_", " ")


def local_github_file_path(link):
    cleaned = str(link).split("#")[0].rstrip("/")

    if "github.com" not in cleaned:
        raise ValueError(f"Link não suportado: {link}")

    parsed = urlparse(cleaned)
    parts = parsed.path.strip("/").split("/")

    if len(parts) < 5 or parts[2] != "blob":
        raise ValueError(f"Formato de link inesperado: {link}")

    owner = parts[0]
    repo = parts[1]
    commit_hash = parts[3]
    file_parts = parts[4:]

    return DOWNLOADS_ROOT / f"{owner}__{repo}" / commit_hash / Path(*file_parts)


def fetch_code_snippet(row):
    local_file = local_github_file_path(row["link"])

    if not local_file.exists():
        print(f"[ERRO] Arquivo local não encontrado: {local_file}")
        return ""

    with open(local_file, "r", encoding="utf-8", errors="ignore") as f:
        lines = f.read().splitlines()

    start = max(int(row["start_line"]) - 1, 0)
    end = min(int(row["end_line"]), len(lines))

    return "\n".join(lines[start:end])


def prepare_base(source_file):
    df = pd.read_excel(source_file)
    df.columns = df.columns.str.strip()

    raw_df = df.copy()
    raw_sample_ids = set(df["sample_id"].unique())

    print("\n=== AUDITORIA ANTES DO FILTRO ===")
    print("Linhas totais na base:", len(df))
    print("Samples únicos na base:", df["sample_id"].nunique())
    print("\nValores originais de veredito_final:")
    print(df["veredito_final"].astype(str).str.strip().str.lower().value_counts(dropna=False))

    df["smell"] = df["smell"].map(normalize_text)
    df["type"] = df["type"].map(normalize_type)

    df["veredito_final_original"] = df["veredito_final"]

    df["veredito_final"] = (
        df["veredito_final"]
        .astype(str)
        .str.strip()
        .str.lower()
        .map(
            {
                "tem_smell": 1,
                "nao_tem_smell": 0,
                "não_tem_smell": 0,
                "empate": -1,
            }
        )
    )
    
    df = df[df["veredito_final"].notna()].copy()
    
    filtered_sample_ids = set(df["sample_id"].unique())
    missing_after_filter = sorted(raw_sample_ids - filtered_sample_ids)

    print("\n=== AUDITORIA DEPOIS DO FILTRO ===")
    print("Linhas após filtro:", len(df))
    print("Samples únicos após filtro:", df["sample_id"].nunique())
    print("Samples removidos pelo filtro:", len(missing_after_filter))

    if missing_after_filter:
        print("\nSamples removidos:")
        print(missing_after_filter)

        print("\nDetalhes dos samples removidos:")
        cols_to_show = [
            "sample_id",
            "smell",
            "type",
            "veredito_final",
            "repository",
            "path",
            "start_line",
            "end_line",
            "link",
        ]
        cols_to_show = [c for c in cols_to_show if c in raw_df.columns]
        print(raw_df[raw_df["sample_id"].isin(missing_after_filter)][cols_to_show])

    index_cols = [
        "sample_id",
        "type",
        "code_name",
        "repository",
        "commit_hash",
        "path",
        "start_line",
        "end_line",
        "link",
        "is_from_industry_relevant_project",
    ]

    base = df[index_cols].drop_duplicates().copy()

    pivot = (
        df.pivot_table(
            index="sample_id",
            columns="smell",
            values="veredito_final",
            aggfunc="max",
        )
        .reset_index()
        .rename_axis(None, axis=1)
    )

    prepared = base.merge(pivot, on="sample_id", how="left")

    for smell in CLASS_SMELLS + FUNCTION_SMELLS:
        if smell not in prepared.columns:
            prepared[smell] = pd.NA

    for smell in CLASS_SMELLS + FUNCTION_SMELLS:
        prepared[smell] = pd.to_numeric(prepared[smell], errors="coerce")

    for smell in CLASS_SMELLS:
        prepared.loc[prepared["type"] == "function", smell] = pd.NA
        prepared.loc[(prepared["type"] == "class") & (prepared[smell].isna()), smell] = 0

    for smell in FUNCTION_SMELLS:
        prepared.loc[prepared["type"] == "class", smell] = pd.NA
        prepared.loc[(prepared["type"] == "function") & (prepared[smell].isna()), smell] = 0

    for smell in CLASS_SMELLS + FUNCTION_SMELLS:
        prepared[smell] = prepared[smell].astype("Int64")

    prepared["start_line"] = prepared["start_line"].astype(int)
    prepared["end_line"] = prepared["end_line"].astype(int)

    prepared = prepared.sort_values(["type", "sample_id"]).reset_index(drop=True)

    print("\n=== AUDITORIA DO PREPARED ===")
    print("Linhas em prepared:", len(prepared))
    print("Samples únicos em prepared:", prepared["sample_id"].nunique())
    print("\nSamples por tipo:")
    print(prepared.groupby("type")["sample_id"].nunique())

    return prepared


def build_entries(prepared, samples_per_type=None, seed=42):
    records = []

    smell_map = {
        "class": CLASS_SMELLS,
        "function": FUNCTION_SMELLS,
    }

    used_sample_ids = set()

    for snippet_type, smells in smell_map.items():
        subset = prepared[prepared["type"] == snippet_type].copy()

        before = len(subset)
        before_unique = subset["sample_id"].nunique()

        subset = subset.sort_values(["sample_id"]).drop_duplicates(subset=["sample_id"], keep="first")

        after = len(subset)

        print(f"\n=== BUILD ENTRIES: {snippet_type} ===")
        print("Linhas antes do drop_duplicates:", before)
        print("Samples únicos antes:", before_unique)
        print("Linhas depois do drop_duplicates:", after)

        if samples_per_type is not None:
            subset = subset.sample(
                n=min(int(samples_per_type), len(subset)),
                random_state=seed,
            ).sort_values(["sample_id"])

        for _, row in subset.iterrows():
            used_sample_ids.add(int(row["sample_id"]))

            code = fetch_code_snippet(row)

            for prompt_version in PROMPT_VERSIONS:
                records.append(
                    {
                        "sample_id": int(row["sample_id"]),
                        "type": row["type"],
                        "target_smell": ", ".join(smells),
                        "target_labels": {smell: int(row[smell]) for smell in smells},
                        "prompt_version": prompt_version,
                        "system": "Responda de forma objetiva",
                        "code": code,
                        "source": {
                            "repository": row["repository"],
                            "commit_hash": row["commit_hash"],
                            "path": row["path"],
                            "start_line": int(row["start_line"]),
                            "end_line": int(row["end_line"]),
                            "link": row["link"],
                        },
                    }
                )

    prepared_sample_ids = set(prepared["sample_id"].astype(int).unique())
    missing_in_entries = sorted(prepared_sample_ids - used_sample_ids)

    print("\n=== AUDITORIA FINAL DE GERAÇÃO ===")
    print("Samples disponíveis em prepared:", len(prepared_sample_ids))
    print("Samples usados em entries:", len(used_sample_ids))
    print("Samples sem prompt:", len(missing_in_entries))

    if missing_in_entries:
        print("Samples sem prompt:")
        print(missing_in_entries)

    return records


def main():
    parser = argparse.ArgumentParser(description="Gera prompts.json a partir da base MLCQ sample por sample.")
    parser.add_argument("--source", default=str(DEFAULT_SOURCE_FILE), help="Caminho para o XLSX de origem")
    parser.add_argument("--output", default=str(DEFAULT_OUTPUT_FILE), help="Caminho para o prompts.json de saída")
    parser.add_argument("--sample-id", type=int, default=None, help="Gera prompts apenas para um sample_id específico")
    parser.add_argument("--samples-per-type", type=int, default=None, help="Amostras por tipo de entidade")
    parser.add_argument("--seed", type=int, default=42, help="Seed para amostragem")
    args = parser.parse_args()

    prepared = prepare_base(Path(args.source))

    if args.sample_id is not None:
        prepared = prepared[prepared["sample_id"] == args.sample_id].copy()
        if prepared.empty:
            raise SystemExit(f"sample_id {args.sample_id} não encontrado na base.")

    print("\n=== PROMPT VERSIONS ===")
    print("PROMPT_VERSIONS:", PROMPT_VERSIONS)
    print("Quantidade de versões:", len(PROMPT_VERSIONS))

    expected = prepared["sample_id"].nunique() * len(PROMPT_VERSIONS)
    print("Prompts esperados com base no prepared:", expected)

    entries = build_entries(prepared, samples_per_type=args.samples_per_type, seed=args.seed)

    print("\n=== RESULTADO ===")
    print("Prompts gerados:", len(entries))
    print("Diferença entre esperado e gerado:", expected - len(entries))

    for index, entry in enumerate(entries, start=1):
        entry["id"] = index
        entry["prompt"] = build_prompt(entry)

    output_path = Path(args.output)
    output_path.parent.mkdir(parents=True, exist_ok=True)
    output_path.write_text(json.dumps(entries, ensure_ascii=False, indent=2), encoding="utf-8")

    if args.sample_id is not None:
        print(f"\nGerados {len(entries)} prompts para sample_id {args.sample_id} em {output_path}")
    else:
        print(f"\nGerados {len(entries)} prompts em {output_path}")


if __name__ == "__main__":
    main()