import json
from collections import defaultdict
from pathlib import Path

INPUT = Path("sonar-trechos/sonar_sample_summary.jsonl")
OUTPUT = Path("sonar-trechos/sonar_sample_group.jsonl")

sample_map = defaultdict(list)

if not INPUT.exists():
    raise FileNotFoundError(
        f"Arquivo de entrada não encontrado: {INPUT}\n"
        "Rode primeiro: python3 sonar-trechos/build_predictions_jsonl.py"
    )

with INPUT.open("r", encoding="utf-8") as f:
    for line in f:
        if line.strip():
            record = json.loads(line)
            sample_map[record["sample_id"]].append(record)

with OUTPUT.open("w", encoding="utf-8") as out:
    for sample_id, records in sorted(sample_map.items()):
        detected_smells = sorted(set(r["detected_smell"] for r in records))
        detected_rules = sorted(set(r["detected_rule"] for r in records))
        lines = sorted(set(r["line"] for r in records if r.get("line") is not None))

        result = {
            "sample_id": sample_id,
            "snippet": records[0]["snippet"],
            "detected_smells": detected_smells,
            "detected_rules": detected_rules,
            "total_detections": len(records),
            "lines": lines
        }

        out.write(json.dumps(result, ensure_ascii=False) + "\n")

print("=== AGRUPAMENTO POR SAMPLE FINALIZADO ===")
print(f"Samples únicos gerados: {len(sample_map)}")
print(f"Arquivo de saída: {OUTPUT}")
