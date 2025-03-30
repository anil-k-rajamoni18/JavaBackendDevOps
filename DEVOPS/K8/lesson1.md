# Kubernetes - Day 1 🚀

## Introduction to Kubernetes

### What is Kubernetes? 🏗️
Kubernetes (K8s) is an open-source container orchestration platform designed to automate deploying, scaling, and operating application containers across clusters of machines.

🔹 Developed by Google, now maintained by CNCF (Cloud Native Computing Foundation).
🔹 Enables containerized applications to run in different environments (on-premise, cloud, or hybrid).
🔹 Supports self-healing, automatic scaling, load balancing, and declarative configurations.

![Kubernetes Logo](https://upload.wikimedia.org/wikipedia/commons/3/39/Kubernetes_logo_without_workmark.svg)

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
### Master Node Components 🖥️
- **API Server**: Exposes the Kubernetes API for communication.
- **Controller Manager**: Handles node management, replication, and endpoint controllers.
- **Scheduler**: Assigns workloads (Pods) to worker nodes based on resource availability.

### Worker Node Components ⚙️
- **Kubelet**: Manages pods on a node.
- **Kube Proxy**: Manages networking and load balancing.
- **Container Runtime**: Runs the containers (e.g., Docker, containerd).

![Kubernetes Architecture](https://miro.medium.com/max/1400/1*RqN8kx5MSXKwWBnt9jc-wg.png)

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
