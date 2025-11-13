-- Inserir roles
INSERT INTO tb_role (role_name) VALUES ('OPERATOR');
INSERT INTO tb_role (role_name) VALUES ('ADMIN');

-- Inserir users
INSERT INTO tb_user (name, email, password) VALUES ('Maria Silva', 'maria@gmail.com', '123456');
INSERT INTO tb_user (name, email, password) VALUES ('João Santos', 'joao@gmail.com', '123456');

-- Relacionar users com roles
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);