import argparse
import json
from pathlib import Path
from urllib.request import urlopen

import pandas as pd


BASE_DIR = Path(__file__).resolve().parent
DEFAULT_SOURCE_FILE = BASE_DIR.parent / "MLCQ_ground_truth_completo.xlsx"
DEFAULT_OUTPUT_FILE = BASE_DIR / "prompts.json"

CLASS_SMELLS = ["blob", "data class"]
FUNCTION_SMELLS = ["long method", "feature envy"]
PROMPT_VERSIONS = ["v1", "v2", "v3", "v4_few_shot"]


def normalize_text(value):
    return str(value).strip().lower().replace("_", " ")


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


def build_prompt(code, snippet_type, prompt_version):
    snippet_type = normalize_type(snippet_type)
    prompt_version = normalize_text(prompt_version).replace(" ", "_")

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
        raise ValueError(f"Tipo inválido: {snippet_type!r}")

    if prompt_version not in builders:
        raise ValueError(f"Versão inválida: {prompt_version!r}")

    return builders[prompt_version](code)


def raw_github_url(link):
    cleaned = str(link).split("#")[0].rstrip("/")
    if "github.com" not in cleaned:
        raise ValueError(f"Link não suportado: {link}")

    parts = cleaned.split("/")
    try:
        blob_index = parts.index("blob")
    except ValueError as exc:
        raise ValueError(f"Formato de link inesperado: {link}") from exc

    owner = parts[3]
    repo = parts[4]
    commit_hash = parts[blob_index + 1]
    file_parts = parts[blob_index + 2 :]
    return f"https://raw.githubusercontent.com/{owner}/{repo}/{commit_hash}/{'/'.join(file_parts)}"


def fetch_code_snippet(row):
    raw_url = raw_github_url(row["link"])
    with urlopen(raw_url, timeout=30) as response:
        full_text = response.read().decode("utf-8", errors="replace")

    lines = full_text.splitlines()
    start = max(int(row["start_line"]) - 1, 0)
    end = min(int(row["end_line"]), len(lines))
    return "\n".join(lines[start:end])


def prepare_base(source_file):
    df = pd.read_excel(source_file)
    df.columns = df.columns.str.strip()

    df["smell"] = df["smell"].map(normalize_text)
    df["type"] = df["type"].map(normalize_type)
    df["veredito_final"] = (
        df["veredito_final"].astype(str).str.strip().str.lower().map(
            {
                "tem_smell": 1,
                "nao_tem_smell": 0,
                "não_tem_smell": 0,
                "empate": pd.NA,
            }
        )
    )

    df = df[df["veredito_final"].notna()].copy()

    index_cols = [
        "sample_id",
        "type",
        "code_name",
        "repository",
        "commit_hash",
        "path",
        "start_line",
        "end_line",
        "link",
        "is_from_industry_relevant_project",
    ]

    base = df[index_cols].drop_duplicates().copy()
    pivot = (
        df.pivot_table(
            index="sample_id",
            columns="smell",
            values="veredito_final",
            aggfunc="max",
        )
        .reset_index()
        .rename_axis(None, axis=1)
    )

    prepared = base.merge(pivot, on="sample_id", how="left")

    for smell in CLASS_SMELLS + FUNCTION_SMELLS:
        if smell not in prepared.columns:
            prepared[smell] = pd.NA

    for smell in CLASS_SMELLS + FUNCTION_SMELLS:
        prepared[smell] = pd.to_numeric(prepared[smell], errors="coerce")

    for smell in CLASS_SMELLS:
        prepared.loc[prepared["type"] == "function", smell] = pd.NA
        prepared.loc[(prepared["type"] == "class") & (prepared[smell].isna()), smell] = 0

    for smell in FUNCTION_SMELLS:
        prepared.loc[prepared["type"] == "class", smell] = pd.NA
        prepared.loc[(prepared["type"] == "function") & (prepared[smell].isna()), smell] = 0

    for smell in CLASS_SMELLS + FUNCTION_SMELLS:
        prepared[smell] = prepared[smell].astype("Int64")

    prepared["start_line"] = prepared["start_line"].astype(int)
    prepared["end_line"] = prepared["end_line"].astype(int)

    return prepared.sort_values(["type", "sample_id"]).reset_index(drop=True)


def build_entries(prepared, prompt_version, samples_per_smell=None, seed=42):
    records = []

    smell_map = {
        "class": CLASS_SMELLS,
        "function": FUNCTION_SMELLS,
    }

    for snippet_type, smells in smell_map.items():
        subset = prepared[prepared["type"] == snippet_type].copy()

        for smell in smells:
            smell_subset = subset[subset[smell].notna()].copy()
            if samples_per_smell is not None:
                positives = smell_subset[smell_subset[smell] == 1]
                negatives = smell_subset[smell_subset[smell] == 0]
                n_each = int(samples_per_smell)
                pos_n = min(n_each, len(positives))
                neg_n = min(n_each, len(negatives))
                smell_subset = pd.concat(
                    [
                        positives.sample(n=pos_n, random_state=seed) if pos_n else positives.head(0),
                        negatives.sample(n=neg_n, random_state=seed) if neg_n else negatives.head(0),
                    ],
                    ignore_index=True,
                )

            for _, row in smell_subset.iterrows():
                code = fetch_code_snippet(row)
                records.append(
                    {
                        "sample_id": int(row["sample_id"]),
                        "type": row["type"],
                        "target_smell": smell,
                        "target_label": int(row[smell]),
                        "prompt_version": prompt_version,
                        "system": "Responda de forma objetiva",
                        "code": code,
                        "source": {
                            "repository": row["repository"],
                            "commit_hash": row["commit_hash"],
                            "path": row["path"],
                            "start_line": int(row["start_line"]),
                            "end_line": int(row["end_line"]),
                            "link": row["link"],
                        },
                    }
                )

    return records


def main():
    parser = argparse.ArgumentParser(description="Gera prompts.json a partir da base MLCQ sample por sample.")
    parser.add_argument("--source", default=str(DEFAULT_SOURCE_FILE), help="Caminho para o XLSX de origem")
    parser.add_argument("--output", default=str(DEFAULT_OUTPUT_FILE), help="Caminho para o prompts.json de saída")
    parser.add_argument("--prompt-version", default="v4_few_shot", choices=PROMPT_VERSIONS, help="Versão do prompt")
    parser.add_argument("--sample-id", type=int, default=None, help="Gera prompts apenas para um sample_id específico")
    parser.add_argument("--samples-per-smell", type=int, default=None, help="Amostras positivas e negativas por smell")
    parser.add_argument("--seed", type=int, default=42, help="Seed para amostragem")
    args = parser.parse_args()

    prepared = prepare_base(Path(args.source))

    if args.sample_id is not None:
        prepared = prepared[prepared["sample_id"] == args.sample_id].copy()
        if prepared.empty:
            raise SystemExit(f"sample_id {args.sample_id} não encontrado na base.")

    entries = build_entries(prepared, args.prompt_version, samples_per_smell=args.samples_per_smell, seed=args.seed)

    for index, entry in enumerate(entries, start=1):
        entry["id"] = index
        entry["prompt"] = build_prompt(entry["code"], entry["type"], entry["prompt_version"])

    output_path = Path(args.output)
    output_path.parent.mkdir(parents=True, exist_ok=True)
    output_path.write_text(json.dumps(entries, ensure_ascii=False, indent=2), encoding="utf-8")

    if args.sample_id is not None:
        print(f"Gerados {len(entries)} prompts para sample_id {args.sample_id} em {output_path}")
    else:
        print(f"Gerados {len(entries)} prompts em {output_path}")


if __name__ == "__main__":
    main()