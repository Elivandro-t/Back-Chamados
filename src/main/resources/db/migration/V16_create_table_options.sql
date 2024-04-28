create table options(
 id bigint not null auto_increment,
 name varchar(255) default null,
 titulo varchar(255) default null,
 sistemas_id bigint not null,
 primary key(id),
 foreign key(sistemas_id) references sistemas(id)
)