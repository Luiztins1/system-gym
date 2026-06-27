# 🏋️‍♂️ System Gym API

## API RESTful para gerenciamento de academias, cobrindo o ciclo de vida de matrículas de alunos, controle de planos e auditoria automatizada de registros.
Essa API foi desenvolvida como foco em atender demandas básicas de uma academia, como registro de alunos, matriculas e planos.

# 🛠 Tecnologias e ferramentas
* **Linguagem:** Java 21
* **Framework Principal:** Spring Boot 3.x (Spring Data JPA, Spring Web, Spring Validation, Spring DevTools)
* **Banco de dados:** PostgreSQL
* **Ferramentas de Suporte:** Docker (para criar um imagem que possa rodar em qualquer máquina), Postman (um cliente para testar as requisições HTTP)

# 🏗 Arquitetura
* **Padrão de Arquitetural:** MVC Pattern (Padrão organizacional de resposabilidades), SOLID SRP (Single Responsibility Principle) e DIP (Dependency Inversion Principle).
* **Padrão de Transferência:** DTO Pattern (Padrão para transferência de dados), Mapper Pattern (Padrão utilizado para converter dados para entidades e entidades para dados; obs: feito manualmente)

# 📝 Documentação dos Endpoints
A API possui documentação interativa e testável integrada com Swagger UI (OpenAPI 3). Para visualizar todos os endpoints em tempo real, os schemas de dados (StudentDTO, PlanDTO, etc.) e realizar testes diretamente pelo navegador, siga os passos:

Suba a aplicação localmente (docker compose).

Acesse o endereço no seu navegador: http://localhost:8080/swagger-ui.html

# ⚙️ Configuração das Variáveis de Ambiente

A API utiliza variáveis de ambiente para proteger dados sensíveis (como credenciais do banco de dados). Antes de rodar, certifique-se de ter um arquivo de configuração com as seguintes chaves:
* `DB_USERNAME`: Usuário do banco de dados (ex: `gym`)
* `DB_PASSWORD`: Senha do banco de dados (ex: `gym1611`)

Por motivos de segurança, as credenciais reais do banco de dados e chaves do sistema estão configuradas em arquivos locais protegidos pelo `.gitignore`. 

O repositório disponibiliza um arquivo modelo chamado `application.example.yml` e um `docker-compose.example.yaml` contendo a estrutura necessária. Para rodar o projeto localmente na sua IDE, siga os passos abaixo:

### 💾 Application
1. Na raiz do pacote de configurações (`src/main/resources`), **duplique** o arquivo `application.example.yml`.
2. Renomeie a cópia para **`application.yml`** (este nome é ignorado pelo Git e lido pelo Spring).
3. Abra o novo `application.yml` e preencha as variáveis com as suas credenciais locais:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/gym
    username: ${DB_USERNAME:seu_usuario_aqui}
    password: ${DB_PASSWORD:sua_senha_aqui}
 ```

### 🐋 Docker-Compose
1. Na raiz do pacote de configurações, **duplique** o arquivo `docker-compose.example.yml`.
2. Renomeie a cópia para **`docker-compose.yml`** (este nome é ignorado pelo Git e lido pelo Spring).

* **Observação:** Abra o `docker-compose.yml` e substituia a porta para `5432:5432`, caso não tenha o PostgreSQL instalado localmente, caso contrário, permaneça o arquivo como está:

```yaml
  system-gym:
    image: postgres:16
    volumes:
      - ./Postgres:/var/lib/postgresql/data
    environment:
      POSTGRES_DB: gym
      POSTGRES_USER: ${DB_USERNAME}
      POSTGRES_PASSWORD: ${DB_PASSWORD}
    ports:
      - "5432:5432"
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U ${DB_USERNAME}"]
      interval: 10s
      timeout: 5s
      retries: 5
```

## 🚀 Como Executar

## 🐋 Docker Compose
1. Certifique-se de que o seu arquivo `cred.env` (ou `.env`) está na raiz do projeto com as variáveis preenchidas.
2. Suba os containers com o comando:
   ```bash
   docker compose up --build
