def call() {
    pipeline {
        agent any
        stages {
            stage('Test') {
                steps {
                    sh './mvnw test'
                }
                post {
                    always {
                        junit 'projects/sample-mavenspringboot/target/surefire-reports/*.xml'
                    }
                }
            }
        }
    }
}