FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven
WORKDIR /apt
COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

RUN apk add --no-cache tzdata
ENV TZ=America/Sao_Paulo

COPY --from=build /apt/target/Informatica-0.0.1-SNAPSHOT.jar /apt/app.jar

ENTRYPOINT ["java", "-jar", "/apt/app.jar"]



