package TEMA_6.JAVA.Actividad_2.Modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestiona la configuración de acceso a base de datos y crea conexiones JDBC.
 * <p>
 * Los parámetros de conexión se leen desde un archivo {@code config.txt}.
 */
public class Conexion {
    /**
     * URL JDBC de la base de datos.
     */
    private String url;
    /**
     * Usuario de la base de datos.
     */
    private String user;
    /**
     * Contraseña del usuario de la base de datos.
     */
    private String password;
    /**
     * Nombre completo de la clase del driver JDBC.
     */
    private String driver;

    /**
     * Crea una instancia y carga la configuración de conexión desde disco.
     */
    public Conexion() {
        cargarConfiguracion();
    }

    /**
     * Devuelve una nueva conexión JDBC.
     *
     * @return conexión abierta a la base de datos
     * @throws SQLException si falla la carga del driver o la conexión
     */
    public Connection getConexion() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver no encontrado: " + driver, e);
        }

        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Lee el archivo de configuración y rellena los parámetros de conexión.
     *
     * @throws RuntimeException si no se encuentra el archivo o hay errores de lectura
     */
    private void cargarConfiguracion() {
        Path rutaConfig = buscarConfig();
        if (rutaConfig == null) {
            throw new RuntimeException("No se encontró config.txt para la conexión a BD.");
        }

        try (BufferedReader br = Files.newBufferedReader(rutaConfig)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty() || !linea.contains("=")) {
                    continue;
                }
                String[] partes = linea.split("=", 2);
                String clave = partes[0].trim();
                String valor = partes[1].trim();
                switch (clave) {
                    case "url":
                        url = valor;
                        break;
                    case "user":
                        user = valor;
                        break;
                    case "password":
                        password = valor;
                        break;
                    case "driver":
                        driver = valor;
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer config.txt", e);
        }
    }

    /**
     * Busca el archivo {@code config.txt} en rutas típicas del proyecto.
     *
     * @return ruta encontrada o {@code null} si no existe en ninguna ruta
     */
    private Path buscarConfig() {
        Path[] posiblesRutas = {
                Paths.get("src", "TEMA_6", "JAVA", "Actividad_2", "config.txt"),
                Paths.get("TEMA_6", "JAVA", "Actividad_2", "config.txt"),
                Paths.get("config.txt")
        };

        for (Path ruta : posiblesRutas) {
            if (Files.exists(ruta)) {
                return ruta;
            }
        }
        return null;
    }

}
