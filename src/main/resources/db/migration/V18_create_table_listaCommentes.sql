create table lista_commentarios(
id bigint not null auto_increment,
comments varchar(255) not null,
usuario varchar(50) default null,
email varchar(255) default null,
comments_id bigint not null,
primary key(id),
foreign key(comments_id) references comments(id)
)