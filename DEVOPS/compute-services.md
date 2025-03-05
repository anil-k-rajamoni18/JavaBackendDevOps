## What is AWS EC2?
- Amazon Elastic Compute Cloud (EC2) is a web service that provides scalable compute capacity in the cloud. 
- EC2 allows users to rent virtual servers (called instances) on-demand to run applications and workloads.

- Overview of AWS EC2:
    a) Scalable: EC2 instances can be easily scaled up or down based on demand.
    b) Flexible: You can choose the instance type, operating system (Linux, Windows), c) storage, and more.
    d) Pay-as-you-go: You only pay for what you use, based on the instance's usage and type.
    e) Secure: Integrated with AWS security services like IAM, VPC, and Security Groups to secure your environment.
    g) High Availability: EC2 instances can be launched in multiple regions and availability zones, ensuring high availability and fault tolerance.

Purpose of AWS EC2:
    Web hosting: Hosting websites and web applications.
    Data processing: Running large-scale data analytics and machine learning models.
    App Hosting: Hosting backend services, APIs, or databases.
    Development & Testing: Developers can create isolated environments for testing applications.


#### Steps to Create an EC2 Instance (Step-by-Step)
Step 1: Log into AWS Management Console

    Go to the AWS Management Console and log in.

Step 2: Navigate to EC2 Dashboard

    In the AWS Console, search for EC2 in the search bar and select EC2 to open the EC2 Dashboard.

Step 3: Launch Instance

    Click on the Launch Instance button to start creating a new EC2 instance.

Step 4: Choose an Amazon Machine Image (AMI)

    Select an AMI (pre-configured OS image):
        Choose a Free Tier eligible image like Amazon Linux 2 or Ubuntu for the free tier if eligible.

Step 5: Choose an Instance Type

    Select the instance type (e.g., t2.micro for free tier).
        t2.micro is eligible for the AWS Free Tier.
    Click Next: Configure Instance Details.

Step 6: Configure Instance

    Configure your instance settings:
        For most use cases, you can leave the default options.
        Optionally, you can add extra configurations like networking settings or IAM roles.

Step 7: Add Storage

    AWS provides default storage (e.g., 8GB) for your instance.
    You can modify or add more storage if needed (e.g., add EBS volumes).

Step 8: Add Tags

    (Optional) Add tags to easily identify and manage your EC2 instance, such as Name = MyFirstEC2.

Step 9: Configure Security Group

    A Security Group acts as a firewall to control traffic to your instance.
        Either create a new security group or use an existing one.
        Make sure to add a rule to allow SSH (port 22) for Linux or RDP (port 3389) for Windows.

Step 10: Review and Launch

    Review all your settings.
    Click on Launch to proceed.

Step 11: Select Key Pair

    Create or select an existing Key Pair (to securely access your instance).
        If you create a new key pair, download it and save it securely (you won't be able to download it again).
    Acknowledge that you have access to the private key and click Launch Instances.

Step 12: Access EC2 Instance

    After the instance is launched, go to the Instances section.
    Wait until the Instance State says "running" and the Status Check is "2/2 checks passed."
    Get the Public IP or Public DNS from the instance details.
    Use SSH (for Linux) or RDP (for Windows) to connect to your instance.
