package CRUD_MySQL_MVC_FUTBOL.Modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion_Entregable {

    private static final String BASE_DATOS = "futbol";

    private String url;
    private String user;
    private String password;
    private String driver;

    public Conexion_Entregable() {
        cargarConfiguracion();
    }

    public Connection getConexion() throws SQLException {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC no encontrado: " + driver, e);
        }
        return DriverManager.getConnection(urlConBaseDatos(), user, password);
    }

    private String urlConBaseDatos() {
        if (url == null || url.isBlank()) {
            throw new IllegalStateException("La URL de conexión no está configurada.");
        }

        String urlNormalizada = url.trim();
        if (urlNormalizada.endsWith("/")) {
            return urlNormalizada + BASE_DATOS;
        }

        return urlNormalizada;
    }

    private void cargarConfiguracion() {
        Path rutaConfig = buscarConfig();
        if (rutaConfig == null) {
            throw new RuntimeException("No se encontró Config_Entregable.txt para la conexión.");
        }

        try (BufferedReader br = Files.newBufferedReader(rutaConfig)) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty() || !linea.contains("=")) continue;

                String[] partes = linea.split("=", 2);
                String clave = partes[0].trim();
                String valor = partes[1].trim();

                switch (clave) {
                    case "url" -> url = valor;
                    case "user" -> user = valor;
                    case "password" -> password = valor;
                    case "driver" -> driver = valor;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error leyendo Config_Entregable.txt", e);
        }
    }

    private Path buscarConfig() {
        Path[] posiblesRutas = {
                Paths.get("src", "TEMA_6", "CRUD_MySQL_MVC_FUTBOL", "Config_Entregable.txt"),
                Paths.get("TEMA_6", "CRUD_MySQL_MVC_FUTBOL", "Config_Entregable.txt"),
                Paths.get("src", "TEMA_6", "ENTREGABLE", "Config_Entregable.txt"),
                Paths.get("TEMA_6", "ENTREGABLE", "Config_Entregable.txt"),
                Paths.get("Config_Entregable.txt")
        };

        for (Path ruta : posiblesRutas) {
            if (Files.exists(ruta)) return ruta;
        }
        return null;
    }
}
