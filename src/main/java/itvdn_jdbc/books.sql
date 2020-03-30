CREATE TABLE Books (bookId SERIAL PRIMARY KEY not null , name VARCHAR(30) NOT NULL , price DEC);
INSERT INTO Books (name, price) VALUES ('Inferno', 45.00);
INSERT INTO Books (name, price) VALUES ('Harry Potter', 45.50);
INSERT INTO Books (name, price) VALUES ('It', 25.00);
INSERT INTO Books (name, price) VALUES ('Spartacus', 55.03);
INSERT INTO Books (name, price) VALUES ('Green Mile', 20.60);
INSERT INTO Books (name, price) VALUES ('Solomon Key', 5.00);

-- DROP TABLE Books;

