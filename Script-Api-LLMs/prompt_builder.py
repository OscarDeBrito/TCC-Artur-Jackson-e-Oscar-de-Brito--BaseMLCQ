PROMPT_VERSIONS = ["zero_shot", "few_shot"]

PROMPT_VERSION_LABELS = {
    "zero_shot": "Zero shot",
    "few_shot": "Few shot",
}

PROMPT_VERSION_ALIASES = {
    "v2": "zero_shot",
    "v4_few_shot": "few_shot",
}


def normalize_type(value):
    return str(value).strip().lower()


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
    String result = "Rental Record for " + name() + "\\n";

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

        result += "\\t" + each.getMovie().getTitle() + "\\t" + String.valueOf(thisAmount) + "\\n";
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
        raise ValueError("A entrada precisa ter 'prompt' ou 'code'.")

    prompt_version = normalize_type(entry.get("prompt_version", "few_shot"))
    prompt_version = PROMPT_VERSION_ALIASES.get(prompt_version, prompt_version)
    snippet_type = normalize_type(entry.get("type"))

    if snippet_type == "class":
        builders = {
            "zero_shot": prompt_class_v2,
            "few_shot": prompt_class_few_shot,
        }
    elif snippet_type == "function":
        builders = {
            "zero_shot": prompt_function_v2,
            "few_shot": prompt_function_few_shot,
        }
    else:
        raise ValueError(f"Tipo de snippet inválido: {entry.get('type')!r}")

    if prompt_version not in builders:
        raise ValueError(f"Versão de prompt inválida: {prompt_version!r}")

    return builders[prompt_version](code)
