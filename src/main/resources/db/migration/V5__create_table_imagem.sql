create table imagem(
    id bigint not null auto_increment,
    name varchar(250) default null,
    imagem_id bigint not null,
    primary key (id),
    foreign KEY (imagem_id) references hardware(id)
)