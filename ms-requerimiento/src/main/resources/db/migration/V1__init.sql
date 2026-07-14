CREATE TABLE IF NOT EXISTS contador (
    prefijo     VARCHAR(10)  NOT NULL,
    anio        INT          NOT NULL,
    ultimo_numero INT        NOT NULL,
    PRIMARY KEY (prefijo, anio)
);

CREATE TABLE IF NOT EXISTS requerimiento (
    id                UUID           NOT NULL DEFAULT gen_random_uuid(),
    codigo            VARCHAR(20)    NOT NULL,
    descripcion       TEXT           NOT NULL,
    estado            VARCHAR(20)    NOT NULL DEFAULT 'PENDIENTE',
    fecha_creacion    TIMESTAMP      NOT NULL DEFAULT now(),
    fecha_actualizacion TIMESTAMP,
    version           BIGINT         NOT NULL DEFAULT 1,
    id_dependencia    UUID           NOT NULL,
    id_usuario        BIGINT         NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_requerimiento_codigo UNIQUE (codigo)
);

CREATE TABLE IF NOT EXISTS requerimiento_detalle (
    id                    UUID        NOT NULL DEFAULT gen_random_uuid(),
    cantidad              INT         NOT NULL,
    unidad_medida         VARCHAR(20) NOT NULL,
    observacion_especifica TEXT,
    id_producto           BIGINT      NOT NULL,
    id_requerimiento      UUID        NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_detalle_requerimiento FOREIGN KEY (id_requerimiento)
        REFERENCES requerimiento(id)
);

CREATE TABLE IF NOT EXISTS proveedor (
    id           UUID           NOT NULL DEFAULT gen_random_uuid(),
    ruc          VARCHAR(11)    NOT NULL,
    razon_social VARCHAR(200)   NOT NULL,
    direccion    VARCHAR(255),
    id_usuario   BIGINT         NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_proveedor_ruc UNIQUE (ruc),
    CONSTRAINT uk_proveedor_usuario UNIQUE (id_usuario)
);

CREATE TABLE IF NOT EXISTS proforma (
    id                UUID           NOT NULL DEFAULT gen_random_uuid(),
    codigo            VARCHAR(20)    NOT NULL,
    estado            VARCHAR(20)    NOT NULL DEFAULT 'PENDIENTE',
    fecha_recepcion   TIMESTAMP      NOT NULL DEFAULT now(),
    fecha_actualizacion TIMESTAMP,
    version           BIGINT         NOT NULL DEFAULT 1,
    precio_total      DECIMAL(12,2)  NOT NULL,
    id_usuario        BIGINT         NOT NULL,
    id_requerimiento  UUID           NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT uk_proforma_codigo UNIQUE (codigo),
    CONSTRAINT fk_proforma_requerimiento FOREIGN KEY (id_requerimiento)
        REFERENCES requerimiento(id)
);

CREATE TABLE IF NOT EXISTS detalle_proforma (
    id                    UUID           NOT NULL DEFAULT gen_random_uuid(),
    cantidad              INT            NOT NULL,
    observacion_especifica TEXT,
    precio_unitario       DECIMAL(12,2)  NOT NULL,
    id_producto           BIGINT         NOT NULL,
    id_proforma           UUID           NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_detalle_proforma FOREIGN KEY (id_proforma)
        REFERENCES proforma(id)
);
