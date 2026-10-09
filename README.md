# Loginy

## 1. Introdução

Aplicação desenvolvida em Java Spring Boot, integrada com Spring Security para autenticação e autorização, Thymeleaf e Tailwind CSS para uma interface web, além de MongoDB e Redis para persistência e gerenciamento de sessões.

## 2. Variáveis de Ambiente (.env)

Para executar o projeto utilizando o Docker Compose, crie um arquivo chamado .env na raiz do projeto contendo as seguintes variáveis de ambiente preenchidas com os seus dados:

```md
# Configurações do Banco de Dados (MongoDB)
DATABASE_USER=user
DATABASE_PASSWORD=password

# Configurações do Mongo Express (Interface Web do Banco)
MONGO_EXPRESS_USERNAME=user
MONGO_EXPRESS_PASSWORD=password

# Configurações do Redis
REDIS_PASSWORD=password

# Configuração do Servidor de E-mail (Utilizado para envio de convites e confirmação)
EMAIL_HOST=host
EMAIL_PORT=port
EMAIL_USERNAME=email
EMAIL_PASSWORD=senha_de_app
```

## 3. Como Executar o Projeto com Docker Compose

A execução do sistema e de seus serviços de suporte (MongoDB, Redis, Mongo Express) é totalmente automatizada via Docker Compose.

### Pré-requisitos

* Docker e Docker Compose instalados em sua máquina.

* Java JDK 21 (caso deseje rodar a aplicação fora do container durante o desenvolvimento).

### Passos para Execução:

* Certifique-se de ter criado o arquivo .env na raiz do projeto conforme a seção anterior.

* Suba todos os containers necessários utilizando o Docker Compose:
```bash
docker-compose up --build -d
```

Acompanhe os logs da aplicação para garantir que a conexão com o MongoDB e o Redis foi estabelecida com sucesso:

```bash
docker-compose logs -f app
```

### Acesse a aplicação no seu navegador:

* Aplicação Web: http://localhost:8080

* Mongo Express: http://localhost:8081
