create table customer (
  id bigint primary key auto_increment,
  name varchar(80) not null,
  email varchar(80) not null,
  phone varchar(50) not null,
  address varchar(80) not null

  primary key (id)
);