# Instruções para Agentes de IA - ExerciciosAlura

## Visão Geral

Projeto Maven multi-módulo com exercícios práticos de backend Java da Alura. Cada módulo é um cliente HTTP independente que consome APIs públicas (livros, moedas, refeições) seguindo padrões consistentes de requisição/resposta.

**Stack:** Java 25, Maven, Gson 2.11.0, HttpClient nativo (java.net.http)

## Arquitetura Multi-Módulo

Estrutura padrão de cada módulo cliente:

```
{modulo}/
├── pom.xml              (herda de parent, apenas coordenadas do módulo)
└── src/main/java/
    └── org/example/
        ├── Main.java    (entry point - instancia cliente → request → resposta)
        ├── *DTO.java    (records para desserializar JSON)
        └── clientHttpUtil/
            ├── Cliente*.java     (fabrica de HttpRequest)
            ├── Requisicao.java   (wrapper de HttpRequest)
            └── Resposta.java     (wrapper de HttpResponse + parsing)
```

**Módulos existentes:**
- `books-client`: Google Books API
- `meal-client`: TheMealDB API  
- `moeda-client`: CoinGecko API

## Padrões Críticos

### 1. **DTOs como Records**
Use `record` (Java 16+) para mapear JSON respostas. Records são imutáveis e funcionam nativamente com Gson:

```java
public record MealsDTO(List<meals> meals) {
    public record meals(String idMeal, String strMeal, ...) {}
}
```

**Regra:** O record deve espelhar exatamente a estrutura JSON da API (nomes de campos em camelCase ou como retorna a API).

### 2. **Fluxo Requisição-Resposta**
Padrão invariável em todos os clientes:

```java
// 1. Cliente constrói URL (pode pedir input do usuário)
ClienteHttp client = new ClienteHttp();
String url = client.criadorUrl(); // ou URL fixa

// 2. Requisição wrapper cria HttpRequest
Requisicao req = new Requisicao(url);

// 3. Cliente HTTP envia
HttpResponse<String> httpResp = clienteHTTP.send(req.getRequest(), ofString());

// 4. Resposta wrapper parseia Gson + armazena DTO
Resposta resposta = new Resposta(httpResp);
resposta.resultado(); // exibe resultado
```

### 3. **Tratamento de Múltiplos Formatos JSON**
Em `moeda-client/reposnse.java`, há lógica para diferenciar listas de objetos únicos:

```java
if (body.startsWith("[")) {
    // é lista: use TypeToken para desserializar genéricos
    Type listType = new TypeToken<List<ListaMoedas>>(){}.getType();
} else {
    // é objeto único: use .class direto
    json.fromJson(body, MoedaIdDTO.class);
}
```

**Aplique este padrão ao implementar novos Resposta wrappers.**

### 4. **Codificação de URLs**
APIs com parâmetros de busca requerem `URLEncoder.encode()` (UTF-8):

```java
String encoded = URLEncoder.encode(usuarioInput, "UTF-8");
String url = "https://api.example.com/search?q=" + encoded;
```

Sempre faça `.trim().toLowerCase()` no input do usuário antes de codificar.

### 5. **Padrão de Nomes (Inconsistência Conhecida)**
⚠️ **Problema recorrente:** Nomes de classes não seguem convenção Java:
- `reposnse.java` (typo: deveria ser `Resposta`)
- `Requisição.java` vs `Requisicao.java` (com/sem til)
- Diretórios: `ClienteHttpUtil/` vs `clientHttpUtil/` (maiúscula inconsistente)

**Ao criar novos arquivos:** Use PascalCase e sempre nomeie `Response/Requisition` em inglês para consistência.

## Build e Execução

```bash
# Compilar todo projeto
mvn clean compile

# Compilar módulo específico
mvn -pl moeda-client clean compile

# Executar módulo (após compilar)
cd moeda-client
mvn exec:java -Dexec.mainClass="org.example.Main"

# Ou via IDE: clique direito Main.java → Run
```

**Dependência Maven:** Projeto pai (`pom.xml`) declara Gson 2.11.0 — todos módulos herdam automaticamente.

## Convenções Específicas

1. **Imports:** Sempre use `import java.net.http.*` (não dependências externas para HTTP)
2. **Gson:** Instancie com `.setPrettyPrinting()` para logs legíveis
3. **Scanner:** Use `new Scanner(System.in)` para input, sempre `.trim().toLowerCase()` antes de usar
4. **Exceções:** Métodos HTTP lançam `IOException, InterruptedException` — propague no throws de `main()`
5. **Output:** Use `System.out.println()` com separadores visuais (linhas tracejadas para sessões)

## Arquivos-Chave para Referência

- **Padrão de resposta bem-formada:** `/moeda-client/src/main/java/org/example/clientHttpUtil/reposnse.java` (trata múltiplos tipos)
- **Padrão DTO:** `/books-client/src/main/java/DTO/BooksResponse.java` (records aninhados)
- **Padrão cliente:** `/moeda-client/src/main/java/org/example/clientHttpUtil/ClienteHttp.java` (requisições variadas)

## Armadilhas Comuns

- ❌ Tentar serializar com Gson sem DTO → use `TypeToken` para genéricos
- ❌ Não codificar parâmetros de URL → URLs quebram com espaços/caracteres especiais
- ❌ Ignorar `response.body().trim()` → JSONs com espaçamento quebram parsing
- ❌ Instanciar Gson por objeto → considere `static` para economizar heap
- ❌ Esquecer que `HttpClient` não é automático → crie uma instância privada no cliente

## Workflow para Novos Módulos

1. Crie diretório `novo-modulo/`
2. Adicione `pom.xml` com coordenadas e parent (copie de `moeda-client`)
3. Estruture `src/main/java/org/example/` com Main.java
4. Crie DTO record espelhando resposta JSON da API
5. Implemente Cliente com método que retorna `HttpResponse<String>`
6. Implemente Resposta que parseia com Gson no construtor
7. Chame da Main: cliente → requisição → resposta
8. Declare módulo em `pom.xml` parent dentro de `<modules>`
