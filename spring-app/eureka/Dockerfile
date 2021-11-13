FROM openjdk:16-alpine

ARG JAR_PATH=target/*.jar
COPY ${JAR_PATH} ./app.jar
ENTRYPOINT ["java", "-jar", "./app.jar"]