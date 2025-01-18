pipeline{
    agent any
    tools{
        maven 'Maven'
    }
    stages{
        stage("checkout from github"){
            steps{
                git branch: 'main',
                url:'https://github.com/sumitpahawa/Jenkinsjob.git'
                echo 'pulled from github successfully'
            }
        }
<<<<<<< HEAD
        stage('Checkout Code') {
            steps {
                // Checkout the repository from GitHub or GitLab
                git branch: 'main', url:'https://github.com/sumitpahawa/Jenkinsjob.git'
=======
        stage("compile the code to executable format"){
            steps{
                sh "mvn clean install"
                echo 'converted the code from human readable to machine readable '
>>>>>>> 1c7226365b1c65bc3a8771ec01e5e2f6609e27ea
            }
        }
        stage("testing the code"){
            steps{
                sh "mvn test"
                echo 'run and execute the test cases written in selenium'
            }
        }
    }
}
