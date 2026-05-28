# CRUD MySQL (FUTBOL) — Java + Swing (MVC)

Aplicación Java con interfaz Swing siguiendo patrón MVC, conectada a MySQL mediante JDBC.
Gestiona datos relacionados con un dominio de fútbol (BD `futbol`).

## Estructura del proyecto
- `MainApp.java`: arranque de la aplicación
- `Controlador/`: lógica de eventos y validaciones
- `Modelo/`: acceso a datos (DAO/Consultas) e inicialización de BD/tablas
- `Vista/`: interfaz Swing
- `Javadoc/`: documentación generada

## Base de datos y conexión
- Host/puerto: `jdbc:mysql://localhost:3306/`
- Base de datos: `futbol`
- Driver: `com.mysql.cj.jdbc.Driver`

### Credenciales (seguridad)
Las credenciales no están en el código. Se leen desde variables de entorno:

- `DB_USER`
- `DB_PASSWORD`

## Requisitos
- Java (recomendado JDK 17 o superior)
- MySQL en ejecución (localhost)
- MySQL Connector/J (driver JDBC) añadido al IDE (el repositorio no usa Maven/Gradle)

## Ejecución (IntelliJ)
1. Añade el driver JDBC (MySQL Connector/J) al proyecto.
2. Configura variables de entorno en tu configuración de ejecución:
    - `DB_USER=root` (o tu usuario)
    - `DB_PASSWORD=TU_PASSWORD`
3. Ejecuta `MainApp.java`.

## Notas
- La aplicación puede crear la BD/tablas si no existen (según implementación en `Modelo/InicializadorBD`).