create table card(
 card_id serial primary key,
 titulo varchar(50) default null,
 subtitulo varchar(250) default null,
 img varchar(250) default null,
 primary key(card_id)
)