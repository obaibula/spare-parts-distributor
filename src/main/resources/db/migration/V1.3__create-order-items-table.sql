CREATE TABLE order_items(
    id BIGSERIAL PRIMARY KEY,
    quantity INTEGER DEFAULT 1,
    order_id BIGINT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    product_id BIGINT NOT NULL REFERENCES products(id) ON DELETE CASCADE
    CHECK(quantity >= 1)
)