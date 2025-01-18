pipeline {
    agent any

 environment {
        JAVA_VERSION = '11'  // Specify the version of Java you want to install
        MAVEN_VERSION = '3.8.9'  // Specify the version of Maven you want to install
    }

    stages {
    stage('Install Java') {
                steps {
                    script {
                        echo "Installing Java ${env.JAVA_VERSION}..."

                        // Update package index
                        sh 'sudo apt-get update'

                        // Install OpenJDK (Java)
                        sh "sudo apt-get install -y openjdk-${env.JAVA_VERSION}-jdk"

                        // Set JAVA_HOME environment variable
                        sh 'echo "export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which javac))))" >> ~/.bashrc'
                        sh 'echo "export PATH=$JAVA_HOME/bin:$PATH" >> ~/.bashrc'

                        // Apply the changes made to .bashrc
                        sh 'source ~/.bashrc'

                        // Verify Java installation
                        sh 'java -version'
                    }
                }
            }

            stage('Install Maven') {
                steps {
                    script {
                        echo "Installing Maven ${env.MAVEN_VERSION}..."

                        // Download Maven binary
                        sh "wget https://apache.osuosl.org/maven/maven-3/${env.MAVEN_VERSION}/binaries/apache-maven-${env.MAVEN_VERSION}-bin.tar.gz"

                        // Extract the tar file
                        sh "tar -xvzf apache-maven-${env.MAVEN_VERSION}-bin.tar.gz"

                        // Move Maven to /opt
                        sh "sudo mv apache-maven-${env.MAVEN_VERSION} /opt/"

                        // Set Maven environment variables
                        sh 'echo "export M2_HOME=/opt/apache-maven-${env.MAVEN_VERSION}" >> ~/.bashrc'
                        sh 'echo "export PATH=$M2_HOME/bin:$PATH" >> ~/.bashrc'

                        // Apply changes made to .bashrc
                        sh 'source ~/.bashrc'

                        // Verify Maven installation
                        sh 'mvn -version'
                    }
                }
            }
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

        stage('Generate Reports') {
            steps {
                // Generate and archive the test reports (Cucumber or JUnit)
                cucumber buildStatus: 'UNSTABLE', fileIncludePattern: '**/target/cucumber-report.html'
                junit '**/target/test-classes/*.xml' // for JUnit test results
            }
        }
    }

    post {
        always {
            // Clean up actions (like stopping the Appium server)
            sh 'pkill -f appium'
        }
    }
}
