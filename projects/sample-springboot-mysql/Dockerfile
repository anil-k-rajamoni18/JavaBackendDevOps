FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY build/libs/springboot-mysql-app-*.jar /app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]
