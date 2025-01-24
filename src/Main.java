/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 17/10/2024
 * Este archivo muestra el menu y llama a las distintas funciones
 * 
 */

//Importo las clases de java.io i la carpeta de src
import java.io.*;

import clases.Player;
import utils.ColoresConsola;
import utils.Snake;
import utils.Configuracion;

public class Main {

    //Creo la funcion princiapl con un throws IOException para capturar mensajes por consola
    public static void main(String[] args) throws IOException, InterruptedException {
        
        /* ----- Parte declarativa ----- */

        //Declaro el BufferedReader para leer datos por consola
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Player player = new Player();

        Boolean continuar = true;
        int option = 0;

        String pedirNombre = "\r\n" + //
                        "  ___                                _  _                 _   ___                    _                              _        _  _                         ___ \r\n" + //
                        " | _ ) _  _  ___  _ _   ___  ___  __| |(_) __ _  ___     (_) / __| ___  _ __   ___  | |_  ___   _ __  _  _  ___  __| | ___  | || | __ _  _ __   __ _  _ _|__ \\\r\n" + //
                        " | _ \\| || |/ -_)| ' \\ / _ \\(_-< / _` || |/ _` |(_-< _  / /_| (__ / _ \\| '  \\ / _ \\ |  _|/ -_) | '_ \\| || |/ -_)/ _` |/ _ \\ | || |/ _` || '  \\ / _` || '_| /_/\r\n" + //
                        " |___/ \\_,_|\\___||_||_|\\___//__/ \\__,_||_|\\__,_|/__/( ) \\___|\\___|\\___/|_|_|_|\\___/  \\__|\\___| | .__/ \\_,_|\\___|\\__,_|\\___/ |_||_|\\__,_||_|_|_|\\__,_||_|  (_) \r\n" + //
                        "                                                    |/                                         |_|                                                            \r\n" + //
                        "";
        String pedirJugar = "\r\n" + //
                        "   _    _                        _                        _                   ___ \r\n" + //
                        "  (_)  /_\\    __ _ _  _ ___   __| |___ ______ __ _ ___   (_)_  _ __ _ __ _ _ |__ \\\r\n" + //
                        " / /_ / _ \\  / _` | || / -_) / _` / -_(_-/ -_/ _` (_-<   | | || / _` / _` | '_|/_/\r\n" + //
                        " \\___/_/ \\_\\ \\__, |\\_,_\\___| \\__,_\\___/__\\___\\__,_/__/  _/ |\\_,_\\__, \\__,_|_| (_) \r\n" + //
                        "                |_|                                    |__/     |___/             \r\n" + //
                        "  _            _                                                                  \r\n" + //
                        " / |  ___   _ | |_  _ __ _ __ _ _ _                                               \r\n" + //
                        " | | |___| | || | || / _` / _` | '_|                                              \r\n" + //
                        " |_| |___|  \\__/ \\_,_\\__, \\__,_|_|                                                \r\n" + //
                        "                     |___/                                                        \r\n" + //
                        "  _ _          ___           __ _                                                 \r\n" + //
                        " |_  )  ___   / __|___ _ _  / _(_)                                                \r\n" + //
                        "  / /  |___| | (__/ _ | ' \\|  _| |                                                \r\n" + //
                        " /___| |___|  \\___\\___|_||_|_| |_|                                                \r\n" + //
                        "                                                                               \r\n" + //
                        "  _ __        ___      _ _                                                        \r\n" + //
                        " |__ /  ___  / __|__ _| (_)_ _                                                    \r\n" + //
                        "  |_ \\ |___| \\__ / _` | | | '_|                                                   \r\n" + //
                        " |___/ |___| |___\\__,_|_|_|_|                                                     \r\n" + //
                        "                                                                                  \r\n" + //
                        "";
        String[][] explicacionJuego = {
            {
                "Paso 1, Abra el Controlador del juego para poder escribir las direciones por consola, si desea salir del controlador pon \"Salir\"",
                "Paso 2, Muevete con \"W\", \"A\", \"S\", \"D\", Arriba, izquierda, abajo y derecha respectivamente",
                "Las letras tendras q escribirlas en el programa controlador y tendras que darle Enter/Intro para enviarlas, de una en una",
                "Paso 3, Evita chocarte con las paredes y tu cuerpo, a su vez evita ir a la direcion contraria a la que vas",
                "Paso 4, Presiona la tecla enter para empezar a jugar",
                "Recuerda cerrar el controlador principal al acabar de jugar, para cerrarlo introduzca \"Salir\"",
                "¿Estas preparado " + player.getName() + "?"
            },
            {
                "Paso 1, Abra el Controlador del juego para poder escribir las direciones por consola, si desea salir del controlador pon \"Salir\"",
                "Paso 2, Muevete con " + ColoresConsola.ANSI_CYAN() + "\"W\"" + ColoresConsola.ANSI_RESET() + "," + ColoresConsola.ANSI_CYAN() + "\"A\"" + ColoresConsola.ANSI_RESET() + "," + ColoresConsola.ANSI_CYAN() + "\"S\"" + ColoresConsola.ANSI_RESET() + "," + ColoresConsola.ANSI_CYAN() + "\"D\"" + ColoresConsola.ANSI_RESET() + ", Arriba, Izquierda, Abajo y Derecha respectivamente",
                "Las letras tendras q escribirlas en el programa controlador y tendras que darle " + ColoresConsola.ANSI_CYAN() + "Enter/Intro" + ColoresConsola.ANSI_RESET() + " para enviarlas, " + ColoresConsola.ANSI_CYAN() + "de una en una" + ColoresConsola.ANSI_RESET(),
                "Paso 3, Evita chocarte con las paredes y tu cuerpo, a su vez evita ir a la direcion contraria a la que vas",
                "Paso 4, Presiona la tecla " + ColoresConsola.ANSI_UNDERLINE() + "enter" + ColoresConsola.ANSI_RESET() + " para empezar a jugar",
                "Recuerda cerrar el controlador principal al acabar de jugar, para cerrarlo introduzca \"" + ColoresConsola.ANSI_UNDERLINE() + "Salir" + ColoresConsola.ANSI_RESET() + "\"",
                "¿Estas preparado " + player.getName() + "?"
            },
        };

        /* ----- Parte principal ----- */

        //Aqui faltaria limpiar la pantalla por consola
        //Evito el error que aparece al introducir un nombre demasiado largo repetiendo que me pida un nombre hasta que sea adecuado
        do {
            System.out.println(pedirNombre);
            player.setName(br.readLine());
            //Este es un if que detecta si es mayor a cuarenta la longitud de la frase si es asi ejecuta el sigueinte linea de codigo
            if(player.getName().length() > 40) System.out.println("Introduzca un nombre menor a 40 caracteres");
        } while (player.getName().length() > 40);
        // Actualizamos el contenido del array con el nombre correcto
        explicacionJuego[0][6] = "¿Estas preparado " + player.getName() + "?";
        explicacionJuego[1][6] = "¿Estas preparado " + player.getName() + "?";

        do {
            System.out.println(pedirJugar);
            //Conversion de tipo de dato
            try { //Este try impide que el usuario escriba una letra y pete el programa, como opcion es = 0, se ira al default en el switch
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Porfavor, escriba una opcion valida");
                option = 0;
            }
            switch (option) {
                case 1:

                    /* ----- Esta zona hara que se reinicie el archivo txt ----- */ //Estas lineas estan explicadas en el ControladorPrincipal.java
                    BufferedWriter fr = new BufferedWriter(new FileWriter("./content.txt", false));
                    fr.close();
                    /* ----- */
                    for(String frases : explicacionJuego[player.getCongiguration()[3]]) {
                        System.out.println(frases);
                    }
                    //Esto hace q no continue el programa sin que presione el enter
                    br.readLine();
                    player.setScore(Snake.juegoPrincipal(player.getCongiguration()));
                    break;
                case 2:
                    player.setCongiguration(Configuracion.cambiarConfiguracion(player.getCongiguration()));
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
}
/* Ideas:
 *
 * Snake 3D, con varias pantallas para ver todas las capas de la 3ª dimension a la vez
 * Snake que atraviese las paredes
 * 
 * 
 */