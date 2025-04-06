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

![Containers vs VMs](https://aqueducttech.com/wp-content/uploads/2021/02/Cloud_VM_Container-1.png)

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

# 🐳 Docker Key Concepts

## 1️⃣ Docker Images

- 📌 A **read-only template** or blueprint used to create containers.
- 📦 Contains the **application code, libraries, and dependencies**.
- 📝 Built using a **Dockerfile**.
- 🌐 Stored in registries like [Docker Hub](https://hub.docker.com/).

## 2️⃣ Docker Containers

- 🚀 A **running instance** of a Docker image.
- 🔄 **Lightweight, isolated, and portable**.
- ✅ Contains **everything needed to run the application**.

## 3️⃣ Dockerfile 📝

- A **text file** with instructions to build a **Docker image**.
- Defines the **base image, dependencies, and commands** to run the application.
- **Example:**

  ```dockerfile
  FROM ubuntu:20.04
  RUN apt-get update && apt-get install -y python3
  COPY . /app
  CMD ["python3", "/app/main.py"]
  ```

## 4️⃣ Docker Hub 🌐

- 🏢 A **public registry** for Docker images.
- 📂 Hosts **official and community images** (e.g., `nginx`, `mysql`, `python`).
- 🔄 You can **push and pull** images to/from [Docker Hub](https://hub.docker.com/).

## 5️⃣ Docker Architecture & Workflow 🏗️

### Components:
- **Docker Daemon** 🖥️: Background service that manages Docker objects (images, containers, networks, volumes).
- **Docker Client** 🖊️: CLI tool to interact with the Docker daemon.
- **Docker Registry** 📦: Stores Docker images (e.g., Docker Hub).
- **Docker Engine** ⚙️: The software that runs and manages containers on your computer or server.

### Workflow 🔄:
1. ✍️ **Write a Dockerfile**.
2. 🏗️ **Build an image** using `docker build`.
3. 🚀 **Run a container** using `docker run`.
4. 📤 **Push the image** to a registry (optional).

---

# 🔧 Install Docker

## 🖥️ Windows:
- 📥 Install [Docker Desktop](https://www.docker.com/products/docker-desktop/).
- 🔧 Enable **WSL 2 (Windows Subsystem for Linux)** for better performance.

## 🍏 macOS:
- 📥 Install [Docker Desktop](https://www.docker.com/products/docker-desktop/).

## 🐧 Linux:
- 📜 Follow the **official installation guide** for your distribution (e.g., Ubuntu, CentOS).
- **Example for Ubuntu**:

  ```bash
  sudo apt-get update
  sudo apt-get install docker.io
  sudo systemctl start docker
  sudo systemctl enable docker
  sudo usermod -aG docker ubuntu $USER
  ```

## ✅ Verify Installation:
- Run the following command to check Docker version:

  ```bash
  docker --version
--- 


# 🧹 How to Completely Uninstall Docker

## 🐧 Ubuntu/Linux (Debian-based)

### 1. Stop Docker Services
```bash
sudo systemctl stop docker
sudo systemctl stop docker.socket
```

### 2. Uninstall Docker Packages
```bash
sudo apt-get purge docker-ce -y (for Docker Community Edition).
sudo apt-get autoremove --purge docker-ce -y (removes any remaining Docker packages and their dependencies).
```

### 3. Remove Docker Data
```bash
sudo rm -rf /var/lib/docker
sudo rm -rf /var/lib/containerd
```

### 4. Remove Config Files (Optional)
```bash
sudo rm -rf /etc/docker
sudo rm -rf ~/.docker
sudo rm -f /var/run/docker.sock
```

### 5. Cleanup System
```bash
sudo apt-get autoremove -y
sudo apt-get autoclean
```

# 🚀 Basic Docker Commands

## 🏗️ Container Management

- `docker run <image>`: Run a container from an image.
- `docker run -d --name <container-name> -p hostPort:containerPort <image>`: Run a container from an image on specific port in background
- `docker start <container>`: Start a stopped container.
- `docker stop <container>`: Stop a running container.
- `docker restart <container>`: Restart a container.
- `docker rm <container>`: Remove a stopped container.
- `docker ps`: List running containers.
- `docker ps -a`: List all containers (running and stopped).
- `docker logs <container>`: View logs of a container.
- `docker exec -it <container> <command>`: Run a command inside a running container 
    -i (interactive): Keeps STDIN open so you can interact with the container.
    -t (tty): Allocates a pseudo-terminal (like a shell).
- `docker exec -it <container_name> sh` : sh is available in Alpine-based images.
- `docker exec -it <container_name> bash` : bash is available in Debian/Ubuntu-based images.
- `docker exec -it webserver ls /app` : Run commands inside container

## 🖼️ Image Management

- `docker images`: List all images.
- `docker pull <image>`: Download an image from a registry (e.g., Docker Hub).
- `docker rmi <image>`: Remove an image.
- `docker build -t <tag> <path>`: Build an image from a Dockerfile.
- `docker push <image>`: Push an image to a registry.

## 🖥️ System Information

- `docker version`: Show Docker version information.
- `docker info`: Display system-wide information.

---

# ⚙️ Intermediate Docker Commands

## 🌐 Networking

- `docker network ls`: List all networks.
- `docker network create <network_name>`: Create a new network.
- `docker network inspect <network_name>`: Inspect a network.
- `docker network connect <network> <container>`: Connect a container to a network.
- `docker network disconnect <network> <container>`: Disconnect a container from a network.

## 💾 Volumes (Persistent Storage)

- `docker volume ls`: List all volumes.
- `docker volume create <volume_name>`: Create a new volume.
- `docker volume inspect <volume_name>`: Inspect a volume.
- `docker volume rm <volume_name>`: Remove a volume.

## 🏗️ Docker Compose (Multi-Container Apps)

- `docker-compose up`: Start containers defined in a `docker-compose.yml` file.
- `docker-compose down`: Stop and remove containers, networks, and volumes.
- `docker-compose logs`: View logs for services in a Compose file.
- `docker-compose ps`: List running services in a Compose file.

---

# 🔥 Advanced Docker Commands

## 🛠️ Container Debugging

- `docker inspect <container>`: Get detailed information about a container.
- `docker stats`: Display live resource usage statistics for containers.
- `docker top <container>`: Show running processes in a container.
- `docker diff <container>`: Inspect changes to files or directories in a container.

## 🖼️ Image Management (Advanced)

- `docker save <image> -o <file.tar>`: Save an image to a tar file.
- `docker load -i <file.tar>`: Load an image from a tar file.
- `docker history <image>`: Show the history of an image.
- `docker tag <image> <new_tag>`: Tag an image with a new name or version.

## 🧹 System Cleanup

- `docker system prune`: Remove all unused containers, networks, and images.
- `docker system prune -a`: Remove all unused containers, networks, images, and volumes.
- `docker container prune`: Remove all stopped containers.
- `docker image prune`: Remove unused images.

## ⚖️ Swarm Mode (Container Orchestration)

- `docker swarm init`: Initialize a Docker Swarm.
- `docker swarm join`: Join a worker node to a Swarm.
- `docker service create <image>`: Create a service in Swarm mode.
- `docker service ls`: List services in a Swarm.
- `docker service scale <service>=<replicas>`: Scale a service up or down.

## 🔒 Security

- `docker scan <image>`: Scan an image for vulnerabilities (requires Docker Scout).
- `docker trust sign <image>`: Sign an image for secure distribution.

## 📊 Logs and Monitoring

- `docker events`: View real-time events from the Docker daemon.
- `docker logs --tail <number> <container>`: Show the last `n` lines of logs.

## 💡 Tips

- Use `--help` with any command to see its usage and options (e.g., `docker run --help`).
- Use `docker-compose` for managing multi-container applications.
- Use `docker swarm` for container orchestration and scaling.

---

# 📌 Homework

- Install Docker on your system if you haven’t already.
- Run the `hello-world` container and explore its output.
- Pull an image (e.g., `nginx`) and run it as a container.
- Use `docker ps`, `docker images`, and `docker logs` to inspect your containers and images.

![Docker Workflow](https://miro.medium.com/v2/resize:fit:1100/format:webp/1*QzySxXC6-Kkvmq-F0tHkxA.jpeg)

--- 

# 🐳 Dockerfile and Building Images

## 1️⃣ Dockerfile Basics
A **Dockerfile** is a text file containing instructions to build a Docker image. Each instruction creates a layer in the image.

### 📌 Key Instructions:

### 🔹 `FROM`
Specifies the **base image** for the Dockerfile.
```dockerfile
FROM ubuntu:20.04
FROM python:3.9
```

### 🔹 `RUN`
Executes commands **during the image build process**.
```dockerfile
RUN apt-get update && apt-get install -y curl
```

### 🔹 `COPY`
Copies files or directories **from the host to the container**.
```dockerfile
COPY ./app /app
```

### 🔹 `ADD`
Similar to `COPY`, but with additional features:
- Extracts **tar files** automatically.
- Downloads files from **URLs**.
```dockerfile
ADD myfile.tar.gz /app/
ADD https://example.com/file.zip /app/
```
🛠 **Prefer `COPY` unless you need these extra features.**

### 🔹 `CMD`
Provides a **default command** to run when the container starts (only one `CMD` allowed).
```dockerfile
CMD ["python", "app.py"]
```

### 🔹 `ENTRYPOINT`
Similar to `CMD`, but the command **is not overridden** by command-line arguments.
```dockerfile
ENTRYPOINT ["python"]
```

### 🔹 `EXPOSE`
Informs Docker that the container **listens on specific network ports**.
```dockerfile
EXPOSE 8080
```

---

## 🔄 Difference Between `ADD` and `COPY`
✅ **Use `COPY`** for simply copying files/directories.
✅ **Use `ADD`** if you need to **extract tar files** or **download files from URLs**.

## 🔄 Difference Between `CMD` and `ENTRYPOINT`
✅ **`CMD`** provides default arguments for the container, which can be overridden at runtime.
✅ **`ENTRYPOINT`** defines the main executable, and arguments passed at runtime **are appended to it**.

---

## 🚀 Example Dockerfiles

### 🐍 Example 1: **Python App**
```dockerfile
FROM python:3.9-slim
WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY . .
EXPOSE 5000
CMD ["python", "app.py"]
```

### 🌍 Example 2: **Node.js App**
```dockerfile
FROM node:18
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 3000
# Define the command to run the app
CMD ["node", "index.js"]
```

### ☕ Example 3: **Java Spring Boot App**
```dockerfile
FROM openjdk:17-jre-slim
WORKDIR /app
COPY target/myapp.jar .
EXPOSE 8080
CMD ["java", "-jar", "myapp.jar"]
```

---

## 📦 Building and Running the Image

🔹 **Build the image:**
```sh
docker build -t my-app .
```

🔹 **Run the container:**
```sh
docker run -p 5000:5000 my-app  # Adjust ports as needed
```
--- 


# 🚢 Docker Layered File System & Multi-Stage Builds

## 🗂 Layered File System
- Docker images are built in **layers**. Each instruction in the Dockerfile creates a new layer.
- **Layers are cached**, so if a layer hasn’t changed, Docker reuses the cached layer during builds.
- Optimize your Dockerfile to **minimize the number of layers** and **reduce image size**.

### ✅ Optimization Tips:
🔹 **Combine multiple RUN commands into one:**
```bash
RUN apt-get update && apt-get install -y \
    curl \
    git \
    && rm -rf /var/lib/apt/lists/*
```
🔹 **Use .dockerignore** to exclude unnecessary files from being copied into the image.

---

## 🔄 Multi-Stage Builds

- Multi-stage builds allow you to use **multiple `FROM` statements** in a single Dockerfile.
- Each `FROM` statement starts a **new build stage**, and you can selectively **copy files** from one stage to another.
- This helps **reduce the final image size** by discarding unnecessary build artifacts.

### 📌 How Multi-Stage Builds Work:
**1️⃣ Build Stage:**  
   - Used to **compile or build** your application.  
   - Includes all the **tools and dependencies** needed for building (e.g., compilers, SDKs).

**2️⃣ Runtime Stage:**  
   - Used to **run your application**.  
   - Includes only the **runtime dependencies** and the final build artifacts (e.g., compiled binaries, packaged files).

### 🎯 Benefits of Multi-Stage Builds:
✅ **Smaller Final Image**  
✅ **Improved Security**  
✅ **Faster Deployment**  

---

## 🚀 Multi-Stage Dockerfile Examples

### 🔹 Example 1: Golang App 🐹
```dockerfile
# Stage 1: Build the application
FROM golang:1.20 AS builder
WORKDIR /app
COPY . .
RUN go build -o myapp .

# Stage 2: Run the application
FROM alpine:latest
WORKDIR /app
COPY --from=builder /app/myapp .
CMD ["./myapp"]
```
📌 **Explanation:**
- **Stage 1 (Builder):** Uses Go compiler to **build the binary**.
- **Stage 2 (Runtime):** Uses a **lightweight Alpine image** and only includes the **compiled binary**.

---

### 🔹 Example 2: Python App 🐍
```dockerfile
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
ENV PATH=/root/.local/bin:$PATH
EXPOSE 5000
CMD ["python", "app.py"]
```
📌 **Explanation:**
- **Stage 1:** Installs dependencies using `pip`.
- **Stage 2:** Copies installed dependencies & source code into a clean Python image.

---

### 🔹 Example 3: Node.js App 🌐
```dockerfile
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
```
📌 **Explanation:**
- **Stage 1:** Installs dependencies.
- **Stage 2:** Builds the application.
- **Stage 3:** Runs the application in a **lightweight Node.js runtime**.

---

## 📅 When to Use Multi-Stage Builds?
✅ **Compiled Languages:** (Go, Java, C++) to **remove build tools** from final image.  
✅ **Complex Build Processes:** When multiple steps or tools are required at **build time** but not at **runtime**.  
✅ **Optimizing Image Size:** Reduces the **final image footprint** by discarding unnecessary dependencies.  

---

## 🎯 Key Takeaways
🚀 Multi-stage builds help create **smaller, more secure Docker images**.  
📦 Useful for **compiled languages** and **complex build processes**.  
🔁 Use `COPY --from` to copy files **between stages**.  

💡 **Optimize your builds, reduce image size, and deploy faster!** 🔥


--- 

# Docker Volumes and Networking 📦🌐

## 1. Docker Volumes 🗄️

### Why Use Volumes?
- Containers are **ephemeral** (data is lost when the container stops).
- Volumes provide **persistent storage** independent of the container lifecycle.
- Useful for **databases, application data, and shared storage**.

### Volume Commands 🛠️
```sh
docker volume create <name>    # Creates a new named volume
docker volume ls               # Lists all volumes
docker volume inspect <name>   # Shows volume details (mount point, driver)
docker volume rm <name>        # Removes a volume
docker volume prune            # Deletes all unused volumes
```

### Mounting a Volume in a Container 🚢
```sh
docker run -d --name mysql-db -v db_data:/var/lib/mysql mysql:latest
```
📌 `-v db_data:/var/lib/mysql` binds the `db_data` volume to the container’s `/var/lib/mysql`.

### Bind Mounts vs. Volumes 🔍
| Feature         | Volumes | Bind Mounts |
|---------------|---------|------------|
| Managed by Docker | ✅ Yes | ❌ No |
| Portability | ✅ Portable | ❌ Host-dependent |
| Performance | ✅ Optimized | ❌ Depends on host FS |
| Use Case | Databases, Persistent Storage | Development (Live Code Reload) |

### Using Bind Mounts 🖇️
```sh
docker run -d --name web-app -v /host/path:/container/path nginx:latest
```
📌 **Example (Development):**
```sh
docker run -d --name dev-app -v $(pwd)/src:/app/src react-app
```

---

## 2. Docker Networking 🌍
- Docker networking allows containers to communicate with each other and with the external world. 
- It is a key part of building containerized applications that involve multiple services like databases, APIs, and frontends.


### 📦 Types of Docker Networks

Docker provides several built-in network drivers:

#### 1. **Bridge (default)**
- **Used for containers on the same host.**
- Each container gets its own IP address.
- Ideal for standalone applications or those that communicate with other containers via internal networking.
```bash
docker network create my-bridge-net
docker run --network my-bridge-net
```

#### 2. Host
- Shares the host's network stack (no isolation).
- No separate IP; the container uses the host's IP.
- Useful for high-performance or low-latency applications.
```bash
docker run --network host ...
```
#### 3. None
- Disables all networking for a container.
- Isolated container with no external access.

```bash
docker run --network none ...
```

#### 4. Overlay
- For multi-host communication (used with Docker Swarm or Kubernetes).
- Enables containers across different physical hosts to communicate securely.
```bash
docker network create --driver overlay my-overlay-net
```

### 🧩 Custom User-Defined Bridge Network
User-defined bridge networks are preferred over the default bridge because:
  - Containers can reference each other by name.
  - DNS-based service discovery is enabled.
  - Better network isolation and management.

**Example:**
```dockerfile
services:
  db:
    container_name: mydb
    networks:
      - my-network

  api:
    container_name: myapi
    depends_on:
      - db
    networks:
      - my-network

networks:
  my-network:
    driver: bridge
```

#### On the Same Network:
- Use container name as hostname.
- No need to expose ports unless accessing from outside.

### Network Commands ⚡
```sh
docker network ls                            # Lists all networks
docker network create <name>                 # Creates a custom network
docker network inspect <name>                # Shows network details
docker network connect <network> <container> # Connects a container to a network
docker network disconnect <network> <container> # Disconnects a container
```

### Creating and Using a Custom Network 🔗

```sh
# Create a custom network
docker network create my-network

# Run containers on the same network
docker run -d --name web --network my-network nginx
docker run -d --name db --network my-network mysql

# Containers can communicate using their names as DNS
ping web  # Inside the 'db' container
```

---
📌 **Pro Tip:**
- Use **volumes** for **persistent storage** like databases.
- Use **custom networks** for inter-container communication.


--- 
# 🚀 Docker Compose - Managing Multi-Container Applications

---

## 📖 1. What is Docker Compose? 🐳

Docker Compose is a tool that simplifies the management of multi-container Docker applications. Instead of manually running `docker run` commands for each container, you can define all services in a single `docker-compose.yml` file and manage them collectively.

### ✅ Key Features of Docker Compose:
- **Declarative Configuration:** Define multiple containers in one YAML file.
- **Simplified Management:** Start, stop, and manage services with single commands.
- **Networking:** Automatically sets up a network for services to communicate.
- **Environment Variables:** Easily configure services with `.env` files.
- **Persistent Storage:** Attach volumes for data persistence.

### 📄 Structure of `docker-compose.yml`
```yaml
version: '3.8'  # Specifies the Docker Compose version

services:
  web:
    image: nginx
    ports:
      - "8080:80"
    depends_on:
      - db
  
  db:
    image: mysql:5.7
    environment:
      MYSQL_ROOT_PASSWORD: example
    volumes:
      - db_data:/var/lib/mysql

volumes:
  db_data:
```

### 🔹 Explanation:
- **`services:`** Defines the application components.
- **`web:`** Runs an Nginx container and exposes port 8080.
- **`db:`** Runs a MySQL container with environment variables.
- **`depends_on:`** Ensures `db` starts before `web`.
- **`volumes:`** Defines persistent storage for MySQL data.

---

## ⚙️ 2. Key Docker Compose Commands

### 🚀 Starting & Stopping Services
```sh
# Start all services in the background
docker-compose up -d

# Stop and remove all containers, networks, and volumes
docker-compose down
```

### 📜 Viewing Logs
```sh
# View logs of all running services
docker-compose logs

# View logs for a specific service
docker-compose logs web
```

### 🔄 Restarting Services
```sh
# Restart a specific service
docker-compose restart web
```

### 🏗️ Building Custom Images
```sh
# Build images for services defined in docker-compose.yml
docker-compose build
```

### 🔍 Debugging Containers
```sh
# Open an interactive shell inside a running container
docker-compose exec web sh
```

---

## 🎯 3. Create a Multi-Container App (WordPress + MySQL)

Let's deploy a WordPress application with MySQL as the database.

### 📄 `docker-compose.yml`
```yaml
version: '3.8'

services:
  wordpress:
    image: wordpress:latest
    ports:
      - "8080:80"
    environment:
      WORDPRESS_DB_HOST: db
      WORDPRESS_DB_USER: user
      WORDPRESS_DB_PASSWORD: password
      WORDPRESS_DB_NAME: wordpress
    depends_on:
      - db
  
  db:
    image: mysql:5.7
    environment:
      MYSQL_DATABASE: wordpress
      MYSQL_USER: user
      MYSQL_PASSWORD: password
      MYSQL_ROOT_PASSWORD: rootpassword
    volumes:
      - db_data:/var/lib/mysql

volumes:
  db_data:
```

### 🚀 Deployment Steps:
```sh
# Step 1: Start the WordPress and MySQL services
docker-compose up -d

# Step 2: Open a browser and go to http://localhost:8080
```

🎉 Now you have a working WordPress website running with a MySQL database!

---

## 🔍 Main Observations
- **Simplifies Multi-Container Apps:** Using a single YAML file, you can define and manage multiple services efficiently.
- **Automatic Networking:** All services in a Compose file communicate on the same network without manual configuration.
- **Data Persistence:** Volumes ensure database data is not lost when containers are restarted.
- **Portability:** The same `docker-compose.yml` can be used across different environments.

---

## ⚠️ Common Issues & Challenges

### 1️⃣ Containers Not Starting 🚨
**Issue:** `docker-compose up` fails due to missing environment variables or dependency errors.
🔹 **Solution:** Check logs using `docker-compose logs` and verify environment variable configurations.

### 2️⃣ Database Connection Issues 🔗
**Issue:** The web application fails to connect to the database.
🔹 **Solution:**
- Ensure the correct database hostname (`WORDPRESS_DB_HOST: db`).
- Check if the MySQL service is running (`docker-compose ps`).

### 3️⃣ Port Conflicts 🛑
**Issue:** Port 8080 is already in use.
🔹 **Solution:** Change the port mapping in `docker-compose.yml` (e.g., `9090:80`).

### 4️⃣ File Permission Issues 📝
**Issue:** Volume mounting errors due to file permission restrictions.
🔹 **Solution:** Use `chmod` to adjust file permissions or run containers as a non-root user.

### 5️⃣ Data Not Persisting 🔄
**Issue:** Database resets after restarting the container.
🔹 **Solution:** Ensure volumes are correctly defined and used.

---

## ✅ Summary
- Docker Compose is essential for managing multi-container applications.
- It simplifies deployment, networking, and data persistence.
- Use `docker-compose up` and `docker-compose down` for easy container management.
- Practice by deploying WordPress with MySQL.


---
# 🐳 Docker Registry and Image Management

---

## 1️⃣ Docker Hub 🌍

Docker Hub is a cloud-based registry where Docker images can be stored and shared.

### 🔹 Pushing and Pulling Images

- **Pull an image from Docker Hub**  
  ```sh
  docker pull nginx:latest
  ```
- **Tag an image before pushing**  
  ```sh
  docker tag myapp:latest mydockerhubusername/myapp:v1.0
  ```
- **Push an image to Docker Hub**  
  ```sh
  docker push mydockerhubusername/myapp:v1.0
  ```
- **List locally stored images**  
  ```sh
  docker images
  ```

### 🔹 Authenticating with Docker Hub
Before pushing, ensure you are logged in:
```sh
docker login
```
It will prompt for your Docker Hub credentials.

---

## 2️⃣ Private Docker Registries 🔒

A private registry allows storing images securely within an organization.

### 🔹 Setting Up a Private Docker Registry
- **Run a local registry container**  
  ```sh
  docker run -d -p 5000:5000 --name registry registry:2
  ```
- **Tag and push an image to the local registry**  
  ```sh
  docker tag myapp localhost:5000/myapp:v1.0
  docker push localhost:5000/myapp:v1.0
  ```
- **Pull an image from the local registry**  
  ```sh
  docker pull localhost:5000/myapp:v1.0
  ```
- **List images in the registry**  
  ```sh
  curl http://localhost:5000/v2/_catalog
  ```

---

## 3️⃣ Image Optimization 📏

Optimizing images reduces size and improves efficiency.

### 🔹 Multi-Stage Builds 🏗️
- Use multi-stage builds to remove unnecessary files in the final image.
- Example for a **Golang application**:
  ```dockerfile
  # Stage 1: Build stage
  FROM golang:1.20 AS builder
  WORKDIR /app
  COPY . .
  RUN go build -o myapp

  # Stage 2: Minimal runtime image
  FROM alpine:latest
  WORKDIR /app
  COPY --from=builder /app/myapp .
  CMD ["./myapp"]
  ```

### 🔹 Using `.dockerignore` 🚫

Exclude unnecessary files when building an image:
```txt
node_modules/
*.log
.git
.env
```

Add this `.dockerignore` file in the project root to prevent copying unwanted files into the image.

---

## 🔥 Practice 🛠️

### ✅ Push a Custom Image to Docker Hub
1. Build a Docker image:
   ```sh
   docker build -t myapp .
   ```
2. Tag it:
   ```sh
   docker tag myapp mydockerhubusername/myapp:v1.0
   ```
3. Push it to Docker Hub:
   ```sh
   docker push mydockerhubusername/myapp:v1.0
   ```

### ✅ Optimize an Image Using Multi-Stage Builds
1. Modify the Dockerfile to use multi-stage builds.
2. Rebuild the image:
   ```sh
   docker build -t optimized-app .
   ```
3. Compare sizes:
   ```sh
   docker images
   ```

---

## 🔍 Main Observations 📌
- Docker Hub is the default registry but private registries offer better control.
- Multi-stage builds significantly reduce image size.
- `.dockerignore` prevents unwanted files from being added to the image.
- Authentication is required before pushing images to Docker Hub.

---

## ⚠️ Common Issues & Challenges 🛑

### ❌ "Denied: requested access to the resource is denied"
🔹 Ensure you are logged in:  
```sh
docker login
```
🔹 Verify you have permission to push images.

### ❌ "Image is too large"
🔹 Use multi-stage builds.
🔹 Use smaller base images (e.g., `alpine`, `distroless`).

### ❌ "Cannot connect to the Docker daemon"
🔹 Ensure Docker is running.
🔹 Try running with `sudo` if using Linux.

### ❌ "Connection refused when pushing to a private registry"
🔹 Ensure the registry is running and accessible.
🔹 If using an insecure registry, configure Docker to allow it:
  ```json
  { "insecure-registries": ["localhost:5000"] }
  ```

---

# 🐳 Advanced Docker Concepts

---
## 1️⃣ Docker Swarm: Native Container Orchestration 🚢

### 🔹 What is Docker Swarm?
Docker Swarm is Docker's built-in container orchestration tool that allows managing multiple containers across multiple hosts.

### 🔹 Key Features of Docker Swarm
- **Cluster Management**: Swarm turns multiple Docker hosts into a single virtual system.
- **Scaling**: Easily scale services up or down using `docker service scale`.
- **Load Balancing**: Built-in load balancer distributes traffic across containers.
- **Rolling Updates**: Update services with minimal downtime.
- **Fault Tolerance**: Ensures high availability by redistributing workloads.


### 🔹 Swarm Commands
```bash
# Initialize a Swarm cluster
$ docker swarm init

# Add worker nodes to the Swarm
$ docker swarm join --token <token> <manager-ip>:2377

# Deploy a service in Swarm
$ docker service create --name web -p 80:80 nginx

# Scale a service
$ docker service scale web=5

# List services
$ docker service ls

# Remove a Swarm cluster
$ docker swarm leave --force
```
---


## 2️⃣ Docker Swarm vs Kubernetes 🤔

| Feature              | Docker Swarm | Kubernetes |
|----------------------|--------------|------------|
| Ease of Setup       | ✅ Simple | ❌ Complex |
| Scaling            | ✅ Quick scaling | ✅ Advanced scaling |
| Load Balancing     | ✅ Built-in | ✅ External LB required |
| Rolling Updates    | ✅ Yes | ✅ Yes |
| Auto-healing       | ❌ Limited | ✅ Yes |
| Networking         | ✅ Simple | ✅ Complex but powerful |
| Community Support  | ✅ Smaller | ✅ Large & active |
| Industry Adoption  | 🚀 Medium | 🌍 Widely adopted |

**➡️ When to use Docker Swarm?**
- Small to medium-scale applications.
- Simple orchestration without complex configurations.
- Faster deployment and learning curve.

**➡️ When to use Kubernetes?**
- Large-scale, enterprise-grade applications.
- Advanced networking and auto-healing required.
- Need robust monitoring, logging, and integrations.

---

## 2️⃣ Docker Security 🔒

**Best Practices for Securing Containers:**
- Use **official** and **trusted images**.
- Apply **least privilege principle** (limit root access inside containers), **Run Containers as Non-Root Users**.
- Regularly **scan images for vulnerabilities**.
- Use **Docker Content Trust (DCT)** for image verification.
- Apply **resource limits** to prevent misuse.

**Vulnerability Scanning with `docker scan`:**
```sh
# Scan a Docker image for security vulnerabilities
docker scan nginx
```

**Enabling Docker Content Trust (DCT):**
```sh
# Enable DCT before pulling/pushing images
export DOCKER_CONTENT_TRUST=1
```

---

## 3️⃣ Resource Management ⚙️

**Why Manage Resources?**
- Prevents excessive CPU and memory usage by containers.
- Ensures fair resource allocation in multi-container environments.
- Avoids performance degradation on the host system.

**Limiting CPU & Memory Usage:**
```sh
# Limit a container to use only 512MB of memory and 50% of a CPU core
docker run -d --memory=512m --cpus=0.5 nginx
```

**Monitor Resource Usage:**
```sh
# Real-time container resource usage
docker stats
```

---

## 4️⃣ Troubleshooting Common Docker Issues 🛠️

**1️⃣ Container Won’t Start 🚫**
- Check logs: `docker logs <container>`
- Verify image existence: `docker images`
- Run interactively to debug: `docker run -it --rm <image> /bin/sh`

**2️⃣ Port Binding Issues 🌐**
- Ensure port is mapped correctly: `docker ps -a`
- Check if port is in use: `netstat -tulnp | grep <port>`
- Restart Docker daemon: `systemctl restart docker`

**3️⃣ Network Connectivity Issues 🔗**
- Inspect container network: `docker network inspect <network>`
- Check connectivity: `docker exec -it <container> ping <target>`
- Restart container network: `docker network rm <network> && docker network create <network>`

**4️⃣ High Resource Consumption 📈**
- Use `docker stats` to identify resource-heavy containers.
- Limit resources using `--memory` and `--cpus` options.
- Optimize Dockerfile to reduce unnecessary overhead.

---

## 🛠️ Practice Tasks
✅ Deploy a service in Docker Swarm.
✅ Apply CPU and memory resource limits to a running container.
✅ Scan an image for vulnerabilities using `docker scan`.
✅ Troubleshoot a failing container and fix the issue.

---

## 📌 Main Observations
✔️ Docker Swarm simplifies multi-node orchestration but has limitations compared to Kubernetes.
✔️ Security is critical; always use trusted images and scan for vulnerabilities.
✔️ Managing resources helps improve performance and avoid container overuse.
✔️ Troubleshooting is easier with `docker logs`, `docker stats`, and `docker network inspect`.

---

## ⚠️ Common Issues & Challenges
❌ **Docker Swarm Scaling Issues:** Swarm works well for small clusters but lacks advanced features of Kubernetes.

❌ **Security Misconfigurations:** Running containers as root or not scanning for vulnerabilities can lead 
to breaches.

❌ **Resource Starvation:** Poorly managed containers can hog CPU and memory, affecting host performance.

❌ **Network Failures:** Misconfigured networks or overlapping subnets can cause connectivity problems.



--- 
# Real-World Projects and Best Practices with Docker 🚀

## 🏗️ **Project 1: CI/CD Pipeline with Docker**

### 🔹 **What is CI/CD?**
Continuous Integration and Continuous Deployment (CI/CD) is a DevOps practice that automates the building, testing, and deployment of applications.

### 🔹 **Why Use Docker in CI/CD?**
- Ensures consistency across different environments.
- Speeds up builds by caching dependencies.
- Simplifies deployment and rollback processes.

### 🔹 **Steps to Integrate Docker into a CI/CD Pipeline**

1. **Build a Docker Image** 📦
   ```sh
   docker build -t myapp:latest .
   ```

2. **Push Image to Docker Hub / Private Registry** ☁️
   ```sh
   docker tag myapp:latest myrepo/myapp:latest
   docker push myrepo/myapp:latest
   ```

3. **Deploy Container on Server** 🚀
   ```sh
   docker run -d -p 80:80 myrepo/myapp:latest
   ```

### 🔹 **Example: CI/CD with GitHub Actions** 🛠️
```yaml
name: Docker CI/CD Pipeline
on:
  push:
    branches:
      - main
jobs:
  build-and-deploy:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout Code
        uses: actions/checkout@v2
      - name: Build Docker Image
        run: docker build -t myapp:latest .
      - name: Push to Docker Hub
        run: |
          echo "${{ secrets.DOCKER_PASSWORD }}" | docker login -u "${{ secrets.DOCKER_USERNAME }}" --password-stdin
          docker tag myapp:latest myrepo/myapp:latest
          docker push myrepo/myapp:latest
      - name: Deploy Container
        run: ssh user@server 'docker pull myrepo/myapp:latest && docker run -d -p 80:80 myrepo/myapp:latest'
```

---

## 🏗️ **Project 2: Microservices with Docker**

### 🔹 **What are Microservices?**
Microservices architecture structures an application as a collection of small, loosely coupled services that communicate over a network.

### 🔹 **Why Use Docker for Microservices?**
- Isolates each microservice in a separate container.
- Simplifies scaling and deployment.
- Allows each service to use different tech stacks.

### 🔹 **Example: Deploying Microservices with Docker Compose** 📝
**docker-compose.yml**
```yaml
version: '3.8'
services:
  web:
    image: myapp-web
    build: ./web
    ports:
      - "8080:8080"
    depends_on:
      - db
  db:
    image: mysql:5.7
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: mydb
      MYSQL_USER: user
      MYSQL_PASSWORD: password
    volumes:
      - db-data:/var/lib/mysql
volumes:
  db-data:
```

### 🔹 **Deploying the Application**
```sh
docker-compose up -d
```

---

## 🏆 **Best Practices for Docker in Production**

### ✅ **Use Official Base Images**
- Prefer lightweight images (e.g., `alpine` versions) to reduce attack surface.
- Keep dependencies up to date.

### ✅ **Keep Containers Lightweight**
- Use multi-stage builds to minimize image size.
- Remove unnecessary files using `.dockerignore`.

### ✅ **Follow the Principle of One Process per Container**
- Each container should run a single service (e.g., database, web server, API).

### ✅ **Use Environment Variables for Configuration**
- Avoid hardcoding secrets inside images.
- Use `.env` files for sensitive configurations.

### ✅ **Implement Logging and Monitoring**
- Use `docker logs <container>` for debugging.
- Implement centralized logging (e.g., ELK Stack, Prometheus, Grafana).

---

## 🔍 **Main Observations**
- Docker significantly simplifies deployment and environment consistency.
- Using Docker in CI/CD automates builds, testing, and deployments.
- Microservices architecture benefits greatly from containerization.
- Security and resource management should be considered in production.

---

## ⚠️ **Common Issues & Challenges**

### ❌ **Image Size is Too Large**
✅ Use multi-stage builds and minimize layers.

### ❌ **Containers Restarting Frequently**
✅ Check logs using `docker logs <container>` to find the issue.
✅ Ensure ports and environment variables are correctly set.

### ❌ **Networking Issues Between Containers**
✅ Ensure containers are on the same Docker network.
✅ Use `docker network inspect <network>` to debug.

### ❌ **Security Risks in Images**
✅ Scan images using `docker scan <image>`.
✅ Use official, trusted base images.

---

### 🏁 **Practice: Build and Deploy a Real-World Application Using Docker**

1. Create a simple web app (e.g., Flask, Express, or Spring Boot).
2. Dockerize the application using a `Dockerfile`.
3. Set up CI/CD to build and deploy the image.
4. Deploy a microservices-based application using Docker Compose.
5. Optimize image size and apply best practices.

