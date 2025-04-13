# Kubernetes - Chapter 1 🚀

## Introduction to Kubernetes

### What is Kubernetes? 🏗️
Kubernetes (K8s) is an open-source container orchestration platform designed to automate deploying, scaling, and operating application containers across clusters of machines.

🔹 Developed by Google, now maintained by CNCF (Cloud Native Computing Foundation).

🔹 Enables containerized applications to run in different environments (on-premise, cloud, or hybrid).

🔹 Supports self-healing, automatic scaling, load balancing, and declarative configurations.

![Kubernetes Logo](https://kubernetes.io/_common-resources/images/flower.svg)

---

## Benefits of Kubernetes 🌟
✅ Automates application deployment and scaling.

✅ Efficient resource utilization with optimized scheduling.

✅ Provides self-healing (restarts failed containers, replaces unresponsive nodes).

✅ Load balancing across containers for high availability.

✅ Manages storage and networking seamlessly.

✅ Works across multiple cloud providers.

---

## Kubernetes vs Traditional Deployments 🆚
| Feature              | Traditional Deployments | Kubernetes |
|---------------------|----------------------|------------|
| Deployment         | Manual setup & scripting | Automated, declarative YAML files |
| Scaling           | Manual intervention | Auto-scaling support |
| Load Balancing    | External tools required | Built-in support |
| Fault Tolerance   | Difficult to implement | Self-healing & automatic recovery |
| Resource Utilization | Less efficient | Optimized scheduling |

---

## Kubernetes vs Docker 🆚
| Feature      | Docker | Kubernetes |
|-------------|--------|------------|
| Purpose     | Container runtime | Orchestration & container management |
| Scaling     | Manual or Docker Swarm | Auto-scaling support |
| Networking  | Basic | Advanced networking & service discovery |
| Multi-host  | Limited | Supports multi-node clusters |

🔹 **Kubernetes & Docker Work Together!** Kubernetes uses Docker as a container runtime but adds automation, orchestration, and management.

---

## Kubernetes vs Docker Swarm 🆚
| Feature           | Kubernetes | Docker Swarm |
|------------------|------------|--------------|
| Complexity      | Higher | Simpler, easier to set up |
| Scalability    | Auto-scaling, advanced scheduling | Basic scaling features |
| Networking     | Advanced, service discovery | Simpler networking model |
| Load Balancing | Built-in | External tools required |
| Storage       | Persistent storage support | Basic volume management |

---

## Kubernetes Architecture 🏗️

K8s follows a **master-worker (control plane + data plane)** architecture:  

- **Control Plane (Master Node)** – Manages the cluster state and workload scheduling.  
- **Worker Nodes** – Run the actual containerized applications (Pods).  

![Kubernetes Architecture](https://d33wubrfki0l68.cloudfront.net/2475489eaf20163ec0f54ddc1d92aa8d4c87c96b/e7c81/images/docs/components-of-kubernetes.svg)  

---

## **1. Kubernetes Control Plane (Master Node) Components**

### **1. API Server (`kube-apiserver`)**
- The **front-end** of the control plane.  
- Exposes the Kubernetes API (RESTful interface).  
- Handles authentication, authorization, and validation of requests.  

### **2. `etcd`**
- A **distributed key-value store** that stores all cluster data (configurations, state).  
- Acts as the **"source of truth"** for the cluster.  

### **3. Scheduler (`kube-scheduler`)**
- Watches for newly created Pods and assigns them to **worker nodes** based on resource availability, constraints, and policies.  

### **4. Controller Manager (`kube-controller-manager`)**
- Runs **controllers** that regulate the state of the cluster:  
  - **Node Controller** – Monitors node status.  
  - **Replication Controller** – Ensures the correct number of Pod replicas.  
  - **Deployment Controller** – Manages rolling updates.  
  - **Endpoint Controller** – Updates Services & Pods mappings.  

### **5. Cloud Controller Manager (Optional)**
- Integrates with cloud provider APIs (AWS, GCP, Azure) for load balancers, storage, and networking.  

---

## **2. Worker Node Components**

### **1. Kubelet**
- The **primary agent** running on each worker node.  
- Ensures Pods are running as expected by communicating with the API Server.  

### **2. Kube Proxy**
- Maintains **network rules** on nodes.  
- Enables communication between Pods and Services (load balancing, IP forwarding).  

### **3. Container Runtime**
- Software responsible for running containers (e.g., **Docker, containerd, CRI-O**).  

### **4. Pods**
- Smallest deployable units in Kubernetes.  
- Can contain **one or more containers** sharing storage & network.  

---

## **3. Add-Ons (Optional Components)**

### **1. DNS (`CoreDNS` / `kube-dns`)**
- Provides DNS-based service discovery within the cluster.  

### **2. Dashboard (Web UI)**
- A graphical interface for managing the cluster.  

### **3. Ingress Controller**
- Manages external access to services (e.g., **Nginx, Traefik**).  

### **4. CNI (Container Network Interface) Plugins**
- Enables networking between Pods (e.g., **Calico, Flannel, Weave Net**).  

### **5. Metrics Server**
- Collects resource usage data (CPU, memory) for monitoring and scaling.  

---

## **4. How Components Work Together**
1. A user submits a **Pod definition** (YAML/JSON) to the **API Server**.  
2. The **Scheduler** assigns the Pod to a suitable **worker node**.  
3. The **Kubelet** on that node pulls the container image and runs the Pod.  
4. **Kube Proxy** sets up networking rules for the Pod.  
5. **Controllers** continuously monitor and adjust the cluster state.  

---

## **Conclusion**
- **Master Node (Control Plane)** → Manages cluster operations.  
- **Worker Nodes** → Run application workloads.  
- **Add-Ons** → Enhance functionality (networking, monitoring, etc.).  


![Kubernetes Architecture](https://kubernetes.io/images/docs/kubernetes-cluster-architecture.svg)

--- 
🏗️ The Architecture – Think of it Like a Warehouse Team:
1. **Master Node (Boss of the Operation)**

This is the brain of Kubernetes. It decides what needs to be done, and where.

It has 3 main parts:

    API Server – The reception desk: Takes your orders (commands) and passes them to the team.

    Scheduler – The dispatcher: Chooses which worker (computer) should handle which task (container).

    Controller Manager – The supervisor: Makes sure everything is running as expected.

    etcd – The filing cabinet: Stores all the important info and settings for the system.

2. **Worker Nodes (The Workers)**
These are the computers that actually run your applications.

Each worker has:

    Kubelet – The foreman: Talks to the boss (API Server) and runs the tasks (containers).

    Container Runtime – The machine: Actually runs the containers (like Docker).

    Kube Proxy – The traffic controller: Makes sure network communication works smoothly.

3. **Pods (The Tasks)**

A Pod is the smallest unit in Kubernetes. It holds one or more containers that work together. Think of it like a box with your tools or parts for a job.



---

## Installing Kubernetes 🏗️
### Minikube 🛠️
🔹 A lightweight Kubernetes implementation for local testing.
🔹 Uses a single-node cluster.

#### Installation Steps:
```sh
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube
minikube start
```

### K3s 🚀
🔹 Lightweight Kubernetes distribution designed for edge computing.

🔹 Uses a single binary and minimal resources.

#### Installation Steps:
```sh
curl -sfL https://get.k3s.io | sh -
sudo systemctl status k3s
```

### Kind 🏗️
🔹 Kubernetes IN Docker (Kind) for local testing using Docker containers.
🔹 Useful for CI/CD and testing.

#### Installation Steps:
```sh
go install sigs.k8s.io/kind@latest
kind create cluster
```

---

## Running Your First Kubernetes Cluster 🚀

### Step 1: Start Minikube
```sh
minikube start
```

### Step 2: Verify Cluster Status
```sh
kubectl cluster-info
kubectl get nodes
```

### Step 3: Deploy a Sample App
```sh
kubectl create deployment hello-k8s --image=k8s.gcr.io/echoserver:1.10
kubectl expose deployment hello-k8s --type=NodePort --port=8080
```

### Step 4: Get Service URL
```sh
minikube service hello-k8s --url
```


---

## Main Observations 📌

🔹 Kubernetes simplifies container orchestration.

🔹 Requires learning curve for YAML configurations & CLI commands.

🔹 Different distributions (Minikube, K3s, Kind) serve different use cases.

## Common Issues & Challenges ❗

⚠️ **Minikube startup issues** (e.g., virtualization not enabled, memory limits).

⚠️ **Networking problems** (firewall settings, port conflicts).

⚠️ **Permissions issues** (kubectl authentication failures).

⚠️ **Pods stuck in pending state** (due to resource constraints).

---


## ✅ Quick Summary of Lesson 1
In this lesson covered following:
- What Kubernetes is and why it's important.
- Key benefits compared to traditional deployments.
- Differences between Kubernetes vs Docker and Docker Swarm.
- Detailed explanation of K8s architecture: Control Plane, Worker Nodes, and Add-Ons.
- Install options for local K8s (Minikube, K3s, Kind).
- How to launch a simple app with kubectl.
- Common challenges learners may face.

## 🏠 Homework Exercises
1. Basic YAML Deployment
    Write a YAML manifest to deploy an Nginx web server as a Pod in Kubernetes.

2. Hands-On: Run Your First Pod
    Using Minikube or Kind, launch a simple app (e.g., nginx) and expose it using a Service. Try accessing it in your browser.

## 💼 Interview Questions
1. What is Kubernetes and why do we use it?
2. What are the main components of the Kubernetes Control Plane?
3. What is the difference between a Pod and a Container?
4. How does Kubernetes handle container failures?
5. Compare Kubernetes with Docker Swarm. When would you choose one over the other?
