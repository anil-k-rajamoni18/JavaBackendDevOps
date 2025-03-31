# Kubernetes Training - Day 3 🚀

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
1. **Pod-to-Pod Communication:**
   - Each Pod gets an IP from the network.
   - Uses **CNI (Container Network Interface) plugins** like Calico, Flannel, or Cilium.
   - Pods communicate within the same cluster over a **flat network**.

2. **Pod-to-Service Communication:**
   - Services provide a **stable DNS name**.
   - The **kube-proxy** component routes traffic to the appropriate Pod.

3. **External Access to Services:**
   - Achieved using **NodePort, LoadBalancer, or Ingress Controllers**.
   - Load balancers (cloud or external) distribute traffic.

### **Networking Components in Kubernetes:**
- **CNI (Container Network Interface):** Manages pod networking.
- **Kube-Proxy:** Handles network rules for routing traffic.
- **CoreDNS:** Provides DNS resolution inside the cluster.
- **Ingress Controller:** Manages external HTTP/HTTPS access.

---

## 4️⃣ Key Networking Components 🔌

### **Pod-to-Pod Communication 🔄**
- Each Pod has a unique **IP address**.
- Pods communicate **directly** over this IP, using **Kubernetes networking plugins** (CNI: Calico, Flannel, Cilium).
- **Example:** A backend Pod communicates with a database Pod using its IP.

### **Pod-to-Service Communication 🖧**
- Services provide a **stable DNS name** for Pods.
- Instead of using Pod IPs (which change), applications use the **Service name** (`my-service.default.svc.cluster.local`).
- **Example:** A frontend Pod calls `http://backend-service:8080` instead of a backend Pod IP.

### **External Access to Services 🌍**
- External users access applications using **Ingress, NodePort, or LoadBalancer**.
- **Example:** A public website served via an **Ingress Controller** with a domain name.

---

## 5️⃣ Service Types in Kubernetes 🏷️

Services enable communication between different components inside a cluster.

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
- Uses **cloud provider’s load balancer** (AWS, GCP, Azure, etc.) to expose service externally.
- Automatically assigns an external **IP address**.

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

