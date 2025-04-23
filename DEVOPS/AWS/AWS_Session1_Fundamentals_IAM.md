
# 🧠 AWS Fundamentals + IAM 

## ☁️ 1. What is Cloud & Virtualization?

### 🔍 Cloud Computing  
On-demand delivery of IT services (servers, storage, databases, networking, software) over the internet.

### ⚙️ Virtualization  
Creates virtual versions of physical components (e.g., multiple OSs on one server).

### 💼 Real-World Example:
> Imagine you own a coffee shop ☕.  
> Before cloud: you buy your own espresso machine (server), maintain it, repair it.  
> With cloud: you **rent** a perfectly working espresso machine **only when needed**.

## 🌐 2. Public vs Private Cloud

| | Public Cloud | Private Cloud |
|--|--------------|----------------|
| 💼 Ownership | AWS, Azure, GCP | Your company |
| 🔒 Control | Limited | Full |
| 💸 Cost | Low startup, scalable | High upfront |
| ⚙️ Setup | Easy | Complex |

### 💼 Real-World Example:
> A **startup** uses AWS (public) to save money.  
> A **bank** uses a private cloud for security.  
> A **hospital** may use hybrid: patient records on-prem, website on AWS.

## 🕰️ 3. Life Before the Cloud

| Old Method | Cloud |
|------------|--------|
| Buy physical servers | Launch EC2 in seconds |
| Power/cooling costs | Included |
| Manual backup | Built-in (S3, RDS) |
| Scaling = weeks | Auto Scaling in mins |

### 💼 Real-World Example:
> Netflix used to ship DVDs 📀.  
> Now, using AWS, they stream video globally 🌍 — no physical servers of their own!

## 🧭 4. AWS Overview & Global Infrastructure

### 🧱 Components:
- **Regions** (e.g., `us-east-1`)
- **AZs** (e.g., `us-east-1a`)
- **Edge Locations** for low-latency content delivery

```
           🌍 Region (us-east-1)
          ┌────────────┬────────────┐
          │   AZ-1     │   AZ-2     │
          └────┬───────┴────┬───────┘
               ▼            ▼
          📍 Edge Location (NYC, London)
```

### 💼 Real-World Example:
> AWS Region = City (e.g., Virginia)  
> AZ = Different power zones within the city  
> Edge Locations = Neighborhood corners with data caches (like mini-warehouses)

## ⭐ 5. Why AWS is the Leader

| Feature | Benefit |
|--------|---------|
| 🌍 Largest reach | 30+ Regions, 99 AZs |
| 🧠 Innovation | AI, ML, IoT, Robotics |
| 💪 Reliability | 99.999% uptime |
| 🔒 Security | Encryption, compliance |
| 💼 Enterprises | Netflix, Airbnb, NASA use AWS |

### 💼 Real-World Example:
> NASA used AWS to **stream Mars Rover footage** to millions.  
> Airbnb scales up/down using EC2, S3, Lambda based on demand.

## 🆓 6. Free Tier & Cost Tracking

### ✅ Free for 12 months:
| Service | Limit |
|--------|--------|
| EC2 | 750 hrs/month (t2.micro) |
| S3 | 5 GB |
| RDS | 750 hrs/month |
| Lambda | 1M requests |

### 💰 Best Practices:
- Enable **billing alerts**
- Use **Cost Explorer**
- Use **Budgets** to track

### 💼 Real-World Example:
> You host a **portfolio website** on a free-tier EC2 + S3.  
> Get alerts if you go over free limits using Budgets.

## 💻 7. AWS Console vs CLI

| Console | CLI |
|--------|-----|
| Web UI | Terminal |
| Visual | Scriptable |
| Best for learning | Best for automation |

### 💼 Real-World Example:
> A **DevOps engineer** automates EC2 launches using CLI.  
> A **new learner** explores via AWS Console.

## 🔐 8. IAM – Identity & Access Management

### 👤 IAM User
- One identity (DevUser, Admin)

### 👥 IAM Group
- Set of users (e.g., `DevTeam`)

### 📜 IAM Policy
- JSON doc defining permissions

### 🧰 IAM Role
- Temporary access for services

### 🚧 Permission Boundary
- Restrict how much power a user/role can have

```
  ┌────────────┐       ┌────────────┐
  │   User     │──────▶│   Group    │
  └────────────┘       └─────┬──────┘
                              ▼
                         ┌──────────┐
                         │ Policy   │
                         └──────────┘
```

### 💼 Real-World Example:
> Give an **intern IAM user** only S3 read access via policy.  
> Give **Lambda** a role to read from DynamoDB without exposing credentials.

## 🧑‍🔧 9. Hands-On Labs

### 🔹 A. Create AWS Account & Enable MFA

1. Sign up at [aws.amazon.com](https://aws.amazon.com)
2. Enable MFA for root user:
   - IAM → Security Credentials → MFA → Virtual MFA
   - Use Authenticator App

### 🔹 B. Create IAM User, Group, and Policy

```bash
aws iam create-group --group-name S3ReadGroup
aws iam attach-group-policy   --group-name S3ReadGroup   --policy-arn arn:aws:iam::aws:policy/AmazonS3ReadOnlyAccess
aws iam create-user --user-name DevUser
aws iam add-user-to-group   --user-name DevUser   --group-name S3ReadGroup
```

### 🔹 C. Configure AWS CLI

```bash
aws configure
```

- Enter Access Key & Secret
- Default Region: `us-east-1`
- Output format: `json`

### 💼 Real-World Example:
> You give your **developer** an IAM user + CLI setup so they can deploy from their local machine without full admin rights.

## ✅ Day 1 Summary Checklist

| ✅ Task | Status |
|--------|--------|
| Cloud vs Virtualization | ✔️ |
| Public vs Private Cloud | ✔️ |
| Life before AWS | ✔️ |
| AWS Regions, AZs | ✔️ |
| IAM Concepts | ✔️ |
| IAM Hands-On | ✔️ |
| CLI Setup | ✔️ |
| Real-World Context | ✔️ |

## 🚀 What’s Next?
➡️ **Day 2: EC2, S3, and Elastic Load Balancing**
