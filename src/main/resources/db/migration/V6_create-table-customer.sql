create table customer(
    id int primary key auto_increment,
    name varchar(20) not null,
    email varchar(80) not null,
    phone_number varchar(15),
    address varchar(100),

    primary key(id)
);