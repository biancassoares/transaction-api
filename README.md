# 💳 Transaction API

REST API developed with **Java and Spring Boot** as part of my backend development studies.

I started this project as a simple transaction CRUD and then improved it step by step while learning more about how a REST API can be structured.

## 🚀 About the project

The API allows users to:

* Create a transaction
* List all transactions
* Find a transaction by ID
* Update a transaction
* Delete a transaction

Besides the basic CRUD operations, I also added some concepts that I have been studying with Spring Boot:

* Request and response DTOs
* Data validation
* Global exception handling
* Custom exception for transactions that are not found
* Unit tests with JUnit and Mockito
* API documentation with Swagger/OpenAPI

## 🛠️ Technologies

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

## 📦 Transaction structure

Each transaction contains:

* `id`
* `description`
* `amount`
* `category`
* `date`

## 🔗 Endpoints

| Method   | Endpoint             | Description              |
| -------- | -------------------- | ------------------------ |
| `POST`   | `/transactions`      | Create a transaction     |
| `GET`    | `/transactions`      | List all transactions    |
| `GET`    | `/transactions/{id}` | Find a transaction by ID |
| `PUT`    | `/transactions/{id}` | Update a transaction     |
| `DELETE` | `/transactions/{id}` | Delete a transaction     |

## 📤 DTOs

To avoid using the `Transaction` entity directly in requests and responses, I created:

* `TransactionRequest` for data received by the API
* `TransactionResponse` for data returned by the API

The flow is basically:

```text
JSON → TransactionRequest → Transaction → Database

Database → Transaction → TransactionResponse → JSON
```

## ✅ Validation

The data received by the API is validated before being saved.

Some of the validation rules are:

* Description is required
* Amount is required and must be greater than zero
* Category is required
* Date is required

When invalid data is sent, the API returns `400 Bad Request` with messages showing which fields are invalid.

## ⚠️ Exception handling

I created a `GlobalExceptionHandler` to centralize error handling in the application.

I also created a custom `TransactionNotFoundException`, which is used when a transaction cannot be found.

In this case, the API returns:

```json
{
  "error": "Transaction not found"
}
```

with status:

```text
404 Not Found
```

## 🧪 Tests

I created unit tests for the `TransactionService` using **JUnit and Mockito**.

The main scenarios tested are:

* Finding an existing transaction
* Trying to find a transaction that does not exist
* Creating a transaction
* Listing transactions
* Updating a transaction
* Deleting a transaction

Mockito is used to mock the repository, so the service can be tested without connecting to the real database.

## 📚 Swagger

I also added API documentation using Swagger/OpenAPI.

With the application running, the Swagger interface is available at:

```text
http://localhost:8080/swagger-ui.html
```

## 🗂️ Project structure

The project follows a layered structure:

```text
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

The application is organized into packages such as:

```text
controller
service
repository
entity
dto
exception
```

## 🎯 Purpose

This project was created as part of my **Java and Spring Boot studies**.

My goal was to start with a simple CRUD and improve it little by little while practicing concepts such as DTOs, validation, exception handling, testing and API documentation.

It was also a way for me to practice organizing a backend project and better understand the responsibility of each layer in a Spring Boot application.
