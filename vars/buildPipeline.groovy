def call(Map config = [:]) {

    pipeline {
        agent any

        stages {

            stage('Log Repo Info') {
                steps {
                    echo "Repository: ${env.JOB_NAME}"
                    echo "Branch: ${env.BRANCH_NAME}"
                }
            }

            stage('Detect PR') {
                steps {
                    script {
                        if (env.CHANGE_ID) {
                            echo "🔁 PR Detected: ${env.CHANGE_ID}"
                            echo "Source: ${env.CHANGE_BRANCH}"
                            echo "Target: ${env.CHANGE_TARGET}"
                        } else {
                            echo "🚫 Not a PR build"
                        }
                    }
                }
            }

            stage('Custom Step') {
                steps {
                    echo "Running shared pipeline logic..."
                }
            }
        }
    }
}