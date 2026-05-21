import json
import time
import base64
from pathlib import Path
from urllib.parse import urlencode
from urllib.request import Request, urlopen

SONAR_URL = "http://localhost:9000"

TOKEN = "sqp_2e188a1ab54cfd814d4cadabbcba34ab84f0913a"

PROJECT_KEY = "tcc-analysis"

RULES = [
    "custom-java-smells:long-method-fixed",
    "custom-java-smells:long-method-statistical",
    "custom-java-smells:data-class",
    "custom-java-smells:feature-envy",
    "custom-java-smells:blob-god-class",
]

OUTPUT_FILE = Path("/home/oscar/sonar-experimento/sonar_issues.jsonl")

PAGE_SIZE = 500


def sonar_request(api_path, params):
    query = urlencode(params)
    url = f"{SONAR_URL}{api_path}?{query}"

    auth = base64.b64encode(f"{TOKEN}:".encode()).decode()

    request = Request(url)
    request.add_header("Authorization", f"Basic {auth}")

    with urlopen(request) as response:
        return json.loads(response.read().decode("utf-8"))


def export_issues():
    OUTPUT_FILE.parent.mkdir(parents=True, exist_ok=True)

    page = 1
    exported = 0

    with OUTPUT_FILE.open("w", encoding="utf-8") as out:

        while True:
            params = {
                "componentKeys": PROJECT_KEY,
                "rules": ",".join(RULES),
                "types": "CODE_SMELL",
                "ps": PAGE_SIZE,
                "p": page,
            }

            data = sonar_request("/api/issues/search", params)

            issues = data.get("issues", [])
            total = data.get("total", 0)

            for issue in issues:
                record = {
                    "issue_key": issue.get("key"),
                    "rule": issue.get("rule"),
                    "severity": issue.get("severity"),
                    "type": issue.get("type"),
                    "component": issue.get("component"),
                    "project": issue.get("project"),
                    "line": issue.get("line"),
                    "message": issue.get("message"),
                    "status": issue.get("status"),
                    "creation_date": issue.get("creationDate"),
                    "update_date": issue.get("updateDate"),
                    "effort": issue.get("effort"),
                    "debt": issue.get("debt"),
                    "raw": issue,
                }

                out.write(json.dumps(record, ensure_ascii=False) + "\n")

                exported += 1

            print(f"Página {page} processada | issues exportadas: {exported}/{total}")

            if page * PAGE_SIZE >= total:
                break

            page += 1

            time.sleep(0.2)

    print("\nEXPORTAÇÃO FINALIZADA")
    print(f"Arquivo: {OUTPUT_FILE}")
    print(f"Total exportado: {exported}")


if __name__ == "__main__":
    export_issues()
