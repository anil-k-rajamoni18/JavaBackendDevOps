# 🚀 Introduction to Amazon EKS (Elastic Kubernetes Service)

## 🌐 What is AWS EKS?
- **Amazon EKS** is a **fully managed Kubernetes service** on AWS.
- It makes it easy to deploy, manage, and scale containerized applications using Kubernetes.
- AWS handles the **Kubernetes control plane**, upgrades, high availability, and security.
- As it handles the complex parts of Kubernetes (like the control plane), so you can focus on building and running your apps. No need to worry about setting up or patching master nodes — AWS does it for you.

> 🧳 **Real-World Example**: Think of EKS as Uber for Kubernetes—you just hop in (deploy containers), and AWS drives (manages control plane, HA, scaling).


## 🧰 EKS Use Cases
- Run microservices at scale using Kubernetes.
- Modernize legacy applications using container orchestration.
- Portable workloads using standard Kubernetes APIs.
- CI/CD pipelines for containerized apps.


## 🔑 Key Benefits of EKS

- 🔧 **No Control Plane Management** – AWS manages the Kubernetes control layer.
- 🔗 **Deep AWS Integration** – Works with IAM, VPC, CloudWatch, ELB, RDS, and more.
- 🔐 **Enterprise-Grade Security** – IAM for access, encryption at rest & in transit.
- ⚖️ **Scalable** – Handles thousands of pods; supports autoscaling & Fargate.
- 🌍 **Hybrid Ready** – Run on AWS, on-prem via **EKS Anywhere**, or in multi-cloud setups.
- 💸 **Cost-Efficient** – Pay only for what you use. No charge for control plane (since Oct 2020).

---

## 🤔 EKS vs. Self-Managed Kubernetes

| Feature 🧩 | Self-Managed K8s 🧱 | Amazon EKS ☁️ |
|-----------|--------------------|---------------|
| Control Plane | You manage it | AWS handles it |
| Node Scaling | Manual setup | Built-in Auto Scaling |
| High Availability | DIY setup | Multi-AZ out-of-box |
| IAM Integration | Custom config | Native IAM support |
| Monitoring | Set up tools (Prometheus) | Built-in CloudWatch |
| Maintenance | You patch everything | AWS patches control plane |
| Cost | Infra + management time | Pay for what you use |

> ⚠️ **Self-managed Kubernetes = More control but more responsibility.**

> 🟢 **EKS = Less hassle, better security, easier scaling.**

--- 

## 🛠️ Master Node Architecture in AWS EKS:

![EKS Architecture](https://github.com/user-attachments/assets/908b7b50-5084-49ba-86d0-5334969b738d)

- 🧬 Control Plane: Distributed across **3 Availability Zones**.
- 🔄 Automatically **patched, scaled, and maintained** by AWS.
- 🔒 Secure VPC endpoints to communicate with worker nodes.
- 🔒 No direct access to master nodes — AWS manages all.

---

## 🧩 EKS Node (Data Plane) Options

### 1️⃣ EC2 Self-Managed Nodes
- Full control over EC2 config and AMIs.
- You manage updates, scaling, etc.
- ✅ Great for: custom workloads, GPU nodes, Spot Instances.

### 2️⃣ EC2 Managed Node Groups
- AWS provisions and updates nodes.
- Just pick instance types—AWS handles the rest.
- ✅ Great for: general workloads, fast scaling.

### 3️⃣ AWS Fargate
- **Serverless Kubernetes**.
- No nodes to manage—pay per running pod.
- ✅ Great for: microservices, batch jobs, quick deployments.

---

## 📊 Comparison: Node Options

| Feature ⚙️ | EC2 Self-Managed | EC2 Managed | Fargate (Serverless) |
|------------|------------------|-------------|----------------------|
| Node Management | You | AWS | None |
| Scaling | Manual/custom | Auto | Auto |
| Cost | EC2 + ops cost | EC2 cost | Pod runtime only |
| Best For | Custom infra, ML | Scalable apps | Microservices, short tasks |

---

## 🔄 Common AWS Services Used with EKS

| Service 🔧 | What It Does for EKS |
|-----------|------------------------|
| **VPC** | Networking and isolation for pods |
| **EBS** | Persistent storage (e.g., databases) |
| **EFS** | Shared file storage across pods |
| **IAM** | Access control for users and pods |
| **ELB (ALB/NLB)** | External/internal traffic to apps |
| **CloudWatch** | Logs, metrics, alarms |
| **Secrets Manager** | Store credentials securely |
| **RDS / Aurora** | Databases for your apps |
| **App Mesh** | Service mesh integration |
| **X-Ray** | Trace microservice calls |

---

## ⚙️ EKS Cluster Creation: Tools Comparison

| Tool 💻 | Control Level | Best For |
|--------|----------------|----------|
| `eksctl` | Medium (auto setups) | Fast, production-ready setup |
| AWS Console | Low | Beginners, demos |
| AWS CLI | High | Scripting, custom setups |
| CloudFormation / CDK / Terraform | Full | Infra-as-code, large teams |

> ✨ **Recommendation:** Use `eksctl` for fast, production-ready clusters.

---

## 🌟 Why is eksctl the Go-To Tool for EKS?

- ⚡ Fast & Easy Setup: With just one command, you can spin up a fully working EKS cluster — no need to manually configure VPCs, IAM roles, or node groups.
```bash
    eksctl create cluster --name my-cluster --region us-east-1
```
- 🧾 YAML Support: Define your entire cluster setup in a config file for consistency and easy reuse.

- ✅ AWS-Backed: Built by Weaveworks and officially recommended by AWS — made specifically for EKS.

- 🔄 Simplified Node Management: Easily create and manage both managed and self-managed node groups.

- ⏱️ Time-Saving: Much faster than manually setting up with the AWS Console, kubectl, or CloudFormation.

---

| **Command** | **Brief Description** |
|------------|----------------------|
| `eksctl create cluster` | Creates an EKS cluster with a default configuration, including one node group with two `m5.large` nodes. |
| `eksctl create cluster --name <name> --version 1.31 --node-type t3.micro --nodes 2` | Creates an EKS cluster with Kubernetes version `1.31`, using a node group with two `t3.micro` nodes. |
| `eksctl create cluster --name <name> --fargate` | Creates an EKS cluster with **Fargate**, enabling serverless compute for running pods without managing worker nodes. |


## eksctl & kubectl

| **Feature**  | **eksctl** | **kubectl** |
|-------------|-----------|------------|
| **Purpose**  | Used for creating and managing **EKS** clusters and node groups. | Used for managing Kubernetes workloads and resources (pods, deployments, services, etc.) across **any** Kubernetes cluster. |
| **Scope**    | **Only for Amazon EKS**. | Works with **all** Kubernetes clusters, including EKS, self-managed, and other cloud K8s providers. |
| **Functionality** | Automates EKS cluster setup, including VPC, IAM, and node groups. | Interacts with the Kubernetes API to deploy, manage, and inspect resources. |


### **Prerequisites to Install `eksctl`**  

1. **AWS CLI** – Must be installed and configured with credentials (`aws configure`).  
2. **kubectl** – Required for managing Kubernetes resources after cluster creation.  
3. **AWS IAM Permissions** – User must have IAM permissions to create and manage EKS clusters.  
