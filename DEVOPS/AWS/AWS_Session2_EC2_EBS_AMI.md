# 🧠 EC2 + Elastic IP + EBS + AMIs 

---

## 🚀 Amazon EC2 – Elastic Compute Cloud

### 💡 What is EC2?
Amazon EC2 provides **resizable virtual servers** (instances) to run applications in the cloud.
- Can run Linux or Windows OS
- Scalable: vertical and horizontal scaling
- Integrates with most AWS services (S3, RDS, CloudWatch, etc.)
- Enables highly available and fault-tolerant deployments

---

## 🧩 EC2 Instance Types & Pricing Models

### 📦 EC2 Instance Families:
| Family | Use Case |
|--------|----------|
| **t** (e.g., t2.micro) | General purpose |
| **m** | Balanced compute/memory |
| **c** | Compute optimized |
| **r** | Memory optimized |
| **g/p/inf** | GPU/accelerated workloads |
| **d/h/i** | Storage optimized workloads |
| **x/z** | High memory enterprise workloads |

### 💸 EC2 Pricing Models:
| Model | Description | Use Case |
|-------|-------------|----------|
| **On-Demand** | Pay per hour/second | Dev/Test |
| **Reserved** | 1 or 3-year commitment | Long-term workloads |
| **Spot** | Use unused capacity at discount | Batch, fault-tolerant jobs |
| **Savings Plan** | Flexible pricing for 1-3 years | Cost-efficient, flexible |
| **Dedicated Hosts** | Physical server for regulatory needs | Licensing/Compliance |

🧠 **Real-World Example:**
> A startup launches their web app using **t2.micro** on-demand during early testing. As traffic grows, they shift to **Reserved Instances** to save cost. For AI training, they use **p3.2xlarge Spot Instances**.

---

## 🌐 Elastic IP + 🔐 Security Groups + 🔑 Key Pairs

### 🌍 Elastic IP:
- A **static IPv4 address** for dynamic cloud hosting
- Can be remapped between instances
- Allocated to your account until released
- Only one free Elastic IP per running instance

💡 Use when you want a **fixed public IP** (e.g., DNS mappings, reboots)

### 🔐 Security Groups:
- Virtual firewall to control **inbound/outbound traffic**
- Stateless: need separate rules for inbound and outbound
- Can associate multiple EC2s with one security group
- Rules are evaluated as **allow-only** (default deny)

### 🔑 Key Pairs:
- SSH Key (private/public) used to log in to EC2
- Must be downloaded at creation – can’t be retrieved again
- You can create using EC2 or import your own key

🧠 **Real-World Example:**
> You host a Node.js app and need to SSH into EC2 regularly. You use a Key Pair to access it and a Security Group to allow only port 22 and 3000. Elastic IP ensures consistent connection.

---

## 💾 Amazon EBS – Elastic Block Store

### 📂 EBS Volumes:
- Durable block storage that persists beyond EC2 lifecycle
- Can attach/detach volumes to/from instances in same AZ
- **Types:** gp3 (default), io1/io2 (high IOPS), st1/sc1 (throughput optimized)
- Auto replication within AZ for durability

### 📸 Snapshots:
- Backup of EBS volumes stored in S3
- Incremental: only changes saved after first snapshot
- Can automate via **Amazon Data Lifecycle Manager**

### 🔒 Encryption:
- Default or custom KMS key
- Encryption covers data at rest, in transit, and snapshots
- Automatically encrypts new volumes and snapshots

🧠 **Real-World Example:**
> Your app stores uploaded files. You use a separate **EBS volume** for storage, take **snapshots nightly**, and encrypt volumes for compliance.

---

## 🧪 AMI – Amazon Machine Images

### 🏗️ Custom AMI:
- Pre-installed software + configuration
- Reuse across multiple EC2 instances
- Save time when launching similar environments
- Backup your fully configured EC2 for disaster recovery

🎯 Use Case:
- Scale EC2 quickly with pre-configured environments
- Create golden images for web/app/database servers
- Backup and restore environments

🧠 **Real-World Example:**
> Your dev team installs LAMP stack on EC2. Once tested, you create an AMI and use it to launch 10+ similar EC2s across Regions for global deployment.

---

## 📜 EC2 Metadata & User Data Scripts

### 🔎 Metadata:
- Info about the instance (e.g., instance-id, IP, hostname)
- Access with: `http://169.254.169.254/latest/meta-data/`
- Useful for automation tools and custom scripts

### ⚙️ User Data:
- Bash script runs **on first boot** (only once)
- Automate package installation, updates, and software configuration
- Can be base64 encoded or plaintext

🧠 **Real-World Example:**
> To automate setup of an Apache web server:
```bash
#!/bin/bash
yum update -y
yum install -y httpd
systemctl start httpd
systemctl enable httpd
echo "<h1>Deployed via User Data</h1>" > /var/www/html/index.html
```

---

## 🛠️ Hands-On Labs

### 🔹 1. Launch EC2 with Custom User-Data Script
- Use Amazon Linux 2
- Add the Apache install script as user-data
- Set Security Group to allow HTTP (port 80)
- Access via browser using EC2 Public IP or Elastic IP

### 🔹 2. Create and Attach EBS Volume
- Create volume in same AZ
- Attach to EC2 instance
- Format using `mkfs -t xfs /dev/xvdf`
- Mount to `/data`
- Persist mount using `/etc/fstab`

### 🔹 3. Take Snapshot & Create Custom AMI
- Go to EC2 → Volumes → Take Snapshot
- Use Snapshot to create a new Volume
- Or create AMI directly from running instance (includes EBS)
- Launch new EC2 using your custom AMI

### 🔹 4. Allocate and Associate Elastic IP
- Go to EC2 → Elastic IPs → Allocate New Address
- Associate with your instance
- Update DNS record to map domain to Elastic IP

---

## ✅ Summary Checklist

| ✅ Task | Covered |
|--------|---------|
| EC2 Types & Pricing | ✔️ |
| Elastic IP, SG, Key Pair | ✔️ |
| EBS + Snapshots + Encryption | ✔️ |
| Custom AMIs | ✔️ |
| Metadata + User Data | ✔️ |
| Hands-on EC2 + Volume + AMI | ✔️ |
| Elastic IP Hands-on | ✔️ |
| Snapshot Automation & fstab mount | ✔️ |

---

