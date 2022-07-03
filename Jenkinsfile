pipeline {
    agent any
   // tools {
        // Install the Maven version configured as "M3" and add it to the path.
       // Maven "M3"
    //}
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
