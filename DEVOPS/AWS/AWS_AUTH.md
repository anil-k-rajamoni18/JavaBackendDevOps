## 🔹 What is AWS CLI?

- AWS CLI (Command Line Interface) is a tool that lets you manage your AWS services using terminal or command-line commands, instead of clicking through the AWS Management Console (web UI).
- It interacts with AWS services by calling their APIs.
- You can script and automate tasks such as launching instances, uploading to S3, and more.

### 🔸 Why Use AWS CLI?
- ✅ Scriptability – Automate repetitive tasks
- ✅ Speed – Faster than clicking through the console
- ✅ Infrastructure-as-code – Works well with CI/CD and DevOps workflows
- ✅ Consistency – Same commands across environments


### 🔹 Usage Scenarios
- Launch, manage, and terminate EC2 instances
- Upload/download files to/from S3 buckets
- Manage IAM users and roles
- Deploy CloudFormation stacks
- Monitor services with CloudWatch
- Set up networking (VPCs, subnets, etc.)


### 🔷 How to Install AWS CLI

**Installation guide**: https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html


### 🔷 How to Configure AWS CLI
- Run
```bash
aws configure
```
- It will prompt:
```
AWS Access Key ID [None]: AKIA...
AWS Secret Access Key [None]: ***********
Default region name [None]: us-east-1
Default output format [None]: json
```
- These credentials are stored in:
    - ~/.aws/credentials (on Linux/macOS)
    - %UserProfile%\.aws\credentials (on Windows)

- 🔐 You can generate access keys from:
    AWS Console → IAM → Users → [Your User] → Security credentials → Create access key


## Commands 

#### 🔹 STS (Security Token Service)
```bash
aws sts get-caller-identity                # Verify your IAM identity
```

#### 🔹 S3 (Simple Storage Service)
```bash
aws s3 ls                                  # List all buckets
aws s3 ls s3://my-bucket                   # List contents of a bucket
aws s3 cp file.txt s3://my-bucket/         # Upload file to bucket
aws s3 cp s3://my-bucket/file.txt .        # Download file from bucket
aws s3 sync . s3://my-bucket/              # Sync local directory to bucket
```

#### 🔹 EC2 (Elastic Compute Cloud)
```bash
aws ec2 describe-instances                 # List all EC2 instances
aws ec2 start-instances --instance-ids i-xxxxxxxx
aws ec2 stop-instances --instance-ids i-xxxxxxxx
aws ec2 terminate-instances --instance-ids i-xxxxxxxx
```

#### 🔹 IAM (Identity and Access Management)
```bash
aws iam list-users                         # List IAM users
aws iam create-user --user-name myuser     # Create a new IAM user
aws iam list-roles                         # List IAM roles
```

#### 🔹 CloudWatch (Monitoring)
```bash
aws cloudwatch list-metrics                # List available metrics
aws cloudwatch get-metric-statistics       # Retrieve specific metric data
```

#### 🔹 CloudFormation
```bash
aws cloudformation deploy \
  --template-file template.yaml \
  --stack-name mystack \
  --capabilities CAPABILITY_IAM            # Deploy a stack

aws cloudformation describe-stacks         # List stacks and their statuses
```

#### 🐳 Amazon ECR (Elastic Container Registry)
```bash
aws ecr create-repository --repository-name my-repo
aws ecr describe-repositories
aws ecr get-login-password | docker login --username AWS --password-stdin <account-id>.dkr.ecr.<region>.amazonaws.com
aws ecr list-images --repository-name my-repo
aws ecr delete-repository --repository-name my-repo --force
```

#### ☸ Amazon EKS (Elastic Kubernetes Service)
```bash
aws eks list-clusters
aws eks create-cluster --name my-cluster --role-arn arn:aws:iam::123456789012:role/EKSRole --resources-vpc-config subnetIds=...,securityGroupIds=...
aws eks describe-cluster --name my-cluster
aws eks delete-cluster --name my-cluster
aws eks update-kubeconfig --name my-cluster
```


## Auth Types


### 🔐 1. IAM User-Based Authentication
- Uses: Long-term credentials (Access Key ID + Secret Access Key)
- Methods:
    - AWS CLI / SDK / Console login
- Suitable for: Human users, developers

### 🔐 2. IAM Role-Based Authentication
- Uses: Temporary credentials assumed via IAM roles
- Common with:
    - EC2 instance roles
    - Lambda execution roles
    - ECS/EKS task roles
- Suitable for: Applications and services

```bash
aws sts assume-role --role-arn arn:aws:iam::123456789012:role/my-role --role-session-name session1
```


### 🔐 3. IAM Identity Center (formerly AWS SSO)
- Uses: Federated login with SAML, OIDC, or direct SSO setup
- Features: Centralized identity management with Microsoft AD, Okta, Google Workspace, etc.
- Suitable for: Enterprises managing many users


### 🔐 4. Temporary Security Credentials (STS)
- Uses: AWS Security Token Service (STS) to grant time-limited access

Examples:
    - assume-role
    - assume-role-with-saml
    - assume-role-with-web-identity (for mobile/web apps using Amazon Cognito or OIDC providers)

### 🔐 5. MFA (Multi-Factor Authentication)
- Adds an extra layer of security for IAM users
- Methods: Virtual MFA (e.g., Google Authenticator), hardware tokens
- Often used with sts get-session-token

### 🔐 6. Amazon Cognito
- Uses: Authentication for web and mobile apps
- Supports:
    - Social identity providers (Facebook, Google)
    - SAML/OpenID Connect
    - User pools and identity pools

### 🔐 7. External Identity Providers (SAML/OIDC)
- Uses: Federation from third-party identity systems
- Examples: Azure AD, Okta, Google Workspace, Auth0
- Methods: IAM roles with SAML or Web Identity Federation

### 🔐 8. Resource-Based Policies with Anonymous or Federated Access
- S3 buckets and Lambda functions can allow:
    - Public (anonymous) access
    - Cross-account roles or federated principals



## 🔷 What is AWS OIDC (OpenID Connect) Authentication?
AWS OIDC (OpenID Connect) is a way to authenticate users or workloads using a federated identity provider (IdP) that supports the OpenID Connect (OIDC) protocol, such as:
    - Google
    - GitHub
    - Azure AD
    - Okta
    - Auth0
    - Amazon Cognito

- AWS supports OIDC primarily for:
    - 🔐 Federating external users or services to access AWS
    - ☸ Secure authentication for Kubernetes workloads on EKS

### 🔸 Use Cases of AWS OIDC
**1. 🚀 OIDC with EKS (IRSA – IAM Roles for Service Accounts)**
- Kubernetes service accounts in EKS can assume IAM roles securely via OIDC without needing AWS credentials inside the container.
- The EKS cluster is associated with an OIDC identity provider.
- 🔧 Example:
```yaml
serviceAccount:
  annotations:
    eks.amazonaws.com/role-arn: arn:aws:iam::123456789012:role/MyPodRole
```

**2. 🔑 OIDC Federation for Users or CI/CD Systems**
- GitHub Actions, Bitbucket, or other CI systems can use OIDC to authenticate to AWS without storing secrets.
- Example Flow 
    - GitHub Action presents a JWT from its OIDC provider.
    - AWS STS validates the JWT and issues temporary credentials.
    - GitHub gains access to AWS resources securely.


### 🔸 How It Works
- The IdP (e.g., GitHub or Google) issues a JWT token after authentication.
- The token is sent to AWS STS using assume-role-with-web-identity.
- AWS validates the token against a registered OIDC identity provider.
- If valid, AWS returns temporary credentials.

- IAM OIDC Identity Providers:
```bash
aws iam create-open-id-connect-provider
```

- Assume role with OIDC token:
```bash
aws sts assume-role-with-web-identity \
  --role-arn arn:aws:iam::123456789012:role/MyWebRole \
  --role-session-name web-session \
  --web-identity-token file://token.jwt
```