package clases.ModosDeJuego;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import clases.JuegoBase;

public class ModoAtrabesarParedes extends JuegoBase {
   /* ----- Metodo constructor ----- */
   public ModoAtrabesarParedes() {
   }

   /**
    * Reinicia las variables del juego a sus valores predeterminados.
    * Establece que el jugador esta vivo, que no ha comido, que no ha ganado,
    * la longitud de la serpiente en 3, los movimientos en "DD" y la
    * direccion en "D".
    */
   @Override
   public void resetearVariables() {
      super.setAlive(true);
      super.setHaComido(true);
      super.setWin(false);
      super.setSnakeLongitud(3);
      super.setMovs("DD");
      super.setDirecion("D");
   }

   /**
    * Inicia el juego de Snake en modo normal. Configura el tablero, las
    * dimensiones
    * y las condiciones del juego, y ejecuta el ciclo principal del juego.
    * 
    * @param configuracionSnake Un array que contiene la configuración inicial del
    *                           juego: [ancho, alto, tiempo de espera en
    *                           milisegundos, admite colores].
    * @return El puntaje final calculado al final del juego.
    * @throws IOException          Si ocurre un error durante la lectura del
    *                              archivo de comandos.
    * @throws InterruptedException Si el hilo de ejecución es interrumpido durante
    *                              el tiempo de espera.
    */
   @Override
   public double iniciarJuego(int[] configuracionSnake) throws IOException, InterruptedException {
      final int[] DIMENSIONES = { configuracionSnake[0], configuracionSnake[1] };
      final int TIEMPOMILISEGUNDOS = configuracionSnake[2];
      final int ADMITECOLORES = configuracionSnake[3];

      BufferedReader fr = new BufferedReader(new FileReader("./content.txt"));
      resetearVariables();

      super.inicializarTablero(DIMENSIONES);

      do {

         if (super.isHaComido()) {
            super.generarFruta(DIMENSIONES);
            super.setHaComido(false);
         }

         mostrarTablero(ADMITECOLORES);

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

               eliminarCola();

               // Elimina el primer movimiento ya que deberia ya haberse ejecutado
               super.setMovs(super.getMovs().substring(1));
            }

            crearCabeza();

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
