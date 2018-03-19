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
          steps {
            sh 'sleep 30'
            timeout(time: 10, unit: 'SECONDS') {
               retry(5) {
                  script {
                    def qg = waitForQualityGate()
                    echo qg.status
                    if (qg.status != 'OK') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
              }
            }
          }
        }
      }
      
   post {
    success {
        slackSend (color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
    }

    failure {
        slackSend (color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
    }
}
    }
  }
}