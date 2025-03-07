package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
}
