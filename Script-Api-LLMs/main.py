import os
import json
import time
import uuid
from datetime import datetime
from pathlib import Path

from dotenv import load_dotenv
from openai import OpenAI
from google import genai
from anthropic import Anthropic

# carregar .env
load_dotenv()

# clientes
openai_client = OpenAI(api_key=os.getenv("OPENAI_API_KEY"))
gemini_client = genai.Client(api_key=os.getenv("GEMINI_API_KEY"))

# DeepSeek usa o mesmo SDK da OpenAI
deepseek_client = OpenAI(
    api_key=os.getenv("DEEPSEEK_API_KEY"),
    base_url="https://api.deepseek.com"
)

claude_client = Anthropic(api_key=os.getenv("ANTHROPIC_API_KEY"))

BASE_DIR = Path(__file__).resolve().parent
OUTPUT_FILE = BASE_DIR / "outputs.jsonl"
PROMPTS_FILE = BASE_DIR / "prompts.json"

CLASS_SMELLS = {"blob", "data class"}
FUNCTION_SMELLS = {"long method", "feature envy"}


def normalize_type(value):
    return str(value).strip().lower()


def prompt_class_v1(code):
    return f"""
Analise a seguinte classe Java.

Pergunta: esta classe possui o code smell BLOB ou DATA CLASS?

Responda apenas neste formato:

Blob: SIM ou NÃO
Data Class: SIM ou NÃO

Código:
{code}
"""


def prompt_function_v1(code):
    return f"""
Analise o seguinte método Java.

Pergunta: este método possui o code smell LONG METHOD ou FEATURE ENVY?

Responda apenas neste formato:

Long Method: SIM ou NÃO
Feature Envy: SIM ou NÃO

Código:
{code}
"""


def prompt_class_v2(code):
    return f"""
Você é especialista em qualidade de código Java.

Definições:
- Blob: classe com muitas responsabilidades
- Data Class: classe com muitos atributos e pouca lógica

Analise a classe abaixo e responda:

Blob: SIM ou NÃO
Data Class: SIM ou NÃO

Código:
{code}
"""


def prompt_function_v2(code):
    return f"""
Você é especialista em qualidade de código Java.

Definições:
- Long Method: método muito longo ou complexo
- Feature Envy: método que usa mais dados de outra classe do que da própria

Analise o método abaixo:

Long Method: SIM ou NÃO
Feature Envy: SIM ou NÃO

Código:
{code}
"""


def prompt_class_v3(code):
    return f"""
Analise a classe Java abaixo.

Responda SOMENTE em JSON:

{{
  "blob": 0 ou 1,
  "data_class": 0 ou 1
}}

Código:
{code}
"""


def prompt_function_v3(code):
    return f"""
Analise o método Java abaixo.

Responda SOMENTE em JSON:

{{
  "long_method": 0 ou 1,
  "feature_envy": 0 ou 1
}}

Código:
{code}
"""


def prompt_class_few_shot(code):
    return f"""
Você é especialista em qualidade de código Java.

Definições:
- Blob: classe com responsabilidades excessivas, concentrando muitas funções ou coordenando múltiplos aspectos do sistema.
- Data Class: classe focada principalmente em armazenar dados, com pouca ou nenhuma lógica de negócio relevante.

Exemplo 1:
Código:
```java
public class OrderManager {{
    private List<Order> orders;
    private DatabaseConnection dbConnection;
    private EmailService emailService;
    private PaymentGateway paymentGateway;
    private TaxCalculator taxCalculator;
    private ReportingService reportingService;

    public void createOrder(Order order) {{ /* implementação complexa */ }}
    public void validateUser(String userId) {{ /* implementação complexa */ }}
    public void processPayment(PaymentInfo payment) {{ /* implementação complexa */ }}
    public void sendConfirmationEmail(String email) {{ /* implementação complexa */ }}
    public void generateMonthlySalesReport() {{ /* implementação complexa */ }}
    public double calculateTotalTax(double subtotal) {{ /* implementação complexa */ }}
}}
```

Resposta:
Blob: SIM
Data Class: NÃO

Exemplo 2:
Código:
```java
public class Point {{
    public double x;
    public double y;

    public Point(double x, double y) {{
        this.x = x;
        this.y = y;
    }}
}}
```

Resposta:
Blob: NÃO
Data Class: SIM

Agora analise a classe abaixo.

Responda apenas com:
Blob: SIM ou NÃO
Data Class: SIM ou NÃO
Sem explicações adicionais.

Código:
```java
{code}
```
"""


def prompt_function_few_shot(code):
    return f"""
Você é especialista em qualidade de código Java.

Definições:
- Long Method: método muito longo, denso ou com muitas etapas, decisões e responsabilidades concentradas.
- Feature Envy: método que depende mais de dados ou comportamentos de outra classe ou objeto do que da própria classe.

Exemplo 1:
Código:
```java
public String statement() {{
    double totalAmount = 0;
    int frequentRenterPoints = 0;
    Enumeration rentals = _rentals.elements();
    String result = "Rental Record for " + name() + "\n";

    while (rentals.hasMoreElements()) {{
        double thisAmount = 0;
        Rental each = (Rental) rentals.nextElement();

        switch (each.getMovie().getPriceCode()) {{
            case Movie.REGULAR:
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
        }}

        frequentRenterPoints++;
        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
            frequentRenterPoints++;

        result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
        totalAmount += thisAmount;
    }}
    return result;
}}
```

Resposta:
Long Method: SIM
Feature Envy: NÃO

Exemplo 2:
Código:
```java
public class GradeAnalyzer {{
    private Student student;

    public double calculateFinalGrade() {{
        List<Grade> grades = student.getGrades();
        if (grades.isEmpty()) return 0;

        double totalEarned = 0;
        double totalPossible = 0;

        for (Grade grade : grades) {{
            if (grade.isLateSubmission()) {{
                totalEarned += grade.getScore() * 0.9;
            }} else {{
                totalEarned += grade.getScore();
            }}
            totalPossible += grade.getTotalPoints();
        }}
        return (totalEarned / totalPossible) * 100;
    }}
}}
```

Resposta:
Long Method: NÃO
Feature Envy: SIM

Agora analise o método abaixo.

Responda apenas com:
Long Method: SIM ou NÃO
Feature Envy: SIM ou NÃO
Sem explicações adicionais.

Código:
```java
{code}
```
"""


def build_prompt(entry):
    if entry.get("prompt") and not entry.get("code"):
        return entry["prompt"]

    code = entry.get("code")
    if not code:
        raise ValueError("A entrada precisa ter `prompt` ou `code`.")

    prompt_version = normalize_type(entry.get("prompt_version", "v3"))
    snippet_type = normalize_type(entry.get("type"))

    if snippet_type == "class":
        builders = {
            "v1": prompt_class_v1,
            "v2": prompt_class_v2,
            "v3": prompt_class_v3,
            "v4_few_shot": prompt_class_few_shot,
        }
    elif snippet_type == "function":
        builders = {
            "v1": prompt_function_v1,
            "v2": prompt_function_v2,
            "v3": prompt_function_v3,
            "v4_few_shot": prompt_function_few_shot,
        }
    else:
        raise ValueError(f"Tipo de snippet inválido: {entry.get('type')!r}")

    if prompt_version not in builders:
        raise ValueError(f"Versão de prompt inválida: {prompt_version!r}")

    return builders[prompt_version](code)


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


def call_deepseek(system, prompt):
    start = time.time()
    try:
        r = deepseek_client.chat.completions.create(
            model="deepseek-reasoner",  # troque para "deepseek-chat" se quiser
            messages=[
                {"role": "system", "content": system},
                {"role": "user", "content": prompt}
            ]
        )

        return {
            "status": "success",
            "response": r.choices[0].message.content,
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


def call_claude(system, prompt):
    start = time.time()
    try:
        r = claude_client.messages.create(
            model="claude-sonnet-4-5",
            max_tokens=200,
            system=system,
            messages=[
                {"role": "user", "content": prompt}
            ]
        )

        response_text = ""
        for block in r.content:
            if getattr(block, "type", None) == "text":
                response_text += block.text

        return {
            "status": "success",
            "response": response_text,
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


def main():
    with open(PROMPTS_FILE, "r", encoding="utf-8") as f:
        prompts = json.load(f)

    for p in prompts:
        prompt_id = p["id"]
        system = p.get("system", "")
        text = build_prompt(p)
        prompt_version = p.get("prompt_version")
        snippet_type = p.get("type")
        target_smell = p.get("target_smell")

        print(f"Rodando prompt {prompt_id}...")

        record_base = {
            "prompt_id": prompt_id,
            "system": system,
            "prompt": text,
            "prompt_version": prompt_version,
            "type": snippet_type,
            "target_smell": target_smell,
            "code": p.get("code"),
            "source_prompt": "generated" if p.get("code") else "manual",
        }

        # OpenAI
        res_openai = call_openai(system, text)
        save_jsonl({
            "run_id": str(uuid.uuid4()),
            "timestamp": now(),
            "provider": "openai",
            "model": "gpt-5.4-mini",
            **record_base,
            **res_openai
        })

        # Gemini
        res_gemini = call_gemini(system, text)
        save_jsonl({
            "run_id": str(uuid.uuid4()),
            "timestamp": now(),
            "provider": "gemini",
            "model": "gemini-2.5-flash",
            **record_base,
            **res_gemini
        })

        # DeepSeek
        res_deepseek = call_deepseek(system, text)
        save_jsonl({
            "run_id": str(uuid.uuid4()),
            "timestamp": now(),
            "provider": "deepseek",
            "model": "deepseek-reasoner",
            **record_base,
            **res_deepseek
        })

        # Claude
        res_claude = call_claude(system, text)
        save_jsonl({
            "run_id": str(uuid.uuid4()),
            "timestamp": now(),
            "provider": "claude",
            "model": "claude-sonnet-4-5",
            **record_base,
            **res_claude
        })

    print("Finalizado 🚀")


if __name__ == "__main__":
    main()