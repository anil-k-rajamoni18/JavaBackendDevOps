# MySQL Docker Setup Guide

## 📄 Documentation
For more details, refer to the official MySQL Docker documentation: [MySQL Docker Hub](https://hub.docker.com/_/mysql)

---

## 🛠 Run the MySQL Container
The default username for a MySQL Docker container is **root**, and by default, it has **no password**.

### 1️⃣ Create a Docker Network
```sh
docker network create my-network
```

### 2️⃣ Start the MySQL Container
```sh
docker run -d --name car-rental \
  --network my-network \
  -e MYSQL_ROOT_PASSWORD=root123 \
  -e MYSQL_DATABASE=car_rental_db \
  -p 3306:3306 \
  mysql
```

---

## 🔗 Connect to MySQL from the MySQL Command-Line Client

Run the following command to connect to the MySQL container:

```sh
docker run -it --network my-network --rm mysql mysql -hcar-rental -uroot -p
```

You will be prompted to enter the **root password** (set as `root_password` in the above example).

### 📌 Command Breakdown:
| Command | Description |
|---------|-------------|
| `docker run` | Starts a new Docker container. |
| `-it` | Runs the container in interactive mode (`-i` for input, `-t` for terminal access). |
| `--network my-network` | Connects the container to an existing Docker network named `my-network`. This allows containers within the network to communicate with each other. |
| `--rm` | Automatically removes the container once it stops to prevent unnecessary leftover containers. |
| `mysql` | Uses the MySQL Docker image. |
| `mysql` | The second `mysql` is the command executed inside the container, which runs the MySQL client. |
| `-hcar-rental` | Specifies the **host** (`car-rental`), which is the hostname of the MySQL server inside the `my-network` Docker network. |
| `-uroot` | Logs in as the MySQL **root** user. |
| `-p` | Prompts for the **password** (you will enter it manually after running the command). |

---

✅ **Now your MySQL container is running and accessible!** 🚀

---

## 💾 Persisting MySQL Data
By default, MySQL stores data inside the container. If the container is removed, the data is lost. To persist data, use **Bind Mounts** or **Docker Volumes**.

---

## 🔄 Bind Mounts
A **bind mount** maps a directory from your host machine to the container.

### 📌 Run MySQL with a Bind Mount
```sh
docker run -d --name car-rental \
  --network my-network \
  -e MYSQL_ROOT_PASSWORD=root123 \
  -e MYSQL_DATABASE=car_rental_db \
  -p 3306:3306 \
  -v /path/to/mysql-data:/var/lib/mysql \
  mysql
```

### ⚠️ Things to Consider:
- Replace `/path/to/mysql-data` with an **absolute path** on your system.
- Ensure the directory **exists** and has the correct permissions:
  ```sh
  chmod 777 /path/to/mysql-data
  ```

### ✅ Pros & ❌ Cons
| Pros | Cons |
|------|------|
| Full control over file location | Can have permission issues, needs manual setup |

---

## 📦 Docker Volumes (Recommended)
Docker **volumes** are managed by Docker and are the best way to persist database data.

### 📌 Run MySQL with a Volume
```sh
docker volume create mysql-data

docker run -d --name car-rental \
  --network my-network \
  -e MYSQL_ROOT_PASSWORD=root123 \
  -e MYSQL_DATABASE=car_rental_db \
  -p 3306:3306 \
  -v mysql-data:/var/lib/mysql \
  mysql
```

✅ **Now, even if you remove the container, your database data will persist!**

### 🔍 Verify Data Persistence
1. **Stop and remove the container:**
   ```sh
   docker stop car-rental && docker rm car-rental
   ```
2. **Start a new MySQL container with the same volume:**
   ```sh
   docker run -d --name car-rental \
     --network my-network \
     -e MYSQL_ROOT_PASSWORD=root123 \
     -e MYSQL_DATABASE=car_rental_db \
     -p 3306:3306 \
     -v mysql-data:/var/lib/mysql \
     mysql
   ```
3. ✅ **Your data will still be there.**

### ✅ Pros & ❌ Cons
| Pros | Cons |
|------|------|
| Easy to use, managed by Docker, portable | Data stored in Docker's internal location |

---

🚀 **Now your MySQL setup is fully persistent and ready for use!**

