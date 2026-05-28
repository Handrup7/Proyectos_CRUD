package CRUD_MySQL_MVC_EMPRESA.Controlador;

import CRUD_MySQL_MVC_EMPRESA.Modelo.Consultas;
import CRUD_MySQL_MVC_EMPRESA.Modelo.Departamento;
import CRUD_MySQL_MVC_EMPRESA.Modelo.Empleado;
import CRUD_MySQL_MVC_EMPRESA.Vista.Ventana;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 * Controlador principal de la aplicación MVC.
 * <p>
 * Escucha los eventos de la interfaz, válida entrada del formulario y delega
 * las operaciones en la capa de consultas contra la base de datos.
 */
public class Controlador implements ActionListener {
    /**
     * Instancia reutilizada del modelo empleado.
     */
    private final Empleado empleado;
    /**
     * Instancia reutilizada del modelo departamento.
     */
    private final Departamento departamento;
    /**
     * Acceso a datos y consultas SQL.
     */
    private final Consultas consultas;
    /**
     * Vista principal de la aplicación.
     */
    public final Ventana ventana;

    /**
     * Crea el controlador y registra todos los listeners de botones.
     *
     * @param empleado     modelo de empleado
     * @param departamento modelo de departamento
     * @param consultas    capa de acceso a datos
     * @param ventana      interfaz grafica principal
     */
    public Controlador(Empleado empleado, Departamento departamento, Consultas consultas, Ventana ventana) {
        this.empleado = empleado;
        this.departamento = departamento;
        this.consultas = consultas;
        this.ventana = ventana;

        this.ventana.btn_empleadoAgregar.addActionListener(this);
        this.ventana.btn_empleadoEliminar.addActionListener(this);
        this.ventana.btn_empleadoActualizar.addActionListener(this);
        this.ventana.btn_departamentoAgregar.addActionListener(this);
        this.ventana.btn_departamentoEliminar.addActionListener(this);
        this.ventana.btn_departamentoActualizar.addActionListener(this);
        this.ventana.btn_departamentoDatos.addActionListener(this);
        this.ventana.btn_modificarSalario.addActionListener(this);
        this.ventana.btn_empleadosComision.addActionListener(this);
        this.ventana.btn_modificarSalarioTodos.addActionListener(this);
        this.ventana.btn_empleadosComisionTodos.addActionListener(this);
        this.ventana.btn_limpiar.addActionListener(this);
    }

    /**
     * Inicializa el controlador.
     * <p>
     * En la implementación actual no requiere lógica adicional porque la ventana
     * ya queda visible tras su construcción.
     */
    public void iniciar() {
    }

    /**
     * Gestiona todos los eventos de la interfaz y redirige a la accion adecuada.
     *
     * @param e evento de acción generado por un botón
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == this.ventana.btn_empleadoAgregar) {
                agregarEmpleado();
                return;
            }

            if (e.getSource() == this.ventana.btn_departamentoAgregar) {
                agregarDepartamento();
                return;
            }

            if (e.getSource() == this.ventana.btn_empleadoEliminar) {
                eliminarEmpleado();
                return;
            }

            if (e.getSource() == this.ventana.btn_empleadoActualizar) {
                actualizarEmpleado();
                return;
            }

            if (e.getSource() == this.ventana.btn_departamentoEliminar) {
                eliminarDepartamento();
                return;
            }

            if (e.getSource() == this.ventana.btn_departamentoActualizar) {
                actualizarDepartamento();
                return;
            }

            if (e.getSource() == this.ventana.btn_departamentoDatos) {
                mostrarDepartamentos();
                return;
            }

            if (e.getSource() == this.ventana.btn_modificarSalario) {
                modificarSalarioFijo();
                return;
            }

            if (e.getSource() == this.ventana.btn_empleadosComision) {
                mostrarEmpleadosArucasComision();
                return;
            }

            if (e.getSource() == this.ventana.btn_modificarSalarioTodos) {
                modificarSalarioRangoPersonalizado();
                return;
            }

            if (e.getSource() == this.ventana.btn_empleadosComisionTodos) {
                mostrarEmpleadosFiltroPersonalizado();
                return;
            }

            if (e.getSource() == this.ventana.btn_limpiar) {
                limpiar();
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage());
        }
    }

    /**
     * Inserta un empleado usando los datos del formulario.
     */
    private void agregarEmpleado() {
        Empleado datos = construirEmpleadoDesdeFormulario(false);
        if (datos == null) {
            return;
        }

        if (consultas.insertarEmpleado(datos)) {
            JOptionPane.showMessageDialog(null, "Empleado insertado correctamente.");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, mensajeErrorBd("insertar empleado"));
        }
    }

    /**
     * Actualiza un empleado existente según su codigo.
     */
    private void actualizarEmpleado() {
        Integer codigoEmpleado = parseEnteroSeguro(this.ventana.txt_empleadoCodigo.getText(), "código de empleado");
        if (codigoEmpleado == null) {
            return;
        }

        Empleado datos = construirEmpleadoDesdeFormulario(true);
        if (datos == null) {
            return;
        }

        if (consultas.actualizarEmpleado(codigoEmpleado, datos)) {
            JOptionPane.showMessageDialog(null, "Empleado actualizado correctamente.");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, mensajeErrorBd("actualizar empleado"));
        }
    }

    /**
     * Elimina un empleado identificado por su codigo.
     */
    private void eliminarEmpleado() {
        Integer codigoEmpleado = parseEnteroSeguro(this.ventana.txt_empleadoCodigo.getText(), "código de empleado");
        if (codigoEmpleado == null) {
            return;
        }

        if (consultas.eliminarEmpleado(codigoEmpleado)) {
            JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente.");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, mensajeErrorBd("eliminar empleado"));
        }
    }

    /**
     * Inserta un departamento usando los datos del formulario.
     */
    private void agregarDepartamento() {
        Departamento datos = construirDepartamentoDesdeFormulario();
        if (datos == null) {
            return;
        }

        if (consultas.insertarDepartamento(datos)) {
            JOptionPane.showMessageDialog(null, "Departamento insertado correctamente.");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, mensajeErrorBd("insertar departamento"));
        }
    }

    /**
     * Actualiza un departamento existente según su codigo.
     */
    private void actualizarDepartamento() {
        Integer codigoDepartamento = parseEnteroSeguro(this.ventana.txt_departamentoCodigo.getText(), "código de departamento");
        if (codigoDepartamento == null) {
            return;
        }

        Departamento datos = construirDepartamentoDesdeFormulario();
        if (datos == null) {
            return;
        }

        if (consultas.actualizarDepartamento(codigoDepartamento, datos)) {
            JOptionPane.showMessageDialog(null, "Departamento actualizado correctamente.");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, mensajeErrorBd("actualizar departamento"));
        }
    }

    /**
     * Elimina un departamento identificado por su codigo.
     */
    private void eliminarDepartamento() {
        Integer codigoDepartamento = parseEnteroSeguro(this.ventana.txt_departamentoCodigo.getText(), "código de departamento");
        if (codigoDepartamento == null) {
            return;
        }

        if (consultas.eliminarDepartamento(codigoDepartamento)) {
            JOptionPane.showMessageDialog(null, "Departamento eliminado correctamente.");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, mensajeErrorBd("eliminar departamento"));
        }
    }

    /**
     * Consulta y muestra la lista de departamentos en un diálogo.
     */
    private void mostrarDepartamentos() {
        mostrarTextoConsulta("Departamentos", consultas.listarDepartamentos());
    }

    /**
     * Ejecuta una subida fija de salario para el rango 1750-2250.
     */
    private void modificarSalarioFijo() {
        int filas = consultas.modificarSalarioPorRango(1750f, 2250f, 10f);
        if (filas >= 0) {
            JOptionPane.showMessageDialog(null, "salario actualizado para " + filas + " empleado(s). Rango: 1750-2250, incremento: 10%.");
        } else {
            JOptionPane.showMessageDialog(null, mensajeErrorBd("modificar salarios"));
        }
    }

    /**
     * Muestra empleados de Arucas con comision superior al 20%.
     */
    private void mostrarEmpleadosArucasComision() {
        mostrarTextoConsulta(
                "Empleados (Arucas y comisión > 20)",
                consultas.listarEmpleadosPorLocalidadYComision("Arucas", 20f)
        );
    }

    /**
     * Solicita rango y porcentaje al usuario para actualizar salarios de forma personalizada.
     */
    private void modificarSalarioRangoPersonalizado() {
        Float minimo = pedirFloat("Introduce salario mínimo:");
        if (minimo == null) {
            return;
        }

        Float maximo = pedirFloat("Introduce salario máximo:");
        if (maximo == null) {
            return;
        }

        Float porcentaje = pedirFloat("Introduce porcentaje de incremento (ej: 8.5):");
        if (porcentaje == null) {
            return;
        }

        int filas = consultas.modificarSalarioPorRango(minimo, maximo, porcentaje);
        if (filas >= 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "salario actualizado para " + filas + " empleado(s).\nRango: " + minimo + " - " + maximo + "\nIncremento: " + porcentaje + "%"
            );
        } else {
            JOptionPane.showMessageDialog(null, mensajeErrorBd("modificar salarios"));
        }
    }

    /**
     * Construye un mensaje de error orientado al usuario a partir del último error SQL.
     *
     * @param accion descripción de la acción que fallo
     * @return mensaje legible para mostrar en interfaz
     */
    private String mensajeErrorBd(String accion) {
        String detalle = consultas.getUltimoError();
        if (detalle == null || detalle.trim().isEmpty()) {
            return "No se pudo " + accion + ".";
        }

        if (detalle.toLowerCase().contains("access denied")) {
            return "No se pudo " + accion + ". Credenciales incorrectas en config.txt (usuario/password) o sin permisos sobre la BD empresa.";
        }

        return "No se pudo " + accion + ". Detalle BD: " + detalle;
    }

    /**
     * Pide filtros al usuario y muestra empleados que cumplan localidad y comision minima.
     */
    private void mostrarEmpleadosFiltroPersonalizado() {
        String localidad = JOptionPane.showInputDialog(null, "Introduce localidad:");
        if (localidad == null || localidad.trim().isEmpty()) {
            return;
        }

        Float comisionMinima = pedirFloat("Introduce comisión minima:");
        if (comisionMinima == null) {
            return;
        }

        mostrarTextoConsulta(
                "Empleados por localidad/comision",
                consultas.listarEmpleadosPorLocalidadYComision(localidad.trim(), comisionMinima)
        );
    }

    /**
     * Válida el formulario de empleado y actualiza la instancia de modelo reutilizada.
     *
     * @param paraActualizar indica si se está preparando una actualización
     * @return empleado con datos validados, o {@code null} si hay errores de entrada
     */
    private Empleado construirEmpleadoDesdeFormulario(boolean paraActualizar) {
        String nombre = textoObligatorio(this.ventana.txt_empleadoNombre.getText(), "nombre de empleado");
        if (nombre == null) {
            return null;
        }

        String cargo = textoObligatorio(this.ventana.txt_empleadoCargo.getText(), "cargo de empleado");
        if (cargo == null) {
            return null;
        }

        Date fecha = parseFechaSeguro(this.ventana.txt_empleadoFecha.getText());
        if (fecha == null) {
            return null;
        }

        Float salario = parseFloatSeguro(this.ventana.txt_empleadoSalario.getText(), "salario");
        if (salario == null) {
            return null;
        }

        Float comision = parseFloatSeguro(this.ventana.txt_empleadoComision.getText(), "comisión");
        if (comision == null) {
            return null;
        }

        Integer codigoDepartamento = parseEnteroSeguro(this.ventana.txt_empleadoDepartamentoCodigo.getText(), "código de departamento del empleado");
        if (codigoDepartamento == null) {
            return null;
        }

        if (!paraActualizar) {
            this.ventana.txt_empleadoCodigo.setText("");
        }

        empleado.setNombre(nombre);
        empleado.setCargo(cargo);
        empleado.setFecha(fecha);
        empleado.setSalario(salario);
        empleado.setComision(comision);
        empleado.setCodigo_departamento(codigoDepartamento);
        return empleado;
    }

    /**
     * Válida el formulario de departamento y actualiza la instancia de modelo reutilizada.
     *
     * @return departamento con datos validados, o {@code null} si hay errores de entrada
     */
    private Departamento construirDepartamentoDesdeFormulario() {
        String nombre = textoObligatorio(this.ventana.txt_departamentoNombre.getText(), "nombre de departamento");
        if (nombre == null) {
            return null;
        }

        String localidad = textoObligatorio(this.ventana.txt_departamentoLocalidad.getText(), "localidad de departamento");
        if (localidad == null) {
            return null;
        }

        departamento.setNombre(nombre);
        departamento.setLocalidad(localidad);
        return departamento;
    }

    /**
     * Muestra un texto largo de resultados dentro de un área desplazable.
     *
     * @param titulo    título de la ventana emergente
     * @param contenido texto a mostrar
     */
    private void mostrarTextoConsulta(String titulo, String contenido) {
        JTextArea area = new JTextArea(contenido, 14, 70);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        JOptionPane.showMessageDialog(null, new JScrollPane(area), titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Convierte una cadena a entero mostrando mensajes de validación.
     *
     * @param valor       texto introducido
     * @param nombreCampo nombre funcional del campo para el mensaje
     * @return entero parseado, o {@code null} si el dato no es valido
     */
    private Integer parseEnteroSeguro(String valor, String nombreCampo) {
        String texto = valor == null ? "" : valor.trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " no puede estar vacío.");
            return null;
        }

        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " debe ser numérico.");
            return null;
        }
    }

    /**
     * Convierte una cadena a decimal aceptando coma o punto.
     *
     * @param valor       texto introducido
     * @param nombreCampo nombre funcional del campo para el mensaje
     * @return decimal parseado, o {@code null} si el dato no es válido
     */
    private Float parseFloatSeguro(String valor, String nombreCampo) {
        String texto = valor == null ? "" : valor.trim().replace(',', '.');
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " no puede estar vacío.");
            return null;
        }

        try {
            return Float.parseFloat(texto);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " debe ser decimal valido.");
            return null;
        }
    }

    /**
     * Convierte una cadena a fecha SQL con formato AAAA-MM-DD.
     *
     * @param valor texto introducido
     * @return fecha parseada, o {@code null} si el formato es inválido
     */
    private Date parseFechaSeguro(String valor) {
        String texto = valor == null ? "" : valor.trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo " + "fecha de empleado (AAAA-MM-DD)" + " no puede estar vacío.");
            return null;
        }

        try {
            return Date.valueOf(texto);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Formato invalido en " + "fecha de empleado (AAAA-MM-DD)" + ". Usa AAAA-MM-DD.");
            return null;
        }
    }

    /**
     * Válida que un texto obligatorio no este vacío.
     *
     * @param valor       texto introducido
     * @param nombreCampo nombre funcional del campo para el mensaje
     * @return texto limpio, o {@code null} si esta vacío
     */
    private String textoObligatorio(String valor, String nombreCampo) {
        String texto = valor == null ? "" : valor.trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " no puede estar vacío.");
            return null;
        }
        return texto;
    }

    /**
     * Solícita un decimal por diálogo y lo valida.
     *
     * @param mensaje mensaje mostrado al usuario
     * @return valor decimal válido, o {@code null} si se cancela o hay error
     */
    private Float pedirFloat(String mensaje) {
        String entrada = JOptionPane.showInputDialog(null, mensaje);
        if (entrada == null) {
            return null;
        }
        return parseFloatSeguro(entrada, mensaje.toLowerCase());
    }

    /**
     * Limpia todos los campos del formulario de empleado y departamento.
     */
    private void limpiar() {
        this.ventana.txt_empleadoCodigo.setText("");
        this.ventana.txt_empleadoNombre.setText("");
        this.ventana.txt_empleadoCargo.setText("");
        this.ventana.txt_empleadoFecha.setText("");
        this.ventana.txt_empleadoSalario.setText("");
        this.ventana.txt_empleadoComision.setText("");
        this.ventana.txt_empleadoDepartamentoCodigo.setText("");
        this.ventana.txt_departamentoCodigo.setText("");
        this.ventana.txt_departamentoLocalidad.setText("");
        this.ventana.txt_departamentoNombre.setText("");
    }
}
