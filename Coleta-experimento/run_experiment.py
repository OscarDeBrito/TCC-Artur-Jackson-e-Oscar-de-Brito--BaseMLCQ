import json
import random
import subprocess
import time
import sys
from datetime import datetime
from pathlib import Path

BASE_DIR = Path(__file__).resolve().parents[1]

ORCHESTRATOR_DIR = Path(__file__).resolve().parent
RUNTIME_PLAN_FILE = ORCHESTRATOR_DIR / "experiment_randomization_plan_runtime.jsonl"
LOG_FILE = ORCHESTRATOR_DIR / "logs" / "execution_log.jsonl"

LLM_DIR = BASE_DIR / "Script-Api-LLMs"
SONAR_DIR = BASE_DIR / "sonar-trechos"
LLM_PYTHON = LLM_DIR / ".venv" / "bin" / "python"
LLM_SCRIPT = LLM_DIR / "main.py"
PROMPTS_FILE = LLM_DIR / "prompts.json"

SEED = 42

TREATMENTS = [
    "gpt_zero_shot",
    "gpt_few_shot",
    "claude_zero_shot",
    "claude_few_shot",
    "deepseek_zero_shot",
    "deepseek_few_shot",
    "sonar",
]

PROVIDER_BY_MODEL = {
    "gpt": "openai",
    "claude": "claude",
    "deepseek": "deepseek",
}


def now_iso():
    return datetime.now().isoformat(timespec="seconds")


def normalize_prompt_version(value):
    return str(value).strip().lower().replace("-", "_")


def load_samples_from_prompts():
    if not PROMPTS_FILE.exists():
        raise FileNotFoundError(f"Arquivo de prompts não encontrado: {PROMPTS_FILE}")

    with PROMPTS_FILE.open("r", encoding="utf-8") as f:
        prompts = json.load(f)

    samples = {}

    for prompt in prompts:
        sample_id = int(prompt["sample_id"])
        prompt_version = normalize_prompt_version(prompt["prompt_version"])

        if sample_id not in samples:
            samples[sample_id] = {
                "sample_id": sample_id,
                "prompt_ids": {},
            }

        if prompt_version in ["zero_shot", "few_shot"]:
            samples[sample_id]["prompt_ids"][prompt_version] = int(prompt["id"])

    return [samples[sample_id] for sample_id in sorted(samples.keys())]


def generate_treatment_order_for_sample(sample_id):
    # Sorteia na hora da execução, mas de forma reprodutível por sample.
    # Se precisar recomeçar, o mesmo sample_id recebe a mesma ordem.
    rng = random.Random(f"{SEED}-{sample_id}")

    treatment_order = TREATMENTS.copy()
    rng.shuffle(treatment_order)

    return treatment_order


def write_runtime_plan(record):
    RUNTIME_PLAN_FILE.parent.mkdir(parents=True, exist_ok=True)

    with RUNTIME_PLAN_FILE.open("a", encoding="utf-8") as out:
        out.write(json.dumps(record, ensure_ascii=False) + "\n")


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
        "stdout": result.stdout[-4000:],
    }


def get_llm_python_executable():
    if LLM_PYTHON.exists():
        return str(LLM_PYTHON)

    return sys.executable


def parse_llm_treatment(treatment):
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

    samples = load_samples_from_prompts()

    if LOG_FILE.exists():
        LOG_FILE.unlink()

    if RUNTIME_PLAN_FILE.exists():
        RUNTIME_PLAN_FILE.unlink()

    print("=== EXECUÇÃO RANDOMIZADA DO EXPERIMENTO ===")
    print(f"Plano gerado durante a execução: {RUNTIME_PLAN_FILE}")
    print(f"Log: {LOG_FILE}")
    print(f"Seed: {SEED}")
    print(f"Total de samples: {len(samples)}")

    for index, item in enumerate(samples, start=1):
        sample_id = int(item["sample_id"])
        prompt_ids = item["prompt_ids"]

        treatment_order = generate_treatment_order_for_sample(sample_id)

        write_runtime_plan({
            "sample_id": sample_id,
            "seed": SEED,
            "treatment_order": treatment_order,
            "prompt_ids": prompt_ids,
            "generated_at": now_iso(),
        })

        print(f"[{index}/{len(samples)}] sample_id={sample_id}")
        print(f"    Ordem sorteada: {', '.join(treatment_order)}")

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
    print(f"Plano runtime salvo em: {RUNTIME_PLAN_FILE}")
    print(f"Log salvo em: {LOG_FILE}")
    print("Saídas das LLMs continuam na pasta Script-Api-LLMs.")
    print("Saídas do Sonar continuam na pasta sonar-trechos.")


if __name__ == "__main__":
    main()