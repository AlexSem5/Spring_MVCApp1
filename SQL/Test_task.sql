CREATE TABLE company
(
    id   INTEGER NOT NULL,
    name CHARACTER VARYING,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id         INTEGER NOT NULL,
    name       CHARACTER VARYING,
    company_id INTEGER,
    FOREIGN KEY (company_id)
        REFERENCES company (id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name)
VALUES (1, 'job4j'),
       (2, 'kot4j'),
       (3, 'romashka'),
       (4, 'apple'),
       (5, 'google');

INSERT INTO person(id, name, company_id)
VALUES (1, 'ivan', 1),
       (2, 'tom', 1),
       (3, 'bob', 1),
       (4, 'fed', 2),
       (5, 'mark', 2),
       (6, 'tim', 2),
       (7, 'vasiliy', 3),
       (8, 'brad', 4),
       (9, 'nik', 5);

SELECT p.name, c.name
FROM person p
         INNER JOIN company c ON c.id = p.company_id
WHERE c.id <> 5;

SELECT COUNT(P.id) c
FROM company C
         INNER JOIN person P ON C.id = P.company_id
GROUP BY c.name;

/*First solution*/
SELECT C.name company_name, COUNT(P.id) people_count
FROM company C
         INNER JOIN person P ON C.id = P.company_id
GROUP BY c.name
HAVING COUNT(P.id) = (SELECT MAX(alias)
                      FROM (SELECT COUNT(P.id) alias
                            FROM company C
                                     INNER JOIN person P ON C.id = P.company_id
                            GROUP BY c.name) subquery);

/*Second solution*/
SELECT C.name company_name, COUNT(P.id) people_count
FROM company C
         INNER JOIN person P ON C.id = P.company_id
GROUP BY C.name
HAVING COUNT(P.id) = (SELECT COUNT(company_id)
                      FROM person
                      GROUP BY company_id
                      ORDER BY COUNT(company_id) DESC
                      LIMIT 1);

