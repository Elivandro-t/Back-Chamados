create table card(
 card_id bigint not null auto_increment,
 titulo varchar(50) default null,
 subtitulo varchar(250) default null,
 img varchar(250) default null,
 primary key(card_id)
)