import os
import time

from openai import OpenAI

MODEL_NAME = "gpt-5.4-mini"


def _build_client():
    api_key = os.getenv("OPENAI_API_KEY")
    if not api_key:
        raise RuntimeError("OPENAI_API_KEY não configurada.")

    return OpenAI(api_key=api_key)


def call_openai(system, prompt):
    start = time.time()
    try:
        client = _build_client()
        response = client.responses.create(
            model=MODEL_NAME,
            instructions=system,
            input=prompt,
        )

        return {
            "status": "success",
            "response": response.output_text,
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
