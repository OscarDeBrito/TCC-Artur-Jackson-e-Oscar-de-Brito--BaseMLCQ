import argparse
from pathlib import Path

from dotenv import load_dotenv

# carregar .env
load_dotenv()

from io_helpers import load_json
from pipeline import AVAILABLE_PROVIDERS, run_all_providers_for_prompt

BASE_DIR = Path(__file__).resolve().parent
OUTPUT_FILE = BASE_DIR / "outputs.jsonl"
PROMPTS_FILE = BASE_DIR / "prompts.json"


def parse_args():
    parser = argparse.ArgumentParser(description="Executa os prompts nos provedores configurados.")
    parser.add_argument(
        "--providers",
        default=",".join(AVAILABLE_PROVIDERS),
        help="Lista de provedores separados por vírgula. Ex.: gemini ou openai,gemini",
    )
    parser.add_argument(
        "--output",
        default=str(OUTPUT_FILE),
        help="Arquivo de saída JSONL.",
    )
    return parser.parse_args()


def build_output_files(base_output_path, selected_providers):
    return {
        provider: base_output_path.with_name(f"{base_output_path.stem}_{provider}{base_output_path.suffix}")
        for provider in selected_providers
    }


def main():
    args = parse_args()
    prompts = load_json(PROMPTS_FILE)
    selected_providers = [value.strip().lower() for value in args.providers.split(",") if value.strip()]

    invalid = [provider for provider in selected_providers if provider not in AVAILABLE_PROVIDERS]
    if invalid:
        raise SystemExit(
            "Provedor(es) inválido(s): "
            + ", ".join(invalid)
            + ". Válidos: "
            + ", ".join(AVAILABLE_PROVIDERS)
        )

    output_path = Path(args.output)
    output_files_by_provider = build_output_files(output_path, selected_providers)

    for entry in prompts:
        print(f"Rodando prompt {entry['id']}...")
        run_all_providers_for_prompt(
            entry,
            output_path,
            selected_providers=selected_providers,
            output_files_by_provider=output_files_by_provider,
        )

    print("Finalizado 🚀")


if __name__ == "__main__":
    main()