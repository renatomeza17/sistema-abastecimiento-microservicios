INSERT INTO dependencia (nombre, descripcion) VALUES
    ('Direccion Administrativa', 'Direccion general administrativa de la entidad'),
    ('Departamento de Logistica', 'Area encargada de adquisiciones y logistica'),
    ('Departamento de Contabilidad', 'Area de contabilidad y finanzas');

INSERT INTO pedido_dependencia (codigo, descripcion, estado, fecha_creacion, fecha_actualizacion, id_usuario, id_dependencia, version) VALUES
    ('PD-2026-001', 'Pedido de papeleria y materiales de oficina', 'PENDIENTE', '2026-07-10 09:00:00', '2026-07-10 09:00:00', 2, (SELECT id FROM dependencia WHERE nombre = 'Direccion Administrativa'), 0),
    ('PD-2026-002', 'Pedido de equipos de computo para el area de sistemas', 'EN_EVALUACION', '2026-07-12 11:00:00', '2026-07-13 14:00:00', 2, (SELECT id FROM dependencia WHERE nombre = 'Departamento de Logistica'), 1),
    ('PD-2026-003', 'Pedido de mobiliario para nueva oficina', 'APROBADO', '2026-07-14 08:30:00', '2026-07-15 10:00:00', 3, (SELECT id FROM dependencia WHERE nombre = 'Departamento de Contabilidad'), 2);

INSERT INTO detalle_pedido_dependencia (cantidad, unidad_medida, observacion_especifica, id_producto, id_pedido) VALUES
    (100, 'UNIDAD', 'Resma de papelBond carta', 101, (SELECT id FROM pedido_dependencia WHERE codigo = 'PD-2026-001')),
    (20, 'CAJA', 'Caja de lapices bic color', 102, (SELECT id FROM pedido_dependencia WHERE codigo = 'PD-2026-001')),
    (5, 'UNIDAD', 'Laptop Lenovo ThinkPad 14 pulgadas', 103, (SELECT id FROM pedido_dependencia WHERE codigo = 'PD-2026-002')),
    (5, 'UNIDAD', 'Monitor LED 24 pulgadas', 104, (SELECT id FROM pedido_dependencia WHERE codigo = 'PD-2026-002')),
    (10, 'UNIDAD', 'Escritorio ejecutivo color nogal', 105, (SELECT id FROM pedido_dependencia WHERE codigo = 'PD-2026-003')),
    (10, 'UNIDAD', 'Silla ergonomica con reposabrazos', 106, (SELECT id FROM pedido_dependencia WHERE codigo = 'PD-2026-003'));
