create table PROJECT_WORKER
(
    PROJECT_ID INTEGER not null
        references PROJECT,
    WORKER_ID  INTEGER not null
        references WORKER,
    primary key (PROJECT_ID, WORKER_ID)
);

