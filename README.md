# HealthSync-Appointment-System
![Healtcare_Appointment_System](https://github.com/user-attachments/assets/e85a9ce6-492b-4126-9e04-d7b03c18feaf)
This project is a Healthcare Appointment System built using a microservices architecture. It is designed to manage patients, doctors, appointments, and notifications efficiently. The system uses Spring Boot, Spring Cloud Gateway, PostgreSQL, and other modern tools and frameworks.

Architecture Overview

The system comprises several microservices, each responsible for specific functionalities. Below is the high-level architecture:

Key Components:

-Spring Cloud Gateway: Acts as the API gateway to route requests to the appropriate microservices.

-Authentication Service: Handles user authentication and authorization.

-Patient Service: Manages patient-related operations.

-Doctor Service: Manages doctor-related operations.

-Appointment Service: Handles scheduling and managing appointments.

-Notification Service: Sends notifications via SMS and email.

-PostgreSQL: Database for storing user, doctor, patient, and appointment data.

-Testing Frameworks: Utilizes JUnit and Mockito for testing.
    
Class Diagram:
![Class Diagram of Services](https://github.com/user-attachments/assets/58dfaad1-4392-4247-b991-234ae19f84d2)
