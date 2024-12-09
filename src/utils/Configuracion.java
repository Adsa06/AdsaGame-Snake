/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 09/12/2024
 * Este archivo es el apartado de configuracion del juego, este devuelve un array con la configuracion establecida
 * 
 */
package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Configuracion {
    public static int[] main(int[] valoresAnteriores) throws IOException{
        /* ----- Parte declarativa ----- */
        int[] dimensiones = {valoresAnteriores[0], valoresAnteriores[1]};
        int tiempoMilisegundos = valoresAnteriores[2];
        int admiteColores = valoresAnteriores[3];

        boolean continuar = true;
        int option = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        /* ----- Parte principal ----- */
        do {
            System.out.println("Apartado de configuracion, escriba la opcion que desee:");
            System.out.println("1. Cambiar dimensiones, los valores actuales son: " + dimensiones[0] + " filas y " + dimensiones[1] + " columnas");
            System.out.println("2. Cambiar velocidad del juego, el valor actual es: " + tiempoMilisegundos + " milisegundos");
            System.out.println("3. Habilitar colores, el valor actual es: " + (admiteColores == 1 ? "Si" : "No") + " admite colores");
            System.out.println("4. Salir");
            try { //Este try impide que el usuario escriba una letra y pete el programa, como opcion es = 0, se ira al default en el switch
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Porfavor, escriba una opcion valida");
            }
            switch (option) {
                case 1:
                    dimensiones = cambioDimensiones(dimensiones);
                    break;
                case 2:
                    tiempoMilisegundos = cambioTiempo();
                    break;
                case 3:
                    admiteColores = cambioColores();
                    break;
                case 4:
                    continuar = false;     
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (continuar);
        /* ----- Envio de datos ----- */
        int[] array = {dimensiones[0], dimensiones[1], tiempoMilisegundos, admiteColores};
        return array;
    }

    public static int[] cambioDimensiones(int[] valoresAnteriores) throws IOException{
        /* ----- Parte declarativa ----- */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nuevasDimensiones = valoresAnteriores;

        String opcion;
        boolean continuar = true;
        /* ----- Parte principal ----- */
        System.out.println("Los valores actuales son: " + valoresAnteriores[0] + " filas y " + valoresAnteriores[1] + " columnas");
        System.out.println("¿Quieres cambiarlos? (S o N)");

        do {
            opcion = br.readLine();
            continuar = opcion.equalsIgnoreCase("S") ? true : false;
        } while (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N"));

        while (continuar) {
            System.out.println("Escribe el nuevo numero de filas: ");
            do {
                System.out.println("Escriba un numero mayor o igual a 3");
                try {
                    nuevasDimensiones[0] = Integer.parseInt(br.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Porfavor, escriba un numero");
                }
            } while (nuevasDimensiones[0] < 3);
    
            System.out.println("Escribe el nuevo numero de columnas: ");
            do {
                System.out.println("Escriba un numero mayor o igual a 3");
                try {
                    nuevasDimensiones[1] = Integer.parseInt(br.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Porfavor, escriba un numero");
                }
            } while (nuevasDimensiones[1] < 3);

            continuar = false;
        }
        /* ----- Envio de datos ----- */
        return nuevasDimensiones;
    }

    public static int cambioTiempo() throws IOException{
        /* ----- Parte declarativa ----- */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nivelDificultad;

        /* ----- Parte principal ----- */
        nivelDificultad = 375;

        /* ----- Envio de datos ----- */
        return nivelDificultad;
    }

    public static int cambioColores() throws IOException{
        /* ----- Parte declarativa ----- */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int admiteColores;

        /* ----- Parte principal ----- */
        admiteColores = 0;
        /* ----- Envio de datos ----- */
        return admiteColores;
    }
}
