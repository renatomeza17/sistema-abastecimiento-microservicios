-- Base de datos: loginDb (microservicio ms-login)
-- Tablas: personas, usuarios, roles, modulo, rol_modulo, usuario_roles
-- Estas tablas SI mantienen sus FOREIGN KEY entre si, porque todas viven
-- dentro de la misma base de datos (son del mismo dominio: Usuarios y Accesos).

CREATE TABLE IF NOT EXISTS public.personas
(
    id_persona bigserial NOT NULL,
    apellido_materno character varying(100) NOT NULL,
    apellido_paterno character varying(100) NOT NULL,
    created_at timestamp NOT NULL DEFAULT now(),
    direccion character varying(200) NOT NULL,
    fecha_nacimiento date NOT NULL,
    nombres character varying(100) NOT NULL,
    ndocumento character varying(20) NOT NULL,
    sexo character varying(20) NOT NULL,
    telefono character varying(20) NOT NULL,
    tipo_documento character varying(20) NOT NULL,
    updated_at timestamp NOT NULL DEFAULT now(),
    CONSTRAINT personas_pkey PRIMARY KEY (id_persona),
    CONSTRAINT uk_personas_ndocumento UNIQUE (ndocumento)
);

CREATE TABLE IF NOT EXISTS public.usuarios
(
    id_usuario bigserial NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(50) NOT NULL,
    id_persona bigint,
    CONSTRAINT usuarios_pkey PRIMARY KEY (id_usuario),
    CONSTRAINT uk_usuarios_username UNIQUE (username),
    CONSTRAINT uk_usuarios_id_persona UNIQUE (id_persona),
    CONSTRAINT fk_usuarios_persona FOREIGN KEY (id_persona)
        REFERENCES public.personas (id_persona)
);

CREATE TABLE IF NOT EXISTS public.roles
(
    id_rol bigserial NOT NULL,
    descripcion character varying(255),
    nombre character varying(50) NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id_rol),
    CONSTRAINT uk_roles_nombre UNIQUE (nombre)
);

CREATE TABLE IF NOT EXISTS public.modulo
(
    id_mod serial NOT NULL,
    activo character varying(10) NOT NULL DEFAULT 'true',
    descripcion character varying(255) NOT NULL,
    url character varying(255) NOT NULL,
    CONSTRAINT modulo_pkey PRIMARY KEY (id_mod),
    CONSTRAINT uk_modulo_descripcion UNIQUE (descripcion),
    CONSTRAINT uk_modulo_url UNIQUE (url)
);

CREATE TABLE IF NOT EXISTS public.rol_modulo
(
    id_rol_mod serial NOT NULL,
    puede_actualizar boolean NOT NULL DEFAULT false,
    puede_crear boolean NOT NULL DEFAULT false,
    puede_eliminar boolean NOT NULL DEFAULT false,
    puede_leer boolean NOT NULL DEFAULT false,
    id_mod integer NOT NULL,
    id_rol bigint NOT NULL,
    CONSTRAINT rol_modulo_pkey PRIMARY KEY (id_rol_mod),
    CONSTRAINT fk_rol_modulo_modulo FOREIGN KEY (id_mod)
        REFERENCES public.modulo (id_mod),
    CONSTRAINT fk_rol_modulo_rol FOREIGN KEY (id_rol)
        REFERENCES public.roles (id_rol)
);

CREATE TABLE IF NOT EXISTS public.usuario_roles
(
    id_usuario bigint NOT NULL,
    id_rol bigint NOT NULL,
    CONSTRAINT usuario_roles_pkey PRIMARY KEY (id_usuario, id_rol),
    CONSTRAINT fk_usuario_roles_usuario FOREIGN KEY (id_usuario)
        REFERENCES public.usuarios (id_usuario),
    CONSTRAINT fk_usuario_roles_rol FOREIGN KEY (id_rol)
        REFERENCES public.roles (id_rol)
);

-- Datos base: los 3 roles que pide HU02 (Jefe, Director, Apoyo)
INSERT INTO public.roles (nombre, descripcion) VALUES
    ('JEFE', 'Jefe de dependencia'),
    ('DIRECTOR', 'Director administrativo, autoriza y firma documentos'),
    ('APOYO', 'Personal de apoyo de la dependencia')
ON CONFLICT (nombre) DO NOTHING;