USE ShopDB;

DROP TABLE JoinTest1;
DROP TABLE JoinTest2;

CREATE TABLE JoinTest1
(
    id_jt1 int,
    name   varchar(50)
);


CREATE TABLE JoinTest2
(
    id_jt2 int,
    name   varchar(50)
);

INSERT JoinTest1
VALUES (1, 'one'),
       (2, 'two'),
       (3, 'three'),
       (4, 'four'),
       (5, 'five'),
       (9, 'nine'),
       (10, 'ten');

INSERT JoinTest2
VALUES (1, 'one'),
       (2, 'two'),
       (3, 'three'),
       (4, 'four'),
       (5, 'five'),
       (6, 'six'),
       (7, 'seven'),
       (8, 'eight');


SELECT * FROM JoinTest2 INNER JOIN JoinTest1 ON id_jt2=id_jt1;

select *
from JoinTest1;
select *
from JoinTest2;


SELECT * FROM JoinTest1 LEFT JOIN JoinTest2 ON id_jt1=id_jt2
UNION
SELECT * FROM JoinTest1 RIGHT JOIN JoinTest2 on id_jt1=id_jt2;



SELECT * FROM JoinTest2 INNER JOIN JoinTest1 ON id_jt1 = id_jt2;



SELECT *FROM JoinTest2 LEFT OUTER JOIN JoinTest1 ON id_jt1 = id_jt2;
SELECT * FROM JoinTest2 RIGHT OUTER JOIN JoinTest1 ON id_jt1 = id_jt2;



SELECT *
FROM JoinTest1
         CROSS JOIN JoinTest2;

