#!/bin/sh

# Criar o diretório persistente, se não existir
mkdir -p "$DATA_DIR"

# Copiar o diretório Logos para o diretório persistente, se ele existir no contêiner
if [ -d "/apps/Logos" ] && [ ! -d "$DATA_DIR/Logos" ]; then
    echo "Copying /apps/Logos to $DATA_DIR/Logos"
    cp -r /app/Logos "$DATA_DIR/Logos"
else
    echo "Directory /apps/Logos does not exist or $DATA_DIR/Logos already exists."
fi

# Listar conteúdo do diretório persistente para verificação
echo "Contents of $DATA_DIR:"
ls -l "$DATA_DIR"

# Iniciar a aplicação
exec java -jar /apps/app.jar
