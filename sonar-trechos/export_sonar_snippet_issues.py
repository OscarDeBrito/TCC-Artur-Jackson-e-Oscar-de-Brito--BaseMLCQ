import json
import requests
from pathlib import Path

SONAR_URL = "http://localhost:9000"
PROJECT_KEY = "tcc-snippets-final-3"

ROOT = Path(__file__).resolve().parents[1]
SONAR_PROPERTIES = ROOT / "sonar-trechos" / "generated-snippets" / "sonar-project.properties"

RULES = [
    "custom-java-smells:long-method",
    "custom-java-smells:data-class",
    "custom-java-smells:feature-envy",
    "custom-java-smells:blob-god-class",
]

OUTPUT = Path("sonar-trechos/sonar_snippet_issues.jsonl")


def load_token():
    if not SONAR_PROPERTIES.exists():
        raise FileNotFoundError(f"Arquivo não encontrado: {SONAR_PROPERTIES}")

    for line in SONAR_PROPERTIES.read_text(encoding="utf-8").splitlines():
        if line.startswith("sonar.token="):
            return line.split("=", 1)[1].strip()

    raise RuntimeError("sonar.token não encontrado no sonar-project.properties")


TOKEN = load_token()
auth = (TOKEN, "")

page_size = 500
total_exported = 0

with OUTPUT.open("w", encoding="utf-8") as out:
    for rule in RULES:
        page = 1
        print(f"\nExportando regra: {rule}")

        while True:
            response = requests.get(
                f"{SONAR_URL}/api/issues/search",
                params={
                    "componentKeys": PROJECT_KEY,
                    "rules": rule,
                    "types": "CODE_SMELL",
                    "ps": page_size,
                    "p": page,
                },
                auth=auth,
            )

            response.raise_for_status()
            data = response.json()

            issues = data.get("issues", [])

            if not issues:
                break

            for issue in issues:
                out.write(json.dumps(issue, ensure_ascii=False) + "\n")

            total_exported += len(issues)
            print(f"{rule} | página {page}: {len(issues)} issues")

            page += 1

print(f"\nTotal exportado: {total_exported}")
print(f"Arquivo: {OUTPUT}")
