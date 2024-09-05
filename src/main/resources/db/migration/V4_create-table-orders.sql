create table orders (
      id bigint not null auto_increment,
      customer_id bigint not null,
      total_amount decimal(10, 2) not null,
      status varchar(100) not null,

      primary key (id),
      foreign key (customer_id) references customer(id)
);