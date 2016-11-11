node {
   // Mark the code checkout 'stage'.....
   stage 'Checkout'
   
   checkout scm	
   
   // Get some code from a GitHub repository
   //git url: 'https://github.com/bhiapp4/practiceApp.git'

  //add maven to env 
   env.path="${tool 'M3'}/bin:${env.PATH}"

   // Mark the code build 'stage'....
   stage 'Build'
   wrap([$class: 'ConfigFileBuildWrapper', managedFiles: [[fileId: 'custom-maven-global', replaceTokens: false, targetLocation: '', variable: 'MAVEN_SETTINGS']]]) {
         // Run the maven build
       sh 'mvn -s $MAVEN_SETTINGS clean package'
   }
 
   //step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
}