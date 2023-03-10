CREATE SEQUENCE first_sequence;
SELECT NEXTVAL('first_sequence');
DROP SEQUENCE first_sequence;

DROP TABLE person;
CREATE TABLE person
(
    id    SERIAL,
    name  VARCHAR,
    age   INT,
    email VARCHAR
);

INSERT INTO person (name, age, email)
VALUES ('John', 39, 'john@gmail.com'),
       ('Tom', 37, 'tom@gmail.com');

-- create table person(
-- id int generated by default as identity);

DROP TABLE person;
CREATE TABLE person
(
    id    INT GENERATED BY DEFAULT AS IDENTITY,
    name  VARCHAR,
    age   INT,
    email VARCHAR
);

INSERT INTO person (name, age, email)
VALUES ('John', 39, 'john@gmail.com'),
       ('Tom', 37, 'tom@gmail.com');
