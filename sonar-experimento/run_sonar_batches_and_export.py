import base64
import json
import os
import re
import subprocess
import time
from pathlib import Path
from urllib.parse import urlencode
from urllib.request import Request, urlopen

ROOT = Path("/media/sf_verificar_links/downloads")
FILES_LIST = Path("/home/oscar/sonar-experimento/java_files_intersection.txt")

SONAR_URL = "http://localhost:9000"
TOKEN = "sqp_2e188a1ab54cfd814d4cadabbcba34ab84f0913a"

PROJECT_KEY = "tcc-analysis"
PROJECT_NAME = "Analise TCC - Oscar e Artur"

SCANNER_HEAP = "-Xmx4G"

MAX_FILES_PER_BATCH = 10
MAX_BATCH_BYTES = 900_000

PROPERTIES_FILE = ROOT / "sonar-project.properties"
OUTPUT_JSONL = Path("/home/oscar/sonar-experimento/sonar_issues_all_batches.jsonl")
COMPLETED_FILES = Path("/home/oscar/sonar-experimento/completed_files.txt")
FAILED_FILES = Path("/home/oscar/sonar-experimento/failed_files.jsonl")
LOG_DIR = Path("/home/oscar/sonar-experimento/batch_logs")

RULES = [
    "custom-java-smells:long-method-statistical",
    "custom-java-smells:data-class",
    "custom-java-smells:feature-envy",
    "custom-java-smells:blob-god-class",
]

PAGE_SIZE = 500


def auth_header():
    encoded = base64.b64encode(f"{TOKEN}:".encode("utf-8")).decode("utf-8")
    return f"Basic {encoded}"


def sonar_get(path, params):
    query = urlencode(params)
    url = f"{SONAR_URL}{path}?{query}"

    request = Request(url)
    request.add_header("Authorization", auth_header())

    with urlopen(request) as response:
        return json.loads(response.read().decode("utf-8"))


def wait_ce_task(scanner_output):
    match = re.search(r"/api/ce/task\?id=([a-zA-Z0-9\-_]+)", scanner_output)

    if not match:
        print("[AVISO] Não encontrei CE task id.")
        time.sleep(8)
        return

    task_id = match.group(1)

    while True:
        data = sonar_get("/api/ce/task", {"id": task_id})
        status = data.get("task", {}).get("status")

        print(f"[INFO] CE task {task_id}: {status}")

        if status in {"SUCCESS", "FAILED", "CANCELED"}:
            if status != "SUCCESS":
                raise RuntimeError(f"CE task terminou como {status}")
            return

        time.sleep(3)


def file_size(relative_path):
    path = ROOT / relative_path
    try:
        return path.stat().st_size
    except FileNotFoundError:
        return 0


def load_completed_files():
    if not COMPLETED_FILES.exists():
        return set()

    return {
        line.strip()
        for line in COMPLETED_FILES.read_text(encoding="utf-8").splitlines()
        if line.strip()
    }


def mark_completed(files):
    with COMPLETED_FILES.open("a", encoding="utf-8") as f:
        for item in files:
            f.write(item + "\n")


def write_sonar_properties(batch_files):
    inclusions = ",".join(batch_files)

    content = f"""sonar.projectKey={PROJECT_KEY}
sonar.projectName={PROJECT_NAME}
sonar.sources=.
sonar.inclusions={inclusions}
sonar.java.binaries=.
sonar.host.url={SONAR_URL}
sonar.token={TOKEN}
sonar.sourceEncoding=UTF-8
"""

    PROPERTIES_FILE.write_text(content, encoding="utf-8")


def run_scanner(batch_id):
    env = os.environ.copy()
    env["SONAR_SCANNER_OPTS"] = SCANNER_HEAP

    result = subprocess.run(
        ["sonar-scanner"],
        cwd=str(ROOT),
        env=env,
        text=True,
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
    )

    LOG_DIR.mkdir(parents=True, exist_ok=True)
    log_file = LOG_DIR / f"{batch_id}.log"
    log_file.write_text(result.stdout, encoding="utf-8", errors="replace")

    print(result.stdout)

    if result.returncode != 0:
        raise RuntimeError(f"sonar-scanner falhou. Log: {log_file}")

    wait_ce_task(result.stdout)


def export_current_issues(batch_id, batch_files, output_handle):
    page = 1
    exported = 0

    while True:
        params = {
            "componentKeys": PROJECT_KEY,
            "rules": ",".join(RULES),
            "types": "CODE_SMELL",
            "p": page,
            "ps": PAGE_SIZE,
        }

        data = sonar_get("/api/issues/search", params)
        issues = data.get("issues", [])
        total = data.get("total", 0)

        for issue in issues:
            record = {
                "batch_id": batch_id,
                "batch_files": batch_files,
                "issue_key": issue.get("key"),
                "rule": issue.get("rule"),
                "severity": issue.get("severity"),
                "type": issue.get("type"),
                "component": issue.get("component"),
                "project": issue.get("project"),
                "line": issue.get("line"),
                "message": issue.get("message"),
                "status": issue.get("status"),
                "creation_date": issue.get("creationDate"),
                "update_date": issue.get("updateDate"),
                "raw": issue,
            }

            output_handle.write(json.dumps(record, ensure_ascii=False) + "\n")
            exported += 1

        if page * PAGE_SIZE >= total:
            break

        page += 1

    print(f"[INFO] {batch_id}: {exported} issues exportadas.")


def make_size_based_batches(files):
    batches = []
    current = []
    current_size = 0

    for f in files:
        size = file_size(f)

        if current and (
            len(current) >= MAX_FILES_PER_BATCH
            or current_size + size > MAX_BATCH_BYTES
        ):
            batches.append(current)
            current = []
            current_size = 0

        current.append(f)
        current_size += size

    if current:
        batches.append(current)

    return batches


def analyze_batch(batch_files, batch_id, output_handle):
    total_size = sum(file_size(f) for f in batch_files)

    print("\n" + "=" * 80)
    print(f"[INFO] Analisando {batch_id}")
    print(f"[INFO] Arquivos: {len(batch_files)} | Tamanho total: {total_size / 1024:.1f} KB")
    print("=" * 80)

    try:
        write_sonar_properties(batch_files)
        run_scanner(batch_id)
        export_current_issues(batch_id, batch_files, output_handle)
        mark_completed(batch_files)
        output_handle.flush()
        return

    except Exception as e:
        print(f"[ERRO] {batch_id} falhou: {e}")

        if len(batch_files) == 1:
            failed_record = {
                "batch_id": batch_id,
                "file": batch_files[0],
                "size_bytes": file_size(batch_files[0]),
                "error": str(e),
            }

            with FAILED_FILES.open("a", encoding="utf-8") as f:
                f.write(json.dumps(failed_record, ensure_ascii=False) + "\n")

            print(f"[ERRO] Arquivo falhou sozinho e foi registrado: {batch_files[0]}")
            return

        mid = len(batch_files) // 2
        left = batch_files[:mid]
        right = batch_files[mid:]

        print(f"[INFO] Dividindo {batch_id} em dois sublotes: {len(left)} + {len(right)}")

        analyze_batch(left, batch_id + "_a", output_handle)
        analyze_batch(right, batch_id + "_b", output_handle)


def main():
    LOG_DIR.mkdir(parents=True, exist_ok=True)
    OUTPUT_JSONL.parent.mkdir(parents=True, exist_ok=True)

    all_files = [
        line.strip()
        for line in FILES_LIST.read_text(encoding="utf-8").splitlines()
        if line.strip()
    ]

    completed = load_completed_files()

    pending = [f for f in all_files if f not in completed]

    print(f"[INFO] Total de arquivos: {len(all_files)}")
    print(f"[INFO] Já concluídos: {len(completed)}")
    print(f"[INFO] Pendentes: {len(pending)}")

    batches = make_size_based_batches(pending)

    print(f"[INFO] Lotes iniciais gerados: {len(batches)}")
    print(f"[INFO] Máx. arquivos por lote: {MAX_FILES_PER_BATCH}")
    print(f"[INFO] Máx. bytes por lote: {MAX_BATCH_BYTES}")

    with OUTPUT_JSONL.open("a", encoding="utf-8") as out:
        for i, batch in enumerate(batches, start=1):
            batch_id = f"batch_{i:04d}"
            analyze_batch(batch, batch_id, out)

    print("\nFINALIZADO")
    print(f"JSONL: {OUTPUT_JSONL}")
    print(f"Concluídos: {COMPLETED_FILES}")
    print(f"Falhas individuais: {FAILED_FILES}")


if __name__ == "__main__":
    main()
