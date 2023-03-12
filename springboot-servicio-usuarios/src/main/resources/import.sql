INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('lora', '$2a$10$3k.0fKnCthiog5eX6egiEugQj5OQHq/vLzUxSn2AZhPlwSIXhnVVG', 1, 'laura', 'sputo', 'lora@gmail.com' );
INSERT INTO usuarios (username, password, enabled, nombre, apellido, email) VALUES ('florencia', '$2a$10$6Vrmupvp7kJwxxTb52NQiuNqFJ6y8qm4QzOL4vc4HOLo3S57g8sdu', 1, 'florencia', 'souto', 'flo@gmail.com' );

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2,1);

