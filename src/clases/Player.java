/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 10/12/2024
 * Este archivo es la clase del jugador, en esta se almacena toda la informacion del jugador
 * 
 */
package clases;

import java.util.Arrays;

public class Player {
    // Variables privadas ya que no deberian hacederse de forma general si no de
    // funciones
    private String name;
    private double score;
    private int[] configuration = new int[4];

    /* ----- Metodos constructores ----- */
    public Player() {
        score = 0;
        configuration = new int[] { 10, 20, 375, 0 };
    }

    public Player(String nombre, int puntuacion, int[] configPersonalizada) {
        this.name = nombre;
        this.score = puntuacion;
        this.configuration = configPersonalizada;
    }

    /* ----- Parte del getter ----- */

    /**
     * Obtiene el nombre del jugador.
     * 
     * @return El nombre del jugador.
     */

    public String getName() {
        return name;
    }

    /**
     * Obtiene el puntaje del jugador.
     * 
     * @return El puntaje del jugador.
     */
    public double getScore() {
        return score;
    }

    /**
     * Obtiene la configuración del jugador.
     * 
     * @return Un array de enteros que representa la configuración actual del
     *         jugador.
     */

    public int[] getCongiguration() {
        return configuration;
    }

    /* ----- Parte del setter ----- */

    /**
     * Establece el nombre del jugador.
     * 
     * @param nombre El nuevo nombre del jugador.
     */
    public void setName(String nombre) {
        this.name = nombre;
    }

    /**
     * Establece el puntaje del jugador.
     * 
     * @param puntuacion El nuevo puntaje del jugador.
     */
    public void setScore(double puntuacion) {
        this.score = puntuacion;
    }

    /**
     * Establece la configuración personalizada del jugador.
     * 
     * Es un array de enteros que tiene la siguiente estructura:
     * [ancho del tablero, alto del tablero, tiempo de espera en milisegundos,
     * admite colores]
     * 
     * @param configPersonalizada La configuración personalizada del jugador.
     */
    public void setCongiguration(int[] configPersonalizada) {
        this.configuration = configPersonalizada;
    }

    /* ----- Metodos de comportamiento ----- */

    /**
     * Muestra el perfil del jugador, incluyendo el nombre y la puntuacion.
     */
    public void mostrarPerfil() {
        System.out.println("Perfil del jugador: \nNombre: " + name + "\nPuntuacion: " + score);
    }

    /**
     * Returns a string representation of the Player object, including the
     * player's name, score, and configuration settings.
     * 
     * @return A string containing the player's name, score, and configuration.
     */

    @Override
    public String toString() {
        return "Player [Nombre: " + name + ", Puntuacion: " + score + ", Configuracion: "
                + Arrays.toString(configuration) + "]";
    }
}