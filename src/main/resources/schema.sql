CREATE TABLE PERSON
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50)         NOT NULL,
    last_name  VARCHAR(50)         NOT NULL,
    email      VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE ADDRESS
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    person_id   BIGINT,
    street      VARCHAR(100),
    city        VARCHAR(50),
    state       VARCHAR(50),
    postal_code VARCHAR(20),
    FOREIGN KEY (person_id) REFERENCES PERSON (id)
);




