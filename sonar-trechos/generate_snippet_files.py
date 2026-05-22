import json
import re
from pathlib import Path

BASE_DIR = Path("/home/oscar/Área de trabalho/TCC-Artur-Jackson-e-Oscar-de-Brito--BaseMLCQ")
PROMPTS_JSON = BASE_DIR / "Script-Api-LLMs" / "prompts.json"
OUTPUT_DIR = BASE_DIR / "sonar-trechos" / "generated-snippets"
MANIFEST = BASE_DIR / "sonar-trechos" / "snippets_manifest.jsonl"

OUTPUT_DIR.mkdir(parents=True, exist_ok=True)


JAVA_KEYWORDS = {
    "if", "for", "while", "switch", "catch", "try",
    "return", "throw", "new", "super", "this",
    "assert", "synchronized", "else", "do"
}


def sanitize_identifier(value):
    value = str(value)
    value = re.sub(r"[^a-zA-Z0-9_]", "_", value)
    if value[0].isdigit():
        value = "_" + value
    return value


def indent(text, spaces):
    prefix = " " * spaces
    return "\n".join(prefix + line if line.strip() else line for line in text.splitlines())


def first_code_line(code):
    in_block = False

    for line in code.splitlines():
        s = line.strip()

        if not s:
            continue

        if in_block:
            if "*/" in s:
                in_block = False
            continue

        if s.startswith("/*"):
            if "*/" not in s:
                in_block = True
            continue

        if s.startswith("//") or s.startswith("*") or s.startswith("@"):
            continue

        return s

    return ""


def looks_like_complete_type(code):
    s = code.strip()

    return bool(re.match(
        r"^(@[\w.]+(\([^)]*\))?\s*)*"
        r"(public\s+|private\s+|protected\s+)?"
        r"(abstract\s+|final\s+|static\s+)?"
        r"(class|interface|enum|record)\s+\w+",
        s
    ))


def looks_like_constructor(code):
    line = first_code_line(code)

    pattern = r"^(public|private|protected)?\s*([A-Za-z_][A-Za-z0-9_]*)\s*\("
    match = re.match(pattern, line)

    if not match:
        return False

    name = match.group(2)

    if name in JAVA_KEYWORDS:
        return False

    return True


def replace_constructor_name(code, synthetic_class_name):
    lines = code.splitlines()
    fixed = []
    replaced = False

    for line in lines:
        if not replaced:
            pattern = r"^(\s*)(public|private|protected)?(\s*)([A-Za-z_][A-Za-z0-9_]*)(\s*\()"
            match = re.match(pattern, line)

            if match:
                original_name = match.group(4)

                if original_name not in JAVA_KEYWORDS:
                    indent_part = match.group(1)
                    visibility = match.group(2) or "public"
                    paren = match.group(5)

                    line = (
                        f"{indent_part}{visibility} "
                        f"{synthetic_class_name}{paren}"
                        + line[match.end():]
                    )

                    replaced = True

        fixed.append(line)

    return "\n".join(fixed)


def generate_java_code(record):
    sample_id = record.get("sample_id")
    sample_type = record.get("type")
    code = (record.get("code") or "").strip()

    class_name = f"Snippet_{sanitize_identifier(sample_id)}"

    if sample_type == "class":
        if looks_like_complete_type(code):
            return code + "\n"

        return f"""public class {class_name} {{

{indent(code, 4)}

}}
"""

    if sample_type in {"method", "function"}:
        if looks_like_constructor(code):
            code = replace_constructor_name(code, class_name)

        return f"""public class {class_name} {{

{indent(code, 4)}

}}
"""

    return f"""public class {class_name} {{

{indent(code, 4)}

}}
"""


def load_prompts():
    text = PROMPTS_JSON.read_text(encoding="utf-8").strip()

    if text.startswith("["):
        return json.loads(text)

    records = []
    for line in text.splitlines():
        line = line.strip()
        if line:
            records.append(json.loads(line))

    return records


def clean_output_dir():
    for file in OUTPUT_DIR.glob("Snippet_*.java"):
        file.unlink()


def main():
    clean_output_dir()

    prompts = load_prompts()
    unique_samples = {}

    for record in prompts:
        sample_id = record.get("sample_id")

        if sample_id in unique_samples:
            continue

        code = record.get("code")
        if not sample_id or not code:
            continue

        generated_code = generate_java_code(record)

        file_name = f"Snippet_{sample_id}.java"
        file_path = OUTPUT_DIR / file_name
        file_path.write_text(generated_code, encoding="utf-8")

        unique_samples[sample_id] = {
            "sample_id": sample_id,
            "generated_relative_path": f"sonar-trechos/generated-snippets/{file_name}",
            "type": record.get("type"),
            "target_smell": record.get("target_smell"),
            "target_labels": record.get("target_labels"),
            "source": record.get("source"),
            "prompt_version_used": record.get("prompt_version"),
        }

    with MANIFEST.open("w", encoding="utf-8") as out:
        for item in unique_samples.values():
            out.write(json.dumps(item, ensure_ascii=False) + "\n")

    print(f"Total de prompts: {len(prompts)}")
    print(f"Total de samples únicos: {len(unique_samples)}")
    print(f"Arquivos gerados em: {OUTPUT_DIR}")
    print(f"Manifest: {MANIFEST}")


if __name__ == "__main__":
    main()
