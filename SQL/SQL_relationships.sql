/*many-to-one: many orders - one customer*/
CREATE TABLE customer
(
    customer_id SERIAL PRIMARY KEY,
    name        VARCHAR(35)
);

CREATE TABLE order_table
(
    order_id    SERIAL PRIMARY KEY,
    amount      INT,
    customer_id INT,
    FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id)
);

/*many-to-many: one customer - many preferences and vice versa*/
CREATE TABLE purchase_preference
(
    preference_id SERIAL PRIMARY KEY,
    pref_name     VARCHAR(35)
);

CREATE TABLE customer_purchase_preference
(
    id            SERIAL PRIMARY KEY,
    customer_id   INT,
    preference_id INT,
    FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id),
    FOREIGN KEY (preference_id)
        REFERENCES purchase_preference (preference_id)
);

/*one-to-one: one customer - one customer account*/
CREATE TABLE customer_account
(
    account_id  SERIAL PRIMARY KEY,
    discount    INT,
    customer_id INT UNIQUE,
    FOREIGN KEY (customer_id)
        REFERENCES customer (customer_id)
);

INSERT INTO customer (name)
VALUES ('Ivan'),
       ('Tom'),
       ('Mark');

INSERT INTO order_table(amount, customer_id)
VALUES (500, 1),
       (1000, 3),
       (1500, 1);

INSERT INTO purchase_preference (pref_name)
VALUES ('sports'),
       ('books'),
       ('it');

INSERT INTO customer_purchase_preference(customer_id, preference_id)
VALUES (1, 2),
       (3, 1),
       (3, 3),
       (1, 1),
       (1, 3);

INSERT INTO customer_account (discount)
VALUES (10),
       (30),
       (50);

