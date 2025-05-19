
# 🧠 **VPC + Subnets + Route Tables + Internet Gateway + NAT**

## Basics
### 🌐 What is an IP Address?

- 📍 IP Address (Internet Protocol Address) is a unique ID for every device on a network 🌐
- It allows devices to send and receive data over the internet or local network.
- 🔢 It's like your device's digital address so it can send/receive information.

- Example:
    - IPv4: 192.168.1.1
    - IPv6: 2001:0db8:85a3::8a2e:0370:7334

### 🧩 Types of IP Addresses

**A. By Version**

| Version  | Format                     | Example         |
| -------- | -------------------------- | --------------- |
| **IPv4** | `xxx.xxx.xxx.xxx` (32-bit) | `192.168.0.1`   |
| **IPv6** | Hexadecimal (128-bit)      | `2001:0db8:...` |

**🏠 B. By Scope**
| Type              | Description                  | Example       |
| ----------------- | ---------------------------- | ------------- |
| 🔒 **Private IP** | Used in LAN (not internet)   | `192.168.0.1` |
| 🌍 **Public IP**  | Unique, used on the internet | `8.8.8.8`     |
| 📌 **Static IP**  | Manually set, doesn’t change | Servers       |
| 🔄 **Dynamic IP** | Assigned by DHCP, changes    | Home networks |


### 📐 What is CIDR?
- 🔢 CIDR = Classless Inter-Domain Routing
- ✂️ Replaces old IP class system
- 📘 Notation: IP/prefix_length (e.g. 192.168.1.0/24)

**📊 CIDR Breakdown Diagram**
```
CIDR Notation: 192.168.1.0/24

Binary:
   192.168.1.0  = 11000000.10101000.00000001.00000000
   /24          = Network: 11000000.10101000.00000001 | Host: 00000000

Visual:
   [ Network Portion (24 bits) ] [ Host Portion (8 bits) ]
   [----------------------------][------------------------]
```

**CIDR Range Example**
- 192.168.1.0/24
- 📦 Total IPs = 2⁸ = 256
- 👨‍💻 Usable Hosts = 256 - 2 = 254
(1 for network, 1 for broadcast)

| Type          | Address         |
| ------------- | --------------- |
| 🌐 Network    | `192.168.1.0`   |
| 🧍 First Host | `192.168.1.1`   |
| 👥 Last Host  | `192.168.1.254` |
| 📣 Broadcast  | `192.168.1.255` |


**Common CIDR Ranges**
| CIDR  | Subnet Mask     | Hosts        | Usage Example   |
| ----- | --------------- | ------------ | --------------- |
| `/30` | 255.255.255.252 | 2 hosts      | Point-to-point  |
| `/24` | 255.255.255.0   | 254 hosts    | Typical LAN     |
| `/16` | 255.255.0.0     | 65,534 hosts | Large network   |
| `/8`  | 255.0.0.0       | 16.7 million | Very large orgs |


### 🧮 Visual Subnet Calculator (CIDR Range & Hosts)
| CIDR  | Subnet Mask     | # of IPs | Usable Hosts | Wildcard Mask | Host Range                    |
| ----- | --------------- | -------- | ------------ | ------------- | ----------------------------- |
| `/30` | 255.255.255.252 | 4        | 2            | 0.0.0.3       | 192.168.1.1 – 192.168.1.2     |
| `/29` | 255.255.255.248 | 8        | 6            | 0.0.0.7       | 192.168.1.1 – 192.168.1.6     |
| `/28` | 255.255.255.240 | 16       | 14           | 0.0.0.15      | 192.168.1.1 – 192.168.1.14    |
| `/27` | 255.255.255.224 | 32       | 30           | 0.0.0.31      | 192.168.1.1 – 192.168.1.30    |
| `/24` | 255.255.255.0   | 256      | 254          | 0.0.0.255     | 192.168.1.1 – 192.168.1.254   |
| `/16` | 255.255.0.0     | 65,536   | 65,534       | 0.0.255.255   | 192.168.0.1 – 192.168.255.254 |


**📝 Notes**:
- Wildcard mask = Inverse of subnet mask, used in access control lists (ACLs)
- First address = Network
- Last address = Broadcast

###  CIDR to Subnet Mask Chart
| CIDR | Subnet Mask     | IPs        | Usable     |
| ---- | --------------- | ---------- | ---------- |
| /8   | 255.0.0.0       | 16,777,216 | 16,777,214 |
| /16  | 255.255.0.0     | 65,536     | 65,534     |
| /24  | 255.255.255.0   | 256        | 254        |
| /25  | 255.255.255.128 | 128        | 126        |
| /26  | 255.255.255.192 | 64         | 62         |
| /27  | 255.255.255.224 | 32         | 30         |
| /28  | 255.255.255.240 | 16         | 14         |
| /29  | 255.255.255.248 | 8          | 6          |
| /30  | 255.255.255.252 | 4          | 2          |

### 🌐 Real-World Use Cases
| Use Case                    | CIDR        | Description                                   |
| --------------------------- | ----------- | --------------------------------------------- |
| 🏠 Home network             | `/24`       | Simple LANs with routers and Wi-Fi devices    |
| 🏢 Small office             | `/25 – /26` | 30–120 devices like PCs, printers, phones     |
| 🛣️ Point-to-Point WAN Link | `/30`       | 2 usable IPs, used for router-to-router links |
| 🏭 Enterprise               | `/16`       | Large networks with thousands of devices      |
| 🌍 Public Cloud / ISPs      | `/8 – /12`  | Massive allocations for internet services     |


---
## 🌐 **1. VPC Basics**

### What is a VPC (Virtual Private Cloud)?
- A **VPC** is a logically isolated network within AWS where you can launch AWS resources, such as EC2 instances, RDS databases, and Lambda functions. 
- It is like your own private data center in the cloud.
- A VPC is your own private, isolated section of the cloud (like AWS or Azure) where you can launch and run resources (like servers, databases, etc.).

- 🧱 Think of it as your own fenced-off area inside a large data center — completely controlled by you.

### 🛠️ Why Do We Need a VPC?
- Security 🔐: Only you control who can access what.
- Customization ⚙️: You choose the IP range, subnets, route rules, gateways, etc.
- Isolation 🧍: Your apps don’t mix with others in the cloud.
- Scalability 🚀: You can grow and shrink as needed.

### 🏠 Real-world Analogy
- Imagine the cloud provider (like AWS) is a giant apartment complex.

- A VPC is like your own private apartment:
    - You control the locks (security)
    - You decide how to arrange rooms (subnets)
    - You choose where the doors and windows are (gateways)
    - You can invite or block people (access rules)

- 🌐 Public Subnet = Living Room
    - Anyone you invite (internet users) can enter. You place your web servers here.

- 🔒 Private Subnet = Bedroom
    - Only you and trusted people can enter. You keep databases and backend APIs here.

- Your VPC = A private building you rent inside that city
- Subnets = Rooms in your building (some public, some private)
- Internet Gateway = Your front door — allows visitors in
- Route Table = Signboards in hallways showing where to go
- Security Groups = Door locks for each room
- NAT Gateway = Back door to exit only (private rooms can access outside, but outsiders can't come in)




### Key Concepts of a VPC
| 🔑 Concept                 | 📘 What It Is                                           |
| -------------------------- | ------------------------------------------------------- |
| **CIDR Block**             | The IP address range for your VPC (e.g., `10.0.0.0/16`) |
| **Subnet**                 | A smaller network within your VPC (Public or Private)   |
| **Internet Gateway (IGW)** | Lets public subnets talk to the internet 🌍             |
| **NAT Gateway**            | Lets private subnets access internet *outbound only*    |
| **Route Table**            | Controls where network traffic goes 🚦                  |
| **Security Group**         | A virtual firewall for your resources 🔐                |
| **Network ACL (NACL)**     | Another firewall at the subnet level 🔥                 |
| **Elastic IP**             | A static IP you can assign to resources 📌              |





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
- **Route Tables** are used to determine where network traffic is directed. 
- A set of rules (routes) that determine where network traffic is directed.
- Acts like a "road map" for traffic in your VPC/subnets.
- Every VPC has a main route table (default) and can have custom route tables.
    - Each subnet must be associated with one route table.
    - Routes consist of:
        - Destination (IP range, e.g., 10.0.0.0/16 or 0.0.0.0/0 for all traffic).
        - Target (Where to send the traffic, e.g., igw-123 for an Internet Gateway).

- **Example**
```
Public Subnet Route Table:
- 10.0.0.0/16 → local (default VPC traffic)
- 0.0.0.0/0 → igw-123 (Internet Gateway)

Private Subnet Route Table (implied):
- 10.0.0.0/16 → local (no internet access by default)
```


### Internet Gateway (IGW)
- **Internet Gateway** is a gateway that allows instances in a **Public Subnet** to access the **Internet**.
- A horizontally scaled, redundant VPC component that allows communication between your VPC and the internet.
- Enables public subnets to:
    - Allow inbound traffic from the internet (e.g., users accessing your web UI).
    - Allow outbound traffic to the internet (e.g., your web server fetching updates).
- Key Details:
    - Attached to a VPC (not a subnet).
    - Used in public subnet route tables (e.g., 0.0.0.0/0 → igw-123).
    - Does not provide internet access to private subnets.

- **Example**
    - The public subnet (10.0.0.0/24) uses the IGW to allow users to access the web UI.


### NAT Gateway
- **NAT (Network Address Translation)** Gateway allows instances in a **Private Subnet** to access the **Internet** while remaining **private** (no direct inbound traffic).
-  A managed service that allows private subnets to initiate outbound internet traffic while blocking inbound traffic from the internet.

- **Purpose**: Used for private subnets (e.g., backend APIs, databases) that need to:
    - Download updates/patches.
    - Access external APIs (e.g., payment gateways).
    - But cannot be directly accessed from the internet.

- Key Details:
    - Deployed in a public subnet (requires an IGW).
    - Requires an Elastic IP (EIP).
    - Added to the private subnet’s route table (e.g., 0.0.0.0/0 → nat-123).
    - Not needed if your private resources never access the internet.

- Example 
    ```
    Private Subnet Route Table (with NAT):
    - 10.0.0.0/16 → local
    - 0.0.0.0/0 → nat-123 (NAT Gateway in the public subnet)
    ```
    This allows backend (10.0.1.0/24) to fetch data from the internet but keeps it secure from direct inbound access.

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

![img1](https://miro.medium.com/v2/resize:fit:641/1*DeUfw9fuFGCpKLwZka4UHg.jpeg)

![img1](https://media2.dev.to/dynamic/image/width=800%2Cheight=%2Cfit=scale-down%2Cgravity=auto%2Cformat=auto/https%3A%2F%2Fdev-to-uploads.s3.amazonaws.com%2Fuploads%2Farticles%2Fnti327hmr7a642l03njz.png)


![img2](https://miro.medium.com/v2/resize:fit:1400/1*f1IvD5Tokv01IgmacBCcdg.gif)
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
