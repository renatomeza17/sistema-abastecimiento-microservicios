-- pedido-dependencia-service
-- id_usuario e id_producto son BIGINT: usuario y producto viven en otros
-- microservicios con IDs autoincrementales.
-- dependencia SI vive aqui (master data propia de este microservicio),
-- por eso pedido_dependencia.id_dependencia tiene REFERENCES local.

CREATE TABLE dependencia (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nombre VARCHAR(150) NOT NULL UNIQUE,
    descripcion TEXT
);

CREATE TABLE pedido_dependencia (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    codigo VARCHAR(20) NOT NULL UNIQUE,
    descripcion TEXT NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP,
    id_usuario BIGINT NOT NULL,
    id_dependencia UUID NOT NULL REFERENCES dependencia(id),
    version BIGINT NOT NULL DEFAULT 0
);

CREATE TABLE detalle_pedido_dependencia (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cantidad INT NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    observacion_especifica TEXT,
    id_producto BIGINT NOT NULL,
    id_pedido UUID NOT NULL REFERENCES pedido_dependencia(id)
);

CREATE TABLE contador (
    prefijo VARCHAR(10) NOT NULL,
    anio INT4 NOT NULL,
    ultimo_numero INT NOT NULL,
    PRIMARY KEY (prefijo, anio)
);
