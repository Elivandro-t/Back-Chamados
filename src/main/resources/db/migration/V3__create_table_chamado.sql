CREATE TABLE chamado(
  id bigint not null auto_increment,
  chamadoid varchar(50) null null,
  titulo varchar(50) default null,
  setor varchar(50) default null,
  patrimonio varchar(50) default null,
  equipamento varchar(50) default null,
  status varchar(50) default null,
  ativo tinyint,
  descricao varchar(250) default null,
  image varchar(250) default null,
  data varchar(50) default null,
  hardware_id bigint not null,
  usuario_id bigint not null,
  usuario varchar(50) not null,
  tecnicoid bigint not null,
  tecnico_responsavel varchar(50) default null,
  primary key(id),
  foreign KEY (hardware_id) references hardware(id)

)