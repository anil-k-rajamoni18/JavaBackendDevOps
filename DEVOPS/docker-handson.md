## Docker Fundamentals

### What is Docker?

Docker is a platform that allows developers to package applications and their dependencies into a standardized unit called a **container**. Containers ensure that applications run consistently across different environments, whether on a local machine, a colleague’s system, or in the cloud.

### Problems Docker Solves

| Problem | Solution with Docker |
|---------|---------------------|
| **"It works on my machine!"** | Containers provide a consistent environment, eliminating compatibility issues. |
| **Dependency conflicts** | Isolates dependencies, preventing clashes between different versions. |
| **Complex setups** | Simplifies environment setup with a single containerized solution. |
| **Inconsistent deployments** | Ensures identical behavior in development, testing, and production. |
| **Resource inefficiency** | More lightweight and faster than traditional Virtual Machines (VMs). |
| **Scalability** | Easily scales applications by running multiple containers. |

### History of Docker

- Released in **2013** by **Docker, Inc.**
- Built on **Linux container technology (LXC)**.
- Standardized containerization across the industry.

### Containers vs. Virtual Machines (VMs)

| Feature | Virtual Machines | Containers |
|---------|-----------------|------------|
| OS | Each VM has its own OS | Containers share the host OS |
| Performance | Slow startup, heavy resource use | Lightweight and fast |
| Isolation | Strong isolation but with overhead | Process-level isolation |
| Scalability | Requires more resources | Highly efficient |

### Software Deployment Before Docker

- Applications were deployed directly on physical servers or VMs.
- Dependency conflicts were common.
- Deployment was slow and error-prone.
- Scaling was difficult and inefficient.

### Benefits of Docker

- **Portability:** Run the same container across different environments.
- **Scalability:** Easily scale by running multiple containers.
- **Isolation:** Avoids dependency conflicts between applications.
- **Efficiency:** Lightweight compared to VMs, utilizing fewer resources.

---

## Key Concepts

### 1. Docker Images
- A **blueprint** for creating containers.
- Contains application code, libraries, and dependencies.
- Built using a **Dockerfile**.
- Stored in registries like **Docker Hub**.

### 2. Docker Containers
- A **running instance** of a Docker image.
- Lightweight, portable, and isolated.
- Contains everything needed to run an application.

### 3. Dockerfile
- Defines how to build a Docker image.
- Example:

  ```dockerfile
  FROM ubuntu:20.04
  RUN apt-get update && apt-get install -y python3
  COPY . /app
  CMD ["python3", "/app/main.py"]
  ```

### 4. Docker Hub
- A public registry for storing and sharing images.
- Contains official images for popular software like **nginx, mysql, python**.

### 5. Docker Architecture

| Component | Description |
|-----------|-------------|
| **Docker Daemon** | Manages Docker objects (images, containers, networks, volumes). |
| **Docker Client** | CLI tool to interact with the Docker daemon. |
| **Docker Registry** | Stores Docker images (e.g., Docker Hub). |
| **Docker Engine** | Runs and manages containers. |

**Basic Workflow:**
1. Write a **Dockerfile**.
2. Build an image using `docker build`.
3. Run a container using `docker run`.
4. Push images to a registry (optional).

---

## Installing Docker

### Windows
- Download and install **Docker Desktop** from [Docker’s official site](https://www.docker.com/products/docker-desktop/).
- Enable **WSL 2** for better performance.

### macOS
- Install **Docker Desktop** from [Docker’s official site](https://www.docker.com/products/docker-desktop/).

### Linux
- Install Docker using package managers:
  ```sh
  sudo apt-get update
  sudo apt-get install docker.io
  sudo systemctl start docker
  sudo systemctl enable docker
  ```

### Verify Installation
  ```sh
  docker --version
  ```

---

## Basic Docker Commands

### Container Management
```sh
docker run <image>        # Run a container
```
```sh
docker start <container>  # Start a stopped container
```
```sh
docker stop <container>   # Stop a running container
```
```sh
docker rm <container>     # Remove a stopped container
```
```sh
docker ps -a             # List all containers (running & stopped)
```

### Image Management
```sh
docker images            # List all images
```
```sh
docker pull <image>      # Download an image
```
```sh
docker rmi <image>       # Remove an image
```
```sh
docker build -t <tag> <path>  # Build an image from a Dockerfile
```

### System Information
```sh
docker version           # Show Docker version
```
```sh
docker info              # Display system-wide information
```

---

## Docker Networking

### Default Networks
| Network Type | Description |
|-------------|-------------|
| **Bridge** | Default, allows communication between containers on the same host. |
| **Host** | Uses the host’s network stack. |
| **None** | No networking, isolated container. |

### Network Commands
```sh
docker network ls           # List all networks
```
```sh
docker network create my-net   # Create a custom network
```
```sh
docker network connect my-net my-container  # Connect a container to a network
```

---

## Docker Volumes (Persistent Storage)

### Why Use Volumes?
- Containers are **ephemeral** (data is lost when the container stops).
- **Volumes persist data** even after a container is removed.

### Volume Commands
```sh
docker volume create my-volume   # Create a volume
```
```sh
docker volume ls                 # List volumes
```
```sh
docker volume inspect my-volume  # View volume details
```

### Mounting a Volume
```sh
docker run -d --name mysql-db -v db_data:/var/lib/mysql mysql:latest
```
- `-v db_data:/var/lib/mysql` binds the volume **db_data** to the container’s `/var/lib/mysql`.

| Storage Type | Description |
|-------------|-------------|
| **Volumes** | Managed by Docker, portable, optimized for performance. |
| **Bind Mounts** | Directly mapped from the host, useful for development. |

Example using Bind Mounts:
```sh
docker run -d --name web-app -v $(pwd)/src:/app/src nginx
```

---

## Homework 📌
✅ Install Docker and verify installation.
✅ Run the **hello-world** container.
✅ Pull and run an image (e.g., `nginx`).
✅ Explore `docker ps`, `docker images`, and `docker logs`.

---

This is a structured and easy-to-read Markdown format for Docker fundamentals. Let me know if you need further improvements! 🚀
