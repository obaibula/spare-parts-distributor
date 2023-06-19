CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    created_at DATE DEFAULT CURRENT_DATE,
    email VARCHAR(70) NOT NULL UNIQUE,
    phone VARCHAR(25) UNIQUE,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    password VARCHAR(50) NOT NULL,
    balance DECIMAL DEFAULT 0,
    discount INTEGER DEFAULT 0,
    payment_deferment INTEGER DEFAULT 0,
    credit_limit DECIMAL DEFAULT 0,
    shipment_status VARCHAR(30) DEFAULT 'ALLOWED',

    CHECK(discount >= 0 AND discount <= 99),
    CHECK(payment_deferment >= 0),
    CHECK(credit_limit >= 0),
    CHECK(balance >= 0)
)