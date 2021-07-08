FROM openjdk:8-jdk-alpine

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} startAPIGatewayService.jar

ENTRYPOINT ["java","-jar","/startAPIGatewayService.jar"]
