# Kubernetes Training - Chapter 3 🚀

## Kubernetes Networking & Services 🌐

---

## 1️⃣ Kubernetes Networking Architecture 🏗️

Kubernetes networking is built on top of Linux networking principles. It ensures **seamless communication** between Pods, Nodes, and external clients using a **flat network model**.

### **Key Components of Kubernetes Networking Architecture:**
1. **Container Network Interface (CNI)** - Responsible for assigning IPs to Pods and setting up network routes.
2. **Kube-Proxy** - Manages service networking by configuring iptables or IPVS rules.
3. **CoreDNS** - Handles service discovery using DNS within the cluster.
4. **Ingress Controller** - Routes external traffic to internal services.
5. **Network Policies** - Enforce security rules on how Pods communicate.

![Kubernetes Networking](https://kubernetes.io/images/docs/container-networking.svg)

---

## 2️⃣ Linux Networking in Kubernetes 🐧

### **How Kubernetes Uses Linux Networking?**
Kubernetes relies heavily on Linux networking fundamentals:
- **Network Namespaces:** Each Pod runs in its own network namespace.
- **Virtual Ethernet Pairs (veth):** Connect Pods to the Node’s network bridge.
- **Bridge Networks (cbr0/docker0):** Manage communication between containers on the same node.
- **iptables & IPVS:** Used by Kube-Proxy to handle service networking.
- **Routing Tables:** Define how traffic flows between Pods across nodes.
- **CNI Plugins:** Implement network policies and manage Pod networking.

### **Example: How a Pod Communicates?**
1. A Pod gets an **IP address** from the CNI plugin.
2. **veth pairs** connect Pods to the Node’s bridge network.
3. Traffic between Pods on different nodes flows through the **CNI overlay network**.
4. **iptables/IPVS** routes requests to the correct Pod based on service rules.

### **Common Linux Networking Tools in Kubernetes:**
- `ip link show` - List network interfaces.
- `ip addr show` - Show IP addresses assigned to interfaces.
- `iptables -L -t nat` - View Kubernetes service NAT rules.
- `tc qdisc show` - Check traffic shaping rules.
- `brctl show` - Show bridge network details.

---

## 3️⃣ Networking Flow  🌐

### **How Networking Works in Kubernetes?**

### 1. **Pod-to-Pod Communication**

- Each pod gets a unique IP address.
- Pods communicate directly over the network.
- Kubernetes relies on **Container Network Interface (CNI)** plugins like Flannel, Calico, or Weave to implement the network.

> 📌 Example: Pod A on Node 1 can ping Pod B on Node 2 using its IP without NAT.

---

### 2. **Pod-to-Service Communication**

- Services are stable endpoints for pods.
- Kubernetes assigns a **virtual IP (ClusterIP)** to each Service.
- Traffic is routed to healthy pods behind the service using **kube-proxy**.

> 📌 Example: A front-end pod communicates with a back-end service using `http://backend-service:5000`.

---

### 3. **External-to-Service Communication**

There are two primary ways to expose services to the outside world:

- **NodePort**: Exposes service on a static port on each node.
- **LoadBalancer**: Provisions an external load balancer (in cloud setups).
- **Ingress**: A smarter HTTP(S) router with URL/path-based routing.

### **Networking Components in Kubernetes:**
- **CNI (Container Network Interface):** Manages pod networking.
- **Kube-Proxy:** Handles network rules for routing traffic.
- **CoreDNS:** Provides DNS resolution inside the cluster.
- **Ingress Controller:** Manages external HTTP/HTTPS access.

---

## 🧩 Types of Networking in Kubernetes

| Type | Description | Real-World Example |
|------|-------------|--------------------|
| **Container-to-Container** | Containers in the same pod share a network namespace | `localhost:8080` inside a pod |
| **Pod-to-Pod** | Direct IP communication between pods | Microservices talking to each other |
| **Pod-to-Service** | Access pods via service abstraction | `http://auth-service` |
| **External-to-Service** | Users accessing your app via NodePort, LoadBalancer, or Ingress | Browsing a web app via public IP |
| **Cluster Networking (CNI)** | Implementation of network policies and routing | Calico for security and routing |

---


## 🔁 Real-World Examples

### 🎯 Example 1: E-commerce Microservices

- **Frontend Pod** → communicates with → **Product Service (ClusterIP)**
- **Product Service** → talks to → **Database Pod**
- **Users** access the system via **Ingress** (e.g., `/products`, `/cart`)

---

### 🎯 Example 2: Dev Environment on Minikube

- Expose app via:
  ```bash
  minikube service my-app-service
  ```

- Uses **NodePort** behind the scenes to expose your app in browser.

---


## ⚙️ Kubernetes Networking Components

---

### 🔌 CNI (Container Network Interface)

- CNI is a **standard interface** for configuring network interfaces in Linux containers. 
- It allows Kubernetes to **plug in different networking solutions** (like Calico, Flannel, Weave, etc.).

### ✅ Role in Kubernetes:
- Assigns **IP addresses** to pods.
- Connects pods to the cluster network.
- Applies network policies (if supported).

### 🔍 Example:
When a pod is created, the CNI plugin (e.g., Calico) gives it an IP address and ensures it can talk to other pods.

> 🛠️ Think of it like the "network adapter" for pods.

---

### 🔀 Kube-Proxy

- Kube-Proxy is a **Kubernetes network component** that runs on each node and **manages networking rules**.

### ✅ Role in Kubernetes:
- Handles **routing traffic** to the correct pod.
- Supports **ClusterIP**, **NodePort**, and **LoadBalancer** types.
- Works using **iptables**, **IPVS**, or **eBPF** to forward traffic.

### 🔍 Example:
When you hit a Kubernetes service like `http://my-service:80`, kube-proxy figures out which pod to forward your request to.

> 🔁 It's like a mini load balancer running on every node.

---

### 🌐 CoreDNS

- CoreDNS is the **DNS server** used in Kubernetes for **service discovery**.

### ✅ Role in Kubernetes:
- Resolves service names like `auth-service.default.svc.cluster.local` to cluster IPs.
- Allows pods to use **friendly DNS names** instead of raw IPs.

### 🔍 Example:
Pod A can call Pod B using `http://user-service` instead of an IP address. CoreDNS makes that resolution possible.

> 📞 It’s the phonebook of your Kubernetes cluster.

---

### 🌍 Ingress Controller

- Ingress Controller is a **Kubernetes component** that manages **external access** (usually HTTP/HTTPS) to services inside the cluster.

### ✅ Role in Kubernetes:
- Handles **routing rules** defined in Ingress resources.
- Provides features like **TLS termination**, **path-based routing**, and **host-based routing**.
- Works with tools like **NGINX**, **Traefik**, or **HAProxy**.

### 🔍 Example:
You define an Ingress rule to route `/api` to the `backend-service` and `/ui` to the `frontend-service`.

> 🌐 It acts like a **gateway** or **reverse proxy** for your cluster.


---

## 🛠 Tools and CNI Plugins

| Plugin | Features |
|--------|----------|
| **Calico** | High performance, policy support |
| **Flannel** | Simple overlay network |
| **Weave** | Automatic peer discovery |
| **Cilium** | eBPF-based security and observability |

---


## 5️⃣ Service Types in Kubernetes 🏷️

- Services enable communication between different components inside a cluster.
- A Service is a stable endpoint (cluster IP, DNS) for a set of pods.
Types:
  - ClusterIP: Default, internal only.
  - NodePort: Exposes service on <NodeIP>:<NodePort>.
  - LoadBalancer: Provisions external LB (cloud-native).
  - ExternalName: Maps service to DNS name.

✅ Why We Need Services

🧠 Imagine This:
  - Pods are ephemeral — they can come and go anytime (like when they crash or are rescheduled).
  - When a pod restarts, it gets a new IP address.  
  - You can’t rely on pod IPs to talk to your app components.

**1. Stable Network Identity**
- A Service gives your pods a consistent IP and DNS name.
- So instead of connecting to 10.244.0.13, you can connect to my-service.default.svc.cluster.local.

**2. Load Balancing**
- A Service automatically load-balances traffic across all healthy pods in a deployment or StatefulSet.

**3.Discovery**
- Other apps in the cluster can find and communicate with your service using DNS, e.g. mysql-service.default.svc.cluster.local.

**4. Internal vs External Access**
- You can choose how your app is exposed:
    - Internally: ClusterIP
    - To outside world: NodePort, LoadBalancer, or via Ingress

**5. Loose Coupling**
- Clients connect to a service — not the pods directly.
- So if you scale pods up/down or replace them, clients don’t need to care.


### **1. ClusterIP (Default) 🏠**
- Exposes service **internally** within the cluster.
- Cannot be accessed from outside the cluster.

```yaml
apiVersion: v1
kind: Service
metadata:
  name: clusterip-service
spec:
  selector:
    app: my-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: ClusterIP
```

### **2. NodePort 🌍**
- Exposes service on a **static port** on each node.
- Accessible from **outside** the cluster via `NodeIP:NodePort`.

```yaml
apiVersion: v1
kind: Service
metadata:
  name: nodeport-service
spec:
  selector:
    app: my-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
      nodePort: 30007
  type: NodePort
```

### **3. LoadBalancer 🌐**
- Provision an external load balancer (via your cloud provider, like AWS, GCP, Azure).
- Assign a public IP address to the Service.
- Automatically configure the load balancer to forward external traffic to the backend Pods.

**This allows users outside your cluster to access your application via a single, stable IP or hostname.** 


```yaml
apiVersion: v1
kind: Service
metadata:
  name: loadbalancer-service
spec:
  selector:
    app: my-app
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: LoadBalancer
```
---

### 💡 Why it doesn't work out of the box in Minikube

- Minikube is a local Kubernetes cluster meant for development and testing. 
- It doesn’t automatically provision cloud load balancers, because there’s no cloud provider backing it (like AWS or GCP).

- So if you define a Service with type: LoadBalancer, it won’t get an external IP right away — you'll see like below
```bash
kubectl get svc

NAME              TYPE           CLUSTER-IP     EXTERNAL-IP   PORT(S)        AGE
nginx-lb-service     LoadBalancer   10.96.0.1      <pending>     80:31042/TCP   1m

```

### ✅ How to make it work in Minikube
- Minikube has a built-in workaround for this using its minikube tunnel feature.
1. Start a tunnel in a separate terminal:
```bash
minikube tunnel # This command creates a network route and runs a process that mimics a cloud load balancer.
```
2. Create your LoadBalancer service (or apply your YAML).
3. You can now access your service at http://<external-ip>:<port>

### 


## 🔥 Main Observations
✅ Kubernetes **networking is flat** – Pods can communicate without NAT.  
✅ **Services** abstract Pod IPs, making communication easier.  
✅ **Ingress Controllers** are essential for managing HTTP traffic externally.  
✅ **Linux networking concepts** like iptables, namespaces, and bridges are used heavily.

---

## 🚨 Common Challenges & Issues
❌ **Pods losing connectivity** → Check CNI plugin configuration.  
❌ **Service not accessible** → Ensure correct **selector labels** and port mappings.  
❌ **Ingress not working** → Verify **Ingress Controller is deployed** and DNS is resolving correctly.  
❌ **ExternalDNS updates failing** → Check **permissions for cloud provider APIs**.  
❌ **iptables rules incorrect** → Debug with `iptables -L -t nat`.

---

🎯 **Happy Learning!** 🚀

