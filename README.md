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

  ## Architecture Overview
This service ingests FX deals in batches, validates and deduplicates them, then persists results to a PostgreSQL database. Partial successes are supported; failed rows are reported but do not block successful imports.
[Client] → [REST Controller] → [Service Layer] → [Validation/Deduplication] → [Repository/JPA] → [Postgres DB]


## How to run (local)

1. Start Postgres:
    - Using Docker Compose: `docker compose up -d`

<img width="800" alt="docker deployment" src="https://github.com/user-attachments/assets/4f0a7f83-6ec5-4d6e-a79a-4461e338d415" />

2. Build & run:
    - `mvn clean package`
    - `java -jar target/deal-importer-0.0.1-SNAPSHOT.jar`

<img width="500" alt="build target" src="https://github.com/user-attachments/assets/c0874866-e9be-4c46-bd84-a9860610d43c" />

3. API:
    - POST `http://localhost:8098/api/deals` with JSON array payload.

<img width="500" alt="api test" src="https://github.com/user-attachments/assets/409a84ca-e3fd-4dd4-9e52-f8fa3dc73458" />

## API Endpoints

| Method | Endpoint        | Description                 | Sample Payload                                          |
| ------ | --------------- | --------------------------- | ------------------------------------------------------- |
| POST   | /api/deals      | Batch import FX deals       | [{"dealUniqueId":"A1","fromCurrencyIsoCode":"USD",...}] |
| GET    | /api/deals      | List all deals              |                                                         |
| GET    | /api/deals/test | API health check            |                                                         |
| GET    | /api/deals/{id} | Fetch one deal by unique id |                                                         |

## Requirements Mapping

| Requirement                         | Location/Class                    | Test(s)                                   |
| ----------------------------------- | --------------------------------- | ----------------------------------------- |
| Mandatory request fields            | Deal,DealRequest                  | DealServiceTest.validate_and_save_success |
| Row validation & correct datatypes  | DealValidator                     | DealServiceTest.invalid_amount_throws     |
| Deduplication (no duplicate import) | DeduplicationService              | (Add a deduplication unit test!)          |
| Partial success, no rollback        | Service logic, transaction config | (Add/mention batch/partial import test)   |

## Tests
- Unit tests: `mvn test`
- Coverage (JaCoCo): `mvn verify` (JaCoCo report will be generated)

<img width="400" alt="test results" src="https://github.com/user-attachments/assets/1d67bafb-2334-4bb4-af82-20acd618410b" />

## Database

pgAdmin available at `http://localhost:5050`

<img width="800" alt="pgadmin dashboard" src="https://github.com/user-attachments/assets/5a0f8785-15da-4d08-8ebf-c88a9ac2219a" />

## Notes
This is a starter scaffold. You should:
- Improve validation, error payloads, and logging structure.
- Add comprehensive unit/integration tests and RestAssured API tests (examples provided).
- Fill README with mapping between tests and requirements before submission.
