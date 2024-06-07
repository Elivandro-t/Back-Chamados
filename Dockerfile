FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven
WORKDIR /apt
COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /apt
RUN mkdir Logos Img sistemBotao
VOLUME /apt
VOLUME /apt/Logos
VOLUME /apt/sistemBotao
EXPOSE 8080
COPY --from=build /apt/target/Informatica-0.0.1-SNAPSHOT.jar /apt/app.jar
COPY --from=build /apt/Logos /apt/Logos
COPY --from=build /apt/sistemBotao /apt/sistemBotao

ENTRYPOINT ["java", "-jar", "/apt/app.jar"]
