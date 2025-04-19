
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

## 🔁 kubectl port-forward — What, Why & How
- kubectl port-forward allows you to forward a port from your local machine to a pod or service inside the Kubernetes cluster.

### ✅ Why use it:
- Quick & simple way to test/debug a service
- Works regardless of your cluster's network setup (e.g., Docker, Minikube, cloud)
- No need to expose the service externally (like with NodePort or LoadBalancer)
- Great for local development & debugging

### ✅ How it works:
**For a service/pod:**

```bash
kubectl port-forward service/<service-name> <local-port>:<target-port>

kubectl port-forward pod/<pod-name> <local-port>:<container-port>
```

---
## 🌐 What is `minikube service`?

👉 `minikube service` is a CLI command that helps you access a Kubernetes **Service** from your local machine when using Minikube.

*minikube service opens a service in your browser (or prints the URL) by creating a temporary tunnel to the Kubernetes NodePort service from your local machine.*

---

### ✅ Usage:
```bash
minikube service <service-name>
```

---

### 🔍 What it does:
- Looks up the **Service** you specify.
- Detects its **NodePort** or **LoadBalancer** settings.
- Opens your **browser to the correct URL** (like `http://<minikube-ip>:<port>`).
- It’s a super convenient shortcut — no need to find IPs or ports manually.

---

### 🧪 Example:

Let’s say you have a service like this:

```yaml
apiVersion: v1
kind: Service
metadata:
  name: my-app
spec:
  type: NodePort
  selector:
    app: my-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
```

To access it, just run:

```bash
minikube service my-app
```

You'll get something like:

```
🏃  Starting tunnel for service my-app.
|-----------|--------|-------------|------------------------|
| NAMESPACE |  NAME  | TARGET PORT |          URL           |
|-----------|--------|-------------|------------------------|
| default   | my-app |        8080 | http://192.168.49.2:30337 |
|-----------|--------|-------------|------------------------|
🎉  Opening service default/my-app in default browser...
```

---

**If you just want the access URLs without launching the browser or blocking the terminal, run:**
```bash
minikube service service-1 --url
minikube service service-2 --url
```

#### ⚖️ Difference Between `kubectl port-forward` and `minikube service`

| Feature                            | `kubectl port-forward`                              | `minikube service`                                |
|------------------------------------|-----------------------------------------------------|---------------------------------------------------|
| **Access type**                    | Manual forwarding to pod/service                    | Automatic tunnel to NodePort service              |
| **Needs exposed service (NodePort)?** | ❌ No                                              | ✅ Yes                                            |
| **Works with any cluster?**        | ✅ Yes (Minikube, GKE, EKS, etc.)                   | ❌ Only Minikube                                  |
| **Persistent access**              | ❌ No (stops when terminal closes)                  | ❌ No (also tunnel stops with terminal)           |
| **Browser launch**                 | ❌ No                                               | ✅ Yes                                            |
| **Use case**                       | Dev/test/debug (fine-grained control)               | Quick access in Minikube                          |


--- 

## 🔌 What is `minikube tunnel`?

👉 `minikube tunnel` simulates a **cloud load balancer** for `LoadBalancer` services on your local Minikube cluster.

Since Minikube doesn’t have a cloud provider (like AWS/GCP) to provision load balancers, this command creates a local workaround.

---

### ✅ Usage:
```bash
minikube tunnel
```

---

### 🔍 What it does:
- Creates a **network route** to expose `LoadBalancer` services on your **local network**.
- Assigns a **real external IP** to those services.
- Runs in a **separate terminal** and may need **admin privileges**.

---

### 💡 Typical Use Case

You create a service like this:

```yaml
spec:
  type: LoadBalancer
```

Then you run:

```bash
minikube tunnel
```

After that:

```bash
kubectl get svc
```

You'll now see an external IP assigned:

```
NAME         TYPE           CLUSTER-IP       EXTERNAL-IP     PORT(S)        AGE
my-service   LoadBalancer   10.109.170.197   192.168.49.2    80:30303/TCP   2m
```

Boom — your `LoadBalancer` now works **locally!** 🎉

---

## 🤔 So When to Use What?

| Use Case                      | Command to Use            |
|------------------------------|---------------------------|
| Access a NodePort service    | `minikube service <name>` |
| Make LoadBalancer work       | `minikube tunnel`         |
| Just test a service quickly  | `kubectl port-forward`    |


## 📘 References

- [Minikube Official Docs](https://minikube.sigs.k8s.io/)
- [Kubernetes Documentation](https://kubernetes.io/docs/home/)
