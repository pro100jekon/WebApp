FROM adoptopenjdk/openjdk11:latest

ARG JAR_PATH=target/*.jar
COPY ${JAR_PATH} ./app.jar
ENTRYPOINT ["java", "-jar", "./app.jar"]