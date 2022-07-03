pipeline {
    agent any
   tools { 
      maven 'Maven 3.3.9'
      jdk 'jdk8'
    }
    stages {
        stage('run test automation') {
            steps {
                 // Run Maven on a Unix agent.
             	sh "mvn clean test -DtestngFile=testSuite.xml"
                 // To run Maven on a Windows agent, use
               //bat "mvn clean test -DtestngFile=testSuite.xml"
            }
        }
    }
}
