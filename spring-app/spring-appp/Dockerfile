FROM adoptopenjdk/openjdk11:latest

ARG JAR_PATH=target/*.jar
ENV JAR_NAME=test.jar

COPY ${JAR_PATH} ${JAR_NAME}
RUN apt-get -q update && apt-get -qy install netcat && apt-get -qy install wget
RUN wget https://raw.githubusercontent.com/eficode/wait-for/v2.2.3/wait-for
RUN chmod 555 wait-for && ls -la
CMD ./wait-for -t 300 eureka1.docker:8761 -- java -jar ${JAR_NAME}