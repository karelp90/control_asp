# Control ASP

Control ASP is demonstrating how to implement simple CRUD operations with the entities: Agente and Centro.

## What's inside 
This project is based on the [Spring Boot](http://projects.spring.io/spring-boot/) project and uses these packages :
- Maven
- Spring Core
- JPA
- Spring Data (Hibernate & MySQL & PostgreSQL)
- Spring MVC (Tomcat)
- [Thymleaf](www.thymeleaf.org)
- JasperReport (PDF Reports)

## Installation 
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies

## Database configuration 
Create a PostgreSQL or MySQL database  with the name `control_asp`and add the credentials to `/resources/application.properties`.  
The default ones are :

```
spring.datasource.url=jdbc:postgresql://localhost:5432/control_asp
spring.datasource.username=youruser
spring.datasource.password=yourpass
```

## Usage 
Run the project and head out to [http://localhost:8080](http://localhost:8080)
