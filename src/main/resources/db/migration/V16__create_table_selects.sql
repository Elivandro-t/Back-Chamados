create table options(
 id serial primary key,
 name varchar(255) default null,
 titulo varchar(255) default null,
 sistema_id bigint not null,
 foreign key(sistema_id) references sistemas(id)
)