ALTER TABLE carts
ADD COLUMN customer_id  INT;

ALTER TABLE carts
ADD CONSTRAINT fk_cart_customer
FOREIGN KEY (customer_id)
REFERENCES customer(id)
ON DELETE CASCADE;