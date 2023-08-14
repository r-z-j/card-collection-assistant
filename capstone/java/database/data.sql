BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO game_type (game_type_name, game_type_id) VALUES ('Magic', 1);
INSERT INTO game_type (game_type_name, game_type_id) VALUES ('Pokemon', 2);

INSERT INTO collection (collection_name, author_id, game_type_id) VALUES ('magic-collection-1', 1, 1);
INSERT INTO collection (collection_name, author_id, game_type_id) VALUES ('poke-collection-1', 2, 2);

INSERT INTO condition (condition_id, condition_name) VALUES (1, 'Mint');
INSERT INTO condition (condition_id, condition_name) VALUES (2, 'Near Mint');
INSERT INTO condition (condition_id, condition_name) VALUES (3, 'Excellent');
INSERT INTO condition (condition_id, condition_name) VALUES (4, 'Good');
INSERT INTO condition (condition_id, condition_name) VALUES (5, 'Light Played');
INSERT INTO condition (condition_id, condition_name) VALUES (6, 'Played');
INSERT INTO condition (condition_id, condition_name) VALUES (7, 'Poor');

INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('abcd1234', 'Black Lotus', 1, 20.00, 2, 1, 1);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('hifjweio452987', 'Red Lotus', 1, 10.00, 1, 2, 1);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('hjkgfd789754390', 'Cornflower Blue Lotus', 1, 5.00, 3, 3, 1);

INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('vcbzxnmvzx', 'Charizard', 2, 10.00, 1, 4, 2);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('89u06uifjkg', 'Gengar', 2, 5.00, 2, 6, 2);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('63yui54o', 'Scyther', 2, 40.00, 1, 1, 2);

INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('xy1-1', 'Venusaur', 2, 12.00, 1, 4, 2);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('mcd19-1', 'Caterpie', 2, 3.00, 2, 7, 2);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('pl1-2', 'Blastoise', 2, 40.00, 1, 1, 2);

INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('bce78225-9dbf-46c1-b63d-083c1858eb98', 'Austere Command', 1, 90.00, 2, 1, 1);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('5654575e-0849-4e7f-98f2-0074ac8e0faa', 'Blessed Orator', 1, 30.00, 1, 4, 1);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('0215cbbf-7bac-4ff7-bceb-23d728797848', 'Ironscale Hydra', 1, 5.00, 3, 7, 1);


INSERT INTO favorite_collection (user_id, collection_id) VALUES (1, 1);
INSERT INTO favorite_collection (user_id, collection_id) VALUES (1, 2);
INSERT INTO favorite_collection (user_id, collection_id) VALUES (2, 1);
INSERT INTO favorite_collection (user_id, collection_id) VALUES (2, 2);


COMMIT TRANSACTION;
