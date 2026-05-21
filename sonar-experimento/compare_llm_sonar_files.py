import json
from pathlib import Path
from collections import Counter

SONAR_LIST = Path("/home/oscar/sonar-experimento/java_files_relative.txt")
LLM_FILE = Path("/media/sf_verificar_links/prompts.json")

OUT_DIR = Path("/home/oscar/sonar-experimento/comparacao")
OUT_DIR.mkdir(parents=True, exist_ok=True)

MISSING_IN_SONAR = OUT_DIR / "faltando_no_sonar.txt"
EXTRA_IN_SONAR = OUT_DIR / "sobrando_no_sonar.txt"
SUMMARY = OUT_DIR / "resumo_comparacao.json"


def normalize_repo(repository: str) -> str:
    repo = repository.strip()

    if repo.startswith("git@github.com:"):
        repo = repo.replace("git@github.com:", "")

    if repo.startswith("https://github.com/"):
        repo = repo.replace("https://github.com/", "")

    if repo.endswith(".git"):
        repo = repo[:-4]

    repo = repo.strip("/")
    parts = repo.split("/")

    if len(parts) < 2:
        return repo.replace("/", "__")

    org = parts[-2]
    name = parts[-1]

    return f"{org}__{name}"


def normalize_path(path: str) -> str:
    return path.strip().lstrip("/").replace("\\", "/")


def source_to_relative_key(source: dict) -> str | None:
    if not source:
        return None

    repository = source.get("repository")
    commit_hash = source.get("commit_hash")
    path = source.get("path")

    if not repository or not commit_hash or not path:
        return None

    repo_key = normalize_repo(repository)
    file_path = normalize_path(path)

    return f"{repo_key}/{commit_hash}/{file_path}"


def load_llm_records(path: Path):
    text = path.read_text(encoding="utf-8").strip()

    if not text:
        return []

    # Caso 1: JSON array
    if text.startswith("["):
        return json.loads(text)

    # Caso 2: JSONL
    records = []
    for line_no, line in enumerate(text.splitlines(), start=1):
        line = line.strip()
        if not line:
            continue
        try:
            records.append(json.loads(line))
        except json.JSONDecodeError as e:
            print(f"[AVISO] Linha JSONL inválida {line_no}: {e}")

    return records


def main():
    sonar_files = set()

    with SONAR_LIST.open("r", encoding="utf-8") as f:
        for line in f:
            value = normalize_path(line)
            if value:
                sonar_files.add(value)

    llm_records = load_llm_records(LLM_FILE)

    llm_keys_all = []
    invalid_records = 0

    for record in llm_records:
        key = source_to_relative_key(record.get("source", {}))
        if key is None:
            invalid_records += 1
        else:
            llm_keys_all.append(key)

    llm_counter = Counter(llm_keys_all)
    llm_unique = set(llm_keys_all)

    missing = sorted(llm_unique - sonar_files)
    extra = sorted(sonar_files - llm_unique)
    intersection = sorted(llm_unique & sonar_files)

    MISSING_IN_SONAR.write_text("\n".join(missing), encoding="utf-8")
    EXTRA_IN_SONAR.write_text("\n".join(extra), encoding="utf-8")

    summary = {
        "sonar_total_unique_files": len(sonar_files),
        "llm_total_records": len(llm_records),
        "llm_invalid_records_without_source": invalid_records,
        "llm_total_file_references": len(llm_keys_all),
        "llm_total_unique_files": len(llm_unique),
        "intersection_unique_files": len(intersection),
        "missing_in_sonar": len(missing),
        "extra_in_sonar": len(extra),
        "top_duplicated_llm_files": llm_counter.most_common(10),
        "output_missing_in_sonar": str(MISSING_IN_SONAR),
        "output_extra_in_sonar": str(EXTRA_IN_SONAR),
    }

    SUMMARY.write_text(json.dumps(summary, indent=2, ensure_ascii=False), encoding="utf-8")

    print(json.dumps(summary, indent=2, ensure_ascii=False))


if __name__ == "__main__":
    main()
