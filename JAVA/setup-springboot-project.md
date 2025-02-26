## 1. Prerequisites
    Java Development Kit (JDK): Ensure you have JDK 8 or later installed. If spring boot 3.x.x version selected ensure you have JDK >=17 
    IDE: Use an IDE like IntelliJ IDEA, Eclipse, or VS Code.
    Build Tool: Use either Maven or Gradle (Spring Boot supports both).

## 2. Setting Up a Spring Boot Project | Using Spring Initializr (Recommended)

a) Visit Spring Initializr:
    Go to https://start.spring.io/.

b) Configure Your Project:

    Project: Choose either Maven or Gradle.
    Language: Java (default).
    Spring Boot Version: Use the latest stable version.
    Project Metadata:
        Group: com.example (e.g., your organization's domain).
        Artifact: myapp (e.g., your project name).
        Name: MyApp (e.g., your project name).
        Package Name: com.example.myapp.
        Packaging: Jar (default for standalone applications).
        Java Version: Choose your JDK version (e.g., 11 or 17).

c) Add Dependencies:
    Add the required dependencies for your project. For example:

        Spring Web: For building web applications (RESTful APIs).
        Spring Data JPA: For database access.
        Spring Boot DevTools: For development-time features like auto-restart.
        H2 Database: For an in-memory database (optional).

d) Generate the Project:
        Click Generate to download a .zip file containing your project.

e) Import the Project:
        Extract the .zip file and import it into your IDE:
        IntelliJ IDEA: File > Open and select the project folder.
        Eclipse: File > Import > Maven/Gradle > Existing Project.


## Spring Boot Project Architecture and Structure

1. Overview of Spring Boot Project Architecture
Spring Boot follows a layered architecture that promotes separation of concerns and modularity. The key layers are:
        a) Presentation Layer: Handles HTTP requests and responses (e.g., REST controllers).
        b) Service Layer: Contains business logic and application services.
        c) Data Access Layer: Manages interactions with the database or other data sources.
        d) Integration Layer: Handles communication with external systems (e.g., APIs, messaging systems).

2. Standard Spring Boot Project Structure
    A well-organized Spring Boot project follows a standard directory structure. Below is an example:

    ```my-spring-boot-app/
    ├── src/
    │   ├── main/
    │   │   ├── java/                  # Java source code
    │   │   │   └── com/example/myapp/
    │   │   │       ├── MyAppApplication.java  # Main application class
    │   │   │       ├── controller/    # REST controllers
    │   │   │       ├── service/       # Business logic and services
    │   │   │       ├── repository/    # Data access layer (e.g., JPA repositories)
    │   │   │       ├── model/         # Entity classes (e.g., JPA entities)
    │   │   │       ├── dto/           # Data Transfer Objects (DTOs)
    │   │   │       ├── exception/     # Custom exception handling
    │   │   │       └── config/        # Configuration classes (e.g., security, beans)
    │   │   └── resources/             # Non-code resources
    │   │       ├── static/            # Static files (e.g., CSS, JS, images)
    │   │       ├── templates/         # Server-side templates (e.g., Thymeleaf)
    │   │       ├── application.properties  # Configuration properties
    │   │       └── data.sql           # Initial data scripts (optional)
    │   └── test/                      # Test code
    │       └── java/
    │           └── com/example/myapp/ # Unit and integration tests
    ├── pom.xml                        # Maven build configuration (or build.gradle for Gradle)
    └── README.md                      # Project documentation
    ```

### Key Components of a Spring Boot Project
1. Main Application Class
The entry point of the Spring Boot application.
    Annotated with @SpringBootApplication, which combines:
    @Configuration: Marks the class as a configuration class.
    @EnableAutoConfiguration: Enables Spring Boot's auto-configuration.
    @ComponentScan: Scans for components (e.g., controllers, services) in the package.

Example:
    package com.example.myapp;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;

    @SpringBootApplication
    public class MyAppApplication {
        public static void main(String[] args) {
            SpringApplication.run(MyAppApplication.class, args);
        }
    }

2. Controller Layer
    Handles HTTP requests and responses.
    Annotated with @RestController or @Controller.
    Maps URLs to methods using annotations like @GetMapping, @PostMapping, etc.

Example:
    package com.example.poc.controller;

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api")
    public class HelloController {

        @GetMapping("/hello")
        public String sayHello() {
            return "Hello, Spring Boot!";
        }
    }

3. Service Layer
    Contains the business logic of the application.
    Annotated with @Service.
    Called by controllers to perform operations.

    package com.example.poc.service;

    import org.springframework.stereotype.Service;

    @Service
    public class HelloService {
        public String getGreeting() {
            return "Hello from the service layer!";
        }
    }

4. Repository Layer
    Manages data access and interactions with the database.
    Annotated with @Repository.
    Extends JpaRepository for CRUD operations (if using Spring Data JPA).


    package com.example.poc.repository;

    import com.example.myapp.model.User;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
        // Custom query methods can be defined here
    }

5. Model Layer
    Represents the data entities (e.g., database tables).
    Annotated with @Entity (for JPA) or used as plain Java objects (POJOs).

    package com.example.poc.model;

    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;

    @Entity
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String email;
        private String password

        // Getters and setters
    }


6. DTO (Data Transfer Object) Layer
    Used to transfer data between layers (e.g., between controller and service).
    Helps decouple the internal data model from the API response.

    package com.example.poc.dto;

    public class UserDTO {
        private String name;
        private String email;

        // Getters and setters
    }

7. Exception Handling
 Centralized exception handling using @ControllerAdvice and @ExceptionHandler.

    package com.example.poc.exception;

    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.ResponseStatus;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(ResourceNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public String handleResourceNotFoundException(ResourceNotFoundException ex) {
            return ex.getMessage();
        }
    }

8. Configuration Classes
    Custom configuration for beans, security, etc.
    Annotated with @Configuration.

    package com.example.poc.config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;

    @Configuration
    public class AppConfig {

        @Bean
        public MyBean myBean() {
            return new MyBean();
        }
    }


9. Configuration Files
    application.properties or application.yml:
    Contains application configuration (e.g., database, server port).

    server.port=8080
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.username=sa
    spring.datasource.password=password
    spring.jpa.hibernate.ddl-auto=update


10. Testing
Unit Tests:
    Test individual components (e.g., services, repositories) using JUnit and Mockito.
    Integration Tests:
    Test the entire application using @SpringBootTest.

    package com.poc.myapp;

    import org.junit.jupiter.api.Test;
    import org.springframework.boot.test.context.SpringBootTest;

    @SpringBootTest
    class MyAppApplicationTests {

        @Test
        void contextLoads() {
        }
    }


### Example Workflow
    HTTP Request:
        A client sends a request to /api/hello.

    Controller:
        The HelloController receives the request and calls the HelloService.

    Service:
        The HelloService performs business logic and returns a response.

    Repository:
     If needed, the service interacts with the UserRepository to fetch or save data.

    Response:
        The controller returns the response to the client.

    