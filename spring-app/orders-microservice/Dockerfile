FROM openjdk:16-alpine

ARG JAR_PATH=orders-microservice/target/*.jar
ENV JAR_NAME=orders-microservice.jar

CMD mkdir app
WORKDIR app

COPY wait-for-it.sh wait-for
COPY ${JAR_PATH} ${JAR_NAME}
CMD ./wait-for -t 300 eureka.docker:8761 -- java -jar ${JAR_NAME}