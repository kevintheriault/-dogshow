create table SEC_USER
(
    userId           BIGINT NOT NULL Primary Key AUTO_INCREMENT,
    userName         VARCHAR(36) NOT NULL UNIQUE,
    encryptedPassword VARCHAR(128) NOT NULL,
    ENABLED           BIT NOT NULL
) ;


create table SEC_ROLE
(
    roleId   BIGINT NOT NULL Primary Key AUTO_INCREMENT,
    roleName VARCHAR(30) NOT NULL UNIQUE
) ;


create table USER_ROLE
(
    ID      BIGINT NOT NULL Primary Key AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    roleId BIGINT NOT NULL
);

alter table USER_ROLE
    add constraint USER_ROLE_UK unique (userId, roleId);

alter table USER_ROLE
    add constraint USER_ROLE_FK1 foreign key (userId)
        references SEC_USER (userId);

alter table USER_ROLE
    add constraint USER_ROLE_FK2 foreign key (roleId)
        references SEC_ROLE (roleId);

CREATE TABLE breed
(
    breedId BIGINT NOT NULL Primary Key AUTO_INCREMENT,
    breedName VARCHAR(25) NOT NULL,
    breedType VARCHAR(25) NOT NULL
);

CREATE TABLE dog
(
    dogID BIGINT NOT NULL Primary Key AUTO_INCREMENT,
    dogName VARCHAR(25) NOT NULL,
    ownerId BIGINT NOT NULL,
    breedId BIGINT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    pathway VARCHAR(15) NOT NULL,
    CONSTRAINT ownerFK FOREIGN KEY (ownerId) REFERENCES sec_user,
    CONSTRAINT breedFK FOREIGN KEY (breedId) REFERENCES breed
);