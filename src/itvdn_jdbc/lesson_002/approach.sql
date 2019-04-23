CREATE TABLE Books (
  book_id MEDIUMINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  price DOUBLE, PRIMARY KEY (book_id));

INSERT INTO Books (name, price) VALUE ('Inferno', 45.0);
INSERT INTO Books (name, price) VALUE ('GHarry Potter', 45.5);
INSERT INTO Books (name, price) VALUE ('It', 25.0);
INSERT INTO Books (name, price) VALUE ('Spartacus', 55.0);
INSERT INTO Books (name, price) VALUE ('Green Mile', 20.6);
INSERT INTO Books (name, price) VALUE ('Solomon key', 5.0);