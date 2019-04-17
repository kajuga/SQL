CREATE TABLE PROSELYTE_TUTORIALS.developers (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  specialty VARCHAR(50) NOT NULL,
  salary INT NOT NULL,
  PRIMARY KEY (id));

INSERT INTO PROSELYTE_TUTORIALS.developers (name, specialty, salary) VALUES ('Proselyte', 'Java', '2000');
INSERT INTO PROSELYTE_TUTORIALS.developers (name, specialty, salary) VALUES ('Peter','C++', '3000');
INSERT INTO PROSELYTE_TUTORIALS.developers (name, specialty, salary) VALUES ('AsyaSmile', 'UI/UX', '2000');

UPDATE PROSELYTE_TUTORIALS.developers SET salary = 2000 WHERE name = 'Proselyte';
UPDATE PROSELYTE_TUTORIALS.developers SET salary = 3000 WHERE id = 2;
UPDATE PROSELYTE_TUTORIALS.developers SET salary = 2000 WHERE id = 3;