import re
from pathlib import Path

LOG_PATH = Path("sonar_batches_run.log")
OUTPUT = Path("sonar_time_summary.txt")


def parse_time(value):
    value = value.strip().replace("s", "").strip()
    parts = value.split(":")

    if len(parts) == 1:
        return float(parts[0])

    if len(parts) == 2:
        minutes = int(parts[0])
        seconds = float(parts[1])
        return minutes * 60 + seconds

    if len(parts) == 3:
        hours = int(parts[0])
        minutes = int(parts[1])
        seconds = float(parts[2])
        return hours * 3600 + minutes * 60 + seconds

    raise ValueError(f"Formato de tempo não reconhecido: {value}")


def format_seconds(seconds):
    hours = int(seconds // 3600)
    minutes = int((seconds % 3600) // 60)
    secs = seconds % 60

    if hours > 0:
        return f"{hours}h {minutes}min {secs:.2f}s"

    if minutes > 0:
        return f"{minutes}min {secs:.2f}s"

    return f"{secs:.2f}s"


if not LOG_PATH.exists():
    raise FileNotFoundError(f"Log não encontrado: {LOG_PATH}")

times = []
batch_sizes = []

with LOG_PATH.open("r", encoding="utf-8", errors="ignore") as f:
    for line in f:
        batch_match = re.search(r"\[(\d+)/(\d+)\]\s+lote com\s+(\d+)\s+snippets", line)
        if batch_match:
            batch_sizes.append(int(batch_match.group(3)))

        time_match = re.search(r"INFO: Total time:\s*([0-9:.]+)\s*s", line)
        if time_match:
            times.append(parse_time(time_match.group(1)))

if not times:
    raise RuntimeError("Nenhum tempo encontrado no log.")

total_seconds = sum(times)
total_batches = len(times)
total_snippets = sum(batch_sizes)
avg_batch = total_seconds / total_batches
avg_snippet = total_seconds / total_snippets if total_snippets else 0

text = f"""=== RESUMO DO TEMPO DE EXECUÇÃO DO SONAR ===

Arquivo de log analisado: {LOG_PATH}

Lotes finalizados: {total_batches}
Snippets analisados: {total_snippets}

Tempo total: {format_seconds(total_seconds)}
Tempo total em segundos: {total_seconds:.2f}

Tempo médio por lote: {format_seconds(avg_batch)}
Tempo médio por snippet: {avg_snippet:.4f}s

Menor tempo de lote: {format_seconds(min(times))}
Maior tempo de lote: {format_seconds(max(times))}
"""

OUTPUT.write_text(text, encoding="utf-8")

print(text)
print(f"Resumo salvo em: {OUTPUT}")