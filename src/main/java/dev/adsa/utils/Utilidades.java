/**
 * @author: Aitor de Santos Amoros
 * Fecha: 1/4/2024
 * Descripcion: Este archivo es el que contiene las funciones de utilidad
 */

package dev.adsa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import dev.adsa.bbdd.GestionDB;
import dev.adsa.clases.Player;

/**
 * Clase que contiene las funciones de utilidad
 */
public class Utilidades {
    /**
     * Solicita al usuario que introduzca un número entero dentro de un rango
     * especificado.
     *
     * @param frase Mensaje que se muestra al usuario para solicitar la entrada.
     * @param min   Valor mínimo aceptable para la entrada del usuario.
     * @param max   Valor máximo aceptable para la entrada del usuario.
     * @return El número entero introducido por el usuario que se encuentra dentro
     *         del rango especificado.
     *         Si la entrada es inválida o fuera de rango, se solicita nuevamente.
     */

    public static int pedirNumeroEntero(String frase, int min, int max) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = -1;
        do {
            System.out.println(frase + " " + min + " y " + max);
            try {
                opcion = Integer.parseInt(br.readLine());
                if (opcion > max || opcion < min)
                    System.out.println("Numero fuera de rango");
            } catch (Exception e) {
                System.out.println("Entrada no valida, Introduce un numero entero");
                opcion = -1;
            }
        } while (opcion > max || opcion < min);
        return opcion;
    }

    /**
     * Pide al usuario que introduzca una cadena de texto
     * 
     * @return La cadena de texto introducida por el usuario
     */
    public static String pedirString() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String opcion = "";
        do {
            try {
                opcion = br.readLine();
                if (opcion.equals(""))
                    System.out.println("Introduzca algo");
            } catch (Exception e) {
                System.out.println("Entrada no valida");
                opcion = "";
            }
        } while (opcion.equals(""));
        return opcion;
    }

    /**
     * Solicita al usuario que pulse la tecla Enter. Se utiliza para que el
     * usuario pueda ver el resultado de una operación o para que el programa
     * espere a que el usuario esté listo para continuar.
     */
    public static void presionarEnter() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try { // Utilizao un try/catch para evitar excepciones de lectura
            br.readLine();
        } catch (Exception e) {
        }
    }

    /**
     * Valida si una cadena coincide con un patrón especificado.
     * 
     * @param patron El patrón de expresión regular a utilizar para la validación.
     * @param cadena La cadena de texto que se va a verificar contra el patrón.
     * @return true si la cadena coincide con el patrón, false en caso contrario.
     */
    public static boolean validarPatron(String patron, String cadena) {
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(cadena);
        return matcher.find();
    }

    /**
     * Inicia al jugador con una cuenta guardada o sin ella.
     * Pide al usuario si tiene una cuenta guardada, si es así la carga
     * si no es así, pide el nombre del jugador y lo guarda.
     * 
     * @return El jugador que se ha iniciado o creado.
     */
    public static Player iniciarJugador() {
        Player player = null;
        boolean playerExists = false;
        do {
            String optionCuentaGuardada = "";
            System.out.println("¿Tienes una cuenta guardada? (S/N)");
            optionCuentaGuardada = Utilidades.pedirString().toUpperCase();
            // cargar una cuenta existente
            if (optionCuentaGuardada.equals("S")) {
                System.out.println("Escriba el nombre de la cuenta");
                String nombre = Utilidades.pedirString();
                // Voy a suponer que si existe localmente existe en la base de datos
                File file = new File("./Players", nombre + ".dat");
                if (file.exists())
                    player = Player.cargarJugador(nombre);
                else if (GestionDB.detectarJugadorExistente(nombre))
                    player = GestionDB.leerDatos(nombre);
                else
                    System.out.println("El jugador no existe");
                playerExists = player != null;
            } else if (optionCuentaGuardada.equals("N")) {
                // Comprueba si existe o no existe la carpeta para saber si de verdad es nuevo
                System.out.println("Buenos dias, ¿Cual es tu nombre?");
                String nombre = "?";
                while (nombre.length() > 40 || Utilidades.validarPatron(".*[\\\\/:*?\"<>|\\s].*", nombre)) {
                    System.out.println(
                            "Introduzca un nombre menor a 40 caracteres y que no contenga caracteres especiales");
                    nombre = Utilidades.pedirString();
                }

                File file = new File("./Players", nombre + ".dat");
                if (!file.exists() && !GestionDB.detectarJugadorExistente(nombre)) {
                    // Creacion de una cuenta nueva
                    player = new Player();
                    player.setName(nombre);

                    playerExists = true;
                    GestionDB.guardarJugadorDB(player);
                } else {
                    System.out.println("¡Jugador existente!");
                }
            }
        } while (!playerExists);
        Player.guardarJugador(player);
        return player;
    }

    /**
     * Cierra la sesión del jugador actual, guarda su progreso y permite iniciar
     * sesión
     * con otro jugador o el mismo jugador nuevamente.
     *
     * @param player El jugador cuya sesión se desea cerrar.
     * @return El nuevo jugador que inicia sesión después de cerrar la sesión
     *         actual.
     */
    public static Player cerrarSesion(Player player) {
        Player.guardarJugador(player);
        System.out.println("Sesion cerrada");
        player = null;
        return iniciarJugador();
    }

    /**
     * Elimina el perfil del jugador actual tanto localmente como en la base de
     * datos.
     * Si el perfil se elimina correctamente, permite iniciar sesión con otro
     * jugador
     * o crear un nuevo perfil.
     *
     * @param player El jugador cuyo perfil se desea eliminar.
     * @return El nuevo jugador que inicia sesión después de eliminar el perfil
     *         actual.
     */
    public static Player eliminarPerfil(Player player) {
        File file = new File("./Players", player.getName() + ".dat");
        if (file.exists())
            if (file.delete())
                System.out.println("Cuenta eliminada localmente");
            else
                System.out.println("Error al eliminar la cuenta localmente");
        else
            System.out.println("Cuenta no encontrada");
        GestionDB.eliminarPerfil(player);
        player = null;
        return iniciarJugador();
    }

    /**
     * Formatea la diferencia entre dos fechas en un formato de tiempo legible
     * (HH:mm:ss).
     *
     * @param fechaInicio La fecha y hora de inicio.
     * @param fechaFinal  La fecha y hora de finalización.
     * @return Una cadena que representa el tiempo transcurrido entre las dos fechas
     *         en el formato "HH:mm:ss".
     */
    public static String formatearFecha(LocalDateTime fechaInicio, LocalDateTime fechaFinal) {

        // Calcular el tiempo transcurrido entre fechaInicio y fechaFinal
        Duration duracion = Duration.between(fechaInicio, fechaFinal);
        long horas = duracion.toHours();
        long minutos = (duracion.toMinutes() % 60);
        long segundos = (duracion.getSeconds() % 60);
        String tiempoTranscurrido = String.format("%02d:%02d:%02d", horas, minutos, segundos);

        return tiempoTranscurrido;
    }
}
