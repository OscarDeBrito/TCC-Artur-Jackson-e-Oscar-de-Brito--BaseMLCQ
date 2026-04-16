import argparse
import json
from pathlib import Path
from urllib.request import urlopen

import pandas as pd

from prompt_builder import PROMPT_VERSIONS, build_prompt, normalize_type


BASE_DIR = Path(__file__).resolve().parent
DEFAULT_SOURCE_FILE = BASE_DIR.parent / "Analise-base-MLCQ" / "MLCQ_ground_truth_completo.xlsx"
DEFAULT_OUTPUT_FILE = BASE_DIR / "prompts.json"

CLASS_SMELLS = ["blob", "data class"]
FUNCTION_SMELLS = ["long method", "feature envy"]


def normalize_text(value):
    return str(value).strip().lower().replace("_", " ")


def raw_github_url(link):
    cleaned = str(link).split("#")[0].rstrip("/")
    if "github.com" not in cleaned:
        raise ValueError(f"Link não suportado: {link}")

    parts = cleaned.split("/")
    try:
        blob_index = parts.index("blob")
    except ValueError as exc:
        raise ValueError(f"Formato de link inesperado: {link}") from exc

    owner = parts[3]
    repo = parts[4]
    commit_hash = parts[blob_index + 1]
    file_parts = parts[blob_index + 2 :]
    return f"https://raw.githubusercontent.com/{owner}/{repo}/{commit_hash}/{'/'.join(file_parts)}"


def fetch_code_snippet(row):
    raw_url = raw_github_url(row["link"])
    with urlopen(raw_url, timeout=30) as response:
        full_text = response.read().decode("utf-8", errors="replace")

    lines = full_text.splitlines()
    start = max(int(row["start_line"]) - 1, 0)
    end = min(int(row["end_line"]), len(lines))
    return "\n".join(lines[start:end])


def prepare_base(source_file):
    df = pd.read_excel(source_file)
    df.columns = df.columns.str.strip()

    df["smell"] = df["smell"].map(normalize_text)
    df["type"] = df["type"].map(normalize_type)
    df["veredito_final"] = (
        df["veredito_final"].astype(str).str.strip().str.lower().map(
            {
                "tem_smell": 1,
                "nao_tem_smell": 0,
                "não_tem_smell": 0,
                "empate": pd.NA,
            }
        )
    )

    df = df[df["veredito_final"].notna()].copy()

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

    return prepared.sort_values(["type", "sample_id"]).reset_index(drop=True)


def build_entries(prepared, samples_per_type=None, seed=42):
    records = []

    smell_map = {
        "class": CLASS_SMELLS,
        "function": FUNCTION_SMELLS,
    }

    for snippet_type, smells in smell_map.items():
        subset = prepared[prepared["type"] == snippet_type].copy()
        subset = subset.sort_values(["sample_id"]).drop_duplicates(subset=["sample_id"], keep="first")

        if samples_per_type is not None:
            subset = subset.sample(
                n=min(int(samples_per_type), len(subset)),
                random_state=seed,
            ).sort_values(["sample_id"])

        for _, row in subset.iterrows():
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

    entries = build_entries(prepared, samples_per_type=args.samples_per_type, seed=args.seed)

    for index, entry in enumerate(entries, start=1):
        entry["id"] = index
        entry["prompt"] = build_prompt(entry)

    output_path = Path(args.output)
    output_path.parent.mkdir(parents=True, exist_ok=True)
    output_path.write_text(json.dumps(entries, ensure_ascii=False, indent=2), encoding="utf-8")

    if args.sample_id is not None:
        print(f"Gerados {len(entries)} prompts para sample_id {args.sample_id} em {output_path}")
    else:
        print(f"Gerados {len(entries)} prompts em {output_path}")


if __name__ == "__main__":
    main()