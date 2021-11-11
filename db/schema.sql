DROP TABLE IF EXISTS accident_rules;
DROP TABLE IF EXISTS accident;
DROP TABLE IF EXISTS accident_type;
DROP TABLE IF EXISTS rules;

CREATE TABLE accident_type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000) NOT NULL
);

CREATE TABLE rules
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000) NOT NULL
);

CREATE TABLE accident
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(2000) NOT NULL,
    text    VARCHAR(2000),
    address VARCHAR(2000) NOT NULL,
    type_id INT           NOT NULL REFERENCES accident_type (id)
);

CREATE TABLE accident_rules
(
    id          SERIAL PRIMARY KEY,
    accident_id INT NOT NULL REFERENCES accident (id),
    rule_id     INT NOT NULL REFERENCES rules (id)
);

INSERT INTO accident_type(name)
VALUES ('Две машины'),
       ('Машина и человек'),
       ('Машина и велосипед');

INSERT INTO rules(name)
VALUES ('Статья. 1'),
       ('Статья. 2'),
       ('Статья. 3');
