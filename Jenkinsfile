pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Clean') {
            steps {
                // Get some code from a GitHub repository
                //git 'https://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@github.com/Enseignant69/helloJenkins.git'

                // Run Maven on a Unix agent.
                //sh "mvn clean"

                // To run Maven on a Windows agent, use
                //dir('Jenkins') {
                    bat "mvn clean"
                //}
                
            }
        }
        
        stage('Test') {
            steps {
                // To run Maven on a Windows agent, use
                //dir('Jenkins') {
                    bat "mvn test"
                //}
                
            }
        }
        stage('Install') {
            steps {
                // To run Maven on a Windows agent, use
                //dir('Jenkins') {
                    bat "mvn install"
                //}
                
            }
        }
    }
    post
    {
        always
        {
            echo "test toujours"
        }
        success
        {
            echo "SUCCESS"
        }
        failure
        {
            echo "FAILURE"
        }
        unstable
        {
            echo "UNSTABLE"
        }
    }
}
