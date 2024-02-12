CREATE TABLE IF NOT EXISTS dictionary
(
    id                 UUID         DEFAULT gen_random_uuid() PRIMARY KEY,
    name               VARCHAR(36) NOT NULL,
    year               VARCHAR(4)   DEFAULT NULL,
    description        VARCHAR(255) DEFAULT NULL,
    created_date       TIMESTAMP    DEFAULT current_timestamp,
    last_modified_date TIMESTAMP    DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS author
(
    id                 UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    name               VARCHAR(36) NOT NULL,
    surname            VARCHAR(36) NOT NULL,
    created_date       TIMESTAMP DEFAULT current_timestamp,
    last_modified_date TIMESTAMP DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS dictionary_author
(
    dictionary_id UUID NOT NULL,
    author_id UUID NOT NULL,

    FOREIGN KEY (dictionary_id) REFERENCES dictionary(id),
    FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE IF NOT EXISTS category
(
    id                 UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    name               VARCHAR(36) NOT NULL,
    created_date       TIMESTAMP DEFAULT current_timestamp,
    last_modified_date TIMESTAMP DEFAULT NULL
);

INSERT INTO category (name)
VALUES ('Literal'),
       ('Dialect'),
       ('Archaism');

CREATE TABLE IF NOT EXISTS explanation
(
    id                 UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    dictionary_id      UUID      DEFAULT NULL,
    text               VARCHAR(255) NOT NULL,
    created_date       TIMESTAMP DEFAULT current_timestamp,
    last_modified_date TIMESTAMP DEFAULT NULL,

    FOREIGN KEY (dictionary_id) REFERENCES dictionary (id)
);

CREATE TABLE IF NOT EXISTS language
(
    id                 UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    name               VARCHAR(36) NOT NULL,
    created_date       TIMESTAMP DEFAULT current_timestamp,
    last_modified_date TIMESTAMP DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS word
(
    id                 UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    category_id        UUID        NOT NULL,
    text               VARCHAR(50) NOT NULL,
    explanation_id     UUID      DEFAULT NULL,
    language_id        UUID        NOT NULL,
    created_date       TIMESTAMP DEFAULT current_timestamp,
    last_modified_date TIMESTAMP DEFAULT NULL,

    FOREIGN KEY (category_id) REFERENCES category (id),
    FOREIGN KEY (explanation_id) REFERENCES explanation (id),
    FOREIGN KEY (language_id) REFERENCES language (id)
);

CREATE TABLE IF NOT EXISTS dictionary_word
(
    dictionary_id UUID NOT NULL,
    word_id UUID NOT NULL,

    FOREIGN KEY (dictionary_id) REFERENCES dictionary(id),
    FOREIGN KEY (word_id) REFERENCES word(id)
);

CREATE TABLE IF NOT EXISTS bel_ru_online_dictionary
(
    id                 UUID      DEFAULT gen_random_uuid() PRIMARY KEY,
    bel_id             UUID NOT NULL,
    ru_id              UUID NOT NULL,
    created_date       TIMESTAMP DEFAULT current_timestamp,
    last_modified_date TIMESTAMP DEFAULT NULL,

    FOREIGN KEY (bel_id) REFERENCES word (id),
    FOREIGN KEY (ru_id) REFERENCES word (id)
);
