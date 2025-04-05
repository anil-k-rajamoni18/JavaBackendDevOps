# Stage 1: Build the application
FROM gradle:8.5-jdk17 AS build
COPY --chown=gradle:gradle . /app
WORKDIR /app

# Build the application using Gradle Wrapper
RUN gradle build --no-daemon

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp

# Copy the jar from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose port (adjust based on your app)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]
