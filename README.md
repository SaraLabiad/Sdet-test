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
  Test : <img width="428" height="197" alt="image" src="https://github.com/user-attachments/assets/1d67bafb-2334-4bb4-af82-20acd618410b" />
  test api : <img width="515" height="348" alt="image" src="https://github.com/user-attachments/assets/409a84ca-e3fd-4dd4-9e52-f8fa3dc73458" />
  Deploy docker : <img width="1280" height="171" alt="image" src="https://github.com/user-attachments/assets/4f0a7f83-6ec5-4d6e-a79a-4461e338d415" />
  Pg admin : <img width="1280" height="658" alt="image" src="https://github.com/user-attachments/assets/5a0f8785-15da-4d08-8ebf-c88a9ac2219a" />
  Target : <img width="577" height="308" alt="image" src="https://github.com/user-attachments/assets/c0874866-e9be-4c46-bd84-a9860610d43c" />

## Notes
This is a starter scaffold. You should:
- Improve validation, error payloads, and logging structure.
- Add comprehensive unit/integration tests and RestAssured API tests (examples provided).
- Fill README with mapping between tests and requirements before submission.
