def call(String env) {
    stage('Deploy') {
        steps {
            echo "Deploying to ${env}"
        }
    }
}