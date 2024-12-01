create table PRODUCT
(
    ID              BIGINT auto_increment,
    ACCOUNT_NUMBER  CHARACTER VARYING(20) not null
        constraint PRODUCT_ACCOUNT_NUMBER
            unique,
    ACCOUNT_BALANCE NUMERIC(20, 2)        not null,
    PRODUCT_TYPE    CHARACTER VARYING     not null,
    USER_ID         BIGINT                not null,
    constraint PRODUCT_PK
        primary key (ID),
    constraint PRODUCT_USERS_ID_FK
        foreign key (USER_ID) references USERS
);