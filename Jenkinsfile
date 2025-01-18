pipeline{
    agent any
    tools{
        maven 'MAVEN_JENKINS'
    }
    stages{
        stage("checkout from github"){
            steps{
                git branch: 'main',
                url:'https://github.com/sumitpahawa/Jenkinsjob.git'
                echo 'pulled from github successfully'
            }
        }
        stage("compile the code to executable format"){
            steps{
                sh "mvn clean install"
                echo 'converted the code from human readable to machine readable '
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
