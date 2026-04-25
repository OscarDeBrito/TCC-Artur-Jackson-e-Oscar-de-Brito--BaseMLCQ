import argparse
from pathlib import Path
from concurrent.futures import ThreadPoolExecutor, as_completed

from dotenv import load_dotenv

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
    parser.add_argument(
        "--max-workers",
        type=int,
        default=10,
        help="Quantidade de prompts rodando em paralelo.",
    )
    parser.add_argument(
    "--limit",
    type=int,
    default=None,
    help="Limita a quantidade de prompts processados.",
    )
    parser.add_argument(
    "--only-ids",
    default=None,
    help="Lista de IDs específicos separados por vírgula. Ex.: 2467,3020,4328",
    )
    parser.add_argument(
        "--no-resume",
        action="store_true",
        help="Não pula prompts já processados.",
    )
    return parser.parse_args()


def build_output_files(base_output_path, selected_providers):
    return {
        provider: base_output_path.with_name(f"{base_output_path.stem}_{provider}{base_output_path.suffix}")
        for provider in selected_providers
    }


def load_done_prompt_ids(output_file):
    done = set()

    if not output_file.exists():
        return done

    import json

    with open(output_file, "r", encoding="utf-8") as f:
        for line in f:
            line = line.strip()
            if not line:
                continue

            try:
                item = json.loads(line)
            except Exception:
                continue

            if item.get("status") == "success":
                done.add(item.get("prompt_id"))

    return done


def process_prompt(entry, output_path, selected_providers, output_files_by_provider, done_by_provider, resume=True):
    providers_to_run = []

    for provider in selected_providers:
        if resume and entry["id"] in done_by_provider.get(provider, set()):
            continue
        providers_to_run.append(provider)

    if not providers_to_run:
        return entry["id"], "skipped"

    run_all_providers_for_prompt(
        entry,
        output_path,
        selected_providers=providers_to_run,
        output_files_by_provider=output_files_by_provider,
    )

    return entry["id"], "done"


def main():
    args = parse_args()

    prompts = load_json(PROMPTS_FILE)

    if args.limit is not None:
        prompts = prompts[:args.limit]

    if args.only_ids:
        only_ids = {int(x.strip()) for x in args.only_ids.split(",") if x.strip()}
        prompts = [p for p in prompts if int(p["id"]) in only_ids]

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

    done_by_provider = {}
    if not args.no_resume:
        for provider, file_path in output_files_by_provider.items():
            done_by_provider[provider] = load_done_prompt_ids(file_path)
            print(f"{provider}: {len(done_by_provider[provider])} prompts já processados.")

    print(f"Total de prompts: {len(prompts)}")
    print(f"Provedores: {', '.join(selected_providers)}")
    print(f"Max workers: {args.max_workers}")

    completed = 0
    skipped = 0

    with ThreadPoolExecutor(max_workers=args.max_workers) as executor:
        futures = [
            executor.submit(
                process_prompt,
                entry,
                output_path,
                selected_providers,
                output_files_by_provider,
                done_by_provider,
                not args.no_resume,
            )
            for entry in prompts
        ]

        for future in as_completed(futures):
            prompt_id, status = future.result()

            if status == "skipped":
                skipped += 1
            else:
                completed += 1

            total_finished = completed + skipped

            if total_finished % 50 == 0:
                print(
                    f"Progresso: {total_finished}/{len(prompts)} "
                    f"| concluídos: {completed} | pulados: {skipped}"
                )

    print("Finalizado 🚀")
    print(f"Concluídos agora: {completed}")
    print(f"Pulados por já existirem: {skipped}")


if __name__ == "__main__":
    main()