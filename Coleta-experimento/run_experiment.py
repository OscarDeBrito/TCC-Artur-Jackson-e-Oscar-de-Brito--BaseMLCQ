import json
import subprocess
import time
import sys
from datetime import datetime
from pathlib import Path

BASE_DIR = Path(__file__).resolve().parents[1]

ORCHESTRATOR_DIR = Path(__file__).resolve().parent
PLAN_FILE = ORCHESTRATOR_DIR / "experiment_randomization_plan.jsonl"
LOG_FILE = ORCHESTRATOR_DIR / "logs" / "execution_log.jsonl"

LLM_DIR = BASE_DIR / "Script-Api-LLMs"
SONAR_DIR = BASE_DIR / "sonar-trechos"
LLM_PYTHON = LLM_DIR / ".venv" / "bin" / "python"


LLM_SCRIPT = LLM_DIR / "main.py"


PROVIDER_BY_MODEL = {
    "gpt": "openai",
    "claude": "claude",
    "deepseek": "deepseek",
}


def now_iso():
    return datetime.now().isoformat(timespec="seconds")


def load_plan():
    if not PLAN_FILE.exists():
        raise FileNotFoundError(
            f"Plano não encontrado: {PLAN_FILE}\n"
            "Rode primeiro: python3 experimento-randomizado/generate_randomization_plan.py"
        )

    records = []

    with PLAN_FILE.open("r", encoding="utf-8") as f:
        for line in f:
            if line.strip():
                records.append(json.loads(line))

    return records


def write_log(record):
    LOG_FILE.parent.mkdir(parents=True, exist_ok=True)

    with LOG_FILE.open("a", encoding="utf-8") as out:
        out.write(json.dumps(record, ensure_ascii=False) + "\n")


def run_command(command, cwd):
    started_at = time.time()

    result = subprocess.run(
        command,
        cwd=str(cwd),
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
        text=True,
    )

    ended_at = time.time()

    return {
        "returncode": result.returncode,
        "duration_seconds": round(ended_at - started_at, 3),
        "stdout": result.stdout[-4000:],  # guarda só o final para não explodir o log
    }


def get_llm_python_executable():
    if LLM_PYTHON.exists():
        return str(LLM_PYTHON)

    return sys.executable


def parse_llm_treatment(treatment):
    # Exemplo: gpt_zero_shot
    # Exemplo: claude_few_shot

    if treatment == "sonar":
        return None, None

    if treatment.endswith("_zero_shot"):
        model = treatment.replace("_zero_shot", "")
        prompt_version = "zero_shot"
        return model, prompt_version

    if treatment.endswith("_few_shot"):
        model = treatment.replace("_few_shot", "")
        prompt_version = "few_shot"
        return model, prompt_version

    raise ValueError(f"Tratamento não reconhecido: {treatment}")


def run_llm_treatment(sample_id, treatment, prompt_id):
    model, prompt_version = parse_llm_treatment(treatment)
    provider = PROVIDER_BY_MODEL[model]
    python_executable = get_llm_python_executable()

    command = [
        python_executable,
        str(LLM_SCRIPT.name),
        "--providers",
        provider,
        "--only-ids",
        str(prompt_id),
    ]

    print(f"    Rodando LLM: sample={sample_id} treatment={treatment} prompt_id={prompt_id}")

    started = now_iso()
    result = run_command(command, cwd=LLM_DIR)
    ended = now_iso()

    log_record = {
        "sample_id": sample_id,
        "treatment": treatment,
        "kind": "llm",
        "provider": provider,
        "prompt_id": prompt_id,
        "started_at": started,
        "ended_at": ended,
        "duration_seconds": result["duration_seconds"],
        "returncode": result["returncode"],
        "status": "success" if result["returncode"] == 0 else "error",
        "command": command,
        "stdout_tail": result["stdout"],
    }

    write_log(log_record)

    if result["returncode"] != 0:
        raise RuntimeError(
            f"Erro ao rodar {treatment} no sample {sample_id}. "
            f"Veja o log em {LOG_FILE}"
        )


def run_sonar_for_sample(sample_id):
    print(f"    Rodando Sonar apenas para sample={sample_id}")

    command = [
        "python3",
        "run_sonar_single_sample.py",
        "--sample-id",
        str(sample_id),
    ]

    started = now_iso()
    result = run_command(command, cwd=SONAR_DIR)
    ended = now_iso()

    log_record = {
        "sample_id": sample_id,
        "treatment": "sonar",
        "kind": "sonar",
        "command": command,
        "cwd": str(SONAR_DIR),
        "started_at": started,
        "ended_at": ended,
        "duration_seconds": result["duration_seconds"],
        "returncode": result["returncode"],
        "status": "success" if result["returncode"] == 0 else "error",
        "stdout_tail": result["stdout"],
    }

    write_log(log_record)

    if result["returncode"] != 0:
        raise RuntimeError(
            f"Erro ao rodar Sonar no sample {sample_id}. "
            f"Veja o log em {LOG_FILE}"
        )

def main():
    if not LLM_SCRIPT.exists():
        raise FileNotFoundError(
            f"Script das LLMs não encontrado: {LLM_SCRIPT}\n"
            "Edite a constante LLM_SCRIPT neste arquivo e coloque o nome correto."
        )

    plan = load_plan()

    if LOG_FILE.exists():
        LOG_FILE.unlink()

    print("=== EXECUÇÃO RANDOMIZADA DO EXPERIMENTO ===")
    print(f"Plano: {PLAN_FILE}")
    print(f"Log: {LOG_FILE}")
    print(f"Total de samples: {len(plan)}")


    for index, item in enumerate(plan, start=1):
        sample_id = int(item["sample_id"])
        treatment_order = item["treatment_order"]
        prompt_ids = item["prompt_ids"]

        print(f"[{index}/{len(plan)}] sample_id={sample_id}")

        for treatment in treatment_order:
            if treatment == "sonar":
                print(f"    Tratamento Sonar sorteado para sample={sample_id}")
                run_sonar_for_sample(sample_id)
                continue

            model, prompt_version = parse_llm_treatment(treatment)
            prompt_id = prompt_ids.get(prompt_version)

            if prompt_id is None:
                write_log({
                    "sample_id": sample_id,
                    "treatment": treatment,
                    "kind": "llm",
                    "status": "skipped_missing_prompt_id",
                    "prompt_version": prompt_version,
                    "started_at": now_iso(),
                    "ended_at": now_iso(),
                })
                continue

            run_llm_treatment(sample_id, treatment, prompt_id)

    print("=== FINALIZADO ===")
    print(f"Log salvo em: {LOG_FILE}")
    print("Saídas das LLMs continuam na pasta Script-Api-LLMs.")
    print("Saídas do Sonar continuam na pasta sonar-trechos.")


if __name__ == "__main__":
    main()