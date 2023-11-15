DROP TABLE IF EXISTS weather, users;

CREATE TABLE users (
    id serial PRIMARY KEY,
    username VARCHAR ( 32 ) UNIQUE NOT NULL, -- Username
    password VARCHAR ( 32 ) NOT NULL, -- Password (hashed, not plain-text)
    salt VARCHAR ( 256 ) NOT NULL -- Password salt
);

CREATE TABLE weather (
    id serial PRIMARY KEY,
    user_id INT NOT NULL,
    zip_code INT NOT NULL,
    main VARCHAR ( 200 ) NOT NULL,
    description VARCHAR ( 300 ) NOT NULL,
    temperature NUMERIC ( 5, 2 ),
    feels_like NUMERIC ( 5, 2 ),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (id)
);
