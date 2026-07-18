-- requerimientos-proformas-service
-- id_usuario e id_producto son BIGINT: usuario y producto viven en otros
-- microservicios con IDs autoincrementales (otro gestor de BD).
-- id_dependencia es UUID: dependencia vive en ms-pedidodependencia (CockroachDB),
-- por eso no lleva REFERENCES (esta fuera de esta base de datos).

CREATE TABLE requerimiento (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(20) NOT NULL UNIQUE,
    descripcion TEXT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP,
    id_dependencia UUID NOT NULL,
    id_usuario BIGINT NOT NULL,
    version BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE requerimiento_detalle (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cantidad INT NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    observacion_especifica TEXT,
    id_producto BIGINT NOT NULL,
    id_requerimiento UUID NOT NULL REFERENCES requerimiento(id)
);

CREATE TABLE proforma (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(20) NOT NULL UNIQUE,
    estado VARCHAR(20) NOT NULL,
    fecha_recepcion TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP,
    precio_total DECIMAL(12,2) NOT NULL,
    id_usuario BIGINT NOT NULL,
    id_requerimiento UUID NOT NULL REFERENCES requerimiento(id),
    version BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE detalle_proforma (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cantidad INT NOT NULL,
    observacion_especifica TEXT,
    precio_unitario DECIMAL(12,2) NOT NULL,
    id_producto BIGINT NOT NULL,
    id_proforma UUID NOT NULL REFERENCES proforma(id)
);

CREATE TABLE proveedor (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    ruc VARCHAR(11) NOT NULL UNIQUE,
    razon_social VARCHAR(200) NOT NULL,
    direccion VARCHAR(255),
    id_usuario BIGINT NOT NULL UNIQUE
);

CREATE TABLE contador (
    prefijo VARCHAR(10) NOT NULL,
    anio INT4 NOT NULL,
    ultimo_numero INT NOT NULL,
    PRIMARY KEY (prefijo, anio)
);
