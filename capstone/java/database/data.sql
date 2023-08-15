BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO game_type (game_type_name, game_type_id) VALUES ('Magic', 1);
INSERT INTO game_type (game_type_name, game_type_id) VALUES ('Pokemon', 2);

INSERT INTO collection (collection_name, author_id, game_type_id) VALUES ('magic-collection-1', 1, 1);
INSERT INTO collection (collection_name, author_id, game_type_id) VALUES ('magic-collection-2', 1, 1);
INSERT INTO collection (collection_name, author_id, game_type_id) VALUES ('poke-collection-1', 1, 2);
INSERT INTO collection (collection_name, author_id, game_type_id) VALUES ('poke-collection-2', 1, 2);

INSERT INTO collection (collection_name, author_id, game_type_id) VALUES ('poke-collection-3', 2, 2);


INSERT INTO condition (condition_id, condition_name) VALUES (1, 'Mint');
INSERT INTO condition (condition_id, condition_name) VALUES (2, 'Near Mint');
INSERT INTO condition (condition_id, condition_name) VALUES (3, 'Excellent');
INSERT INTO condition (condition_id, condition_name) VALUES (4, 'Good');
INSERT INTO condition (condition_id, condition_name) VALUES (5, 'Light Played');
INSERT INTO condition (condition_id, condition_name) VALUES (6, 'Played');
INSERT INTO condition (condition_id, condition_name) VALUES (7, 'Poor');

INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('3e64afdc-7623-4b88-a952-45243b4dff79', 'Ugin, The Spirit Dragon', 1, 20.00, 2, 1, 1);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('0ee0719c-07d0-419a-b6ed-fc3bf982e4d3', 'Elesh Norn, Grand Cenobite', 1, 10.00, 1, 2, 1);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('8df6603a-38c1-4d18-8b84-6211e9a7cc09', 'Sheoldred, the Apocalypse', 1, 5.00, 3, 3, 1);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('6d545e9d-3b8e-44f5-8b4e-d8573ecc5819', 'Doubling Season', 1, 5.00, 3, 3, 1);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('5654575e-0849-4e7f-98f2-0074ac8e0faa', 'Blessed Orator', 1, 30.00, 1, 4, 1);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('0215cbbf-7bac-4ff7-bceb-23d728797848', 'Ironscale Hydra', 1, 5.00, 3, 7, 1);

INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('7572bb1d-0578-4ebb-9180-dbb709ec9a5c', 'Posts Citadel', 1, 20.00, 2, 1, 2);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('b441a33a-3459-4d33-b733-7084335a16cd', 'Nicol Bolas', 1, 10.00, 1, 2, 2);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('aef9212c-9a66-4621-842d-c8b64f3937b2', 'Emrakul, The Promised End', 1, 5.00, 3, 3, 2);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('d5da52b2-cc09-49ac-8091-737f23a77165', 'Unbreakable Formation', 1, 5.00, 3, 3, 2);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('ade7d00d-4e7b-46e9-ace1-63f628a589fc', 'Time Walk', 1, 30.00, 1, 4, 2);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('ff37b863-f8c4-4584-8cc2-ac0e096e583f', 'Bazaar of Baghdad', 1, 5.00, 3, 7, 2);

INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('ex12-11', 'Muk', 2, 12.00, 1, 4, 3);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('swsh2-91', 'Dragapult', 2, 3.00, 2, 7, 3);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('gym1-14', 'Sabrinas Gengar', 2, 40.00, 1, 1, 3);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('xy1-1', 'Venusaur', 2, 12.00, 1, 4, 3);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('mcd19-1', 'Caterpie', 2, 3.00, 2, 7, 3);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('pl1-2', 'Blastoise', 2, 40.00, 1, 1, 3);

INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('xy10-1', 'Shuckle', 2, 12.00, 1, 4, 4);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('swsh35-8', 'Incineroar', 2, 3.00, 2, 7, 4);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('swsh1-4', 'Roserade', 2, 40.00, 1, 1, 4);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('sv1-80', 'Miraidon', 2, 12.00, 1, 4, 4);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('pl4-5', 'Luxray', 2, 3.00, 2, 7, 4);
INSERT INTO card (card_api_id, card_name, game_type_id, user_price, quantity, condition_id, collection_id) VALUES ('sv3-200', 'Palafin', 2, 40.00, 1, 1, 4);



INSERT INTO favorite_collection (user_id, collection_id) VALUES (1, 1);
INSERT INTO favorite_collection (user_id, collection_id) VALUES (1, 2);
INSERT INTO favorite_collection (user_id, collection_id) VALUES (2, 1);
INSERT INTO favorite_collection (user_id, collection_id) VALUES (2, 2);


COMMIT TRANSACTION;
