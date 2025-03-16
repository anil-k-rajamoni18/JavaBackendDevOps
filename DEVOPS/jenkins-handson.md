## 1. Introduction to Jenkins
- Jenkins is an open-source automation tool that helps developers automate tasks related to building, testing, and deploying software.

- It’s widely used for Continuous Integration (CI) and Continuous Delivery (CD), meaning it helps automate the process of integrating code into a shared repository and then delivering it to production.

Why use Jenkins?

    - Automation: Jenkins automates repetitive tasks like running tests, building software, and deploying it.
    
    - Faster Development: It helps developers focus on writing code instead of manually performing tasks like testing and deployment.
    
    - Error Reduction: By automating the process, Jenkins helps reduce human error and ensures consistency in the process.

Jenkins History
    2004: Hudson was created by Kohsuke Kawaguchi at Sun Microsystems as an automation tool for software development.
    2010: Hudson and Oracle had disagreements, leading to a fork.
    2011: Jenkins was born from the fork, with Kohsuke and others continuing the project.
    2016: The Jenkins Foundation was formed to manage Jenkins as an open-source community-driven project.
    2017 - Present: Jenkins grew rapidly with features like Pipelines and Blue Ocean, supporting modern DevOps practices and integrations with cloud technologies.


### 2. Jenkins Installation
How to Install Jenkins (local machine)?
1.Install Java: Jenkins requires Java, so ensure you have Java 8 or later installed on your system.
        Check Java version: java -version
2. Download Jenkins:
        Go to the Jenkins website and download the appropriate version for your operating system.
        Link: https://www.jenkins.io/doc/book/installing/
3. Run Jenkins:
        After downloading, run the Jenkins installer, which will automatically start Jenkins as a service.
4. Access Jenkins:
        Open your browser and go to http://localhost:8080. You’ll see the Jenkins dashboard.


#### How to Install Jenkins on an EC2 Ubuntu Machine (Step-by-Step)
1. Launch EC2 Instance:
    Launch an Ubuntu instance on AWS EC2 and connect to it via SSH.

2. Update System Packages:
    SSH into your EC2 instance.
    Update the package list:
        sudo apt update

3. Install Java (Jenkins Requirement):
    Jenkins requires Java, so install it:
            sudo apt install openjdk-17-jdk -y
    Verify the Java installation:
            java -version

4. Add Jenkins Repository and Key:
    Add the Jenkins repository to the system & Add the Jenkins package repository to your sources list:
        sudo wget -O /usr/share/keyrings/jenkins-keyring.asc \
        https://pkg.jenkins.io/debian-stable/jenkins.io-2023.key
        echo "deb [signed-by=/usr/share/keyrings/jenkins-keyring.asc]" \
        https://pkg.jenkins.io/debian-stable binary/ | sudo tee \
        /etc/apt/sources.list.d/jenkins.list > /dev/null

5. Install Jenkins:
    Update package lists and install Jenkins:
        sudo apt update
        sudo apt install jenkins -y

6. Start Jenkins Service:
    Start the Jenkins service:
        sudo systemctl start jenkins

   Enable Jenkins to start on boot:
        sudo systemctl enable jenkins

7. Open Jenkins in Browser:
    Find the Jenkins default port (8080) and open it in your browser:
        http://<EC2_Public_IP>:8080
        
8. Unlock Jenkins:
    Get the Jenkins unlock key from the file:
        sudo cat /var/lib/jenkins/secrets/initialAdminPassword

9. Install Suggested Plugins:
    After unlocking, follow the setup wizard.
    Choose to install suggested plugins (this installs common plugins for Jenkins).

10. Create an Admin User:
    Create an admin username and password for Jenkins to complete the setup.

### Docker Slave Configuration
1. Run the below command to Install Docker
    sudo apt update
    sudo apt install docker.io

2. Grant Jenkins user and Ubuntu user permission to docker deamon.
    sudo su - 
    usermod -aG docker jenkins
    usermod -aG docker ubuntu
    systemctl restart docker

3. Once you are done with the above steps, it is better to restart Jenkins.
    http://<ec2-instance-public-ip>:8080/restart

4. Install the docker pipeline plugin in Plugin section. 
    Manage Jenkins > Plugins > Available Plugins > Search docker pieline 
    Restart the jenkins 
    The docker agent configuration is now successful.


### Continuous Integration (CI):
- Continuous Integration (CI) is a practice where developers frequently merge their code changes into a shared repository (like GitHub).
- Each change is automatically tested to ensure it works well with the existing code.
- Why is CI Important?
    Catches bugs early.
    Reduces integration problems.
    Encourages teamwork and faster development.

### Continuous Delivery (CD):
- Continuous Delivery (CD) is the process of automatically preparing software for release after it passes all tests. 
- It ensures the software is always ready to be deployed to users.
- Why is CD Important?
    Faster delivery of new features.
    Reduces manual errors in deployment.
    Makes software updates reliable and predictable.

### Role of Jenkins in DevOps:
- DevOps is a culture and set of practices that bring together development (Dev) and operations (Ops) teams to work collaboratively. 
- It focuses on automating processes to deliver software faster and more reliably.

- Jenkins in DevOps:
    Jenkins is a tool that automates CI/CD pipelines.
    It helps developers and operations teams work together by:
        Automating code integration, testing, and deployment.
        Providing feedback on the health of the software.

### Jenkins Architecture (Master-Agent)
    Master:
        The central brain of Jenkins.
        Manages all jobs, pipelines, and configurations.
        Schedules tasks and monitors the system.

    Agent (Node):
        A worker machine that executes tasks assigned by the Master.
        Can run on the same machine as the Master or on separate machines.
        Helps distribute the workload.

    Why Use Master-Agent?
        Scalability: Multiple agents can handle more tasks.
        Flexibility: Agents can run on different operating systems or environments.

### How Jenkins Works:

    Create a Job/Pipeline:
        A job is a task Jenkins performs (e.g., build, test, deploy).
        A pipeline is a series of jobs grouped together.

    Fetch Code:
        Jenkins pulls the latest code from a version control system (e.g., GitHub).

    Build and Test:
        Jenkins compiles the code (build) and runs tests to check for errors.

    Report Results:
        Jenkins provides feedback on whether the build and tests were successful.

    Deploy (Optional):
        If everything is successful, Jenkins can deploy the software to a server.

Key Terms to Remember
    CI: Continuous Integration (frequent code merging and testing).
    CD: Continuous Delivery (automated software release).
    DevOps: Collaboration between development and operations teams.
    Master: The central controller in Jenkins.
    Agent: A worker machine that executes tasks.
    Pipeline: A series of automated steps (e.g., build, test, deploy).

## Jenkins Dashboard Overview
1. Navigating the Jenkins Dashboard

    Accessing the Dashboard:
        Open your browser and go to the Jenkins URL (e.g., http://localhost:8080).
        Log in with your credentials (username/password).

    Dashboard Layout:
        The dashboard is the main screen where you can manage and monitor all Jenkins activities.
        It has a sidebar on the left and a main panel in the center.

2. Key Components of the Jenkins Dashboard

a. Jobs (Projects)

    What is a Job?
        A job is a task or project that Jenkins performs, such as building, testing, or deploying software.
    Types of Jobs:
        Freestyle Project: A simple job where you define steps manually.
        Pipeline: A more advanced job where you define steps using a script (Jenkinsfile).

    Where to Find Jobs:
        Jobs are listed in the main panel of the dashboard.
        You can create, edit, or delete jobs from here.

b. Builds

    What is a Build?
        A build is an instance of a job being executed. For example, if a job is to compile code, a build is the process of compiling it.

    Build Status:
        Success: The build completed without errors.
        Failure: The build encountered errors.
        Unstable: The build completed but with some issues (e.g., failing tests).

    Where to Find Builds:
        Click on a job to see its build history.
        Each build has a number (e.g., #1, #2) and a status icon.

c. Nodes (Agents)

    What is a Node?
        A node is a machine (physical or virtual) that executes jobs. It can be the Master (main Jenkins server) or an Agent (worker machine).

    Where to Find Nodes:
        Go to Manage Jenkins > Manage Nodes and Clouds.
        Here, you can see all nodes and their status (online/offline).

d. Plugins

    What is a Plugin?
        A plugin is an add-on that extends Jenkins' functionality (e.g., Git integration, Docker support).

    Where to Find Plugins:
        Go to Manage Jenkins > Manage Plugins.
        Here, you can install, update, or remove plugins.

e. Build Queue

    What is the Build Queue?
        The build queue shows jobs that are waiting to be executed (e.g., if all nodes are busy).

    Where to Find the Build Queue:
        It’s displayed on the left sidebar of the dashboard.

f. Build Executor Status

    What is a Build Executor?
        A build executor is a slot on a node that runs a build.

    Where to Find Build Executor Status:
        It’s displayed on the left sidebar of the dashboard.
        Shows how many executors are busy or idle.

g. User Interface (UI) Features

    Search Bar:
        Use the search bar at the top to quickly find jobs, builds, or nodes.

    New Item:
        Click New Item on the left sidebar to create a new job.

    Build History:
        View the history of builds for a specific job by clicking on the job name.

    Console Output:
        Click on a build to see its console output, which shows detailed logs of what happened during the build.


Key Terms:
    Job: A task or project in Jenkins.
    Build: An instance of a job being executed.
    Node: A machine that runs jobs (Master or Agent).
    Plugin: An add-on to extend Jenkins' features.
    Build Queue: List of jobs waiting to run.
    Build Executor: Slot on a node that runs a build.


### Freestyle 
- Freestyle projects are the simplest and most flexible type of Jenkins job.
- They are configured entirely through the Jenkins web interface.
- Suitable for small to medium-sized projects or simple automation tasks.

=> Use Cases for Freestyle Projects
    Building and testing code.
    Running scripts or commands.
    Deploying applications.
    Automating repetitive tasks.

=> Features of Freestyle Projects
1. Source Code Management (SCM):

    Integrate with version control systems like Git, SVN, or Mercurial.
    Example: Pull code from a GitHub repository.

2. Build Triggers:

    Automatically trigger builds based on events like:
    SCM changes (e.g., new commit).
    Scheduled builds (e.g., every night at 2 AM).
    Manual triggers.

3. Build Environment:

    Set up environment variables.
    Delete workspace before build starts.
    Add timestamps to the console output.

4. Build Steps:

    Execute shell commands, Windows batch commands, or scripts.
    Invoke Ant, Maven, or Gradle builds.
    Example: Run mvn clean install to build a Maven project.

5. Post-Build Actions:

    Archive artifacts (e.g., JAR, WAR files).
    Publish test results (e.g., JUnit reports).
    Send notifications (e.g., email, Slack).
    Trigger other projects.

6. Parameterized Builds:
    Allow users to input parameters (e.g., version number, environment) when triggering a build.

7. Workspace Management:

    Jenkins provides a dedicated workspace for each project where the code is checked out and builds are executed.



=> Cons of Freestyle Projects
    1. Limited Scalability
    2. Less Reproducible
    3. Manual Configuration
    4. Less Flexible Than Pipelines
    5. Maintenance Overhead


=> Comparison with Jenkins Pipelines
Freestyle Projects:
    GUI-based configuration.
    Suitable for simple tasks.
    Limited scalability.

Pipelines:
    Code-based configuration (Jenkinsfile).
    Suitable for complex workflows.
    Version-controlled and reproducible.

## Proj-1: Create the Freestyle Job

1. Log in to Jenkins:

    Open your Jenkins dashboard in a web browser.

2. Create a New Freestyle Job:

    Click on New Item on the Jenkins dashboard.
    Enter a name for your job (e.g., HelloWorld-Date-Freestyle).
    Select Freestyle project and click OK.

3. Configure the Job:
    You will be taken to the job configuration page.

4. Add a Build Step:

    Scroll down to the Build section.
    Click Add build step and select Execute shell (for Linux/macOS) or Execute Windows batch command (for Windows).

5. Write the Commands:
    For Linux/macOS:
        echo "Hello, World!"
        echo "Current Date: $(date)"

    For Windows:
        echo Hello, World!
        echo Current Date: %date% %time%

6. Save the Job:
    Scroll down and click Save.

7. Run the Job:
    On the job page, click Build Now to run the job.
    Jenkins will execute the commands and display the output in the console.

8. Check the Output:
    After the build completes, click on the build number (e.g., #1) in the Build History section.

## Proj-2: Add a Parameter for Customization

1. Go to the job configuration page.
2. In the General section, check This project is parameterized.
3. Add a String Parameter:

    Name: GREETING
    Default Value: Hello, World!

3. Modify the Execute shell or Execute Windows batch command step to use the parameter:
    For Linux/macOS:
        echo "$GREETING"
        echo "Current Date: $(date)"

    For Windows:
        echo %GREETING%
        echo Current Date: %date% %time%

### Proj-3: Run Sample java & python file 
Build Steps:
    Add a shell command to execute a Python script: python3 main.py.
    Add a shell command to execute a Java script: java Main.java.

Post-Build Actions:
    Archive the output files generated by the script.
    Send a Slack notification with the script's execution status.

### Proj-4: Building a Java Spring Application with Gradle
Step 1: Create a New Freestyle Project
    Click on New Item on the Jenkins dashboard.
    Enter a name for your project (e.g., SpringApp-Gradle-Build).
    Select Freestyle project.
    Click OK.

Step 2: Configure General Settings
    Description: Add a description for your project (e.g., "Build and test a Java Spring application using Gradle").
    Discard Old Builds: Optionally, enable this to save disk space by deleting old builds.
    GitHub Project: If your project is hosted on GitHub, you can add the project URL here (optional).

Step 3: Set Up Source Code Management (SCM)
    Select Git under the Source Code Management section.
    Enter the Repository URL (e.g., repo url).
    Add credentials if your repository is private:
    Click Add > Jenkins.
    Provide your Git username and password (or use a token for GitHub).
    Specify the branch to build (e.g., main or master).

Step 4: Configure Build Triggers
    Poll SCM: Schedule Jenkins to check for changes in the repository periodically.
    Example: H/5 * * * * (checks every 5 minutes).
    GitHub Hook Trigger: If you're using GitHub, you can set up a webhook to trigger builds automatically when code is pushed (optional).

Step 5: Set Up Build Environment
    Delete Workspace Before Build Starts: Optionally, enable this to ensure a clean workspace for each build.
    Add Timestamps to Console Output: Optionally, enable this to add timestamps to the build logs.

Step 6: Add Build Steps
    Click Add build step and select Invoke Gradle script.
    Choose Use Gradle Wrapper (recommended) or specify the Gradle installation path if not using the wrapper.
    Enter the Tasks you want Gradle to execute:
        Example: clean build (cleans the project and builds it).
    Optionally, add additional build steps:
    Execute Shell: Run custom shell commands (e.g., ./gradlew test).
    Invoke Gradle Script: Add more Gradle tasks if needed.

Step 7: Configure Post-Build Actions
    Archive the Artifacts:
        Specify the path to the generated JAR/WAR file (e.g., build/libs/*.jar).
    Publish JUnit Test Results:
        Specify the path to the test results (e.g., build/test-results/test/*.xml).
    Send Email Notifications:
        Configure email notifications to send build status updates to the team.
    Trigger Another Project:
        Optionally, trigger another Jenkins job after this build completes (e.g., a deployment job).

Step 8: Save and Run the Project
    Click Save to save your project configuration.
    Manually trigger a build by clicking Build Now.
    Monitor the build progress in the Console Output.


### Proj-5 : Sample Java Project with Webhooks

1. Create a New Freestyle Project:
    Go to Jenkins Dashboard → New Item → Enter a name (e.g., MyJavaApp-Webhooks) → Select Freestyle project → Click OK.

2. Configure Source Code Management (SCM):
    Under the Source Code Management section, select Git.
    Enter the Repository URL (e.g., https://github.com/your-username/my-java-app.git).
    Specify the branch (e.g., main or master).

3. Enable Webhooks:
    Under the Build Triggers section, check GitHub hook trigger for GITScm polling (or the equivalent for GitLab/Bitbucket).
    This ensures Jenkins will trigger a build whenever a push event is detected in the repository.

    GitHub:
        Go to your repository → Settings → Webhooks → Add webhook.
        Set the Payload URL to http://<your-jenkins-server>/github-webhook/.
        Set the Content type to application/json.
        Select the events (e.g., Just the push event).
        Click Add webhook.

    GitLab:
        Go to your repository → Settings → Webhooks.
        Set the URL to http://<your-jenkins-server>/project/<your-project-name>.
        Set the Trigger to Push events.
        Click Add webhook.

    Bitbucket:
        Go to your repository → Repository settings → Webhooks → Add webhook.
        Set the URL to http://<your-jenkins-server>/bitbucket-hook/.
        Set the Triggers to Repository push.
        Click Save.


3. Add Build Step:
    Under the Build section, click Add build step and select Invoke top-level Maven targets.
    Enter the Maven goal (e.g., clean compile).
    Save the Project:
    Click Save to apply the configuration.


## Configuring Docker agents as slave nodes to a Jenkins master
Prerequisites

1. Jenkins Master:
    Jenkins is installed and running.
    The SSH Slaves Plugin is installed (go to Manage Jenkins > Manage Plugins > Available and search for "SSH Slaves").

2. Slave Node (EC2 Instance):

    An EC2 instance is running and accessible via SSH.
    Java is installed on the EC2 instance (required for Jenkins agents).
    Ensure the EC2 instance allows SSH access from the Jenkins master (check security groups).

Steps to Add a Slave Node with SSH Authentication
1. Generate SSH Key Pair on Jenkins Master
    On the Jenkins master, generate an SSH key pair (if not already generated):
        ssh-keygen -t rsa -b 4096 -C "jenkins@master"
    
    Save the key pair in the default location (~/.ssh/id_rsa).
    Do not set a passphrase (press Enter when prompted).

    Copy the public key (id_rsa.pub):
        cat ~/.ssh/id_rsa.pub


2. Add Public Key to EC2 Instance
    Log in to the EC2 instance (slave node) via SSH:
        ssh ec2-user@<ec2-public-ip>
    Add the public key to the ~/.ssh/authorized_keys file:
        echo "<public-key>" >> ~/.ssh/authorized_keys

    Set the correct permissions for the .ssh directory and authorized_keys file:
        chmod 700 ~/.ssh
        chmod 600 ~/.ssh/authorized_keys

3. Add Slave Node in Jenkins
3.1  Go to Jenkins Dashboard:
    Click on Manage Jenkins > Manage Nodes and Clouds.

3.2 Add a New Node:
    Click New Node.
        Enter a name for the node (e.g., EC2-Slave).
        Select Permanent Agent and click OK.

3.3 Configure the Node:

    Name: EC2-Slave (or any name you prefer).
    Description: Optional description.
    Number of Executors: Set the number of concurrent builds (e.g., 2).
    Remote Root Directory: Specify a directory on the EC2 instance for Jenkins to use (e.g., /home/ec2-user/jenkins).
    Labels: Add labels to identify the node (e.g., linux, ec2).
    Usage: Select Use this node as much as possible.
    Launch Method: Select Launch agents via SSH.
    Host: Enter the public IP or DNS of the EC2 instance.
    Credentials: Click Add to create a new SSH credential:
        Kind: Select SSH Username with private key.
        Username: Enter the SSH username (e.g., ec2-user for Amazon Linux).
        Private Key: Select Enter directly and paste the private key (id_rsa) from the Jenkins master.
        Click Add.

    Select the newly created credential.

    Host Key Verification Strategy: Select Non verifying Verification Strategy (for simplicity) or Manually trusted key Verification Strategy (for better security).

4. Save the Configuration:
5. Click Save.



## What is a Pipeline?
    
    - A Pipeline in Jenkins is a suite of plugins that supports implementing and integrating continuous delivery pipelines into Jenkins.
    - It allows you to define the entire build, test, and deployment process as code (often referred to as "Pipeline as Code").
    - Pipelines are defined using a Jenkinsfile, which is a text file stored in the source code repository.


=> Difference between Scripted and Declarative Pipelines

Declarative Pipeline:
    - A more recent and simplified way to define pipelines.
    - Uses a structured, pre-defined syntax.
    - Easier to read and write, especially for beginners.
    Example:
        pipeline {
            agent any
            stages {
                stage('Build') {
                    steps {
                        echo 'Building...'
                    }
                }
            }
        }

Scripted Pipeline:
    - A more flexible and powerful way to define pipelines.
    - Uses Groovy-based syntax.
    - Better for complex workflows and custom logic.
    Example:
        node {
            stage('Build') {
                echo 'Building...'
            }
        }


### Declarative Pipeline
- Declarative Pipeline is a simplified and structured way to define CI/CD pipelines in Jenkins.
- It uses a predefined syntax, making it easier to read, write, and maintain.

=> Key Concepts of Declarative Pipeline
1. Pipeline Block:
    The pipeline block is the outermost block that defines the entire pipeline.
    Example:
        pipeline {
            // Pipeline configuration goes here
        }

2. Agent:
    The agent directive specifies where the pipeline will run (e.g., on any available agent, a specific node, or in a Docker container).
    Declarative Pipeline:
        Use agent { label 'node-label' } to specify a Jenkins slave by label.
        Use agent { docker { image 'docker-image' } } to specify a Docker container.
        use agent any # job will run on any available slave
    Scripted Pipeline:
        Use node('node-label') to specify a Jenkins slave by label.
        Use docker.image('docker-image').inside to run steps inside a Docker container.

    Example:
        agent any

3. Stages:
    The stages block contains one or more stage blocks, each representing a logical step in the pipeline (e.g., Build, Test, Deploy).
    Example:

        stages {
            stage('Build') {
                steps {
                    echo 'Building...'
                }
            }
        }

4. Steps:
    The steps block contains the actual commands or actions to be executed within a stage.
    Example:
        steps {
            echo 'Running tests...'
        }

5. Environment Variables:
    The environment directive is used to define environment variables that can be used throughout the pipeline.
    Example:
        environment {
            DEPLOY_ENV = 'production'
        }

6. Post Actions:
    The post block defines actions to be performed after the pipeline completes (e.g., success, failure, always).
    Example:
        post {
            success {
                echo 'Pipeline succeeded!'
            }
            failure {
                echo 'Pipeline failed!'
            }
        }

7. Parameters:
    The parameters directive allows you to define user-input parameters for the pipeline.
    Example:
        parameters {
            string(name: 'VERSION', defaultValue: '1.0', description: 'Version to deploy')
        }

8. Tools:
    The tools directive is used to specify tools (e.g., JDK, Maven) to be installed and used in the pipeline.
    Example:

        tools {
            maven 'Maven-3.8.6'
        }


9.  when Conditional
    - The when directive allows you to execute a stage or step conditionally based on a specified condition. 
    - It is useful for skipping stages or steps based on the pipeline's state or parameters.
    Conditions:
        branch: Execute if the branch matches a pattern.
        environment: Execute if an environment variable matches a value.\
        expression: Execute if a Groovy expression evaluates to true.
        allOf: Execute if all nested conditions are true.
        anyOf: Execute if any nested condition is true.
        not: Execute if the nested condition is false.

10.  input Step
    The input step pauses the pipeline and waits for user input. It is useful for manual approvals or parameterized deployments.
    Key Features of input
    Parameters:
        message: A message to display to the user.
        id: A unique identifier for the input step.
        ok: Custom text for the "Proceed" button.
        submitter: Restrict who can approve the input.
        parameters: Define input fields for user-provided values.

    Example:
        pipeline {
            agent any
            stages {
                stage('Build') {
                    steps {
                        echo 'Building the application...'
                    }
                }
                stage('Approval') {
                    steps {
                        input message: 'Deploy to production?', ok: 'Deploy'
                    }
                }
                stage('Deploy') {
                    steps {
                        echo 'Deploying to production...'
                    }
                }
            }
        }


#### Example 1: Basic Pipeline
- This pipeline has three stages: Build, Test, and Deploy.

    pipeline {
        agent any
        stages {
            stage('Build') {
                steps {
                    echo 'Building the application...'
                }
            }
            stage('Test') {
                steps {
                    echo 'Running tests...'
                }
            }
            stage('Deploy') {
                steps {
                    echo 'Deploying the application...'
                }
            }
        }
        post {
            success {
                echo 'Pipeline succeeded!'
            }
            failure {
                echo 'Pipeline failed!'
            }
        }
    }


#### Example 2: Pipeline with Environment Variables
- This pipeline uses environment variables to customize the build process.

    pipeline {
        agent any
        environment {
            DEPLOY_ENV = 'production'
            VERSION = '1.0.0'
        }
        stages {
            stage('Build') {
                steps {
                    echo "Building version ${VERSION} for ${DEPLOY_ENV}..."
                }
            }
            stage('Test') {
                steps {
                    echo 'Running tests...'
                }
            }
            stage('Deploy') {
                steps {
                    echo "Deploying to ${DEPLOY_ENV}..."
                }
            }
        }
    }


### Example 3: Pipeline with Parameters
 This pipeline accepts a user-defined parameter for the version to deploy.

    pipeline {
        agent any
        parameters {
            string(name: 'VERSION', defaultValue: '1.0.0', description: 'Version to deploy')
        }
        stages {
            stage('Build') {
                steps {
                    echo "Building version ${params.VERSION}..."
                }
            }
            stage('Deploy') {
                steps {
                    echo "Deploying version ${params.VERSION}..."
                }
            }
        }
    }


### Example 4: Pipeline with Tools
    This pipeline uses Maven to build a Java project.

    pipeline {
        agent {
            docker {
                image 'maven:3.8.6-jdk-11'
                args '-v /tmp:/tmp'
            }
        }
        stages {
            stage('Build') {
                steps {
                    sh 'mvn clean package'
                }
            }
        }
    }

### Example 5: Pipeline with Docker
    This pipeline runs the build inside a Docker container.

    pipeline {
        agent {
            docker {
                image 'maven:3.8.6-jdk-11'
                args '-v /tmp:/tmp'
            }
        }
        stages {
            stage('Build') {
                steps {
                    sh 'mvn clean package'
                }
            }
        }
    }


### Example-6: when conditional & input 

    pipeline {
        agent any
        environment {
            DEPLOY_ENV = 'production'
        }
        stages {
            stage('Build') {
                steps {
                    echo 'Building the application...'
                }
            }
            stage('Deploy') {
                when {
                    allOf {
                        branch 'main'
                        environment name: 'DEPLOY_ENV', value: 'production'
                    }
                }
                steps {
                    echo 'Deploying to production...'
                }
            }
        }
    }



    pipeline {
        agent any
        stages {
            stage('Build') {
                steps {
                    echo 'Building the application...'
                }
            }
            stage('Approval') {
                steps {
                    script {
                        def userInput = input(
                            id: 'userInput',
                            message: 'Provide deployment details',
                            parameters: [
                                string(name: 'VERSION', defaultValue: '1.0.0', description: 'Version to deploy')
                            ]
                        )
                        env.VERSION = userInput.VERSION
                    }
                }
            }
            stage('Deploy') {
                steps {
                    echo "Deploying version ${env.VERSION}..."
                }
            }
        }
    }


    pipeline {
        agent any
        stages {
            stage('Build') {
                steps {
                    echo 'Building the application...'
                }
            }
            stage('Approval') {
                when {
                    branch 'main'
                }
                steps {
                    input message: 'Deploy to production?', ok: 'Deploy'
                }
            }
            stage('Deploy') {
                when {
                    branch 'main'
                }
                steps {
                    echo 'Deploying to production...'
                }
            }
        }
    }


### Example-7: Java Spring Boot with Gradle

    pipeline {
        agent any
        environment {
            // Define environment variables
            DEPLOY_ENV = 'staging'
            VERSION = '1.0.0'
        }
        tools {
            // Specify Gradle version (configured in Jenkins)
            gradle 'Gradle-7.4'
        }
        stages {
            stage('Build') {
                steps {
                    echo 'Building the Java Spring Boot application...'
                    sh './gradlew clean build'
                }
            }
            stage('Test') {
                steps {
                    echo 'Running unit tests...'
                    sh './gradlew test'
                }
            }
            stage('Code Quality Check') {
                steps {
                    echo 'Running code quality checks...'
                    sh './gradlew check'
                }
            }
            stage('Approval') {
                when {
                    // Only prompt for approval if deploying to production
                    environment name: 'DEPLOY_ENV', value: 'production'
                }
                steps {
                    input message: 'Deploy to production?', ok: 'Deploy'
                }
            }
            stage('Deploy') {
                when {
                    // Only deploy if the branch is main or release
                    anyOf {
                        branch 'main'
                        branch 'release/*'
                    }
                }
                steps {
                    echo "Deploying version ${VERSION} to ${DEPLOY_ENV}..."
                    sh './gradlew bootJar'
                    // Add deployment commands here (e.g., copying the JAR to a server)
                }
            }
        }
        post {
            success {
                echo 'Pipeline succeeded!'
            }
            failure {
                echo 'Pipeline failed!'
            }
        }
    }


#### Example-8: Jenkinsfile for Python
    pipeline {
        agent any
        environment {
            // Define environment variables
            DEPLOY_ENV = 'staging'
            VERSION = '1.0.0'
        }
        stages {
            stage('Setup Virtual Environment') {
                steps {
                    echo 'Setting up Python virtual environment...'
                    sh 'python3 -m venv venv'
                    sh 'source venv/bin/activate'
                }
            }
            stage('Install Dependencies') {
                steps {
                    echo 'Installing dependencies...'
                    sh 'pip install -r requirements.txt'
                }
            }
            stage('Lint') {
                steps {
                    echo 'Running linting...'
                    sh 'pylint *.py'
                }
            }
            stage('Test') {
                steps {
                    echo 'Running unit tests...'
                    sh 'pytest'
                }
            }
            stage('Approval') {
                when {
                    // Only prompt for approval if deploying to production
                    environment name: 'DEPLOY_ENV', value: 'production'
                }
                steps {
                    input message: 'Deploy to production?', ok: 'Deploy'
                }
            }
            stage('Deploy') {
                when {
                    // Only deploy if the branch is main
                    branch 'main'
                }
                steps {
                    echo "Deploying version ${VERSION} to ${DEPLOY_ENV}..."
                    // Add deployment commands here (e.g., copying files to a server)
                }
            }
        }
        post {
            success {
                echo 'Pipeline succeeded!'
            }
            failure {
                echo 'Pipeline failed!'
            }
        }
    }
