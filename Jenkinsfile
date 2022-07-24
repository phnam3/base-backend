pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage('Checkout Github') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/phnam3/base-backend']]])
            }
        }
        stage('Build Maven') {
            steps {
                sh 'mvn clean install'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker-compose build base-backend'
                    sh 'docker tag my-first-docker:v1.0.0 phnam3/my-first-docker:v1.0.0'
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                        sh 'docker login -u phnam3 -p ${dockerhubpwd}'
                        sh 'docker push phnam3/my-first-docker:v1.0.0'
                    }
                }
            }
        }
        stage('Run Docker Image') {
            steps {
                script {
                    sh 'docker pull phnam3/my-first-docker:v1.0.0'
                    sh 'docker compose up -d base-backend'
                }
            }
        }
    }
}