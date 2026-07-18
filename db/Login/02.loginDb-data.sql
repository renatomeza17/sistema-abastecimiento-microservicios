INSERT INTO usuarios (username, password, email, activo, nombres, apellido_paterno, apellido_materno, ndocumento, tipo_documento)
VALUES
    ('admin', '$2b$12$Uo41yDDs2xW3ZNSytGitO.can6tntkTThLD3Wikexny9vGS8S6HYe', 'admin@sudab.pe', true, 'Administrador', 'Sistema', 'General', '12345678', 'DNI'),
    ('jefe', '$2b$12$Uo41yDDs2xW3ZNSytGitO.can6tntkTThLD3Wikexny9vGS8S6HYe', 'jefe@sudab.pe', true, 'Juan', 'Perez', 'Lopez', '87654321', 'DNI'),
    ('director', '$2b$12$Uo41yDDs2xW3ZNSytGitO.can6tntkTThLD3Wikexny9vGS8S6HYe', 'director@sudab.pe', true, 'Maria', 'Garcia', 'Ruiz', '11223344', 'DNI')
ON CONFLICT (username) DO NOTHING;

INSERT INTO usuario_roles (id_usuario, id_rol) VALUES
    (2, 1),
    (3, 2),
    (4, 3)
ON CONFLICT DO NOTHING;
