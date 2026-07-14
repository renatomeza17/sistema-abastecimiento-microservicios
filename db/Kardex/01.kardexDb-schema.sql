-- Limpiar tablas si existen (útil para reiniciar el entorno)
DROP TABLE IF EXISTS public.kardex_movimiento CASCADE;
DROP TABLE IF EXISTS public.kardex CASCADE;
DROP TABLE IF EXISTS public.producto CASCADE;

-- 1. TABLA: PRODUCTO (Catálogo Maestro)
CREATE TABLE public.producto (
    id_producto BIGSERIAL NOT NULL,
    codigo VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    unidad_medida VARCHAR(255) NOT NULL,
    activo BOOLEAN DEFAULT TRUE, -- Atributo clave para tu listarProductosActivos()
    CONSTRAINT producto_pkey PRIMARY KEY (id_producto),
    CONSTRAINT uk_producto_codigo UNIQUE (codigo),
    CONSTRAINT uk_producto_nombre UNIQUE (nombre)
);

-- 2. TABLA: KARDEX (Ficha Técnica / Cabecera)
CREATE TABLE public.kardex (
    id_kardex BIGSERIAL NOT NULL,
    id_producto BIGINT NOT NULL,
    stock_actual INTEGER DEFAULT 0,
    stock_minimo INTEGER DEFAULT 0,
    ubicacion_almacen VARCHAR(20),
    fecha_apertura DATE,
    caracteristicas VARCHAR(100),
    CONSTRAINT kardex_pkey PRIMARY KEY (id_kardex),
    CONSTRAINT uk_kardex_producto UNIQUE (id_producto),
    CONSTRAINT fk_kardex_producto FOREIGN KEY (id_producto) 
        REFERENCES public.producto (id_producto) MATCH SIMPLE
        ON UPDATE NO ACTION 
        ON DELETE RESTRICT
);

-- 3. TABLA: KARDEX_MOVIMIENTO (Historial de Entradas y Salidas)
CREATE TABLE public.kardex_movimiento (
    id_movimiento BIGSERIAL NOT NULL,
    id_kardex BIGINT NOT NULL,
    tipo_movimiento VARCHAR(30) NOT NULL,
    cantidad INTEGER NOT NULL,
    saldo_stock INTEGER NOT NULL,
    fecha_movimiento TIMESTAMP(6) WITHOUT TIME ZONE NOT NULL,
    documento_referencia VARCHAR(40),
    observaciones VARCHAR(100),
    CONSTRAINT kardex_movimiento_pkey PRIMARY KEY (id_movimiento),
    CONSTRAINT fk_movimiento_kardex FOREIGN KEY (id_kardex) 
        REFERENCES public.kardex (id_kardex) MATCH SIMPLE
        ON UPDATE NO ACTION 
        ON DELETE CASCADE
);

ALTER TABLE public.producto OWNER TO postgres;
ALTER TABLE public.kardex OWNER TO postgres;
ALTER TABLE public.kardex_movimiento OWNER TO postgres;