CREATE TABLE IF NOT EXISTS dictionary
(
    id                 UUID         DEFAULT gen_random_uuid() PRIMARY KEY,
    name               VARCHAR(36) NOT NULL,
    year               VARCHAR(4)   DEFAULT NULL,
    description        VARCHAR(255) DEFAULT NULL,
    created_date       TIMESTAMP   NOT NULL,
    last_modified_date TIMESTAMP    DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS author
(
    id                 UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    name               VARCHAR(36) NOT NULL,
    surname            VARCHAR(36) NOT NULL,
    created_date       TIMESTAMP   NOT NULL,
    last_modified_date TIMESTAMP DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS category
(
    id                 UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    name               VARCHAR(36) NOT NULL,
    created_date       TIMESTAMP   NOT NULL,
    last_modified_date TIMESTAMP DEFAULT NULL
);

INSERT INTO category (name, created_date)
VALUES ('Literal', current_timestamp),
       ('Dialect', current_timestamp),
       ('Archaism', current_timestamp);

CREATE TABLE IF NOT EXISTS explanation
(
    id                 UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    dictionary_id      UUID      DEFAULT NULL,
    text               VARCHAR(255) NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP DEFAULT NULL,

    FOREIGN KEY (dictionary_id) REFERENCES dictionary (id)
);

CREATE TABLE IF NOT EXISTS word
(
    id                 UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    category_id        UUID        NOT NULL,
    text               VARCHAR(50) NOT NULL,
    explanation_id     UUID      DEFAULT NULL,
    created_date       TIMESTAMP   NOT NULL,
    last_modified_date TIMESTAMP DEFAULT NULL,

    FOREIGN KEY (category_id) REFERENCES category (id),
    FOREIGN KEY (explanation_id) REFERENCES explanation (id)
);
