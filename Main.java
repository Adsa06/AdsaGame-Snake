/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 17/10/2024
 * 
 */

//Importo todas las clases de java.io
import java.io.*;

public class Main {

    //Creo la funcion princiapl con un throws IOException para capturar mensajes por consola
    public static void main(String[] args) throws IOException {
        
        /* ----- Parte declarativa ----- */

        //Declaro el BufferedReader para leer datos por consola
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name;
        Boolean salir = false;
        Byte opcion;
        /* ----- Parte principal ----- */

        //Aqui faltaria limpiar la pantalla por consola
        System.out.println("Buenos dias, ¿Como te puedo llamar?");
        name = br.readLine();
        System.out.println("\nBienvenido " + name);

        /*
        while (!salir) {
            System.out.println("¿A que deseas jugar?");
            opcion = br
            switch (salir) {
                case value:
                    
                    break;
            
                default:
                    break;
            }
        }
        */
    }
}
