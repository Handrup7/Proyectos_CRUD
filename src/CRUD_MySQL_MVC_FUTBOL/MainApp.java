package CRUD_MySQL_MVC_FUTBOL;

import CRUD_MySQL_MVC_FUTBOL.Controlador.Controlador;
import CRUD_MySQL_MVC_FUTBOL.Modelo.Consultas;
import CRUD_MySQL_MVC_FUTBOL.Modelo.InicializadorBD;
import CRUD_MySQL_MVC_FUTBOL.Vista.Ventana;

public class MainApp {
    public static void main(String[] args) {

        // 1) Crear BD y tablas si no existen
        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (user == null || password == null) {
            throw new IllegalStateException("Faltan variables de entorno DB_USER y DB_PASSWORD");
        }

        InicializadorBD.crearBaseDatosSiNoExiste("jdbc:mysql://localhost:3306/", user, password);
        InicializadorBD.crearTablasSiNoExisten("jdbc:mysql://localhost:3306/futbol", user, password);

        // 2) MVC
        Ventana vista = new Ventana();
        Consultas dao = new Consultas();
        new Controlador(vista, dao);
    }
}
