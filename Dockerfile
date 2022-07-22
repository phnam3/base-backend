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
EXPOSE 8084
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Duser.timezone=GMT+7","-Dspring.profiles.active=dev,swagger","-jar","application.jar"]