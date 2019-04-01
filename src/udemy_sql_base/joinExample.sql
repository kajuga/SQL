CREATE TABLE udemy_sql_base.city (
  id integer NOT NULL,
  city_name varchar(10) NOT NULL
) WITH (oids = false);

CREATE TABLE udemy_sql_base.person (
  name varchar(10) NOT NULL,
  id_city integer
) WITH (oids = false);

INSERT INTO udemy_sql_base.city (id, city_name)
VALUES (2, 'Киев');
INSERT INTO udemy_sql_base.city (id, city_name)
VALUES (1, 'Харьков');
INSERT INTO udemy_sql_base.city (id, city_name)
VALUES (3, 'Одесса');

INSERT INTO udemy_sql_base.person (name, id_city)
VALUES ('Иван', 1);
INSERT INTO udemy_sql_base.person (name, id_city)
VALUES ('Сергей', 2);
INSERT INTO udemy_sql_base.person (name, id_city)
VALUES ('Николай', 1);
INSERT INTO udemy_sql_base.person (name, id_city)
VALUES ('Дмитрий', 4);

ALTER TABLE ONLY udemy_sql_base.person
ADD CONSTRAINT person_pkey
PRIMARY KEY (name);

ALTER TABLE ONLY udemy_sql_base.city
ADD CONSTRAINT city_pkey
PRIMARY KEY (id);
SELECT * FROM udemy_sql_base.person p INNER JOIN udemy_sql_base.city c ON p.id_city = c.id;
SELECT * FROM udemy_sql_base.person p CROSS JOIN udemy_sql_base.city c;
SELECT * FROM udemy_sql_base.person p LEFT OUTER JOIN udemy_sql_base.city c ON p.id_city = c.id;
SELECT * FROM udemy_sql_base.person p RIGHT OUTER JOIN udemy_sql_base.city c ON p.id_city = c.id;

SELECT * FROM udemy_sql_base.person p, udemy_sql_base.city c WHERE p.id_city = c.id UNION
SELECT p.*, NULL as id, NULL as city_name FROM udemy_sql_base.person p WHERE id_city NOT IN (SELECT id FROM udemy_sql_base.city)