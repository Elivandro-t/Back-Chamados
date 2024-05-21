FROM ubuntu:latest as build
RUN apk add --no-cache apk-tools && \
    cp /usr/share/zoneinfo/America/Sao_Paulo /etc/localtime && \
    echo "America/Sao_Paulo" > /etc/timezone && \
    apk del apk-tools
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven
WORKDIR /apt
COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /apt/target/Informatica-0.0.1-SNAPSHOT.jar /apt/app.jar

ENTRYPOINT ["java", "-jar", "/apt/app.jar"]



