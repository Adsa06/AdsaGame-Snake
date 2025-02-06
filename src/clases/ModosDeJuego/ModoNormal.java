package clases.ModosDeJuego;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import clases.JuegoBase;

public class ModoNormal extends JuegoBase {

   /* ----- Metodo constructor ----- */
   public ModoNormal() {
   }

   @Override
   public double iniciarJuego(int[] configuracionSnake) throws IOException, InterruptedException {
      final int[] DIMENSIONES = { configuracionSnake[0], configuracionSnake[1] };
      final int TIEMPOMILISEGUNDOS = configuracionSnake[2];
      final int ADMITECOLORES = configuracionSnake[3];

      BufferedReader fr = new BufferedReader(new FileReader("./content.txt"));

      super.inicializarTablero(super.getCordenadas(), DIMENSIONES);

      do {

         if (super.isHaComido()) {
            super.generarFruta(super.getCordenadas(), DIMENSIONES);
            super.setHaComido(false);
         }

         mostrarTablero(super.getCordenadas(), super.getCordsCabeza(), super.getCordsCola(), ADMITECOLORES);

         // Tiempo de espera con hilos
         Thread.sleep(TIEMPOMILISEGUNDOS);
         separacion();

         // Esto se tendra que hacer despues para que un espacio en blanco no de fallo
         super.setGuardarDireccion(fr.readLine());
         // 1 condicion ternarias para validar si no es nulo y si es W, A, S o D
         super.setDirecion((super.getGuardarDireccion() != null && !super.getGuardarDireccion().equals("")
               && "WASD".contains(super.getGuardarDireccion().toUpperCase())) ? super.getGuardarDireccion()
                     : super.getDirecion());
         super.setDirecion(super.getDirecion().toUpperCase());
         // Detecta si es un movimiento valido con una condicion ternaria y guarda el
         // movimiento para crear la cola

         try { // Este try lo que esta haciendo es para que en el switch de la cabeza me pille
               // el error de que se ha salido del array

            switch (super.getDirecion()) {
               case "W":
                  if ('2' == super.getCordenadas()[super.getCordsCabeza()[1] - 1]
                        .charAt(super.getCordsCabeza()[0] - 1)) {
                     super.setHaComido(true);
                     super.setSnakeLongitud(super.getSnakeLongitud() + 1);
                  }
                  break;

               case "A":
                  if ('2' == super.getCordenadas()[super.getCordsCabeza()[1]].charAt(super.getCordsCabeza()[0] - 2)) {
                     super.setHaComido(true);
                     super.setSnakeLongitud(super.getSnakeLongitud() + 1);
                  }
                  break;

               case "S":
                  if ('2' == super.getCordenadas()[super.getCordsCabeza()[1] + 1]
                        .charAt(super.getCordsCabeza()[0] - 1)) {
                     super.setHaComido(true);
                     super.setSnakeLongitud(super.getSnakeLongitud() + 1);
                  }
                  break;

               case "D":
                  if ('2' == super.getCordenadas()[super.getCordsCabeza()[1]].charAt(super.getCordsCabeza()[0])) {
                     super.setHaComido(true);
                     super.setSnakeLongitud(super.getSnakeLongitud() + 1);
                  }
                  break;
               default:
                  break;
            }

            /*
             * 
             * Parte para la eliminacion y actualizacion de la cola
             * 
             */
            super.setMovs(super.getMovs().concat(getDirecion()));

            if (!super.isHaComido()) {

               eliminarCola(super.getCordenadas(), super.getCordsCola(), super.getMovs());

               // Elimina el primer movimiento ya que deberia ya haberse ejecutado
               super.setMovs(super.getMovs().substring(1));
            }

            super.setAlive(crearCabeza(super.getCordenadas(), super.getCordsCabeza(), super.getDirecion()));

         } catch (StringIndexOutOfBoundsException e) {// Aqui capta el error de que el snake se ha salido de la
                                                      // pantalla, por lo tanto pasa de vivo a muerto
            super.setAlive(false);
         } catch (Exception e) { // Por si acaso que no me fio xD
            super.setAlive(false);
         }

         if (super.getSnakeLongitud() == DIMENSIONES[0] * DIMENSIONES[1])
            super.setWin(true);
         ;
      } while (super.isAlive() && !super.isWin());

      System.out.println(super.isAlive() ? "Enhorabuena, has ganado" : "Has perdido");
      System.out.println(calcularPuntaje(super.getSnakeLongitud(), DIMENSIONES[0], DIMENSIONES[1], TIEMPOMILISEGUNDOS));
      fr.close();

      return calcularPuntaje(super.getSnakeLongitud(), DIMENSIONES[0], DIMENSIONES[1], TIEMPOMILISEGUNDOS);
   }

}
