def call(String env) {
    stages {
        stage('Deploy') {
            steps {
                echo "Deploying to ${env}"
            }
        }
    }
}