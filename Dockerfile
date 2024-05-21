FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven
WORKDIR /apt
COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /apt/target/Informatica-0.0.1-SNAPSHOT.jar /apt/app.jar

ENTRYPOINT ["java", "-Duser.timezone=America/Sao_Paulo", "-jar", "/app.jar"]



