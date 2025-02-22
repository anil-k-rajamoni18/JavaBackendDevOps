### Spring Framework
- Spring is a comprehensive, modular framework for building enterprise-level Java applications.
- It provides infrastructure support for developing robust, scalable, and maintainable applications.

=> Key Features:

a) Inversion of Control (IoC):

    Manages object creation and dependencies, promoting loose coupling.

b) Dependency Injection (DI):
    Injects dependencies into objects, making the code more testable and maintainable.

c) Aspect-Oriented Programming (AOP):
    Enables modularization of cross-cutting concerns like logging, security, and transaction management.

d) Data Access:
    Simplifies database access with JDBC, ORM (Hibernate, JPA), and transaction management.

e) Web Framework:
    Provides Spring MVC for building web applications.

f) Integration:
    Supports integration with other frameworks and technologies like messaging (JMS), REST, and more.


=> Modules:
    Core Container (IoC, DI)
    AOP
    Data Access/Integration (JDBC, ORM, Transactions)
    Web (MVC, WebSocket)
    Testing (JUnit, TestNG integration)


## Spring Boot
- Spring Boot is an extension of the Spring Framework designed to simplify the development of production-ready applications.
- It eliminates much of the boilerplate configuration required in traditional Spring applications.

=> Key Features:

    Auto-Configuration:
        Automatically configures Spring applications based on the dependencies present in the classpath.

    Standalone Applications:
        Allows you to create standalone applications with embedded servers (Tomcat, Jetty, etc.).

    Opinionated Defaults:
        Provides sensible defaults for configurations, reducing the need for manual setup.

    Production-Ready Features:
        Includes built-in support for metrics, health checks, and externalized configuration.

    No XML Configuration:
        Uses Java-based configuration and annotations instead of XML.

=> Advantages Over Spring:
    Faster development with minimal setup.
    Embedded server for easy deployment.
    Simplified dependency management with starter POMs.
    Out-of-the-box support for RESTful services, security, and databases.

### 3. Inversion of Control (IoC): Decoupling Objects from Their Dependencies
- IoC is a design principle where the control of object creation and dependency management is transferred from the application code to a framework or container.

- Instead of objects creating their dependencies, the container injects them.

Key Points:
    Promotes loose coupling between classes.
    Enhances testability and maintainability.
    Spring IoC container manages the lifecycle of objects and their dependencies.

Example:
    public class UserService {
        private UserRepository userRepository;

        // Constructor Injection (IoC)
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    }

## 4. Dependency Injection (DI): Injecting Dependencies into Objects
- DI is a design pattern that implements IoC by injecting dependencies into a class rather than the class creating them itself.

- Types of DI: Constructor Injection, Setter Injection, and Field Injection.

Key Points:
    Constructor Injection: Preferred for mandatory dependencies.
    Setter Injection: Used for optional dependencies.
    Field Injection: Less recommended due to reduced testability.

Example:
    // Constructor Injection
    public class UserService {
        private UserRepository userRepository;

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    }

    // Setter Injection
    public class UserService {
        private UserRepository userRepository;

        public void setUserRepository(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    }

## 5. Beans: Objects Managed by the Spring IoC Container
- A bean is an object that is instantiated, assembled, and managed by the Spring IoC container.
- Beans are defined in configuration files (XML, Java annotations, or Java configuration classes).

Key Points:
    Beans are singleton by default (one instance per Spring container).
    Can be configured as prototype, request, session, etc.
    Defined using @Bean, @Component, @Service, @Repository, etc.

Example:
    @Configuration
    public class AppConfig {
        @Bean
        public UserRepository userRepository() {
            return new UserRepository();
        }
    }

    @Service
    public class UserService {
        private final UserRepository userRepository;

        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }
    }

## 6. ApplicationContext: The Central Interface for Accessing the Spring IoC Container
- ApplicationContext is the central interface in Spring that provides configuration and management of beans.

- It extends BeanFactory and adds additional features like AOP, event propagation, and resource handling.

Key Points:
    Implementations: AnnotationConfigApplicationContext, ClassPathXmlApplicationContext, FileSystemXmlApplicationContext.
    Used to retrieve beans and manage their lifecycle.
    Provides access to application-wide resources and configurations.

Example:
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);


## 7. Spring Modules: Core, AOP, JDBC, ORM, Web, MVC, etc.
Core Container:

    Provides the fundamental functionality of Spring, including IoC and DI.

    Key packages: org.springframework.beans, org.springframework.context.

AOP (Aspect-Oriented Programming):

    Enables modularization of cross-cutting concerns (e.g., logging, security).

    Uses aspects, pointcuts, and advices.

JDBC:

    Simplifies database access by abstracting boilerplate code.

    Provides JdbcTemplate for executing SQL queries.

ORM (Object-Relational Mapping):

    Integrates with ORM frameworks like Hibernate, JPA, etc.

    Provides HibernateTemplate, JpaTemplate.

Web:

    Provides features for web application development.

    Includes support for file uploads, web listeners, etc.

MVC (Model-View-Controller):

    Framework for building web applications.

    Separates concerns into models, views, and controllers.

    Uses @Controller, @RequestMapping, etc.


### Summary of Key Takeaways:
    IoC and DI are foundational concepts in Spring that promote loose coupling and testability.
    Beans are the objects managed by the Spring container.
    ApplicationContext is the central interface for accessing and managing beans.
    Spring is modular, with dedicated modules for core functionality, AOP, data access, and web development.
