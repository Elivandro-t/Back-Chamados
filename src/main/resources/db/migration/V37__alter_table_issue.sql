DO $$
BEGIN
    -- Verifica se a coluna 'hora_aceito' já existe na tabela 'issue'
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'issue' AND column_name = 'hora_aceito'
    ) THEN
        -- Adiciona a coluna 'hora_aceito' se não existir
        ALTER TABLE issue ADD COLUMN hora_aceito TIMESTAMP DEFAULT NULL;
    END IF;

    -- Verifica se a coluna 'data_criacao' já existe na tabela 'issue'
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'issue' AND column_name = 'data_criacao'
    ) THEN
        -- Adiciona a coluna 'data_criacao' se não existir
        ALTER TABLE issue ADD COLUMN data_criacao TIMESTAMP DEFAULT NULL;
    END IF;
END $$;
