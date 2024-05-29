CREATE TABLE refeshtoken (
    id SERIAL PRIMARY KEY,
    refreshtoken VARCHAR(255),
    expirationtime TIMESTAMP,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES usuario (id)
);