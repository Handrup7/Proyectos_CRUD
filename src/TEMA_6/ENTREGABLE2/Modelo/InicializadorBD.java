package TEMA_6.ENTREGABLE2.Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InicializadorBD {

    // 1) Crear BD si no existe
    public static void crearBaseDatosSiNoExiste(String urlSinBD, String user, String password) {
        try (Connection con = DriverManager.getConnection(urlSinBD, user, password);
             Statement st = con.createStatement()) {
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS futbol");
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear la base de datos: " + e.getMessage(), e);
        }
    }

    // 2) Crear tablas si no existen
    public static void crearTablasSiNoExisten(String urlBD, String user, String password) {

        String sqlJugadores = """
                CREATE TABLE IF NOT EXISTS Jugadores (
                    id_jugador INT AUTO_INCREMENT PRIMARY KEY,
                    nombre VARCHAR(40),
                    dorsal INT,
                    equipo VARCHAR(40),
                    goles INT,
                    asistencias INT,
                    tarjetas_rojas INT,
                    tarjetas_amarillas INT
                )
                """;

        String sqlCompeticiones = """
                CREATE TABLE IF NOT EXISTS Competiciones (
                    id_competicion INT AUTO_INCREMENT PRIMARY KEY,
                    liga INT,
                    copa INT,
                    champions INT,
                    tiempo_total INT,
                    id_jugador INT,
                    FOREIGN KEY (id_jugador) REFERENCES Jugadores(id_jugador)
                )
                """;
        try (Connection con = DriverManager.getConnection(urlBD, user, password);
             Statement st = con.createStatement()) {
            st.executeUpdate(sqlJugadores);
            st.executeUpdate(sqlCompeticiones);
        } catch (SQLException e) {
            throw new RuntimeException("Error al crear las tablas: " + e.getMessage(), e);
        }
    }
}
