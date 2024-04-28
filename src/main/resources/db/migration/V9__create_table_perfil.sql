create table perfil(
id bigint not null auto_increment,
name varchar(50) default null,
ativo tinyint,
user_id bigint not null,
primary key(id),
foreign key(user_id ) references usuario(id)
)
