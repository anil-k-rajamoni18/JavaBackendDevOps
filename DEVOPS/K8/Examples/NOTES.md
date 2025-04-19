1. Create a Simple HTML File

2. Create a ConfigMap from Your HTML File
```bash
  kubectl create configmap nginx-html --from-file=index.html
```
 3. Create a Deployment for Nginx

```yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment
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
          subPath: index.html
        - name: podname
          mountPath: /usr/share/nginx/html/podinfo
          subPath: podname
      volumes:
      - name: html-volume
        configMap:
          name: nginx-html
      - name: podname
        downwardAPI:
          items:
          - path: "podname"
            fieldRef:
              fieldPath: metadata.name
```

 4. Create a Service to Expose Nginx
```yml
apiVersion: v1
kind: Service
metadata:
  name: nginx-service
spec:
  type: NodePort
  selector:
    app: nginx
  ports:
    - protocol: TCP
      port: 80
      targetPort: 80
      nodePort: 30080

```

5. Apply the Config and Deploy

```bash
kubectl apply -f nginx-deployment.yaml
kubectl apply -f nginx-service.yaml

```

6. Access It from Your Browser
```bash
minikube service nginx-service

or 

http://<minikube-ip>:30080
minikube ip



```