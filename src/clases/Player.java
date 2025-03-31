/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 10/12/2024
 * Este archivo es la clase del jugador, en esta se almacena toda la informacion del jugador
 * 
 */
package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Player {
    // Variables privadas ya que no deberian hacederse de forma general si no de
    // funciones
    /** Nombre del jugador */
    private String name;
    /**
     * Array de configuración
     * Los 2 primeros datos indican las dimensiones (Filas y Columnas) del tablero
     * El tercer dato es el tiempo de descanso que hay entre cada actualizacion del
     * tablero
     * El cuarto dato es si admite o no colores la consola que estas utilizando (un
     * 0 es que admite, un 1 no admite)
     */
    private int[] configuration = new int[4];
    /** Score maximo del jugador */
    private double maxScore = 0;
    /** La lista de todas la partidas */
    private List<Partida> partidas = new ArrayList<Partida>();

    /* ----- Metodos constructores ----- */
    public Player() {
        maxScore = 0;
        configuration = new int[] { 10, 20, 375, 0 };
    }

    public Player(String nombre, int[] configPersonalizada) {
        this.name = nombre;
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
     * Obtiene la configuración del jugador.
     * 
     * @return Un array de enteros que representa la configuración actual del
     *         jugador.
     */

    public int[] getCongiguration() {
        return configuration;
    }

    /**
     * Obtiene el puntaje del jugador.
     * 
     * @return El puntaje del jugador.
     */
    public double getMaxScore() {
        return maxScore;
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

    /**
     * Establece el puntaje del jugador.
     * 
     * @param puntuacion El nuevo puntaje del jugador.
     */
    public void setMaxScore(double puntuacion) {
        this.maxScore = puntuacion;
    }

    /* ----- Metodos de comportamiento ----- */

    /**
     * Muestra el perfil del jugador, incluyendo el nombre y la puntuacion.
     */
    public void mostrarPerfil() {
        System.out.println("Perfil del jugador: \nNombre: " + name + "\nPuntuacion maxima: " + maxScore);
        if(partidas.size() > 0) {
            System.out.println("Partidas jugadas: " + partidas.size());
            for (Partida partida : partidas) {
                System.out.println(partida.toString());
            }
        } else {
            System.out.println("No se han jugado partidas");
        }
    }

    /**
     * Añade una partida al historial de partidas del jugador.
     * 
     * @param partida La partida a agregar.
     */
    public void addPartida(Partida partida) {
        this.partidas.add(partida);
    }

        // Método para guardar la lista de jugadores en un archivo
        public static void guardarJugadores(List<Player> jugadores, String rutaArchivo) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
                oos.writeObject(jugadores);
                System.out.println("Jugadores guardados correctamente en " + rutaArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // Método para cargar la lista de jugadores desde un archivo
        public static Player cargarJugadores(String rutaArchivo) {
            Player jugadores = null;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
                jugadores = (Player) ois.readObject();
                System.out.println("Jugadores cargados correctamente desde " + rutaArchivo);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return jugadores;
        }
    

    /**
     * Devuelve una representación en forma de cadena de texto del objeto Player,
     * incluyendo el nombre, la configuración y las partidas.
     *
     * @return Una cadena de texto que representa al jugador y sus atributos.
     */

    @Override
    public String toString() {
        return "Player [name=" + name + ", configuration=" + Arrays.toString(configuration) + ", maxScore=" + maxScore
                + ", partidas=" + partidas + "]";
    }

}