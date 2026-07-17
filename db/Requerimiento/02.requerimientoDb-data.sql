INSERT INTO contador (prefijo, anio, ultimo_numero) VALUES ('REQ', 2026, 3);

INSERT INTO proveedor (ruc, razon_social, direccion, id_usuario) VALUES
    ('20567890123', 'Distribuidora Lima SAC', 'Av. Industrial 123', 2),
    ('20678901234', 'Suministros Peru EIRL', 'Jr. Comercio 456', 3);

INSERT INTO requerimiento (codigo, descripcion, estado, fecha_creacion, fecha_actualizacion, id_dependencia, id_usuario, version) VALUES
    ('REQ-2026-001', 'Requerimiento de Papeleria y Utiles de oficina', 'PENDIENTE', '2026-07-10 09:00:00', '2026-07-10 09:00:00', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 2, 0),
    ('REQ-2026-002', 'Requerimiento de Equipos de Computo', 'EN_EVALUACION', '2026-07-12 14:30:00', '2026-07-14 10:00:00', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 2, 1),
    ('REQ-2026-003', 'Requerimiento de Mobiliario para oficina', 'APROBADO', '2026-07-15 08:00:00', '2026-07-16 16:00:00', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 3, 2);

INSERT INTO requerimiento_detalle (cantidad, unidad_medida, observacion_especifica, id_producto, id_requerimiento) VALUES
    (50, 'UNIDAD', 'Resma de papel carta 75g', 101, (SELECT id FROM requerimiento WHERE codigo = 'REQ-2026-001')),
    (20, 'UNIDAD', 'Caja de lapices bic', 102, (SELECT id FROM requerimiento WHERE codigo = 'REQ-2026-001')),
    (10, 'UNIDAD', 'Monitor LED 24 pulgadas', 103, (SELECT id FROM requerimiento WHERE codigo = 'REQ-2026-002')),
    (5, 'UNIDAD', 'Teclado inalambrico', 104, (SELECT id FROM requerimiento WHERE codigo = 'REQ-2026-002')),
    (3, 'UNIDAD', 'Escritorio ejecutivo', 105, (SELECT id FROM requerimiento WHERE codigo = 'REQ-2026-003')),
    (6, 'UNIDAD', 'Silla ergonomica', 106, (SELECT id FROM requerimiento WHERE codigo = 'REQ-2026-003'));
