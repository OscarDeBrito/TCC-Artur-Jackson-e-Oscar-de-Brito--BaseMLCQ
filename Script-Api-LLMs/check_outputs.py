import json
from collections import Counter

FILE = "outputs_deepseek.jsonl"
EXPECTED_TOTAL = 8728


def check_file():
    total_lines = 0
    valid_json = 0
    invalid_json = 0
    status_count = Counter()
    missing_status = 0
    prompt_ids = []
    valid_lines = []

    with open(FILE, "r", encoding="utf-8") as f:
        for line_number, line in enumerate(f, start=1):
            total_lines += 1
            original_line = line.rstrip("\n")
            line = line.strip()

            if not line:
                continue

            try:
                item = json.loads(line)
                valid_json += 1
                valid_lines.append(original_line)
            except Exception:
                invalid_json += 1
                print(f"JSON inválido na linha {line_number}")
                continue

            if "status" not in item:
                missing_status += 1
                print(f"Sem status na linha {line_number}, prompt_id={item.get('prompt_id')}")
            else:
                status_count[item["status"]] += 1

            prompt_ids.append(item.get("prompt_id"))

    expected = set(range(1, EXPECTED_TOTAL + 1))
    found = set(x for x in prompt_ids if isinstance(x, int))

    missing = sorted(expected - found)
    duplicated = [x for x, count in Counter(prompt_ids).items() if count > 1]

    print("\n=== RELATÓRIO ===")
    print("Arquivo:", FILE)
    print("Total de linhas:", total_lines)
    print("JSONs válidos:", valid_json)
    print("JSONs inválidos:", invalid_json)
    print("Status:", dict(status_count))
    print("Sem status:", missing_status)
    print("Prompt IDs únicos:", len(set(prompt_ids)))
    print("Total prompt_ids:", len(prompt_ids))

    print("\nFaltando:", len(missing))
    print(missing[:50])

    print("\nDuplicados:", len(duplicated))
    print(duplicated[:50])

    return invalid_json, valid_lines


def remove_invalid_lines(valid_lines):
    with open(FILE, "w", encoding="utf-8") as f:
        for line in valid_lines:
            f.write(line + "\n")

    print("\nLinhas inválidas removidas.")
    print("Linhas restantes:", len(valid_lines))


def main():
    invalid_count, valid_lines = check_file()

    if invalid_count == 0:
        print("\n✅ Nenhuma linha inválida encontrada.")
        return

    answer = input(f"\nForam encontradas {invalid_count} linhas inválidas. Deseja removê-las? (s/n): ")

    if answer.strip().lower() in {"s", "sim", "y", "yes"}:
        remove_invalid_lines(valid_lines)
    else:
        print("Nenhuma alteração feita.")


if __name__ == "__main__":
    main()