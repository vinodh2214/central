def call(Map config = [:]) {

    pipeline {
        agent any

        stages {

            stage('Identify Build') {
                steps {
                    script {
                        if (env.CHANGE_ID) {
                            echo "🔁 PR Build: ${env.CHANGE_ID}"
                            echo "Source: ${env.CHANGE_BRANCH}"
                            echo "Target: ${env.CHANGE_TARGET}"
                        } else {
                            echo "🚀 Branch Build: ${env.BRANCH_NAME}"
                        }
                    }
                }
            }

            stage('Checkout') {
                steps {
                    checkout scm
                }
            }

            stage('Build') {
                steps {
                    bat 'echo Running centralized build logic...'
                }
            }

            stage('Optional Config') {
                steps {
                    script {
                        if (config.appName) {
                            echo "App Name: ${config.appName}"
                        }
                    }
                }
            }
        }

        post {
            success {
                echo "✅ Build successful"
            }
            failure {
                echo "❌ Build failed"
            }
        }
    }
}
