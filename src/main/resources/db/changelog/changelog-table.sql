-- liquibase formatted sql

-- changeset obaibula:1
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- preconditions-sql-check expectedResult:0 select count(1) from pg_tables where tablename = 'users'
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    created_at DATE DEFAULT CURRENT_DATE,
    email VARCHAR(70) NOT NULL UNIQUE,
    phone VARCHAR(17),
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    password VARCHAR(50) NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0,
    discount INTEGER DEFAULT 0,
    payment_deferment INTEGER DEFAULT 0,
    credit_limit DECIMAL(10, 2) DEFAULT 0,
    shipment_status VARCHAR(30) DEFAULT 'ALLOWED',

    CHECK(discount >= 0 AND discount <= 99),
    CHECK(payment_deferment >= 0),
    CHECK(credit_limit >= 0),
    CHECK(balance >= credit_limit * -1)
)
-- rollback drop table users cascade;

-- changeset obaibula:2
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- preconditions-sql-check expectedResult:0 select count(1) from pg_tables where tablename = 'orders'
CREATE TABLE orders(
    id BIGSERIAL PRIMARY KEY,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(10, 2) DEFAULT 0,
    status VARCHAR(20) DEFAULT 'NEW',
    shipping_address VARCHAR(100) NOT NULL,
    user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE
)
-- rollback drop table orders cascade;

-- changeset obaibula:3
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- preconditions-sql-check expectedResult:0 select count(1) from pg_tables where tablename = 'parts'
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
-- rollback drop table parts cascade;

-- changeset obaibula:4
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- preconditions-sql-check expectedResult:0 select count(1) from pg_tables where tablename = 'order_items'
CREATE TABLE order_items(
    id BIGSERIAL PRIMARY KEY,
    quantity INTEGER DEFAULT 1,
    order_id BIGINT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    part_id BIGINT NOT NULL REFERENCES parts(id) ON DELETE CASCADE
    CHECK(quantity >= 1)
)
-- rollback drop table order_items cascade;

-- changeset obaibula:5
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- preconditions-sql-check expectedResult:0 select count(1) from pg_tables where tablename = 'carts'
CREATE TABLE carts(
    user_id BIGINT PRIMARY KEY REFERENCES users(id) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_price DECIMAL(10, 2) DEFAULT 0
);
-- rollback drop table carts cascade;

-- changeset obaibula:6
-- preconditions onFail:MARK_RAN onError:MARK_RAN
-- preconditions-sql-check expectedResult:0 select count(1) from pg_tables where tablename = 'cart_items'
CREATE TABLE cart_items(
    id BIGSERIAL PRIMARY KEY,
    quantity INTEGER DEFAULT 1,
    cart_id BIGINT NOT NULL REFERENCES carts(user_id) ON DELETE CASCADE,
    part_id BIGINT NOT NULL REFERENCES parts(id) ON DELETE CASCADE,
    CHECK(quantity >= 1),
    CONSTRAINT uc_cart_part UNIQUE (cart_id, part_id)
);
-- rollback drop table cart_items cascade;