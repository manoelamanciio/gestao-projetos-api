# SGP API - Sistema de Gestão de Projetos

API REST para gerenciamento de **projetos, tarefas e usuários**, desenvolvida com **Java e Spring Boot** e persistência em **MySQL**. Faz parte de um projeto Full Stack, com front-end em React.js.

> 🖥️ **Front-end:** [gestao-projetos-web](https://github.com/manoelamanciio/gestao-projetos-web)

## Funcionalidades

- Cadastro, consulta e exclusão de **usuários**, com CPF mascarado e idade calculada na resposta.
- Cadastro, consulta, filtro e exclusão de **projetos**, vinculados a um usuário responsável.
- Cadastro, consulta, atualização e exclusão de **tarefas**, vinculadas a um projeto e, opcionalmente, a um usuário.
- Filtros de projetos por **status**, por **período de data de início** e por **responsável**.
- Validação de dados de entrada (Bean Validation, incluindo `@CPF`) com respostas de erro padronizadas.
- Documentação interativa com **Swagger / OpenAPI**.

## Tecnologias

- Java 25
- Spring Boot 4.1.0 (Web MVC, Data JPA, Validation)
- MySQL e Hibernate
- Lombok
- SpringDoc OpenAPI (Swagger UI)
- Maven

## Arquitetura

O projeto segue arquitetura em camadas:

```
src/main/java/br/com/treina/recife/sgp/api
├── config        # CORS e OpenAPI
├── controller    # Endpoints REST
├── service       # Regras de negócio
├── repository    # Acesso a dados (Spring Data JPA)
├── model         # Entidades e enums
├── dto           # Objetos de requisição e resposta (records)
└── exceptions    # Tratamento global de erros
```

## Modelo de dados

- **Usuário** (`TB_USUARIOS`): nome, CPF, e-mail, senha, data de nascimento, status (`ATIVO`, `INATIVO`, `BLOQUEADO`).
- **Projeto** (`TB_PROJETOS`): nome, descrição, data de início, data de conclusão, status (`ATIVO`, `EM_ANDAMENTO`, `CONCLUIDO`, `CANCELADO`), responsável (usuário).
- **Tarefa** (`TB_TAREFAS`): título, descrição, data de criação, data de conclusão, prioridade (`BAIXA`, `MEDIA`, `ALTA`), status (`PENDENTE`, `EM_ANDAMENTO`, `CONCLUIDA`), projeto e usuário.

Relacionamentos: um usuário é responsável por vários projetos, e um projeto tem várias tarefas.

## Endpoints

### Usuários (`/usuarios`)

| Método | Rota | Descrição |
|---|---|---|
| POST | `/usuarios` | Cadastra um usuário |
| GET | `/usuarios` | Lista todos os usuários |
| GET | `/usuarios/{id}` | Busca um usuário pelo id |
| DELETE | `/usuarios/{id}` | Exclui um usuário |

### Projetos (`/projetos`)

| Método | Rota | Descrição |
|---|---|---|
| POST | `/projetos` | Cadastra um projeto |
| GET | `/projetos` | Lista todos os projetos |
| GET | `/projetos/{id}` | Busca um projeto pelo id |
| DELETE | `/projetos/{id}` | Exclui um projeto |
| GET | `/projetos/filtroPorStatus?status=ATIVO` | Filtra por status |
| GET | `/projetos/filtroPelaDataInicio?startDate=2026-01-01&endDate=2026-12-31` | Filtra por período de início |
| GET | `/projetos/responsavel/{idResponsavel}` | Lista os projetos de um responsável |

### Tarefas (`/tarefas`)

| Método | Rota | Descrição |
|---|---|---|
| POST | `/tarefas` | Cadastra uma tarefa |
| GET | `/tarefas` | Lista todas as tarefas |
| GET | `/tarefas/{id}` | Busca uma tarefa pelo id |
| PUT | `/tarefas/{id}` | Atualiza uma tarefa |
| DELETE | `/tarefas/{id}` | Exclui uma tarefa |

### Exemplo de requisição

`POST /projetos`

```json
{
  "nome": "Portal do Cliente",
  "descricao": "Novo portal de atendimento",
  "dataInicio": "2026-10-22",
  "dataConclusao": "2027-03-30",
  "status": "ATIVO",
  "idResponsavel": 1
}
```

Resposta de erro de validação (`400`):

```json
{
  "dataHora": "2026-10-22T23:00:00",
  "status": 400,
  "erro": "Campos Inválidos",
  "campos": [
    { "campo": "nome", "mensagem": "não deve estar em branco" }
  ]
}
```

## Como executar

### Pré-requisitos

- JDK 25
- MySQL 8 ou superior
- Maven (ou use o `mvnw` incluído no projeto)

### Passo a passo

1. Clone o repositório:
   ```bash
   git clone https://github.com/manoelamanciio/gestao-projetos-api.git
   cd gestao-projetos-api
   ```

2. Crie o banco de dados no MySQL:
   ```sql
   CREATE DATABASE sgp_db;
   ```

3. Defina as variáveis de ambiente com os dados do seu MySQL:

   | Variável | Descrição | Padrão |
   |---|---|---|
   | `MYSQL_HOST` | Host do MySQL | `localhost` |
   | `DB_USER` | Usuário do MySQL | `root` |
   | `DB_PASSWORD` | Senha do MySQL | (obrigatória) |

   Exemplo no Linux ou Mac:
   ```bash
   export DB_PASSWORD=sua_senha
   ```
   No Windows (PowerShell):
   ```powershell
   $env:DB_PASSWORD="sua_senha"
   ```

4. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Acesse:
   - API: http://localhost:8080
   - Swagger UI: http://localhost:8080/swagger-ui.html

As tabelas são criadas automaticamente na primeira execução (`spring.jpa.hibernate.ddl-auto=update`).

### CORS

Por padrão, a API aceita requisições do front-end em `http://localhost:5173` (Vite).

## Próximos passos

- Criptografar senhas com BCrypt (Spring Security) e adicionar autenticação com JWT.
- Usar DTOs de resposta em todos os endpoints.
- Adicionar endpoints de atualização (`PUT`) para usuários e projetos.
- Ampliar a cobertura de testes automatizados.
- Criar exceções de negócio com respostas `404` padronizadas.

## Autor

**Manoel Amancio da Silva Neto**
Estudante de Engenharia de Software e Desenvolvedor Full Stack (formado pela Treina Recife)

[LinkedIn](https://www.linkedin.com/in/manoellamanciio) | [GitHub](https://github.com/manoelamanciio) | manoel.amanciio@gmail.com
