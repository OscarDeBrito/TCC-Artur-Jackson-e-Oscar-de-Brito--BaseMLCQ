import json
from collections import Counter

FILE = "outputs_openai.jsonl"
EXPECTED_TOTAL = 8728


def read_file():
    valid_items = []
    valid_lines = []
    invalid_lines = []

    with open(FILE, "r", encoding="utf-8") as f:
        for line_number, line in enumerate(f, start=1):
            original_line = line.rstrip("\n")
            stripped = line.strip()

            if not stripped:
                continue

            try:
                item = json.loads(stripped)
                valid_items.append(item)
                valid_lines.append(original_line)
            except Exception:
                invalid_lines.append(line_number)

    return valid_items, valid_lines, invalid_lines


def print_report(valid_items, invalid_lines):
    status_count = Counter()
    prompt_ids = []

    for item in valid_items:
        status_count[item.get("status", "SEM_STATUS")] += 1
        prompt_ids.append(item.get("prompt_id"))

    expected = set(range(1, EXPECTED_TOTAL + 1))
    found = set(x for x in prompt_ids if isinstance(x, int))

    missing = sorted(expected - found)
    duplicated = [x for x, count in Counter(prompt_ids).items() if count > 1]

    print("\n=== RELATÓRIO ===")
    print("Arquivo:", FILE)
    print("Total de linhas:", len(valid_items) + len(invalid_lines))
    print("JSONs válidos:", len(valid_items))
    print("JSONs inválidos:", len(invalid_lines))
    print("Status:", dict(status_count))
    print("Prompt IDs únicos:", len(set(prompt_ids)))
    print("Total prompt_ids:", len(prompt_ids))

    print("\nFaltando:", len(missing))
    print(missing[:50])

    print("\nDuplicados:", len(duplicated))
    print(duplicated[:50])

    errors = [item for item in valid_items if item.get("status") == "error"]

    print("\nErros:", len(errors))
    for item in errors[:50]:
        print(item.get("prompt_id"), item.get("error"))

    return missing, duplicated, errors


def remove_invalid_lines(valid_lines):
    with open(FILE, "w", encoding="utf-8") as f:
        for line in valid_lines:
            f.write(line + "\n")

    print("\n✅ Linhas inválidas removidas.")
    print("Linhas restantes:", len(valid_lines))


def clean_duplicates_keep_success(valid_items):
    best_by_prompt = {}

    for item in valid_items:
        pid = item.get("prompt_id")

        if pid is None:
            continue

        if pid not in best_by_prompt:
            best_by_prompt[pid] = item
            continue

        current = best_by_prompt[pid]

        # prioridade 1: manter success
        if current.get("status") != "success" and item.get("status") == "success":
            best_by_prompt[pid] = item

        # se ambos são success, mantém o último
        elif current.get("status") == "success" and item.get("status") == "success":
            best_by_prompt[pid] = item

        # se nenhum é success, mantém o último
        elif current.get("status") != "success" and item.get("status") != "success":
            best_by_prompt[pid] = item

    ordered_items = [best_by_prompt[pid] for pid in sorted(best_by_prompt)]

    with open(FILE, "w", encoding="utf-8") as f:
        for item in ordered_items:
            f.write(json.dumps(item, ensure_ascii=False) + "\n")

    print("\n✅ Duplicados removidos.")
    print("Linhas restantes:", len(ordered_items))


def ask_yes_no(question):
    answer = input(question + " (s/n): ").strip().lower()
    return answer in {"s", "sim", "y", "yes"}


def main():
    valid_items, valid_lines, invalid_lines = read_file()
    missing, duplicated, errors = print_report(valid_items, invalid_lines)

    if invalid_lines:
        print("\nLinhas inválidas encontradas:", invalid_lines[:50])
        if ask_yes_no(f"Deseja remover {len(invalid_lines)} linhas inválidas?"):
            remove_invalid_lines(valid_lines)
            valid_items, valid_lines, invalid_lines = read_file()
            missing, duplicated, errors = print_report(valid_items, invalid_lines)

    if duplicated:
        if ask_yes_no(
            f"Deseja remover {len(duplicated)} duplicados mantendo success quando existir?"
        ):
            clean_duplicates_keep_success(valid_items)
            valid_items, valid_lines, invalid_lines = read_file()
            print_report(valid_items, invalid_lines)

    if not invalid_lines and not duplicated and not missing and not errors:
        print("\n✅ Arquivo perfeito: sem inválidos, sem duplicados, sem faltantes e sem erros.")


if __name__ == "__main__":
    main()