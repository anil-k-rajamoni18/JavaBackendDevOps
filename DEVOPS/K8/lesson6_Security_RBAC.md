# Security & Role-Based Access Control (RBAC) 🔐

Security in Kubernetes is a layered approach—starting from API access control to pod-level restrictions and network segmentation. Let’s break down the core security components for Day 6, with real-world context and visuals. 🛡️

---

## 🧰 Kubernetes Security Best Practices

Implementing security best practices helps protect workloads from internal and external threats.

### ✅ Key Practices:
- Use **namespaces** for multitenancy isolation.
- Restrict access using **RBAC**.
- Enable **audit logging**.
- Use **image scanning** tools (like Trivy).
- Keep clusters and dependencies **updated**.
- Enable **encryption** at rest and in transit.

### 🌍 Real-world Example:
A fintech company uses namespaces to separate dev, staging, and production environments, enforcing RBAC and network policies to limit access between teams.

--- 

![Kubernetes Security](https://earthly.dev/blog/assets/images/k8s-cluster-security/FB9gnth.png)

---

## 🔐 Role-Based Access Control (RBAC)

RBAC defines **who can do what** in the cluster.

### 🔑 Key Resources:
- `Role` / `ClusterRole`
- `RoleBinding` / `ClusterRoleBinding`

### 📸 Example YAML:
```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: Role
metadata:
  namespace: dev
  name: pod-reader
rules:
- apiGroups: [""]
  resources: ["pods"]
  verbs: ["get", "watch", "list"]
---
apiVersion: rbac.authorization.k8s.io/v1
kind: RoleBinding
metadata:
  name: read-pods
  namespace: dev
subjects:
- kind: User
  name: jane
  apiGroup: rbac.authorization.k8s.io
roleRef:
  kind: Role
  name: pod-reader
  apiGroup: rbac.authorization.k8s.io
```

### 🌍 Real-world Example:
In a SaaS platform, only DevOps engineers can delete pods. RBAC ensures developers can view logs but can’t delete workloads.

---

## 👤 Service Accounts & Authentication

Service accounts provide identity to pods so they can interact securely with the Kubernetes API or other services.

### 🔐 Default Behavior:
- Every pod gets a default service account.
- Custom service accounts can be used with fine-grained permissions.

### 📸 Example YAML:
```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: dashboard-sa
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: dashboard-access
subjects:
- kind: ServiceAccount
  name: dashboard-sa
  namespace: kube-system
roleRef:
  kind: ClusterRole
  name: cluster-admin
  apiGroup: rbac.authorization.k8s.io
```
---

![ServiceAccount](https://miro.medium.com/v2/resize:fit:1100/format:webp/1*6CGN47bA2mjzRGBlFKK25g.png)

--

### 🌍 Real-world Example:
A CI/CD pipeline pod uses a dedicated service account with limited permissions to create deployments.

---

## 🌐 Network Policies for Securing Communication

Network Policies control **which pods can communicate** with each other.

### 🚦 Key Concepts:
- Define ingress/egress rules based on labels, ports, and namespaces.
- Implemented via CNI plugins like Calico or Cilium.

### 📸 Example YAML:
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
```

### 🌍 Real-world Example:
In an online banking app, only frontend pods can talk to backend pods—network policies prevent other internal services from accessing sensitive APIs.

![Network Policy](https://kubernetes.io/images/docs/network-policy.png)

---

## 🛡️ Pod Security Policies (PSP) & SecurityContext

**PodSecurityPolicies** (now deprecated) and **SecurityContext** define security restrictions at the pod/container level.

- Security context in Kubernetes defines security settings for pods or containers at runtime. 
- It includes settings such as Linux capabilities, SELinux, AppArmor profiles, user and group IDs, and more.
- If you do not specify a security context for your pod or container, Kubernetes will apply a default security context

### 🔐 SecurityContext Options:
- Run as non-root user
- Set read-only root filesystem
- Drop Linux capabilities

### 📸 Example YAML:
```yaml
apiVersion: v1
kind: Pod
metadata:
  name: secure-pod
spec:
  securityContext:
    runAsUser: 1000
    runAsNonRoot: true
  containers:
  - name: nginx
    image: nginx
    securityContext:
      readOnlyRootFilesystem: true
      allowPrivilegeEscalation: false
```

## Default UIDs in Linux/Containers 🆔

| UID Range       | User Type       | Description                                                                 | Security Note               |
|-----------------|----------------|-----------------------------------------------------------------------------|-----------------------------|
| **0**          | `root`         | Superuser with full admin privileges (default in most base images)          | ⚠️ **Avoid in production** |
| **1-99**       | System users   | Predefined for system services/daemons (`bin`, `daemon`, `nobody`)          | 🛡️ Restricted use          |
| **100-999**    | Service users  | For installed services (e.g., `postgres`=999, `nginx`=101 in some distros)  | 🔐 Service-specific         |
| **1000+**      | Regular users  | Default range for human users and applications                              | ✅ **Recommended**          |


### Kubernetes Default Behavior 🐳
  If no runAsUser is specified:
  Containers run as root (UID 0) by default
  This is insecure for production workload

### How to Find UIDs in an Image 🔍

```bash
# Method 1: Inspect existing users
docker run --rm <image> cat /etc/passwd

# Method 2: Check current user
docker run --rm <image> id

# Method 3: Build with known UID (Dockerfile)
RUN useradd -u 2000 myuser
USER myuser
```
### 🌍 Real-world Example:
A healthcare platform requires all pods to run as non-root and use read-only filesystems for compliance.

![SecurityContext](https://miro.medium.com/v2/resize:fit:1100/format:webp/1*sc5vj78Kad9LAOXSYzQArA.png)

---

## 📌 Summary
| Feature                  | Function                            | Benefit                                |
|--------------------------|-------------------------------------|----------------------------------------|
| RBAC                     | Restrict user actions               | Least privilege access                 |
| Service Account          | Identity for pods                   | Secure API access                      |
| Network Policy           | Restrict pod communication          | Network segmentation & isolation       |
| SecurityContext          | Limit container privileges          | Harden pod security                    |
| Security Best Practices  | Enforce security at all layers      | Strong security posture                |

---

## 📚 Reference Documentation
- [Kubernetes Security Best Practices](https://kubernetes.io/docs/concepts/security/overview/)
- [RBAC](https://kubernetes.io/docs/reference/access-authn-authz/rbac/)
- [Service Accounts](https://kubernetes.io/docs/tasks/configure-pod-container/configure-service-account/)
- [Network Policies](https://kubernetes.io/docs/concepts/services-networking/network-policies/)
- [Pod Security Standards](https://kubernetes.io/docs/concepts/security/pod-security-standards/)
- [SecurityContext](https://kubernetes.io/docs/tasks/configure-pod-container/security-context/)

---
