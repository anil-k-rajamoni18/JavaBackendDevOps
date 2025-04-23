
# 🧠 **VPC + Subnets + Route Tables + Internet Gateway + NAT**

## 🌐 **1. VPC Basics**

### What is a VPC (Virtual Private Cloud)?
- A **VPC** is a logically isolated network within AWS where you can launch AWS resources, such as EC2 instances, RDS databases, and Lambda functions. It is like your own private data center in the cloud.

### Key Components:
- **CIDR Block**: The range of IP addresses assigned to your VPC (e.g., `10.0.0.0/16`).
- **Private Network**: Your isolated network, with resources that can be controlled and configured independently.

---

### 💼 **Real-World Example**:
> A **company** uses VPC to host its **internal services** (databases, internal tools) in the cloud, keeping them isolated from the public internet.

---

## 🔀 **2. CIDR Ranges & Subnets**

### What is a CIDR Block?
- **CIDR (Classless Inter-Domain Routing)** specifies the size of your network. It's represented as an IP address followed by a subnet mask (e.g., `10.0.0.0/16`).

---

### Types of Subnets:

1. **Public Subnet**: Subnet that can access the **Internet** directly through an **Internet Gateway**. Used for resources like web servers.
2. **Private Subnet**: Subnet that doesn't have direct access to the Internet. Used for databases or application servers.

---

📊 **Flowchart of Subnetting**:

```
CIDR Block: 10.0.0.0/16
    |
    ▼
Subnet A: 10.0.0.0/24 (Public)
    |
    ▼
Subnet B: 10.0.1.0/24 (Private)
```

---

### 💼 **Real-World Example**:
> A **web application** has:
- A **Public Subnet** with EC2 instances running a web server, accessible via the Internet.
- A **Private Subnet** with a **database** that is not directly accessible from the Internet.

---

## 🛣️ **3. Route Tables, Internet Gateway, NAT Gateway**

### Route Tables
- **Route Tables** are used to determine where network traffic is directed. Each subnet in your VPC is associated with a route table.

### Internet Gateway (IGW)
- **Internet Gateway** is a gateway that allows instances in a **Public Subnet** to access the **Internet**.

### NAT Gateway
- **NAT (Network Address Translation)** Gateway allows instances in a **Private Subnet** to access the **Internet** while remaining **private** (no direct inbound traffic).

---

📊 **Flowchart of Routing**:

```
Internet Traffic → IGW → Public Subnet (EC2)
Private EC2 → NAT → IGW → Internet
```

---

### 💼 **Real-World Example**:
> A **website** needs:
- **Internet Gateway** for public access to the **EC2 web server** in the **Public Subnet**.
- **NAT Gateway** to allow the **Private EC2** to access software updates from the Internet.

---

## 🔐 **4. NACLs vs Security Groups**

### NACLs (Network Access Control Lists)
- NACLs are **stateless** and operate at the **subnet** level.
- They control inbound and outbound traffic to/from subnets.

### Security Groups
- **Security Groups** are **stateful** and operate at the **instance** level.
- They control inbound and outbound traffic for individual resources (like EC2 instances).

---

### 💼 **Real-World Example**:
> A **web server** may have a **Security Group** that allows HTTP/HTTPS traffic (port 80, 443). However, the **Private Subnet** where a database lives might have a **NACL** that blocks incoming traffic from the internet.

---

## 🌍 **5. DHCP Options, DNS Resolution**

### DHCP Options Set
- **DHCP Options** configure DNS settings for instances in your VPC (e.g., domain name servers, NTP servers).

### DNS Resolution
- **DNS Resolution** allows instances in your VPC to resolve domain names to IP addresses.

---

### 💼 **Real-World Example**:
> **Internal Applications** in your VPC rely on **DNS Resolution** to access backend services like databases and APIs, making it easier to refer to resources by name instead of IP address.

---

## 🕵️‍♂️ **6. Elastic IPs & Bastion Hosts**

### Elastic IP (EIP)
- An **Elastic IP** is a static, public IPv4 address designed for dynamic cloud computing. It's useful for instances that need a fixed, publicly accessible IP.

### Bastion Host
- A **Bastion Host** is an EC2 instance used to securely connect to **private instances** in your VPC. It acts as a "jump" host that you can SSH into before accessing private resources.

---

### 💼 **Real-World Example**:
> A **company** uses a **Bastion Host** to securely connect to a **Private EC2 instance** in a **Private Subnet** without exposing it directly to the Internet.

---

## 🧑‍💻 **7. Hands-On Labs**

### 🔹 A. Create a Custom VPC with 2 Subnets

1. Go to **VPC Dashboard** → **Create VPC**.
2. Assign a CIDR block (e.g., `10.0.0.0/16`).
3. Create a **Public Subnet** (`10.0.0.0/24`) and a **Private Subnet** (`10.0.1.0/24`).
4. Create an **Internet Gateway** and attach it to your VPC.

### 🔹 B. Launch EC2s in Public/Private Subnets

1. Launch an EC2 instance in the **Public Subnet**.
2. Launch another EC2 instance in the **Private Subnet**.
3. Associate the **Public EC2** with a **Security Group** that allows HTTP/SSH traffic.

### 🔹 C. Access Private EC2 Using Bastion from Public Subnet

1. SSH into the **Public EC2** (your Bastion Host).
2. From the Bastion Host, SSH into the **Private EC2** using its private IP.

---

## ✅ **Session 4 Summary Checklist**

| ✅ Task | Status |
|--------|--------|
| Created Custom VPC | ✔️ |
| Launched EC2 in Public Subnet | ✔️ |
| Launched EC2 in Private Subnet | ✔️ |
| Accessed Private EC2 via Bastion | ✔️ |

---

## 🚀 **What’s Next?**
➡️ **Session 5: ELB, Auto Scaling, and High Availability**
