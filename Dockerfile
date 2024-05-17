FROM ubuntu:latest as build
RUN apt-get update
RUN  apt-get install openjdk-17-openjdk -y
COPY . . \
RUN apt-install maven -
RUN mvn clean install
FROM openjdk:17-jdk-slim
EXPOSE 8080
COPY --from=build /target/Informatica-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT["java","-jar","app.jar"]
