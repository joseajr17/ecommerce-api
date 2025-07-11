CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE product (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price NUMERIC(10, 2) NOT NULL,
    category VARCHAR(100) NOT NULL,
    stock INTEGER NOT NULL
);