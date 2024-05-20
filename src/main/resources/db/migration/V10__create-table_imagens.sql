create table imagens(
 id serial primary key,
 name varchar(1000) default null,
 chamado_id bigint not null,
 foreign key(chamado_id) references chamado(id)
)