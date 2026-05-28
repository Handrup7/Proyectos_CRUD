package TEMA_6.ENTREGABLE2.Vista;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    // ====== PESTAÑA JUGADORES ======
    public JTextField txtJugadorId = new JTextField(10);
    public JTextField txtJugadorNombre = new JTextField(20);
    public JTextField txtJugadorDorsal = new JTextField(10);
    public JTextField txtJugadorEquipo = new JTextField(20);
    public JTextField txtJugadorGoles = new JTextField(10);
    public JTextField txtJugadorAsistencias = new JTextField(10);
    public JTextField txtJugadorRojas = new JTextField(10);
    public JTextField txtJugadorAmarillas = new JTextField(10);

    public JButton btnJugadorInsertar = new JButton("Insertar");
    public JButton btnJugadorActualizar = new JButton("Actualizar");
    public JButton btnJugadorEliminar = new JButton("Eliminar");
    public JButton btnJugadorVer = new JButton("Ver/Listar");
    public JButton btnJugadorLimpiar = new JButton("Limpiar");

    // ====== PESTAÑA COMPETICIONES ======
    public JTextField txtCompId = new JTextField(10);          // id_competicion (update/delete)
    public JTextField txtCompLiga = new JTextField(10);
    public JTextField txtCompCopa = new JTextField(10);
    public JTextField txtCompChampions = new JTextField(10);
    public JTextField txtCompTotal = new JTextField(10);       // solo mostrar (calculado)
    public JTextField txtCompIdJugador = new JTextField(10);   // FK

    public JButton btnCompInsertar = new JButton("Insertar");
    public JButton btnCompActualizar = new JButton("Actualizar");
    public JButton btnCompEliminar = new JButton("Eliminar");
    public JButton btnCompVer = new JButton("Ver/Listar");
    public JButton btnCompLimpiar = new JButton("Limpiar");

    // ====== SALIDA COMÚN ======
    public JTextArea areaSalida = new JTextArea(12, 70);

    public Ventana() {
        initComponentes();
    }

    private void initComponentes() {
        setTitle("Gestión Futbol (MVC + MySQL)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Aplicación de Gestión - Futbol", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // --- Tabs ---
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Jugadores", crearTabJugadores());
        tabs.addTab("Competiciones", crearTabCompeticiones());
        add(tabs, BorderLayout.CENTER);

        // --- Salida ---
        areaSalida.setEditable(false);
        areaSalida.setLineWrap(true);
        areaSalida.setWrapStyleWord(true);

        JPanel panelSalida = new JPanel(new BorderLayout());
        panelSalida.setBorder(BorderFactory.createTitledBorder("Salida / Resultados"));
        panelSalida.add(new JScrollPane(areaSalida), BorderLayout.CENTER);
        add(panelSalida, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Tooltips útiles
        txtJugadorId.setToolTipText("Solo para actualizar/eliminar");
        txtCompId.setToolTipText("Solo para actualizar/eliminar");
        txtCompTotal.setToolTipText("Se calcula automáticamente (liga+copa+champions)");
        txtCompTotal.setEditable(false);
    }

    private JPanel crearTabJugadores() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formulario
        JPanel form = new JPanel(new GridLayout(8, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Datos del jugador"));

        form.add(new JLabel("ID (solo update/delete):"));
        form.add(txtJugadorId);

        form.add(new JLabel("Nombre:"));
        form.add(txtJugadorNombre);

        form.add(new JLabel("Dorsal:"));
        form.add(txtJugadorDorsal);

        form.add(new JLabel("Equipo:"));
        form.add(txtJugadorEquipo);

        form.add(new JLabel("Goles:"));
        form.add(txtJugadorGoles);

        form.add(new JLabel("Asistencias:"));
        form.add(txtJugadorAsistencias);

        form.add(new JLabel("Tarjetas rojas:"));
        form.add(txtJugadorRojas);

        form.add(new JLabel("Tarjetas amarillas:"));
        form.add(txtJugadorAmarillas);

        panel.add(form, BorderLayout.CENTER);

        // Botones
        JPanel botones = new JPanel(new GridLayout(1, 5, 10, 10));
        botones.setBorder(BorderFactory.createTitledBorder("CRUD Jugadores"));
        botones.add(btnJugadorInsertar);
        botones.add(btnJugadorActualizar);
        botones.add(btnJugadorEliminar);
        botones.add(btnJugadorVer);
        botones.add(btnJugadorLimpiar);

        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel crearTabCompeticiones() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Formulario
        JPanel form = new JPanel(new GridLayout(6, 2, 8, 8));
        form.setBorder(BorderFactory.createTitledBorder("Datos de competiciones"));

        form.add(new JLabel("ID Competición (solo update/delete):"));
        form.add(txtCompId);

        form.add(new JLabel("Minutos Liga:"));
        form.add(txtCompLiga);

        form.add(new JLabel("Minutos Copa:"));
        form.add(txtCompCopa);

        form.add(new JLabel("Minutos Champions:"));
        form.add(txtCompChampions);

        form.add(new JLabel("Tiempo total (auto):"));
        form.add(txtCompTotal);

        form.add(new JLabel("ID Jugador (FK):"));
        form.add(txtCompIdJugador);

        panel.add(form, BorderLayout.CENTER);

        // Botones
        JPanel botones = new JPanel(new GridLayout(1, 5, 10, 10));
        botones.setBorder(BorderFactory.createTitledBorder("CRUD Competiciones"));
        botones.add(btnCompInsertar);
        botones.add(btnCompActualizar);
        botones.add(btnCompEliminar);
        botones.add(btnCompVer);
        botones.add(btnCompLimpiar);

        panel.add(botones, BorderLayout.SOUTH);
        return panel;
    }
}