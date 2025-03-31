def call(String version) {
    stage('Build') {
        steps {
            echo "Building application version ${version}"
            sh "./mvnw clean install -Dversion=${version}"
        }
    }
}