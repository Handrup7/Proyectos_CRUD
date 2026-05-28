package TEMA_6.ENTREGABLE2.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Consultas extends Conexion_Entregable {
    private String ultimoError = "";

    public String getUltimoError() {
        return ultimoError;
    }

    private void limpiarError() {
        ultimoError = "";
    }

    private void registrarError(SQLException e) {
        ultimoError = e.getMessage();
        System.err.println("SQL ERROR: " + ultimoError);
    }

    public boolean insertarJugador(Jugador j) {
        String sql = """
                INSERT INTO Jugadores (nombre, dorsal, equipo, goles, asistencias, tarjetas_rojas, tarjetas_amarillas)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        try (Connection con = this.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, j.getNombre());
            ps.setInt(2, j.getDorsal());
            ps.setString(3, j.getEquipo());
            ps.setInt(4, j.getGoles());
            ps.setInt(5, j.getAsistencias());
            ps.setInt(6, j.getTarjetasRojas());
            ps.setInt(7, j.getTarjetasAmarillas());

            limpiarError();
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    public boolean actualizarJugador(int idJugador, Jugador j) {
        String sql = """
                UPDATE Jugadores
                SET nombre=?, dorsal=?, equipo=?, goles=?, asistencias=?, tarjetas_rojas=?, tarjetas_amarillas=?
                WHERE id_jugador=?
                """;

        try (Connection con = this.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, j.getNombre());
            ps.setInt(2, j.getDorsal());
            ps.setString(3, j.getEquipo());
            ps.setInt(4, j.getGoles());
            ps.setInt(5, j.getAsistencias());
            ps.setInt(6, j.getTarjetasRojas());
            ps.setInt(7, j.getTarjetasAmarillas());
            ps.setInt(8, idJugador);

            limpiarError();
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    public boolean eliminarJugador(int idJugador) {
        String sql = "DELETE FROM Jugadores WHERE id_jugador=?";

        try (Connection con = this.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idJugador);

            limpiarError();
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    public String listarJugadores() {
        String sql = """
                SELECT id_jugador, nombre, dorsal, equipo, goles, asistencias, tarjetas_rojas, tarjetas_amarillas
                FROM Jugadores
                ORDER BY id_jugador
                """;

        StringBuilder sb = new StringBuilder();

        try (Connection con = this.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            limpiarError();

            while (rs.next()) {
                sb.append("ID: ").append(rs.getInt("id_jugador"))
                        .append(" | Nombre: ").append(rs.getString("nombre"))
                        .append(" | Dorsal: ").append(rs.getInt("dorsal"))
                        .append(" | Equipo: ").append(rs.getString("equipo"))
                        .append(" | Goles: ").append(rs.getInt("goles"))
                        .append(" | Asist: ").append(rs.getInt("asistencias"))
                        .append(" | Rojas: ").append(rs.getInt("tarjetas_rojas"))
                        .append(" | Amarillas: ").append(rs.getInt("tarjetas_amarillas"))
                        .append(System.lineSeparator());
            }

        } catch (SQLException e) {
            registrarError(e);
            return "Error listando jugadores: " + e.getMessage();
        }

        return sb.isEmpty() ? "No hay jugadores registrados." : sb.toString();
    }

    // ---------- CRUD COMPETICIONES ----------

    public boolean insertarCompeticion(Competicion c) {
        String sql = """
                INSERT INTO Competiciones(liga, copa, champions, tiempo_total, id_jugador)
                VALUES(?,?,?,?,?)
                """;

        int total = c.getLiga() + c.getCopa() + c.getChampions();
        c.setTiempoTotal(total);

        try (Connection con = this.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getLiga());
            ps.setInt(2, c.getCopa());
            ps.setInt(3, c.getChampions());
            ps.setInt(4, c.getTiempoTotal());
            ps.setInt(5, c.getIdJugador());

            limpiarError();
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    public boolean actualizarCompeticion(int idCompeticion, Competicion c) {
        String sql = """
                UPDATE Competiciones
                SET liga=?, copa=?, champions=?, tiempo_total=?, id_jugador=?
                WHERE id_competicion=?
                """;

        int total = c.getLiga() + c.getCopa() + c.getChampions();
        c.setTiempoTotal(total);

        try (Connection con = this.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getLiga());
            ps.setInt(2, c.getCopa());
            ps.setInt(3, c.getChampions());
            ps.setInt(4, c.getTiempoTotal());
            ps.setInt(5, c.getIdJugador());
            ps.setInt(6, idCompeticion);

            limpiarError();
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    public boolean eliminarCompeticion(int idCompeticion) {
        String sql = "DELETE FROM Competiciones WHERE id_competicion=?";

        try (Connection con = this.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCompeticion);

            limpiarError();
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    public String listarCompeticiones() {
        String sql = """
                SELECT id_competicion, liga, copa, champions, tiempo_total, id_jugador
                FROM Competiciones
                ORDER BY id_competicion
                """;

        StringBuilder sb = new StringBuilder();

        try (Connection con = this.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            limpiarError();

            while (rs.next()) {
                sb.append("ID_COMP: ").append(rs.getInt("id_competicion"))
                        .append(" | Liga: ").append(rs.getInt("liga"))
                        .append(" | Copa: ").append(rs.getInt("copa"))
                        .append(" | Champions: ").append(rs.getInt("champions"))
                        .append(" | Total: ").append(rs.getInt("tiempo_total"))
                        .append(" | ID_Jugador: ").append(rs.getInt("id_jugador"))
                        .append(System.lineSeparator());
            }

        } catch (SQLException e) {
            registrarError(e);
            return "Error listando competiciones: " + e.getMessage();
        }

        return sb.isEmpty() ? "No hay competiciones registradas." : sb.toString();
    }
}
