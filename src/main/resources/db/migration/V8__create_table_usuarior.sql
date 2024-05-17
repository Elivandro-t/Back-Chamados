create table usuario(
  id bigint not null auto_increment,
  name varchar(50) default null,
  lastname varchar(50) default null,
  setor varchar(50) default null,
  imagem varchar(255) default null,
  email varchar(255) default null,
  filial bigint not null,
  password varchar(255) default null,
  codigo varchar(50) default null,
  accountLocked tinyint,
  exp bigint not null,
  counts bigint not null,
  primary key(id)
)
