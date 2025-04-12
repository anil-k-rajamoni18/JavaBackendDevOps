# 📦 Custom Helm Chart for Book API Project (React + Node.js + MySQL)

This Helm chart example deploys a simple full-stack app:
- **Frontend**: React
- **Backend**: Node.js REST API
- **Database**: MySQL

It includes:
- Deployments for all three components
- Persistent Volume Claims (PVCs)
- Services
- Ingress

---

## 📁 Folder Structure
```
book-api-chart/
├── charts/
├── templates/
│   ├── frontend-deployment.yaml
│   ├── backend-deployment.yaml
│   ├── mysql-deployment.yaml
│   ├── frontend-service.yaml
│   ├── backend-service.yaml
│   ├── mysql-service.yaml
│   ├── pvc.yaml
│   └── ingress.yaml
├── values.yaml
├── Chart.yaml
```

---

## 📜 `Chart.yaml`
```yaml
apiVersion: v2
name: book-api-chart
version: 0.1.0
description: Helm chart for Book API stack
```

---

## 📜 `values.yaml`
```yaml
frontend:
  image: yourdockerhub/react-frontend:latest
  port: 3000

backend:
  image: yourdockerhub/node-backend:latest
  port: 5000
  env:
    - name: DB_HOST
      value: mysql
    - name: DB_USER
      value: user
    - name: DB_PASSWORD
      value: password

mysql:
  image: mysql:8
  port: 3306
  storage: 1Gi
  env:
    - name: MYSQL_ROOT_PASSWORD
      value: password
```

---

## 🚀 Deployment Templates

### 🔧 `frontend-deployment.yaml`
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
          image: {{ .Values.frontend.image }}
          ports:
            - containerPort: {{ .Values.frontend.port }}
```

### 🔧 `backend-deployment.yaml`
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
          image: {{ .Values.backend.image }}
          ports:
            - containerPort: {{ .Values.backend.port }}
          env:
            {{- toYaml .Values.backend.env | nindent 12 }}
```

### 🔧 `mysql-deployment.yaml`
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mysql
  template:
    metadata:
      labels:
        app: mysql
    spec:
      containers:
        - name: mysql
          image: {{ .Values.mysql.image }}
          ports:
            - containerPort: {{ .Values.mysql.port }}
          env:
            {{- toYaml .Values.mysql.env | nindent 12 }}
          volumeMounts:
            - mountPath: "/var/lib/mysql"
              name: mysql-storage
      volumes:
        - name: mysql-storage
          persistentVolumeClaim:
            claimName: mysql-pvc
```

---

## 🔗 Services

### 🌐 `frontend-service.yaml`
```yaml
apiVersion: v1
kind: Service
metadata:
  name: frontend
spec:
  type: ClusterIP
  selector:
    app: frontend
  ports:
    - port: 80
      targetPort: {{ .Values.frontend.port }}
```

### 🌐 `backend-service.yaml`
```yaml
apiVersion: v1
kind: Service
metadata:
  name: backend
spec:
  type: ClusterIP
  selector:
    app: backend
  ports:
    - port: 80
      targetPort: {{ .Values.backend.port }}
```

### 🌐 `mysql-service.yaml`
```yaml
apiVersion: v1
kind: Service
metadata:
  name: mysql
spec:
  type: ClusterIP
  selector:
    app: mysql
  ports:
    - port: 3306
      targetPort: 3306
```

---

## 📦 Persistent Volume Claim (PVC)

### 💾 `pvc.yaml`
```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: mysql-pvc
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: {{ .Values.mysql.storage }}
```

---

## 🌐 Ingress

### 📥 `ingress.yaml`
```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: book-api-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  rules:
    - host: bookapp.local
      http:
        paths:
          - path: /frontend
            pathType: Prefix
            backend:
              service:
                name: frontend
                port:
                  number: 80
          - path: /api
            pathType: Prefix
            backend:
              service:
                name: backend
                port:
                  number: 80
```

---

# 📘 Helm Commands Practice Guide for Book API Project

This guide provides all essential Helm CLI commands to deploy, manage, and test the Book API (React + Node.js + MySQL) Helm chart in Kubernetes. 🚀

---

## 🛠️ 1. Create a New Helm Chart
```bash
helm create book-api-chart
```

---

## 📥 2. Install the Helm Chart
```bash
helm install book-app ./book-api-chart
```

---

## 🔄 3. Upgrade the Release
```bash
helm upgrade book-app ./book-api-chart
```

---

## 📤 4. Uninstall the Release
```bash
helm uninstall book-app
```

---

## 🔍 5. Dry Run to Preview Kubernetes Manifests
```bash
helm install book-app ./book-api-chart --dry-run --debug
```

---

## 🧪 6. Template and Output YAML
```bash
helm template book-app ./book-api-chart
```

---

## ⚙️ 7. Use Custom Values File
```bash
helm install book-app ./book-api-chart -f custom-values.yaml
```

---

## 📄 8. List Installed Helm Releases
```bash
helm list
```

---

## 🔍 9. Inspect Values from a Chart
```bash
helm show values ./book-api-chart
```

---

## 📦 10. Package the Chart into a `.tgz` Archive
```bash
helm package ./book-api-chart
```

---

## 🔐 11. Add & Use External Chart Repos
```bash
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update
helm search repo bitnami/mysql
```

---

## 🧹 12. Cleanup (Delete PVCs, etc.)
```bash
kubectl delete pvc -l app=mysql
```

---

📝 **Pro Tip:** Create environment-specific values files like `dev-values.yaml`, `prod-values.yaml` and use `-f` flag to deploy accordingly.


