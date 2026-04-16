import os
import time

from anthropic import Anthropic

MODEL_NAME = "claude-sonnet-4-5"


def _build_client():
    api_key = os.getenv("ANTHROPIC_API_KEY")
    if not api_key:
        raise RuntimeError("ANTHROPIC_API_KEY não configurada.")

    return Anthropic(api_key=api_key)


def call_claude(system, prompt):
    start = time.time()
    try:
        client = _build_client()
        response = client.messages.create(
            model=MODEL_NAME,
            max_tokens=200,
            system=system,
            messages=[{"role": "user", "content": prompt}],
        )

        response_text = ""
        for block in response.content:
            if getattr(block, "type", None) == "text":
                response_text += block.text

        return {
            "status": "success",
            "response": response_text,
            "latency": round((time.time() - start) * 1000, 2),
            "raw": response.model_dump(),
        }
    except Exception as exc:
        return {
            "status": "error",
            "response": None,
            "latency": round((time.time() - start) * 1000, 2),
            "error": str(exc),
            "raw": None,
        }
