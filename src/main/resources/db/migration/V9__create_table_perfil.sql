create table perfil(
id serial primary key,
name varchar(50) default null,
ativo boolean,
user_id bigint not null,
foreign key(user_id ) references usuario(id)
)
