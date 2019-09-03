
CREATE TABLE client(id INT AUTO_INCREMENT NOT NULL, name VARCHAR(30), age INT, phone VARCHAR(15), PRIMARY KEY (id));

DROP TABLE client;
CREATE TABLE client
(
    id INT NOT NULL,
    name VARCHAR(30),
    age INT,
    phone VARCHAR(15) DEFAULT 'unknown',
#     DATE datetime NOT NULL ,
    PRIMARY KEY (id)
);

# INSERT INTO client (name, age, phone, DATE) VALUES ('Sashok', 23, 666666, NOW());
SELECT * FROM client;
ALTER TABLE client ADD newcolumn int NULL;
ALTER TABLE client ADD newcolumnnew int NULL AFTER name;
ALTER TABLE client drop column newcolumn, DROP column newcolumnnew;
ALTER TABLE client ADD column XXX VARCHAR(10) DEFAULT 'xxxxxxxx' AFTER phone;
ALTER TABLE client DROP column XXX;

INSERT INTO client (id, name, age, phone) VALUES
(1, 'Alex', 15, 555666),(2, 'Semen', 22, 666777);

INSERT INTO client (id, name, age, phone) VALUES
(4, 'Sashok', 44, 888999);
UPDATE client SET id = 3 WHERE id=4;

CREATE TABLE client
(
    id int not null,
    name VARCHAR(15) NOT NULL ,
    phone VARCHAR(15) DEFAULT 'unknown',
    primary key (id, name)
);

insert into client
(id, name, phone) VALUES
(1, 'Petr', '(095)1616165'),
(2, 'Vasya','(8442)375235');

INSERT INTO client(id, name, phone) VALUES
(1, 'SASHOK', '(412)35467');
SELECT * FROM client;

DROP TABLE client;

CREATE TABLE client
(
id INTEGER NOT NULL ,
name VARCHAR(15) NOT NULL ,
age INTEGER(10) NOT NULL ,
phone VARCHAR(15) default 'unknown'
);

ALTER TABLE client
ADD CONSTRAINT prime_key PRIMARY KEY (id);
DROP TABLE client;

INSERT INTO client (id, name, age, phone) VALUES
(1, 'Petr', 12, 333555),
(2, 'Vaasya', 16, 121212),
(3, 'Sashok', 23, 765653535);
ALTER TABLE client ADD PRIMARY KEY (id, age);




