DO $$
BEGIN
    -- Verifica se a coluna 'contato' já existe na tabela 'issue'
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_name = 'issue' AND column_name = 'contato'
    ) THEN
        -- Adiciona a coluna 'contato' se não existir
        ALTER TABLE issue ADD COLUMN contato VARCHAR(100) DEFAULT NULL;
    END IF;
END $$;
