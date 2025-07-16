CREATE TABLE cart(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,

    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);