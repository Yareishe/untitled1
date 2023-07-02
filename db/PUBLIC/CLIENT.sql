create table CLIENT
(
    ID   INTEGER auto_increment
        primary key,
    NAME CHARACTER VARYING(1000) not null,
    check ((CHAR_LENGTH("NAME") >= 2)
        AND (CHAR_LENGTH("NAME") <= 1000))
);

