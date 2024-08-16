create table compras(
   id serial primary key,
   usuarioid bigint not null,
   contato varchar(100) default null,
   status_andamento varchar(100) default null,
   hora_aceito varchar(100) default null
)