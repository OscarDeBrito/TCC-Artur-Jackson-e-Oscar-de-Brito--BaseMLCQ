import json
import random
import shutil
import subprocess
import time
from pathlib import Path

import requests

SEED = 42
BATCH_SIZE = 100

BASE_DIR = Path("/home/oscar/Área de trabalho/TCC-Artur-Jackson-e-Oscar-de-Brito--BaseMLCQ")

ORIGINAL_SNIPPETS_DIR = BASE_DIR / "sonar-trechos/generated-snippets"
TEMP_DIR = BASE_DIR / "sonar-trechos/generated-snippets-batch-run"
PROPERTIES_SOURCE = ORIGINAL_SNIPPETS_DIR / "sonar-project.properties"

OUTPUT_FILE = BASE_DIR / "sonar-trechos/sonar_snippet_issues.jsonl"
LOG_FILE = BASE_DIR / "sonar-trechos/sonar_batches_run.log"

SONAR_HOST_URL = "http://localhost:9000"

RULES = [
    "custom-java-smells:long-method",
    "custom-java-smells:data-class",
    "custom-java-smells:feature-envy",
    "custom-java-smells:blob-god-class",
]


def read_property(path, key):
    with path.open("r", encoding="utf-8") as f:
        for line in f:
            line = line.strip()
            if line.startswith(key + "="):
                return line.split("=", 1)[1].strip()
    return None


def check_sonar_is_up():
    response = requests.get(f"{SONAR_HOST_URL}/api/system/status", timeout=10)
    response.raise_for_status()

    status = response.json().get("status")

    if status != "UP":
        raise RuntimeError(f"SonarQube ainda não está UP. Status atual: {status}")

    print("SonarQube está UP.")


def wait_a_little_for_processing():
    time.sleep(5)


def export_batch_issues(project_key, token, batch_file_names, out):
    batch_names_set = set(batch_file_names)
    total_exported = 0

    for rule in RULES:
        page = 1

        while True:
            params = {
                "componentKeys": project_key,
                "rules": rule,
                "ps": 500,
                "p": page,
            }

            response = requests.get(
                f"{SONAR_HOST_URL}/api/issues/search",
                params=params,
                auth=(token, "")
            )
            response.raise_for_status()

            data = response.json()
            issues = data.get("issues", [])
            total = data.get("total", 0)

            for issue in issues:
                component = issue.get("component", "")

                if any(file_name in component for file_name in batch_names_set):
                    out.write(json.dumps(issue, ensure_ascii=False) + "\n")
                    total_exported += 1

            if page * 500 >= total:
                break

            page += 1

    return total_exported


def chunks(items, size):
    for i in range(0, len(items), size):
        yield items[i:i + size]


def main():
    check_sonar_is_up()

    if not ORIGINAL_SNIPPETS_DIR.exists():
        raise FileNotFoundError(f"Pasta não encontrada: {ORIGINAL_SNIPPETS_DIR}")

    if not PROPERTIES_SOURCE.exists():
        raise FileNotFoundError(f"Arquivo não encontrado: {PROPERTIES_SOURCE}")

    project_key = read_property(PROPERTIES_SOURCE, "sonar.projectKey")
    token = read_property(PROPERTIES_SOURCE, "sonar.token")

    if not project_key:
        raise RuntimeError("Não encontrei sonar.projectKey no sonar-project.properties")

    if not token:
        raise RuntimeError("Não encontrei sonar.token no sonar-project.properties")

    snippets = sorted(ORIGINAL_SNIPPETS_DIR.glob("Snippet_*.java"))

    if not snippets:
        raise RuntimeError("Nenhum Snippet_*.java encontrado.")

    random.seed(SEED)
    random.shuffle(snippets)

    batches = list(chunks(snippets, BATCH_SIZE))

    if OUTPUT_FILE.exists():
        OUTPUT_FILE.unlink()

    if LOG_FILE.exists():
        LOG_FILE.unlink()

    with OUTPUT_FILE.open("w", encoding="utf-8") as out, LOG_FILE.open("w", encoding="utf-8") as log:
        print("=== SONAR EM LOTES ALEATÓRIOS ===")
        print(f"Seed: {SEED}")
        print(f"Tamanho do lote: {BATCH_SIZE}")
        print(f"Total de snippets: {len(snippets)}")
        print(f"Total de lotes: {len(batches)}")
        print(f"Projeto Sonar: {project_key}")
        print(f"Saída: {OUTPUT_FILE}")

        for batch_index, batch in enumerate(batches, start=1):
            if TEMP_DIR.exists():
                shutil.rmtree(TEMP_DIR)

            TEMP_DIR.mkdir(parents=True)

            for snippet_path in batch:
                shutil.copy2(snippet_path, TEMP_DIR / snippet_path.name)

            shutil.copy2(PROPERTIES_SOURCE, TEMP_DIR / "sonar-project.properties")

            batch_file_names = [p.name for p in batch]

            print(f"[{batch_index}/{len(batches)}] Analisando lote com {len(batch)} snippets")
            log.write(f"[{batch_index}/{len(batches)}] lote com {len(batch)} snippets\n")
            log.write("Arquivos:\n")
            for name in batch_file_names:
                log.write(f"{name}\n")
            log.flush()

            result = subprocess.run(
                ["sonar-scanner"],
                cwd=str(TEMP_DIR),
                stdout=subprocess.PIPE,
                stderr=subprocess.STDOUT,
                text=True
            )

            log.write(result.stdout)
            log.write("\n" + "=" * 80 + "\n")
            log.flush()

            if result.returncode != 0:
                print(f"ATENÇÃO: scanner retornou erro no lote {batch_index}. Tentando exportar mesmo assim.")

            wait_a_little_for_processing()

            exported = export_batch_issues(project_key, token, batch_file_names, out)
            out.flush()

            print(f"    issues exportadas neste lote: {exported}")

    print("=== FINALIZADO ===")
    print(f"Arquivo acumulado: {OUTPUT_FILE}")
    print(f"Log: {LOG_FILE}")


if __name__ == "__main__":
    main()