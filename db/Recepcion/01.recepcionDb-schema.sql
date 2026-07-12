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

ALTER TABLE IF EXISTS public.pedido_pendiente
    OWNER to postgres;
