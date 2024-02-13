INSERT INTO chat.users (login, password) VALUES ('Q', 'bdsb');
INSERT INTO chat.users (login, password) VALUES ('W', 'bdsnftsnsba');
INSERT INTO chat.users (login, password) VALUES ('E', 'e4ayrjnz');
INSERT INTO chat.users (login, password) VALUES ('R', 'febtrsbn');
INSERT INTO chat.users (login, password) VALUES ('T', 'btrsbts');

INSERT INTO chat.chatrooms (name, owner) VALUES ('Hello', 1);
INSERT INTO chat.chatrooms (name, owner) VALUES ('World', 2);
INSERT INTO chat.chatrooms (name, owner) VALUES ('Peace', 3);
INSERT INTO chat.chatrooms (name, owner) VALUES ('Work', 4);
INSERT INTO chat.chatrooms (name, owner) VALUES ('May', 5);

INSERT INTO chat.messages (author, room, text, timestamp) VALUES (1, 2, 'vew', current_timestamp);
INSERT INTO chat.messages (author, room, text, timestamp) VALUES (2, 3, 'breabaeb', current_timestamp);
INSERT INTO chat.messages (author, room, text, timestamp) VALUES (3, 2, 'peace', current_timestamp);
INSERT INTO chat.messages (author, room, text, timestamp) VALUES (4, 5, 'Ok', current_timestamp);
INSERT INTO chat.messages (author, room, text, timestamp) VALUES (5, 1, 'Hi', current_timestamp);