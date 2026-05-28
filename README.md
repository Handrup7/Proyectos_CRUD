# Proyectos_CRUD (Java + MySQL)

Repositorio de prácticas/proyectos académicos de CRUD en Java conectando con MySQL (JDBC).
Incluye dos proyectos dentro de `src/`:

- `src/CRUD_MySQL_MVC_FUTBOL` → BD: `futbol`
- `src/CRUD_MySQL_MVC_EMPRESA` → BD: `actividadjava`

Ambos usan MySQL en `localhost:3306`.

## Tecnologías
- Java
- MySQL
- JDBC (MySQL Connector/J)
- Git/GitHub

## Requisitos
- Java instalado (recomendado JDK 17 o superior)
- MySQL instalado y en ejecución (localhost)
- IntelliJ IDEA (recomendado)
- Driver JDBC de MySQL: **MySQL Connector/J** (`mysql-connector-j-8.x.x.jar`)

> Nota: Este proyecto **no usa Maven/Gradle**, por lo que el driver JDBC hay que añadirlo manualmente al IDE.

## 1) Añadir el driver JDBC (MySQL Connector/J) en IntelliJ
1. Descarga MySQL Connector/J (ZIP, "Platform Independent").
2. Extrae el ZIP y localiza el archivo `.jar` (ej: `mysql-connector-j-8.4.x.jar`).
3. En IntelliJ: **File → Project Structure → Libraries → + → Java**
4. Selecciona el `.jar` y aplícalo al módulo del proyecto.
5. Comprueba que aparece en **External Libraries**.

## 2) Configurar variables de entorno (DB_USER / DB_PASSWORD)
Las credenciales de BD se leen desde variables de entorno:

- `DB_USER`
- `DB_PASSWORD`

### Opción A: Configurarlas en IntelliJ (recomendado)
1. **Run → Edit Configurations…**
2. Selecciona la configuración de la clase `MainApp` del proyecto que vayas a ejecutar.
3. En **Environment variables**, añade:
    - `DB_USER=root` (o tu usuario)
    - `DB_PASSWORD=TU_PASSWORD`
4. Apply → OK → Run

### Opción B: Configurarlas en Windows (CMD, temporal)
```bat
set DB_USER=root
set DB_PASSWORD=TU_PASSWORD
```

## 3) Configuración de conexión (proyecto EMPRESA)
El proyecto EMPRESA usa el driver:

- `driver = com.mysql.cj.jdbc.Driver`

y esta URL (por defecto):

- `url = jdbc:mysql://localhost:3306/actividadjava`

> Importante: **no subas contraseñas al repositorio**. Cada usuario debe configurar sus propias credenciales mediante `DB_USER` y `DB_PASSWORD`.

## 4) Ejecutar
1. Abre el proyecto en IntelliJ.
2. Añade el driver JDBC (paso 1).
3. Configura las variables de entorno (paso 2).
4. Ejecuta la clase `MainApp` del proyecto que quieras probar.

## Autor
Handrup7 (estudiante DAM)