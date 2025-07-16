CREATE TABLE cart_item(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    quantity INTEGER NOT NULL,

    cart_id UUID,
    FOREIGN KEY(cart_id) REFERENCES cart(id) ON DELETE CASCADE,

    product_id UUID,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);