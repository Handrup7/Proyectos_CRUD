# Actividad 2 - JDBC con MySQL

Este modulo implementa una app Swing (MVC) conectada a MySQL para gestionar `Empleado` y `Departamento`.

## Requisitos funcionales implementados

1. Insercion en ambas tablas (IDs autoincrementales, no se piden al insertar).
2. Actualizacion de ambas tablas por codigo.
3. Eliminacion de registros en ambas tablas por codigo.
4. Consulta de nombre/localidad de todos los departamentos.
5. Modificacion de salario en rango fijo 1750-2250 (incremento del 10%).
6. Listado de empleados de departamentos en Arucas con comision > 20.
7. Variante del punto 5 pidiendo rango e incremento al usuario.
8. Variante del punto 6 pidiendo localidad y comision minima al usuario.

## Estructura

- `MainGestion.java`: arranque de la app.
- `Controlador/Controlador.java`: logica de eventos y validaciones.
- `Modelo/Conexion.java`: conexion JDBC usando `config.txt`.
- `Modelo/Consultas.java`: sentencias SQL.
- `Vista/Ventana.java`: interfaz Swing.

## Configuracion de BD

Archivo `config.txt`:

- `url=jdbc:mysql://localhost:3306/empresa`
- `user=...`
- `password=...`
- `driver=com.mysql.cj.jdbc.Driver`

## Esquema SQL recomendado (auto_increment)

```sql
CREATE DATABASE IF NOT EXISTS empresa;
USE empresa;

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

## Compilacion rapida (PowerShell)

```powershell
& "C:\Program Files\Java\jdk-25\bin\javac.exe" -encoding UTF-8 -cp "D:\AlumnoM\1ºA_DAM\PRO_DAM\src\TEMA_6\JAVA\Actividad_2\mysql-connector-j-9.6.0.jar;D:\AlumnoM\1ºA_DAM\PRO_DAM\src" -d "D:\AlumnoM\1ºA_DAM\PRO_DAM\out\production\PRO_DAM" "D:\AlumnoM\1ºA_DAM\PRO_DAM\src\TEMA_6\JAVA\Actividad_2\MainGestion.java"
```

## Ejecucion rapida (PowerShell)

```powershell
& "C:\Program Files\Java\jdk-25\bin\java.exe" -classpath "D:\AlumnoM\1ºA_DAM\PRO_DAM\out\production\PRO_DAM;D:\AlumnoM\1ºA_DAM\PRO_DAM\src\TEMA_6\JAVA\Actividad_2\mysql-connector-j-9.6.0.jar" TEMA_6.JAVA.Actividad_2.MainGestion
```

