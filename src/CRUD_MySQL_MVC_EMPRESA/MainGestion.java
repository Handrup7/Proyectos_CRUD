package CRUD_MySQL_MVC_EMPRESA;

import CRUD_MySQL_MVC_EMPRESA.Controlador.Controlador;
import CRUD_MySQL_MVC_EMPRESA.Modelo.Consultas;
import CRUD_MySQL_MVC_EMPRESA.Modelo.Departamento;
import CRUD_MySQL_MVC_EMPRESA.Modelo.Empleado;
import CRUD_MySQL_MVC_EMPRESA.Vista.Ventana;

/**
 * Punto de entrada de la aplicación de gestion.
 * <p>
 * Crea las instancias de modelo, vista y controlador siguiendo el patron MVC,
 * y arranca la interfaz gráfica.
 */
public class MainGestion {
    /**
     * Inicia la aplicación.
     *
     * @param args argumentos de linea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        //Creamos los objetos del modelo
        Empleado empleado = new Empleado();
        Departamento departamento = new Departamento();
        Consultas consultas = new Consultas();

        //Creamos la vista
        Ventana ventana = new Ventana();

        //Creamos el objeto del controlador
        Controlador controlador = new Controlador(empleado, departamento, consultas, ventana);
        //Lo iniciamos
        controlador.iniciar();
    }
}
