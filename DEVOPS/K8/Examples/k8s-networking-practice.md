# 🚀 Kubernetes Networking Practice Project

## 🧩 Project: Deploy a Two-Tier Application (Frontend + Backend)

This hands-on project demonstrates Kubernetes networking using:

- **Services**
- **Ingress**
- **Network Policies**

---

## 🔧 1. Project Architecture

```
            +-------------------------+
            |       Ingress          |
            |  (HTTP external entry) |
            +----------+-------------+
                       |
             +---------v----------+
             |    Frontend Pod    |
             |  (Node.js/Nginx)   |
             +---------+----------+
                       |
             +---------v----------+
             |    Backend Pod     |
             |   (API Service)    |
             +--------------------+
```

---

## 🛠️ 2. Deployment Files

### 🧱 Backend API Deployment (Express/Flask)

**backend-deployment.yaml**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: backend
spec:
  replicas: 1
  selector:
    matchLabels:
      app: backend
  template:
    metadata:
      labels:
        app: backend
    spec:
      containers:
      - name: backend
        image: your-dockerhub/backend:latest
        ports:
        - containerPort: 5000
---
apiVersion: v1
kind: Service
metadata:
  name: backend-service
spec:
  selector:
    app: backend
  ports:
    - port: 5000
      targetPort: 5000
  type: ClusterIP
```

---

### 🌐 Frontend Deployment (Nginx or Node.js)

**frontend-deployment.yaml**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: frontend
spec:
  replicas: 1
  selector:
    matchLabels:
      app: frontend
  template:
    metadata:
      labels:
        app: frontend
    spec:
      containers:
      - name: frontend
        image: your-dockerhub/frontend:latest
        ports:
        - containerPort: 80
        env:
        - name: BACKEND_URL
          value: "http://backend-service:5000"
---
apiVersion: v1
kind: Service
metadata:
  name: frontend-service
spec:
  selector:
    app: frontend
  ports:
    - port: 80
      targetPort: 80
  type: ClusterIP
```

---

## 🌐 3. Ingress Controller and Rule

> 💡 Make sure you have an Ingress controller installed.

**frontend-ingress.yaml**
```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: frontend-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  rules:
    - host: frontend.local
      http:
        paths:
          - path: /
            pathType: Prefix
            backend:
              service:
                name: frontend-service
                port:
                  number: 80
```

### 🧪 Add to `/etc/hosts`
```
127.0.0.1  frontend.local
```

---

## 🔒 4. NetworkPolicy to Restrict Backend

**backend-networkpolicy.yaml**
```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-frontend-to-backend
spec:
  podSelector:
    matchLabels:
      app: backend
  ingress:
    - from:
        - podSelector:
            matchLabels:
              app: frontend
      ports:
        - protocol: TCP
          port: 5000
  policyTypes:
    - Ingress
```

✅ Allows only pods with `app: frontend` to connect to backend on port 5000.

---

## 🧪 5. Apply & Test

```bash
kubectl apply -f backend-deployment.yaml
kubectl apply -f frontend-deployment.yaml
kubectl apply -f frontend-ingress.yaml
kubectl apply -f backend-networkpolicy.yaml
```

- Visit: http://frontend.local
- Frontend ➝ Backend ✅
- Try accessing backend from unrelated pod ❌ (blocked)

---

## ✅ Concepts You Practiced

| Feature            | Description                                 |
|--------------------|---------------------------------------------|
| **Services**        | ClusterIP for internal communication        |
| **Ingress**         | HTTP routing from outside                   |
| **NetworkPolicy**   | Limit pod communication via labels/ports    |
| **Environment Vars**| Inject backend URL into frontend            |

---

## 🧰 Bonus Add-ons

- Add **TLS** to Ingress
- Deploy into a **Namespace**
- Monitor with **kubectl logs / exec**
- Add **PodSecurityContext**

---

Happy Learning Kubernetes Networking! 🎉
