# Spring Hospital

This Spring Boot application saves entities into DB and exposes REST API of them to this Spring web server [here](https://github.com/Finishxx/tjv-hospital-frontend). Made as a [FIT CTU](https://fit.cvut.cz/cs) semestral work for BI-TJV class. The entities are in the hospital domain.

## Domain

Hospital internal system for booking patients with their doctors.
A patient can only be booked to his own doctor only if the doctor is free at that time.

### ER Schema

![relational schema](/schema.png)

## Architecture & technology

Clean architecture with three layers: Persistence, Business and Presentation

### Data

ORM using Spring Data JPA. Postgres DB in memory.

### Business logic

Implemented using Service classes:
- Create, Read, Update, Delete operations on all entities.
- Schedule an appointment
- Cancel an appointment
- Cancel all appointments in a given doctor's day

### Presentation

Controller for each domain entity exposing REST API with OpenAPI documentation [here](src/main/resources/static/openapi.yaml).
