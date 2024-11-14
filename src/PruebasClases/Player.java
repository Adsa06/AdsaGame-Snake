package PruebasClases;

public class Player {
    //Variables privadas ya que no deberian hacederse de forma general si no de funciones
    private String name;
    private int score;

    /* ----- Creacion del personaje ----- */
    public Player(String nombre, int puntuacion) {
        this.name = nombre;
        this.score = puntuacion;
    }

    /* ----- Parte del get ----- */
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    /* ----- Parte del set ----- */
    public void setName(String nombre) {
        this.name = nombre;
    }

    public void setScore(int puntuacion) {
        this.score = puntuacion;
    }
}