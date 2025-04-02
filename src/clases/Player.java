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

import utils.ColoresConsola;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Player implements Serializable {
    /** Constante para la Serializacion */
    private static final long serialVersionUID = 1L;
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
        String[] infoPlayer = {
            ("Perfil del jugador: \nNombre: " + name + "\nPuntuacion maxima: " + String.format("%.3f",maxScore)), 
            (ColoresConsola.ANSI_BLUE() + "Perfil del jugador: \nNombre: " + name + "\nPuntuacion maxima: " + String.format("%.3f",maxScore) + ColoresConsola.ANSI_RESET())
        };
        System.out.println(infoPlayer[configuration[3]]);
        if (partidas.size() > 0) {
            System.out.println("Partidas jugadas: " + partidas.size());
            for (Partida partida : partidas) {
                System.out.println(partida.mostrarInfo(configuration[3] == 1));
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

    /**
     * Guarda un objeto Player en un archivo especificado.
     * 
     * @param jugadores   El objeto Player que se desea guardar.
     * @param rutaArchivo La ruta del archivo donde se guardará el objeto Player.
     */

    public static void guardarJugador(Player player) {
        File file = new File("./Players");
        if (!file.exists()) {
            file.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./Players/" + player.getName() + ".dat"))) {
            oos.writeObject(player);
            System.out.println("Jugador guardado correctamente");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga un objeto Player desde un archivo especificado.
     * 
     * @param rutaArchivo La ruta del archivo desde donde se cargará el objeto
     *                    Player.
     * @return El objeto Player cargado desde el archivo, o null si ocurre un error.
     */

    public static Player cargarJugador(String nombreJugador) {
        File file = new File("./Players/" + nombreJugador + ".dat");
        Player jugador = null;
        if(file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                jugador = (Player) ois.readObject();
                System.out.println("Jugador cargado correctamente");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El jugador no existe");
        }

        return jugador;
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