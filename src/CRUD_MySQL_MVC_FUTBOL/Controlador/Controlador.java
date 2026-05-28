package CRUD_MySQL_MVC_FUTBOL.Controlador;

import CRUD_MySQL_MVC_FUTBOL.Modelo.Competicion;
import CRUD_MySQL_MVC_FUTBOL.Modelo.Consultas;
import CRUD_MySQL_MVC_FUTBOL.Modelo.Jugador;
import CRUD_MySQL_MVC_FUTBOL.Vista.Ventana;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador implements ActionListener {

    private final Ventana vista;
    private final Consultas dao;

    public Controlador(Ventana vista, Consultas dao) {
        this.vista = vista;
        this.dao = dao;

        // ---- listeners jugadores ----
        vista.btnJugadorInsertar.addActionListener(this);
        vista.btnJugadorActualizar.addActionListener(this);
        vista.btnJugadorEliminar.addActionListener(this);
        vista.btnJugadorVer.addActionListener(this);
        vista.btnJugadorLimpiar.addActionListener(this);

        // ---- listeners competiciones ----
        vista.btnCompInsertar.addActionListener(this);
        vista.btnCompActualizar.addActionListener(this);
        vista.btnCompEliminar.addActionListener(this);
        vista.btnCompVer.addActionListener(this);
        vista.btnCompLimpiar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Object src = e.getSource();

            // ====== JUGADORES ======
            if (src == vista.btnJugadorInsertar) {
                insertarJugador();
                return;
            }
            if (src == vista.btnJugadorActualizar) {
                actualizarJugador();
                return;
            }
            if (src == vista.btnJugadorEliminar) {
                eliminarJugador();
                return;
            }
            if (src == vista.btnJugadorVer) {
                listarJugadores();
                return;
            }
            if (src == vista.btnJugadorLimpiar) {
                limpiarJugador();
                return;
            }

            // ====== COMPETICIONES ======
            if (src == vista.btnCompInsertar) {
                insertarCompeticion();
                return;
            }
            if (src == vista.btnCompActualizar) {
                actualizarCompeticion();
                return;
            }
            if (src == vista.btnCompEliminar) {
                eliminarCompeticion();
                return;
            }
            if (src == vista.btnCompVer) {
                listarCompeticiones();
                return;
            }
            if (src == vista.btnCompLimpiar) {
                limpiarCompeticion();
            }

        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    // =========================
    //          JUGADORES
    // =========================

    private void insertarJugador() {
        Jugador j = construirJugadorDesdeFormulario(false);
        if (j == null) return;

        boolean ok = dao.insertarJugador(j);
        if (ok) {
            vista.areaSalida.setText("Jugador insertado correctamente.\n\n" + dao.listarJugadores());
            limpiarJugador();
        } else {
            mostrarErrorBD("insertar jugador");
        }
    }

    private void actualizarJugador() {
        Integer id = parseEntero(vista.txtJugadorId.getText(), "ID jugador");
        if (id == null) return;

        Jugador j = construirJugadorDesdeFormulario(true);
        if (j == null) return;

        boolean ok = dao.actualizarJugador(id, j);
        if (ok) {
            vista.areaSalida.setText("Jugador actualizado correctamente.\n\n" + dao.listarJugadores());
            limpiarJugador();
        } else {
            mostrarErrorBD("actualizar jugador");
        }
    }

    private void eliminarJugador() {
        Integer id = parseEntero(vista.txtJugadorId.getText(), "ID jugador");
        if (id == null) return;

        boolean ok = dao.eliminarJugador(id);
        if (ok) {
            vista.areaSalida.setText("Jugador eliminado correctamente.\n\n" + dao.listarJugadores());
            limpiarJugador();
        } else {
            mostrarErrorBD("eliminar jugador");
        }
    }

    private void listarJugadores() {
        vista.areaSalida.setText(dao.listarJugadores());
    }

    private void limpiarJugador() {
        vista.txtJugadorId.setText("");
        vista.txtJugadorNombre.setText("");
        vista.txtJugadorDorsal.setText("");
        vista.txtJugadorEquipo.setText("");
        vista.txtJugadorGoles.setText("");
        vista.txtJugadorAsistencias.setText("");
        vista.txtJugadorRojas.setText("");
        vista.txtJugadorAmarillas.setText("");
    }

    private Jugador construirJugadorDesdeFormulario(boolean paraUpdate) {
        String nombre = textoObligatorio(vista.txtJugadorNombre.getText(), "Nombre");
        if (nombre == null) return null;

        Integer dorsal = parseEntero(vista.txtJugadorDorsal.getText(), "Dorsal");
        if (dorsal == null) return null;

        String equipo = textoObligatorio(vista.txtJugadorEquipo.getText(), "Equipo");
        if (equipo == null) return null;

        Integer goles = parseEnteroOpcional(vista.txtJugadorGoles.getText(), "Goles");
        if (goles == null) return null;

        Integer asist = parseEnteroOpcional(vista.txtJugadorAsistencias.getText(), "Asistencias");
        if (asist == null) return null;

        Integer rojas = parseEnteroOpcional(vista.txtJugadorRojas.getText(), "Tarjetas rojas");
        if (rojas == null) return null;

        Integer amarillas = parseEnteroOpcional(vista.txtJugadorAmarillas.getText(), "Tarjetas amarillas");
        if (amarillas == null) return null;

        Jugador j = new Jugador();
        j.setNombre(nombre);
        j.setDorsal(dorsal);
        j.setEquipo(equipo);
        j.setGoles(goles);
        j.setAsistencias(asist);
        j.setTarjetasRojas(rojas);
        j.setTarjetasAmarillas(amarillas);

        // en insert no usamos ID (la BD lo genera), en update el ID va por parámetro
        if (!paraUpdate) vista.txtJugadorId.setText("");

        return j;
    }

    // =========================
    //        COMPETICIONES
    // =========================

    private void insertarCompeticion() {
        Competicion c = construirCompeticionDesdeFormulario(false);
        if (c == null) return;

        boolean ok = dao.insertarCompeticion(c);
        if (ok) {
            vista.areaSalida.setText("Competición insertada correctamente.\n\n" + dao.listarCompeticiones());
            limpiarCompeticion();
        } else {
            mostrarErrorBD("insertar competición");
        }
    }

    private void actualizarCompeticion() {
        Integer idComp = parseEntero(vista.txtCompId.getText(), "ID competición");
        if (idComp == null) return;

        Competicion c = construirCompeticionDesdeFormulario(true);
        if (c == null) return;

        boolean ok = dao.actualizarCompeticion(idComp, c);
        if (ok) {
            vista.areaSalida.setText("Competición actualizada correctamente.\n\n" + dao.listarCompeticiones());
            limpiarCompeticion();
        } else {
            mostrarErrorBD("actualizar competición");
        }
    }

    private void eliminarCompeticion() {
        Integer idComp = parseEntero(vista.txtCompId.getText(), "ID competición");
        if (idComp == null) return;

        boolean ok = dao.eliminarCompeticion(idComp);
        if (ok) {
            vista.areaSalida.setText("Competición eliminada correctamente.\n\n" + dao.listarCompeticiones());
            limpiarCompeticion();
        } else {
            mostrarErrorBD("eliminar competición");
        }
    }

    private void listarCompeticiones() {
        vista.areaSalida.setText(dao.listarCompeticiones());
    }

    private void limpiarCompeticion() {
        vista.txtCompId.setText("");
        vista.txtCompLiga.setText("");
        vista.txtCompCopa.setText("");
        vista.txtCompChampions.setText("");
        vista.txtCompTotal.setText("");
        vista.txtCompIdJugador.setText("");
    }

    private Competicion construirCompeticionDesdeFormulario(boolean paraUpdate) {
        Integer liga = parseEnteroOpcional(vista.txtCompLiga.getText(), "Minutos liga");
        if (liga == null) return null;

        Integer copa = parseEnteroOpcional(vista.txtCompCopa.getText(), "Minutos copa");
        if (copa == null) return null;

        Integer champions = parseEnteroOpcional(vista.txtCompChampions.getText(), "Minutos champions");
        if (champions == null) return null;

        Integer idJugador = parseEntero(vista.txtCompIdJugador.getText(), "ID jugador (FK)");
        if (idJugador == null) return null;

        int total = liga + copa + champions;
        vista.txtCompTotal.setText(String.valueOf(total)); // lo mostramos también en pantalla

        Competicion c = new Competicion();
        c.setLiga(liga);
        c.setCopa(copa);
        c.setChampions(champions);
        c.setTiempoTotal(total);
        c.setIdJugador(idJugador);

        if (!paraUpdate) vista.txtCompId.setText("");

        return c;
    }

    // =========================
    //        VALIDACIONES
    // =========================

    private String textoObligatorio(String valor, String nombreCampo) {
        String t = valor == null ? "" : valor.trim();
        if (t.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " no puede estar vacío.");
            return null;
        }
        return t;
    }

    private Integer parseEntero(String valor, String nombreCampo) {
        String t = valor == null ? "" : valor.trim();
        if (t.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " es obligatorio.");
            return null;
        }
        try {
            return Integer.parseInt(t);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " debe ser numérico.");
            return null;
        }
    }

    /**
     * Entero opcional: si está vacío lo interpretamos como 0 (típico de goles/tarjetas).
     */
    private Integer parseEnteroOpcional(String valor, String nombreCampo) {
        String t = valor == null ? "" : valor.trim();
        if (t.isEmpty()) return 0;
        try {
            return Integer.parseInt(t);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El campo " + nombreCampo + " debe ser numérico.");
            return null;
        }
    }

    private void mostrarErrorBD(String accion) {
        String detalle = dao.getUltimoError();
        if (detalle == null || detalle.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se pudo " + accion + ".");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo " + accion + ".\nDetalle: " + detalle);
        }
    }
}