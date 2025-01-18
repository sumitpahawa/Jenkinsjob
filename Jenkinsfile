pipeline {
    agent any

    environment {
        JAVA_VERSION = '11'  // Specify the version of Java you want to install
        MAVEN_VERSION = '3.8.9'  // Specify the version of Maven you want to install
    }

    stages {
        stage('Checkout Code') {
              steps {
                // Checkout the repository from GitHub or GitLab
                    git branch: 'main', url:'https://github.com/sumitpahawa/Jenkinsjob.git'
              }
        }

        stage('Install Dependencies') {
              steps {
                // Install Maven dependencies
                sh 'mvn clean install'
                // Install Appium dependencies if needed
                sh 'npm install -g appium'
              }
        }

        stage('Run BDD Tests') {
               steps {
                // Run the Cucumber tests using Maven
                sh 'mvn test'
              }
        }
    }
    post {
        always {
            // Clean up actions (like stopping the Appium server)
            archiveArtifacts allowEmptyArchive: true, artifacts: 'test-output/Spark/Index.html', followSymlinks: false

        }
    }
}
