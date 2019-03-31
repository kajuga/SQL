
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
INSERT INTO udemy_sql_base.contact VALUES (1, 1, 'Иван', 'Иванович', 'Иванов', 20);
INSERT INTO udemy_sql_base.contact VALUES
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