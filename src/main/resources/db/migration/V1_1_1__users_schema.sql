create table USERS
(
    ID       BIGINT auto_increment,
    USERNAME CHARACTER VARYING(255) not null
        constraint USERS_USERNAME
            unique,
    constraint USERS_PK
        primary key (ID)
);