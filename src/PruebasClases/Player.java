package PruebasClases;

public class Player {
    //Variables privadas ya que no deberian hacederse de forma general si no de funciones
    private String name;
    private int score;
    private int[] configuration = new int[4];
    
    /* ----- Creacion del personaje ----- */
    public Player(String nombre, int puntuacion, int[] configPersonalizada) {
        this.name = nombre;
        this.score = puntuacion;
        this.configuration = configPersonalizada;
    }

    /* ----- Parte del get ----- */
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int[] getCongiguration() {
        return configuration;
    }

    /* ----- Parte del set ----- */
    public void setName(String nombre) {
        this.name = nombre;
    }

    public void setScore(int puntuacion) {
        this.score = puntuacion;
    }

    public void setCongiguration(int[] configPersonalizada) {
        this.configuration = configPersonalizada;
    }
}