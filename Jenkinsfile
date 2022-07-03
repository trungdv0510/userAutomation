pipeline {
    agent any
   tools { 
      maven 'MAVEN_HOME_2' 
      jdk 'JAVA_HOME' 
    }
    stages {
        stage('run test automation') {
            steps {
                 // Run Maven on a Unix agent.
             	//sh "mvn clean test -DtestngFile=testSuite.xml"
                 // To run Maven on a Windows agent, use
               bat "mvn clean test -DtestngFile=testSuite.xml"
            }
        }
    }
}
