FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

RUN . /app 

RUN ./gradlew clean build 

COPY build/libs/*.jar app.jar

EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
