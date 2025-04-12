
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
curl -LO "https://dl.k8s.io/release/$(curl -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
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

---

## 📘 References

- [Minikube Official Docs](https://minikube.sigs.k8s.io/)
- [Kubernetes Documentation](https://kubernetes.io/docs/home/)
