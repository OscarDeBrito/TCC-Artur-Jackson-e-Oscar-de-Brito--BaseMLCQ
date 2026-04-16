import os
import time

from openai import OpenAI

MODEL_NAME = "deepseek-reasoner"


def _build_client():
    api_key = os.getenv("DEEPSEEK_API_KEY")
    if not api_key:
        raise RuntimeError("DEEPSEEK_API_KEY não configurada.")

    return OpenAI(
        api_key=api_key,
        base_url="https://api.deepseek.com",
    )


def call_deepseek(system, prompt):
    start = time.time()
    try:
        client = _build_client()
        response = client.chat.completions.create(
            model=MODEL_NAME,
            messages=[
                {"role": "system", "content": system},
                {"role": "user", "content": prompt},
            ],
        )

        return {
            "status": "success",
            "response": response.choices[0].message.content,
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
