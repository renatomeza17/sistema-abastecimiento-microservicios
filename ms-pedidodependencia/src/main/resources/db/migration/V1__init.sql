CREATE TABLE IF NOT EXISTS contador (
    prefijo     VARCHAR(10)  NOT NULL,
    anio        INT          NOT NULL,
    ultimo_numero INT        NOT NULL,
    PRIMARY KEY (prefijo, anio)
);

CREATE TABLE IF NOT EXISTS dependencia (
    id          UUID           NOT NULL DEFAULT gen_random_uuid(),
    nombre      VARCHAR(150)   NOT NULL,
    descripcion TEXT,
    PRIMARY KEY (id),
    CONSTRAINT uk_dependencia_nombre UNIQUE (nombre)
);

CREATE TABLE IF NOT EXISTS pedido_dependencia (
    id                 UUID           NOT NULL DEFAULT gen_random_uuid(),
    codigo             VARCHAR(20)    NOT NULL,
    descripcion        TEXT           NOT NULL,
    estado             VARCHAR(20)    NOT NULL DEFAULT 'PENDIENTE',
    fecha_creacion     TIMESTAMP      NOT NULL DEFAULT now(),
    fecha_actualizacion TIMESTAMP,
    version            BIGINT         NOT NULL DEFAULT 1,
    id_usuario         BIGINT         NOT NULL,
    id_dependencia     UUID           NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_pedido_codigo UNIQUE (codigo),
    CONSTRAINT fk_pedido_dependencia FOREIGN KEY (id_dependencia)
        REFERENCES dependencia(id)
);

CREATE TABLE IF NOT EXISTS detalle_pedido_dependencia (
    id                    UUID        NOT NULL DEFAULT gen_random_uuid(),
    cantidad              INT         NOT NULL,
    unidad_medida         VARCHAR(20) NOT NULL,
    observacion_especifica TEXT,
    id_producto           BIGINT      NOT NULL,
    id_pedido             UUID        NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_detalle_pedido FOREIGN KEY (id_pedido)
        REFERENCES pedido_dependencia(id)
);
