create table sistemas(
 id bigint not null auto_increment,
 name varchar(255) default null,
 imagem varchar(255) not null,
 titulo varchar(255) default null,
 primary key(id)
)