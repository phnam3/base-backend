## syntax=docker/dockerfile:1
#FROM openjdk:11
#
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src
##
##CMD ["./mvnw", "spring-boot:run"]

FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/*.jar
WORKDIR /opt/app
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java","-jar","application.jar"]