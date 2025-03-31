def call(String version) {
    pipeline {
        agent any
        stages {
            stage('Build') {
                steps {
                    echo "Building application version ${version}"
                    sh "./mvnw clean install -Dversion=${version}"
                }
            }
        }
    }
}