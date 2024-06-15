CREATE TABLE log(
  id serial primary key,
  name_usuario_acess varchar(50) default null,
  usuario_id bigint not null,
  issue_id bigint not null,
  timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)