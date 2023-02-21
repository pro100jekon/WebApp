FROM adoptopenjdk/openjdk11:latest

ARG JAR_PATH=target/*.jar
ENV JAR_NAME=test.jar

COPY ${JAR_PATH} ${JAR_NAME}
CMD java -Ddebug -jar ${JAR_NAME}