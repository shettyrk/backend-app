# 🕯️ CandleStore Backend

## 🧩 Overview
**CandleStore** is a microservices-based e-commerce backend system built using **Spring Boot**, **Spring Cloud**, and **MySQL**.  
It powers the CandleStore frontend by managing users, authentication, products, orders, and notifications in a distributed architecture.

---

## ⚙️ Architecture Overview

             ┌───────────────────┐
             │   API Gateway     │  ← Entry Point
             └────────┬──────────┘
                      │
   ┌──────────────────┴────────────────────┐
   │                  │                    │
┌────────────┐ ┌──────────────┐ ┌──────────────┐
│ Auth Svc │ │ Product Svc │ │ Order Svc │
│ (JWT Auth) │ │ (Catalog) │ │ (Cart, Order)│
└────────────┘ └──────────────┘ └──────────────┘
│ │ │
└──────────┬─────────┴───────────┬────────┘
│ │
┌─────────────┐ ┌──────────────┐
│Inventory Svc│ │Notification │
│ (Stock) │ │ Svc (Email) │
└─────────────┘ └──────────────┘
│
┌───────────────┐
│Config Server │
│Eureka Server │
└───────────────┘
---

## 🧱 Microservices List

| Service | Description | Port | Dependencies |
|----------|--------------|------|---------------|
| 🧭 **service-discovery** | Eureka Server for service registration | `8761` | Netflix Eureka |
| ⚙️ **config-server** | Centralized configuration server | `8888` | Spring Cloud Config |
| 🛡️ **api-gateway** | Routes requests to backend services | `8080` | Spring Cloud Gateway |
| 🔐 **auth-service** | Authentication (JWT), user management | `8081` | Security, JPA, MySQL |
| 🕯️ **product-service** | Product & candle catalog CRUD | `8082` | JPA, MySQL |
| 🛒 **order-service** | Manages orders, carts, checkout | `8083` | JPA, OpenFeign |
| 📦 **inventory-service** | Stock and availability tracking | `8084` | JPA, MySQL |
| ✉️ **notification-service** | Sends order & signup notifications | `8085` | Spring Mail |

---

## 🧩 Tech Stack

| Category | Technology |
|-----------|-------------|
| **Backend Framework** | Spring Boot (v3.x) |
| **Service Discovery** | Netflix Eureka |
| **API Gateway** | Spring Cloud Gateway |
| **Database** | MySQL |
| **ORM** | Spring Data JPA |
| **Security** | Spring Security + JWT |
| **Configuration Management** | Spring Cloud Config |
| **Build Tool** | Maven |
| **Language** | Java 17 |

---

## 🗂️ Project Structure

CandleStore-Backend/
│
├── api-gateway/
├── service-discovery/
├── config-server/
├── auth-service/
├── product-service/
├── order-service/
├── inventory-service/
├── notification-service/
└── README.md



---

## ⚡ Setup Instructions

### 1️⃣ Clone Repository

git clone https://github.com/<your-username>/CandleStore-Backend.git
cd CandleStore-Backend
2️⃣ Configure Database
Each service using MySQL should have its own DB configuration in:

css
Copy code
src/main/resources/application-dev.yml
Example:

yaml
Copy code
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/product_db
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
3️⃣ Run the Services (in this order)
bash


# 1. Start service discovery
mvn spring-boot:run -f service-discovery/pom.xml

# 2. Start config server
mvn spring-boot:run -f config-server/pom.xml

# 3. Start api-gateway
mvn spring-boot:run -f api-gateway/pom.xml

# 4. Start other microservices
mvn spring-boot:run -f auth-service/pom.xml
mvn spring-boot:run -f product-service/pom.xml
mvn spring-boot:run -f order-service/pom.xml
mvn spring-boot:run -f inventory-service/pom.xml
mvn spring-boot:run -f notification-service/pom.xml
✅ Access Eureka Dashboard → http://localhost:8761
✅ Gateway routes start at → http://localhost:8080

🧾 Profiles
Profile	Purpose
dev	Local development
qa	Testing and QA environment
prod	Production configuration

👨‍💻 Author

Ramakrishna Shetty
Associate Software Developer
🔗 LinkedIn

📧 ramakrishna.shetty@example.com

🪪 License

This project is licensed under the MIT License — feel free to use, modify, and distribute.


---

Would you like me to now give you a **ready-to-copy `application.yml` + `application-dev.yml` + `application-qa.yml
