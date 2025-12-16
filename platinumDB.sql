-- 🚗 BASE DE DATOS: Platinum Car Detailers
-- Versión: 2025-12-15
-- Autores: Juan Ureña y Leonardo Ortiz
-- ================================================================

DROP DATABASE IF EXISTS platinum;
CREATE DATABASE platinum DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE platinum;

-- 3. Usuarios y permisos
CREATE USER IF NOT EXISTS 'platinum_user'@'%' IDENTIFIED BY 'Platinum_123.';
CREATE USER IF NOT EXISTS 'platinum_report'@'%' IDENTIFIED BY 'Platinum_Report.';
GRANT SELECT, INSERT, UPDATE, DELETE ON platinum.* TO 'platinum_user'@'%';
GRANT SELECT ON platinum.* TO 'platinum_report'@'%';
FLUSH PRIVILEGES;

-- 4.1 CLIENTE
CREATE TABLE cliente (
  id_cliente INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  telefono VARCHAR(25),
  correo VARCHAR(100),
  fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_cliente)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 4.2 VEHICULO
CREATE TABLE vehiculo (
  id_vehiculo INT NOT NULL AUTO_INCREMENT,
  id_cliente INT NULL,
  placa VARCHAR(20),
  tipo_vehiculo VARCHAR(30),
  marca VARCHAR(50),
  modelo VARCHAR(50),
  anno INT,
  color VARCHAR(30),
  fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_vehiculo),
  INDEX idx_vehiculo_cliente (id_cliente),
  CONSTRAINT fk_vehiculo_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 4.3 SERVICIO
CREATE TABLE servicio (
  id_servicio INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(255) NOT NULL,
  tipo_vehiculo ENUM('Sedan','SUV','Pickup','Motocicleta') NOT NULL,
  paquete ENUM('Basico','Premium','VIP') NOT NULL,
  duracion_minutos INT NOT NULL,
  precio DECIMAL(10,2) NOT NULL,
  activo TINYINT(1) NOT NULL DEFAULT 1,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_servicio)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 4.4 CITA
CREATE TABLE cita (
  id_cita INT NOT NULL AUTO_INCREMENT,
  nombre_cliente VARCHAR(100) NOT NULL,
  telefono VARCHAR(25) NOT NULL,
  correo VARCHAR(100),
  tipo_vehiculo VARCHAR(30) NOT NULL,
  placa VARCHAR(20),
  servicio_solicitado VARCHAR(100) NOT NULL,
  fecha_cita DATE NOT NULL,
  hora_cita TIME NOT NULL,
  estado ENUM('Pendiente','Confirmada','Rechazada','Cancelada','Completada') NOT NULL DEFAULT 'Pendiente',
  notas TEXT,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_cita)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 4.5 MEMBRESIA
CREATE TABLE membresia (
  id_membresia INT NOT NULL AUTO_INCREMENT,
  nombre_cliente VARCHAR(100) NOT NULL,
  correo VARCHAR(100) NOT NULL,
  tipo ENUM('Mensual','Anual') NOT NULL,
  fecha_inicio DATE NOT NULL,
  fecha_fin DATE NOT NULL,
  estado ENUM('Activa','Vencida','Cancelada') NOT NULL DEFAULT 'Activa',
  precio DECIMAL(10,2) NOT NULL,
  activo TINYINT(1) NOT NULL DEFAULT 1,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_membresia)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 4.6 PROMOCION
CREATE TABLE promocion (
  id_promocion INT NOT NULL AUTO_INCREMENT,
  titulo VARCHAR(100) NOT NULL,
  descripcion VARCHAR(255) NOT NULL,
  porcentaje_descuento DECIMAL(5,2),
  fecha_inicio DATE NOT NULL,
  fecha_fin DATE NOT NULL,
  imagen VARCHAR(1024),
  activa TINYINT(1) NOT NULL DEFAULT 1,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_promocion)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 4.7 COTIZACION
CREATE TABLE cotizacion (
  id_cotizacion INT NOT NULL AUTO_INCREMENT,
  nombre_cliente VARCHAR(100) NOT NULL,
  correo VARCHAR(100),
  telefono VARCHAR(25),
  tipo_vehiculo VARCHAR(30),
  placa VARCHAR(20),
  descripcion_solicitud TEXT,
  monto_estimado DECIMAL(10,2),
  estado ENUM('EnRevision','Enviada','Aceptada','Rechazada') DEFAULT 'EnRevision',
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_cotizacion)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 4.8 IMAGENES DE COTIZACION
CREATE TABLE imagen_cotizacion (
  id_imagen INT NOT NULL AUTO_INCREMENT,
  id_cotizacion INT NOT NULL,
  ruta VARCHAR(512) NOT NULL,
  fecha_subida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_imagen),
  INDEX idx_imagen_cotizacion (id_cotizacion),
  CONSTRAINT fk_imagen_cotizacion FOREIGN KEY (id_cotizacion) REFERENCES cotizacion(id_cotizacion) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 4.9 CONTACTO
CREATE TABLE contacto (
  id_contacto INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(100),
  telefono VARCHAR(25),
  correo VARCHAR(100),
  mensaje TEXT,
  canal ENUM('WhatsApp','Correo') DEFAULT 'WhatsApp',
  fecha_contacto TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id_contacto)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 5.1 ROL
CREATE TABLE rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  descripcion VARCHAR(255),
  PRIMARY KEY (id_rol)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 5.2 USUARIO (MODIFICADA PARA COINCIDIR CON LA ENTIDAD Usuario.java)
CREATE TABLE usuario (
  id_usuario INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  apellidos VARCHAR(50),
  correo VARCHAR(75) UNIQUE,
  telefono VARCHAR(25),
  ruta_imagen VARCHAR(1024),
  activo TINYINT(1) NOT NULL DEFAULT 1,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  fecha_modificacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id_usuario)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 5.3 USUARIO_ROL
CREATE TABLE usuario_rol (
  id_usuario INT NOT NULL,
  id_rol INT NOT NULL,
  PRIMARY KEY (id_usuario, id_rol),
  CONSTRAINT fk_usuario_rol_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_usuario_rol_rol FOREIGN KEY (id_rol) REFERENCES rol(id_rol) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

-- 6. DATOS DE EJEMPLO
INSERT INTO servicio (nombre, descripcion, tipo_vehiculo, paquete, duracion_minutos, precio, activo) VALUES
('Lavado Básico', 'Lavado exterior rápido', 'Sedan', 'Basico', 30, 8000.00, 1),
('Detallado Interior', 'Limpieza profunda de cabina', 'SUV', 'Premium', 90, 25000.00, 1),
('Cerámica Premium', 'Aplicación de recubrimiento cerámico', 'Pickup', 'VIP', 240, 80000.00, 1);

INSERT INTO promocion (titulo, descripcion, porcentaje_descuento, fecha_inicio, fecha_fin, activa) VALUES
('Promo Fin de Semana', '10% de descuento en servicios Premium', 10.00, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), 1);