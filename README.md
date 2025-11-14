# Deal Importer - SDET Assignment Starter

This repository is a **starter scaffold** implementing the FX deals import API described in the assignment.

## What is included
- Spring Boot (Maven) project skeleton
- Domain entity, repository, service, and REST controller
- JaCoCo configured to enforce 100% coverage for measured code (adjust exclusions as needed)
- `docker-compose.yml` for Postgres and app (app image build instructions)
- `Makefile` with common tasks
- Sample Postman collection and K6 script stub
- Unit test examples

## How to run (local)
1. Start Postgres:
    - Using Docker Compose: `docker compose up -d`
2. Build & run:
    - `mvn clean package`
    - `java -jar target/deal-importer-0.0.1-SNAPSHOT.jar`
3. API:
    - POST `http://localhost:8098/api/deals` with JSON array payload.

## Tests
- Unit tests: `mvn test`
- Coverage (JaCoCo): `mvn verify` (JaCoCo report will be generated)

## Notes
This is a starter scaffold. You should:
- Improve validation, error payloads, and logging structure.
- Add comprehensive unit/integration tests and RestAssured API tests (examples provided).
- Fill README with mapping between tests and requirements before submission.
