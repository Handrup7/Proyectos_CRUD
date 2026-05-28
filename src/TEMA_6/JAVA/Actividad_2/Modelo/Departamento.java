package TEMA_6.JAVA.Actividad_2.Modelo;

/**
 * Modelo de datos que representa un departamento de la empresa.
 */
public class Departamento {
    /**
     * Nombre del departamento.
     */
    private String nombre;
    /**
     * Localidad donde se ubica el departamento.
     */
    private String localidad;

    /**
     * Crea un departamento vacío.
     */
    public Departamento() {
    }

    /**
     * Crea un departamento con sus datos principales.
     *
     * @param nombre    nombre del departamento
     * @param localidad localidad del departamento
     */
    public Departamento(String nombre, String localidad) {
        this.nombre = nombre;
        this.localidad = localidad;
    }


    /**
     * Devuelve el nombre del departamento.
     *
     * @return nombre del departamento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualiza el nombre del departamento.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve la localidad del departamento.
     *
     * @return localidad del departamento
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Actualiza la localidad del departamento.
     *
     * @param localidad nueva localidad
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
