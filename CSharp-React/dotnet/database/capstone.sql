USE master
GO

--drop database if it exists
IF DB_ID('weather_fullstack_csharp') IS NOT NULL
BEGIN
	ALTER DATABASE weather_fullstack SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
	DROP DATABASE weather_fullstack;
END

CREATE DATABASE weather_fullstack
GO

USE weather_fullstack
GO

--create tables
CREATE TABLE users (
	user_id int IDENTITY(1,1) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	salt varchar(200) NOT NULL,
	user_role varchar(50) NOT NULL
	CONSTRAINT PK_user PRIMARY KEY (user_id)
)

CREATE TABLE weather (
    id int IDENTITY(1,1) NOT NULL,
    user_id int NOT NULL,
    zipcode int NOT NULL,
    main varchar(200) NOT NULL,
    description varchar (300) NOT NULL,
    temperature numeric(5, 2),
	CONSTRAINT PK_weather PRIMARY KEY (id),
    CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES users (user_id)
)

--populate default data
INSERT INTO users (username, password_hash, salt, user_role) VALUES ('user','Jg45HuwT7PZkfuKTz6IB90CtWY4=','LHxP4Xh7bN0=','user');
INSERT INTO users (username, password_hash, salt, user_role) VALUES ('admin','YhyGVQ+Ch69n4JMBncM4lNF/i9s=', 'Ar/aB2thQTI=','admin');

GO