package clases.ModosDeJuego;

import java.io.IOException;

import clases.JuegoBase;

public class ModoNormal extends JuegoBase{

   /* ----- Metodo constructor ----- */
   public ModoNormal() {
   }
   @Override
   public double iniciarJuego(int[] configuracionSnake) throws IOException, InterruptedException {
      final int[] DIMENSIONES = {configuracionSnake[0], configuracionSnake[1]};
      final int TIEMPOMILISEGUNDOS = configuracionSnake[2];
      final int ADMITECOLORES = configuracionSnake[3];

      super.inicializarTablero(super.getCordenadas(), DIMENSIONES);

      return 0;
   }
   
}
