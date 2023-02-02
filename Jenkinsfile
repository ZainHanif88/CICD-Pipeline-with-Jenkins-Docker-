pipeline {
    //agent { label 'agent1' }
    agent any

    tools {
        // Run the Maven installation named "3.8.7" and add it to the path.
        maven "MY_MAVEN"
    }

    stages {
        stage('clean and checkout') {
            steps {
                sh 'mvn clean -f backend'
                echo 'downloading github project...'
            }
        }

        stage('build') {
            steps {
                echo 'building...'
                sh 'mvn test-compile -f backend'
                echo 'finished building'
            }
        }
 
        stage('test') {
            steps {
                echo 'starting test.....'
                sh 'mvn surefire:test -f backend'
                echo 'finished test'
            }
        }
        
        stage('package') {
            steps {
                echo 'packaging...'
                sh 'mvn war:war -f backend'
                echo 'packaged'
                sh 'pwd'

            }
        }

        stage ('deploy'){
             steps {
                dir('./backend'){
                    sh 'cp ./target/ROOT.war /artifacts'
                }
                
        }
    }

    }


}
