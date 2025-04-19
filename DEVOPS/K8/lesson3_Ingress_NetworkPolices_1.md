
# 🚪 Ingress and 🛡️ Network Policies in Kubernetes

---

## 🚪 Ingress in Kubernetes

### 🔍 What is Ingress?

**Ingress** is an API object in Kubernetes that **manages external access** to services, typically HTTP and HTTPS. It provides **routing rules**, **TLS termination**, and **virtual hosting**.

Instead of exposing each service with a `NodePort` or `LoadBalancer`, you define one Ingress controller that handles traffic based on **paths or hostnames**.

---
## ✅ Why We Need Ingress (Even If We Have Services)

### 1. Multiple Routes, One Entry Point
- **Without Ingress**: You need a separate `LoadBalancer` or `NodePort` per service (expensive, hard to manage).
- **With Ingress**: You can route multiple URLs or domains through **one IP** (e.g., `/api`, `/app`, or `app1.example.com`, `app2.example.com`).

---

### 2. Smart Routing
Ingress lets you define **rules** like:
- Route `example.com/api` to service A  
- Route `example.com/blog` to service B  
- Route based on headers, paths, etc.

---

### 3. TLS/HTTPS Termination
- Ingress controllers can handle **SSL certificates** and terminate HTTPS traffic.
- Without Ingress, you'd have to configure TLS on **every individual service**.

---

### 4. Authentication, Rate Limiting, Caching, etc.
Many Ingress controllers (like **NGINX** or **Traefik**) support **middleware features** like:
- Basic auth  
- Rate limits  
- Logging  
- Caching

---

### 5. Cleaner Setup
- Reduces cloud resource usage (fewer `LoadBalancers`).
- Easier to maintain and scale.

---

## 🆚 Quick Comparison

| Feature                         | Service (`NodePort`, `LoadBalancer`) | Ingress                     |
|---------------------------------|--------------------------------------|-----------------------------|
| Exposes app                     | ✅                                   | ✅                          |
| Smart routing (path/domain)     | ❌                                   | ✅                          |
| TLS termination                 | ❌ (manual per service)              | ✅                          |
| One IP for many services        | ❌                                   | ✅                          |
| Middleware features             | ❌                                   | ✅ (via controller)         |

---

## 📦 TL;DR

- Use **Service** to make your app **reachable**.  
- Use **Ingress** to make that reachability **smart, scalable, and secure**.
---

---


### 🔧 How Ingress Works

1. Users make HTTP/HTTPS requests from the internet.
2. Ingress Controller receives the request.
3. It matches the request against **Ingress rules**.
4. Routes the request to the appropriate **service** inside the cluster.

---

### 🛠️ Ingress Controller

- Ingress needs a controller to actually process traffic.
- Popular controllers:
  - **NGINX Ingress Controller**
  - **Traefik**
  - **HAProxy**
  - **Istio Gateway** (for service mesh)

---

### 📘 Basic Ingress YAML Example

```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: web-ingress
spec:
  rules:
  - host: myapp.example.com
    http:
      paths:
      - path: /api
        pathType: Prefix
        backend:
          service:
            name: backend-service
            port:
              number: 80
      - path: /ui
        pathType: Prefix
        backend:
          service:
            name: frontend-service
            port:
              number: 80
```

---

### ✅ Ingress Features

- URL-based routing (`/api`, `/login`)
- Host-based routing (`app.example.com`, `blog.example.com`)
- TLS/HTTPS termination
- Central point for authentication and logging

---

### 🔍 Real-World Use Case

You host multiple apps behind a single domain:

```
https://mycompany.com/app1 → frontend-service
https://mycompany.com/api  → backend-service
```

Using Ingress, you manage all traffic through a **single load balancer** and **Ingress rules**.

---

## 🛡️ Network Policies in Kubernetes

### 🔍 What is a Network Policy?

**Network Policies** are Kubernetes resources that define **how pods are allowed to communicate** with each other and with external endpoints.

Think of it like a **firewall for your pods**.

---

### 🧱 Default Behavior

By default, **all pods can talk to all other pods**.

After applying a NetworkPolicy, **only the allowed traffic is permitted**. All other communication is **denied by default**.

---

### 🛠️ Example1: Allow Only Frontend to Talk to Backend

```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-frontend
spec:
  podSelector:
    matchLabels:
      role: backend
  ingress:
  - from:
    - podSelector:
        matchLabels:
          role: frontend
```

- Only pods with label `role=frontend` can talk to pods with `role=backend`.
- All other pods are **blocked**.

---

### ✅ Example 2: Limit Access to Database (Ingress)
**Scenario: You want only your backend pod to access your PostgreSQL database.**
```yaml
spec:
  podSelector:
    matchLabels:
      app: postgres
  policyTypes:
  - Ingress
  ingress:
  - from:
    - podSelector:
        matchLabels:
          app: backend
```
- 🔒 Blocks all other pods (like frontend, jobs, etc.) from accessing the database.

---
### ✅ Example 3: Restrict Pod from Accessing Internet (Egress)
**Scenario: You have a sensitive app that shouldn't talk to the internet — only internal services.**
```yaml
spec:
  podSelector:
    matchLabels:
      app: secure-app
  policyTypes:
  - Egress
  egress:
  - to:
    - ipBlock:
        cidr: 10.0.0.0/8  # Internal network range
```
- 🔒 Blocks internet access (8.8.8.8, etc.) but allows talking to internal apps.

---

### ✅ Example 3: Allow App to Access Only a Specific API (Egress)
**Scenario: A microservice should only talk to the internal auth-service and nothing else.**
```yaml
spec:
  podSelector:
    matchLabels:
      app: restricted-app
  policyTypes:
  - Egress
  egress:
  - to:
    - podSelector:
        matchLabels:
          app: auth-service
```
---

#### ✅ Example 4: Combine Ingress and Egress
**Scenario: A payment processing pod should only:**
  Receive traffic from frontend
  Send traffic to payment gateway

```yaml
policyTypes:
- Ingress
- Egress
ingress:
- from:
  - podSelector:
      matchLabels:
        app: frontend
egress:
- to:
  - ipBlock:
      cidr: 192.168.1.0/24  # payment gateway IP range
```
----

### Ingress and Egress with Port Restrictions

```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: restricted-policy
  namespace: my-namespace
spec:
  podSelector:
    matchLabels:
      app: my-app
  policyTypes:
  - Ingress
  - Egress
  ingress:
  - from:
    - podSelector: {}
    ports:
    - protocol: TCP
      port: 80
    - protocol: TCP
      port: 443
  egress:
  - to:
    - ipBlock:
        cidr: 0.0.0.0/0
    ports:
    - protocol: TCP
      port: 3306 # MYSQL
    - protocol: TCP
      port: 5432 # POSTGRES 
    - protocol: TCP
      port: 27017 # MONGODB
    - protocol: TCP
      port: 9092 # KAFKA
```


### 📌 Key Concepts

| Concept         | Description                                           |
|----------------|-------------------------------------------------------|
| `podSelector`  | Selects the target pods the policy applies to         |
| `ingress`      | Controls incoming traffic to selected pods            |
| `egress`       | Controls outgoing traffic from selected pods          |
| `from` / `to`  | Specifies the allowed sources or destinations         |

---

### 🚨 Requirements

- Requires a **CNI plugin** that supports Network Policies:
  - **Calico**
  - **Cilium**
  - **Weave Net** (with limitations)

---

### 🧪 Real-World Example

- You have a **database pod** that should only be accessed by the **backend**.
- A NetworkPolicy ensures:
  - Only `backend` can access `database`
  - `frontend`, `monitoring`, etc., **cannot** reach the database

---

## ✅ Summary

| Feature             | Ingress                              | NetworkPolicy                     |
|---------------------|--------------------------------------|-----------------------------------|
| Purpose             | Manage **external** HTTP/S access    | Control **internal** traffic      |
| Controls            | URLs, paths, domains                 | Pod-level network access          |
| Works with          | Services + Ingress Controller        | CNI plugin with policy support    |
| Benefits            | Smart routing, TLS, fewer load balancers | Pod isolation, security          |

---

## 📚 References

- [Ingress Concepts](https://kubernetes.io/docs/concepts/services-networking/ingress/)
- [Network Policies](https://kubernetes.io/docs/concepts/services-networking/network-policies/)
- [NGINX Ingress Controller](https://kubernetes.github.io/ingress-nginx/)
- [Calico Network Policies](https://docs.tigera.io/)
