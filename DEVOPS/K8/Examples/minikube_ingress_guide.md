# 🧭 How to Set Up Ingress in Minikube

This guide walks you through enabling and using **Ingress** in a **Minikube** cluster.

---

## ✅ Step 1: Start Minikube

```bash
minikube start
```

_Optional_: Add flags for driver, CPU, or memory:

```bash
minikube start --driver=docker --cpus=2 --memory=4096
```

---

## ✅ Step 2: Enable the Ingress Addon

Minikube provides an NGINX-based ingress controller:

```bash
minikube addons enable ingress
```

Verify the controller is running:

```bash
kubectl get pods -n kube-system
```

You should see a pod like `ingress-nginx-controller`.

---

## ✅ Step 3: Deploy an Example App with Ingress

Create the following YAML files:

---

### 📄 `deployment.yaml`

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: hello-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: hello
  template:
    metadata:
      labels:
        app: hello
    spec:
      containers:
      - name: hello
        image: hashicorp/http-echo
        args:
        - "-text=Hello from Minikube"
        ports:
        - containerPort: 5678
```

---

### 📄 `service.yaml`

```yaml
apiVersion: v1
kind: Service
metadata:
  name: hello-service
spec:
  selector:
    app: hello
  ports:
  - protocol: TCP
    port: 80
    targetPort: 5678
```

---

### 📄 `ingress.yaml`

```yaml
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: hello-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  rules:
  - host: hello.local
    http:
      paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: hello-service
            port:
              number: 80
```

---

### ✅ Apply the YAML

```bash
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
kubectl apply -f ingress.yaml
```

---

## ✅ Step 4: Add to Your Hosts File

Update your local DNS by editing `/etc/hosts` (Linux/macOS) or `C:\Windows\System32\drivers\etc\hosts` (Windows):

```bash
echo "$(minikube ip) hello.local" | sudo tee -a /etc/hosts
```

---

## ✅ Step 5: Test It

You should now be able to access the service:

```bash
curl http://hello.local
```

Expected output:

```
Hello from Minikube
```

Or open in your browser:

```
http://hello.local
```

---

### 🚧 The Problem (If we use Minikube inside WSL2 with Docker as Driver)
- When Minikube runs inside WSL2 with Docker, it creates a Docker network inside WSL2. 
- That network isn't directly visible to the Windows host, so even if you map hello.local to Minikube's IP (192.168.49.2), it won’t work because that IP isn’t reachable from Windows — it's internal to WSL2.


#### ✅ Use minikube tunnel (Best for Ingress)
- Open a new WSL2 terminal and run:
```bash
sudo minikube tunnel
```
- This creates a network route from your host (Windows) to the Minikube services, especially LoadBalancer and Ingress.
- Then on Windows, Make sure /etc/hosts (or Windows hosts file) has:
```bash
127.0.0.1 nginx.local
```
- Open browser and go to: http://nginx.local
- This works because minikube tunnel exposes the ingress controller to your Windows localhost.


---

## 🚀 Bonus Tips

- Use multiple Ingress rules for different paths or subdomains.
- Enable `ingress-dns` addon for dynamic domain resolution (macOS/Linux only).
- Add TLS with cert-manager or use self-signed certs.

---

Enjoy your Minikube-powered Ingress setup! 🎉