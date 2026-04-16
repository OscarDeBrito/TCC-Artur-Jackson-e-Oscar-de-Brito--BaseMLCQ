import os
import time

from google import genai

MODEL_NAME = "gemini-2.5-flash"

_client = genai.Client(api_key=os.getenv("GEMINI_API_KEY"))


def call_gemini(system, prompt):
    start = time.time()
    try:
        full_prompt = f"{system}\n\n{prompt}"
        response = _client.models.generate_content(
            model=MODEL_NAME,
            contents=full_prompt,
        )

        return {
            "status": "success",
            "response": response.text,
            "latency": round((time.time() - start) * 1000, 2),
            "raw": response.model_dump() if hasattr(response, "model_dump") else {},
        }
    except Exception as exc:
        return {
            "status": "error",
            "response": None,
            "latency": round((time.time() - start) * 1000, 2),
            "error": str(exc),
            "raw": None,
        }
