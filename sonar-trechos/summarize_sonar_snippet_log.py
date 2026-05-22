from pathlib import Path
import re

BASE_DIR = Path("/home/oscar/Área de trabalho/TCC-Artur-Jackson-e-Oscar-de-Brito--BaseMLCQ/sonar-trechos")
LOG_FILE = BASE_DIR / "sonar_snippets_run.log"
GENERATED_DIR = BASE_DIR / "generated-snippets"

text = LOG_FILE.read_text(encoding="utf-8", errors="replace")

total_java_files = len(list(GENERATED_DIR.glob("Snippet_*.java")))

indexed_match = re.search(r"INFO:\s+(\d+)\s+files indexed", text)
indexed_files = int(indexed_match.group(1)) if indexed_match else None

parse_error_files = re.findall(r"Unable to parse source file : '([^']+)'", text)
unique_parse_error_files = sorted(set(parse_error_files))

analysis_successful = "ANALYSIS SUCCESSFUL" in text

success_files_estimate = total_java_files - len(unique_parse_error_files)

print("\n=== RESUMO DA ANÁLISE SONAR DOS SNIPPETS ===\n")
print(f"Total de snippets gerados: {total_java_files}")

if indexed_files is not None:
    print(f"Arquivos indexados pelo Sonar: {indexed_files}")

print(f"Arquivos com parse error: {len(unique_parse_error_files)}")
print(f"Arquivos estimados sem parse error: {success_files_estimate}")

if total_java_files > 0:
    success_rate = (success_files_estimate / total_java_files) * 100
    error_rate = (len(unique_parse_error_files) / total_java_files) * 100
    print(f"Taxa estimada de sucesso: {success_rate:.2f}%")
    print(f"Taxa de parse error: {error_rate:.2f}%")

print(f"Análise finalizou com sucesso: {analysis_successful}")

print("\n=== PRIMEIROS 30 ARQUIVOS COM ERRO ===\n")
for item in unique_parse_error_files[:30]:
    print(item)

output = BASE_DIR / "parse_error_files.txt"
output.write_text("\n".join(unique_parse_error_files) + "\n", encoding="utf-8")

print(f"\nLista completa salva em: {output}")