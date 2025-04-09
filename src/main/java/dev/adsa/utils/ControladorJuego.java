/**
 * @author: Aitor de Santos Amoros
 * Fecha: 1/4/2024
 * Descripcion: Este archivo es el que contiene las funciones para iniciar el juego
 */
package dev.adsa.utils;

import java.io.IOException;

import dev.adsa.clases.JuegoBase;
import dev.adsa.clases.Partida;
import dev.adsa.clases.Player;
import dev.adsa.clases.modos.ModoAtravesarParedes;
import dev.adsa.clases.modos.ModoNormal;

public class ControladorJuego {
   /**
    * Inicia el juego de Snake con las configuraciones especificadas.
    * 
    * @param configuracionSnake Un array que contiene la configuracion
    *                           inicial del juego: [ancho, alto, tiempo de
    *                           espera en milisegundos, admite colores].
    * @param player             El jugador que va a jugar.
    * @return El puntaje final
    *         calculado al final del juego.
    * @throws IOException          Si ocurre un error durante la lectura del
    *                              archivo de comandos.
    * @throws InterruptedException Si el hilo de ejecuci n es interrumpido
    *                              durante el tiempo de espera.
    */
   public static double iniciarJuego(Player player, Partida partida) throws IOException, InterruptedException {

      /* ----- Parte declarativa ----- */
      int opcionModoJuego = 0;
      JuegoBase nuevoJuego;
      String[] explicacionModos = {
            "1. Modo Normal: es el modo clasico del juego de Snake",
            "2. Modo Atrabesar Paredes: es el modo en el cual el jugador puede atrabesar las paredes",

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
      for (String frases : explicacionModos) {
         System.out.println(frases);
      }

      opcionModoJuego = Utilidades.pedirNumeroEntero("Elige el modo: ", 1, 2);

      partida.setModoDeJuego(opcionModoJuego);
      switch (opcionModoJuego) {
         case 1:
            nuevoJuego = new ModoNormal();
            break;
         case 2:
            nuevoJuego = new ModoAtravesarParedes();
            break;
         default:
            nuevoJuego = new ModoNormal();
            break;
      }

      // Elegir tipo de juego
      for (String frases : explicacionJuego[player.getCongiguration()[3]]) {
         System.out.println(frases);
      }
      // Esto hace q no continue el programa sin que presione el enter
      Utilidades.presionarEnter();
      return nuevoJuego.iniciarJuego(player.getCongiguration(), partida);
   }
}