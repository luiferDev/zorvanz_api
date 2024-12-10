create table users (
        id int primary key auto_increment,
        name varchar(20) not null,
        last_name varchar(80) not null,
        password varchar(50) not null,
        user_name varchar(20) not null,
        email varchar(50) not null,
        role varchar(10) not null,

        primary key(id)
);