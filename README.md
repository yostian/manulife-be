# manulife-be
Simple CRUD Application with Spring Boot and JasperReports

Overview

This project is a simple CRUD (Create, Read, Update, Delete) application for managing User entities using Spring Boot and JasperReports for generating reports.

Features

Create a new user

Retrieve a list of users

Update user details

Delete a user

Generate user reports using JasperReports (PDF format)

Tech Stack

Java (Spring Boot)

Spring Data JPA (for database interactions)

H2/PostgreSQL/MySQL (configurable database)

Lombok (for reducing boilerplate code)

JasperReports (for reporting)

Maven (dependency management)

Installation & Setup

1. Clone the Repository

git clone https://github.com/yourusername/your-repo.git
cd your-repo

2. Configure Database

Modify application.properties in src/main/resources:

spring.datasource.url=jdbc:mysql://localhost:3306/manulife
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

(You can replace with H2 or PostgreSQL as needed.)

3. Run the Application

mvn spring-boot:run

API Endpoints

Method

Endpoint

Description

POST

/users

Create a new user

GET

/users

Get all users

GET

/users/{id}

Get user by ID

PUT

/users/{id}

Update user by ID

DELETE

/users/{id}

Delete user by ID

GET

/api/user

Generate user report (PDF)

Example User Model

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String alamat;
}

Generating Reports

JasperReports is used to generate PDF reports. The report template (users_report.jrxml) is stored in src/main/resources/reports/ in the backend.
To generate a report, use:

GET /api/reports/users/pdf

This will return a PDF file containing user details.
