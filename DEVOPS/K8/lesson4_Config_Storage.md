
# 📦 Chapter 4: Kubernetes Configuration Management & Storage

---

## 🧩 ConfigMaps – External Configuration

### ✅ What is a ConfigMap?

- Used to inject **non-sensitive**, **external configuration data** (key-value pairs) into your app.
- Keeps your application **decoupled** from the configuration.

### 📦 Use Cases

- Application settings
- Environment-specific configs (URLs, flags)

### 📘 Example

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config
data:
  DB_HOST: mysql
  DB_PORT: "3306"
```

Mount it as environment variables:

```yaml
envFrom:
- configMapRef:
    name: app-config
```

---

## 🔐 Secrets – Secure Data Management

### ✅ What is a Secret?

- Used to store **sensitive** information like:
  - Passwords
  - Tokens
  - SSH keys
- Stored base64-encoded in etcd (encrypt at rest recommended)

### 📘 Example

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: db-secret
type: Opaque
data:
  username: YWRtaW4=    # base64 encoded "admin"
  password: cGFzc3dvcmQ= # base64 encoded "password"
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

---

## 💾 Persistent Volumes (PV) & Persistent Volume Claims (PVC)

### ✅ What is a PV?

- A piece of **storage** in the cluster, provisioned by an admin or dynamically.
- Represents the **actual storage backend** (NFS, cloud disk, etc.)

### ✅ What is a PVC?

- A **user request** for storage (size, access mode).
- K8s binds the PVC to a matching PV.

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
