
# 🚪 Ingress and 🛡️ Network Policies in Kubernetes

---

## 🚪 Ingress in Kubernetes

### 🔍 What is Ingress?

**Ingress** is an API object in Kubernetes that **manages external access** to services, typically HTTP and HTTPS. It provides **routing rules**, **TLS termination**, and **virtual hosting**.

Instead of exposing each service with a `NodePort` or `LoadBalancer`, you define one Ingress controller that handles traffic based on **paths or hostnames**.

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

### 🛠️ Example: Allow Only Frontend to Talk to Backend

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
