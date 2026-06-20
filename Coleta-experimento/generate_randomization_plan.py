import json
import random
from pathlib import Path

SEED = 42

BASE_DIR = Path(__file__).resolve().parents[1]

PROMPTS_FILE = BASE_DIR / "Script-Api-LLMs" / "prompts.json"
OUTPUT_FILE = Path(__file__).resolve().parent / "experiment_randomization_plan.jsonl"

TREATMENTS = [
    "gpt_zero_shot",
    "gpt_few_shot",
    "claude_zero_shot",
    "claude_few_shot",
    "deepseek_zero_shot",
    "deepseek_few_shot",
    "sonar",
]


def normalize_prompt_version(value):
    return str(value).strip().lower().replace("-", "_")


def main():
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

    rng = random.Random(SEED)

    missing_zero = 0
    missing_few = 0

    with OUTPUT_FILE.open("w", encoding="utf-8") as out:
        for sample_id in sorted(samples.keys()):
            prompt_ids = samples[sample_id]["prompt_ids"]

            zero_prompt_id = prompt_ids.get("zero_shot")
            few_prompt_id = prompt_ids.get("few_shot")

            if zero_prompt_id is None:
                missing_zero += 1

            if few_prompt_id is None:
                missing_few += 1

            treatment_order = TREATMENTS.copy()
            rng.shuffle(treatment_order)

            record = {
                "sample_id": sample_id,
                "seed": SEED,
                "treatment_order": treatment_order,
                "prompt_ids": {
                    "zero_shot": zero_prompt_id,
                    "few_shot": few_prompt_id,
                },
            }

            out.write(json.dumps(record, ensure_ascii=False) + "\n")

    print("=== PLANO DE RANDOMIZAÇÃO GERADO ===")
    print(f"Entrada: {PROMPTS_FILE}")
    print(f"Saída: {OUTPUT_FILE}")
    print(f"Seed: {SEED}")
    print(f"Samples únicos: {len(samples)}")
    print(f"Samples sem zero_shot: {missing_zero}")
    print(f"Samples sem few_shot: {missing_few}")
    print(f"Tratamentos por sample: {len(TREATMENTS)}")


if __name__ == "__main__":
    main()