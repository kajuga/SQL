
CREATE TABLE udemy_sql_base.category (
  id SMALLINT PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  active BOOLEAN NOT NULL DEFAULT (TRUE));

CREATE TABLE udemy_sql_base.contact_type (
  id SMALLINT PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  protocol VARCHAR(10) NOT NULL,
  active BOOLEAN NOT NULL DEFAULT (TRUE));

CREATE TABLE udemy_sql_base.contact (
  id INTEGER PRIMARY KEY NOT NULL,
  id_category SMALLINT NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  middle_name VARCHAR(50),
  last_name VARCHAR(50) NOT NULL,
  age SMALLINT NOT NULL DEFAULT (20),
  created TIMESTAMP(0) NOT NULL DEFAULT (now()),
  updated TIMESTAMP (0) DEFAULT (NULL),
  active BOOLEAN NOT NULL DEFAULT (TRUE));

CREATE TABLE udemy_sql_base.contact_value (
  id INTEGER PRIMARY KEY NOT NULL,
  id_contact INTEGER NOT NULL,
  id_contact_type SMALLINT NOT NULL,
  value VARCHAR(100) DEFAULT (NULL),
  created TIMESTAMP(0) NOT NULL DEFAULT (now()),
  updated TIMESTAMP(0) DEFAULT (NULL),
  active BOOLEAN NOT NULL DEFAULT TRUE);

ALTER TABLE udemy_sql_base.contact ADD FOREIGN KEY (id_category) REFERENCES udemy_sql_base.category(id) ON DELETE RESTRICT;
ALTER TABLE udemy_sql_base.contact_value ADD FOREIGN KEY (id_contact_type) REFERENCES udemy_sql_base.contact_type(id) ON DELETE RESTRICT;
ALTER TABLE udemy_sql_base.contact_value ADD FOREIGN KEY (id_contact) REFERENCES udemy_sql_base.contact(id) ON DELETE CASCADE;

INSERT INTO udemy_sql_base.category VALUES (1, 'Друзья'), (2, 'Коллеги');
-- INSERT INTO udemy_sql_base.category VALUES (3, 'NOTHING');
INSERT INTO udemy_sql_base.contact VALUES (1, 1, 'Иван', 'Иванович', 'Иванов', 20),
-- INSERT INTO udemy_sql_base.contact VALUES (5, 6, 'Sashok', 'Sashkovich', 55);
  (2, 1, 'Петр', NULL, 'Петров', 18),
  (3, 2, 'Ирина', 'Викторовна', 'Николаева', 22),
  (4, 2, 'Иван', 'Иванович', 'Иванов', 24);
SELECT * FROM udemy_sql_base.contact;

INSERT INTO udemy_sql_base.contact_type VALUES
  (1, 'Mobile Phone', 'tel:'),
  (2, 'Phone', 'tel:'),
  (3, 'Email', 'email:'),
  (4, 'Skype', 'skype:');

INSERT INTO udemy_sql_base.contact_value VALUES
  (1, 1, 1, '+38(050) 123-45-67'),
  (2, 1, 3, 'ivanov@gmail.com'),
  (3, 1, 4, 'ivan.ivanov'),
  (4, 2, 1, '+38(066) 159-35-87'),
  (5, 3, 2, '365-69-69'),
  (6, 3, 1, '+38(063) 12-22-33'),
  (7, 3, 4, 'irina.nikolaeva');


DELETE FROM udemy_sql_base.contact_value WHERE id = 7;
SELECT * FROM udemy_sql_base.contact_value WHERE id < 4;
SELECT firstname, middle_name, last_name FROM udemy_sql_base.contact;
SELECT DISTINCT firstname, middle_name, last_name FROM udemy_sql_base.contact;
ALTER TABLE udemy_sql_base.contact RENAME COLUMN firstname TO first_name;
SELECT first_name, last_name AS surname, 'Hello' AS txt, age + 100 AS age1, age>18 as adult FROM udemy_sql_base.contact;
SELECT last_name || ' ' || first_name || ' ' || middle_name AS fio, length(last_name) AS last_length, pow(age, 2) FROM udemy_sql_base.contact;
SELECT first_name, last_name, age FROM udemy_sql_base.contact WHERE age BETWEEN 10 AND 30;
SELECT avg(age) FROM udemy_sql_base.contact ;
SELECT first_name AS имя,  count(*) AS количество FROM udemy_sql_base.contact GROUP BY first_name;
SELECT first_name, last_name, count(*) FROM udemy_sql_base.contact GROUP BY first_name, last_name HAVING count(*) > 1;
SELECT first_name, last_name, count(*) FROM udemy_sql_base.contact GROUP BY first_name, last_name HAVING first_name LIKE 'И%';
SELECT first_name, count(8) FROM udemy_sql_base.contact WHERE first_name LIKE 'И%' GROUP BY first_name;
SELECT * FROM udemy_sql_base.contact ORDER BY first_name;
SELECT * FROM udemy_sql_base.contact ORDER BY first_name, last_name, age;
SELECT * FROM udemy_sql_base.contact ORDER BY first_name, last_name DESC ;
SELECT first_name, count(*) as cnt FROM udemy_sql_base.contact GROUP BY first_name ORDER BY cnt ;
SELECT first_name, count(*) as cnt FROM udemy_sql_base.contact GROUP BY first_name ORDER BY cnt DESC;
SELECT cat.name as category, c.first_name, c.middle_name, c.last_name, c.age,
  ct.protocol, ct.name as contact_type, cv.value FROM udemy_sql_base.contact c, udemy_sql_base.contact_value cv,
  udemy_sql_base.category cat, udemy_sql_base.contact_type ct WHERE c.id_category=cat.id AND cv.id_contact = c.id AND cv.id_contact_type = ct.id;

SELECT c1.id XXX, c2.id YYY FROM udemy_sql_base.contact c1, udemy_sql_base.contact c2 WHERE c1.first_name = c2.first_name AND c1.age <> c2.age;

SELECT  cat.name as category, c.age, count(*) as cnt
FROM udemy_sql_base.contact c, udemy_sql_base.contact_value cv, udemy_sql_base.category cat, udemy_sql_base.contact_type ct
WHERE c.id_category=cat.id AND cv.id_contact = c.id
GROUP BY category, age
HAVING count(*) > 4
ORDER BY cnt ASC, category DESC
LIMIT 1
OFFSET 1;

SELECT  cat.name as category, c.age, count(*) as cnt
FROM udemy_sql_base.contact c, udemy_sql_base.contact_value cv, udemy_sql_base.category cat, udemy_sql_base.contact_type ct
WHERE c.id_category=cat.id AND cv.id_contact = c.id
GROUP BY category, age;

SELECT cat.name as category, c.first_name, c.middle_name,
  c.last_name, c.age FROM udemy_sql_base.contact c, udemy_sql_base.category cat WHERE
  c.id_category=cat.id;

SELECT (SELECT name from udemy_sql_base.category cat where cat.id =
c.id_category) as category, c.first_name, c.middle_name,
c.last_name, c.age FROM udemy_sql_base.contact c;
SELECT cv.value FROM udemy_sql_base.contact_value cv WHERE cv.id_contact =
(SELECT id FROM udemy_sql_base.contact WHERE age=20);
SELECT c.first_name, c.middle_name, c.last_name, c.age FROM
  udemy_sql_base.contact c WHERE c.age > (SELECT avg(age) FROM udemy_sql_base.contact);

SELECT * FROM udemy_sql_base.category WHERE id NOT IN (SELECT id_category FROM udemy_sql_base.contact);
SELECT cat.* FROM category cat LEFT OUTER JOIN contact c ON cat.id = c.id_category WHERE c.id_category IS NULL;
