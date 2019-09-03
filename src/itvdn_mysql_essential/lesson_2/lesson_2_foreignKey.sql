USE car_shop;
# DROP TABLE client;
CREATE TABLE client
(
    id INTEGER AUTO_INCREMENT NOT NULL ,
    name VARCHAR(30) NOT NULL ,
    age VARCHAR(25) NOT NULL ,
    phone VARCHAR(25) DEFAULT 'unknown',
    PRIMARY KEY (id)
);

CREATE TABLE car (
    id INT auto_increment NOT NULL ,
    client_id INT NOT NULL ,
    mark VARCHAR(25) NOT NULL ,
    model VARCHAR(30) NOT NULL ,
    FOREIGN KEY (client_id) references client(id),
    primary key (id)
);

SELECT * FROM car;
SELECT * FROM client;
DROP TABLE car;
DROP TABLE client;

INSERT INTO client (name, age, phone) VALUES
('Petrenko Petr Petrovich', 25, 343666366),
('Ivanchenko Ivan Ivanovich', 33, 778845635),
('Abrashov Oleg Petrovich', 48, 99999999);

INSERT INTO car (client_id, mark, model) VALUES
(1, 'Audi', 'A5'),
(2, 'BMW', 'X6'),
(3, 'Opel', 'Zafira'),
(1, 'Dodge', 'CX12');

# SELECT id, name, age FROM client;

ALTER TABLE car ADD CONSTRAINT fk_client_id
FOREIGN KEY (client_id) REFERENCES client(id);

ALTER TABLE car DROP FOREIGN KEY fk_client_id;

# откатить авто инкремент
ALTER TABLE client AUTO_INCREMENT=0;

