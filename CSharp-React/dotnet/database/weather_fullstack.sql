BEGIN TRANSACTION;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


CREATE TABLE weather (
    id serial PRIMARY KEY,
    user_id int NOT NULL,
    zipcode int NOT NULL,
    main varchar(200) NOT NULL,
    description varchar (300) NOT NULL,
    temperature numeric(5, 2),
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

COMMIT TRANSACTION;