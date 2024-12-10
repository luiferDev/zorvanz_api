ALTER TABLE cart
ADD COLUMN customer_id  INT

ALTER TABLE cart
ADD CONSTRAINT fk_cart_customer
FOREIGN KEY (customer_id)
REFERENCES customer(id)
ON DELETE CASCADE;