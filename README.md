# 💳 Transaction API

API REST desenvolvida com **Java e Spring Boot** como parte dos meus estudos de desenvolvimento backend.

Comecei esse projeto como um CRUD simples de transações e fui melhorando aos poucos conforme estudava novos conceitos e entendia melhor como uma API REST pode ser estruturada.

## 🚀 Sobre o projeto

A API permite:

* Criar uma transação
* Listar todas as transações
* Buscar uma transação por ID
* Atualizar uma transação
* Deletar uma transação

Além das operações básicas de CRUD, também implementei alguns conceitos que venho estudando com Spring Boot:

* DTOs de entrada e saída
* Validação de dados
* Tratamento global de exceções
* Exceção personalizada para transações não encontradas
* Testes unitários com JUnit e Mockito
* Documentação da API com Swagger/OpenAPI

## 🛠️ Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Docker
* Jakarta Validation
* JUnit
* Mockito
* Swagger / OpenAPI
* Maven

## 📦 Estrutura da Transaction

Cada transação possui:

* `id`
* `description`
* `amount`
* `category`
* `date`

## 🔗 Endpoints

| Método   | Endpoint             | Descrição                   |
| -------- | -------------------- | --------------------------- |
| `POST`   | `/transactions`      | Criar uma transação         |
| `GET`    | `/transactions`      | Listar todas as transações  |
| `GET`    | `/transactions/{id}` | Buscar uma transação por ID |
| `PUT`    | `/transactions/{id}` | Atualizar uma transação     |
| `DELETE` | `/transactions/{id}` | Deletar uma transação       |

## 📤 DTOs

Para evitar o uso direto da entidade `Transaction` nas requisições e respostas da API, criei:

* `TransactionRequest` para os dados recebidos
* `TransactionResponse` para os dados retornados

O fluxo funciona de forma simplificada assim:

```text
JSON → TransactionRequest → Transaction → Banco de dados

Banco de dados → Transaction → TransactionResponse → JSON
```

## ✅ Validação

Os dados recebidos pela API são validados antes de serem salvos.

Algumas das regras implementadas são:

* Descrição obrigatória
* Valor obrigatório e maior que zero
* Categoria obrigatória
* Data obrigatória

Quando algum dado inválido é enviado, a API retorna `400 Bad Request` com mensagens indicando quais campos precisam ser corrigidos.

Exemplo:

```json
{
  "description": "Description is required",
  "amount": "Amount must be greater than zero"
}
```

## ⚠️ Tratamento de exceções

Criei um `GlobalExceptionHandler` para centralizar o tratamento dos erros da aplicação.

Também criei uma exceção personalizada chamada `TransactionNotFoundException`, utilizada quando uma transação não é encontrada.

Nesse caso, a API retorna:

```json
{
  "error": "Transaction not found"
}
```

com status:

```text
404 Not Found
```

## 🧪 Testes

Criei testes unitários para o `TransactionService` utilizando **JUnit e Mockito**.

Os principais cenários testados foram:

* Buscar uma transação existente
* Buscar uma transação que não existe
* Criar uma transação
* Listar transações
* Atualizar uma transação
* Deletar uma transação

O Mockito foi utilizado para simular o repository, permitindo testar a lógica do service sem precisar acessar o banco de dados real.

## 📚 Swagger

Também adicionei documentação da API utilizando Swagger/OpenAPI.

Com a aplicação rodando, a interface do Swagger pode ser acessada em:

```text
http://localhost:8080/swagger-ui.html
```

## 🗂️ Estrutura do projeto

O projeto segue uma arquitetura em camadas:

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
Banco de dados
```

A aplicação também está organizada em packages como:

```text
controller
service
repository
entity
dto
exception
```

## 🎯 Objetivo

Esse projeto foi desenvolvido como parte dos meus estudos de **Java e Spring Boot**.

Meu objetivo foi começar com um CRUD simples e evoluir o projeto aos poucos, praticando conceitos como DTOs, validação, tratamento de exceções, testes e documentação de APIs.

Também utilizei o projeto para entender melhor a responsabilidade de cada camada e como organizar uma aplicação backend de forma mais clara.
