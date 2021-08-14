insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('admin', '$2a$12$PqCz6Y6ITIDRhUamCusUL.9bxGabBLxV/0q8BprFcKWzp3MPbCHRO', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Kevin', '$2a$12$PqCz6Y6ITIDRhUamCusUL.9bxGabBLxV/0q8BprFcKWzp3MPbCHRO', 1);

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
('Test1', 2, 4, 'male', 'speciality' ),
('Test2', 2, 5, 'female', 'class' ),
('Test3', 2, 6, 'male', 'speciality' ),
('Test4', 2, 7, 'female', 'class' ),
('Test5', 2, 8, 'male', 'speciality' ),
('Test6', 2, 9, 'female', 'class' ),
('Test7', 2, 10, 'male', 'speciality' ),
('Test8', 2, 11, 'female', 'class' ),
('Test9', 2, 12, 'male', 'speciality' ),
('Test10', 2, 1, 'female', 'class' ),
('Test11', 2, 2, 'male', 'speciality' ),
('Test12', 2, 3, 'female', 'class' ),
('Test13', 2, 4, 'male', 'speciality' ),
('Test14', 2, 5, 'female', 'class' ),
('Test15', 2, 6, 'male', 'speciality' ),
('Test16', 2, 7, 'female', 'class' ),
('Test17', 2, 8, 'male', 'speciality' ),
('Test18', 2, 9, 'female', 'class' ),
('Test19', 2, 10, 'male', 'speciality' ),
('Test20', 2, 11, 'female', 'class' ),
('Test21', 2, 12, 'male', 'class' ),
('Test22', 2, 1, 'female', 'class' ),
('Test23', 2, 2, 'male', 'speciality' ),
('Test24', 2, 3, 'female', 'class' ),
('Test25', 2, 4, 'male', 'class' ),
('Test26', 2, 5, 'female', 'speciality' ),
('Test27', 2, 6, 'male', 'speciality' ),
('Test28', 2, 7, 'female', 'speciality' ),
('Test29', 2, 8, 'male', 'speciality' ),
('Test30', 2, 9, 'female', 'class' ),
('Test31', 2, 10, 'male', 'class' ),
('Test32', 2, 1, 'female', 'speciality' ),
('Test33', 2, 2, 'male', 'class' ),
('Test34', 2, 3, 'female', 'speciality' ),
('Test35', 2, 4, 'male', 'class' ),
('Test36', 2, 5, 'female', 'class' ),
('Test37', 2, 6, 'male', 'class' ),
('Test38', 2, 7, 'female', 'speciality' ),
('Test39', 2, 8, 'male', 'class' );
