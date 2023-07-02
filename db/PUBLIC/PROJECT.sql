create table PROJECT
(
    ID           INTEGER auto_increment
        primary key,
    CLIENT_ID    INTEGER
        references CLIENT,
    START_DATE   DATE,
    FINISH_DATE  DATE,
    PROJECT_NAME CHARACTER VARYING(1000)
);

