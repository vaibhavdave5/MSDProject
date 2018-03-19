pipeline {
   agent {
       docker {
           image 'maven:3-alpine'
           args '-v /root/.m2:/root/.m2'
       }
   }

   stages {
       stage('Build') {
           steps {
               echo "Building"
               sh 'mvn -f ./plagiarism-detector install:install-file -Dfile=jfxrt.jar -DgroupId=com.oracle -DartifactId=javaFX -Dversion=2.2 -Dpackaging=jar'
               sh 'mvn -f ./plagiarism-detector compile'
               sh 'mvn -f ./plagiarism-detector package'
           }
       }
       stage('Test'){
           steps {
               echo "Testing"
               sh 'mvn -f ./plagiarism-detector test'
           }
       }
       
       stage('SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                        sh 'mvn -f ./plagiarism-detector install:install-file -Dfile=jfxrt.jar -DgroupId=com.oracle -DartifactId=javaFX -Dversion=2.2 -Dpackaging=jar'
                        sh 'mvn -f ./plagiarism-detector clean install'
                        sh 'mvn -f ./plagiarism-detector sonar:sonar'
                        
                }
            }
        }
            
        stage('Quality') {
          timeout(time: 1, unit: 'HOURS') {
              def qg = waitForQualityGate()
              if (qg.status != 'OK') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
              }
          }
      }
    }
}