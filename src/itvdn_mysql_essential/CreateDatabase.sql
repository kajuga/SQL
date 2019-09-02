DROP DATABASE  car_shop;
CREATE DATABASE car_shop;
USE car_shop;

CREATE TABLE client(id INT AUTO_INCREMENT NOT NULL, name VARCHAR(30), age INT, phone VARCHAR(15), PRIMARY KEY (id));

INSERT INTO client(name, age, phone) VALUES ('aaa', 23, '833333');
INSERT INTO client(name, age, phone) VALUES ('bbb', 44, '4343434');
INSERT INTO client(name, age, phone)  VALUES ('ccc', 14, '35566778');
insert into client(name, age, phone) VALUES ('ddd', 55, '66666664');
insert into client(name, age, phone) VALUES ('ddd', 11, '811111111');

DROP TABLE client;


CREATE TABLE car (
    car_id int AUTO_INCREMENT NOT NULL,
    mark VARCHAR(20) NOT NULL,
    model VARCHAR(20) NOT NULL,
    speed int NOT NULL,
    price INT NOT NULL,
    PRIMARY KEY (car_id)
);

INSERT INTO car(mark, model, speed, price) VALUES ('BMW', '320d', 225, 36000);
INSERT INTO car(mark, model, speed, price) VALUES ('Audi', 'a5', 250, 50000);
INSERT INTO car(mark, model, speed, price) VALUES ('Porsche', '911', 300, 100000);
INSERT INTO car(mark, model, speed, price) VALUES ('BMW', '512d', 310, 70000);
INSERT INTO car(mark, model, speed, price) VALUES ('Porsche', '526', 330, 120000);
INSERT INTO car(mark, model, speed, price) VALUES ('BMW', '720i', 350, 150000);


DROP TABLE car;


SELECT price, mark, model FROM car where price > 40000;
SELECT * FROM car_shop.client WHERE id =2;
SELECT * FROM car_shop.client WHERE id >2;
SELECT * FROM client WHERE id BETWEEN 2 AND 5 ORDER BY phone;
SELECT * FROM client WHERE name = 'aaa' OR name='ddd';
SELECT name, age FROM client WHERE NOT name ='ddd' AND NOT age=14;
SELECT name,age FROM client WHERE phone LIKE '8%';
SELECT name,age FROM client WHERE phone LIKE '%8';
SELECT name, age FROM client WHERE age IN (23, 14, 55, 11);

SELECT mark, model FROM car WHERE speed > 10 ORDER BY car_id DESC;
SELECT model, mark FROM car WHERE price IN (50000, 100000);
SELECT mark, SUM(speed) FROM car WHERE speed > 100 GROUP BY mark;
SELECT car_id AS ID, SUM(speed) AS Total FROM car WHERE speed in (300, 350, 330, 225) GROUP BY ID;
SELECT COUNT(*) AS Emp FROM car;
SELECT mark, avg(speed) AS AVERAGE_SPEED FROM car WHERE speed > 300 GROUP BY mark;
SELECT mark as MARK, count(*) AS TOTAL FROM car group by MARK HAVING COUNT(*) > 1;

INSERT client (name, age, phone) VALUES ('Max', 66, 12131313);
SELECT * FROM client;
UPDATE client SET age=99 WHERE name='Max';
UPDATE client SET name = 'MaxNew', age = 15, phone = 16161616 WHERE name='Max';
DELETE FROM client WHERE name='aaa';
DROP table client;