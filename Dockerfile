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


#FROM ubuntu:latest as build
#
#RUN apt-get update && apt-get install -y \
#    openjdk-17-jdk \
#    maven
#
#WORKDIR /app
#
## Copie o pom.xml primeiro para aproveitar o cache do Docker
#COPY pom.xml .
#
#COPY . .
#RUN mvn clean package
#
#FROM openjdk:17-jdk-slim
#
#WORKDIR /app
#
## Copie o arquivo .jar do primeiro estágio
#COPY --from=build /app/target/Informatica-0.0.1-SNAPSHOT.jar app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "app.jar"]

# Estágio de construção
FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven

WORKDIR /app

# Copie o pom.xml primeiro para aproveitar o cache do Docker
COPY pom.xml .

# Copie o restante dos arquivos da aplicação, incluindo app/Logos
COPY . .

# Execute o comando Maven para construir o projeto
RUN mvn clean package

# Estágio de produção
FROM openjdk:17-jdk-slim

# Diretório de trabalho para a aplicação
WORKDIR /app

# Copie o arquivo .jar do estágio de construção
COPY --from=build /app/target/Informatica-0.0.1-SNAPSHOT.jar app.jar

# Expor a porta 8080 para acesso externo
EXPOSE 8080

# Diretório onde os arquivos de dados serão armazenados no disco persistente
ENV DATA_DIR=/var/lib/data

# Força a recriação do diretório persistente e a cópia dos dados
RUN mkdir -p $DATA_DIR && \
    if [ -d "/app/Logos" ]; then cp -r /app/Logos $DATA_DIR/Logos; fi && \
    ls -l $DATA_DIR/Logos

# Comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]