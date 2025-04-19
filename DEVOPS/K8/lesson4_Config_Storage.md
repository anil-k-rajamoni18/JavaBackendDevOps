
# 📦 Chapter 4: Kubernetes Configuration Management & Storage

---

## 🧩 ConfigMaps – External Configuration

### ✅ What is a ConfigMap?

- Used to inject **non-sensitive**, **external configuration data** (key-value pairs) into your app.
- Keeps your application **decoupled** from the configuration.
- Instead of hardcoding values (like file paths, settings, URLs) into your containers, you store them in a ConfigMap and inject them at runtime.
- Think of it like a config file or environment variable that your app can read—but managed by Kubernetes.



### 📦 Use Cases

- Application settings
- Environment-specific configs (URLs, flags)

### 📘 Example-1

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config
data:
  APP_MODE: "production"
  APP_VERSION: "1.0.3"
  WELCOME_MSG: "Hello from K8s ConfigMap 👋"
```
**Using CLI**
```bash
kubectl create configmap app-config \
  --from-literal=APP_MODE=production \
  --from-literal=APP_VERSION=1.0.3 \
  --from-literal=WELCOME_MSG="Hello from K8s ConfigMap 👋"
```

Mount it as environment variables:

```yaml
envFrom:
- configMapRef:
    name: app-config

or 

env:
- name: APP_MODE
  valueFrom:
    configMapKeyRef:
      name: app-config
      key: APP_MODE
```



### 🎯 Example-2
- 🔸 Step 1: Create your HTML file
**k8s-welcome.html:**
```html
<!DOCTYPE html>
<html>
  <body>
    <h1>Welcome to Kubernetes! 🚀</h1>
    <p>This page is served from a ConfigMap. 📦</p>
  </body>
</html>
```
- 🔸 Step 2: Create a ConfigMap from it
```bash
kubectl create configmap nginx-html --from-file=k8s-welcome.html
```
  - This creates a ConfigMap named nginx-html that holds your HTML as a key-value pair, where:
      key = k8s-welcome.html
      value = contents of the file

- 🔸 Step 3: Mount the ConfigMap in a Pod
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-web-server
spec:
  replicas: 1
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
        volumeMounts:
        - name: html-volume
          mountPath: /usr/share/nginx/html/index.html
          subPath: k8s-welcome.html
      volumes:
      - name: html-volume
        configMap:
          name: nginx-html
```
  - Mount the file from the ConfigMap (k8s-welcome.html)
  - Into the container at /usr/share/nginx/html/index.html (Nginx's default page path)

---
### 📦 Why Use ConfigMaps?
  - 🔁 Easily update config without rebuilding Docker images
  - 🔄 Reuse the same image in different environments (dev, QA, prod)
  - 🔐 Can be combined with Secrets for sensitive values
  - 🔧 Great for runtime configs, feature toggles, or templates


---
## 🔐 Secrets – Secure Data Management

### ✅ What is a Secret?

A Secret is a Kubernetes object used to store sensitive data like:
  - Passwords
  - API keys
  - TLS certificates
  - DB credentials

- Unlike ConfigMaps (used for non-sensitive data), Secrets are base64-encoded and are meant to be kept confidential.


### 📘 Example

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: db-secret
type: Opaque
data:
  DB_USER: YWRtaW4=       # base64 for "admin"
  DB_PASSWORD: c2VjcmV0   # base64 for "secret"
```

**Using CLI**
```bash
kubectl create secret generic db-secret \
  --from-literal=DB_USER=admin \
  --from-literal=DB_PASSWORD=secret
```

Mount it into a Pod:

```yaml
env:
- name: DB_USER
  valueFrom:
    secretKeyRef:
      name: db-secret
      key: username
```

As Volume Mounts (Files):

```yaml
volumeMounts:
- name: secret-volume
  mountPath: /etc/secret-data

volumes:
- name: secret-volume
  secret:
    secretName: db-secret

# Now your secret values will show up as files like /etc/secret-data/DB_USER
```

### 📌 Tips and Best Practices
  - Don’t store Secrets in Git unless they’re encrypted (e.g., Sealed Secrets or SOPS).
  - Use RBAC to restrict who can read Secrets.
  - Use kubectl get secret db-secret -o yaml carefully – it will show base64-encoded content.
  - In production: use external secret managers (AWS Secrets Manager, Vault, etc.) with integrations.

---

## 💾 Persistent Volumes (PV) & Persistent Volume Claims (PVC)

### ✅ What is a PV?

- A piece of **storage** in the cluster, provisioned by an admin or dynamically.
- Represents the **actual storage backend** (NFS, cloud disk, etc.)
- A Persistent Volume (PV) is a pre-provisioned piece of storage in your cluster.


### ✅ What is a PVC?

- A **user request** for storage (size, access mode).
- K8s binds the PVC to a matching PV.
- A PVC is a request made by a pod to use a PV.
- 🔁 It’s like ordering storage on demand:
*“Hey K8s, I need 1Gi of storage that supports ReadWriteOnce access.”*

### 📘 Example

**PersistentVolume:**

```yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: my-pv
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadWriteOnce
  hostPath:
    path: /data/pv
```

**PersistentVolumeClaim:**

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: my-pvc
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 1Gi
```
### 🔹 Difference: spec.volumes vs spec.containers.volumeMounts
Think of it like plugging in a hard drive (volume) and choosing where to use it inside a container (mount path).

#### ✅ spec.volumes (📦 Define the storage)
- This defines the source of the volume.
- It can be an emptyDir, hostPath, persistentVolumeClaim, configMap, Secret, etc.
- It is cluster-level and independent of any container.

#### ✅ spec.containers.volumeMounts (📍Use the volume inside a container)
- This tells Kubernetes where to mount that volume inside the container’s file system.
- Each container has its own volumeMounts.

### 📦 Common Volume Types in Kubernetes

Here’s a cheat sheet of popular volume types:

| Volume Type                                        | Description                                      | Use Case                                      |
|---------------------------------------------------|--------------------------------------------------|-----------------------------------------------|
| `emptyDir`                                         | Temporary storage, erased when pod is deleted    | Caching, scratch space                        |
| `hostPath`                                         | Mounts a file/dir from host node                 | Access host files, logs (risky in production) |
| `configMap`                                        | Mounts ConfigMap data as files                   | App configs, envs                             |
| `secret`                                           | Mounts Secret data as files                      | Sensitive configs                             |
| `persistentVolumeClaim`                            | Mounts a PersistentVolume via PVC                | Persistent storage (e.g. DBs)                 |
| `nfs`                                              | Mounts a remote NFS share                        | Shared storage                                |
| `projected`                                        | Combines several volume sources into one         | Secrets + ConfigMaps together                 |
| `downwardAPI`                                      | Mounts pod metadata into containers              | Inject pod name, IP, labels                   |
| `csi`                                              | Mounts storage using CSI drivers                 | Dynamic/cloud storage (EBS, PD, etc)          |
| `azureDisk` / `awsElasticBlockStore` / `gcePersistentDisk` | Cloud-specific disks                       | Cloud volumes                                 |

---

## 📦 StorageClasses & Dynamic Provisioning

### ✅ What is a StorageClass?

- Defines **how storage is provisioned dynamically**.
- Used with cloud providers or external storage systems.

### 📘 Example

**StorageClass:**

```yaml
apiVersion: storage.k8s.io/v1
kind: StorageClass
metadata:
  name: fast-storage
provisioner: kubernetes.io/aws-ebs
parameters:
  type: gp2
```

**PVC using StorageClass:**

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: fast-pvc
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 5Gi
  storageClassName: fast-storage
```

No need to create a PV manually – it's **provisioned on-demand**.

---

## 🧱 StatefulSets – For Stateful Applications

### ✅ What is a StatefulSet?

- Manages **stateful** applications (e.g., databases).
- Maintains:
  - **Stable network identity**
  - **Persistent storage**
  - **Ordered startup and termination**

### Use Cases

- MySQL, Cassandra, Kafka
- Any app that needs a unique, stable identity and storage

### 📘 Example

```yaml
apiVersion: apps/v1
kind: StatefulSet
metadata:
  name: mysql
spec:
  serviceName: "mysql"
  replicas: 3
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
        image: mysql:5.7
        ports:
        - containerPort: 3306
        volumeMounts:
        - name: mysql-data
          mountPath: /var/lib/mysql
  volumeClaimTemplates:
  - metadata:
      name: mysql-data
    spec:
      accessModes: ["ReadWriteOnce"]
      resources:
        requests:
          storage: 1Gi
```

Each pod gets a **unique name** (`mysql-0`, `mysql-1`, …) and **persistent volume**.

---

## ✅ Summary Table

| Concept            | Purpose                               | Example Use              |
|--------------------|---------------------------------------|--------------------------|
| ConfigMap          | External config (non-sensitive)       | DB hostnames, flags      |
| Secret             | Store sensitive data securely         | API keys, DB passwords   |
| PV / PVC           | Static or dynamic storage binding     | File storage, logs       |
| StorageClass       | Dynamic provisioning strategy         | Cloud disks, SSDs        |
| StatefulSet        | Run stateful apps with stable IDs     | MySQL, Kafka, Redis      |

---

## 📚 References

- [ConfigMaps](https://kubernetes.io/docs/concepts/configuration/configmap/)
- [Secrets](https://kubernetes.io/docs/concepts/configuration/secret/)
- [Persistent Volumes](https://kubernetes.io/docs/concepts/storage/persistent-volumes/)
- [StorageClasses](https://kubernetes.io/docs/concepts/storage/storage-classes/)
- [StatefulSets](https://kubernetes.io/docs/concepts/workloads/controllers/statefulset/)
