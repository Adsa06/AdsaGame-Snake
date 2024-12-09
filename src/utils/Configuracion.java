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
    public static int[] main() throws IOException{
        /* ----- Parte declarativa ----- */
        int[] dimensiones = {10, 20};
        int tiempoMilisegundos = 375;
        int admiteColores = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        /* ----- Parte principal ----- */
        br.readLine();
        
        /* ----- Envio de datos ----- */
        int[] array = {dimensiones[0], dimensiones[1], tiempoMilisegundos, admiteColores};
        return array;
    }
}
