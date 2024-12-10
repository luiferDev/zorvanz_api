ALTER TABLE cart_item
ADD COLUMN customer_id INT;

ALTER TABLE cart_item
ADD CONSTRAINT fk_customer
FOREIGN KEY (customer_id)
REFERENCES customer(id)
ON DELETE CASCADE;







    -- TODO: cambiar la migracion sobre cartItem para que sea siga por customer en vez de por cart
    -- TODO: crear la nueva migracion para alterar la tabla y agregar una nueva foreing key para customer en vez de cart
    -- o puede quedar asi pero que tambien haga referencia a la tabla customer


    --TODO también cambiar el Cart para que tambien se conecte con el customer
    -- ademas el customer puede ser un usuario o puede ser un cliente que no este registrado