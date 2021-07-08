FROM openjdk:8-jdk-alpine

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} startEurekaServer.jar

ENTRYPOINT ["java","-jar","/startEurekaServer.jar"]
