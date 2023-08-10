BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS game_type;
DROP TABLE IF EXISTS condition;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS collection;
DROP TABLE IF EXISTS card_collection;
DROP TABLE IF EXISTS favorite_collection;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id));

CREATE TABLE game_type (
    game_type_id integer NOT NULL,
    game_type_name varchar(50) NOT NULL,
	CONSTRAINT PK_game_type PRIMARY KEY (game_type_id));

CREATE TABLE collection (
	collection_id SERIAL,
	collection_name varchar(100) NOT NULL UNIQUE,
	author_id integer,
	game_type_id integer NOT NULL,
    CONSTRAINT FK_author_id FOREIGN KEY (author_id) REFERENCES users (user_id),
    CONSTRAINT FK_game_type_id FOREIGN KEY (game_type_id) REFERENCES game_type (game_type_id),
	CONSTRAINT PK_collection PRIMARY KEY (collection_id));

CREATE TABLE condition (
    condition_id integer NOT NULL,
    condition_name varchar(50) NOT NULL,
    CONSTRAINT PK_condition PRIMARY KEY (condition_id));

CREATE TABLE card (
    card_id SERIAL,
    card_api_id varchar(200) NOT NULL,
    card_name varchar(100) NOT NULL,
	game_type_id integer NOT NULL,
	user_price numeric(10,2) NOT NULL,
	quantity integer,
	condition_id integer NOT NULL,
    CONSTRAINT FK_game_type_id FOREIGN KEY (game_type_id) REFERENCES game_type (game_type_id),
	CONSTRAINT FK_condition_id FOREIGN KEY (condition_id) REFERENCES condition (condition_id),
	CONSTRAINT PK_card PRIMARY KEY (card_id));

CREATE TABLE card_collection (
    card_id integer NOT NULL,
    collection_id integer NOT NULL,
    CONSTRAINT PK_card_collection PRIMARY KEY (card_id, collection_id),
    CONSTRAINT FK_card_collection_card FOREIGN KEY (card_id) REFERENCES card (card_id),
    CONSTRAINT FK_card_collection_collection FOREIGN KEY (collection_id) REFERENCES collection (collection_id)
    );

CREATE TABLE favorite_collection (
    user_id int NOT NULL,
    collection_id integer NOT NULL,
    CONSTRAINT PK_favorite_collection PRIMARY KEY (user_id, collection_id),
    CONSTRAINT FK_favorite_collection_user FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT FK_favorite_collection_collection FOREIGN KEY(collection_id) REFERENCES collection (collection_id)
    );

COMMIT TRANSACTION;
