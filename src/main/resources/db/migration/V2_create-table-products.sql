create table products (
      id bigint not null auto_increment,
      nombre varchar(100) not null,
      descripcion varchar(1000) not null,
      precio numeric(10, 2) not null,
      categoria_id bigint not null,
      stock int not null,
      popularity numeric(50, 2),
      image_url varchar(255),

      primary key (id),
      foreign key (categoria_id) references categories(id)
);