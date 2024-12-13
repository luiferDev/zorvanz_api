ALTER TABLE orders
ADD COLUMN carts_id bigint;

ALTER  TABLE orders
ADD COLUMN payment_method varchar(25);

ALTER TABLE orders
ADD CONSTRAINT fk_orders_carts_id
FOREIGN KEY (carts_id)
REFERENCES carts(id)
ON DELETE CASCADE;