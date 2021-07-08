FROM openjdk:8-jdk-alpine

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} startSubscriptionService.jar

ENTRYPOINT ["java","-jar","/startSubscriptionService.jar"]
