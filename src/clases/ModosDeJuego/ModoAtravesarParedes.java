package clases.ModosDeJuego;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import clases.JuegoBase;

public class ModoAtravesarParedes extends JuegoBase {
   /* ----- Metodo constructor ----- */
   public ModoAtravesarParedes() {
   }


   /**
    * Elimina la cola de la serpiente. La cola se encuentra en la esquina opuesta
    * a la cabeza de la serpiente. La cola se elimina reemplazando su valor en el
    * tablero por un "0" y actualizando la posicion de la cola en la variable
    * cordsCola.
    * 
    * @param movs La lista de movimientos que se va a realizar.
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
            if (getCordsCola()[1] == 0)
               setCordsCola(getCordsCola()[0], getCordenadas().length - 1);
            else
               setCordsCola(getCordsCola()[0], getCordsCola()[1] - 1);
            break;

         case 'A':
            if (getCordsCola()[0] == 1)
               setCordsCola(getCordenadas()[getCordsCola()[0]].length(), getCordsCola()[1]);
            else
               setCordsCola(getCordsCola()[0] - 1, getCordsCola()[1]);
            break;

         case 'S':
            if (getCordsCola()[1] == (getCordenadas().length - 1))
               setCordsCola(getCordsCola()[0], 0);
            else
               setCordsCola(getCordsCola()[0], getCordsCola()[1] + 1);
            break;

         case 'D':
            if (getCordsCola()[0] == (getCordenadas()[getCordsCola()[1]].length()))
               setCordsCola(1, getCordsCola()[1]);
            else
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
   public double iniciarJuego(int[] configuracionSnake) throws IOException, InterruptedException {
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

         mostrarTablero(ADMITECOLORES);

         // Tiempo de espera con hilos
         Thread.sleep(TIEMPOMILISEGUNDOS);
         separacion();

         // Esto se tendra que hacer despues para que un espacio en blanco no de fallo
         guardarDireccion = fr.readLine();
         direcion = (guardarDireccion != null ? guardarDireccion : direcion.toUpperCase());
         // Detecta si es un movimiento valido con una condicion ternaria y guarda el
         // movimiento para crear la cola

         try { // Este try lo que esta haciendo es para que en el switch de la cabeza me pille
               // el error de que se ha salido del array

            if (comprobarColision(direcion)) {
               if(detectarFrutaDetras(direcion)) {
                  snakeLongitud++;
                  haComido = true;
               }
            } else {
               if(detectarFruta(direcion)) {
                  snakeLongitud++;
                  haComido = true;
               }
            }
            /*
             * 
             * Parte para la eliminacion y actualizacion de la cola
             * 
             */
            movs = (movs.concat(direcion));

            if (!haComido) {

               eliminarCola(movs);

               // Elimina el primer movimiento ya que deberia ya haberse ejecutado
               movs = movs.substring(1);
            }

            if (comprobarColision(direcion))
               crearCabezaDetras(alive, direcion);
            else
               crearCabeza(alive, direcion);

         } catch (StringIndexOutOfBoundsException e) {// Aqui capta el error de que el snake se ha salido de la
                                                      // pantalla, por lo tanto pasa de vivo a muerto
            alive = false;
            System.out.println("Ha petado");
         } catch (Exception e) { // Por si acaso que no me fio xD
            alive = false;
         }

         if (snakeLongitud == DIMENSIONES[0] * DIMENSIONES[1])
            win = true;

      } while (alive && !win);

      System.out.println(alive ? "Enhorabuena, has ganado" : "Has perdido");
      fr.close();

      return calcularPuntaje(snakeLongitud, DIMENSIONES[0], DIMENSIONES[1], TIEMPOMILISEGUNDOS);
   }


/**
 * Comprueba si la serpiente ha llegado al borde del tablero en función de la dirección
 * en la que se está moviendo.
 *
 * @param direcion La dirección en la que se mueve la serpiente (W, A, S, D).
 * @return Un booleano que indica si la serpiente ha colisionado con el borde del tablero.
 */

   public boolean comprobarColision(String direcion) {

      boolean estaAlBorde = false;
      if (getCordsCabeza()[1] == 0 && direcion.equals("W")) {
         estaAlBorde = true;
      } else if (getCordsCabeza()[1] == (getCordenadas().length - 1) && direcion.equals("S")) {
         estaAlBorde = true;
      } else if (getCordsCabeza()[0] == 1 && direcion.equals("A")) {
         estaAlBorde = true;
      } else if (getCordsCabeza()[0] == (getCordenadas()[0].length()) && direcion.equals("D")) {
         estaAlBorde = true;
      }

      return estaAlBorde;
   }


   /**
    * Metodo para crear la cabeza en la pared opuesta a la que se iba a chocar
    * En este Switch lo que hago es detectar hacia donde va la cabeza y remplazo lo
    * que haya por la cabeza y luego actualizo la posicion de ella
    * Con el if detecta si la posicion a la que quiere haceder esta el cuerpo de la
    * serpiente, si es asi muere
    * @param alive   Variable que indica si el snake esta vivo o no
    * @param direcion Direccion en la que se esta moviendo el snake
    * @return Un boolean que indica si el snake ha muerto o no
    */
   public boolean crearCabezaDetras(boolean alive, String direcion) {
      switch (direcion) {
         case "W":
            setCordsCabeza(getCordsCabeza()[0], (getCordenadas().length - 1));
            if ('1' == getCordenadas()[getCordsCabeza()[1] - 1].charAt(getCordsCabeza()[0] - 1))
               alive = false;

            reemplazarCasilla((getCordenadas().length - 1), getCordsCabeza()[0] - 1, getCordsCabeza()[0], "1");
            break;

         case "A":
            setCordsCabeza(getCordenadas()[getCordsCabeza()[1]].length(), getCordsCabeza()[1]);
            if ('1' == getCordenadas()[getCordsCabeza()[1]].charAt(getCordsCabeza()[0] - 2))
               alive = false;
            reemplazarCasilla(getCordsCabeza()[1], getCordenadas()[1].length() - 1, getCordenadas()[1].length(), "1");
            break;

         case "S":
            setCordsCabeza(getCordsCabeza()[0], 0);
            if ('1' == getCordenadas()[getCordsCabeza()[1] + 1].charAt(getCordsCabeza()[0] - 1))
               alive = false;

            reemplazarCasilla(0, getCordsCabeza()[0] - 1, getCordsCabeza()[0], "1");
            break;

         case "D":
            setCordsCabeza(1, getCordsCabeza()[1]);
            if ('1' == getCordenadas()[getCordsCabeza()[1]].charAt(getCordsCabeza()[0]))
               alive = false;
            reemplazarCasilla(getCordsCabeza()[1], 0, 1, "1");
            break;

         default:
            break;
      }

      return alive;
   }

   /**
    * Detecta si la serpiente ha comido una fruta. Segun la direccion en la que
    * se esta moviendo la serpiente, se evalua la casilla que esta en la pared
    * opuesta
    * para ver si es una fruta. Si es una fruta se marca que ha comido y se
    * incrementa la longitud de la serpiente en 1.
    * 
    * @param snakeLongitud La longitud actual de la serpiente
    * @return La longitud actualizada de la serpiente
    */
   public boolean detectarFrutaDetras(String direcion) {
      boolean frutaDetectada = false;
      switch (direcion) {
         case "W":
            if ('2' == getCordenadas()[getCordenadas().length - 1].charAt(getCordsCabeza()[0] - 1)) {
               frutaDetectada = true;
            }
            break;

         case "A":
            if ('2' == getCordenadas()[getCordsCabeza()[1]].charAt(getCordenadas()[getCordsCabeza()[1]].length() - 1)) {
               frutaDetectada = true;
            }
            break;

         case "S":
            if ('2' == getCordenadas()[0].charAt(getCordsCabeza()[0] - 1)) {
               frutaDetectada = true;
            }
            break;

         case "D":
            if ('2' == getCordenadas()[getCordsCabeza()[1]].charAt(0)) {
               frutaDetectada = true;
            }
            break;
         default:
            break;
      }

      return frutaDetectada;
   }
}