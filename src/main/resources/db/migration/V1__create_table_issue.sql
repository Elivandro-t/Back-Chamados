-- Criação da tabela se não existir
CREATE TABLE IF NOT EXISTS issue (
    id serial PRIMARY KEY,
    filial bigint NOT NULL
);

-- Atualizações se a tabela já existir
DO $$
BEGIN
    -- Adiciona uma nova coluna se não existir
    IF EXISTS (SELECT 1 FROM pg_tables WHERE schemaname = 'public' AND tablename = 'issue') THEN
        -- Adiciona a coluna usuario_id com referência à tabela usuario
        ALTER TABLE issue ADD COLUMN IF NOT EXISTS usuario_id INTEGER REFERENCES usuario(id);
    END IF;
END $$;
