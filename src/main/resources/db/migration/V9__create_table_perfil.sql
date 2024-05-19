create table perfil(
id serial primary key,
name varchar(50) default null,
ativo tinyint,
user_id bigint not null,
primary key(id),
foreign key(user_id ) references usuario(id)
)
