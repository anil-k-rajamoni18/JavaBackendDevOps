def call(String env) {
    pipeline {
        agent any
        stages {
            stage('Deploy') {
                steps {
                    echo "Deploying to ${env}"
                }
            }
        }
    }
}