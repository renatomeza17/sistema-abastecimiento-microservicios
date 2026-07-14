-- Table: public.pedido_pendiente

CREATE TABLE IF NOT EXISTS public.pedido_pendiente
(
    id_pedido_pendiente bigserial NOT NULL,
    id_orden bigint NOT NULL,
    motivo character varying(255) COLLATE pg_catalog."default" NOT NULL,
    observacion text COLLATE pg_catalog."default",
    estado character varying(255) COLLATE pg_catalog."default" NOT NULL DEFAULT 'PENDIENTE',
    fecha_registro timestamp(6) without time zone,
    fecha_resolucion timestamp(6) without time zone,
    CONSTRAINT pedido_pendiente_pkey PRIMARY KEY (id_pedido_pendiente)
);


CREATE TABLE IF NOT EXISTS public.verificacion_recepcion
(
    id_verificacion bigserial NOT NULL,
    id_orden bigint NOT NULL,
    id_usuario_verifica bigint,
    estado character varying(30) NOT NULL DEFAULT 'EN_PROCESO', -- EN_PROCESO | ACEPTADO_TOTAL | RECHAZADO
    fecha_inicio timestamp NOT NULL DEFAULT now(),
    fecha_fin timestamp,
    CONSTRAINT verificacion_recepcion_pkey PRIMARY KEY (id_verificacion)
);

CREATE TABLE IF NOT EXISTS public.detalle_verificacion
(
    id_detalle_verificacion bigserial NOT NULL,
    id_verificacion bigint NOT NULL,
    id_orden_detalle bigint NOT NULL,
    nombre_producto character varying(255),
    cantidad_esperada integer,
    verificado boolean NOT NULL DEFAULT false, -- el checkbox por producto
    observacion character varying(255),
    CONSTRAINT detalle_verificacion_pkey PRIMARY KEY (id_detalle_verificacion),
    CONSTRAINT fk_detalle_verificacion_cabecera FOREIGN KEY (id_verificacion)
        REFERENCES public.verificacion_recepcion (id_verificacion)
);

-- pedido_pendiente puede originarse de un producto rechazado durante la
-- verificacion de recepcion (HU08). Nullable: tambien se puede registrar manual.
ALTER TABLE public.pedido_pendiente
    ADD COLUMN IF NOT EXISTS id_detalle_verificacion bigint NULL;

ALTER TABLE public.pedido_pendiente
    ADD CONSTRAINT fk_pedido_pendiente_detalle_verificacion FOREIGN KEY (id_detalle_verificacion)
        REFERENCES public.detalle_verificacion (id_detalle_verificacion);