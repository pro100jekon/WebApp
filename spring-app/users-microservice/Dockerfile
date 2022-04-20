FROM adoptopenjdk/openjdk11:latest

ARG JAR_PATH=users-microservice/target/*.jar
ENV JAR_NAME=users-microservice.jar

CMD mkdir app
WORKDIR app

COPY wait-for-it.sh wait-for
COPY ${JAR_PATH} ${JAR_NAME}
RUN apt-get -q update && apt-get -qy install netcat
CMD ./wait-for -t 300 eureka.docker:8761 -- java -jar ${JAR_NAME}