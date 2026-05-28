package TEMA_6.JAVA.Actividad_2.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Vista principal de la aplicación de gestion de empleados y departamentos.
 * <p>
 * Expone sus componentes para que el controlador pueda registrar listeners,
 * leer datos de entrada y actualizar estados de la interfaz.
 */
public class Ventana extends JFrame {

    /**
     * Campo para el código del empleado (actualizar/eliminar).
     */
    public JTextField txt_empleadoCodigo = new JTextField(20);
    /**
     * Campo para el nombre del empleado.
     */
    public JTextField txt_empleadoNombre = new JTextField(20);
    /**
     * Campo para el cargo del empleado.
     */
    public JTextField txt_empleadoCargo = new JTextField(20);
    /**
     * Campo para la fecha del empleado en formato AAAA-MM-DD.
     */
    public JTextField txt_empleadoFecha = new JTextField(20);
    /**
     * Campo para el salario del empleado.
     */
    public JTextField txt_empleadoSalario = new JTextField(20);
    /**
     * Campo para la comisión del empleado.
     */
    public JTextField txt_empleadoComision = new JTextField(20);
    /**
     * Campo para el código de departamento del empleado.
     */
    public JTextField txt_empleadoDepartamentoCodigo = new JTextField(20);

    /**
     * Campo para el código del departamento (actualizar/eliminar).
     */
    public JTextField txt_departamentoCodigo = new JTextField(20);
    /**
     * Campo para el nombre del departamento.
     */
    public JTextField txt_departamentoNombre = new JTextField(20);
    /**
     * Campo para la localidad del departamento.
     */
    public JTextField txt_departamentoLocalidad = new JTextField(20);

    /**
     * Etiqueta descriptiva del código de empleado.
     */
    public JLabel lbl_empleadoCodigo = new JLabel("Código de empleado (solo actualizar/eliminar):");
    /**
     * Etiqueta descriptiva del nombre de empleado.
     */
    public JLabel lbl_empleadoNombre = new JLabel("Nombre de empleado:");
    /**
     * Etiqueta descriptiva del cargo del empleado.
     */
    public JLabel lbl_empleadoCargo = new JLabel("Cargo de empleado:");
    /**
     * Etiqueta descriptiva de la fecha del empleado.
     */
    public JLabel lbl_empleadoFecha = new JLabel("Fecha de empleado:");
    /**
     * Etiqueta descriptiva del salario del empleado.
     */
    public JLabel lbl_empleadoSalario = new JLabel("Salario de empleado:");
    /**
     * Etiqueta descriptiva de la comisión del empleado.
     */
    public JLabel lbl_empleadoComision = new JLabel("Comisión de empleado:");
    /**
     * Etiqueta descriptiva del departamento del empleado.
     */
    public JLabel lbl_empleadoDepartamentoCodigo = new JLabel("Código de departamento del empleado:");

    /**
     * Etiqueta descriptiva del código de departamento.
     */
    public JLabel lbl_departamentoCodigo = new JLabel("Código del departamento (solo actualizar/eliminar):");
    /**
     * Etiqueta descriptiva del nombre de departamento.
     */
    public JLabel lbl_departamentoNombre = new JLabel("Nombre del Departamento:");
    /**
     * Etiqueta descriptiva de la localidad del departamento.
     */
    public JLabel lbl_departamentoLocalidad = new JLabel("Localidad del Departamento:");

    /**
     * Boton para insertar empleados.
     */
    public JButton btn_empleadoAgregar = new JButton("Agregar Empleado");
    /**
     * Boton para eliminar empleados.
     */
    public JButton btn_empleadoEliminar = new JButton("Eliminar Empleado");
    /**
     * Boton para actualizar empleados.
     */
    public JButton btn_empleadoActualizar = new JButton("Actualizar Empleado");
    /**
     * Boton para insertar departamentos.
     */
    public JButton btn_departamentoAgregar = new JButton("Agregar Departamento");
    /**
     * Boton para eliminar departamentos.
     */
    public JButton btn_departamentoEliminar = new JButton("Eliminar Departamento");
    /**
     * Boton para actualizar departamentos.
     */
    public JButton btn_departamentoActualizar = new JButton("Actualizar Departamento");
    /**
     * Boton para mostrar datos de departamentos.
     */
    public JButton btn_departamentoDatos = new JButton("Datos Departamento");
    /**
     * Boton para aplicar subida salarial predefinida.
     */
    public JButton btn_modificarSalario = new JButton("Modificar salario (1750€-2250€)");
    /**
     * Boton para listar empleados por filtro fijo.
     */
    public JButton btn_empleadosComision = new JButton("Mostar Empleados (Arucas/>20%)");
    /**
     * Boton para aplicar subida salarial personalizada.
     */
    public JButton btn_modificarSalarioTodos = new JButton("Modificar salario (Valores)");
    /**
     * Boton para listar empleados por filtro personalizado.
     */
    public JButton btn_empleadosComisionTodos = new JButton("Mostar Empleados (Ciudad/Comision)");
    /**
     * Boton para limpiar todos los campos del formulario.
     */
    public JButton btn_limpiar = new JButton("Limpiar");

    /**
     * Construye la ventana e inicializa todos sus componentes visuales.
     */
    public Ventana() {
        initComponentes();
    }

    /**
     * Configura layout, paneles, componentes y ajustes finales de la ventana.
     */
    private void initComponentes() {
        setTitle("Manipulación de Empleados y Departamentos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        //Titulo
        JLabel titulo = new JLabel("Gestión de Empleados y Departamentos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(titulo, BorderLayout.NORTH);

        //Panel del centro 4x5
        JPanel panelCentro = new JPanel(new GridLayout(5, 4, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Fila 1
        panelCentro.add(lbl_empleadoCodigo);
        panelCentro.add(txt_empleadoCodigo);
        panelCentro.add(lbl_empleadoComision);
        panelCentro.add(txt_empleadoComision);

        // Fila 2
        panelCentro.add(lbl_empleadoNombre);
        panelCentro.add(txt_empleadoNombre);
        panelCentro.add(lbl_empleadoDepartamentoCodigo);
        panelCentro.add(txt_empleadoDepartamentoCodigo);

        // Fila 3
        panelCentro.add(lbl_empleadoCargo);
        panelCentro.add(txt_empleadoCargo);
        panelCentro.add(lbl_departamentoCodigo);
        panelCentro.add(txt_departamentoCodigo);

        // Fila 4
        panelCentro.add(lbl_empleadoFecha);
        panelCentro.add(txt_empleadoFecha);
        panelCentro.add(lbl_departamentoNombre);
        panelCentro.add(txt_departamentoNombre);

        // Fila 5
        panelCentro.add(lbl_empleadoSalario);
        panelCentro.add(txt_empleadoSalario);
        panelCentro.add(lbl_departamentoLocalidad);
        panelCentro.add(txt_departamentoLocalidad);

        add(panelCentro, BorderLayout.CENTER);

        //Botones
        JPanel panelBotones = new JPanel(new GridLayout(4, 4, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelBotones.add(btn_empleadoAgregar);
        panelBotones.add(btn_empleadoEliminar);
        panelBotones.add(btn_empleadoActualizar);
        panelBotones.add(btn_departamentoAgregar);
        panelBotones.add(btn_departamentoEliminar);
        panelBotones.add(btn_departamentoActualizar);
        panelBotones.add(btn_departamentoDatos);
        panelBotones.add(btn_modificarSalario);
        panelBotones.add(btn_empleadosComision);
        panelBotones.add(btn_modificarSalarioTodos);
        panelBotones.add(btn_empleadosComisionTodos);
        panelBotones.add(btn_limpiar);

        add(panelBotones, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        txt_empleadoCodigo.setToolTipText("No se usa para insertar, la BD lo genera automáticamente");
        txt_departamentoCodigo.setToolTipText("No se usa para insertar, la BD lo genera automáticamente");
    }
}
