# Spring Boot Microservices - Quiz App

This project is a microservices-based architecture for a Quiz Application, built using Spring Boot and Spring Cloud.

## Project Structure

microservices/
├── api-gateway/ # Spring Cloud API Gateway
├── question-service # Question microservice
├── quiz-service/ # Quiz microservice
├── service-registry/ # Eureka Server


## Tech Stack

- Java 21
- Spring Boot
- Spring Cloud Gateway
- Netflix Eureka Server
- Maven

## How to Run

### 1. Start Eureka Server

```bash
cd service-registry
./mvnw spring-boot:run
URL: http://localhost:8761

2. Start API Gateway
cd ../api-gateway
URL: http://localhost:8085

3. Run both microservices

4. Hit - http://localhost:8085/quiz-service/quiz/...
          
    
