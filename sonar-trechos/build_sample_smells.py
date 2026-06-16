import json
import re
from pathlib import Path

INPUT = Path("sonar_snippet_issues.jsonl")
OUTPUT = Path("sonar_sample_smells.jsonl")

RULE_TO_SMELL = {
    "custom-java-smells:long-method": "long-method",
    "custom-java-smells:data-class": "data-class",
    "custom-java-smells:feature-envy": "feature-envy",
    "custom-java-smells:blob-god-class": "blob",
}

if not INPUT.exists():
    raise FileNotFoundError(f"Arquivo não encontrado: {INPUT}")

seen = set()
records = []

with INPUT.open("r", encoding="utf-8") as f:
    for line in f:
        if not line.strip():
            continue

        issue = json.loads(line)

        rule = issue.get("rule")
        smell = RULE_TO_SMELL.get(rule)

        if smell is None:
            continue

        component = issue.get("component", "")
        match = re.search(r"Snippet_(\d+)\.java", component)

        if not match:
            continue

        sample_id = int(match.group(1))
        snippet = f"Snippet_{sample_id}.java"

        key = (sample_id, smell)

        # evita repetir o mesmo sample+smell várias vezes
        if key in seen:
            continue

        seen.add(key)

        records.append({
            "sample_id": sample_id,
            "snippet": snippet,
            "smell": smell
        })

records.sort(key=lambda x: (x["sample_id"], x["smell"]))

with OUTPUT.open("w", encoding="utf-8") as out:
    for record in records:
        out.write(json.dumps(record, ensure_ascii=False) + "\n")

print("=== LISTA SAMPLE + SMELL GERADA ===")
print(f"Entrada: {INPUT}")
print(f"Saída: {OUTPUT}")
print(f"Pares únicos sample+smell: {len(records)}")