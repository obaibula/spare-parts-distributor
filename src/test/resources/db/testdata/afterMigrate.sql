INSERT INTO users (created_at, email, phone, first_name, last_name,
password, balance, discount, payment_deferment, credit_limit, shipment_status)
VALUES (CURRENT_DATE, 'example@example.com', '1234567890', 'John', 'Doe', 'password123', 0, 10, 7, 5000, 'ALLOWED');

INSERT INTO carts (user_id, created_at, total_price)
VALUES (1, CURRENT_TIMESTAMP, 0);

INSERT INTO parts (part_number, name, brand, category, price, stock_quantity, available_quantity, delivery_time, image)
VALUES ('P123456', 'Example Part', 'Example Brand', 'Example Category', 9.99, 10, 10, 3, 'example-image.jpg');


