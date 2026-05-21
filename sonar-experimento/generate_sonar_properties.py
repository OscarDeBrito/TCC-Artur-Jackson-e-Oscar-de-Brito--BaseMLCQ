from pathlib import Path

ROOT = Path("/media/sf_verificar_links/downloads")
FILES_LIST = Path("/home/oscar/sonar-experimento/java_files_intersection.txt")
OUTPUT = ROOT / "sonar-project.properties"

PROJECT_KEY = "tcc-analysis"
PROJECT_NAME = "Analise TCC - Oscar e Artur"
SONAR_URL = "http://localhost:9000"

TOKEN = "sqp_2e188a1ab54cfd814d4cadabbcba34ab84f0913a"

def escape_property_value(value: str) -> str:
    return value.replace("\\", "\\\\").replace("\n", "")

with FILES_LIST.open("r", encoding="utf-8") as f:
    files = [line.strip() for line in f if line.strip()]

inclusions = ",".join(escape_property_value(file) for file in files)

content = f"""sonar.projectKey={PROJECT_KEY}
sonar.projectName={PROJECT_NAME}
sonar.sources=.
sonar.inclusions={inclusions}
sonar.java.binaries=.
sonar.host.url={SONAR_URL}
sonar.token={TOKEN}
sonar.sourceEncoding=UTF-8
"""

OUTPUT.write_text(content, encoding="utf-8")

print(f"Arquivo gerado em: {OUTPUT}")
print(f"Total de arquivos incluídos: {len(files)}")
print(f"Project key: {PROJECT_KEY}")
