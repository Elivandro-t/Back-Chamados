CREATE TABLE chamado(
  id serial primary key,
  patrimonio varchar(50) default null,
  equipamento varchar(50) default null,
  issue_id bigint not null,
  foreign KEY (issue_id) references issue(id)
)