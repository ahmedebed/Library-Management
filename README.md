# Library Management System

## Overview
This project is a Library Management System built with Java, Spring Boot, and a relational database. It supports managing books, members, system users with roles, and borrowing transactions.

## Features
- CRUD operations for Books, Members, and Users
- Role-Based Access Control with roles: Administrator, Librarian, Staff
- Borrowing and returning books with transaction tracking
- Secure authentication and password storage using JWT
- User activity logging
- Supports multiple authors per book, hierarchical categories, and publishers

## Technology Stack
- Java 21
- Spring Boot 3.2.3
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token) for authentication
- MySQL Connector
- Lombok for boilerplate code reduction
- Maven for project build and dependency management
- Postman for API testing

## Maven Configuration (pom.xml)
The project uses Maven as the build tool. Below is a summary of important dependencies and plugins:

### Dependencies Highlights
- `spring-boot-starter-data-jpa`: JPA and Hibernate for database operations
- `spring-boot-starter-web`: To build REST APIs
- `spring-boot-starter-security`: For securing the app and managing roles
- `spring-boot-starter-validation`: For request data validation
- `mysql-connector-j`: MySQL database connector
- `lombok`: To reduce boilerplate code like getters/setters
- `jjwt-api`, `jjwt-impl`, `jjwt-jackson`: Libraries for JWT token creation and validation
- Testing dependencies for unit and security tests
## Database ERD
![ERD Diagram](ERD/ERG.png)
## Access Rules (URL Patterns)

| URL Pattern          | Access Allowed                  |
|----------------------|--------------------------------|
| `/api/auth/**`       | Public (no authentication needed) |
| `/admin-api/**`      | Only users with role `ADMIN`     |
| `/librarian-api/**`  | Users with role `ADMIN` or `LIBRARIAN` |
| `/staff-api/**`      | Users with roles `ADMIN`, `LIBRARIAN`, or `STAFF` |
| Any other request    | Authenticated users only         |
