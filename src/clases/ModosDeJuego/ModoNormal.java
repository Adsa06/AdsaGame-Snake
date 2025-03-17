package clases.ModosDeJuego;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import clases.JuegoBase;
import clases.Partida;
public class ModoNormal extends JuegoBase {

   /* ----- Metodo constructor ----- */
   public ModoNormal() {
   }

   /**
    * Elimina la cola de la serpiente en el tablero y actualiza las coordenadas
    * de la cola en función del movimiento especificado.
    *
    * @param movs Un string que representa la lista de movimientos. El primer
    *             carácter de este string se utiliza para determinar la dirección
    *             del movimiento actual de la serpiente ('W', 'A', 'S', 'D').
    */

   @Override
   public void eliminarCola(String movs) {
      // Elimino la cola
      reemplazarCasilla(getCordsCola()[1], getCordsCola()[0] - 1, getCordsCola()[0], "0");

      // Puedo hacer con 2 dobles operadortes ternarios, pero creo que me decanto mas
      // por el switch, q es mas facil de ver
      /*
       * cordsCola[0] += (movs.charAt(0) == 'A' ? -1 : (movs.charAt(0) == 'D' ? 1 :
       * 0));
       * cordsCola[1] += (movs.charAt(0) == 'W' ? -1 : (movs.charAt(0) == 'S' ? 1 :
       * 0));
       */

      switch (movs.charAt(0)) {
         case 'W':
            setCordsCola(getCordsCola()[0], getCordsCola()[1] - 1);
            break;

         case 'A':
            setCordsCola(getCordsCola()[0] - 1, getCordsCola()[1]);
            break;

         case 'S':
            setCordsCola(getCordsCola()[0], getCordsCola()[1] + 1);
            break;

         case 'D':
            setCordsCola(getCordsCola()[0] + 1, getCordsCola()[1]);
            break;

         default:
            break;
      }
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
   public double iniciarJuego(int[] configuracionSnake, Partida partida) throws IOException, InterruptedException {
      final int[] DIMENSIONES = { configuracionSnake[0], configuracionSnake[1] };
      final int TIEMPOMILISEGUNDOS = configuracionSnake[2];
      final int ADMITECOLORES = configuracionSnake[3];

      String guardarDireccion;
      int snakeLongitud = 3;
      boolean win = false;
      boolean alive = true;
      boolean haComido = true;
      String direcion = "D";
      String movs = "DD"; // Secuencia de movimientos para saber la continuacion de la cola

      BufferedReader fr = new BufferedReader(new FileReader("./content.txt"));

      super.inicializarTablero(DIMENSIONES);

      do {

         if (haComido) {
            super.generarFruta(DIMENSIONES);
            haComido = false;
         }

         System.out.println("Longitud de la serpiente: " + snakeLongitud);
         mostrarTablero(ADMITECOLORES);

         // Tiempo de espera con hilos
         Thread.sleep(TIEMPOMILISEGUNDOS);
         separacion();

         // Esto se tendra que hacer despues para que un espacio en blanco no de fallo
         guardarDireccion = fr.readLine();
         direcion = ((guardarDireccion != null && guardarDireccion.length() == 1) ? guardarDireccion.toUpperCase() : direcion.toUpperCase());
         // Detecta si es un movimiento valido con una condicion ternaria y guarda el
         // movimiento para crear la cola

         try { // Este try lo que esta haciendo es para que en el switch de la cabeza me pille
               // el error de que se ha salido del array
            if (detectarFruta(direcion)) {
               haComido = true;
               snakeLongitud++;
            }

            movs = movs.concat(direcion);

            if (!haComido) {

               eliminarCola(movs);

               // Elimina el primer movimiento ya que deberia ya haberse ejecutado
               movs = movs.substring(1);
            }

            alive = crearCabeza(alive, direcion);

         } catch (StringIndexOutOfBoundsException e) {// Aqui capta el error de que el snake se ha salido de la
                                                      // pantalla, por lo tanto pasa de vivo a muerto
            alive = false;
         } catch (Exception e) { // Por si acaso que no me fio xD
            alive = false;
         }

         if (snakeLongitud == DIMENSIONES[0] * DIMENSIONES[1])
            win = true;

      } while (alive && !win);

      System.out.println(alive ? "Enhorabuena, has ganado" : "Has perdido");
      fr.close();
      partida.setGanado(win);
      partida.setLongitudSerpiente(snakeLongitud);
      return calcularPuntaje(snakeLongitud, DIMENSIONES[0], DIMENSIONES[1], TIEMPOMILISEGUNDOS);
   }

}