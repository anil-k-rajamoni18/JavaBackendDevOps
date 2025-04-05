# Stage 1: Build the Go binary
FROM golang:1.21-alpine AS builder

# Install dependencies
RUN apk add --no-cache git

# Set working directory
WORKDIR /app

# Copy go source files
COPY . .

# Build the Go app
RUN go build -o movies-api

# Stage 2: Create a lightweight runtime image
FROM alpine:latest

# Install SSL certificates (required for HTTP clients in Go)
RUN apk --no-cache add ca-certificates

# Set working directory
WORKDIR /root/

# Copy the binary from builder stage
COPY --from=builder /app/movies-api .

# Expose port
EXPOSE 8080

# Run the binary
CMD ["./movies-api"]
