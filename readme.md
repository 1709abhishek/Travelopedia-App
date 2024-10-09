# Travelopedia v1 - Microservices based architecture

Travelopedia is a microservices-based application designed to provide a seamless travel planning experience. This README provides an overview of the project's architecture, setup instructions, and other relevant details.

## Architecture

Travelopedia follows a microservices architecture with high cohesion and low coupling. The services and their respective ports are as follows:

- **Spring Cloud Config Server**: 8888
- **Customer Service**: 8000
- **Itinerary Service**: 8100
- **Recommendation Service**: 8200
- **Budget/Expense Service**: 8300

### Service Interactions

- **Recommendation Service**: Communicates with the Customer Service to fetch places for a particular trip.
- **Budget/Expense Service**: Communicates with the Itinerary Service to fetch the itinerary for a particular trip.

### Databases

- **Customer Service**: Connected to its own database.
- **Itinerary Service**: Connected to its own database.
- **Recommendation Service**: Requires a database for fetching data on hotels and spots, but no database is needed for storing static content like popular destinations.
- **Budget/Expense Service**: Connected to its own database.

## Setup Instructions

### Prerequisites

- Java 11 or higher
- Maven
- Git

### Configuration

1. **Spring Cloud Config Server**: Ensure the config server is running on port 8888 and is connected to your local Git repository for configuration files.

   ```bash
   spring.config.name=configserver
   server.port=8888

   ```

2. **Microservices**: Each microservice should be configured to fetch its configuration from the Spring Cloud Config Server. Example configuration for a microservice:

   ```bash
   spring:
     cloud:
       config:
         uri: http://localhost:8888

   ```

3. Running the application :-

a. Start the Spring Cloud Config Server on 8888
b. Run each microservice one by one.
c. ensure the configurations are working correctly.

4. Architecture Diagrams
   ![microservices-architecture](./images/Screenshot%202024-09-29%20at%201.09.45 AM.png)
   ![configrations-server](./images/Screenshot%202024-09-29%20at%201.23.53 AM.png)
