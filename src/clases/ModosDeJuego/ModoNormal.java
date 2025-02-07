package clases.ModosDeJuego;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import clases.JuegoBase;

public class ModoNormal extends JuegoBase {

   /* ----- Metodo constructor ----- */
   public ModoNormal() {
   }

   /**
    * 
    * @param tablero     Es la variable en la que genera la cabeza
    * @param cordsCabeza Es la posicion de la cabeza
    * @param direcion    Es la direccion de la cabeza
    * @return Devuelve un booleano que dice si esta vivo o muerto
    */
   @Override
   public void crearCabeza() {
      /*
       * 
       * Switch para crear la cabeza
       * En este Switch lo que hago es detectar hacia donde va la cabeza y remplazo lo
       * que haya por la cabeza y luego actualizo la posicion de ella
       * Con el if detecta si la posicion a la que quiere haceder esta el cuerpo de la
       * serpiente, si es asi muere
       */
      switch (getDirecion()) {
         case "W":
            if ('1' == getCordenadas()[getCordsCabeza()[1] - 1].charAt(getCordsCabeza()[0] - 1))
               setAlive(false);

            /* ----- Parte de la cabeza ----- */
            reemplazarCasilla(getCordsCabeza()[1] - 1, getCordsCabeza()[0] - 1, getCordsCabeza()[0], "1");

            setCordsCabeza(getCordsCabeza()[0], getCordsCabeza()[1] - 1);
            break;

         case "A":
            if ('1' == getCordenadas()[getCordsCabeza()[1]].charAt(getCordsCabeza()[0] - 2))
               setAlive(false);

            /* ----- Parte de la cabeza ----- */
            reemplazarCasilla(getCordsCabeza()[1], getCordsCabeza()[0] - 2, getCordsCabeza()[0] - 1, "1");

            setCordsCabeza(getCordsCabeza()[0] - 1, getCordsCabeza()[1]);
            break;

         case "S":
            if ('1' == getCordenadas()[getCordsCabeza()[1] + 1].charAt(getCordsCabeza()[0] - 1))
               setAlive(false);

            /* ----- Parte de la cabeza ----- */
            reemplazarCasilla(getCordsCabeza()[1] + 1, getCordsCabeza()[0] - 1, getCordsCabeza()[0], "1");

            setCordsCabeza(getCordsCabeza()[0], getCordsCabeza()[1] + 1);
            break;

         case "D":
            if ('1' == getCordenadas()[getCordsCabeza()[1]].charAt(getCordsCabeza()[0]))
               setAlive(false);

            /* ----- Parte de la cabeza ----- */
            reemplazarCasilla(getCordsCabeza()[1], getCordsCabeza()[0], getCordsCabeza()[0] + 1, "1");

            setCordsCabeza(getCordsCabeza()[0] + 1, getCordsCabeza()[1]);
            break;

         default:
            break;
      }
   }

   /**
    * 
    * @param tablero   Es la variable en la que elimina la cola
    * @param cordsCola Es la posicion de la cola actual
    * @param movs      Es la lista de movimientos
    */
   @Override
   public void eliminarCola() {
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

      switch (getMovs().charAt(0)) {
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

            super.detectarFruta();

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

      } while (super.isAlive() && !super.isWin());

      System.out.println(super.isAlive() ? "Enhorabuena, has ganado" : "Has perdido");
      fr.close();

      return calcularPuntaje(super.getSnakeLongitud(), DIMENSIONES[0], DIMENSIONES[1], TIEMPOMILISEGUNDOS);
   }

}