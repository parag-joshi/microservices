FROM openjdk:8-jdk-alpine

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} startBookService.jar

ENTRYPOINT ["java","-jar","/startBookService.jar"]
