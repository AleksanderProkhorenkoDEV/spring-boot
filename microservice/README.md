# Microservice

A Spring Boot RESTful microservice responsible for data persistence. It connects directly to a MySQL database and exposes a REST API that other services, such as the Consumer Service, call to retrieve or manipulate data.

---

## Project Structure

```
src/main/java/com/example/microservice/
├── controllers/
│   └── (REST controllers)
├── entities/
│   └── (JPA entities)
├── repository/
│   └── (Spring Data repositories)
├── services/
│   └── (Business logic)
└── MicroserviceApplication.java
```

---

## Package Descriptions

### controllers

Contains the REST controllers that expose the API endpoints of this microservice. Each controller maps incoming HTTP requests to the appropriate service method and returns the corresponding HTTP response. This layer has no business logic — it only handles routing and response formatting.

### entities

Contains the JPA entity classes that represent the database tables. Each entity maps directly to a MySQL table and defines the columns, data types, and relationships between tables. These classes are used by the repository layer to perform database operations.

### repository

Contains the Spring Data JPA repository interfaces. These interfaces extend `JpaRepository` (or similar) and provide ready-to-use CRUD operations against the MySQL database without requiring manual SQL queries. Custom queries can also be defined here when the default methods are not sufficient.

### services

Contains the service classes that hold the business logic of the application. The controllers delegate all processing to this layer, which in turn calls the repository layer to read or write data. Keeping logic here, separate from the controllers, makes the code easier to test and maintain.

---

## How It Works

1. An external client (such as the Consumer Service) sends an HTTP request to one of the endpoints exposed by the controllers.
2. The controller delegates the call to the corresponding service method.
3. The service applies any business logic and calls the repository to interact with the MySQL database.
4. The result is mapped and returned up the chain as an HTTP response.

---

## Database

This service connects to a **MySQL** database. Connection settings such as the URL, username, and password are defined in `src/main/resources/application.properties`.

---

## Technologies

- Java
- Spring Boot
- Spring Data JPA
- Spring Web (REST)
- MySQL
- Maven / Gradle
