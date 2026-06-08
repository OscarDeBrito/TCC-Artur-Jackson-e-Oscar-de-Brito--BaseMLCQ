import json

INPUT = "sonar-trechos/sonar_snippet_issues_joined.jsonl"
OUTPUT = "sonar-trechos/sonar_sample_predictions.jsonl"


def normalize_smell(rule):
    mapping = {
        "custom-java-smells:long-method": "long-method",
        "custom-java-smells:feature-envy": "feature-envy",
        "custom-java-smells:blob-god-class": "blob",
        "custom-java-smells:data-class": "data-class"
    }
    return mapping.get(rule, rule)


count = 0

with open(INPUT, "r", encoding="utf-8") as fin, \
     open(OUTPUT, "w", encoding="utf-8") as fout:

    for line in fin:
        if not line.strip():
            continue

        obj = json.loads(line)

        record = {
            "sample_id": obj["sample_id"],
            "snippet": obj["component"].split(":")[-1],
            "detected_rule": obj["rule"],
            "detected_smell": normalize_smell(obj["rule"]),
            "line": obj.get("line"),
            "severity": obj.get("severity")
        }

        fout.write(json.dumps(record, ensure_ascii=False) + "\n")
        count += 1

print(f"Registros gerados: {count}")
print(f"Arquivo: {OUTPUT}")
