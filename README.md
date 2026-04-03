# 📓 Journal Application (Spring Boot + MongoDB + Spring Security)

A RESTful Journal Application built using **Spring Boot**, **MongoDB**, and **Spring Security (Basic Authentication)**.
Users can create accounts, write journal entries, and admins can manage users.

---

# 🚀 Features

* 🔐 User Authentication using **Spring Security (Basic Auth)**
* 👤 User Registration
* 📓 Create and Manage Journal Entries
* 🛡 Role Based Authorization (USER / ADMIN)
* 📡 REST API Architecture
* 🗄 MongoDB Database Integration
* ⚡ Health Check Endpoint

---

# 🛠 Tech Stack

* Java 17 / 21
* Spring Boot
* Spring Security
* MongoDB Atlas
* Maven
* Lombok
* REST APIs

---

# 📂 Project Structure

```
src/main/java/com/example/journal

│
├── controller
│   ├── PublicController
│   ├── JournalEntryController
│   └── AdminController
│
├── service
│   ├── UserService
│   └── JournalEntryService
│
├── repository
│   ├── UserRepository
│   └── JournalEntryRepository
│
├── model
│   ├── User
│   └── JournalEntry
│
├── config
│   └── SecurityConfig
│
└── JournalApplication.java
```

---

# ⚙️ Setup & Installation

### 1️⃣ Clone the repository

```bash
git clone https://github.com/yourusername/journal-app.git
```

### 2️⃣ Open Project

Open in **IntelliJ IDEA / VS Code**

---

### 3️⃣ Configure MongoDB

Update `application.properties`

```properties
spring.data.mongodb.uri=your_mongodb_connection_string
spring.data.mongodb.database=journaldb
```

---

### 4️⃣ Run the Application

```bash
mvn spring-boot:run
```

or run the **main class**

```
JournalApplication.java
```

Server runs at:

```
http://localhost:8080
```

---

# 📡 API Endpoints

## 🔓 Public APIs

### Create User

```
POST /public/create-user
```

Body

```json
{
"userName": "neeraj",
"password": "1234"
}
```

---

# 👤 User APIs

### Create Journal Entry

```
POST /journal
```

Authentication Required

```json
{
"title": "My First Journal",
"content": "Today I learned Spring Boot"
}
```

---

# 🛡 Admin APIs

### Get All Users

```
GET /admin/all-users
```

Admin authentication required.

---

### Make User Admin

```
PUT /admin/make-admin/{username}
```

---

### Create Admin User

```
POST /admin/create-admin-user
```

---

# ❤️ Health Check

```
GET /health-check
```

Response

```json
{
"status": "UP",
"message": "Application Running"
}
```

---

# 🔐 Security Rules

* Passwords are **BCrypt encoded**
* Role based access control
* Roles stored as

```
ROLE_USER
ROLE_ADMIN
```

Security config uses

```
hasRole("ADMIN")
```

---

# ❗ Common Errors

| Error            | Cause                      |
| ---------------- | -------------------------- |
| 401              | Wrong username/password    |
| 403              | Role missing               |
| 500              | Server error               |
| DBRef not saving | user not saved after entry |

---

# 📸 Example Flow

1️⃣ Create User
2️⃣ Login using Basic Auth
3️⃣ Create Journal Entry
4️⃣ Admin can view all users

---

# 🧠 Future Improvements

* JWT Authentication
* Refresh Token
* Swagger Documentation
* Global Exception Handling
* DTO Layer
* Docker Deployment

---

# 👨‍💻 Author

**Neeraj Gupta**

Java Backend Developer (Learning Phase 🚀)

---

⭐ If you like this project, give it a star on GitHub.
