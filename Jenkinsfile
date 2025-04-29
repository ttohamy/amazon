pipeline {
    agent any

    stages {
        stage('Build & Test') {
            steps {
                script {
                    docker.image('maven:3.9-eclipse-temurin-17').inside('-v /root/.m2:/root/.m2') {
                        sh 'mvn clean test'
                    }
                }
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}
