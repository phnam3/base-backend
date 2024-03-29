## syntax=docker/dockerfile:1
#TEST PUSH
FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
WORKDIR /opt/app
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","application.jar"]