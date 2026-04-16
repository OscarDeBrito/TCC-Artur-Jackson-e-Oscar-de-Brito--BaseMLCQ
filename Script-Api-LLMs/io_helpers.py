import json
from datetime import datetime, timezone


def now_iso():
    return datetime.now(timezone.utc).isoformat()


def load_json(file_path):
    with open(file_path, "r", encoding="utf-8") as file_obj:
        return json.load(file_obj)


def append_jsonl(file_path, data):
    with open(file_path, "a", encoding="utf-8") as file_obj:
        file_obj.write(json.dumps(data, ensure_ascii=False) + "\n")
