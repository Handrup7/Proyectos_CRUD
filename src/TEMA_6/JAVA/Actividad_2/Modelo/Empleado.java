package TEMA_6.JAVA.Actividad_2.Modelo;

import java.sql.Date;

/**
 * Modelo de datos que representa a un empleado.
 */
public class Empleado {
    /**
     * Nombre del empleado.
     */
    private String nombre;
    /**
     * Cargo o puesto del empleado.
     */
    private String cargo;
    /**
     * Fecha asociada al empleado (alta/contratacion según contexto).
     */
    private Date fecha;
    /**
     * Salario base del empleado.
     */
    private float salario;
    /**
     * Comisión del empleado en porcentaje.
     */
    private float comision;
    /**
     * Código del departamento al que pertenece.
     */
    private int codigoDepartamento;

    /**
     * Crea un empleado vacío.
     */
    public Empleado() {
    }

    /**
     * Crea un empleado con todos sus datos.
     *
     * @param nombre             nombre del empleado
     * @param cargo              cargo del empleado
     * @param fecha              fecha del empleado
     * @param salario            salario del empleado
     * @param comision           comisión del empleado
     * @param codigoDepartamento código del departamento
     */
    public Empleado(String nombre, String cargo, Date fecha, float salario, float comision, int codigoDepartamento) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.fecha = fecha;
        this.salario = salario;
        this.comision = comision;
        this.codigoDepartamento = codigoDepartamento;
    }

    /**
     * Devuelve el nombre del empleado.
     *
     * @return nombre del empleado
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualiza el nombre del empleado.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el cargo del empleado.
     *
     * @return cargo del empleado
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Actualiza el cargo del empleado.
     *
     * @param cargo nuevo cargo
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Devuelve la fecha del empleado.
     *
     * @return fecha del empleado
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Actualiza la fecha del empleado.
     *
     * @param fecha nueva fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve el salario del empleado.
     *
     * @return salario actual
     */
    public float getSalario() {
        return salario;
    }

    /**
     * Actualiza el salario del empleado.
     *
     * @param salario nuevo salario
     */
    public void setSalario(float salario) {
        this.salario = salario;
    }

    /**
     * Devuelve la comision del empleado.
     *
     * @return comisión actual
     */
    public float getComision() {
        return comision;
    }

    /**
     * Actualiza la comision del empleado.
     *
     * @param comision nueva comisión
     */
    public void setComision(float comision) {
        this.comision = comision;
    }

    /**
     * Devuelve el codigo del departamento del empleado.
     *
     * @return código de departamento
     */
    public int getCodigo_departamento() {
        return codigoDepartamento;
    }

    /**
     * Actualiza el codigo de departamento del empleado.
     *
     * @param codigo_departamento nuevo código de departamento
     */
    public void setCodigo_departamento(int codigo_departamento) {
        this.codigoDepartamento = codigo_departamento;
    }
}
