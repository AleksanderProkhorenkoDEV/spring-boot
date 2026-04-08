# Spring Boot Practice Projects

A collection of Spring Boot practice projects covering core concepts such as REST APIs, JPA persistence, authentication, and layered architecture.

---

## Projects

### first-app

An introductory Spring Boot application with two endpoints demonstrating basic request mapping.

- One endpoint using a standard route
- One endpoint using a path variable

**Concepts:** `@RestController`, `@GetMapping`, `@PathVariable`

---

### palindrome

A Spring Boot controller exposing a single endpoint that checks whether a given word is a palindrome.

**Concepts:** `@RestController`, request parameters, string manipulation logic

---

### books

A REST API for managing a book catalog, built without a database. Persistence is handled in-memory using an `ArrayList`.

The project follows a layered architecture:

- Controller layer
- Service interface and implementation
- Repository interface and implementation

**Concepts:** Dependency injection, service layer, repository pattern, in-memory persistence

---

### crud-jpa

A Spring Boot project implementing full CRUD operations connected to a MySQL database using Spring Data JPA.

**Concepts:** `@Entity`, `JpaRepository`, `@RestController`, MySQL datasource configuration, HTTP methods (GET, POST, PUT, DELETE)

---

### auth

A Spring Boot REST API implementing user authentication with registration and login functionality.

**Concepts:** Spring Security, JWT, `UserDetailsService`, password encoding, protected endpoints

---

## Requirements

- Java 17 or higher
- Maven
- MySQL (required for `crud-jpa`)

## Getting Started

Clone the repository and navigate into any project folder:

```bash
git clone https://github.com/your-username/spring-boot-practice.git
cd spring-boot-practice/<project-name>
mvn spring-boot:run
```

For `crud-jpa`, configure your database connection in `src/main/resources/application.properties` before running.
