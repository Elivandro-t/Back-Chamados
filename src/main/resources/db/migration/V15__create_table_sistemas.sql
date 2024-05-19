create table sistemas(
 id serial primary key,
 name varchar(255) default null,
 imagem varchar(255) not null,
 titulo varchar(255) default null,
 primary key(id)
)