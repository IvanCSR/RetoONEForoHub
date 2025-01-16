create table topicos(
    id bigint not null auto_increment,
    titulo varchar(200) not null unique,
    mensaje varchar(300) not null unique,
    fechaCreacion datetime not null,
    status tinyint,
    autor varchar(100) not null,
    curso varchar(100) not null,
    primary key(id)
);