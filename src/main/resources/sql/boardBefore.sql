drop table if exists tbl_reply;

drop table if exists tbl_board_attach;

drop table if exists tbl_board;

create table tbl_board
(
    bno      int auto_increment
        primary key,
    title    varchar(300)                          not null,
    content  text                                  not null,
    writer   varchar(50)                           not null,
    regDate  timestamp default current_timestamp() null,
    modDate  timestamp default current_timestamp() null,
    replyCnt int       default 0                   null,
    tag      varchar(500)                          null,
    viewCnt  int       default 0                   null
);

create table tbl_board_attach
(
    bno      int           null,
    fileName varchar(200)  not null,
    ord      int default 0 null,
    constraint fk_board
        foreign key (bno) references tbl_board (bno)
);

create index idx_board
    on tbl_board_attach (bno desc, ord asc);

create table tbl_reply
(
    rno     int auto_increment
        primary key,
    bno     int                                   not null,
    reply   varchar(500)                          not null,
    replyer varchar(50)                           not null,
    regDate timestamp default current_timestamp() null,
    modDate timestamp default current_timestamp() null,
    constraint fk_board_reply
        foreign key (bno) references tbl_board (bno)
);

create index idx_board
    on tbl_reply (bno desc, rno asc);
