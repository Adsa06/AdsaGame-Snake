package dev.adsa;


/**
 * @author: Aitor de Santos Amoros
 * Fecha: 17/10/2024
 * Este archivo es el controlador, el cual se comunica con el archivo txt para escribir los comandos
 * 
 */
import java.io.*;

import dev.adsa.utils.ColoresConsola;

public class ControladorPrincipal {
    /**
     * Metodo principal, este es el que se encarga de comunicarse con el usuario,
     * y de recibir las instrucciones para el juego. Tambien es el que se encarga de
     * escribir las instrucciones en un archivo de texto.
     * 
     * @param args Los argumentos del main, que en este caso no se utilizan
     * @throws IOException La excepcion de lectura
     */
    public static void main(String[] args) throws IOException /* Capto las excepciones de la entrada de datos */ {

        /* ----- Parte declarativa ----- */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Creo una variable BufferedWriter, y hago que sea un objeto de la clase
        // FileWriter para que pueda escribir archivos por consola
        // Al poner false el archivo se sobreescribira por completo
        BufferedWriter fr = new BufferedWriter(new FileWriter("./content.txt", false));

        String instrucion;
        boolean continuar = true;

        String quiereColores;
        boolean colores = false;
        /* ----- Parte principal ----- */
        System.out.println("Bienvenido, ¿Quieres habilitar los colores? (S o N)");
        do {
            quiereColores = br.readLine();
            if (quiereColores.equalsIgnoreCase("S")) {
                colores = true;
            } else if (quiereColores.equalsIgnoreCase("N")) {
                colores = false;
            } else {
                System.out.println(
                        "Porfavor, escriba una opcion valida (\"S\" para poner colores, \"N\" para no poner colores)");
            }
        } while (!quiereColores.equalsIgnoreCase("S") && !quiereColores.equalsIgnoreCase("N"));

        System.out.println("Para salir escribe \"Salir\", presiona enter para comenzar");
        br.readLine();
        if (colores) {
            do {
                // Pergunto y leo una linea por consola
                System.out.printf("Escribe \"" + ColoresConsola.ANSI_CYAN() + "W" + ColoresConsola.ANSI_RESET()
                        + "\",\"" + ColoresConsola.ANSI_CYAN() + "A" + ColoresConsola.ANSI_RESET() + "\",\""
                        + ColoresConsola.ANSI_CYAN() + "S" + ColoresConsola.ANSI_RESET() + "\",\""
                        + ColoresConsola.ANSI_CYAN() + "D" + ColoresConsola.ANSI_RESET() + "\": ");
                instrucion = br.readLine();
                // write escribe en el archivo
                if(instrucion.length() == 1 && "wasd".contains(instrucion.toLowerCase()))
                    fr.write(instrucion + "\n");
                // flush fuerza a escribir los fatos en el archivo
                fr.flush();
                // Ahora tiene una forma de salir
                continuar = instrucion.equalsIgnoreCase("Salir") ? false : true;
            } while (continuar);
        } else {
            do {
                // Pergunto y leo una linea por consola
                System.out.printf("Escribe \"" + "W" + "\",\"" + "A" + "\",\"" + "S" + "\",\"" + "D" + "\": ");
                instrucion = br.readLine();
                // write escribe en el archivo
                if(instrucion.length() == 1 && "wasd".contains(instrucion.toLowerCase()))
                    fr.write(instrucion + "\n");
                // flush fuerza a escribir los fatos en el archivo
                fr.flush();
                // Ahora tiene una forma de salir
                continuar = instrucion.equalsIgnoreCase("Salir") ? false : true;
            } while (continuar);
        }
        br.close();
        fr.close();

    }
}
