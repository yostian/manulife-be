# Simple CRUD Application with Spring Boot and JasperReports

## Overview

This project is a simple **CRUD (Create, Read, Update, Delete) application** for managing `User` entities using **Spring Boot** and **JasperReports** for generating reports.

## Features

- Create a new user
- Retrieve a list of users
- Update user details
- Delete a user
- Generate user reports using JasperReports (PDF format)

## Tech Stack

- **Java** (Spring Boot)
- **Spring Data JPA** (for database interactions)
- **H2/PostgreSQL/MySQL** (configurable database)
- **Lombok** (for reducing boilerplate code)
- **JasperReports** (for reporting)
- **Maven** (dependency management)

## Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/your-repo.git
cd your-repo
```

### 2. Configure Database

Modify `application.properties` in `src/main/resources`:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

*(You can replace H2 with MySQL or PostgreSQL as needed.)*

### 3. Run the Application

```bash
mvn spring-boot:run
```

## API Endpoints

| Method     | Endpoint        | Description                |
| ---------- | --------------- | -------------------------- |
| **POST**   | `/users`        | Create a new user          |
| **GET**    | `/users`        | Get all users              |
| **GET**    | `/users/{id}`   | Get user by ID             |
| **PUT**    | `/users/{id}`   | Update user by ID          |
| **DELETE** | `/users/{id}`   | Delete user by ID          |
| **GET**    | `/users/report` | Generate user report (PDF) |

## Example User Model

```java
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String alamat;
}
```

## Generating Reports

JasperReports is used to generate **PDF reports**. The report template (`users_report.jrxml`) is stored in `src/main/resources/reports/` in the backend. To generate a report, use:

```bash
GET /api/reports/users/pdf
```

This will return a **PDF file** containing user details.
