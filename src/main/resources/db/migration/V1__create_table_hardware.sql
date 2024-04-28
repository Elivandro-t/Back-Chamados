create table hardware(
   id bigint not null auto_increment,
   usuarioid bigint not null,
   filial bigint not null,
   servico varchar(50) default null,
   primary key (id)
);