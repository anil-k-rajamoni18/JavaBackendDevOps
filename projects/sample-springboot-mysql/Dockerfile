FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . /app 
RUN /app/gradlew clean build
COPY /app/build/libs/*.jar app.jar
EXPOSE 8081
# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
