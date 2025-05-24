# web-application-backend
# Church Management System - Backend

This is the backend of the **Church Management System**, developed using **Spring Boot** and **PostgreSQL**. The system supports essential church operations such as admin authentication, member registration, financial tracking, leadership management, event scheduling, and announcements.

---

## 🛠️ Tech Stack

- **Backend Framework:** Spring Boot
- **Language:** Java
- **Database:** PostgreSQL
- **ORM:** Spring Data JPA
- **Authentication:** Spring Security (JWT)
- **API Testing:** Postman
- **Build Tool:** Maven or Gradle
- **Others:** Lombok, MapStruct (optional)

---

## 📁 Project Structure

church-management-backend/
├── src/
│ ├── main/
│ │ ├── java/com/church/
│ │ │ ├── controller/
│ │ │ ├── service/
│ │ │ ├── repository/
│ │ │ ├── model/
│ │ │ └── security/
│ │ └── resources/
│ │ └── application.properties
├── pom.xml or build.gradle
└── README.md

markdown
Copy
Edit

---

## ✅ Core Features

### 🔐 Admin Authentication
- Secure login for church administrators
- JWT-based token authentication
- Role-based access control

### 👥 Member Management
- Register, view, update, and deactivate member records
- Search and filter members

### 💰 Finance Management
- Record tithes, offerings, and donations
- Track income and expenses
- Generate financial reports

### 📢 Announcement Management
- Create and manage announcements
- Publish updates to the congregation

### 👤 Leadership Management
- Assign leadership roles (e.g., Pastor, Secretary)
- Track responsibilities and leadership history

### 📅 Event Management
- Schedule and manage church events
- Track attendance and notify members

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven or Gradle
- PostgreSQL
- Git

### Setup Steps

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/church-management-backend.git
   cd church-management-backend
Create PostgreSQL Database

Create a database named church_db.

Configure Application Properties

Edit src/main/resources/application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:postgresql://localhost:5432/church_db
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT settings
app.jwt.secret=your_secret_key
app.jwt.expiration-ms=86400000
Run the application

Using Maven:

bash
Copy
Edit
./mvnw spring-boot:run
Or using Gradle:

bash
Copy
Edit
./gradlew bootRun
📬 API Testing with Postman
Use Postman to test and interact with the RESTful APIs.

🔑 Authentication
Endpoint: POST /api/admin/login

Body (JSON):

json
Copy
Edit
{
  "email": "admin@example.com",
  "password": "your_password"
}
Response: JWT token

Include the returned token in the Authorization header of subsequent requests:

makefile
Copy
Edit
Authorization: Bearer <token>


