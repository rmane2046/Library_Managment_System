# 📚 Library Management System

A **RESTful Library Management System** developed using **Java, Spring Boot, Spring Data JPA, Hibernate, and Oracle Database**.

The application provides APIs to manage books, authors, members, and book issue/return records. It follows a layered architecture using **Controller, Service, Repository, Entity, and VO** layers and includes custom exception handling for common library operations.

---

## 📌 Table of Contents

* About the Project
* Features
* Technologies Used
* Project Architecture
* Project Structure
* Database Design
* REST API Endpoints
* Getting Started
* Database Configuration
* Running the Application
* Testing APIs
* Exception Handling
* Future Enhancements
* Learning Outcomes
* Author

---

## 🔎 About the Project

The **Library Management System** is a backend REST API application designed to simplify and manage common library operations.

The system allows library administrators to:

* Manage books
* Manage authors
* Manage library members
* Issue books to members
* Return issued books
* Check book availability
* Maintain issue records
* Handle invalid operations through custom exceptions

The project uses **Spring Boot** for REST API development and **Spring Data JPA/Hibernate** for database interaction with **Oracle Database**.

---

## ✨ Features

### 📖 Book Management

* Add a new book
* View book details by ID
* View all books
* Update book information
* Delete a book
* View available books
* Track available copies

### ✍️ Author Management

* Add authors
* View author details
* View all authors
* Update author information
* Delete authors
* Associate books with authors

### 👤 Member Management

* Add library members
* View member details
* View all members
* Update member information
* Delete members
* Maintain member records

### 🔄 Book Issue & Return

* Issue available books to members
* Maintain issue records
* Automatically reduce available book copies when a book is issued
* Automatically increase available copies when a book is returned
* Calculate due dates for issued books
* Prevent issuing unavailable books
* Prevent invalid return operations

### ⚠️ Exception Handling

The project includes custom exception handling for situations such as:

* Book not found
* Book already exists
* Book unavailable
* Book already returned
* Author not found
* Author already exists
* Member not found
* Member already exists
* Issue record not found

A global exception handler is used to provide structured error responses.

---

## 🛠 Technologies Used

| Technology                        | Purpose                       |
| --------------------------------- | ----------------------------- |
| **Java**                          | Programming Language          |
| **Spring Boot**                   | Backend Framework             |
| **Spring MVC**                    | REST API Development          |
| **Spring Data JPA**               | Database Access               |
| **Hibernate**                     | ORM                           |
| **Oracle Database**               | Data Storage                  |
| **Maven**                         | Dependency Management & Build |
| **Postman**                       | API Testing                   |
| **Eclipse / STS / IntelliJ IDEA** | Development Environment       |

---

## 🏗 Project Architecture

The application follows a layered architecture:

                    ┌─────────────────────┐
                    │      REST Client    │
                    │  Postman / Frontend │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Controller      │
                    │  REST API Endpoints │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │       Service       │
                    │    Business Logic   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │      Repository     │
                    │    JPA Operations   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   Oracle Database   │
                    └─────────────────────┘

### Architecture Layers

**Controller Layer**

Handles HTTP requests and responses and exposes REST endpoints.

**Service Layer**

Contains the application's business logic such as book issuing, returning, availability checking, and validation.

**Repository Layer**

Uses Spring Data JPA to communicate with the Oracle database.

**Entity Layer**

Contains JPA entities representing database tables.

**VO Layer**

Contains value/request objects used for transferring data between the client and application.

**Exception Layer**

Contains custom exceptions and global exception handling.

---

## 📁 Project Structure

Library_Management/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── lib/
│       │           │
│       │           ├── controller/
│       │           │   ├── AuthorController.java
│       │           │   ├── BookController.java
│       │           │   ├── HomeController.java
│       │           │   ├── IssueBookController.java
│       │           │   └── MemberController.java
│       │           │
│       │           ├── entity/
│       │           │   ├── AuthorEntity.java
│       │           │   ├── BookEntity.java
│       │           │   ├── IssueRecordEntity.java
│       │           │   └── MemberEntity.java
│       │           │
│       │           ├── exceptions/
│       │           │   ├── AuthorAlreadyPresentException.java
│       │           │   ├── AuthorNotFoundException.java
│       │           │   ├── BookAlreadyPresentException.java
│       │           │   ├── BookAlreadyReturnedException.java
│       │           │   ├── BookNotAvailableException.java
│       │           │   ├── BookNotFoundException.java
│       │           │   ├── ExceptionDetails.java
│       │           │   ├── GlobalExceptions.java
│       │           │   ├── MemberAlreadyExitException.java
│       │           │   ├── MemberNotFoundException.java
│       │           │   └── RecordNotFoundException.java
│       │           │
│       │           ├── repository/
│       │           │   ├── AuthorEntityRepository.java
│       │           │   ├── BookEntityRepository.java
│       │           │   ├── IssueRecordEntityRepository.java
│       │           │   └── MemberEntityRepository.java
│       │           │
│       │           ├── services/
│       │           │   ├── AuthorServices.java
│       │           │   ├── AuthorsService.java
│       │           │   ├── BookService.java
│       │           │   ├── BooksService.java
│       │           │   ├── IssueRecordService.java
│       │           │   ├── IssueRecordServices.java
│       │           │   ├── MemberService.java
│       │           │   └── MemberServices.java
│       │           │
│       │           ├── vo/
│       │           │   ├── AuthorBookVo.java
│       │           │   ├── AuthorVo.java
│       │           │   ├── BookVo.java
│       │           │   ├── IssueRecordVo.java
│       │           │   └── MemberRecordVo.java
│       │           │
│       │           └── LibraryManagementApplication.java
│       │
│       └── resources/
│           └── application.properties
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## 🗄 Database Design

The application uses **Oracle Database** for persistent data storage.

### Main Entities

```text
┌──────────────┐
│    AUTHOR    │
└──────┬───────┘
       │
       │ 1
       │
       │ *
┌──────▼───────┐
│     BOOK     │
└──────┬───────┘
       │
       │ *
       │
       │
┌──────▼────────────┐
│  ISSUE_RECORD     │
└──────▲────────────┘
       │ *
       │
       │
┌──────┴───────┐
│    MEMBER    │
└──────────────┘
```

### Relationships

* One **Author** can have multiple **Books**
* A **Book** can have multiple issue records over time
* A **Member** can have multiple issue records
* Issue records connect **Books** and **Members**

JPA relationships such as `@OneToMany` and `@ManyToOne` are used to represent these relationships.

---

# 🔌 REST API Endpoints

## 📖 Book APIs

| Method   | Endpoint              | Description         |
| -------- | --------------------- | ------------------- |
| `POST`   | `/addbook`            | Add a new book      |
| `GET`    | `/get-book/{id}`      | Get book by ID      |
| `GET`    | `/find-all-books`     | Get all books       |
| `PUT`    | `/update-book`        | Update book details |
| `DELETE` | `/delete-book/{id}`   | Delete a book       |
| `GET`    | `/get-available-book` | Get available books |

---

## ✍️ Author APIs

Author-related REST endpoints are provided through `AuthorController` for:

* Adding authors
* Retrieving authors
* Updating authors
* Deleting authors
* Managing author/book relationships

---

## 👤 Member APIs

Member-related REST endpoints are provided through `MemberController` for:

* Adding members
* Retrieving members
* Updating members
* Deleting members
* Managing member records

---

## 🔄 Issue & Return APIs

Issue and return operations are handled through `IssueBookController`.

The system supports:

* Issuing books
* Returning books
* Maintaining issue records
* Checking book availability
* Managing due dates

---

# 🚀 Getting Started

## Prerequisites

Before running the project, make sure you have installed:

* **Java JDK 17 or compatible version**
* **Maven**
* **Oracle Database**
* **Git**
* **Postman** or another REST API client

You can verify Java installation using:

```bash
java -version
```

Verify Maven using:

```bash
mvn -version
```

---

## 📥 Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/Library_Management.git
```

Move into the project directory:

```bash
cd Library_Management
```

---

# 🗄 Database Configuration

Create an Oracle database/schema for the project.

Configure your database connection in:

```text
src/main/resources/application.properties
```

Example configuration:

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### ⚠️ Security Notice

**Do not commit real database credentials to GitHub.**

Use environment variables instead:

```text
DB_USERNAME
DB_PASSWORD
```

This prevents sensitive database credentials from being exposed publicly.

---

# ▶️ Running the Application

### Using Maven

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On Linux/macOS:

```bash
./mvnw spring-boot:run
```

Or, if Maven is installed:

```bash
mvn spring-boot:run
```

The application will start on the default Spring Boot port:

```text
http://localhost:8080
```

---

# 🧪 Testing APIs

You can test the REST APIs using **Postman**.

Example request:

```http
GET http://localhost:8080/find-all-books
```

Example POST request:

```http
POST http://localhost:8080/addbook
Content-Type: application/json
```

Request body will depend on the fields defined in `BookVo`.

You can use the available controller endpoints to test:

* Book operations
* Author operations
* Member operations
* Issue operations
* Return operations

---

# ⚠️ Exception Handling

The application uses custom exceptions and a global exception handler to provide meaningful responses when an operation cannot be completed.

Examples include:

```text
BookNotFoundException
BookAlreadyPresentException
BookNotAvailableException
BookAlreadyReturnedException
AuthorNotFoundException
AuthorAlreadyPresentException
MemberNotFoundException
MemberAlreadyExitException
RecordNotFoundException
```

The `GlobalExceptions` class handles these exceptions centrally.

This improves the consistency and maintainability of API error responses.

---

# 🔮 Future Enhancements

The following features can be added in future versions:

* 🔐 Spring Security authentication
* 👑 Admin and Member role-based authorization
* 🔑 JWT-based authentication
* 🖥️ Frontend using React / Angular / HTML-CSS-JavaScript
* 🔎 Book search and filtering
* 📅 Overdue book tracking
* 💰 Automatic fine calculation
* 📊 Admin dashboard and library statistics
* 📧 Email notifications for due dates
* 🧪 Unit and integration testing
* 📖 Swagger/OpenAPI API documentation
* 📱 Responsive user interface
* ☁️ Cloud deployment

---

# 🎯 Learning Outcomes

Through this project, the following concepts were implemented and practiced:

* Java Object-Oriented Programming
* Spring Boot application development
* RESTful API development
* Spring MVC
* Spring Data JPA
* Hibernate ORM
* Oracle Database integration
* Entity relationships
* CRUD operations
* Service-layer business logic
* Exception handling
* Maven project management
* API testing using Postman
* Layered application architecture

---

# 📌 Project Status

**Status:** 🚧 Backend REST API – Completed

The current version focuses on the backend REST API and database functionality.

Frontend, authentication, role-based authorization, automated testing, and additional reporting features can be added as future enhancements.

---

# 👨‍💻 Author

**Your Name**

Computer Engineering Student
Java | Spring Boot | REST API | Oracle | SQL

---

## ⭐ If you find this project useful

If this project helped you learn Spring Boot and REST API development, consider giving the repository a ⭐ on GitHub.

---

## 📄 License

This project is created for **educational and learning purposes**.
