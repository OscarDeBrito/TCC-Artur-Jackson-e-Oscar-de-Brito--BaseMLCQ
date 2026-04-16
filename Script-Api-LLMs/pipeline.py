import uuid

from io_helpers import append_jsonl, now_iso
from prompt_builder import build_prompt
from providers.claude_provider import MODEL_NAME as CLAUDE_MODEL, call_claude
from providers.deepseek_provider import MODEL_NAME as DEEPSEEK_MODEL, call_deepseek
from providers.gemini_provider import MODEL_NAME as GEMINI_MODEL, call_gemini
from providers.openai_provider import MODEL_NAME as OPENAI_MODEL, call_openai

PROVIDERS = [
    ("openai", OPENAI_MODEL, call_openai),
    ("gemini", GEMINI_MODEL, call_gemini),
    ("deepseek", DEEPSEEK_MODEL, call_deepseek),
    ("claude", CLAUDE_MODEL, call_claude),
]

AVAILABLE_PROVIDERS = [provider_name for provider_name, _, _ in PROVIDERS]


def build_record_base(entry, prompt_text):
    return {
        "prompt_id": entry["id"],
        "system": entry.get("system", ""),
        "prompt": prompt_text,
        "prompt_version": entry.get("prompt_version"),
        "type": entry.get("type"),
        "target_smell": entry.get("target_smell"),
        "code": entry.get("code"),
        "source_prompt": "generated" if entry.get("code") else "manual",
    }


def run_all_providers_for_prompt(entry, output_file, selected_providers=None, output_files_by_provider=None):
    system = entry.get("system", "")
    prompt_text = build_prompt(entry)
    record_base = build_record_base(entry, prompt_text)

    selected = set(selected_providers or AVAILABLE_PROVIDERS)

    for provider_name, model_name, caller in PROVIDERS:
        if provider_name not in selected:
            continue

        provider_output_file = output_file
        if output_files_by_provider:
            provider_output_file = output_files_by_provider.get(provider_name, output_file)

        result = caller(system, prompt_text)
        append_jsonl(
            provider_output_file,
            {
                "run_id": str(uuid.uuid4()),
                "timestamp": now_iso(),
                "provider": provider_name,
                "model": model_name,
                **record_base,
                **result,
            },
        )
