# 🚕 RideSharingDeliveryApp – Microservices Ride-Sharing & Delivery Platform

A Java/Spring Boot **microservices** project that models a ride-sharing & delivery platform with drivers, riders, dispatchers, vehicles, trips, and ratings.  
The system is split into multiple services and uses Spring Cloud patterns like **service discovery** and **declarative HTTP clients**.

---

## 🧱 Project Structure

```text
RideSharingDeliveryApp/
├── DistanceService/        # Microservice that talks to external distance API
├── EurekaServer/           # Eureka service discovery server
├── RideSharingDeliveryApp/ # Main app: users, trips, ratings, vehicles, etc.
└── README.md               # Project documentation
```

### Modules

#### EurekaServer

- Spring Boot **Eureka server** (service registry)  
- Other services register here and query by **service name** instead of hard-coded URLs.

#### DistanceService

- Exposes REST endpoint like: `GET /api/distances/getDistance?from=A&to=B`  
- Uses an external distance API (e.g. **DistanceMatrix**)  
- Registers with Eureka as **`distance-service`**  

#### RideSharingDeliveryApp (Main Service)

Core domain logic:

- Users: **Rider, Driver, Dispatcher**
- **Vehicles**
- Trips: **Ride** vs **Delivery**
- Ratings (e.g. riders rating drivers)

REST controllers (examples):

- `/api/auth/**` – authentication / registration  
- `/api/users/**` – user management  
- `/api/vehicles/**` – vehicle registration  
- `/api/dispatcher/**`, `/api/driver/**`, `/api/rider/**` – role-specific actions  
- `/api/rating/**` – rating trips / drivers  

Uses **OpenFeign** client to call `distance-service` through Eureka:

- `IDistanceClient#getDistance(from, to)`

---

## 🧠 Key Concepts Implemented

### Architecture & Spring Cloud

- ✅ **Microservices architecture** with multiple Spring Boot apps  
- ✅ **Service Discovery** using **Eureka Server**  
- ✅ Client-side service lookup via service ID (`distance-service`)  
- ✅ **OpenFeign** declarative clients for inter-service communication  
- ✅ Clear separation into **domain-focused services** (distance, discovery, core app)  

### Core Spring & Domain Design

- ✅ **Layered architecture** (Controller → Service → Repository → Entity)  
- ✅ **Spring Data JPA** repositories  
- ✅ **Entity relationships** between users, drivers, riders, vehicles, trips, and ratings  
- ✅ **Inheritance** in the domain (e.g., `Ride` and `Delivery` extending `Trip`)  
- ✅ Error handling for cases like:
  - Non-existent users or trips  
  - Invalid roles (e.g., non-rider trying to rate a ride)  
  - Trips not belonging to a given user  

### Security

- Spring Security configuration with:
  - CSRF disabled for APIs  
  - Public endpoints for registration/auth (`/api/auth/**`)  
  - Role-based protection, e.g.:
    - `/api/dispatcher/**` → `DISPATCHER`  
    - `/api/driver/**` → `DRIVER`  
    - `/api/rider/**` → `RIDER`  
- Structured to be ready for **JWT authentication** (stateless security) as a future enhancement.

### Other Implementation Details

- Per-service configuration via `application.yml`  
- `.gitignore` configured to keep build output, IDE metadata, and secrets out of Git  
- Easy to extend with **Swagger/OpenAPI**, **Docker**, additional microservices, etc.

---

## 🛠 Tech Stack

- **Language:** Java (17+ recommended)  
- **Frameworks & Libraries:**
  - Spring Boot  
  - Spring Web  
  - Spring Data JPA  
  - Spring Security  
  - Spring Cloud Netflix Eureka  
  - Spring Cloud OpenFeign  
- **Build Tool:** Maven  
- **Database:** Configurable (H2 / PostgreSQL / MySQL, etc.)  
- **IDE:** IntelliJ IDEA / VS Code  

---

## 🚀 Getting Started

### 1. Prerequisites

- Java 17+  
- Maven 3.8+  
- Internet access (for the external distance API used by `DistanceService`)  
- A local database if you are not using an in-memory DB  

### 2. Clone the Repository

```bash
git clone https://github.com/<your-username>/RideSharingDeliveryApp.git
cd RideSharingDeliveryApp
```

### 3. Configure Environment & Secrets

> ⚠️ **Never commit real credentials or API keys.**

**DistanceService** – in `DistanceService/src/main/resources/application.yml`:

```yaml
server:
  port: 8081

spring:
  application:
    name: distance-service

distance:
  base-url: https://api.distancematrix.ai
  key: YOUR_API_KEY_HERE
```

**EurekaServer** – in `EurekaServer/src/main/resources/application.yml` (example):

```yaml
server:
  port: 8761

spring:
  application:
    name: discovery-server

eureka:
  client:
    register-with-eureka: false
    fetch-registry: false
```

**Main App** – in `RideSharingDeliveryApp/src/main/resources/application.yml` configure:

- `server.port`  
- `spring.datasource.*` (if using a real DB)  
- `spring.application.name`  
- Any security or JPA properties you need  

These config files are ignored by Git (see `.gitignore`), so you can safely keep local secrets here.

---

## ▶️ How to Run the Services

Open three terminals (or three run configurations in your IDE) and start the services in this order:

### 1. EurekaServer

```bash
cd EurekaServer
mvn spring-boot:run
```

Visit: `http://localhost:8761` to see the Eureka dashboard.

### 2. DistanceService

```bash
cd DistanceService
mvn spring-boot:run
```

It should register with Eureka as `distance-service`.

### 3. RideSharingDeliveryApp (main app)

```bash
cd RideSharingDeliveryApp
mvn spring-boot:run
```

Default port is usually `8080` (unless you change it in `application.yml`).

---

## 📡 Example API Endpoints

> Endpoint paths may differ slightly depending on final controller mappings.

### DistanceService

```http
GET /api/distances/getDistance?from=Toronto&to=Montreal
```

Returns a `DistanceDto` containing the distance and duration between two locations.

### Main App – Common Endpoints

**Authentication / Registration**

```http
POST /api/auth/register
POST /api/auth/login
```

**Users**

```http
GET  /api/users
GET  /api/users/{id}
POST /api/users
```

**Trips / Dispatching**

```http
POST /api/dispatcher/trips        # create a new ride or delivery trip
GET  /api/dispatcher/trips/{id}
```

**Driver / Rider Actions**

```http
GET  /api/driver/trips/active
GET  /api/rider/trips/history
```

**Ratings**

```http
POST /api/rating/driver/{tripId}
```

These are just examples; check your actual controller classes for the complete set of mappings.

---

## 🔐 Configuration & Secrets

This project is set up to **avoid committing sensitive data**:

- `application.properties` / `application.yml` are ignored by `.gitignore`  
- API keys, database passwords, JWT secrets, etc. should live only in your **local config**  

Recommended pattern:

1. Commit **template files** like `application-example.yml`  
2. Document required properties in the template or in this README.  
3. Developers copy the template locally and rename to `application.yml`.  
