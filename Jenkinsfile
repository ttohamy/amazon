pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
     tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }
    stages {
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
