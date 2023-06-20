CREATE TABLE parts(
    id BIGSERIAL PRIMARY KEY,
    part_number VARCHAR(60) NOT NULL,
    name VARCHAR(60) NOT NULL,
    brand VARCHAR(60) NOT NULL,
    category VARCHAR(60) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock_quantity INTEGER DEFAULT 0,
    available_quantity INTEGER DEFAULT 0,
    delivery_time INTEGER DEFAULT 0,
    image VARCHAR(240) NOT NULL,
    CHECK(delivery_time >= 0),
    -- We cannot have the brand with two identical part numbers
    CONSTRAINT uc_parts_brand_part_number UNIQUE (brand, part_number)
)