FROM openjdk:16-alpine

ARG JAR_PATH=config-server/target/*.jar
ENV JAR_NAME=config-server.jar

RUN mkdir -p /root/.ssh
ADD id_rsa /root/.ssh/id_rsa
RUN chmod 700 /root/.ssh/id_rsa
RUN echo "Host github.com\n\tStrictHostKeyChecking no\n" >> /root/.ssh/config

CMD mkdir app
WORKDIR app

COPY wait-for-it.sh wait-for
COPY ${JAR_PATH} ${JAR_NAME}
CMD ./wait-for -t 300 eureka.docker:8761 -- java -jar ${JAR_NAME}