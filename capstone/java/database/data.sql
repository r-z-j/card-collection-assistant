BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO game_type (game_type_name, game_type_id) VALUES ('Magic', 1);
INSERT INTO game_type (game_type_name, game_type_id) VALUES ('Pokemon', 2);

INSERT INTO collection (collection_name, author_id, game_type_id) VALUES ('magic-collection-1', 1, 1);
INSERT INTO collection (collection_name, author_id, game_type_id) VALUES ('poke-collection-1', 2, 2);

INSERT INTO card (card_api_id, card_name, game_type_id) VALUES ('abcd1234', 'Black Lotus', 1);
INSERT INTO card (card_api_id, card_name, game_type_id) VALUES ('hifjweio452987', 'Red Lotus', 1);
INSERT INTO card (card_api_id, card_name, game_type_id) VALUES ('hjkgfd789754390', 'Cornflower Blue Lotus', 1);

INSERT INTO card (card_api_id, card_name, game_type_id) VALUES ('vcbzxnmvzx', 'Charizard', 2);
INSERT INTO card (card_api_id, card_name, game_type_id) VALUES ('89u06uifjkg', 'Gengar', 2);
INSERT INTO card (card_api_id, card_name, game_type_id) VALUES ('63yui54o', 'Scyther', 2);

INSERT INTO card_collection (card_id, collection_id) VALUES (1,1);
INSERT INTO card_collection (card_id, collection_id) VALUES (2,1);
INSERT INTO card_collection (card_id, collection_id) VALUES (3,1);

INSERT INTO card_collection (card_id, collection_id) VALUES (4,2);
INSERT INTO card_collection (card_id, collection_id) VALUES (5,2);
INSERT INTO card_collection (card_id, collection_id) VALUES (6,2);

INSERT INTO favorite_collection (user_id, collection_id) VALUES (1, 1);
INSERT INTO favorite_collection (user_id, collection_id) VALUES (1, 2);
INSERT INTO favorite_collection (user_id, collection_id) VALUES (2, 1);
INSERT INTO favorite_collection (user_id, collection_id) VALUES (2, 2);


COMMIT TRANSACTION;
