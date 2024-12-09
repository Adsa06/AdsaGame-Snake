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
            try { //Este try impide que el usuario escriba una letra y pete el programa, como opcion es = 0, se ira al default en el switch
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Porfavor, escriba una opcion valida");
            }
            switch (option) {
                case 1:
                    dimensiones = cambioDimensiones();
                    break;
                case 2:
                    tiempoMilisegundos = cambioTiempo();
                    break;
                case 3:
                    admiteColores = cambioColores();
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

    public static int[] cambioDimensiones(){
        /* ----- Parte declarativa ----- */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] dimensiones = new int[2];

        /* ----- Parte principal ----- */

        /* ----- Envio de datos ----- */
        return dimensiones;
    }

    public static int cambioTiempo(){
        /* ----- Parte declarativa ----- */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nivelDificultad;

        /* ----- Parte principal ----- */
        nivelDificultad = 375;


        /* ----- Envio de datos ----- */
        return nivelDificultad;
    }

    public static int cambioColores(){
        /* ----- Parte declarativa ----- */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int admiteColores;

        /* ----- Parte principal ----- */
        admiteColores = 0;

        /* ----- Envio de datos ----- */
        return admiteColores;
    }
}
