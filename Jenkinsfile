pipeline {
    agent any

    stages {
        stage('Test in Docker') {
            steps {
                script {
                    docker.image('maven:3.9-eclipse-temurin-17').inside {
                        sh 'mvn clean test'
                    }
                }
            }
        }
    }
}
