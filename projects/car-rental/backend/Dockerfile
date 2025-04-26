# === Stage 1: Build the app ===
FROM eclipse-temurin:17-jdk-alpine AS builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Download dependencies (cache-friendly)
RUN ./gradlew dependencies --no-daemon || true

# Copy full source
COPY . .

# Build app
RUN ./gradlew clean build --no-daemon

# === Stage 2: Runtime ===
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# COPY the actual JAR (adjust the name if needed)
COPY --from=builder /app/build/libs/springboot-mysql-app-0.0.1-SNAPSHOT.jar /app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app.jar"]
