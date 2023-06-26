## 📖 Description
The Java back-end project for the local spare parts distributor's shop 
aims to streamline and enhance the overall management and operations of the business. 
With a focus on efficiency, accuracy, and customer satisfaction, 
the project offers a comprehensive solution for inventory management, 
sales tracking, and customer relationship management. 

The project is currently in progress and under active development.

## 📋 Project Structure
This project follows a 3-tier architecture with the following components:

- Controller: Responsible for handling incoming requests, processing data, 
and generating appropriate responses. 
It acts as an interface between the client and the back-end services.
- Service: Implements the business logic and provides services to handle specific operations, 
such as managing inventory, processing sales, and managing customer relationships.
- Repository: Handles the persistence and retrieval of data from the underlying database. 
It provides an abstraction layer for interacting with the data storage.

## 🖥️ Technologies and Principles
The project utilizes a wide range of technologies and principles 
to ensure its effectiveness and efficiency. These include:

- PostgreSQL
- Java 17
- Spring Boot 3
- REST
- Spring HATEOAS
- Spring Data
- Flyway
- Validation
- Testcontainers
- Spring Security, and more.

## Additional Information
### A Few Words about Interfaces for Single Service Classes

I acknowledge that using an interface for every single class is considered bad practice.
However, using concrete classes instead of interfaces causes Spring to use Cglib instead of
the native API, which is considered the preferred approach in the Spring documentation.
Therefore, I have chosen to use interfaces even for a single class.