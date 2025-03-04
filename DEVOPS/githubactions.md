### 1. Introduction to GitHub Action
- GitHub Actions is a powerful tool that allows you to automate, customize, and execute your software development workflows right in your GitHub repository.
- GitHub Actions allows you to automate workflows for your GitHub repository. 
- These workflows can run based on specific events (e.g., push, pull request, issue comment). 
- You define workflows in YAML files, and they run in a GitHub-hosted or self-hosted environment.
- It can help you with tasks like Continuous Integration (CI), Continuous Deployment (CD), automating tests, running scripts, and more.


=> Basic Terminology:

    Workflow: A defined process (e.g., CI/CD pipeline) in your repository.
    Event: A trigger for running the workflow (e.g., a push to a branch).
    Job: A collection of steps that run in a specific environment.
    Step: A single task in a job, such as running a script or action.
    Action: A reusable unit of code that performs a specific task (e.g., checking out code, setting up a Node.js environment).
    Runner: A server that executes the workflows.

### 2. Setting Up GitHub Actions in Your Repository
- Go to your GitHub repository.
- In the top menu, click on Actions. If this is your first time using GitHub Actions, you’ll see suggested workflows or an option to create a new one.

- Create a new YAML file inside the .github/workflows directory (if it doesn’t exist, you can create it).
    Example file: .github/workflows/ci.yml


### 3. Writing a Basic Workflow

=> github-actions-demo.yml

    name: GitHub Actions Demo
    run-name: ${{ github.actor }} is testing out GitHub Actions 🚀
    on: [push]
    jobs:
      Explore-GitHub-Actions:
          runs-on: ubuntu-latest
          steps:
          - run: echo "🎉 The job was automatically triggered by a ${{ github.event_name }} event."
          - run: echo "🐧 This job is now running on a ${{ runner.os }} server hosted by GitHub!"
          - run: echo "🔎 The name of your branch is ${{ github.ref }} and your repository is ${{ github.repository }}."

          - name: Check out repository code
            uses: actions/checkout@v4
          
          - run: echo "💡 The ${{ github.repository }} repository has been cloned to the runner."
          
          - run: echo "🖥️ The workflow is now ready to test your code on the runner."
          
          - name: List files in the repository
              run: |
              ls ${{ github.workspace }}
          - run: echo "🍏 This job's status is ${{ job.status }}."


.github/workflows/ci.yml

    name: CI Workflow

    on:
      push:
          branches:
          - main
          - feature/dev
      pull_request:
          branches:
          - main

    jobs:
      build:
          runs-on: ubuntu-latest

          steps:
          # Step 1: Checkout the repository
          - name: Checkout repository
            uses: actions/checkout@v2

          # Step 2: Set up JDK (Java Development Kit)
          - name: Set up JDK 11
            uses: actions/setup-java@v2
            with:
            java-version: '11'  # You can modify this to your preferred version of Java
            distribution: 'adoptopenjdk'  # You can change the distribution to OpenJDK, Zulu, etc.

          # Step 3: Cache Gradle dependencies to speed up builds
          - name: Cache Gradle dependencies
            uses: actions/cache@v2
            with:
            path: ~/.gradle/caches
            key: ${{ runner.os }}-gradle-${{ hashFiles('**/*.gradle*', '**/gradle-wrapper.properties') }}
            restore-keys: |
                ${{ runner.os }}-gradle-

          # Step 4: Build the project with Gradle
          - name: Build with Gradle
            run: ./gradlew build --no-daemon  # The --no-daemon option prevents Gradle from running in the background

          # Step 5: Run tests with Gradle
          - name: Run tests with Gradle
            run: ./gradlew test --no-daemon  # This will run the tests using Gradle


Explanation of the Example:
    on: Specifies the events that trigger the workflow (push and pull_request on the main branch).
    jobs: Defines the jobs that will run. In this case, there's only one job called build.
    runs-on: Specifies the operating system for the runner (here, it’s ubuntu-latest).
    steps: Each step in the job defines an action to run. We first checkout the code, then set up Node.js, install dependencies, and run tests.


### 4. GitHub Actions Workflow Syntax
    A basic GitHub Actions workflow is structured in YAML. Key components include:

    Triggers (on:): Define events like push, pull_request, workflow_dispatch (manual trigger), etc.
    Jobs (jobs:): Each job runs on a separate runner.
    runs-on: Define the environment (ubuntu-latest, windows-latest, macos-latest).
    Steps: List of actions or shell commands to execute in the job.

### 5. Actions and Reusable Workflows
    => GitHub Actions uses predefined actions, which are reusable steps that help with common tasks. 
    You can find popular actions in the GitHub Actions Marketplace.
    
        - name: Checkout code
          uses: actions/checkout@v2

    =>  Actions can also be created as reusable workflows:
        You can reference another workflow in a different repository.

        - name: Run Reusable Workflow
          uses: username/repo-name/.github/workflows/workflow.yml@main

        
### 6. Secrets and Environment Variables
- GitHub Actions can use secrets and environment variables for secure information such as API keys or credentials.

- Adding Secrets: Go to your repository's settings > Secrets > New Repository Secret.

- Using Secrets in Workflows:
    steps:
    - name: Use secret
        run: echo ${{ secrets.MY_SECRET }}

- Example (Setting at the Job Level):

jobs:
  build:
    runs-on: ubuntu-latest
    env:
      MY_VARIABLE: 'value'

    steps:
      - name: Print environment variable
        run: echo "The value of MY_VARIABLE is $MY_VARIABLE"

- Example (Setting at the Workflow Level):

name: Example Workflow

on: [push]

env:
  GLOBAL_VAR: 'global_value'

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Print global variable
        run: echo "The value of GLOBAL_VAR is $GLOBAL_VAR"


## 7. Conditional Steps and Job Dependencies
    - You can make certain steps or jobs conditional, based on whether previous jobs succeed or fail.
    - Conditionally run jobs (e.g., only run on a push to main):

    jobs:
        test:
            runs-on: ubuntu-latest
            if: github.ref == 'refs/heads/main'


### 8. Matrix Builds
    - A matrix build allows you to test your code on multiple environments at once. For example, testing on different versions of Node.js:

    - Example:
        jobs:
            build:
                runs-on: ubuntu-latest
                strategy:
                matrix:
                    node-version: [14, 16, 18]
                steps:
                - uses: actions/setup-node@v2
                    with:
                    node-version: ${{ matrix.node-version }}
                - run: npm install
                - run: npm test


### 9. Take Input from User in a GitHub Actions Workflow:
- Define Inputs for the Workflow Using workflow_dispatch
- The workflow_dispatch event allows you to manually trigger a workflow and specify inputs when triggering the workflow through the GitHub UI.

=> Example: 

name: Example Workflow with User Input
on:
  workflow_dispatch:
    inputs:
      name:
        description: 'Enter your name'
        required: true
        default: 'Guest'
      environment:
        description: 'Choose the environment (dev, prod)'
        required: true
        default: 'dev'
        options:
          - dev
          - prod

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - name: Check user input
        run: |
          echo "Hello, ${{ github.event.inputs.name }}!"
          echo "Environment: ${{ github.event.inputs.environment }}"



### 10. Workflow Monitoring and Debugging
- GitHub provides great insights into your workflow runs. You can check:
    Workflow logs to see each step’s output.
    The status of each job (success, failure).
- For debugging purposes, we can add debug logging to your steps:
- Example:

    - name: Debug step
        run: |
            echo "Debug info"
            echo "GITHUB_SHA: $GITHUB_SHA"
