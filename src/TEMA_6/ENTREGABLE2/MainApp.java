package TEMA_6.ENTREGABLE2;

import TEMA_6.ENTREGABLE2.Controlador.Controlador;
import TEMA_6.ENTREGABLE2.Modelo.Consultas;
import TEMA_6.ENTREGABLE2.Modelo.InicializadorBD;
import TEMA_6.ENTREGABLE2.Vista.Ventana;

public class MainApp {
    public static void main(String[] args) {

        // 1) Crear BD y tablas si no existen
        String user = "root";
        String password = "Colombia10";

        InicializadorBD.crearBaseDatosSiNoExiste("jdbc:mysql://localhost:3306/", user, password);
        InicializadorBD.crearTablasSiNoExisten("jdbc:mysql://localhost:3306/futbol", user, password);

        // 2) MVC
        Ventana vista = new Ventana();
        Consultas dao = new Consultas();
        new Controlador(vista, dao);
    }
}