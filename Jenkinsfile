pipeline {
    agent any

    environment {
        RECIPIENT = 'mvsarankumar@gmail.com'
    }

    tools {
        maven 'maven3'
        jdk 'jdk17'
    }

    stages {

        stage('Run Test Suites') {
            parallel {

                stage('Unit Tests') {
                    steps {
                        bat 'mvn test -DsuiteXmlFile=testng-unit.xml'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }

                stage('Smoke Tests') {
                    steps {
                        bat 'mvn test -DsuiteXmlFile=testng-smoke.xml'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }

                stage('Sanity Tests') {
                    steps {
                        bat 'mvn test -DsuiteXmlFile=testng-sanity.xml'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }

                stage('Regression Tests') {
                    steps {
                        bat 'mvn test -DsuiteXmlFile=testng-regression.xml'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }

                stage('Integration Tests') {
                    steps {
                        bat 'mvn test -DsuiteXmlFile=testng-integration.xml'
                    }
                    post {
                        always {
                            junit 'target/surefire-reports/*.xml'
                        }
                    }
                }
            }
        }
    }

    post {
        always {
            emailext(
                subject: "Pipeline Result: ${currentBuild.currentResult}",
                body: "Build URL: ${env.BUILD_URL}",
                to: "${RECIPIENT}"
            )
        }
    }
}
