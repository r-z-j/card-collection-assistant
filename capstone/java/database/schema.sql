BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id));

DROP TABLE IF EXISTS collections;

CREATE TABLE collections (
	collection_id SERIAL,
	collection_name varchar(50) NOT NULL UNIQUE,
	author_id SERIAL,
	game_type_id varchar(200) NOT NULL,
    CONSTRAINT FK_author_id FOREIGN KEY(author_id) REFERENCES users(user_id),
	CONSTRAINT PK_collection PRIMARY KEY (collection_id));

COMMIT TRANSACTION;
