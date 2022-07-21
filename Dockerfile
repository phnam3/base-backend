# syntax=docker/dockerfile:1
FROM openjdk:11

WORKDIR /app

COPY .mvn/ .mvn
COPY mvn pom.xml ./

RUN ./mvn dependency:go-offline

COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]