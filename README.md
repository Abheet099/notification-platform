# Notification Platform

Maven-based Spring Boot 3 MVP for an email-only notification platform.

## Requirements

- Java 21
- Maven 3.9+
- Docker and Docker Compose

## MVP Scope

- Email notifications only.
- Mock email provider.
- Asynchronous processing with Spring `@Async`.
- PostgreSQL with Flyway-managed schema.
- No Kafka, Redis, authentication, or retry logic.

## Start PostgreSQL

```bash
docker compose up -d postgres
```

PostgreSQL will be available at `localhost:5432` with:

- Database: `notification_platform`
- User: `notification_user`
- Password: `notification_password`

## Run the Application

```bash
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

Useful URLs:

- Health: `http://localhost:8080/actuator/health`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Verify

```bash
mvn clean verify
```

## Notes

The notification APIs are intentionally not implemented in the initial project setup change. The first Flyway migration creates the notification table shape needed by the MVP.
