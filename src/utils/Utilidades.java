package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import clases.Player;

import java.util.regex.Matcher;

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
     * @param player El jugador que se va a iniciar
     */
    public static Player iniciarJugador() {
        Player player = null;
        boolean playerExists = false;
        do {
            String optionCuentaGuardada = "";
            System.out.println("¿Tienes una cuenta guardada? (S/N)");
            optionCuentaGuardada = Utilidades.pedirString().toUpperCase();
            // cargar una cuenta existente
            if(optionCuentaGuardada.equals("S")) {
                System.out.println("Escriba el nombre de la cuenta");
                String nombre = Utilidades.pedirString();
                player = Player.cargarJugador(nombre);
                playerExists = player != null;
            } else {
                player = new Player();
                // Creacion de una cuenta nueva
                do {
                    System.out.println("Buenos dias, ¿Cual es tu nombre?");
                    player.setName(Utilidades.pedirString());
                    // Este es un if que detecta si es mayor a cuarenta la longitud de la frase si
                    // es asi ejecuta el sigueinte linea de codigo
                    // El pattern detecta \ / y espacios
                    if (player.getName().length() > 40 || Utilidades.validarPatron(".*[\\\\/:*?\"<>|\\s].*", player.getName()))
                        System.out.println("Introduzca un nombre menor a 40 caracteres y que no contenga caracteres especiales");
                } while (player.getName().length() > 40 || Utilidades.validarPatron(".*[\\\\/:*?\"<>|\\s].*", player.getName()));
                playerExists = true;
            }
        } while (!playerExists);
        return player;
    }

    public static Player cerrarSesion(Player player) {
        Player.guardarJugador(player);
        System.out.println("Sesion cerrada");
        player = null;
        return iniciarJugador();
    }
}
