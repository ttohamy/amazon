pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }
     environment {
        RECIPIENTS = 'm_eltohamy@outlook.com'
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
                sh ''' mvn clean test '''
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
    post {
        always {
            emailext(
                subject: "🚀 Build #${BUILD_NUMBER} - Testing Report",
                body: """
                    <p>Hello Team,</p>
                    <p>The test execution for build <b>#${BUILD_NUMBER}</b> is complete.</p>
                    <p><b>View the Allure report:</b> <a href="http://your-server.com:5050">Click here</a></p>
                    <p>Or see the attached report.</p>
                    <br>
                    <p>Regards,<br>Jenkins</p>
                """,
                mimeType: 'text/html',
                to: "${env.RECIPIENTS}",
                attachmentsPattern: 'allure-report.zip',
                attachLog: true
            )
        }
    }
}
