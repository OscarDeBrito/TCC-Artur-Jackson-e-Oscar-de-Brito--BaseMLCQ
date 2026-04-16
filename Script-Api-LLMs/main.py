from pathlib import Path

from dotenv import load_dotenv

# carregar .env
load_dotenv()

from io_helpers import load_json
from pipeline import run_all_providers_for_prompt

BASE_DIR = Path(__file__).resolve().parent
OUTPUT_FILE = BASE_DIR / "outputs.jsonl"
PROMPTS_FILE = BASE_DIR / "prompts.json"

def main():
    prompts = load_json(PROMPTS_FILE)

    for entry in prompts:
        print(f"Rodando prompt {entry['id']}...")
        run_all_providers_for_prompt(entry, OUTPUT_FILE)

    print("Finalizado 🚀")


if __name__ == "__main__":
    main()