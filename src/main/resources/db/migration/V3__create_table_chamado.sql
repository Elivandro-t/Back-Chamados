CREATE TABLE chamado(
  id bigint not null auto_increment,
  patrimonio varchar(50) default null,
  equipamento varchar(50) default null,
  issue_id bigint not null,
  primary key(id),
  foreign KEY (issue_id) references issue(id)

)