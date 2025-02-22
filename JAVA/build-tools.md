 ### Build Tools
- Build tools automate the process of compiling, testing, packaging, and deploying Java applications.
- They manage dependencies, generate documentation, and ensure consistent builds across environments.

=> Why Use Build Tools?
    Automate repetitive tasks.
    Manage project dependencies.
    Ensure consistent builds.
    Simplify collaboration in team environments.

=> Popular Build Tools in Java
    Apache Maven
    Gradle
    Ant (with Ivy)

## 1. Apache Maven
- Maven is a widely used build tool that uses a Project Object Model (POM) file (pom.xml) to manage project dependencies, build configurations, and plugins.
- Key Features:
    Convention over Configuration: Follows a standard project structure.
    Dependency Management: Automatically downloads and manages dependencies from repositories (e.g., Maven Central).
    Lifecycle and Phases: Defines a build lifecycle with phases like compile, test, package, install, and deploy.
    Plugins: Extensible through plugins for additional functionality.

- Project Structure:
        my-app/
        ├── src/
        │   ├── main/
        │   │   ├── java/          # Application code
        │   │   └── resources/     # Configuration files
        │   └── test/
        │       ├── java/          # Test code
        │       └── resources/     # Test resources
        ├── pom.xml               # Maven configuration file

- Example pom.xml:
<project>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example</groupId>
    <artifactId>my-app</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>

=> Common Maven Commands:
    mvn clean: Cleans the project (deletes the target directory).
    mvn compile: Compiles the source code.
    mvn test: Runs unit tests.
    mvn package: Packages the application (e.g., into a JAR file).
    mvn install: Installs the package into the local Maven repository.

## 2.Gradle
- Gradle is a modern build tool that uses a Groovy or Kotlin DSL (Domain-Specific Language) for configuration.
- Combines the best features of Maven and Ant.

Key Features:
    Flexibility: Uses a script-based configuration (build.gradle).
    Performance: Incremental builds and caching for faster builds.
    Dependency Management: Supports Maven and Ivy repositories.
    Extensibility: Custom tasks and plugins can be easily added.

Project Structure:
    my-app/
    ├── src/
    │   ├── main/
    │   │   ├── java/          # Application code
    │   │   └── resources/     # Configuration files
    │   └── test/
    │       ├── java/          # Test code
    │       └── resources/     # Test resources
    ├── build.gradle           # Gradle configuration file

=> Example build.gradle:
    plugins {
        id 'java'
    }

    group 'com.example'
    version '1.0-SNAPSHOT'

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation 'junit:junit:4.12'
    }

=> Common Gradle Commands:
    gradle clean: Cleans the project (deletes the build directory).
    gradle build: Compiles, tests, and packages the application.
    gradle test: Runs unit tests.
    gradle run: Executes the application.
