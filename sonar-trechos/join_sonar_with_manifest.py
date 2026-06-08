import json
import re
from pathlib import Path

BASE_DIR = Path("/home/oscar/Área de trabalho/TCC-Artur-Jackson-e-Oscar-de-Brito--BaseMLCQ")

ISSUES_FILE = BASE_DIR / "sonar-trechos" / "sonar_snippet_issues.jsonl"
MANIFEST_FILE = BASE_DIR / "sonar-trechos" / "snippets_manifest.jsonl"

OUTPUT_FILE = BASE_DIR / "sonar-trechos" / "sonar_snippet_issues_joined.jsonl"


def extract_sample_id(component):
    match = re.search(r"Snippet_+(\d+)\.java", component)

    if match:
        return int(match.group(1))

    return None

def load_manifest():
    manifest = {}

    with MANIFEST_FILE.open("r", encoding="utf-8") as f:
        for line in f:
            item = json.loads(line)
            manifest[item["sample_id"]] = item

    return manifest


def main():
    manifest = load_manifest()

    total = 0
    matched = 0
    unmatched = 0

    with ISSUES_FILE.open("r", encoding="utf-8") as issues, \
         OUTPUT_FILE.open("w", encoding="utf-8") as out:

        for line in issues:
            issue = json.loads(line)
            total += 1

            component = issue.get("component", "")
            sample_id = extract_sample_id(component)

            manifest_item = manifest.get(sample_id)

            if manifest_item:
                matched += 1
            else:
                unmatched += 1

            record = {
                "sample_id": sample_id,
                "rule": issue.get("rule"),
                "severity": issue.get("severity"),
                "component": component,
                "line": issue.get("line"),
                "message": issue.get("message"),
                "issue_key": issue.get("key"),
                "issue_status": issue.get("status"),
                "issue_type": issue.get("type"),

                "mlcq_type": manifest_item.get("type") if manifest_item else None,
                "target_smell": manifest_item.get("target_smell") if manifest_item else None,
                "target_labels": manifest_item.get("target_labels") if manifest_item else None,
                "source": manifest_item.get("source") if manifest_item else None,

                "raw_issue": issue,
            }

            out.write(json.dumps(record, ensure_ascii=False) + "\n")

    print(f"Total issues: {total}")
    print(f"Cruzadas com manifest: {matched}")
    print(f"Sem match: {unmatched}")
    print(f"Saída: {OUTPUT_FILE}")


if __name__ == "__main__":
    main()