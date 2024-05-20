create table issue(
   id serial primary key,
   usuarioid bigint not null,
   filial bigint not null,
   servico varchar(50) default null
)