CREATE TABLE payment(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    amount NUMERIC(10, 2) NOT NULL,
    status VARCHAR(100),
    paymentDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    order_id UUID,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
);

ALTER TABLE order_item RENAME COLUMN unitPrice TO unit_price;