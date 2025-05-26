# 📚 Book Management API

A RESTful API built with Spring Boot to manage a catalog of books and user profiles.

---

## 🚀 Tecnologies

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Lombok
- Swagger
- Docker

---

## 📂 Project Structure

```plaintext
src/
 └── main/
     ├── java/
     │   └── com/angeldev/bookmanagement/
     │       ├── controller/        # REST Controllers
     │       ├── dto/               # DTO Classes
     │       ├── exception/         # Exceptions
     │       ├── mapper/            # Mappers for DTOs    
     │       ├── persistence/       # Model
     │           ├── model/             # JPA Entities
     │           ├── repository/        # Interfaces
     │       ├── service/           # Business Logic
     │       ├── util/              # Enum Class
     │       └── BookApiApplication.java
     └── resources/
         ├── application.properties
         └── data.sql            # Scripts for MySQL
         └── docker-compose.yml  # Set up database with docker
```
---

## 📌 Endpoints 

> All endpoints are under the prefix: `/api/v1`

| Método | Endpoint             | Descripción                         | Body              |
|--------|----------------------|-------------------------------------|-------------------|
| GET    | `/profiles`          | Get all profiles                    |                   |
| GET    | `/profiles/{id}`     | Get a profile                       |                   |
| POST   | `/profiles`          | Create a profile                    |  `ProfileRequest` |
| PUT    | `/profiles/{id}`     | Update a profile                    |  `ProfileRequest` |
| DELETE | `/profiles/{id}`     | Delete a profile                    |                   |
| GET    | `/profiles/{id}/books`| Get all books from a profile       |                   |
|        |                      |                                     |                   |
| GET    | `/books`             | Get all books                       |                   |
| GET    | `/books/{id}`        | Get a book                          |                   |
| POST   | `/books`             | Create a a book                     |  `BookRequest`    |
| PUT    | `/books`             | Update a book                       |  `BookRequest`    |
| DELETE | `/books/{id}`        | Eliminar un libro por ID            |                   |
---

### 🔧 Prerequisites

Before running this project, make sure you have the following installed:

- **Java 21** – Required to build and run the application.
- **Docker & Docker Compose** – To run the MySQL database.
- **DBeaver** *(optional)* – For visualizing and managing the database.

## ⚙️ Installation  

### 1. Clone Repository

```bash
git clone https://github.com/tu-usuario/book-api.git
cd book-api
```

### 2. Run docker compose

```bash
run docker-compose up
```

### 3. Build and run project

```bash
./mvnw clean install
./mvnw spring-boot:run
```

### 4. Test the API
The app should be running at: http://localhost:8080/api/v1







