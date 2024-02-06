INSERT INTO user_types (name) VALUES ('CLIENT');
INSERT INTO user_types (name) VALUES ('ADMIN');

INSERT INTO users (login, password, name, user_type_id, online, banned) VALUES ('admin', 'admin', 'Юрий', 2, false, false);
INSERT INTO users (login, password, name, user_type_id, online, banned) VALUES ('user1', 'user1', 'Олег', 1, false, false);
INSERT INTO users (login, password, name, user_type_id, online, banned) VALUES ('user2', 'user2', 'Иван', 1, false, false);
INSERT INTO users (login, password, name, user_type_id, online, banned) VALUES ('user3', 'user3', 'Дмитрий', 1, false, true);

INSERT INTO messages (id, sender_id, content, timestamp) VALUES (1, 1, 'Всем привет!', CURRENT_TIMESTAMP);