FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY build/libs/bookapi-spring-*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
