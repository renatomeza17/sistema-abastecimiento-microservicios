INSERT INTO public.pedido_pendiente (id_orden, motivo, observacion, estado, fecha_registro) VALUES
(1, 'Falta de stock', 'Producto agotado en bodega principal', 'PENDIENTE', NOW()),
(2, 'Pedido duplicado', 'Se registro dos veces por error del sistema', 'PENDIENTE', NOW()),
(3, 'Cambio de proveedor', 'Proveedor anterior no cumplio con el pedido', 'EN_REVISION', NOW()),
(4, 'Error en cantidad', 'Se recibieron 50 unidades menos de las solicitadas', 'PENDIENTE', NOW()),
(5, 'Producto danado', 'Llego con defectos de fabricacion', 'RESUELTO', NOW());
