/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 17/10/2024
 * Este archivo muestra el menu y llama a las distintas funciones
 * 
 */

//Importo las clases de java.io i la carpeta de src
import java.io.*;

import utils.ColoresConsola;
import utils.Snake;

public class Main {

    //Creo la funcion princiapl con un throws IOException para capturar mensajes por consola
    public static void main(String[] args) throws IOException, InterruptedException {
        
        /* ----- Parte declarativa ----- */

        //Declaro el BufferedReader para leer datos por consola
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name = "";
        Boolean continuar = true;
        int option = 0;

        String[] pedirNombre = {"Buenos dias, ¿Como te puedo llamar?"};
        String[] pedirJugar = {
            "¿A que deseas jugar " + name + "?",
            "",
            "1 = Jugar",
            "2 = Confi",
            "3 = Salir"
        };
        String[][] explicacionJuego = {
            {
                "Paso 1, Abra el Controlador del juego para poder escribir las direciones por consola, si desea salir del controlador pon \"Salir\"",
                "Paso 2, Muevete con " + ColoresConsola.ANSI_CYAN() + "\"W\"" + ColoresConsola.ANSI_RESET() + "," + ColoresConsola.ANSI_CYAN() + "\"A\"" + ColoresConsola.ANSI_RESET() + "," + ColoresConsola.ANSI_CYAN() + "\"S\"" + ColoresConsola.ANSI_RESET() + "," + ColoresConsola.ANSI_CYAN() + "\"D\"" + ColoresConsola.ANSI_RESET() + ", Arriba, Izquierda, Abajo y Derecha respectivamente",
                "Las letras tendras q escribirlas en el programa controlador y tendras que darle " + ColoresConsola.ANSI_CYAN() + "Enter/Intro" + ColoresConsola.ANSI_RESET() + " para enviarlas, " + ColoresConsola.ANSI_CYAN() + "de una en una" + ColoresConsola.ANSI_RESET(),
                "Paso 3, Evita chocarte con las paredes y tu cuerpo, a su vez evita ir a la direcion contraria a la que vas",
                "Paso 4, Presiona la tecla " + ColoresConsola.ANSI_UNDERLINE() + "enter" + ColoresConsola.ANSI_RESET() + " para empezar a jugar",
                "Recuerda cerrar el controlador principal al acabar de jugar, para cerrarlo introduzca \"" + ColoresConsola.ANSI_UNDERLINE() + "Salir" + ColoresConsola.ANSI_RESET() + "\""
            },
            {
                "Paso 1, Abra el Controlador del juego para poder escribir las direciones por consola, si desea salir del controlador pon \"Salir\"",
                "Paso 2, Muevete con \"W\", \"A\", \"S\", \"D\", Arriba, izquierda, abajo y derecha respectivamente",
                "Las letras tendras q escribirlas en el programa controlador y tendras que darle Enter/Intro para enviarlas, de una en una",
                "Paso 3, Evita chocarte con las paredes y tu cuerpo, a su vez evita ir a la direcion contraria a la que vas",
                "Paso 4, Presiona la tecla enter para empezar a jugar",
                "Recuerda cerrar el controlador principal al acabar de jugar, para cerrarlo introduzca \"Salir\""
            },
        };

        final int RESOLUCION_ALTURA = 30;
        final int RESOLUCION_ANCHO = 120;

        int[] dimensionesTableroSnake = {10, 20};
        int tiempoMilisegundosSnake = 375;
        int admiteColores = 0;
        /* ----- Parte principal ----- */

        //Aqui faltaria limpiar la pantalla por consola
        //Evito el error que aparece al introducir un nombre demasiado largo repetiendo que me pida un nombre hasta que sea adecuado
        do {
            sueloTecho(RESOLUCION_ANCHO);
            paredesMultiplesFrases(RESOLUCION_ANCHO, RESOLUCION_ALTURA, 14, pedirNombre);
            //System.out.println("Buenos dias, ¿Como te puedo llamar?");
            sueloTecho(RESOLUCION_ANCHO);
            name = br.readLine();
            //Este es un if que detecta si es mayor a cuarenta la longitud de la frase si es asi ejecuta el sigueinte linea de codigo
            if(name.length() > 40) System.out.println("Introduzca un nombre menor a 40 caracteres");
        } while (name.length() > 40);
        // Actualizamos el contenido del array con el nombre correcto
        pedirJugar[0] = "¿A que deseas jugar " + name + "?";

        do {
            sueloTecho(RESOLUCION_ANCHO);
            paredesMultiplesFrases(RESOLUCION_ANCHO, RESOLUCION_ALTURA, 11, pedirJugar);

            sueloTecho(RESOLUCION_ANCHO);
            //Conversion de tipo de dato
            try { //Este try impide que el usuario escriba una letra y pete el programa, como opcion es = 0, se ira al default en el switch
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Porfavor, escriba una opcion valida");
            }
            switch (option) {
                case 1:

                    /* ----- Esta zona hara que se reinicie el archivo txt ----- */ //Estas lineas estan explicadas en el ControladorPrincipal.java
                    BufferedWriter fr = new BufferedWriter(new FileWriter("./content.txt", false));
                    fr.close();
                    /* ----- */
                    for(String frases : explicacionJuego[admiteColores]) {
                        System.out.println(frases);
                    }
                    //Esto hace q no continue el programa sin que presione el enter
                    br.readLine();
                    Snake.main(dimensionesTableroSnake, tiempoMilisegundosSnake, admiteColores);
                    break;
                case 2:

                    break;
                case 3:
                continuar = false;     
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (continuar);
        System.out.println("Hasta otra");
    }

    public static void sueloTecho(int longitud) {
        for(int i = longitud; i > 0; i--) {
            System.out.print("-");
        }
        System.out.printf("%n");
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
                
                    System.out.printf(ColoresConsola.ANSI_RED() + frases[k] +  ColoresConsola.ANSI_RESET());
                
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