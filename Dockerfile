FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven

WORKDIR /app
COPY . .
RUN mvn clean install

FROM openjdk:17-jdk-slim

# Crie diretórios para volumes
RUN mkdir -p /var/lib/myapp/Logos \
    && mkdir -p /var/lib/myapp/Img \
    && mkdir -p /var/lib/myapp/sistemBotao

EXPOSE 8080

# Copie os arquivos do estágio de compilação para o estágio de produção
COPY --from=build /app/target/Informatica-0.0.1-SNAPSHOT.jar /app/app.jar
COPY --from=build /app/Logos /var/lib/myapp/Logos
COPY --from=build /app/Img /var/lib/myapp/Img
COPY --from=build /app/sistemBotao /var/lib/myapp/sistemBotao

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
