-- Base de datos: loginDb (microservicio ms-login)
-- Tablas: usuarios, roles, modulo, rol_modulo, usuario_roles


CREATE TABLE IF NOT EXISTS public.usuarios
(
    id_usuario bigserial NOT NULL,
    -- Datos de acceso
    username character varying(50) NOT NULL,
    password character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    activo boolean NOT NULL DEFAULT true,
    nombres character varying(100) NOT NULL,
    apellido_paterno character varying(100) NOT NULL,
    apellido_materno character varying(100),
    ndocumento character varying(20) NOT NULL,
    tipo_documento character varying(20) NOT NULL,
    direccion character varying(200),
    fecha_nacimiento date,
    sexo character varying(20),
    telefono character varying(20),
    created_at timestamp NOT NULL DEFAULT now(),
    updated_at timestamp NOT NULL DEFAULT now(),
    CONSTRAINT usuarios_pkey PRIMARY KEY (id_usuario),
    CONSTRAINT uk_usuarios_username UNIQUE (username),
    CONSTRAINT uk_usuarios_email UNIQUE (email),
    CONSTRAINT uk_usuarios_ndocumento UNIQUE (ndocumento)
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