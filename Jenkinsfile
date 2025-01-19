pipeline{
    agent any
    tools{
        maven "Maven"
        jdk "jdk-1.8.jdk"
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
    post {
        always{
            zeeReporter createPackage: false, cycleDuration: '30 days', cycleKey: 'CreateNewCycle', cyclePrefix: '', parserTemplateKey: '5', projectKey: '1', releaseKey: '1', resultXmlFilePath: 'target/surefire-reports/*.xml', serverAddress: 'https://mattel.yourzephyr.com'
        }
    }

}
