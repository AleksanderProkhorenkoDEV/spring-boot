# Consumer Service

A Spring Boot microservice that acts as a consumer, delegating HTTP calls to an external product microservice. It uses WebClient for non-blocking reactive communication and exposes its own REST endpoint to the outside world.

---

## Project Structure

```
src/main/java/com/example/consumerservice/
├── config/
│   └── WebClientConfig.java
├── controller/
│   └── ConsumerController.java
├── dto/
│   └── (Data Transfer Objects)
├── product_client/
│   └── ProductClient.java
└── ConsumerserviceApplication.java
```

---

## Package Descriptions

### config

Contains the WebClient configuration class (`WebClientConfig`). This class defines and registers a `WebClient` bean with Spring's application context, typically setting the base URL of the target microservice, default headers, and any other client-level settings such as timeouts or filters. Centralizing this configuration avoids repeating setup logic across the codebase.

### controller

Contains the REST controller that exposes the consumer's endpoint. When a request arrives here, the controller delegates the call to the `product_client` layer. It does not contain business logic or HTTP communication details — its sole responsibility is to receive the incoming request and return the response to the caller.

### dto

Contains the Data Transfer Object classes used to model the data flowing through the service. These classes define the shape of the data received from the external microservice and, if necessary, the shape of the data returned to the consumer's own clients. Using DTOs decouples the internal representation from the external API contract.

### product_client

Contains the client class (`ProductClient`) responsible for making the actual HTTP call to the external microservice. It uses the configured `WebClient` bean to send the request to the target URL, receives the response, and maps it into the appropriate DTO. This layer isolates all outbound communication logic in one place, making it easy to test, mock, or replace independently of the controller.

---

## How It Works

1. An external client sends an HTTP request to the endpoint exposed by `ConsumerController`.
2. The controller delegates the call to `ProductClient`.
3. `ProductClient` uses the `WebClient` (configured in `WebClientConfig`) to call the external product microservice.
4. The response is mapped into a DTO and returned up the chain to the original caller.

---

## Technologies

- Java
- Spring Boot
- Spring WebFlux (WebClient)
- Maven / Gradle
