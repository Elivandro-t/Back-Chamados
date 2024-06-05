FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven
WORKDIR /apt
COPY . .

RUN mvn clean install

FROM openjdk:17-jdk-slim
VOLUME /apt
VOLUME /apt/Logos
VOLUME /apt/Img
VOLUME /apt/sistemaBotao
EXPOSE 8080

COPY --from=build /apt/target/Informatica-0.0.1-SNAPSHOT.jar /apt/app.jar
COPY --from=build /apt/Logos /apt/Logos
COPY --from=build /apt/Img /apt/Img
COPY --from=build /apt/sistemaBotao /apt/sistemaBotao

ENTRYPOINT ["java", "-jar", "/apt/app.jar"]



