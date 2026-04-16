import os
import time

from openai import OpenAI

MODEL_NAME = "deepseek-reasoner"

_client = OpenAI(
    api_key=os.getenv("DEEPSEEK_API_KEY"),
    base_url="https://api.deepseek.com",
)


def call_deepseek(system, prompt):
    start = time.time()
    try:
        response = _client.chat.completions.create(
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
