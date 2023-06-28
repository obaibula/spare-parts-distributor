INSERT INTO users (created_at, email, phone, first_name, last_name,
password, balance, discount, payment_deferment, credit_limit, shipment_status)
VALUES
(CURRENT_DATE, 'user1@mail.com', '+380501339531', 'John', 'Doe', 'PAssWORD1#', 0, 10, 7, 5000, 'ALLOWED'),
(CURRENT_DATE, 'user2@example.com', '+380732264366', 'Ann', 'Roy', 'PaSsWoRd1#', 0, 10, 7, 5000, 'ALLOWED');

INSERT INTO carts (user_id, created_at, total_price)
VALUES
(1, CURRENT_TIMESTAMP, 0),
(2, CURRENT_TIMESTAMP, 0);

INSERT INTO parts (part_number, name, brand, category, price, stock_quantity, available_quantity, delivery_time, image)
VALUES
('P123456', 'Example Part', 'Example Brand', 'Example Category', 9.99, 10, 10, 3, 'example-image.jpg'),
('P324563', 'Example Part', 'Example Brand', 'Example Category', 9.99, 10, 10, 3, 'example-image.jpg'),
('X212121', 'Example Part', 'Example Brand', 'Example Category', 9.99, 10, 10, 3, 'example-image.jpg'),
('Z3424123', 'Example Part', 'Example Brand', 'Example Category', 9.99, 10, 10, 3, 'example-image.jpg'),
('Y543545', 'Example Part', 'Example Brand', 'Example Category', 9.99, 10, 10, 3, 'example-image.jpg');

INSERT INTO cart_items (cart_id, part_id)
VALUES
(1, 3),
(1, 4),
(1, 5),
(2, 2);



