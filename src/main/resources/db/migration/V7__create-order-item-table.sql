CREATE TABLE order_item(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    quantity INTEGER NOT NULL,
    unitPrice NUMERIC(10, 2) NOT NULL,

    product_id UUID,
    FOREIGN KEY(product_id) REFERENCES product(id) ON DELETE CASCADE,

    order_id UUID,
    FOREIGN KEY(order_id) REFERENCES orders(id) ON DELETE CASCADE
);