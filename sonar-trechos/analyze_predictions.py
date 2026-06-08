import json
from collections import Counter, defaultdict
from pathlib import Path

INPUT = Path("sonar-trechos/sonar_sample_predictions.jsonl")
OUTPUT_DUPLICATES = Path("sonar-trechos/sample_duplicates_report.jsonl")
OUTPUT_SUMMARY = Path("sonar-trechos/predictions_summary.txt")


records = []

with INPUT.open("r", encoding="utf-8") as f:
    for line in f:
        if line.strip():
            records.append(json.loads(line))


total_predictions = len(records)

sample_ids = [r["sample_id"] for r in records]
snippets = [r["snippet"] for r in records]
smells = [r["detected_smell"] for r in records]
rules = [r["detected_rule"] for r in records]

sample_counter = Counter(sample_ids)
snippet_counter = Counter(snippets)
smell_counter = Counter(smells)
rule_counter = Counter(rules)

sample_to_records = defaultdict(list)
for r in records:
    sample_to_records[r["sample_id"]].append(r)

duplicated_samples = {
    sample_id: recs
    for sample_id, recs in sample_to_records.items()
    if len(recs) > 1
}

samples_with_multiple_smells = {
    sample_id: recs
    for sample_id, recs in sample_to_records.items()
    if len(set(r["detected_smell"] for r in recs)) > 1
}

pair_counter = Counter()

for sample_id, recs in sample_to_records.items():
    unique_smells = sorted(set(r["detected_smell"] for r in recs))
    if len(unique_smells) > 1:
        for i in range(len(unique_smells)):
            for j in range(i + 1, len(unique_smells)):
                pair_counter[(unique_smells[i], unique_smells[j])] += 1


with OUTPUT_DUPLICATES.open("w", encoding="utf-8") as out:
    for sample_id, recs in sorted(
        duplicated_samples.items(),
        key=lambda item: len(item[1]),
        reverse=True
    ):
        simplified = {
            "sample_id": sample_id,
            "total_detections": len(recs),
            "detected_smells": sorted(set(r["detected_smell"] for r in recs)),
            "detections": recs,
        }
        out.write(json.dumps(simplified, ensure_ascii=False) + "\n")


with OUTPUT_SUMMARY.open("w", encoding="utf-8") as out:
    out.write("=== RESUMO DAS PREDIÇÕES SONAR ===\n\n")

    out.write(f"Total de detecções: {total_predictions}\n")
    out.write(f"Samples únicos detectados: {len(set(sample_ids))}\n")
    out.write(f"Snippets únicos detectados: {len(set(snippets))}\n")
    out.write(f"Samples com mais de uma detecção: {len(duplicated_samples)}\n")
    out.write(f"Samples com mais de um tipo de smell: {len(samples_with_multiple_smells)}\n\n")

    out.write("=== DETECÇÕES POR SMELL ===\n")
    for smell, count in smell_counter.most_common():
        out.write(f"{smell}: {count}\n")

    out.write("\n=== DETECÇÕES POR REGRA ===\n")
    for rule, count in rule_counter.most_common():
        out.write(f"{rule}: {count}\n")

    out.write("\n=== TOP 30 SAMPLES COM MAIS DETECÇÕES ===\n")
    for sample_id, count in sample_counter.most_common(30):
        out.write(f"{sample_id}: {count}\n")

    out.write("\n=== SOBREPOSIÇÃO ENTRE SMELLS ===\n")
    if pair_counter:
        for pair, count in pair_counter.most_common():
            out.write(f"{pair[0]} + {pair[1]}: {count}\n")
    else:
        out.write("Nenhuma sobreposição entre smells encontrada.\n")


print(f"Total de detecções: {total_predictions}")
print(f"Samples únicos detectados: {len(set(sample_ids))}")
print(f"Snippets únicos detectados: {len(set(snippets))}")
print(f"Samples com mais de uma detecção: {len(duplicated_samples)}")
print(f"Samples com mais de um tipo de smell: {len(samples_with_multiple_smells)}")
print()
print("Detecções por smell:")
for smell, count in smell_counter.most_common():
    print(f"- {smell}: {count}")

print()
print(f"Resumo salvo em: {OUTPUT_SUMMARY}")
print(f"Duplicados salvos em: {OUTPUT_DUPLICATES}")
