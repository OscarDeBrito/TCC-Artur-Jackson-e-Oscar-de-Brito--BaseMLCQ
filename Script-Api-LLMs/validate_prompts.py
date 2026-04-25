import json
from pathlib import Path
from collections import Counter, defaultdict
from urllib.parse import urlparse


PROMPTS_FILE = Path("prompts.json")
DOWNLOADS_ROOT = Path(r"C:\Users\Oscar Neto\Desktop\verificar links\downloads")

EXPECTED_TOTAL = 8728
EXPECTED_PROMPT_VERSIONS = {"zero_shot", "few_shot"}
VALID_LABELS = {1, 0, -1}


def local_github_file_path(link):
    cleaned = str(link).split("#")[0].rstrip("/")
    parsed = urlparse(cleaned)
    parts = parsed.path.strip("/").split("/")

    owner = parts[0]
    repo = parts[1]
    commit_hash = parts[3]
    file_parts = parts[4:]

    return DOWNLOADS_ROOT / f"{owner}__{repo}" / commit_hash / Path(*file_parts)


def extract_code_from_source(source):
    local_file = local_github_file_path(source["link"])

    if not local_file.exists():
        return None, f"Arquivo não encontrado: {local_file}"

    with open(local_file, "r", encoding="utf-8", errors="ignore") as f:
        lines = f.read().splitlines()

    start = max(int(source["start_line"]) - 1, 0)
    end = min(int(source["end_line"]), len(lines))

    return "\n".join(lines[start:end]), None


def main():
    errors = []
    warnings = []

    with open(PROMPTS_FILE, "r", encoding="utf-8") as f:
        prompts = json.load(f)

    print("Total de prompts:", len(prompts))

    if len(prompts) != EXPECTED_TOTAL:
        errors.append(f"Total esperado {EXPECTED_TOTAL}, mas veio {len(prompts)}.")

    required_fields = {
        "id",
        "sample_id",
        "type",
        "target_smell",
        "target_labels",
        "prompt_version",
        "system",
        "code",
        "source",
        "prompt",
    }

    required_source_fields = {
        "repository",
        "commit_hash",
        "path",
        "start_line",
        "end_line",
        "link",
    }

    by_sample = defaultdict(list)
    prompt_versions = Counter()
    types = Counter()
    empty_code_count = 0

    for i, item in enumerate(prompts, start=1):
        missing = required_fields - set(item.keys())
        if missing:
            errors.append(f"Prompt índice {i}: campos ausentes {missing}")
            continue

        sample_id = item["sample_id"]
        by_sample[sample_id].append(item)

        prompt_versions[item["prompt_version"]] += 1
        types[item["type"]] += 1

        if item["prompt_version"] not in EXPECTED_PROMPT_VERSIONS:
            errors.append(
                f"sample_id {sample_id}: prompt_version inválido: {item['prompt_version']}"
            )

        if item["type"] not in {"class", "function"}:
            errors.append(f"sample_id {sample_id}: type inválido: {item['type']}")

        source = item["source"]
        missing_source = required_source_fields - set(source.keys())
        if missing_source:
            errors.append(f"sample_id {sample_id}: source incompleto {missing_source}")

        labels = item["target_labels"]
        for smell, value in labels.items():
            if value not in VALID_LABELS:
                errors.append(
                    f"sample_id {sample_id}: label inválido para {smell}: {value}"
                )

        if item["type"] == "class":
            expected_smells = {"blob", "data class"}
            if set(labels.keys()) != expected_smells:
                errors.append(
                    f"sample_id {sample_id}: labels de class incorretos: {labels.keys()}"
                )

        if item["type"] == "function":
            expected_smells = {"long method", "feature envy"}
            if set(labels.keys()) != expected_smells:
                errors.append(
                    f"sample_id {sample_id}: labels de function incorretos: {labels.keys()}"
                )

        code = item["code"]

        if not code or not str(code).strip():
            empty_code_count += 1
            errors.append(f"sample_id {sample_id}: code vazio")
        else:
            extracted_code, err = extract_code_from_source(source)

            if err:
                errors.append(f"sample_id {sample_id}: {err}")
            elif extracted_code != code:
                errors.append(
                    f"sample_id {sample_id}: code salvo não bate com o trecho local "
                    f"L{source['start_line']}-L{source['end_line']}"
                )

        if item["prompt"] and item["code"] not in item["prompt"]:
            warnings.append(
                f"sample_id {sample_id}: code não aparece literalmente dentro do prompt"
            )

    for sample_id, items in by_sample.items():
        if len(items) != 2:
            errors.append(
                f"sample_id {sample_id}: esperado 2 prompts, mas veio {len(items)}"
            )

        versions = {item["prompt_version"] for item in items}
        if versions != EXPECTED_PROMPT_VERSIONS:
            errors.append(
                f"sample_id {sample_id}: versões incorretas: {versions}"
            )

        codes = {item["code"] for item in items}
        if len(codes) != 1:
            errors.append(
                f"sample_id {sample_id}: zero_shot e few_shot têm códigos diferentes"
            )

        labels = {
            json.dumps(item["target_labels"], sort_keys=True)
            for item in items
        }
        if len(labels) != 1:
            errors.append(
                f"sample_id {sample_id}: zero_shot e few_shot têm labels diferentes"
            )

    print("\n=== RESUMO ===")
    print("Samples únicos:", len(by_sample))
    print("Prompt versions:", dict(prompt_versions))
    print("Tipos:", dict(types))
    print("Codes vazios:", empty_code_count)

    print("\n=== WARNINGS ===")
    print("Total:", len(warnings))
    for w in warnings[:20]:
        print("-", w)

    print("\n=== ERROS ===")
    print("Total:", len(errors))
    for e in errors[:50]:
        print("-", e)

    if not errors:
        print("\n✅ prompts.json validado com sucesso.")
    else:
        print("\n❌ prompts.json tem problemas. Veja os erros acima.")


if __name__ == "__main__":
    main()