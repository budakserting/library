# Library

## Overview
A seemingly simple RESTful API project.

## Technologies Used
- **Java 17**: The second-latest LTS release.
- **Spring Boot 3.3.4**: A framework for building stand-alone, production-grade Spring-based applications.
- **Apache Maven**: A powerful build automation tool which simplifies the process of managing project dependencies, compiling source code, packaging binaries, and deploying applications.
- **Hibernate Envers**: A library for auditing and versioning of persistent classes.
- **Lombok**: A library that simplifies your code by automating boilerplate code generation.
- **MySQL 8.0**: An advanced relational database management system. ( I used MySQL more in recent years compared to SQL Server)
- **Docker**: Containerization technology for creating and deploying applications in lightweight containers.

## Getting Started

### Prerequisites
- JDK 17
- Apache Maven
- Docker

### MySQL 8.0 Installation
1. Pull the image `mysql/mysql-server:8.0` into your local registry.
2. Navigate to `{project_root}/mysql` in a terminal
3. Run `docker compose up`

### Library Installation
1. Navigate to `{project_root}` in a terminal
2. Run `./mvnw clean package -Dskiptests`
3. Run `./mvnw spring-boot:run`

## Usage

### Add A Borrower
```
POST http://localhost:8080/borrower/add?name={borrower_name}&email={borrower_email}
```
- Replace borrower_name and borrower_email with the actual value to input/save

### Add A Book
```
POST http://localhost:8080/book/add
Content-Type: application/json
```
Request Body
```json
{
    "isbn": "9780261102736",
    "title": "The Silmarillion",
    "author": "J.R.R. Tolkien"
}
```

### List All Books
```
GET http://localhost:8080/book/all
```

### Borrow A Book
```
POST http://localhost:8080/borrow/book?bookId={book_id}&borrowerId={borrower_id}
```
- Replace book_id and borrower_id with the corresponding 32-character-long value

### Return A Book
```
POST http://localhost:8080/return/book?bookId={book_id}&borrowerId={borrower_id}
```
- Replace book_id and borrower_id with the corresponding 32-character-long value

### **For a look at all possible responses, please see [Library Specifications Sheet](./docs/library_specs_sheet.pdf).**

### **A [Postman collection](./postman/Library.postman_collection.json) is also provided with this bundle.**

------