-- 1. Llenar el Catálogo de Productos Disponibles
INSERT INTO public.producto (codigo, nombre, descripcion, unidad_medida, activo) VALUES
('UTI-001', 'Papel Bond A4 80gr', 'Millar de papel bond color blanco premium para impresoras', 'MILLAR', true),
('UTI-002', 'Lapicero Azul Pilot', 'Caja de 50 lapiceros tinta seca punta fina', 'CAJA', true),
('TEC-001', 'Proyector Epson HD', 'Proyector interactivo 3500 lúmenes para aulas', 'UNIDAD', true),
('TEC-002', 'Monitor Samsung 24"', 'Monitor LED Full HD 75Hz para oficinas administrativas', 'UNIDAD', true);

-- 2. Generar una Ficha Técnica de Kárdex (Ej: Solo para el Papel Bond)
-- Nota: Dejaremos los otros productos libres para que pruebes tu HU11 (Nuevo Asiento)
INSERT INTO public.kardex (id_producto, stock_actual, stock_minimo, ubicacion_almacen, fecha_apertura, caracteristicas) VALUES
(1, 15, 5, 'Estante A-1', CURRENT_DATE, 'Mantener alejado de la humedad y luz directa');

-- 3. Generar el historial de movimientos (Auditoría para ese Papel Bond)
INSERT INTO public.kardex_movimiento (id_kardex, tipo_movimiento, cantidad, saldo_stock, fecha_movimiento, documento_referencia, observaciones) VALUES
(1, 'ENTRADA', 20, 20, CURRENT_TIMESTAMP - INTERVAL '2 days', 'OC-2026-0015', 'Recepción inicial conforme de almacén central'),
(1, 'SALIDA', 5, 15, CURRENT_TIMESTAMP - INTERVAL '1 day', 'PECOSA-2026-0042', 'Despacho urgente a Facultad de Ingeniería de Sistemas');