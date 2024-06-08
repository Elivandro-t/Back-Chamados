#FROM ubuntu:latest as build
#
#RUN apt-get update && apt-get install -y \
#    openjdk-17-jdk \
#    maven
#WORKDIR /apt
#COPY . .
#RUN mvn clean package
#
#FROM openjdk:17-jdk-slim
#WORKDIR /apt
#COPY --from=build /apt/target/Informatica-0.0.1-SNAPSHOT.jar /apt/app.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/apt/app.jar"]
FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven

WORKDIR /app

# Copie o pom.xml primeiro para aproveitar o cache do Docker
COPY pom.xml .

COPY . .
RUN mvn clean package

FROM openjdk:17-jdk-slim

WORKDIR /app

# Copie o arquivo .jar do primeiro estágio
COPY --from=build /app/target/Informatica-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]