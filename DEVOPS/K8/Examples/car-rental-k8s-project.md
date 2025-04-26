# 🚗 Kubernetes Project: Deploy a Car Rental REST API (Spring Boot + MySQL)

This hands-on project helps you deploy a **Spring Boot REST API** with a **MySQL Database** using Kubernetes. It includes:

- ✅ Deployments (API & DB)
- ✅ Services
- ✅ PVC (PersistentVolumeClaim)
- ✅ Secrets (DB credentials)
- ✅ Volumes & Bind Mounts
- ✅ Network Policies

---

## 🧱 Architecture Overview

```
+-------------+         +--------------------+
|  Ingress /  |         |  Frontend (Optional)|
|  External   +-------->                    |
|  Access     |         +--------------------+
      |
      v
+-------------+          +-----------------+
|  Spring Boot| <------> |   MySQL DB      |
|   API Pod   |          | (Persistent Vol)|
+-------------+          +-----------------+
```

---

## 📁 1. Create Namespace

```bash
kubectl create namespace car-rental
```

---

## 🔐 2. Create Secrets

**db-secret.yaml**
```yaml
apiVersion: v1
kind: Secret
metadata:
  name: mysql-secret
  namespace: car-rental
type: Opaque
data:
  mysql-root-password: cGFzc3dvcmQ=   # base64: password
  mysql-user: Y2FydXNlcg==           # base64: caruser
  mysql-password: Y2FycGFzcw==       # base64: carpass
```

```bash
kubectl apply -f db-secret.yaml
```

---

## 📦 3. Persistent Volume Claim

**mysql-pvc.yaml**
```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: mysql-pvc
  namespace: car-rental
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 1Gi
```

---

## 🗄️ 4. MySQL Deployment

**mysql-deployment.yaml**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mysql
  namespace: car-rental
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
        image: mysql:latest
        env:
          - name: MYSQL_ROOT_PASSWORD
            valueFrom:
              secretKeyRef:
                name: mysql-secret
                key: mysql-root-password
          - name: MYSQL_USER
            valueFrom:
              secretKeyRef:
                name: mysql-secret
                key: mysql-user
          - name: MYSQL_PASSWORD
            valueFrom:
              secretKeyRef:
                name: mysql-secret
                key: mysql-password
          - name: MYSQL_DATABASE
            value: car_rental_db
        ports:
          - containerPort: 3306
        volumeMounts:
          - name: mysql-storage
            mountPath: /var/lib/mysql
      volumes:
        - name: mysql-storage
          persistentVolumeClaim:
            claimName: mysql-pvc
---
apiVersion: v1
kind: Service
metadata:
  name: mysql-service
  namespace: car-rental
spec:
  ports:
    - port: 3306
  selector:
    app: mysql
  type: ClusterIP
```

---

## 🚘 5. Spring Boot App Deployment

**app-deployment.yaml**
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: carrental-api
  namespace: car-rental
spec:
  replicas: 1
  selector:
    matchLabels:
      app: carrental-api
  template:
    metadata:
      labels:
        app: carrental-api
    spec:
      containers:
        - name: carrental
          image: akumarraj/apps:car-rentalapi-spring-0.1
          ports:
            - containerPort: 8080

          env:
            - name: DB_HOST
              value: mysql-service
            - name: DB_USER
              valueFrom:
                secretKeyRef:
                  name: mysql-secret
                  key: mysql-user
            - name: DB_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: mysql-secret
                  key: mysql-password
            - name: DB_DATABASE
              value: car_rental_db

          resources:
            requests:
              memory: "256Mi"
              cpu: "250m"
            limits:
              memory: "512Mi"
              cpu: "500m"

          livenessProbe:
            httpGet:
              path: /actuator/health/liveness
              port: 8080
            initialDelaySeconds: 90
            periodSeconds: 10
            failureThreshold: 3

          readinessProbe:
            httpGet:
              path: /actuator/health/readiness
              port: 8080
            initialDelaySeconds: 90
            periodSeconds: 10
            failureThreshold: 3

          securityContext:
            runAsNonRoot: true
            runAsUser: 1000
            runAsGroup: 3000
            allowPrivilegeEscalation: false
            capabilities:
              drop:
                - ALL
                - KILL
---
apiVersion: v1
kind: Service
metadata:
  name: carrental-service
  namespace: car-rental
spec:
  ports:
    - port: 80
      targetPort: 8080
  selector:
    app: carrental-api
  type: NodePort
```

---

## 🔒 6. Network Policy

**network-policy.yaml**
```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: api-to-mysql
  namespace: car-rental
spec:
  podSelector:
    matchLabels:
      app: mysql
  policyTypes:
    - Ingress
  ingress:
    - from:
        - podSelector:
            matchLabels:
              app: carrental-api
      ports:
        - protocol: TCP
          port: 3306
```

---

## 🧪 7. Apply All Resources

```bash
kubectl apply -f db-secret.yaml
kubectl apply -f mysql-pvc.yaml
kubectl apply -f mysql-deployment.yaml
kubectl apply -f app-deployment.yaml
kubectl apply -f network-policy.yaml
```

---

## 🧼 8. Clean Up

```bash
kubectl delete ns car-rental
```

---

## ✅ Features Practiced

| Feature             | Purpose                             |
|---------------------|-------------------------------------|
| **Deployment**       | Spring Boot & MySQL Pods            |
| **Service**          | ClusterIP & NodePort                |
| **PVC & Volumes**    | Persistent storage for MySQL        |
| **Secrets**          | DB Credentials                      |
| **Network Policy**   | Restrict DB access to app only      |

---

Happy Deploying 🚀
