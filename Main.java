/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 17/10/2024
 * 
 */

//Importo todas las clases de java.io
import java.io.*;
import java.nio.channels.Pipe.SourceChannel;

public class Main {

    //Creo la funcion princiapl con un throws IOException para capturar mensajes por consola
    public static void main(String[] args) throws IOException {
        
        /* ----- Parte declarativa ----- */

        //Declaro el BufferedReader para leer datos por consola
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name = "";
        Boolean quit = false;
        int option = 0;

        String[] pedirNombre = {"Buenos dias, ¿Como te puedo llamar?"};
        String[] pedirJugar = {"¿A que deseas jugar " + name + "?", "1 = Jugar", "2 = Conf", "3 = Salir"};

        final int RESOLUCION_ALTURA = 30;
        final int RESOLUCION_ANCHO = 120;
        /* ----- Parte principal ----- */

        //Aqui faltaria limpiar la pantalla por consola
        
        sueloTecho(RESOLUCION_ANCHO);
        paredes(RESOLUCION_ANCHO, RESOLUCION_ALTURA, 14, -1, pedirNombre);
        //System.out.println("Buenos dias, ¿Como te puedo llamar?");
        sueloTecho(RESOLUCION_ANCHO);
        name = br.readLine();

        while (!quit) {
            sueloTecho(120);
            paredes(RESOLUCION_ANCHO, RESOLUCION_ALTURA, 14, -1, pedirJugar);

            //System.out.print("¿A que deseas jugar " + name + "?");

            sueloTecho(120);
            option = Integer.parseInt(br.readLine());
            switch (option) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    quit = true;     
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        }
        System.out.println("Hasta otra");
    }

    public static void sueloTecho(int longitud) {
        for(int i = longitud; i > 0; i--) {
            System.out.print("-");
        }
        System.out.printf("%n");
    }

    public static void paredes(int anchura, int longitud, int fila, int espaciado, String[] frases) {

        //Hay que tener en cuenta el espaciado y el numero de frases

        //Bucle
        for(int i = 0; i != (longitud-3); i++) {

            System.out.printf("=");

            //Si ha llegado a la fila entra dentro
            if(i == fila) {

                //Depende si la fila tiene que estar centrada 
                if (espaciado <= -1){

                    if(frases.length > 1) {
                        for(int k = 0; k < frases.length; k++) {

                            //Imprime los primeros espacios
                            for(int j = 0; j != (((anchura-2) - frases[k].length()) / 2); j++) {
                                System.out.print(" ");
                            }
        
                            System.out.print(frases[k]);
        
                            //Terminacion de la frase dependiendo si es impar o par
                            if (frases[k].length() % 2 == 0) {
                                for(int j = 0; j != ((anchura-2) - frases[k].length() - espaciado); j++) {
                                    System.out.print(" ");
                                }
                            } else { //Si la frase es impar se le sumara 1 para que no se joda
                                for(int j = 0; j != (((anchura-2) - frases[k].length()) / 2 + 1); j++) {
                                    System.out.print(" ");
                                }
                            }
                        }
                    } else {
                        for(int j = 0; j != (((anchura-2) - frases[0].length()) / 2); j++) {
                            System.out.print(" ");
                        }
    
                        System.out.print(frases[0]);
    
                        //Terminacion de la frase dependiendo si es impar o par
                        if (frases[0].length() % 2 == 0) {
                            for(int j = 0; j != ((anchura-2) - frases[0].length() - espaciado); j++) {
                                System.out.print(" ");
                            }
                        } else { //Si la frase es impar se le sumara 1 para que no se joda
                            for(int j = 0; j != (((anchura-2) - frases[0].length()) / 2 + 1); j++) {
                                System.out.print(" ");
                            }
                        }
                    }

                } else { //Si no tiene que estar centrada
                    //Imprimira el espaciado y luego la frase
                    for(int j = 0; j != (espaciado); j++) {
                        System.out.print(" ");
                    }
                    
                    System.out.print(frases[0]);

                    //Imprime el resto de la linea
                    for(int j = 0; j != ((anchura-2) - frases[0].length() - espaciado); j++) {
                        System.out.print(" ");
                    }
                }
                
            } else { //Imprime espacios

                for(int j = 0; j != (anchura-2); j++) {
                    System.out.print(" ");
                }

            }
            System.out.print("=");
            }
            

        
    }
}