# 🚀 Kubernetes Hands-On Project: Web App Deployment (Nginx)

---

## 🎯 Project Objective

Deploy a simple **Nginx web server** on Kubernetes that serves a static HTML page. You'll use:
- **Deployments**
- **Services**
- **Rolling Updates**
- **Scaling**
- **Rollback**

---

## 📁 Step-by-Step Setup

### 1️⃣ Create a Namespace

```bash
kubectl create namespace web-app
```

---

### 2️⃣ Create Deployment for Nginx Web Server

📄 File: `nginx-deployment.yaml`

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
  namespace: web-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
        - name: nginx
          image: nginx:latest
          ports:
            - containerPort: 80
```

▶️ Apply it:

```bash
kubectl apply -f nginx-deployment.yaml
```

---

### 3️⃣ Create a Service to Expose the Deployment

📄 File: `nginx-service.yaml`

```yaml
apiVersion: v1
kind: Service
metadata:
  name: nginx-service
  namespace: web-app
spec:
  selector:
    app: nginx
  ports:
    - protocol: TCP
      port: 80
      targetPort: 80
  type: LoadBalancer
```

▶️ Apply it:

```bash
kubectl apply -f nginx-service.yaml
```

---

### 4️⃣ Verify Everything is Running

```bash
kubectl get deployments -n web-app
kubectl get services -n web-app
kubectl get pods -n web-app
```

---

### 5️⃣ Access the Web App

```bash
kubectl get services -n web-app
```

Open the **EXTERNAL-IP** in your browser to see the Nginx welcome page.

---

### 6️⃣ Rolling Update (Change Image Version)

Update the deployment to use `nginx:alpine`.

```yaml
image: nginx:alpine
```

▶️ Re-apply:

```bash
kubectl apply -f nginx-deployment.yaml
```

🧭 Monitor rollout:

```bash
kubectl rollout status deployment/nginx-deployment -n web-app
```

---

### 7️⃣ Scale the Deployment

```bash
kubectl scale deployment/nginx-deployment --replicas=5 -n web-app
kubectl get pods -n web-app
```

---

### 8️⃣ Roll Back the Deployment

```bash
kubectl rollout undo deployment/nginx-deployment -n web-app
```

👉 Or to a specific revision:

```bash
kubectl rollout undo deployment/nginx-deployment --to-revision=1 -n web-app
```

---

### 9️⃣ Clean Up

```bash
kubectl delete deployment nginx-deployment -n web-app
kubectl delete service nginx-service -n web-app
```

---

## ✅ Concepts Practiced

- **Deployment**
- **Pods & ReplicaSets**
- **LoadBalancer Service**
- **Rolling Updates**
- **Scaling**
- **Rollback**

---

## 🔁 Next Practice Ideas

- Add **Readiness & Liveness Probes**
- Add **Persistent Volumes** for stateful storage
- Use **ConfigMaps** and **Secrets** for configuration
- Add **Ingress** and expose using a domain

---