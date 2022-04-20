FROM adoptopenjdk/openjdk11:latest

ARG JAR_PATH=config-server/target/*.jar
ENV JAR_NAME=config-server.jar

RUN mkdir -p /root/.ssh
ADD id_ecdsa /root/.ssh/id_ecdsa
RUN chmod 700 /root/.ssh/id_ecdsa
RUN apt-get -q update && apt-get -qy install ssh
RUN ssh-keyscan -t ecdsa github.com >> /root/.ssh/known_hosts
RUN echo "Host github.com\n\tHostName github.com\n\tIdentityFile ~/.ssh/id_ecdsa" >> /root/.ssh/config

CMD mkdir app
WORKDIR app

COPY wait-for-it.sh wait-for
COPY ${JAR_PATH} ${JAR_NAME}
RUN apt-get -q update && apt-get -qy install netcat
CMD ./wait-for -t 300 eureka.docker:8761 -- java -jar ${JAR_NAME}