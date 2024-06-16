CREATE TABLE log(
  id serial primary key,
  card_id bigint not null,
  name_usuario_acess varchar(50) default null,
  usuario_id bigint not null,
  msg varchar(250) default null,
  timestamp varchar(255) default null
)