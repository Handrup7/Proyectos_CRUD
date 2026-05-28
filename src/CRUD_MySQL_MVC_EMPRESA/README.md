# CRUD MySQL (EMPRESA) — Java + Swing (MVC)

Aplicación Java con interfaz Swing siguiendo patrón MVC, conectada a MySQL mediante JDBC.
Permite gestionar entidades relacionadas con `Empleado` y `Departamento`.

## Funcionalidades (resumen)
- Inserción, actualización y eliminación de registros en ambas tablas
- Consultas y listados personalizados
- Validaciones desde el controlador (según la práctica)

## Estructura del proyecto
- `MainGestion.java`: arranque de la app
- `Controlador/Controlador.java`: lógica de eventos y validaciones
- `Modelo/Conexion.java`: conexión JDBC (lee configuración)
- `Modelo/Consultas.java`: sentencias SQL
- `Vista/Ventana.java`: interfaz Swing

## Configuración de Base de Datos
Este proyecto se ejecuta contra MySQL en `localhost:3306`.

Base de datos usada (por defecto):
- `actividadjava`

Driver:
- `com.mysql.cj.jdbc.Driver`

### Credenciales (recomendado)
No se suben contraseñas al repositorio. Configura tus credenciales de forma local usando variables de entorno:

- `DB_USER`
- `DB_PASSWORD`

> Si tu implementación actual usa un archivo local tipo `config.txt`, úsalo solo en tu PC y evita subir credenciales reales a GitHub.

## Esquema SQL (orientativo)
```sql
CREATE DATABASE IF NOT EXISTS actividadjava;
USE actividadjava;

CREATE TABLE IF NOT EXISTS Departamento (
  codigo_departamento INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(20) NOT NULL,
  localidad VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS Empleado (
  codigo_empleado INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(25) NOT NULL,
  cargo VARCHAR(15) NOT NULL,
  fecha DATE NOT NULL,
  salario DECIMAL(10,2) NOT NULL,
  comision DECIMAL(10,2) NOT NULL,
  codigo_departamento INT NOT NULL,
  CONSTRAINT fk_empleado_departamento
    FOREIGN KEY (codigo_departamento)
    REFERENCES Departamento(codigo_departamento)
    ON UPDATE CASCADE
    ON DELETE RESTRICT
);
```

## Ejecución
1. Añade el driver JDBC (MySQL Connector/J) al proyecto en tu IDE.
2. Configura `DB_USER` y `DB_PASSWORD`.
3. Ejecuta `MainGestion.java`.