import os
import json
import time
import uuid
from datetime import datetime

from dotenv import load_dotenv
from openai import OpenAI
from google import genai

# carregar .env
load_dotenv()

# clientes
openai_client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))
gemini_client = genai.Client(api_key=os.getenv("GEMINI_API_KEY"))

OUTPUT_FILE = "outputs.jsonl"


def now():
    return datetime.utcnow().isoformat()


def save_jsonl(data):
    with open(OUTPUT_FILE, "a", encoding="utf-8") as f:
        f.write(json.dumps(data, ensure_ascii=False) + "\n")


def call_openai(system, prompt):
    start = time.time()
    try:
        r = openai_client.responses.create(
            model="gpt-5.4-mini",
            instructions=system,
            input=prompt
        )

        return {
            "status": "success",
            "response": r.output_text,
            "latency": round((time.time() - start) * 1000, 2),
            "raw": r.model_dump()
        }

    except Exception as e:
        return {
            "status": "error",
            "response": None,
            "latency": round((time.time() - start) * 1000, 2),
            "error": str(e),
            "raw": None
        }


def call_gemini(system, prompt):
    start = time.time()
    try:
        full_prompt = f"{system}\n\n{prompt}"

        r = gemini_client.models.generate_content(
            model="gemini-2.5-flash",
            contents=full_prompt
        )

        return {
            "status": "success",
            "response": r.text,
            "latency": round((time.time() - start) * 1000, 2),
            "raw": r.model_dump() if hasattr(r, "model_dump") else {}
        }

    except Exception as e:
        return {
            "status": "error",
            "response": None,
            "latency": round((time.time() - start) * 1000, 2),
            "error": str(e),
            "raw": None
        }


def main():
    with open("prompts.json", "r", encoding="utf-8") as f:
        prompts = json.load(f)

    for p in prompts:
        prompt_id = p["id"]
        system = p.get("system", "")
        text = p["prompt"]

        print(f"Rodando prompt {prompt_id}...")

        # OpenAI
        res_openai = call_openai(system, text)

        save_jsonl({
            "run_id": str(uuid.uuid4()),
            "timestamp": now(),
            "provider": "openai",
            "model": "gpt-5.4-mini",
            "prompt_id": prompt_id,
            "system": system,
            "prompt": text,
            **res_openai
        })

        # Gemini
        res_gemini = call_gemini(system, text)

        save_jsonl({
            "run_id": str(uuid.uuid4()),
            "timestamp": now(),
            "provider": "gemini",
            "model": "gemini-2.5-flash",
            "prompt_id": prompt_id,
            "system": system,
            "prompt": text,
            **res_gemini
        })

    print("Finalizado 🚀")


if __name__ == "__main__":
    main()