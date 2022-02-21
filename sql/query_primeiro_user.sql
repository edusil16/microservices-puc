INSERT INTO tb_perfil (id_perfil,descricao) VALUES (01,'ADMIN');
INSERT INTO tb_perfil (id_perfil,descricao) VALUES (02,'FORNECEDOR');
INSERT INTO tb_perfil (id_perfil,descricao) VALUES (03,'CLIENTE');
INSERT INTO tb_perfil (id_perfil,descricao) VALUES (04,'MOTORISTA');
INSERT INTO tb_perfil (id_perfil,descricao) VALUES (05,'OPERADOR');


INSERT INTO tb_usuario (id_usuario, user_login, password, flg_ativo) values (1, 'edusilva', '$2a$10$199a20oX1oVLUH1zJsmubel2/TTaVFRVtRj6cKlBj3GuaDdsYfKfC', true);

INSERT INTO tb_usuario_perfil (id_perfil_usuario, id_perfil, id_usuario) values (1, 1, 1);
