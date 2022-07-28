pipeline {
    agent any
    environment {
          DOCKER_IMAGE_NAME  = 'phnam3/my-first-docker'
          IMAGE_TAG          = 'v1.0.0'
          CONTAINER_NAME     = 'base-backend'
    }
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
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Login To Docker') {
            steps {
                withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd2')]) {
                    sh 'docker login -u phnam3 -p ${dockerhubpwd2}'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    sh 'docker-compose build $CONTAINER_NAME'
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    sh 'docker-compose push $CONTAINER_NAME'
                }
            }
        }
        stage('Run Docker Image') {
            steps{
                script {
                    sh "docker-compose down"
                    sh "docker-compose up -d $CONTAINER_NAME"
                }
            }
        }
    }
}