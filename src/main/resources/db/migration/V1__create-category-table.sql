CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE category (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);