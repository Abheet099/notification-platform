# Notification Platform MVP Plan

## Agreed MVP Scope

- Java 21, Maven, Spring Boot 3.
- PostgreSQL as the only database.
- Spring Web, Spring Data JPA, Spring Validation, Flyway, Lombok, Actuator, OpenAPI/Swagger.
- Email notifications only.
- Mock email provider only.
- Asynchronous processing with Spring `@Async`.
- UUID notification identifiers.
- `Instant` timestamps.
- Hibernate DDL mode set to `validate`.

## Explicitly Out of Scope

- Kafka.
- Redis.
- Authentication or authorisation.
- Retry logic.
- Real email provider integration.
- Notification API implementation in the first project setup change.

## Initial Endpoints

- `POST /api/v1/notifications`
- `GET /api/v1/notifications/{id}`
- `GET /api/v1/notifications`
- `GET /actuator/health`

## Notification Statuses

- `ACCEPTED`
- `PROCESSING`
- `SENT`
- `FAILED`

## Milestones

1. Initialise the Maven-based Spring Boot project with dependencies, configuration, Docker Compose, Flyway schema, README updates, and a basic application context test.
2. Add notification domain model, DTOs, mapper, repository, and validation rules.
3. Implement notification creation and retrieval APIs.
4. Add mock email provider and asynchronous processing with `@Async`.
5. Add service, controller, and error-handling tests.
6. Harden observability through health checks and useful application metadata.
