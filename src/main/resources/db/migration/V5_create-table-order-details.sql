create table order_details (
      id bigint not null auto_increment,
      order_id bigint not null,
      product_id bigint not null,
      quantity int not null,
      unit_price decimal(10,2) not null,
      total_amount decimal(10,2) not null,

      primary key (id),
      foreign key (order_id) references orders(id),
      foreign key (product_id) references product(id)
);