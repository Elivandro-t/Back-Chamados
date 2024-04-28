create table imagens(
 id bigint not null auto_increment,
 name varchar(1000) default null,
 chamado_id bigint not null,
 primary key(id),
 foreign key(chamado_id) references chamado(id)
)