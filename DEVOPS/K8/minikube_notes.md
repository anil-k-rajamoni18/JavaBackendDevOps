
# 🐳 Minikube - Kubernetes on Your Local Machine

## 🌟 What is Minikube?

**Minikube** is a lightweight tool that lets you run a **single-node Kubernetes cluster** on your **local machine**. It's ideal for:

- Learning Kubernetes
- Local development and testing
- Simulating real-world Kubernetes environments without cloud dependencies

---

## 🔧 Usage of Minikube

Minikube is commonly used for:

- **Developing and testing** Kubernetes applications locally
- Running **kubectl** commands against a real cluster
- Trying out Kubernetes features like Ingress, LoadBalancer, Volumes, etc.
- Practicing Kubernetes deployments before pushing to staging/production

---

## 🛠️ How to Set Up Minikube on Ubuntu (22.04 or 24.04)

### Step 1: Install Required Dependencies

```bash
sudo apt update
sudo apt install -y curl apt-transport-https virtualbox virtualbox-ext-pack
```

> 💡 Alternatively, you can use Docker as the driver instead of VirtualBox.

---

### Step 2: Install kubectl

```bash
curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
chmod +x kubectl
sudo mv kubectl /usr/local/bin/
```

---

### Step 3: Install Minikube

```bash
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube
```

---

### Step 4: Start the Cluster

```bash
minikube start --driver=docker
```

> You can also use `--driver=virtualbox`, `--driver=none`, or `--driver=kvm2`.

---

## 🧾 Popular Minikube Commands

| Command | Description |
|--------|-------------|
| `minikube start` | Start the Minikube cluster |
| `minikube stop` | Stop the running cluster |
| `minikube delete` | Delete the cluster |
| `minikube status` | Show status of the cluster |
| `minikube dashboard` | Launch the Kubernetes dashboard |
| `minikube ssh` | SSH into the Minikube node |
| `minikube service <svc-name>` | Open the URL of a service (NodePort/LoadBalancer) |
| `kubectl get pods` | View pods in the cluster (kubectl is linked to Minikube) |

---

## 🔍 How Minikube Differs from Other Kubernetes Tools

| Tool | Description | Key Differences |
|------|-------------|-----------------|
| **Minikube** | Local K8s cluster using VM/Container | Full K8s experience, multiple drivers |
| **kind** (Kubernetes in Docker) | Cluster inside Docker containers | Lighter, great for CI/CD |
| **k3s** | Lightweight Kubernetes distro | Ideal for edge/IoT, minimal setup |
| **MicroK8s** | Canonical’s K8s snap distro | Easy install, production-capable |
| **Docker Desktop** | GUI with K8s support | Great for GUI users, heavy on resources |

---

## ✅ Pros and ❌ Cons

### ✅ Pros

- Simple setup on Linux, macOS, and Windows
- Great for learning and experimenting
- Supports full K8s API and features (Ingress, Volumes, LoadBalancers)
- Flexible VM or Docker drivers
- CLI tools (`minikube`, `kubectl`) are easy to use

---

### ❌ Cons

- Not suitable for production use
- Slower startup than **kind**
- Requires system virtualization or Docker
- Resource usage can be high on low-spec machines

---

## 📎 Bonus Tips

- To change Kubernetes versions:
  ```bash
  minikube start --kubernetes-version=v1.27.0
  ```

- Enable Addons (like Ingress or Metrics Server):
  ```bash
  minikube addons enable ingress
  minikube addons enable metrics-server
  ```


# 📘 Kubernetes (K8s) Command Cheat Sheet

### 🔍 Cluster Info & Context
```bash
kubectl cluster-info                     # Show cluster info
kubectl config get-contexts              # List all contexts
kubectl config use-context CONTEXT_NAME # Switch context
kubectl get nodes                        # List all nodes
```

### 🧱 Pods
```bash
kubectl get pods                         # List all pods
kubectl get pods -n NAMESPACE           # Pods in a specific namespace
kubectl describe pod POD_NAME           # Detailed pod info
kubectl logs POD_NAME                   # View logs
kubectl exec -it POD_NAME -- bash       # Shell into a pod (if bash is available)
kubectl delete pod POD_NAME             # Delete a pod
```

### 🚀 Deployments
```bash
kubectl get deployments                      # List deployments
kubectl create deployment NAME --image=IMAGE_NAME       # Create a deployment
kubectl scale deployment NAME --replicas=3              # Scale deployment
kubectl rollout restart deployment NAME                 # Restart deployment
kubectl describe deployment DEPLOYMENT_NAME             # Detailed deployment info
kubectl delete deployment NAME                          # Delete deployment
```
### 🔄 Services
```bash
kubectl get svc                                           # List services
kubectl expose deployment NAME --port=80 --target-port=8080 --type=NodePort  # Expose deployment
kubectl describe svc SERVICE_NAME                         # Get service details
kubectl delete svc SERVICE_NAME                           # Delete a service
```

### 📁 YAML Config Files
```bash
kubectl apply -f file.yaml       # Apply resources from file
kubectl delete -f file.yaml      # Delete resources from file
kubectl get all -o yaml          # Output all resources in YAML
kubectl diff -f file.yaml        # Show changes before applying
```

### 📦 Namespaces
```bash
kubectl get namespaces           # List all namespaces
kubectl create namespace NAME    # Create a new namespace
kubectl delete namespace NAME    # Delete a namespace
```

### ⚙️ Useful Flags & Tricks
```bash
kubectl get pods -o wide                 # Show more pod details (IP, node, etc.)
kubectl get all                          # List all resources in current namespace
kubectl get events                       # Show recent cluster events
kubectl explain pod                      # Get documentation for a resource
```

### 🧹 Clean Up
```bash
kubectl delete pod POD_NAME
kubectl delete service SERVICE_NAME
kubectl delete deployment DEPLOYMENT_NAME
```

## 📘 References

- [Minikube Official Docs](https://minikube.sigs.k8s.io/)
- [Kubernetes Documentation](https://kubernetes.io/docs/home/)
