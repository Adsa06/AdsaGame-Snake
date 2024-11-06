/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 17/10/2024
 * 
 */

//Importo las clases de java.io i la carpeta de src
import java.io.*;
import src.ColoresConsola;
import src.Snake;

public class Main {

    //Creo la funcion princiapl con un throws IOException para capturar mensajes por consola
    public static void main(String[] args) throws IOException, InterruptedException {
        
        /* ----- Parte declarativa ----- */

        //Declaro el BufferedReader para leer datos por consola
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name = "";
        Boolean quit = false;
        int option = 0;

        String[] pedirNombre = {"Buenos dias, ¿Como te puedo llamar?"};
        String[] pedirJugar = {
            "¿A que deseas jugar " + name + "?",
            "",
            "1 = Jugar",
            "2 = Confi",
            "3 = Salir"
        };

        final int RESOLUCION_ALTURA = 30;
        final int RESOLUCION_ANCHO = 120;

        final int[] DIMENSIONES_SNAKE = {10, 20};
        /* ----- Parte principal ----- */

        //Aqui faltaria limpiar la pantalla por consola
        
        sueloTecho(RESOLUCION_ANCHO);
        paredesMultiplesFrases(RESOLUCION_ANCHO, RESOLUCION_ALTURA, 14, pedirNombre);
        //System.out.println("Buenos dias, ¿Como te puedo llamar?");
        sueloTecho(RESOLUCION_ANCHO);
        name = br.readLine();

        while (!quit) {
            sueloTecho(RESOLUCION_ANCHO);
            paredesMultiplesFrases(RESOLUCION_ANCHO, RESOLUCION_ALTURA, 11, pedirJugar);

            sueloTecho(RESOLUCION_ANCHO);
            //Conversion de tipo de dato
            option = Integer.parseInt(br.readLine());
            switch (option) {
                case 1:
                    Snake.main(DIMENSIONES_SNAKE);
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

    public static void funcParedes(int anchura, int longitud, int fila, int espaciado, String[] frases) {

    }

    public static void paredes(int anchura, int longitud, int fila, int espaciado, String frase) {

        //Hay que tener en cuenta el espaciado y el numero de frases

        //Bucle
        for(int i = 0; i != (longitud-3); i++) {

            System.out.printf("=");

            //Si ha llegado a la fila entra dentro
            if(i == fila) {

                //Depende si la fila tiene que estar centrada 
                if (espaciado <= -1){

                        for(int j = 0; j != (((anchura-2) - frase.length()) / 2); j++) {
                            System.out.print(" ");
                        }
    
                        System.out.println(ColoresConsola.ANSI_RED() + frase);
    
                        //Terminacion de la frase dependiendo si es impar o par
                        if (frase.length() % 2 == 0) {
                            for(int j = 0; j != (((anchura-2) - frase.length()) / 2); j++) {
                                System.out.print(" ");
                            }
                        } else { //Si la frase es impar se le sumara 1 para que no se joda
                            for(int j = 0; j != (((anchura-2) - frase.length()) / 2 + 1); j++) {
                                System.out.print(" ");
                            }
                        }

                } else { //Si no tiene que estar centrada
                    //Imprimira el espaciado y luego la frase 
                    
                    for(int j = 0; j != (espaciado); j++) {
                        System.out.print(" ");
                    }
                    
                    System.out.print(frase);

                    //Imprime el resto de la linea
                    for(int j = 0; j != ((anchura-2) - frase.length() - espaciado); j++) {
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
    public static void paredesMultiplesFrases(int anchura, int longitud, int fila, String[] frases) {
        for(int i = 0; i != (longitud-3 -(frases.length - 1)); i++) {

            //Si ha llegado a la fila entra dentro
            if(i == fila) {
                for(int k = 0; k < frases.length; k++) {
                    System.out.printf("=");
                    for(int j = 0; j != (((anchura-2) - frases[k].length()) / 2); j++) {
                        System.out.print(" ");
                    }
                
                    System.out.print(ColoresConsola.ANSI_RED() + frases[k] +  ColoresConsola.ANSI_RESET());
                
                    //Terminacion de la frase dependiendo si es impar o par
                    if (frases[k].length() % 2 == 0) {
                        for(int j = 0; j != (((anchura-2) - frases[k].length()) / 2); j++) {
                            System.out.print(" ");
                        }
                    } else { //Si la frase es impar se le sumara 1 para que no se joda
                        for(int j = 0; j != (((anchura-2) - frases[k].length()) / 2 + 1); j++) {
                            System.out.print(" ");
                        }
                    }
                    System.out.printf("=%n");

                }
                
            } else { //Imprime espacios
                
                System.out.printf("=");
                for(int j = 0; j != (anchura-2); j++) {
                    System.out.print(" ");
                }
                System.out.printf("=%n");
            }
        }
    }
     
}
/*
 * Unir las 2 funciones de paredes
 * Agregar colores con una nueva lista xD
 * 
 */