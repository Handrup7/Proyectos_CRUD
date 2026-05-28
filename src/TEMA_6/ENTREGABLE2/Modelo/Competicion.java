package TEMA_6.ENTREGABLE2.Modelo;

public class Competicion {
    private int idCompeticion;
    private int liga;
    private int copa;
    private int champions;
    private int tiempoTotal;
    private int idJugador; // FK

    public Competicion() {
    }

    public int getIdCompeticion() {
        return idCompeticion;
    }

    public void setIdCompeticion(int idCompeticion) {
        this.idCompeticion = idCompeticion;
    }

    public int getLiga() {
        return liga;
    }

    public void setLiga(int liga) {
        this.liga = liga;
    }

    public int getCopa() {
        return copa;
    }

    public void setCopa(int copa) {
        this.copa = copa;
    }

    public int getChampions() {
        return champions;
    }

    public void setChampions(int champions) {
        this.champions = champions;
    }

    public int getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(int tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
}