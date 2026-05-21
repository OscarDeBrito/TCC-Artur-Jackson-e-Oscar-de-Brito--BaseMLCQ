from pathlib import Path

INPUT = Path("/home/oscar/sonar-experimento/java_files_intersection.txt")
OUTPUT = Path("/home/oscar/sonar-experimento/java_files_filtered.txt")

EXCLUDED_PATTERNS = [
    "/gen/",
    "/generated/",
    "/src-gen/",
    "/gen-java/",
    "/protobuf/",
    "/proto/",
    "/thrift/",
]

excluded = []
kept = []

with INPUT.open("r", encoding="utf-8") as f:
    for line in f:
        path = line.strip()

        if not path:
            continue

        normalized = path.lower()

        should_exclude = any(
            pattern in normalized
            for pattern in EXCLUDED_PATTERNS
        )

        if should_exclude:
            excluded.append(path)
        else:
            kept.append(path)

OUTPUT.write_text(
    "\n".join(kept) + "\n",
    encoding="utf-8"
)

print(f"Arquivos originais: {len(kept) + len(excluded)}")
print(f"Arquivos removidos: {len(excluded)}")
print(f"Arquivos finais: {len(kept)}")
print(f"Saída: {OUTPUT}")
