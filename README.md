# 🚀 Journal Application (Production Ready Backend)

A **production-ready backend system** built using **Spring Boot**, featuring **JWT Authentication, OAuth2 Login, Kafka Event Streaming, Redis Caching, Email Notifications**, and deployed on cloud.

---

# 🌟 Key Features

## 🔐 Authentication & Security

* JWT आधारित Authentication (Bearer Token)
* OAuth2 Login (Google)
* Spring Security (Role-Based Access Control)
* Secure API endpoints

---

## 👤 User & Journal Management

* User Registration & Login
* Create / Update / Delete Journal Entries
* User-specific journal storage (MongoDB)

---

## ⚡ Advanced Backend Features

* 📩 Email Sending (Kafka + Gmail SMTP)
* 📨 Kafka Producer & Consumer (Event Driven)
* 🧠 Redis Caching (Performance Optimization)
* 📊 Swagger UI (API Documentation)
* 🌐 REST APIs

---

## ☁️ Deployment

* 🚀 Deployed on Render
* 🌍 Environment-based configuration

---

# 🛠 Tech Stack

* Java 21
* Spring Boot
* Spring Security
* JWT (Bearer Token)
* OAuth2 (Google Login)
* MongoDB Atlas
* Redis
* Apache Kafka
* Spring Mail
* Swagger (OpenAPI)
* Maven

---

# ⚙️ Configuration (application.yml)

```yaml
server:
  port: ${PORT:8081}

spring:
  profiles:
    active: dev

  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENT_ID}
            client-secret: ${GOOGLE_CLIENT_SECRET}

  data:
    mongodb:
      uri: ${MONGODB_URI}
      auto-index-creation: true

    redis:
      uri: ${REDIS_URI}

  mail:
    host: smtp.gmail.com
    port: 587
    username: ${MAIL_USERNAME}
    password: ${MAIL_PASSWORD}

  kafka:
    bootstrap-servers: ${KAFKA_SERVERS}

weather:
  api:
    key: ${WEATHER_API_KEY}
```

---

# 🔐 Authentication Methods

## 1️⃣ JWT Authentication

* Login → JWT Token generate
* Use in header:

```
Authorization: Bearer <your_token>
```

---

## 2️⃣ OAuth2 Login (Google)

* Secure login using Google account
* Requires:

  * GOOGLE_CLIENT_ID
  * GOOGLE_CLIENT_SECRET

---

# 📡 API Documentation

Swagger UI:

```
http://localhost:8081/swagger-ui/index.html
```

---

# 📂 Complete Project Structure

```bash
my-first-project/
│
├── .github/
├── .mvn/
│
├── src/
│   ├── main/
│   │   ├── java/com/edigest/my/first/project/
│   │   │
│   │   │── api/response/
│   │   │── cache/
│   │   │
│   │   │── config/
│   │   │   ├── EnvConfig.java
│   │   │   ├── RedisConfig.java
│   │   │   ├── SpringSecurity.java
│   │   │   └── SwaggerConfig.java
│   │   │
│   │   │── constants/
│   │   │
│   │   │── controller/
│   │   │   ├── AdminController.java
│   │   │   ├── GoogleAuthController.java
│   │   │   ├── JournalEntryController.java
│   │   │   ├── PublicController.java
│   │   │   └── UserController.java
│   │   │
│   │   │── dto/
│   │   │   └── UserDTO.java
│   │   │
│   │   │── entity/
│   │   │   ├── ConfigJournalApp.java
│   │   │   ├── JournalEntry.java
│   │   │   └── User.java
│   │   │
│   │   │── enums/
│   │   │   └── Sentiment.java
│   │   │
│   │   │── jwtfilter/
│   │   │   └── JwtFilter.java
│   │   │
│   │   │── repository/
│   │   │   ├── ConfigJournalAppRepository.java
│   │   │   ├── JournalEntryRepository.java
│   │   │   ├── UserRepository.java
│   │   │   └── UserRepositoryImpl.java
│   │   │
│   │   │── scheduler/
│   │   │   └── UsersScheduler.java
│   │   │
│   │   │── service/
│   │   │   ├── EmailService.java
│   │   │   ├── JournalEntryService.java
│   │   │   ├── RedisService.java
│   │   │   ├── UserDetailsServiceImpl.java
│   │   │   ├── UserService.java
│   │   │   └── WeatherService.java
│   │   │
│   │   │── sentiment/
│   │   │   ├── SentimentData.java
│   │   │   └── SentimentProducer.java
│   │   │
│   │   │── utils/
│   │   │
│   │   │── JournalApplication.java
│   │
│   │   ├── resources/
│   │   │   ├── static/
│   │   │   ├── templates/
│   │   │   ├── application.yml
│   │   │   └── logback.xml
│   │
│   ├── test/
│
├── .env
├── Dockerfile
├── pom.xml
├── README.md
└── HELP.md
```

---

# 📩 Kafka Flow

### Producer

* Event trigger hone par message send karta hai

### Consumer

* Message receive karke:

  * Email send karta hai
  * Data process karta hai

---

# 🧠 Redis Caching

* Frequently accessed data cache hota hai
* Performance improve hoti hai

---

# 📧 Email Service

* SMTP (Gmail) based
* Kafka events se trigger hota hai

---

# 🐳 Docker Support

```bash
docker build -t journal-app .
docker run -p 8081:8081 journal-app
```

---

# 🔑 Environment Variables

| Variable             | Description        |
| -------------------- | ------------------ |
| PORT                 | Server Port        |
| MONGODB_URI          | MongoDB Connection |
| REDIS_URI            | Redis              |
| KAFKA_SERVERS        | Kafka              |
| MAIL_USERNAME        | Gmail              |
| MAIL_PASSWORD        | App Password       |
| GOOGLE_CLIENT_ID     | OAuth              |
| GOOGLE_CLIENT_SECRET | OAuth              |
| WEATHER_API_KEY      | Weather API        |

---

# ❗ Common Issues

| Issue       | Fix           |
| ----------- | ------------- |
| 401         | Invalid token |
| 403         | Role issue    |
| Kafka error | Broker check  |
| Email fail  | App password  |
| Redis fail  | URI check     |

---

# 📊 System Flow

```
Client
 ↓
Controller
 ↓
Service
 ↓
Repository → MongoDB
 ↓
Kafka → Consumer → Email
 ↓
Redis Cache
```

---

# 📈 Resume Points

* Built production-ready backend using Spring Boot
* Implemented JWT & OAuth2 authentication
* Integrated Kafka for event-driven architecture
* Used Redis for caching
* Deployed on cloud platform
* Designed scalable REST APIs

---

# 👨‍💻 Author

**Neeraj Gupta**
Backend Developer 🚀

---

# ⭐ Support

If you like this project:

👉 Star the repository
👉 Share with others

---
