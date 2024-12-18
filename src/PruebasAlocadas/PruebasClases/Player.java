/** //Tiene que haber doble "**"
 * @autor Adsa
 * @version 1.0
 * 
 */


package PruebasAlocadas.PruebasClases;

import java.util.Arrays;

public class Player { //Las clases pueden ser abstract (La clase mas general que hay, no va haber ningun objeto de esta clase) y final (La clase mas especifica que hay)
    //Variables privadas ya que no deberian hacederse de forma general si no de funciones
    private String name;
    private int score;
    private int[] configuration = new int[4];

    private static int pruebaStaticAtrivuto = 0;
    //private final String nombreAdsa = "Adsa"; //Constante
    /*
     * Un sistema de logros, con diccionarios
     * -Poner un numero en concreto en el contador
     * -Codigo kunami en movs
     * 
     * 
     */

     /*
      * 
      * static: solo tiene una copia de eso para todo
      *     - SI se pone en un atrivuto/variable esa variable si se cambia en un objeto se cambia en todos
      *     - Si se pone en un metodo siempre se puede utilizar sin necesidad de tener un objeto creado
      *
      *
      */
    /* ----- Metodos constructores ----- */
    public Player(String nombre, int puntuacion, int[] configPersonalizada) {
        this.name = nombre;
        this.score = puntuacion;
        this.configuration = configPersonalizada;
    }

    /* ----- Parte del getter ----- */
    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int[] getCongiguration() {
        return configuration;
    }

    public int getPruebaStaticAtrivuto() {
        return pruebaStaticAtrivuto;
    }

    /* ----- Parte del setter ----- */
    public void setName(String nombre) {
        this.name = nombre;
    }

    public void setScore(int puntuacion) {
        this.score = puntuacion;
    }

    public void setCongiguration(int[] configPersonalizada) {
        this.configuration = configPersonalizada;
    }

    public void setPruebaStaticAtrivuto(int nuevaVariable) {
        Player.pruebaStaticAtrivuto = nuevaVariable;
    }
    /* ----- Metodos de comportamiento -----  */

    /* ----- Metodos Static ----- */ //Tambien puedes haber metodos abstract (Pero al ser tan general no hara nada)
    public static boolean comparar2Numeros (int num1, int num2) { //Se puede llamar sin tener que tener una clase creada
        return num1 > num2;
    }

    @Override
    public String toString() {
        return "Player [Nombre: " + name + ", Puntuacion: " + score + ", Configuracion: " + Arrays.toString(configuration) + "]";
    }

}