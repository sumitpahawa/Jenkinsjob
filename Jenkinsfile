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
            zeeReporter createPackage: true, cycleDuration: '7 days', cycleKey: 'CreateNewCycle', cyclePrefix: '', parserTemplateKey: '6', projectKey: '12', releaseKey: '24', resultXmlFilePath: 'target/surefire-reports/surefire-reports/testng-results.xml', serverAddress: 'https://mattel.yourzephyr.com'
        }
        success {
                  publishHTML([
                              allowMissing: false,
                              alwaysLinkToLastBuild: false,
                              keepAll: false,
                              reportDir: 'test-output/Spark/',
                              reportFiles: 'Index.html',
                              reportName: 'AutomationReport',
                              reportTitles: '',
                              useWrapperFileDirectly: true])
                }

    }

}
