import argparse
import json
import shutil
import subprocess
import time
from pathlib import Path

import requests

BASE_DIR = Path("/home/oscar/Área de trabalho/TCC-Artur-Jackson-e-Oscar-de-Brito--BaseMLCQ")

ORIGINAL_SNIPPETS_DIR = BASE_DIR / "sonar-trechos/generated-snippets"
TEMP_DIR = BASE_DIR / "sonar-trechos/generated-snippets-single-run"
PROPERTIES_SOURCE = ORIGINAL_SNIPPETS_DIR / "sonar-project.properties"

OUTPUT_FILE = BASE_DIR / "sonar-trechos/sonar_snippet_issues.jsonl"
DONE_FILE = BASE_DIR / "sonar-trechos/sonar_done_samples.jsonl"

SONAR_HOST_URL = "http://localhost:9000"

RULES = [
    "custom-java-smells:long-method",
    "custom-java-smells:data-class",
    "custom-java-smells:feature-envy",
    "custom-java-smells:blob-god-class",
]


def parse_args():
    parser = argparse.ArgumentParser()
    parser.add_argument("--sample-id", required=True, type=int)
    parser.add_argument("--no-resume", action="store_true")
    return parser.parse_args()


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


def load_done_samples():
    done = set()

    if not DONE_FILE.exists():
        return done

    with DONE_FILE.open("r", encoding="utf-8") as f:
        for line in f:
            if not line.strip():
                continue

            try:
                item = json.loads(line)
                done.add(int(item["sample_id"]))
            except Exception:
                continue

    return done


def mark_done(sample_id, exported_issues):
    with DONE_FILE.open("a", encoding="utf-8") as out:
        out.write(json.dumps({
            "sample_id": sample_id,
            "exported_issues": exported_issues,
            "status": "done"
        }, ensure_ascii=False) + "\n")


def export_sample_issues(project_key, token, snippet_file_name):
    total_exported = 0

    with OUTPUT_FILE.open("a", encoding="utf-8") as out:
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

                    if snippet_file_name in component:
                        out.write(json.dumps(issue, ensure_ascii=False) + "\n")
                        total_exported += 1

                if page * 500 >= total:
                    break

                page += 1

    return total_exported


def main():
    args = parse_args()
    sample_id = args.sample_id

    check_sonar_is_up()

    if not args.no_resume:
        done = load_done_samples()
        if sample_id in done:
            print(f"Sample {sample_id} já processado pelo Sonar. Pulando.")
            return

    snippet_file_name = f"Snippet_{sample_id}.java"
    snippet_source = ORIGINAL_SNIPPETS_DIR / snippet_file_name

    if not snippet_source.exists():
        raise FileNotFoundError(f"Snippet não encontrado: {snippet_source}")

    if not PROPERTIES_SOURCE.exists():
        raise FileNotFoundError(f"sonar-project.properties não encontrado: {PROPERTIES_SOURCE}")

    project_key = read_property(PROPERTIES_SOURCE, "sonar.projectKey")
    token = read_property(PROPERTIES_SOURCE, "sonar.token")

    if not project_key:
        raise RuntimeError("Não encontrei sonar.projectKey no sonar-project.properties")

    if not token:
        raise RuntimeError("Não encontrei sonar.token no sonar-project.properties")

    if TEMP_DIR.exists():
        shutil.rmtree(TEMP_DIR)

    TEMP_DIR.mkdir(parents=True)

    shutil.copy2(snippet_source, TEMP_DIR / snippet_file_name)
    shutil.copy2(PROPERTIES_SOURCE, TEMP_DIR / "sonar-project.properties")

    print(f"Rodando Sonar apenas para {snippet_file_name}")

    result = subprocess.run(
        ["sonar-scanner"],
        cwd=str(TEMP_DIR),
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
        text=True
    )

    if result.returncode != 0:
        print(result.stdout)
        raise RuntimeError(f"Erro ao rodar sonar-scanner para {snippet_file_name}")

    time.sleep(5)

    exported = export_sample_issues(project_key, token, snippet_file_name)
    mark_done(sample_id, exported)

    print(f"Issues exportadas para {snippet_file_name}: {exported}")


if __name__ == "__main__":
    main()