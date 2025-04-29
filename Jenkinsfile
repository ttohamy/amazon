pipeline {
    agent {
        docker {
            image 'maven:3.9-eclipse-temurin-17'
            args '-v $HOME/.m2:/root/.m2'
        }
    }
    
    triggers {
        pollSCM '* * * * *'
    }
    
    stages {
        stage('Check Docker') {
    steps {
        sh 'docker version'
    }
}
        stage('Checkout') {
            steps {
                git 'https://github.com/ttohamy/amazon.git' // or use local folder if mounted
            }
        }
        stage('Build') {
            steps {
                echo "Building.."
                // sh '''
                // cd myapp
                // pip install -r requirements.txt
                // '''
            }
        }
        stage('Test') {
            steps {
                echo "Testing.."
                // sh '''
                // cd myapp
                // python3 hello.py
                // python3 hello.py --name=Brad
                // '''
                sh ''' mvn clean test
                '''
            }
        }
        stage('Deliver') {
            steps {
                echo 'Deliver....'
                sh '''
                echo "doing delivery stuff.."
                '''
            }
        }
    }
}
