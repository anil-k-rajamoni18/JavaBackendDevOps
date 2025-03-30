# 🐳 Docker Fundamentals

## What is Docker? 🚢

- Docker is a tool that packages an application and all its dependencies (like libraries, settings, and code) into a **container**.
- A **container** is like a lightweight, portable "box" that can run the same way on any computer or server.
- It ensures that your app works **the same everywhere**, whether it's on your laptop, a teammate's computer, or in the cloud.
- Helps create, deploy, and run applications in a **consistent and isolated** environment. Think of it as a **"virtual box"** for your app.

![Docker Logo](https://upload.wikimedia.org/wikipedia/commons/4/4e/Docker_%28container_engine%29_logo.svg)

---

## 🔥 What Problems Does Docker Solve?

### 1️⃣ "It works on my machine!" problem ❌
- Ever had an app that works perfectly on your computer but breaks on someone else's?
- Docker creates a **consistent environment** for your app to run in, no matter where it’s deployed.

### 2️⃣ Dependency Conflicts ⚔️
- Different apps might need different versions of the same software (e.g., **Python 2 vs Python 3**).
- Docker **isolates dependencies** in separate containers to avoid conflicts.

### 3️⃣ Hard to Set Up Environments 🛠️
- Setting up a development environment can be **time-consuming** and **error-prone**.
- Docker **packages everything** your app needs into a container, so you can get started with just **one command**. 🚀

### 4️⃣ Inconsistent Deployments 🔄
- Deploying apps to servers can be **tricky** if the server environment isn’t identical to the development environment.
- Docker ensures that the app runs **the same way** in development, testing, and production.

### 5️⃣ Resource Efficiency ⚡
- Unlike traditional **Virtual Machines (VMs)**, Docker containers **share the host OS**, making them **lightweight and fast**.
- You can run **many containers** on a single machine without slowing it down.

### 6️⃣ Scalability 📈
- Docker makes it **easy to scale** your app.
- Quickly **spin up multiple containers** to handle more traffic or shut them down when not needed.

---

## 🕰️ Docker History

- **Released in 2013** by **Docker, Inc.**
- Built on top of **Linux container technology (LXC)**.
- Revolutionized software development by **standardizing containerization**.

More details: [Docker Official Website](https://www.docker.com/) 🌐

---

## 🏗️ Containers vs Virtual Machines (VMs)

| Feature           | Virtual Machines (VMs) 🏢 | Containers 📦 |
|------------------|----------------------|------------|
| **OS**          | Runs a full OS per VM | Shares host OS |
| **Performance** | Heavy, slow to start  | Lightweight, fast |
| **Resources**   | Each VM has its own kernel, libraries, binaries | Shares system resources efficiently |
| **Isolation**   | Fully isolated environments | Process-level isolation |
| **Use Case**    | Monolithic applications | Microservices, scalable apps |

![Containers vs VMs](https://www.docker.com/wp-content/uploads/2022/03/VM-vs-Containers.png)

---

## 🛠️ Software Process Before Docker

- Apps were deployed directly on **physical servers or VMs**.
- **Dependency conflicts** were common (e.g., different apps requiring different library versions).
- Deployment was **slow and error-prone**.
- Scaling was **difficult** due to resource constraints.

---

## 🚀 Benefits of Docker

✅ **Portability** – Run the same container across **dev, test, and prod** environments.
✅ **Scalability** – Easily **scale up/down** by running multiple containers.
✅ **Isolation** – Each container runs in its **own isolated** environment.
✅ **Efficiency** – Containers share the **host OS kernel**, making them **lightweight and fast**.

---

## Key Concepts:

1. Docker Images:
    A read-only template or blueprint used to create containers.
    Contains the application code, libraries, and dependencies.
    Built using a Dockerfile.
    Stored in registries like Docker Hub.


2. Docker Containers:
    A running instance of a Docker image.
    Lightweight, isolated, and portable.
    Contains everything needed to run the application.

3. Dockerfile:
    A text file with instructions to build a Docker image.
    Defines the base image, dependencies, and commands to run the application.
    Example:

        FROM ubuntu:20.04
        RUN apt-get update && apt-get install -y python3
        COPY . /app
        CMD ["python3", "/app/main.py"]

4. Docker Hub:
    A public registry for Docker images.
    Hosts official and community images (e.g., nginx, mysql, python).
    You can push and pull images to/from Docker Hub.

5. Docker Architecture and Workflow:
    Docker Daemon: Background service that manages Docker objects (images, containers, networks, volumes).
    Docker Client: CLI tool to interact with the Docker daemon.
    Docker Registry: Stores Docker images (e.g., Docker Hub)
    Docker Engine: The software that runs and manages containers on your computer or server.
    Workflow:
        Write a Dockerfile.
        Build an image using docker build.
        Run a container using docker run.
        Push the image to a registry (optional).


### Install Docker
1. Windows:
    Install Docker Desktop from Docker's official website (https://www.docker.com/products/docker-desktop/).
    Enable WSL 2 (Windows Subsystem for Linux) for better performance.

2. macOS:
    Install Docker Desktop from Docker's official website (https://www.docker.com/products/docker-desktop/).

3. Linux:
    Follow the official installation guide for your distribution (e.g., Ubuntu, CentOS).
    Example for Ubuntu:
        sudo apt-get update
        sudo apt-get install docker.io
        sudo systemctl start docker
        sudo systemctl enable docker

4. Verify Installation:
    Run the following command to check Docker version:
        docker --version



### Basic Docker Commands

1. Container Management

    docker run <image>: Run a container from an image.
    docker start <container>: Start a stopped container.
    docker stop <container>: Stop a running container.
    docker restart <container>: Restart a container.
    docker rm <container>: Remove a stopped container.
    docker ps: List running containers.
    docker ps -a: List all containers (running and stopped).
    docker logs <container>: View logs of a container.
    docker exec -it <container> <command>: Run a command inside a running container (e.g., docker exec -it mycontainer bash).

2. Image Management

    docker images: List all images.
    docker pull <image>: Download an image from a registry (e.g., Docker Hub).
    docker rmi <image>: Remove an image.
    docker build -t <tag> <path>: Build an image from a Dockerfile.
    docker push <image>: Push an image to a registry.

3. System Information
    docker version: Show Docker version information.
    docker info: Display system-wide information.

### Intermediate Docker Commands

1. Networking

    docker network ls: List all networks.
    docker network create <network_name>: Create a new network.
    docker network inspect <network_name>: Inspect a network.
    docker network connect <network> <container>: Connect a container to a network.
    docker network disconnect <network> <container>: Disconnect a container from a network.


3. Volumes (Persistent Storage)

    docker volume ls: List all volumes.
    docker volume create <volume_name>: Create a new volume.
    docker volume inspect <volume_name>: Inspect a volume.
    docker volume rm <volume_name>: Remove a volume.


3. Docker Compose (Multi-Container Apps)

    docker-compose up: Start containers defined in a docker-compose.yml file.
    docker-compose down: Stop and remove containers, networks, and volumes.
    docker-compose logs: View logs for services in a Compose file.
    docker-compose ps: List running services in a Compose file.


### Advanced Docker Commands

1. Container Debugging

    docker inspect <container>: Get detailed information about a container.
    docker stats: Display live resource usage statistics for containers.
    docker top <container>: Show running processes in a container.
    docker diff <container>: Inspect changes to files or directories in a container.

2. Image Management (Advanced)

    docker save <image> -o <file.tar>: Save an image to a tar file.
    docker load -i <file.tar>: Load an image from a tar file.
    docker history <image>: Show the history of an image.
    docker tag <image> <new_tag>: Tag an image with a new name or version.

3. System Cleanup

    docker system prune: Remove all unused containers, networks, and images.
    docker system prune -a: Remove all unused containers, networks, images, and volumes.
    docker container prune: Remove all stopped containers.
    docker image prune: Remove unused images.

4. Swarm Mode (Container Orchestration)

    docker swarm init: Initialize a Docker Swarm.
    docker swarm join: Join a worker node to a Swarm.
    docker service create <image>: Create a service in Swarm mode.
    docker service ls: List services in a Swarm.
    docker service scale <service>=<replicas>: Scale a service up or down.

5. Security

    docker scan <image>: Scan an image for vulnerabilities (requires Docker Scout).
    docker trust sign <image>: Sign an image for secure distribution.

6. Logs and Monitoring

    docker events: View real-time events from the Docker daemon.
    docker logs --tail <number> <container>: Show the last n lines of logs.

7. Tips
    Use --help with any command to see its usage and options (e.g., docker run --help).
    Use docker-compose for managing multi-container applications.
    Use docker swarm for container orchestration and scaling.

#### Homework:
    Install Docker on your system if you haven’t already.
    Run the hello-world container and explore its output.
    Pull an image (e.g., nginx) and run it as a container.
    Use docker ps, docker images, and docker logs to inspect your containers and images.

## Dockerfile and Building Images

1. Dockerfile Basics
    A Dockerfile is a text file that contains instructions to build a Docker image. 
    Each instruction creates a layer in the image.

Key Instructions:
a) FROM:

    Specifies the base image for the Dockerfile.
    Example: FROM ubuntu:20.04 or FROM python:3.9.

b) RUN:

    Executes commands during the image build process.
    Example: RUN apt-get update && apt-get install -y curl.

c) COPY:

        Copies files or directories from the host to the container.
        Example: COPY ./app /app.

d) ADD:

    Similar to COPY, but with additional features like:
        Extracting tar files automatically.
        Downloading files from URLs.
    Prefer COPY unless you need these extra features.


e) CMD:

    Provides a default command to run when the container starts.
    Only one CMD is allowed in a Dockerfile.
    Example: CMD ["python", "app.py"]

f) ENTRYPOINT:

    Similar to CMD, but the command is not overridden by command-line arguments.
    Often used to define the main executable of the container.
    Example: ENTRYPOINT ["python"].

g) EXPOSE:

    Informs Docker that the container listens on specific network ports.
    Example: EXPOSE 8080.

=> Difference Between ADD and COPY:
    Use COPY for simply copying files/directories.
    Use ADD if you need to extract tar files or download files from URLs.

=> Difference Between CMD and ENTRYPOINT:
    CMD provides default arguments for the container, which can be overridden at runtime.
    ENTRYPOINT defines the main executable, and arguments passed at runtime are appended to it.


*Example 1: Python App*

    # Use an official Python runtime as the base image
    FROM python:3.9-slim

    # Set the working directory in the container
    WORKDIR /app

    # Copy the requirements file into the container
    COPY requirements.txt .

    # Install dependencies
    RUN pip install --no-cache-dir -r requirements.txt

    # Copy the rest of the application code
    COPY . .

    # Expose port 5000
    EXPOSE 5000

    # Define the command to run the app
    CMD ["python", "app.py"]


*Example 2: Node.js App*

    # Use an official Node.js runtime as the base image
    FROM node:18

    # Set the working directory
    WORKDIR /app

    # Copy package.json and package-lock.json
    COPY package*.json ./

    # Install dependencies
    RUN npm install

    # Copy the rest of the application code
    COPY . .

    # Expose port 3000
    EXPOSE 3000

    # Define the command to run the app
    CMD ["node", "index.js"]


*Example 3: Java Spring Boot App*

    # Use an official OpenJDK runtime as the base image
    FROM openjdk:17-jre-slim

    # Set the working directory
    WORKDIR /app

    # Copy the JAR file into the container
    COPY target/myapp.jar .

    # Expose port 8080
    EXPOSE 8080

    # Define the command to run the app
    CMD ["java", "-jar", "myapp.jar"]


-> Build the image: docker build -t my-app .
-> Run the container: docker run -p 5000:5000 my-app  # Adjust ports as needed


### Layered File System
- Docker images are built in layers. Each instruction in the Dockerfile creates a new layer.
- Layers are cached, so if a layer hasn’t changed, Docker reuses the cached layer during builds.
- Optimize your Dockerfile to minimize the number of layers and reduce image size.
- Optimization Tips:
    a) Combine multiple RUN commands into one:
        RUN apt-get update && apt-get install -y \
            curl \
            git \
            && rm -rf /var/lib/apt/lists/*  

    b) Use .dockerignore to exclude unnecessary files from being copied into the image.


### Multi-Stage Builds

- Multi-stage builds allow you to use multiple FROM statements in a single Dockerfile.
- Each FROM statement starts a new build stage, and you can selectively copy files from one stage to another 
- This helps reduce the final image size by discarding unnecessary build artifacts.


=> How Multi-Stage Builds Work
    Build Stage: 
        This stage is used to compile or build your application. 
        It typically includes all the tools and dependencies needed for building (e.g., compilers, SDKs).

    Runtime Stage: 
        This stage is used to run your application. 
        It only includes the runtime dependencies (e.g., JRE, Node.js, Python) and the final build artifacts (e.g., compiled binaries, packaged files).

=> Benefits of Multi-Stage Builds
1. Smaller Final Image
2. Improved Security
3. Improved Security

**Example of a Multi-Stage Dockerfile**

    # Stage 1: Build the application
    FROM golang:1.20 AS builder
    WORKDIR /app
    COPY . .
    RUN go build -o myapp .

    # Stage 2: Run the application
    FROM alpine:latest
    WORKDIR /app
    # Copy only the compiled binary from the builder stage
    COPY --from=builder /app/myapp .
    # Set the command to run the application
    CMD ["./myapp"]

**Explanation of the Example**

    Stage 1 (Builder):
        Uses the golang:1.20 image, which includes the Go compiler and tools.
        Copies the source code into the container.
        Compiles the Go application into a binary named myapp.

    Stage 2 (Runtime):
        Uses a lightweight alpine image as the base for the final image.
        Copies only the compiled binary (myapp) from the builder stage using COPY --from=builder.
        Sets the command to run the binary when the container starts.


**Example-2: Multi-Stage Dockerfile for a Python App***

    # Stage 1: Build
    FROM python:3.9-slim as builder

    WORKDIR /app
    COPY requirements.txt .
    RUN pip install --user -r requirements.txt

    # Stage 2: Final Image
    FROM python:3.9-slim

    WORKDIR /app
    COPY --from=builder /root/.local /root/.local
    COPY . .

    # Ensure scripts in .local are usable
    ENV PATH=/root/.local/bin:$PATH

    EXPOSE 5000
    CMD ["python", "app.py"]


**Example-3**

    # Stage 1: Build dependencies
    FROM node:16 AS deps
    WORKDIR /app
    COPY package.json .
    RUN npm install

    # Stage 2: Build the application
    FROM node:16 AS builder
    WORKDIR /app
    COPY . .
    COPY --from=deps /app/node_modules ./node_modules
    RUN npm run build

    # Stage 3: Run the application
    FROM node:16-alpine
    WORKDIR /app
    COPY --from=builder /app/dist ./dist
    COPY --from=builder /app/node_modules ./node_modules
    CMD ["node", "dist/index.js"]

=> When to Use Multi-Stage Builds
Compiled Languages: For languages like Go, Java, C++, etc., where you need a build environment but not the build tools in the final image.
Complex Build Processes: When your build process involves multiple steps or tools that aren’t needed at runtime.
Optimizing Image Size: When you want to minimize the size of your final Docker image.

=> Key Takeaways
    Multi-stage builds help you create smaller, more secure Docker images.
    They are especially useful for compiled languages and complex build processes.
    Use COPY --from to copy files between stages.


## Docker Volumes and Networking

### 1. Docker Volumes
Why Use Volumes?
    Containers are ephemeral (data is lost when the container stops).
    Volumes provide persistent storage independent of the container lifecycle.
    Useful for databases, application data, and shared storage.

Volume Commands:
    docker volume create <name>	Creates a new named volume
    docker volume ls	Lists all volumes
    docker volume inspect <name>	Shows volume details (mount point, driver)
    docker volume rm <name>	Removes a volume
    docker volume prune	Deletes all unused volumes

=> Mounting a Volume in a Container
    docker run -d --name mysql-db -v db_data:/var/lib/mysql mysql:latest
    -v db_data:/var/lib/mysql binds the db_data volume to the container’s /var/lib/mysql.

=> Bind Mounts vs. Volumes
    Volumes:
        - Managed by Docker 
        - Portable 
        - Optimized Performance
        - Use Case: Databases, persistent storage. 

    Blind Mounts:
        - Not Managed by Docker, It is host-dependent
        - Not Portable, It required host path
        - Depends on host FS. 
        - Use case: Development (live code reload)

=> Using Bind Mounts
    docker run -d --name web-app -v /host/path:/container/path nginx:latest

Example (Development):
    docker run -d --name dev-app -v $(pwd)/src:/app/src react-app


### 3. Docker Networking

Default Networks
    Bridge (Default): Isolated network for containers on the same host.
    Host: Bypasses Docker networking, uses host’s network directly.
    None: No networking.

Network Commands:
    docker network ls	Lists all networks
    docker network create <name>	Creates a custom network
    docker network inspect <name>	Shows network details
    docker network connect <network> <container>	Connects a container to a network
    docker network disconnect <network> <container>	Disconnects a container

=> Creating and Using a Custom Network

    # Create a custom network
    docker network create my-network

    # Run containers on the same network
    docker run -d --name web --network my-network nginx
    docker run -d --name db --network my-network mysql

    # Containers can communicate using their names as DNS
    ping web  # Inside the 'db' container


