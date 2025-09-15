pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'docker build -t jatin454/webapp:8.0 /home/webkorps/Desktop/MPrashant/testapp'
            }
        }
        
        stage('Push to Docker Hub') {
            steps {
                sh 'docker push jatin454/webapp:8.0'
            }
        }
        
        stage('Creating Namespace') {
            steps {
                sh 'kubectl apply -f /home/webkorps/namespace.yaml'
            }
        }
        
        stage('Creating Deployment') {
            steps {
                sh 'kubectl apply -f /home/webkorps/deployment.yaml'
            }
        }
        
        stage('Creating Service') {
            steps {
                sh 'kubectl apply -f /home/webkorps/service.yaml'
            }
        }
        
        stage('Deploy') {
            steps {
                sh 'kubectl get service my-service'
            }
        }
    }
}

