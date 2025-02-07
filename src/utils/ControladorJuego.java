package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import clases.ModosDeJuego.ModoAtrabesarParedes;
import clases.ModosDeJuego.ModoNormal;
import clases.JuegoBase;
import clases.Player;

public class ControladorJuego {
   public static double iniciarJuego(int[] configuracionSnake, Player player) throws IOException, InterruptedException {

      /* ----- Parte declarativa ----- */
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int opcionModoJuego = 0;
      JuegoBase nuevoJuego;
      String[] explicacionModos = {
            "1. Modo Normal: es el modo clasico del juego de Snake",
            "2. Modo Atrabesar Paredes: es el modo en el cual el jugador puede atrabesar las paredes",
            "Elige el modo \"1\" o \"2\""
      };
      String[][] explicacionJuego = {
            {
                  "Paso 1, Abra el Controlador del juego para poder escribir las direciones por consola, si desea salir del controlador pon \"Salir\"",
                  "Paso 2, Muevete con \"W\", \"A\", \"S\", \"D\", Arriba, izquierda, abajo y derecha respectivamente",
                  "Las letras tendras q escribirlas en el programa controlador y tendras que darle Enter/Intro para enviarlas, de una en una",
                  "Paso 3, Evita chocarte con los obstaculos, a su vez evita ir a la direcion contraria a la que vas",
                  "Paso 4, Presiona la tecla enter para empezar a jugar",
                  "Recuerda cerrar el controlador principal al acabar de jugar, para cerrarlo introduzca \"Salir\"",
                  "¿Estas preparado " + player.getName() + "?"
            },
            {
                  "Paso 1, Abra el Controlador del juego para poder escribir las direciones por consola, si desea salir del controlador pon \"Salir\"",
                  "Paso 2, Muevete con " + ColoresConsola.ANSI_CYAN() + "\"W\"" + ColoresConsola.ANSI_RESET() + ","
                        + ColoresConsola.ANSI_CYAN() + "\"A\"" + ColoresConsola.ANSI_RESET() + ","
                        + ColoresConsola.ANSI_CYAN() + "\"S\"" + ColoresConsola.ANSI_RESET() + ","
                        + ColoresConsola.ANSI_CYAN() + "\"D\"" + ColoresConsola.ANSI_RESET()
                        + ", Arriba, Izquierda, Abajo y Derecha respectivamente",
                  "Las letras tendras q escribirlas en el programa controlador y tendras que darle "
                        + ColoresConsola.ANSI_CYAN() + "Enter/Intro" + ColoresConsola.ANSI_RESET() + " para enviarlas, "
                        + ColoresConsola.ANSI_CYAN() + "de una en una" + ColoresConsola.ANSI_RESET(),
                  "Paso 3, Evita chocarte con los obstaculos y tu cuerpo, a su vez evita ir a la direcion contraria a la que vas",
                  "Paso 4, Presiona la tecla " + ColoresConsola.ANSI_UNDERLINE() + "enter" + ColoresConsola.ANSI_RESET()
                        + " para empezar a jugar",
                  "Recuerda cerrar el controlador principal al acabar de jugar, para cerrarlo introduzca \""
                        + ColoresConsola.ANSI_UNDERLINE() + "Salir" + ColoresConsola.ANSI_RESET() + "\"",
                  "¿Estas preparado " + player.getName() + "?"
            },
      };

      /* ----- Parte principal ----- */
      do {

         for (String frases : explicacionModos) {
            System.out.println(frases);
         }

         try {
            opcionModoJuego = Integer.parseInt(br.readLine());
         } catch (NumberFormatException e) {
            System.out.println("Porfavor, escriba una opcion valida");
            opcionModoJuego = 0;
         }

         switch (opcionModoJuego) {
            case 1:
               nuevoJuego = new ModoNormal();
               break;
            case 2:
               nuevoJuego = new ModoAtrabesarParedes();
               break;
            default:
               nuevoJuego = new ModoNormal();
               break;
         }
      } while (opcionModoJuego < 1 || opcionModoJuego > 2);

      // Elegir tipo de juego
      for (String frases : explicacionJuego[player.getCongiguration()[3]]) {
         System.out.println(frases);
      }
      // Esto hace q no continue el programa sin que presione el enter
      br.readLine();
      return nuevoJuego.iniciarJuego(configuracionSnake);
   }
}