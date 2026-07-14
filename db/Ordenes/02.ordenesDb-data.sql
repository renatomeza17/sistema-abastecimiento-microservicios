-- Limpiar tablas para evitar errores de duplicado si se ejecuta de nuevo
DELETE FROM orden_compra_detalles;
DELETE FROM ordenes_compra;

-- 1. Insertar Órdenes de Compra de prueba
-- Orden 1: OC-2026-0001 (Pendiente)
INSERT INTO ordenes_compra (
    id_orden, codigo, descripcion, fecha_creacion, fecha_entrega, estado, 
    monto_total, id_proforma, id_proveedor, lugar_entrega, observaciones, 
    forma_pago, plazo_entrega, garantia, fecha_autorizacion, firma_digital_hash, autorizado_por
) VALUES (
    1, 'OC-2026-0001', 'Adquisición de insumos de papelería para oficina central', 
    '2026-07-10', '2026-07-20', 'PENDIENTE', 350.50, 101, 201, 
    'Av. Universitaria 1234, San Miguel', 'Entregar en horario de oficina de 9 am a 5 pm.', 
    'Crédito 30 días', '10 días útiles', 'Garantía de fábrica 1 año', 
    NULL, NULL, NULL
);

-- Orden 2: OC-2026-0002 (Aprobada y firmada)
INSERT INTO ordenes_compra (
    id_orden, codigo, descripcion, fecha_creacion, fecha_entrega, estado, 
    monto_total, id_proforma, id_proveedor, lugar_entrega, observaciones, 
    forma_pago, plazo_entrega, garantia, fecha_autorizacion, firma_digital_hash, autorizado_por
) VALUES (
    2, 'OC-2026-0002', 'Compra de componentes de hardware para actualización', 
    '2026-07-12', '2026-07-15', 'APROBADA', 1500.00, 102, 202, 
    'Oficina de TI - Sede Ate, Santa Clara', 'Urgente para soporte técnico de laboratorios.', 
    'Transferencia Bancaria', '3 días útiles', 'Garantía local de 12 meses', 
    '2026-07-12 14:30:00', 'sha256:7f83b1a293c8d4e5f6a7b8c9d0e1f2a3b4c5d6e7f8g9h0i1j2k3l4m5n6o7p8q9', 'director_ti_unmsm'
);


-- 2. Insertar Detalles de las Órdenes
-- Detalles para la Orden 1 (Monto Total: 350.50)
-- 50 Unidades de Producto ID 10 (ej: Archivadores) a 5.00 c/u = 250.00
INSERT INTO orden_compra_detalles (orden_compra_id, id_producto, nombre_producto, cantidad, precio_unitario) 
VALUES (1, 10, 'Archivadores Lomo Ancho Oficio', 50, 5.00);

-- 6 Unidades de Producto ID 11 (ej: Cajas de Lapiceros) a 16.75 c/u = 100.50
INSERT INTO orden_compra_detalles (orden_compra_id, id_producto, nombre_producto, cantidad, precio_unitario) 
VALUES (1, 11, 'Caja de Lapiceros Tinta Gel Azul x 12', 6, 16.75);


-- Detalles para la Orden 2 (Monto Total: 1500.00)
-- 2 Unidades de Producto ID 50 (ej: SSD NVMe 1TB) a 350.00 c/u = 700.00
INSERT INTO orden_compra_detalles (orden_compra_id, id_producto, nombre_producto, cantidad, precio_unitario) 
VALUES (2, 50, 'SSD Kingston NV2 1TB PCIe 4.0 NVMe', 2, 350.00);

-- 1 Unidad de Producto ID 51 (ej: Memoria RAM DDR4 16GB) a 180.00 c/u = 180.00
INSERT INTO orden_compra_detalles (orden_compra_id, id_producto, nombre_producto, cantidad, precio_unitario) 
VALUES (2, 51, 'Memoria RAM Corsair Vengeance LPX DDR4 16GB', 1, 180.00);

-- 2 Unidades de Producto ID 52 (ej: Fuente de poder) a 310.00 c/u = 620.00
INSERT INTO orden_compra_detalles (orden_compra_id, id_producto, nombre_producto, cantidad, precio_unitario) 
VALUES (2, 52, 'Fuente de Poder EVGA 600W 80 Plus Bronze', 2, 310.00);