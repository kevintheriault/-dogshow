insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$RvCYgH2cbecTsKzmVS77m.yOlYkrJrdN2MKtyxH731/0ZFCoFfQ2C', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Kevin', '$2a$10$RvCYgH2cbecTsKzmVS77m.yOlYkrJrdN2MKtyxH731/0ZFCoFfQ2C', 1);

insert into sec_role (roleName)
values ('ROLE_ADMIN');

insert into sec_role (roleName)
values ('ROLE_USER');

insert into user_role (userId, roleId)
values (1, 1);

insert into user_role (userId, roleId)
values (1, 2);

insert into user_role (userId, roleId)
values (2, 2);

INSERT INTO breed (breedname, breedtype)
VALUES
       ('Labrador Retriever', 'Sporting'),
       ('German Shepherd', 'Herding'),
       ('Golden Retriever', 'Sporting'),
       ('French Bulldog', 'Non-Sporting'),
       ('Bulldog', 'Non-Sporting'),
       ('Beagle', 'Hound'),
       ('Whippet', 'Hound'),
       ('American Foxhound', 'Hound'),
       ('Bull Terrier', 'Terrier'),
       ('Boxer', 'Working'),
       ('Dachshund', 'Hound'),
       ('Australian Shepherd', 'Herding'),
       ('Collie', 'Herding'),
       ('Akita', 'Working'),
       ('Bullmastif', 'Working');


INSERT INTO dog (DOGNAME, OWNERID, BREEDID, GENDER, PATHWAY)
VALUES
('Jazz', 2, 3, 'female', 'class' ),
('Test1', 1, 4, 'female', 'speciality' ),
('Test2', 1, 5, 'female', 'class' ),
('Test3', 1, 6, 'female', 'speciality' ),
('Test4', 1, 7, 'female', 'class' ),
('Test5', 1, 8, 'male', 'speciality' ),
('Test6', 1, 9, 'female', 'class' ),
('Test7', 1, 10, 'male', 'speciality' ),
('Test8', 1, 11, 'male', 'class' ),
('Test9', 1, 12, 'female', 'speciality' ),
('Test10', 2, 1, 'male', 'class' ),
('Test11', 2, 2, 'male', 'speciality' ),
('Test12', 2, 3, 'female', 'class' ),
('Test13', 2, 4, 'female', 'speciality' ),
('Test14', 2, 5, 'female', 'class' ),
('Test15', 2, 6, 'male', 'speciality' ),
('Test16', 2, 7, 'male', 'class' ),
('Test17', 2, 8, 'male', 'speciality' ),
('Test18', 2, 9, 'male', 'class' ),
('Test19', 1, 10, 'female', 'speciality' ),
('Test20', 1, 11, 'female', 'class' ),
('Test21', 1, 12, 'male', 'class' ),
('Test22', 1, 1, 'male', 'class' ),
('Test23', 1, 2, 'female', 'speciality' ),
('Test24', 1, 3, 'male', 'class' ),
('Test25', 1, 4, 'male', 'class' ),
('Test26', 1, 5, 'male', 'speciality' ),
('Test27', 1, 6, 'male', 'speciality' ),
('Test28', 1, 7, 'female', 'speciality' ),
('Test29', 2, 8, 'female', 'speciality' ),
('Test30', 2, 9, 'female', 'class' ),
('Test31', 2, 10, 'female', 'class' ),
('Test32', 2, 1, 'female', 'speciality' ),
('Test33', 2, 2, 'female', 'class' ),
('Test34', 2, 3, 'female', 'speciality' ),
('Test35', 2, 4, 'female', 'class' ),
('Test36', 2, 5, 'female', 'class' ),
('Test37', 2, 6, 'male', 'class' ),
('Test38', 2, 7, 'male', 'speciality' ),
('Test39', 2, 8, 'male', 'class' );
