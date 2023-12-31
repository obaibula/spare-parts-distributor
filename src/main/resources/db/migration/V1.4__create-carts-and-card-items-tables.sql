CREATE TABLE carts(
    user_id BIGINT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(10, 2) DEFAULT 0
);

CREATE TABLE cart_items(
    id BIGSERIAL PRIMARY KEY,
    quantity INTEGER DEFAULT 1,
    cart_id BIGINT NOT NULL REFERENCES carts(user_id) ON DELETE CASCADE,
    part_id BIGINT NOT NULL REFERENCES parts(id) ON DELETE CASCADE,
    CHECK(quantity >= 1),
    CONSTRAINT uc_cart_part UNIQUE (cart_id, part_id)
);