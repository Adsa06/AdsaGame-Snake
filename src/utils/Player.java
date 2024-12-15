/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 10/12/2024
 * Este archivo es la clase del jugador, en esta se almacena toda la informacion del jugador
 * 
 */
package utils;

public class Player {
    //Variables privadas ya que no deberian hacederse de forma general si no de funciones
    private String name;
    private int score;
    private int[] configuration = new int[4];
    /*
     * Un sistema de logros, con diccionarios
     * -Poner un numero en concreto en el contador
     * -Codigo kunami en movs
     * 
     * 
     */
    /* ----- Metodos constructores ----- */
    public Player(){
        //Array de configuracuin
        /*
         * El primer dato es el numero de filas que hay en el tablero
         * El segundo dato es el numero de columnas que hay en el tablero
         * 
         * El tercero es el tiempo de descanso que hay entre cada actualizacion del tablero
         * 
         * El cuarto dato es si admite o no colores la consola que estas utilizando (un 0 es que admite, un 1 no admite)
         * 
         */
        configuration = new int[] {10, 20, 375, 0};
    }
    
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

    /* ----- Metodos de comportamiento -----  */
}