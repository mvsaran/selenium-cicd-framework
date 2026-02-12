pipeline {
    agent any
    environment {
        RECIPIENT = 'mvsarankumar@gmail.com' // Replace with actual email
    }
    stages {
        stage('Unit Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng-unit.xml'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Smoke Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng-smoke.xml'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Sanity Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng-sanity.xml'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Regression Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng-regression.xml'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Integration Tests') {
            steps {
                sh 'mvn test -DsuiteXmlFile=testng-integration.xml'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
    post {
        always {
            emailext(
                subject: "Saucedemo Pipeline Results",
                body: "Build Status: ${currentBuild.currentResult}\nTotal Tests: ${testResultAction.totalCount}\nPassed: ${testResultAction.passCount}\nFailed: ${testResultAction.failCount}",
                recipientProviders: [[$class: 'DevelopersRecipientProvider']],
                to: "${RECIPIENT}"
            )
        }
    }
}
