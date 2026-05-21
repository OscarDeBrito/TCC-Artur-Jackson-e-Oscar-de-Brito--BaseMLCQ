from pathlib import Path

SONAR_LIST = Path("/home/oscar/sonar-experimento/java_files_relative.txt")
EXTRA_LIST = Path("/home/oscar/sonar-experimento/comparacao/sobrando_no_sonar.txt")
OUTPUT = Path("/home/oscar/sonar-experimento/java_files_intersection.txt")

sonar_files = {
    line.strip()
    for line in SONAR_LIST.read_text(encoding="utf-8").splitlines()
    if line.strip()
}

extra_files = {
    line.strip()
    for line in EXTRA_LIST.read_text(encoding="utf-8").splitlines()
    if line.strip()
}

intersection_files = sorted(sonar_files - extra_files)

OUTPUT.write_text("\n".join(intersection_files) + "\n", encoding="utf-8")

print(f"Arquivos originais Sonar: {len(sonar_files)}")
print(f"Arquivos extras removidos: {len(extra_files)}")
print(f"Arquivos finais: {len(intersection_files)}")
print(f"Saída: {OUTPUT}")
