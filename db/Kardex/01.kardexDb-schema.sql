DROP TABLE IF EXISTS kardex_movimiento;
DROP TABLE IF EXISTS kardex;
DROP TABLE IF EXISTS producto;
 
CREATE TABLE producto (
    id_producto BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(255) NOT NULL UNIQUE,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    descripcion VARCHAR(200) NOT NULL,
    unidad_medida VARCHAR(255) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);
 
CREATE TABLE kardex (
    id_kardex BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_producto BIGINT NOT NULL UNIQUE,
    stock_actual INT DEFAULT 0,
    stock_minimo INT DEFAULT 0,
    ubicacion_almacen VARCHAR(20),
    fecha_apertura DATE,
    caracteristicas VARCHAR(100),
    CONSTRAINT fk_kardex_producto FOREIGN KEY (id_producto)
        REFERENCES producto (id_producto)
        ON UPDATE NO ACTION ON DELETE RESTRICT
);
 
CREATE TABLE kardex_movimiento (
    id_movimiento BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_kardex BIGINT NOT NULL,
    tipo_movimiento VARCHAR(30) NOT NULL,
    cantidad INT NOT NULL,
    saldo_stock INT NOT NULL,
    fecha_movimiento DATETIME(6) NOT NULL,
    documento_referencia VARCHAR(40),
    observaciones VARCHAR(100),
    CONSTRAINT fk_movimiento_kardex FOREIGN KEY (id_kardex)
        REFERENCES kardex (id_kardex)
        ON UPDATE NO ACTION ON DELETE CASCADE
);