# MLCQ - Limpeza, Validacao e Testes com LLMs

Repositorio contendo o processamento e validacao da base **MLCQ (Multi-Level Code Smell Samples)**, alem da geracao de prompts e execucao de experimentos com LLMs para deteccao de code smells.

## Visao Geral

O fluxo atual do projeto funciona em duas etapas:

1. Gerar a base de prompts (`prompts.json`) a partir da planilha ground truth.
2. Executar esses prompts em uma ou mais LLMs e salvar os resultados em arquivos JSONL.

## Estrutura do Projeto

### Raiz

- `README.md`: documentacao principal do projeto.
- `Analise-base-MLCQ/`: notebooks, planilhas intermediarias e base final usada como fonte para os prompts.
- `Script-Api-LLMs/`: scripts de geracao de prompts e execucao nas LLMs.

### Pasta Analise-base-MLCQ

- `01_preprocessamento_MLCQ.ipynb`: preprocessamento inicial da base.
- `02_construcao_base_avaliacoes.ipynb`: consolidacao das avaliacoes.
- `03_analise_e_ground_truth.ipynb`: analises finais e geracao da ground truth.
- `mini_experimento_prompts_smells.ipynb`: notebook de mini experimento para prompts.
- `MLCQ_ground_truth_completo.xlsx`: base principal consumida pelo gerador de prompts.
- `MLCQ_ground_truth_classes.xlsx`: recorte da base para entidades do tipo classe.
- `MLCQ_ground_truth_methods.xlsx`: recorte da base para entidades do tipo metodo.
- `MLCQCodeSmellSamples.xlsx`, `MLCQ_status.xlsx`, `MLCQ_status_200.xlsx`, `mapa_repos_sonar.xlsx`: arquivos auxiliares de apoio ao processo analitico.

### Pasta Script-Api-LLMs

- `generate_prompts_json.py`: gera o `prompts.json` a partir da planilha ground truth.
- `prompt_builder.py`: constroi o texto final do prompt em duas versoes (`zero_shot` e `few_shot`).
- `main.py`: executa os prompts nos provedores selecionados e controla arquivos de saida.
- `pipeline.py`: orquestra a chamada dos provedores e escrita de resultados.
- `io_helpers.py`: utilitarios de IO (leitura JSON, escrita JSONL, timestamp).
- `providers/openai_provider.py`: cliente e chamada da OpenAI.
- `providers/gemini_provider.py`: cliente e chamada do Gemini.
- `providers/deepseek_provider.py`: cliente e chamada do DeepSeek.
- `providers/claude_provider.py`: cliente e chamada do Claude.
- `requirements.txt`: dependencias Python dos scripts.
- `prompts.json`: arquivo de prompts gerados para execucao.
- `outputs*.jsonl`: arquivos de resultados das execucoes.

## Configuracao do Ambiente

Na pasta raiz do projeto:

```powershell
python -m venv .venv
& ".\\.venv\\Scripts\\Activate.ps1"
& ".\\.venv\\Scripts\\python.exe" -m pip install -r .\\Script-Api-LLMs\\requirements.txt
```

Configure as variaveis no arquivo `.env` dentro de `Script-Api-LLMs`:

- `OPENAI_API_KEY`
- `GEMINI_API_KEY`
- `DEEPSEEK_API_KEY`
- `ANTHROPIC_API_KEY`

## Geracao de Prompts

Entrar na pasta:

```powershell
cd .\Script-Api-LLMs
```

### Possibilidades

1. Base completa:

```powershell
python generate_prompts_json.py
```

2. Sample especifico:

```powershell
python generate_prompts_json.py --sample-id 3698323
```

3. Quantidade limitada por tipo (`class` e `function`):

```powershell
python generate_prompts_json.py --samples-per-type 100 --seed 42
```

4. Fonte e saida customizadas:

```powershell
python generate_prompts_json.py --source ..\Analise-base-MLCQ\MLCQ_ground_truth_completo.xlsx --output prompts_teste.json
```

### Regras atuais da geracao

- O script deduplica por `sample_id` (cada sample entra uma vez por tipo).
- Para cada sample unico, sao geradas sempre duas versoes de prompt:
	- `zero_shot`
	- `few_shot`

## Execucao nas LLMs

Ainda em `Script-Api-LLMs`, execute:

### 1. Rodar todos os provedores

```powershell
python main.py
```

Por padrão, essa execucao roda `openai`, `deepseek` e `claude`. Se quiser incluir o `gemini`, passe explicitamente `--providers`.

Provedores disponiveis:

- `openai`
- `deepseek`
- `claude`

### 2. Rodar apenas um provedor

```powershell
python main.py --providers gemini
```

### 3. Rodar combinacao de provedores

```powershell
python main.py --providers openai,gemini
```

### 4. Definir nome base de saida

```powershell
python main.py --providers gemini,claude --output resultados.jsonl
```

## Saida dos Resultados

A saida e sempre separada por provedor.

Exemplo com `--output outputs.jsonl`:

- `outputs_openai.jsonl`
- `outputs_gemini.jsonl`
- `outputs_deepseek.jsonl`
- `outputs_claude.jsonl`

Exemplo com `--output resultados.jsonl`:

- `resultados_openai.jsonl`
- `resultados_gemini.jsonl`
- `resultados_deepseek.jsonl`
- `resultados_claude.jsonl`

## Combinacoes de Execucao Recomendadas

1. Teste rapido (1 sample, 1 LLM):

```powershell
python generate_prompts_json.py --sample-id 3698323
python main.py --providers gemini
```

2. Execucao completa (base inteira, 1 LLM):

```powershell
python generate_prompts_json.py
python main.py --providers gemini
```

## Autores

- Artur Jackson
- Oscar de Brito

