package CRUD_MySQL_MVC_EMPRESA.Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Capa de acceso a datos para operaciones CRUD y consultas de negocio.
 * <p>
 * Extiende {@link Conexion} para reutilizar la lógica de apertura de conexiones
 * y mantiene un mensaje de último error para que la capa de controlador pueda
 * informar al usuario.
 */
public class Consultas extends Conexion {
    /**
     * Último mensaje de error SQL registrado.
     */
    private String ultimoError = "";

    /**
     * Devuelve el último error producido en una operación SQL.
     *
     * @return mensaje de error, o cadena vacía si no hubo errores recientes
     */
    public String getUltimoError() {
        return ultimoError;
    }

    /**
     * Limpia el estado de error antes de una nueva operación correcta.
     */
    private void limpiarUltimoError() {
        ultimoError = "";
    }

    /**
     * Guarda un error SQL para consulta posterior desde el controlador.
     *
     * @param e excepción SQL capturada
     */
    private void registrarError(SQLException e) {
        ultimoError = e.getMessage();
        System.err.println(ultimoError);
    }

    /**
     * Inserta un empleado en la base de datos.
     *
     * @param empleado datos del empleado a insertar
     * @return {@code true} si se inserta al menos una fila; {@code false} en caso contrario
     */
    public boolean insertarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO Empleado(nombre, cargo, fecha, salario, comision, codigo_departamento) VALUES(?,?,?,?,?,?)";

        try (Connection con = this.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getCargo());
            ps.setDate(3, empleado.getFecha());
            ps.setFloat(4, empleado.getSalario());
            ps.setFloat(5, empleado.getComision());
            ps.setInt(6, empleado.getCodigo_departamento());
            limpiarUltimoError();
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    /**
     * Inserta un departamento en la base de datos.
     *
     * @param departamento datos del departamento a insertar
     * @return {@code true} si se inserta al menos una fila; {@code false} en caso contrario
     */
    public boolean insertarDepartamento(Departamento departamento) {
        String sql = "INSERT INTO Departamento(nombre, localidad) VALUES(?,?)";

        try (Connection con = this.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, departamento.getNombre());
            ps.setString(2, departamento.getLocalidad());
            limpiarUltimoError();
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    /**
     * Actualiza un empleado existente identificado por su codigo.
     *
     * @param codigoEmpleado código del empleado a actualizar
     * @param empleado       nuevos datos del empleado
     * @return {@code true} si se actualiza al menos una fila; {@code false} en caso contrario
     */
    public boolean actualizarEmpleado(int codigoEmpleado, Empleado empleado) {
        String sql = "UPDATE Empleado SET nombre=?, cargo=?, fecha=?, salario=?, comision=?, codigo_departamento=? WHERE codigo_empleado=?";

        try (Connection con = this.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getCargo());
            ps.setDate(3, empleado.getFecha());
            ps.setFloat(4, empleado.getSalario());
            ps.setFloat(5, empleado.getComision());
            ps.setInt(6, empleado.getCodigo_departamento());
            ps.setInt(7, codigoEmpleado);
            limpiarUltimoError();
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    /**
     * Elimina un empleado por su codigo.
     *
     * @param codigoEmpleado código del empleado a eliminar
     * @return {@code true} si se elimina al menos una fila; {@code false} en caso contrario
     */
    public boolean eliminarEmpleado(int codigoEmpleado) {
        String sql = "DELETE FROM Empleado WHERE codigo_empleado=?";

        try (Connection con = this.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigoEmpleado);
            limpiarUltimoError();
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    /**
     * Actualiza un departamento existente identificado por su codigo.
     *
     * @param codigoDepartamento código del departamento a actualizar
     * @param departamento       nuevos datos del departamento
     * @return {@code true} si se actualiza al menos una fila; {@code false} en caso contrario
     */
    public boolean actualizarDepartamento(int codigoDepartamento, Departamento departamento) {
        String sql = "UPDATE Departamento SET nombre=?, localidad=? WHERE codigo_departamento=?";

        try (Connection con = this.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, departamento.getNombre());
            ps.setString(2, departamento.getLocalidad());
            ps.setInt(3, codigoDepartamento);
            limpiarUltimoError();
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    /**
     * Elimina un departamento por su codigo.
     *
     * @param codigoDepartamento código del departamento a eliminar
     * @return {@code true} si se elimina al menos una fila; {@code false} en caso contrario
     */
    public boolean eliminarDepartamento(int codigoDepartamento) {
        String sql = "DELETE FROM Departamento WHERE codigo_departamento=?";

        try (Connection con = this.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigoDepartamento);
            limpiarUltimoError();
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            registrarError(e);
            return false;
        }
    }

    /**
     * Lista todos los departamentos ordenados por codigo.
     *
     * @return texto formateado para mostrar en interfaz
     */
    public String listarDepartamentos() {
        String sql = "SELECT codigo_departamento, nombre, localidad FROM Departamento ORDER BY codigo_departamento";
        StringBuilder sb = new StringBuilder();

        try (Connection con = this.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            limpiarUltimoError();

            while (rs.next()) {
                sb.append("COD: ").append(rs.getInt("código_departamento"))
                        .append(" | NOMBRE: ").append(rs.getString("nombre"))
                        .append(" | LOCALIDAD: ").append(rs.getString("localidad"))
                        .append(System.lineSeparator());
            }
        } catch (SQLException e) {
            registrarError(e);
            return "Error al consultar departamentos: " + e.getMessage();
        }

        return sb.isEmpty() ? "No hay departamentos registrados." : sb.toString();
    }

    /**
     * Incrementa el salario de empleados dentro de un rango dado.
     *
     * @param minimo     salario mínimo del filtro
     * @param maximo     salario máximo del filtro
     * @param porcentaje porcentaje de incremento
     * @return numero de filas afectadas, o {@code -1} si ocurre error SQL
     */
    public int modificarSalarioPorRango(float minimo, float maximo, float porcentaje) {
        String sql = "UPDATE Empleado SET salario = salario + (salario * ? / 100) WHERE salario BETWEEN ? AND ?";

        try (Connection con = this.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setFloat(1, porcentaje);
            ps.setFloat(2, minimo);
            ps.setFloat(3, maximo);
            limpiarUltimoError();
            return ps.executeUpdate();
        } catch (SQLException e) {
            registrarError(e);
            return -1;
        }
    }

    /**
     * Lista empleada filtrando por localidad del departamento y comision minima.
     *
     * @param localidad      localidad exacta del departamento
     * @param comisionMinima comisión minima exigida al empleado
     * @return texto formateado para mostrar en interfaz
     */
    public String listarEmpleadosPorLocalidadYComision(String localidad, float comisionMinima) {
        String sql = "SELECT e.codigo_empleado, e.nombre, e.cargo, e.salario, e.comision, d.nombre AS nombre_departamento, d.localidad " +
                "FROM Empleado e JOIN Departamento d ON e.codigo_departamento = d.codigo_departamento " +
                "WHERE d.localidad = ? AND e.comision > ? ORDER BY e.codigo_empleado";

        StringBuilder sb = new StringBuilder();

        try (Connection con = this.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, localidad);
            ps.setFloat(2, comisionMinima);
            limpiarUltimoError();

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sb.append("EMP: ").append(rs.getInt("código_empleado"))
                            .append(" | NOMBRE: ").append(rs.getString("nombre"))
                            .append(" | CARGO: ").append(rs.getString("cargo"))
                            .append(" | SALARIO: ").append(rs.getFloat("salario"))
                            .append(" | COMISION: ").append(rs.getFloat("comisión"))
                            .append(" | DEPTO: ").append(rs.getString("nombre_departamento"))
                            .append(" (").append(rs.getString("localidad")).append(")")
                            .append(System.lineSeparator());
                }
            }
        } catch (SQLException e) {
            registrarError(e);
            return "Error al consultar empleados: " + e.getMessage();
        }

        return sb.isEmpty() ? "No hay empleados que cumplan el filtro." : sb.toString();

    }

}
