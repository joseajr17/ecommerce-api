CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE product (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2) NOT NULL,
    stock BIGINT,

    category_id UUID,
    FOREIGN KEY (category_id) REFERENCES category(id) ON DELETE SET NULL
);