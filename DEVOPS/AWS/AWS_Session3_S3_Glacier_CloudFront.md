
# 🧠 S3 + Glacier + CloudFront + Storage Classes

## ☁️ **1. S3 Buckets & Objects**

### What is S3?
- **Amazon S3 (Simple Storage Service)** is a scalable object storage service where you can store data (files) as **objects** within **buckets**.
- It's designed to store and retrieve any amount of data, from anywhere on the web, at any time.
- Amazon S3 was officially launched on March 14, 2006. 
- It was one of the first AWS services to be made publicly available and marked the beginning of Amazon's cloud services for external customers.


### 🧪 Use Cases
- Website hosting
- Data backup and disaster recovery
- Media hosting and streaming
- Big data analytics
- Static content storage for web and mobile apps

### Key Concepts:
- **Bucket**: Container for storing objects (files).
- **Object**: The actual data you upload (e.g., images, videos, documents). The files/data themselves, stored with metadata and a unique key.
- **Region**: Buckets are tied to specific AWS regions for data locality and latency.
- **Storage Classes**: Different cost/performance tiers (e.g., Standard, Glacier).

### Objects
- Fundamental entities stored in S3.
- Each object consists of:
  - **Data**
  - **Key** (unique identifier)
  - **Metadata**
  - **Version ID** (if versioning is enabled)


### 💼 **Real-World Example**:
> A photographer stores **high-resolution photos** on S3. Each photo is an **object**, and all the photos are stored in an S3 **bucket** called `photo-archive`.

---

### ✅  Types of Buckets 

#### 1. General Purpose Bucket
- What it is: The standard, traditional S3 bucket.
- Use case: Storing any type of object (files, images, videos, backups, etc.).
- Structure: Flat structure (no real folders, just keys that look like paths).
- Access: Can be private, public, versioned, encrypted, etc.
- Good for: Almost any cloud storage use case — it's the most flexible and widely used.
- *Think of it like a regular file cabinet where you put all kinds of documents.*

#### 2. Directory Bucket (Newer feature, launched in 2023)
-What it is: A special type of S3 bucket that supports strong consistency and a hierarchical namespace (more like a real file system).
- Use case: Designed for applications that need fast file lookups, such as big data workloads or cloud-native file systems.
- Key Features:
    - POSIX-like directory structure.
    - Faster access to objects in deeply nested "folders".
    - Works better with tools that expect traditional file systems.
- Good for: Analytics, AI/ML workloads, or replacing on-premise file systems.
- *Think of it like a smart folder system where the structure really matters and access is faster.*

#### 3. Amazon S3 Table Buckets 
- Used to store tabular data and metadata for analytics.
- AWS automatically maintains them to reduce storage costs.
- Supports Apache Iceberg and integrates with:
    - AWS Glue Data Catalog
    - Amazon S3 Tables Catalog
    - Iceberg REST endpoint for open-source query engines

- Private by default – access must be explicitly granted.
- Controlled using IAM policies (resource-based + identity-based).
- Unique ARN format:
```ruby
arn:aws:s3tables:Region:AccountID:bucket/bucket-name
```
- Limit: 10 table buckets per AWS Region (can request more).

---

## 🔄 **2. Versioning & Lifecycle**

### Versioning
- **Versioning** enables you to keep multiple versions of an object in a bucket.
- By enabling versioning, AWS keeps track of every update to an object.

### 💼 **Real-World Example**:
> You accidentally overwrite a file in your S3 bucket. With versioning enabled, you can easily recover the previous version.

### Lifecycle Rules
- You can automatically move objects to cheaper storage tiers or delete them after a certain period using **Lifecycle Rules**.

📊 **Flowchart of Lifecycle Rule**:

```
Object Stored in S3 (Standard)
  |
  ▼
Move to IA after 30 days
  |
  ▼
Move to Glacier after 90 days
  |
  ▼
Delete after 1 year
```

### 💼 **Real-World Example**:
> A **company** storing logs on S3 can move logs from **Standard** to **IA (Infrequent Access)** after 30 days, then to **Glacier** for archiving after 90 days.

---

## 📦 **3. Storage Classes**

### Types of S3 Storage Classes:

1. **Standard**  
   - Frequently accessed data, low latency, high throughput.
   - Suitable for data like images, videos, and backups.
  
2. **IA (Infrequent Access)**  
   - Data that is less frequently accessed but needs to be retrieved quickly when needed.
   - Suitable for backups, data archives.

3. **Glacier**  
   - Low-cost archival storage for data you rarely access.
   - Retrieval takes hours.
   - Suitable for long-term backup storage.

4. **Intelligent-Tiering**  
   - Moves data automatically between two access tiers: frequent and infrequent access, based on access patterns.
   - Ideal for datasets with unpredictable access patterns.

### 💼 **Real-World Example**:
> A **media company** stores raw video files in **Glacier**, while its active projects are stored in **Standard**. Old video projects that aren’t accessed frequently move to **IA**.

---

## 🔐 **4. Bucket Policies, ACLs, Encryption**

### Bucket Policies
- Define **permissions** at the bucket level.
- Policies can specify who can access the bucket and what actions they can perform (read, write, delete).

### ACLs (Access Control Lists)
- Specify **permissions** at the object level (e.g., public read access).

### Encryption
- **SSE-S3**: Server-side encryption using S3-managed keys.
- **SSE-KMS**: Server-side encryption using AWS Key Management Service (KMS) keys.
- **Client-Side Encryption**: Data is encrypted before it is uploaded to S3.

### 💼 **Real-World Example**:
> A **company** uses **SSE-KMS** for encrypting sensitive documents, while using a **Bucket Policy** to grant read access to its marketing team.

---

## 🌍 **5. Static Website Hosting on S3**

### Hosting Static Websites
- **S3** can be used to host **static websites** by uploading HTML, CSS, and JS files.
- Simply enable static website hosting on the bucket and configure an **index.html** and **error.html**.

### 💼 **Real-World Example**:
> A **portfolio website** of a developer can be hosted entirely on **S3** as a static website. The website consists of HTML, CSS, and JS files, served to users worldwide.

📊 **Flowchart for S3 Static Website Hosting**:

```
Upload Files (HTML, CSS, JS)
  |
  ▼
Enable Static Website Hosting on Bucket
  |
  ▼
Set Index.html & Error.html as defaults
  |
  ▼
Access Site via S3 URL
```

---

## 🌐 **6. CloudFront (CDN) for S3**

### What is CloudFront?
- **Amazon CloudFront** is a **Content Delivery Network (CDN)** that speeds up the delivery of content to users by caching copies at **Edge Locations**.

### How CloudFront works with S3:
1. CloudFront caches your S3 content at **Edge Locations** closest to the user.
2. When a user requests a file (e.g., an image), CloudFront serves the file from the nearest edge location, reducing latency.

### 💼 **Real-World Example**:
> A **news website** uses CloudFront to serve images and videos stored in **S3**. Users in New York and Tokyo receive fast loading times because the content is cached at CloudFront edge locations in those cities.

---


## 🔍 AWS S3 vs EBS vs EFS – Key Differences
| Feature            | **Amazon S3**                                                            | **Amazon EBS**                                             | **Amazon EFS**                                   |
| ------------------ | ------------------------------------------------------------------------ | ---------------------------------------------------------- | ------------------------------------------------ |
| **Type**           | Object Storage                                                           | Block Storage                                              | File Storage                                     |
| **Use Case**       | Store any amount of unstructured data like images, videos, backups, logs | Attach storage to EC2 instances (like a virtual hard disk) | Shared file system across multiple EC2 instances |
| **Data Access**    | Via web/API, SDKs, HTTP(S)                                               | Only from attached EC2 instances                           | Mountable via NFS                                |
| **Persistence**    | Persistent & independent of compute                                      | Persistent but tied to EC2 availability zone               | Persistent & shared across AZs                   |
| **Performance**    | High throughput for large files                                          | High IOPS for transactional workloads                      | Good for parallel access workloads               |
| **Storage Format** | Objects (key-value pairs)                                                | Blocks (like hard drive sectors)                           | Files and directories                            |
| **Examples**       | Backup, static website hosting, data lakes                               | Databases, file systems, boot volumes                      | Web servers, shared project files                |
| **Pricing**        | Pay per GB + requests                                                    | Pay per provisioned GB + IOPS                              | Pay per GB + throughput & IOPS                   |
| **Availability**   | Regional                                                                 | Availability Zone-specific                                 | Regional (Multi-AZ)                              |
| **Scalability**    | Automatically scales                                                     | Manual sizing                                              | Automatically scales                             |


#### 🧠 Quick Summary:
S3 is for scalable object storage, ideal for backup, media, and web content.

EBS is like a virtual hard drive, best for OS, databases, and apps requiring fast read/write.

EFS is a shared file system, useful for multi-user file access across EC2 instances.

--- 

## 🧑‍💻 **7. Hands-On Labs**

### 🔹 A. Host a Static Website on S3

1. Create an S3 Bucket (e.g., `my-static-site`).
2. Enable **Static Website Hosting** in the bucket properties.
3. Upload **index.html** and **error.html**.
4. Set permissions to **public read**.

### 🔹 B. Set up Lifecycle Rule to Move Files to Glacier

1. In the S3 bucket, navigate to **Management** → **Lifecycle rules**.
2. Create a rule to transition objects from **Standard** to **Glacier** after 90 days.

### 🔹 C. Distribute Site Using CloudFront

1. Go to **CloudFront** in the AWS Console.
2. Create a new distribution.
3. Set **S3 bucket** as the origin.
4. Deploy the distribution and get the **CloudFront URL**.

### 💼 **Real-World Example**:
> A **personal blog** is hosted on **S3**, and as traffic increases, you set up **CloudFront** to ensure users from different countries load the site quickly.

---
