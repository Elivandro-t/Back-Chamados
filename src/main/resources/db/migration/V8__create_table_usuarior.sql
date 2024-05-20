create table usuario(
  id serial primary key,
  name varchar(50) default null,
  lastname varchar(50) default null,
  setor varchar(50) default null,
  imagem varchar(255) default null,
  email varchar(255) default null,
  filial bigint not null,
  password varchar(255) default null,
  codigo varchar(50) default null,
  account_locked boolean,
  exp bigint not null,
  counts bigint not null
)
