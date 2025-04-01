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

### Proj-4: Building a Java SpringBoot Application with Gradle
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
            echo 'Running tests...''
        }
    
    Script:
        Use scripts when you need more flexibility and complexity such as performing conditional logics or loops or executing multiple tasks 
        stage('Build') {
            steps {
                script {
                    def buildResult = sh(returnStdOut: true, script:'echo build').trim()
                    if (buildResult == 'build'){
                        echo 'Build is Success'
                    }else {
                         echo 'Build failed'
                    }
                }
            }
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
            always {
                echo 'pipeline completed'
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


### Example-5: when conditional & input 

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


### Example-5.a

    pipeline {
        agent any
        stages {
            stage('Build') {
                steps {
                    echo 'Building the application...'
                }
            }
            stage('Approval') {
                input {
                        id 'userInput'
                        message 'Provide deployment details'
                        parameters {
                            string(name: 'VERSION', defaultValue: '1.0.0', description: 'Version to deploy')
                            choice(name: 'ENVs', choices: ['DEV', 'TEST', 'RELEASE', 'PROD'], description: 'Environment to deploy')
                        }
                }
                steps {
                    script {
                        env.DEPLOY_ENV = "${ENVs}"
                        env.DEPLOY_VERSION = "${VERSION}"
                    }
                }
            }
            stage('Deploying') {
                when {
                    allOf {
                        environment name: 'DEPLOY_ENV', value: 'PROD'
                        expression { "${env.DEPLOY_VERSION}" > '0.0.0'}   
                    }
                }
                steps {
                    echo "Deploying env ${env.DEPLOY_ENV}..."
                    echo "Deploying version ${env.DEPLOY_VERSION}..."
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


## Agents, Stages, Steps, and Post Actions,  Parallel Execution, Shared Libraries
1. Agents: Define where the pipeline runs (e.g., a specific node, Docker container, or any available agent).
Example:
    pipeline {
        agent any // Runs on any available agent
        stages {
            stage('Build') {
                steps {
                    echo 'Building the application...'
                }
            }
        }
    }    

2. Stages: Logical divisions in the pipeline (e.g., Build, Test, Deploy).
    pipeline {
        agent any
        stages {
            stage('Build') {
                steps {
                    echo 'Building...'
                }
            }
            stage('Test') {
                steps {
                    echo 'Testing...'
                }
            }
        }
    }

3. Steps: Individual tasks within a stage (e.g., running a shell command, compiling code).

    steps {
        sh 'mvn clean install' // Runs a Maven build
    }

4. Post Actions: Define actions to run after a stage or pipeline completes (e.g., success, failure).
    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
        always {
            cleanWs()
        }
    }

5. Using Environment Variables
    pipeline {
        agent any
        environment {
            APP_VERSION = '1.0.0'
            BUILD_NUMBER = currentBuild.number
        }
        stages {
            stage('Build') {
                steps {
                    echo "Building version ${env.APP_VERSION}, build number ${BUILD_NUMBER}"
                }
            }
        }
    }


6. Parallel Execution

- Execute multiple stages simultaneously to save time.
    pipeline {
        agent any
        stages {
            stage('Build and Test') {
                parallel {
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
                }
            }
        }
    }

7. Shared Libraries
- Creating Reusable Pipeline Code
- Shared libraries allow you to reuse code across multiple pipelines.

Why Use Shared Libraries?
    Code Reusability: Avoid duplication across multiple pipelines.
    Maintainability: Centralized updates to pipeline logic.
    Standardization: Enforce best practices across all jobs.
    Modularity: Define reusable functions for different stages (build, test, deploy, etc.).

- Create a shared library repository with a structure like:
    vars/
        buildApp.groovy
        testApp.groovy
        deployApp.groovy

- Example of a shared library method (buildApp.groovy):
    
    def call(String version) {
        echo "Building application version ${version}"
        sh "mvn clean install -Dversion=${version}"
    }

- Configure Jenkins to Use the Shared Library
    Go to Jenkins Dashboard → Manage Jenkins → Configure System.
    Scroll to 'Global Pipeline Libraries'.
    Add a new library:
    Name: my-shared-library
    Default version: main (or specific branch/tag)
    Retrieval method: Git
    Repository URL: https://github.com/example/jenkins-shared-library.git
    Check Load implicitly if you want it automatically loaded in all pipel

- Use the shared library in a pipeline:

    @Library('my-shared-library') _
    pipeline {
        agent any
        stages {
            stage('Build') {
                steps {
                    buildApp('1.0.0')
                }
            }
        }
    }


## Jenkins Integration with Maven, Gradle, Docker, and Kubernetes

1. Maven:

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}

2. Gradle:

pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'gradle build'
            }
        }
    }
}

3. Docker:

pipeline {
    agent any
    stages {
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t my-app:1.0.0 .'
            }
        }
        stage('Push Docker Image') {
            steps {
                sh 'docker push my-app:1.0.0'
            }
        }
    }
}

4. Kubernetes:

- Use the Kubernetes plugin to deploy applications.

    pipeline {
        agent any
        stages {
            stage('Deploy to Kubernetes') {
                steps {
                    sh 'kubectl apply -f k8s-deployment.yaml'
                }
            }
        }
    }

## Multi-Branch Pipeline in Jenkins
- A multi-branch pipeline in Jenkins automatically creates and manages pipelines for different branches in a source control repository (e.g., GitHub, GitLab, Bitbucket). 
- This setup is useful for projects where multiple branches exist, such as dev, staging, and main, ensuring each branch is tested and deployed separately.

Prerequisites
    Jenkins installed and running.
    Git installed on the Jenkins server.
    Jenkins plugins installed:
        Pipeline
        Git
        Multibranch Pipeline
    Access to a Git repository with multiple branches.

Steps:

1. Create a Jenkins Multi-Branch Pipeline Job

    Open Jenkins and navigate to New Item.
    Enter a name (e.g., My-Multibranch-Pipeline).
    Select Multibranch Pipeline and click OK.
        Configure the job:
        Under Branch Sources, click Add source → Git.
        Enter the repository URL.
        Add credentials if required.
        Set the branch discovery strategy (e.g., all branches, named branches, wildcards).
    Click Save.
2. Create a Jenkinsfile in Your Repository
3. Trigger the Multi-Branch Pipeline
    When a new branch is pushed, Jenkins automatically detects and creates a pipeline for it.
    Each branch runs independently based on the Jenkinsfile.

### Use Case:
A development team maintains three branches:
    dev → For development
    staging → For testing
    main → For production

Each branch follows a different build and deployment process:
    dev branch runs unit tests.
    staging branch deploys to a test environment.
    main branch triggers a production deployment.

## Jenkins Plugins and Integrations

1. Installing and Managing Plugins

- Jenkins plugins extend the functionality of Jenkins, enabling integration with various tools and technologies. 
- The Plugin Manager is used to install, update, and manage plugins.

Steps:

a). Access Plugin Manager:
    Go to Manage Jenkins > Manage Plugins.
    The Plugin Manager has four tabs: Updates, Available, Installed, and Advanced.

b). Install Essential Plugins:
    Search for and install the following plugins:
        Git Plugin: Integrates Jenkins with Git repositories.
        Pipeline Plugin: Enables defining pipelines as code.
        Blue Ocean Plugin: Provides a modern UI for Jenkins pipelines.
        Docker Plugin: Integrates Jenkins with Docker for containerized builds.

    Example: Install the Git Plugin:
        Go to Available tab, search for "Git Plugin", check the box, and click Install without restart.

c). Manage Plugins:
    Update plugins from the Updates tab.
    Uninstall plugins from the Installed tab.

2. Blue Ocean Plugin

- Blue Ocean is a modern UI for Jenkins that provides a better visualization of pipelines and their status.

Steps:

a) Install Blue Ocean Plugin:
    Use the Plugin Manager to install the Blue Ocean plugin.

b) Access Blue Ocean:
    Click on Open Blue Ocean from the Jenkins dashboard.
    Alternatively, append /blue to your Jenkins URL (e.g., http://localhost:8080/blue).

c). Create and Visualize Pipelines:
    Use the Blue Ocean interface to create a new pipeline or visualize existing ones.
    Example: Create a pipeline from a GitHub repository:
        Click New Pipeline, select GitHub, and follow the steps to connect your repository.
    View pipeline runs with a graphical representation of stages and steps.

3. Integrating Jenkins with Version Control
- Jenkins can integrate with version control systems like GitHub or GitLab to trigger builds automatically.

Steps:

a) Set Up GitHub/GitLab Integration:
    Install the GitHub Plugin or GitLab Plugin via the Plugin Manager.
    Configure Jenkins to connect to your GitHub/GitLab repository:
        Go to Manage Jenkins > Configure System.
        Add GitHub/GitLab credentials (e.g., personal access token).

b) Use Webhooks for Automatic Builds:
    Set up a webhook in your GitHub/GitLab repository:
    Go to your repository settings > Webhooks.
    Add a webhook URL (e.g., http://<jenkins-server>/github-webhook/).
    Configure Jenkins job to trigger on webhook events:
        In your Jenkins job configuration, enable GitHub hook trigger for GITScm polling.

c) Example:
    Create a Jenkins pipeline job that pulls code from a GitHub repository:
        In the job configuration, under Source Code Management, select Git and provide the repository URL.
        Under Build Triggers, select GitHub hook trigger for GITScm polling.


4. Integrating Jenkins with Artifact Repositories

- Jenkins can push or pull artifacts from repositories like Nexus, Artifactory, or Docker Hub.

Steps:
a) Install Required Plugins:
    Install plugins like Nexus Artifact Uploader, Artifactory Plugin, or Docker Pipeline Plugin.

b) Push Artifacts to Nexus/Artifactory:
    Configure Jenkins to push build artifacts to Nexus/Artifactory:
    Use the Nexus Artifact Uploader plugin to upload artifacts.
    Example: Add a post-build step in your Jenkins job to upload a .jar file to Nexus:

        nexusArtifactUploader(
            nexusVersion: 'nexus3',
            protocol: 'http',
            nexusUrl: 'nexus.example.com',
            groupId: 'com.example',
            version: '1.0',
            repository: 'snapshots',
            credentialsId: 'nexus-credentials',
            artifacts: [
                [artifactId: 'my-app',
                type: 'jar',
                file: 'target/my-app.jar']
            ]
        )

c) Use the Docker Pipeline Plugin to pull Docker images:

- Example: Add a pipeline step to pull an image from Docker Hub:
    pipeline {
        agent any
        stages {
            stage('Pull Docker Image') {
                steps {
                    script {
                        docker.image('nginx:latest').pull()
                    }
                }
            }
        }
    }


## Different Types of Credentials in Jenkins and Using Them in Pipelines

- Jenkins provides a Credentials Plugin to securely store and manage sensitive information such as usernames, passwords, SSH keys, and API tokens. 
- These credentials can be used in pipelines without hardcoding sensitive data, improving security and maintainability.

1. Types of Credentials in Jenkins
Jenkins supports the following types of credentials:
    Username and Password
    Secret Text (e.g., API tokens, encryption keys)
    SSH Username with Private Key
    Certificate
    File (e.g., configuration files, certificates)

2. Steps to Create Credentials
Install the Credentials Plugin:
    Go to Manage Jenkins > Manage Plugins.
    Search for and install the Credentials Plugin (if not already installed).

Add Credentials:
    Go to Manage Jenkins > Manage Credentials.
    Click on the domain (e.g., global) and then Add Credentials.

Select the Credential Type:
    Choose the appropriate credential type and fill in the required fields.


#### 2.1 Username and Password

- Store a username and password for accessing a database or a version control system (e.g., GitHub).
Steps to Create:
    Go to Manage Jenkins > Manage Credentials.
    Click Add Credentials.
    Select Username and Password.
    Fill in the fields:
        Username: db-user
        Password: db-password
        ID: db-credentials (optional, but recommended for easy reference).

Using in Pipeline:
    pipeline {
        agent any
        stages {
            stage('Example') {
                steps {
                    withCredentials([usernamePassword(credentialsId: 'db-credentials', usernameVariable: 'DB_USER', passwordVariable: 'DB_PASSWORD')]) {
                        sh 'echo "Username: $DB_USER"'
                        sh 'echo "Password: $DB_PASSWORD"'
                    }
                }
            }
        }
    }


#### 2.2 Secret Text
-  Store an API token or encryption key.
Steps to Create:
    Go to Manage Jenkins > Manage Credentials.
    Click Add Credentials.
    Select Secret Text.
    Fill in the fields:
        Secret: your-api-token
        ID: api-token (optional, but recommended for easy reference).

Using in Pipeline:
    pipeline {
        agent any
        stages {
            stage('Example') {
                steps {
                    withCredentials([string(credentialsId: 'api-token', variable: 'API_TOKEN')]) {
                        sh 'echo "API Token: $API_TOKEN"'
                    }
                }
            }
        }
    }


#### 2.3 SSH Username with Private Key

- Store an SSH private key for accessing remote servers or Git repositories.
Steps to Create:
    Go to Manage Jenkins > Manage Credentials.
    Click Add Credentials.
    Select SSH Username with Private Key.
    Fill in the fields:
        Username: git-user
        Private Key: Paste the private key or upload the key file.
        ID: ssh-key (optional, but recommended for easy reference).

Using in Pipeline:
    pipeline {
        agent any
        stages {
            stage('Example') {
                steps {
                    withCredentials([sshUserPrivateKey(credentialsId: 'ssh-key', keyFileVariable: 'SSH_KEY', usernameVariable: 'SSH_USER')]) {
                        sh 'echo "SSH User: $SSH_USER"'
                        sh 'echo "SSH Key: $SSH_KEY"'
                    }
                }
            }
        }
    }


#### 2.4 Certificate
-  Store a certificate file for secure communication (e.g., SSL/TLS).
Steps to Create:

Go to Manage Jenkins > Manage Credentials.
Click Add Credentials.
Select Certificate.
Fill in the fields:
    Certificate: Upload the certificate file.
    Password: (Optional) Password for the certificate.
    ID: ssl-certificate (optional, but recommended for easy reference).

Using in Pipeline:
    pipeline {
        agent any
        stages {
            stage('Example') {
                steps {
                    withCredentials([certificate(credentialsId: 'ssl-certificate', keystoreVariable: 'KEYSTORE')]) {
                        sh 'echo "Keystore: $KEYSTORE"'
                    }
                }
            }
        }
    }

#### 2.5 File

- Store a configuration file or other sensitive files.

Steps to Create:
    Go to Manage Jenkins > Manage Credentials.
    Click Add Credentials.
    Select File.
        Fill in the fields:
        File: Upload the file.
        ID: config-file (optional, but recommended for easy reference).

Using in Pipeline:
    pipeline {
        agent any
        stages {
            stage('Example') {
                steps {
                    withCredentials([file(credentialsId: 'config-file', variable: 'CONFIG_FILE')]) {
                        sh 'echo "Config File: $CONFIG_FILE"'
                    }
                }
            }
        }
    }


##  Jenkins Security and Best Practices

#### 1.1 Set up User Authentication and Authorization

Why is it important?
    Prevents unauthorized access to Jenkins.
    Ensures only authorized users can perform specific actions (e.g., create jobs, manage plugins).

Steps to Set Up Authentication:
    Go to Manage Jenkins > Configure Global Security.
    Enable Security and select Jenkins’ own user database.
    Enable Allow users to sign up (optional for self-registration).
    Enable Matrix-based security or Role-Based Strategy for granular control.
Example:
    Create a user developer with read-only access to jobs.
    Create an admin user jenkins-admin with full control over Jenkins.


#### 1.2 Use Credentials Management (e.g., Jenkins Credentials Plugin)

Why use Credentials Plugin?
    Securely store and manage sensitive information (e.g., API keys, passwords).
    Avoid hardcoding credentials in pipelines.

Steps to Use Credentials Plugin:
    Install the Credentials Plugin (if not already installed).
    Go to Manage Jenkins > Manage Credentials.
    Add credentials (e.g., username/password, SSH key, secret text).
    Use credentials in pipelines using the credentials() function.

Example:
    Store a GitHub API token as a secret text credential with ID github-token.
    Use it in a pipeline:

    pipeline {
        agent any
        stages {
            stage('Example') {
                steps {
                    withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                        sh 'echo $GITHUB_TOKEN'
                    }
                }
            }
        }
    }


#### 1.3 Best Practices

a) Organize Jobs and Pipelines

Why organize jobs?
    Improves maintainability and reduces clutter.
    Makes it easier to locate and manage jobs.

Best Practices:
    Use folders to group related jobs (e.g., by project or team).
    Use naming conventions (e.g., project-name-job-type).

Example:
    Folder: Project-A
        Jobs: Project-A-Build, Project-A-Deploy
    Folder: Project-B
        Jobs: Project-B-Build, Project-B-Test

b) Use Declarative Pipelines for Readability

Why use declarative pipelines?
    Easier to read and maintain compared to scripted pipelines.
    Provides a structured syntax for defining pipelines.

c) Avoid Hardcoding Credentials in Pipelines

Why avoid hardcoding?
    Hardcoded credentials are a security risk.
    Difficult to manage and rotate credentials.

Best Practices:
    Use the Jenkins Credentials Plugin to store and manage credentials.
    Reference credentials in pipelines using withCredentials.

Example:
Bad Practice (Hardcoding):

    pipeline {
        agent any
        stages {
            stage('Deploy') {
                steps {
                    sh 'scp -i /path/to/private.key user@server:/path/to/deploy'
                }
            }
        }
    }

Good Practice (Using Credentials Plugin):

    pipeline {
        agent any
        stages {
            stage('Deploy') {
                steps {
                    withCredentials([sshUserPrivateKey(credentialsId: 'ssh-key', keyFileVariable: 'SSH_KEY')]) {
                        sh 'scp -i $SSH_KEY user@server:/path/to/deploy'
                    }
                }
            }
        }
    }

