# Spring basic crm
# Spring Basic CRM

A basic Customer Relationship Management (CRM) system built with Spring Boot.

## Description

This project is a lightweight CRM application that helps manage customer data, interactions, and business relationships. It provides a RESTful API for CRUD operations on customers and related entities.

## Technologies

- Java
- Spring Boot
- PostgreSQL
- Gradle
- Docker

## Prerequisites

- JDK 17 or later
- Gradle
- Docker (optional)
- PostgreSQL database

## Setup and Installation

### Database Configuration

Ensure you have a PostgreSQL database named `spring_crm` running. The default configuration expects:

```
Database: spring_crm
Username: user
Password: password
Port: 5432
```

### Running Locally

1. Clone the repository
2. Navigate to the project directory
3. Build the project:
   ```bash
   ./gradlew build
   ```
4. Run the application:
   ```bash
   ./gradlew bootRun
   ```

### Running with Docker

1. Build the Docker image:
   ```bash
   docker build -t spring-crm .
   ```

2. Run the container:
   ```bash
   docker run --rm -p 8080:8080 \
   -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/spring_crm \
   -e SPRING_DATASOURCE_USERNAME=user \
   -e SPRING_DATASOURCE_PASSWORD=password \
   spring-crm
   ```

## API Endpoints

The application exposes RESTful endpoints at `http://localhost:8080/api/v1/`.

## Features

- Customer management
- Contact tracking
- Interaction logging
- Basic reporting

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/crm/
│   │       ├── controller/
│   │       ├── model/
│   │       ├── repository/
│   │       ├── service/
│   │       └── CrmApplication.java
│   └── resources/
│       ├── application.properties
│       └── data.sql
```

## License

This project is licensed under the MIT License.