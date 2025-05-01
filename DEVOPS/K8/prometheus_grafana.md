# Setting Up Prometheus and Grafana on a Local Minikube Cluster

## Prerequisites
- Minikube installed and running (`minikube start`)
- `kubectl` configured to work with your Minikube cluster
- Helm installed (recommended for easier installation)

## Method 1: Using Helm (Recommended)

### 1. Start Minikube
```bash
minikube start
```

### 2. Install Helm
```bash
# On macOS
brew install helm

# On Linux
curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash
```

### 3. Add Helm Repositories
```bash
helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo add grafana https://grafana.github.io/helm-charts
helm repo update
```

### 4. Install Prometheus
```bash
helm install prometheus prometheus-community/prometheus \
  --namespace monitoring \
  --create-namespace \
  --set alertmanager.persistentVolume.storageClass="standard" \
  --set server.persistentVolume.storageClass="standard"
```

### 5. Install Grafana
```bash
helm install grafana grafana/grafana \
  --namespace monitoring \
  --set persistence.storageClassName="standard" \
  --set persistence.enabled=true \
  --set adminPassword='admin' \
  --set service.type=NodePort
```

### 6. Access the Services
**Get Grafana URL:**
```bash
export NODE_PORT=$(kubectl get --namespace monitoring -o jsonpath="{.spec.ports[0].nodePort}" services grafana)
export NODE_IP=$(minikube ip)
echo "Grafana URL: http://$NODE_IP:$NODE_PORT"
```

**Get Prometheus URL:**
```bash
export NODE_PORT=$(kubectl get --namespace monitoring -o jsonpath="{.spec.ports[0].nodePort}" services prometheus-server)
export NODE_IP=$(minikube ip)
echo "Prometheus URL: http://$NODE_IP:$NODE_PORT"
```

**Get Grafana admin password:**
```bash
kubectl get secret --namespace monitoring grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo
```

### 7.Expose Services
```bash
# Expose Grafana
kubectl port-forward -n monitoring svc/grafana 3000:3000 &

# Expose Prometheus
kubectl port-forward -n monitoring svc/prometheus-k8s 9090:9090 &

# Expose Alertmanager (optional)
kubectl port-forward -n monitoring svc/alertmanager-main 9093:9093 &
```

### 8.Accessing the Dashboards
```bash
Grafana: http://localhost:3000 (admin/admin by default)

Prometheus: http://localhost:9090
```

### 9.Configure Grafana to Use Prometheus
- Log in to Grafana
- Go to Configuration > Data Sources
- Add Prometheus as a data source
- Set URL to:
    Helm: http://prometheus-server.monitoring.svc.cluster.local
    Manifests: http://prometheus-k8s.monitoring.svc.cluster.local
- Click "Save & Test"