package PruebasClases;

public class Player {
    //Variables privadas ya que no deberian hacederse de forma general si no de funciones
    private int health;
    private int defense;
    private int strengh;
    private String name;

    /* ----- Creacion del personaje ----- */
    public Player(String name, int vida, int defensa, int fuerza) {
        this.name = name;
        this.health = vida;
        this.defense = defensa;
        this.strengh = fuerza;
    }

    /* ----- Parte del get ----- */
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDefense() {
        return defense;
    }

    public int getStrengh() {
        return strengh;
    }

    /* ----- Parte del set ----- */
    public void setHealth(int vida) {
        this.health = vida;
    }
}