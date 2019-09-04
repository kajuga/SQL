USE ShopDB;

DROP TABLE SubTest1;
DROP TABLE SubTest2;

CREATE TABLE SubTest1
(
    id1  int,
    name varchar(50)
);


CREATE TABLE SubTest2
(
    id2  int,
    name varchar(50)
);


INSERT SubTest1
VALUES (1, 'one'),
       (2, 'two'),
       (3, 'three'),
       (4, 'four'),
       (5, 'five'),
       (9, 'nine'),
       (10, 'ten');


INSERT SubTest2
VALUES (1, 'one'),
       (2, 'two'),
       (3, 'three'),
       (4, 'four'),
       (5, 'five'),
       (6, 'six'),
       (7, 'seven'),
       (8, 'eight');

SELECT *
FROM SubTest1;
SELECT *
FROM SubTest2;


SELECT *
FROM SubTest1
WHERE id1 IN
      (SELECT id2 FROM SubTest2);



SELECT *
FROM SubTest1
WHERE id1 =
      (SELECT id2 FROM SubTest2 WHERE name='four');



SELECT *
FROM SubTest1
WHERE id1 =
      (SELECT id2 FROM SubTest2 WHERE name = 'four');


USE car_shop;

SELECT name, phone
FROM client
WHERE age = 75;

SELECT name, phone
FROM client
WHERE age = (SELECT MAX(age) FROM client);