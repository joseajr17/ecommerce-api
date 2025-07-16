CREATE TABLE orders(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    total_amount NUMERIC(10, 2) NOT NULL,
    status VARCHAR(100),
    createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    user_id UUID,
    FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE
);