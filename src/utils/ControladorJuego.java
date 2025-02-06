package utils;

import java.io.IOException;

import clases.ModosDeJuego.ModoNormal;

public class ControladorJuego {
   public static double iniciarJuego(int[] configuracionSnake) throws IOException, InterruptedException {

      // Elegir tipo de juego

      // Una funcion por cada modo de juego
      ModoNormal nuevoJuego = new ModoNormal();
      nuevoJuego.iniciarJuego(configuracionSnake);
      return 0;
   }
}
