-- Inserir roles padrão
insert into roles (name) values
    ('ROLE_ADMIN'),
    ('ROLE_USER');

-- Inserir usuário admin (senha será codificada depois)
insert into users (username, email, password, role, enabled)
values
('admin', 'admin@clinica.com', '$2a$10$0G7yEcuq5Y8mMZkPqpvZeu/Xmpm7bShmc3cMsuU5He7tiO1ZjLZP.', 'ROLE_ADMIN', 1),
('user', 'user@clinica.com', '$2a$10$0G7yEcuq5Y8mMZkPqpvZeu/Xmpm7bShmc3cMsuU5He7tiO1ZjLZP.', 'ROLE_USER', 1);

