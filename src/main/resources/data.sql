INSERT INTO PERSON (id, first_name, last_name, email)
VALUES (11, 'Amsidh', 'Lokhande', 'amsidh@example.com'),
       (12, 'Adithi', 'Lokhande', 'adithi@example.com'),
       (13, 'Aditya', 'Lokhande', 'aditya@example.com'),
       (14, 'Anjali', 'Lokhande', 'anjali@example.com'),
       (15, 'Raju', 'Lokhande', 'raju@example.com');

INSERT INTO ADDRESS (id, city, street, state, postal_code, person_id)
VALUES (1, 'Pune', 'PWC', 'MH', '412105', 11),
       (2, 'Pune', 'PWC', 'MH', '412105', 12),
       (3, 'Pune', 'PWC', 'MH', '412105', 13),
       (4, 'Pune', 'PWC', 'MH', '412105', 14),
       (5, 'Pune', 'PWC', 'MH', '412105', 15);
