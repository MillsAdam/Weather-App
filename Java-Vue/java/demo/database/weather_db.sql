DROP TABLE IF EXISTS weather, users;

CREATE TABLE users (
    id serial PRIMARY KEY,
    username VARCHAR ( 50 ) UNIQUE NOT NULL,
    password VARCHAR ( 50 ) NOT NULL,
    salt VARCHAR ( 50 ) NOT NULL
);

CREATE TABLE weather (
    id serial PRIMARY KEY,
    user_id INT NOT NULL,
    zip_code INT NOT NULL,
    main VARCHAR ( 50 ) NOT NULL,
    description VARCHAR ( 50 ) NOT NULL,
    temperature NUMERIC ( 5, 2 ),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);
