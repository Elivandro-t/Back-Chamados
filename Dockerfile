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

# Criar o diretório persistente e copiar os dados com comandos de depuração
RUN mkdir -p $DATA_DIR && \
    echo "Checking if /app/Logos exists..." && \
    if [ -d "/app/Logos" ]; then \
        echo "Directory /app/Logos exists. Copying to $DATA_DIR/Logos"; \
        cp -r /app/Logos $DATA_DIR/Logos; \
    else \
        echo "Directory /app/Logos does not exist."; \
    fi && \
    echo "Contents of $DATA_DIR:" && ls -l $DATA_DIR && \
    echo "Contents of $DATA_DIR/Logos:" && ls -l $DATA_DIR/Logos || echo "Directory $DATA_DIR/Logos does not exist."

# Comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]