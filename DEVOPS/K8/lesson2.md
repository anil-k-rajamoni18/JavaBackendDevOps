# Kubernetes Training - Chapter 2 🚀

## Core Kubernetes Objects & Deployments

## Syntax/Structure of YAML File 📄
- YAML (Yet Another Markup Language) is used for Kubernetes configurations. 
- It follows indentation-based syntax.

### Basic YAML Structure:
```yaml
apiVersion: v1   # Specifies the API version
kind: Pod        # Defines the object type
metadata:        # Metadata about the object
  name: my-pod   # Name of the Pod
spec:            # Specifications of the object
  containers:
  - name: my-container
    image: nginx
    ports:
    - containerPort: 80
```

🔹 **Key YAML Components**:
- **apiVersion**: Defines the API version.
- **kind**: Specifies the Kubernetes object type.
- **metadata**: Stores data like name and labels.
- **spec**: Defines the desired state.
- **containers**: Lists containers within the Pod.

---

## Understanding Pods 🏗️

### What is a Pod? 📦
A **Pod** is the smallest and simplest unit in Kubernetes, representing a single instance of a running process in a cluster.

🔹 Can contain **one or more containers**.

🔹 Shares **network and storage** resources.

🔹 Communicates via **localhost** within the Pod.

🔹 Managed by **ReplicaSets, Deployments, and StatefulSets**.

### Pod Example (YAML Configuration)
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-pod
  labels:
    app: my-app
spec:
  containers:
  - name: my-container
    image: nginx
    ports:
    - containerPort: 80
```

🔹 Deploy using:
```sh
kubectl apply -f pod.yaml
```


### 🔄 Container States
- Containers run within Pods and follow a simpler lifecycle defined by the container runtime (like containerd)

1. Waiting
  - The container is waiting to be started. This can happen due to:
      - Image pull delays
      - Backoff after failures

2. Running
  - The container is executing.

3. Terminated
  - The container has stopped, either:
    - Normally (exit code 0)
    - With failure (non-zero exit)
    - Or was killed (e.g., due to resource limits or kubectl delete pod)

### 🔁 Pod Lifecycle
1. Pending  
    - The Pod is accepted by the Kubernetes system but not yet scheduled to a node or still pulling images.
    - Includes:
        Scheduling
        Image pulling
        Waiting for volume attachments

2. Running
    - The Pod has been bound to a node.
    - All containers are created and at least one is in a Running or Started state.

3. Succeeded
    - All containers in the Pod have completed successfully (i.e., exited with status 0) and won’t be restarted.\

4. Failed
    - All containers have terminated, and at least one has failed (non-zero exit code or was killed).

5. Unknown 
    - The Pod’s state can’t be determined. Usually happens when communication with the node is lost.

### ⚙️ Pod Lifecycle Events
  - PodScheduled – Pod is assigned to a node.
  - Pulling – Images are being pulled.
  - Created – Containers are created.
  - Started – Containers are running.
  - Killing – Pod is being terminated.




### 🔁 Restart Policies
The behavior when containers exit depends on the Pod's restartPolicy:
  - Always (default for Deployments): Restart container no matter what.
  - OnFailure: Restart only if exit code ≠ 0.
  - Never: Never restart.


---

## ReplicaSets & Scaling Applications 📊

### What is a ReplicaSet? 🔄
A **ReplicaSet (RS)** ensures that a specified number of Pod replicas are running at any time.

🔹 Used for **high availability & fault tolerance**.

🔹 Automatically replaces failed pods.

### ReplicaSet Example
```yaml
apiVersion: apps/v1
kind: ReplicaSet
metadata:
  name: my-replicaset
spec:
  replicas: 3
  selector:
    matchLabels:
      app: my-app
  template:
    metadata:
      labels:
        app: my-app
    spec:
      containers:
      - name: my-container
        image: nginx
```

🔹 Deploy using:
```sh
kubectl apply -f replicaset.yaml
```
🔹 Scale up/down:
```sh
kubectl scale --replicas=5 rs/my-replicaset
```

---

## Deployments: Rolling Updates & Rollbacks 🔄

### What is a Deployment? 🚀
A **Deployment** manages ReplicaSets and provides declarative updates to applications.

🔹 Enables **rolling updates** without downtime.

🔹 Supports **rollbacks** in case of failure.

🔹 Ensures controlled deployment of new versions.

### Deployment Example
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-deployment
spec:
  replicas: 3
  selector:
    matchLabels:
      app: my-app
  template:
    metadata:
      labels:
        app: my-app
    spec:
      containers:
      - name: my-container
        image: nginx:1.17
```

🔹 Deploy using:
```sh
kubectl apply -f deployment.yaml
```

### Rolling Update Deployment 🚀
```sh
kubectl set image deployment/my-deployment my-container=nginx:1.18
```

### Rollback to Previous Version 🔄
```sh
kubectl rollout undo deployment/my-deployment
```

---

## Namespaces for Multi-Tenancy 🌐

### What are Namespaces? 🏢
**Namespaces** allow separation of Kubernetes resources within the same cluster.

🔹 Useful for **multi-tenancy** (e.g., dev, staging, production).

🔹 Prevents conflicts by organizing resources.

### List Existing Namespaces
```sh
kubectl get namespaces
```

### Create a New Namespace
```sh
kubectl create namespace dev-environment
```

### Deploy a Pod in a Namespace
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-pod
  namespace: dev-environment
spec:
  containers:
  - name: my-container
    image: nginx
```

🔹 Delete a Namespace:
```sh
kubectl delete namespace dev-environment
```

---

## Labels & Selectors for Object Management 🏷️

### What are Labels? 🔖
Labels are key-value pairs used to **organize and filter** Kubernetes objects.

🔹 Used by **Selectors** to target specific resources.

🔹 Helps in **grouping related objects** (e.g., all frontend pods).

### Label Example
```yaml
metadata:
  labels:
    app: frontend
    env: production
```

### Select Pods by Label
```sh
kubectl get pods -l app=frontend
```

---

## Running & Managing Stateful Applications 💾

### What are Stateful Applications? 🔁
Applications that **maintain state/data** across Pod restarts (e.g., Databases, Message Queues).

🔹 Managed using **StatefulSets** instead of Deployments.

🔹 Ensures **stable network identity & persistent storage**.

### StatefulSet Example
```yaml
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: mysql-statefulset
spec:
  serviceName: "mysql"
  replicas: 2
  selector:
    matchLabels:
      app: mysql
  template:
    metadata:
      labels:
        app: mysql
    spec:
      containers:
      - name: mysql
        image: mysql:5.7
        env:
        - name: MYSQL_ROOT_PASSWORD
          value: "password"
```

🔹 Deploy using:
```sh
kubectl apply -f statefulset.yaml
```

🔹 How to Access ?
- Create Service of NodePort Type 
- Needed mysql client, if not install with following command
```sh
sudo apt update && sudo apt install mysql-client -y
```
- Get the minikude ip using
```bash
kubectl get nodes -o wide
  or
minikube ip 
```
- Connect to MYSQL DB (which is running inside pod (inside a container as a docker image))
```bash
mysql -h $(minikube ip) -P 30864 -u root -p
```
---

# Kubernetes (K8s) Terminologies Explained 🚀

## 1️⃣ Cluster 🏢
A **cluster** is like a university campus. It consists of multiple **buildings (nodes)** where different activities happen. Each building has classrooms (pods) where students (containers) study.

🔹 **Real-world Example:**  
A **university campus** where students are assigned to different classrooms based on subjects.

---

## 2️⃣ Node 🖥️
A **node** is a physical or virtual machine that runs applications. It is like a **building** in a university where different subjects are taught.

🔹 **Real-world Example:**  
A **college building** where multiple classrooms exist.

---

## 3️⃣ Pod 📦
A **Pod** is the smallest unit in Kubernetes. It contains one or more containers (applications) that share resources like network and storage.

🔹 **Real-world Example:**  
A **classroom** where students sit together and share resources like whiteboards, projectors, and desks.

---

## 4️⃣ Container 🏗️
A **container** is like a student inside a classroom (pod). Each student is assigned a task (running an app), and multiple students can be in the same classroom.

🔹 **Real-world Example:**  
Each **student** in a classroom represents an application (container) inside a pod.

---

## 5️⃣ Deployment 🚀
A **Deployment** helps manage and update applications automatically without downtime.

🔹 **Real-world Example:**  
When a **new syllabus** is introduced in a university, instead of stopping classes completely, it is rolled out gradually.

---

## 6️⃣ ReplicaSet 🔄
A **ReplicaSet** ensures that a specific number of copies of a pod are always running.

🔹 **Real-world Example:**  
A university ensures that **multiple classrooms** are available for a popular subject so that all students can attend lectures.

---

## 7️⃣ Service 🌐
A **Service** helps in communication between different pods and exposes applications to the outside world.

🔹 **Real-world Example:**  
A **help desk in a university** where students can ask for information or services.

---

## 8️⃣ Ingress 🚦
An **Ingress** is like a traffic controller that manages external access to applications inside the cluster.

🔹 **Real-world Example:**  
A **security checkpoint** at the university gate that decides who can enter.

---

## 9️⃣ Namespace 🏷️
A **Namespace** is like different departments in a university (Engineering, Medical, Arts) that help manage resources separately.

🔹 **Real-world Example:**  
A university has separate **departments** for different courses, ensuring that resources are used efficiently.

---

## 🔟 ConfigMap & Secret 🔑
🔹 **ConfigMap** stores general configuration data (like college notices).  
🔹 **Secret** stores sensitive information (like student passwords).  

🔹 **Real-world Example:**  
- **ConfigMap** → A public notice board 📜 (anyone can read).  
- **Secret** → A student’s **locker password** 🔐 (only the owner can access).  

---

## 1️⃣1️⃣ Persistent Volume (PV) 💾
A **Persistent Volume** is like a **library** where books (data) are stored permanently even if students (pods) leave.

🔹 **Real-world Example:**  
A university **library** where books remain available even if students graduate.

---

## 1️⃣2️⃣ StatefulSet 🏛️
A **StatefulSet** is used for applications that need a fixed identity and storage, like databases.

🔹 **Real-world Example:**  
A **student ID system** where every student has a unique number that remains the same throughout their university journey.

---

## 1️⃣3️⃣ Horizontal Pod Autoscaler (HPA) 📈
The **HPA** automatically scales the number of pods based on demand.

🔹 **Real-world Example:**  
If a university notices **more students enrolling**, they add **more classrooms** dynamically.

---

🎯 **Happy Learning!** 🚀
