INSERT INTO producto (codigo, nombre, descripcion, unidad_medida, activo) VALUES
('UTI-001', 'Papel Bond A4 80gr', 'Millar de papel bond color blanco premium para impresoras', 'MILLAR', true),
('UTI-002', 'Lapicero Azul Pilot', 'Caja de 50 lapiceros tinta seca punta fina', 'CAJA', true),
('TEC-001', 'Proyector Epson HD', 'Proyector interactivo 3500 lumenes para aulas', 'UNIDAD', true),
('TEC-002', 'Monitor Samsung 24 pulgadas', 'Monitor LED Full HD 75Hz para oficinas administrativas', 'UNIDAD', true);
 
INSERT INTO kardex (id_producto, stock_actual, stock_minimo, ubicacion_almacen, fecha_apertura, caracteristicas) VALUES
(1, 15, 5, 'Estante A-1', CURDATE(), 'Mantener alejado de la humedad y luz directa');
 
INSERT INTO kardex_movimiento (id_kardex, tipo_movimiento, cantidad, saldo_stock, fecha_movimiento, documento_referencia, observaciones) VALUES
(1, 'ENTRADA', 20, 20, DATE_SUB(NOW(), INTERVAL 2 DAY), 'OC-2026-0015', 'Recepcion inicial conforme de almacen central'),
(1, 'SALIDA', 5, 15, DATE_SUB(NOW(), INTERVAL 1 DAY), 'PECOSA-2026-0042', 'Despacho urgente a Facultad de Ingenieria');