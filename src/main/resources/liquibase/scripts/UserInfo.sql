-- liquibase formatted sql

-- changeset dmitri:1
CREATE TYPE ROLE AS ENUM ('USER', 'ADMIN');

CREATE TABLE user_data
(   id INTEGER PRIMARY KEY NOT NULL,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    phone TEXT NOT NULL,
    email TEXT NOT NULL,
    role ROLE NOT NULL
);

CREATE TABLE ad
(   pk INTEGER PRIMARY KEY NOT NULL,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    image TEXT NOT NULL,
    price INTEGER NOT NULL,
    author_id INTEGER NOT NULL,
    CONSTRAINT user_data_id foreign key (author_id) references user_data(id)
);

CREATE TABLE comment
(   pk INTEGER PRIMARY KEY NOT NULL,
    text TEXT NOT NULL,
    created_at BIGINT NOT NULL,
    author_id INTEGER NOT NULL,
    CONSTRAINT user_data_id foreign key (author_id) references user_data(id)
);