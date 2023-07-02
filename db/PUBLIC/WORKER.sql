create table WORKER
(
    ID       INTEGER auto_increment
        primary key,
    NAME     CHARACTER VARYING(1000)                        not null,
    BIRTHDAY DATE,
    LEVEL    ENUM ('Trainee', 'Junior', 'Middle', 'Senior') not null,
    SALARY   INTEGER,
    check ((CHAR_LENGTH("NAME") >= 2)
        AND (CHAR_LENGTH("NAME") <= 1000)),
    check (EXTRACT(YEAR FROM "BIRTHDAY") > 1900),
    check (("SALARY" >= 100)
        AND ("SALARY" <= 100000))
);

