-- Criação de usuário ADMIN caso não exista
INSERT INTO users (id, username, email, password, role)
SELECT
    '00000000-0000-0000-0000-000000000001',
    'admin',
    'admin@gmail.com',
    '$2a$10$ws2wk7Za1S9DqCQvGXmdJOxShtwQFel68RcvlA6/kI5WSqRI/6gHm', -- hash da senha 'admin123'
    'ADMIN'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE email = 'admin@gmail.com'
);
