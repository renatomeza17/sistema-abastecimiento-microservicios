-- 1. Crear tabla de Orden de Compra
CREATE TABLE IF NOT EXISTS ordenes_compra (
    id_orden BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(255) NOT NULL UNIQUE,
    descripcion VARCHAR(100) NOT NULL,
    fecha_creacion DATE NOT NULL,
    fecha_entrega DATE NOT NULL,
    estado VARCHAR(255) NOT NULL,
    monto_total DOUBLE NOT NULL,
    id_proforma BIGINT NOT NULL,
    id_proveedor BIGINT NOT NULL,
    lugar_entrega VARCHAR(255),
    observaciones TEXT,
    forma_pago VARCHAR(100),
    plazo_entrega VARCHAR(100),
    garantia VARCHAR(100),
    fecha_autorizacion DATETIME,
    firma_digital_hash VARCHAR(500),
    autorizado_por VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 2. Crear tabla de Detalles de Orden de Compra
CREATE TABLE IF NOT EXISTS orden_compra_detalles (
    id_orden_detalle BIGINT AUTO_INCREMENT PRIMARY KEY,
    orden_compra_id BIGINT,
    id_producto BIGINT,
    nombre_producto VARCHAR(255),
    cantidad INT NOT NULL,
    precio_unitario DOUBLE NOT NULL,
    CONSTRAINT fk_detalle_orden 
        FOREIGN KEY (orden_compra_id) 
        REFERENCES ordenes_compra (id_orden) 
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;