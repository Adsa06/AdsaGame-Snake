/**
 * @author: Aitor de Santos Amoros
 * Fecha: 1/4/2024
 * Descripcion: Clase plantilla del juego
 * @see modos.ModoAtravesarParedes
 * @see modos.ModoNormal
 */
package clases;

import java.io.IOException;

import utils.ColoresConsola;

public abstract class JuegoBase {
   private StringBuilder[] coordenadas;

   private int[] coordsCabeza = { 3, 0 };
   private int[] coordsCola = { 1, 0 };

   /* ----- GETTERS Y SETTERS ----- */

   /**
    * @return Un array de StringBuilder que representa el tablero del juego. Cada
    *         StringBuilder es una fila del tablero.
    */
   public StringBuilder[] getCordenadas() {
      return coordenadas;
   }

   /**
    * @return Un array con dos enteros que representan las coordenadas x e y de la
    *         cabeza de la serpiente en el tablero.
    */
   public int[] getCordsCabeza() {
      return coordsCabeza;
   }

   /**
    * @return Un array con dos enteros que representan las coordenadas x e y de la
    *         cola de la serpiente en el tablero.
    */
   public int[] getCordsCola() {
      return coordsCola;
   }

   /**
    * Establece las cordenadas de la cabeza de la serpiente.
    * 
    * @param primeraCords La primera cordenada de la cabeza de la serpiente.
    * @param segundaCords La segunda cordenada de la cabeza de la serpiente.
    */
   public void setCordsCabeza(int primeraCords, int segundaCords) {
      this.coordsCabeza[0] = primeraCords;
      this.coordsCabeza[1] = segundaCords;
   }

   /**
    * Establece las cordenadas de la cabeza de la serpiente.
    * 
    * @param cordsCabeza Un array de dos enteros que representa las nuevas
    *                    coordenadas x e y de la cabeza de la serpiente en el
    *                    tablero.
    */
   public void setCordsCabeza(int[] cordsCabeza) {
      this.coordsCabeza = cordsCabeza;
   }

   /**
    * Establece las cordenadas de la cola de la serpiente.
    * 
    * @param primeraCords La primera cordenada de la cola de la serpiente.
    * @param segundaCords La segunda cordenada de la cola de la serpiente.
    */
   public void setCordsCola(int primeraCords, int segundaCords) {
      this.coordsCola[0] = primeraCords;
      this.coordsCola[1] = segundaCords;
   }

   /**
    * Establece las coordenadas de la cola de la serpiente.
    * 
    * @param cordsCola Un array de dos enteros que representa las nuevas
    *                  coordenadas x e y de la cola de la serpiente en el tablero.
    */

   public void setCordsCola(int[] cordsCola) {
      this.coordsCola = cordsCola;
   }

   /* ----- Metodos abstractos ----- */
   public abstract double iniciarJuego(int[] configuracionSnake, Partida partida) throws IOException, InterruptedException;

   public abstract void eliminarCola(String movs);

   /* ----- Metodos ----- */

   /**
    * Reemplaza una serie de caracteres en una fila del tablero por un nuevo
    * caracter.
    * 
    * @param fila          La fila del tablero en la que se va a reemplazar.
    * @param inicioColumna La columna inicial de la seccion que se va a
    *                      reemplazar.
    * @param finalColumna  La columna final de la seccion que se va a
    *                      reemplazar.
    * @param nuevoCaracter El nuevo caracter que se va a reemplazar en la seccion
    *                      del tablero.
    */
   public void reemplazarCasilla(int fila, int inicioColumna, int finalColumna, String nuevoCaracter) {
      coordenadas[fila].replace(inicioColumna, finalColumna, nuevoCaracter);
   }

   /**
    * Inicializa el tablero del juego con las dimensiones indicadas.
    * 
    * @param dimensiones Son las dimensiones que debe tener el tablero
    */
   public void inicializarTablero(int[] dimensiones) {
      coordsCola = new int[] { 1, 0 };
      coordsCabeza = new int[] { 3, 0 };
      coordenadas = new StringBuilder[dimensiones[0]];

      // Creacion del mapa segun las variables de dimensiones
      for (int filas = 0; filas < dimensiones[0]; filas++) {
         // Como comienza con el valor "null" tengo que crearlo vacio
         coordenadas[filas] = new StringBuilder("");
         for (int columnas = 0; columnas < dimensiones[1]; columnas++) {
            coordenadas[filas].append("0");
         }
      }
      // Remplazo de la primera linAea las 3 primeras letras para hacer la serpiente
      coordenadas[0].replace(0, 3, "111");
   }

   /**
    * Genera una fruta en el tablero. La fruta se coloca en una posicion aleatoria
    * en el tablero, y se evita que se coloque en una posicion en la que ya este
    * la serpiente.
    * 
    * @param dimensiones Un array que contiene las dimensiones del tablero.
    */
   public void generarFruta(int[] dimensiones) {
      int[] cordsComida = { 0, 0 };

      cordsComida[0] = (int) (Math.random() * (dimensiones[0] - 1));
      cordsComida[1] = (int) (Math.random() * (dimensiones[1] - 1));

      Character comprobarPosicionTablero = coordenadas[cordsComida[0]].charAt(cordsComida[1]);
      while (comprobarPosicionTablero.equals('1')) {
         if (cordsComida[1] == coordenadas[0].length() - 1) {
            cordsComida[1] = 0;
            if (cordsComida[0] == coordenadas.length - 1) {
               cordsComida[0] = 0;
            } else {
               cordsComida[0] += 1;
            }
         } else {
            cordsComida[1] += 1;
         }
         comprobarPosicionTablero = coordenadas[cordsComida[0]].charAt(cordsComida[1]);
      }
      coordenadas[cordsComida[0]].replace(cordsComida[1], cordsComida[1] + 1, "2");
   }

   /**
    * Genera una fila del tablero para mostrar por pantalla.
    * 
    * @param numFila       El numero de la fila que se va a generar.
    * @param admiteColores Un boolean que indica si la consola admite colores.
    * @return La fila generada como un String.
    */
   public String generarFila(int numFila, int admiteColores) {
      // Utilizo un StringBuilder para trabajar mejor con la frase
      StringBuilder fila = new StringBuilder();
      final String[][] FRUTA = {
            { "@", "l" },
            { ColoresConsola.ANSI_RED() + "@" + ColoresConsola.ANSI_RESET(),
                  ColoresConsola.ANSI_YELLOW() + "l" + ColoresConsola.ANSI_RESET() },
      };

      final String[][] SNAKE = {
            { "*", "#", "O" },
            { ColoresConsola.ANSI_RGB(116, 198, 157) + "*" + ColoresConsola.ANSI_RESET(),
                  ColoresConsola.ANSI_GREEN() + "#" + ColoresConsola.ANSI_RESET(),
                  ColoresConsola.ANSI_RGB(45, 106, 79) + "O" + ColoresConsola.ANSI_RESET() },

      };

      // Borde izquierdo
      fila.append("=");

      for (int columnas = 0; columnas < coordenadas[numFila].length(); columnas++) {

         Character character = coordenadas[numFila].charAt(columnas);
         switch (character) {
            case '1':
               // Seleciono el simbolo correspondiente: cabeza (2), cola (0) o cuerpo (1)
               int parteSnake = (numFila == coordsCabeza[1] && columnas == coordsCabeza[0] - 1) ? 2
                     : (numFila == coordsCola[1] && columnas == coordsCola[0] - 1) ? 0 : 1;
               fila.append(SNAKE[admiteColores][parteSnake]);
               break;

            case '2':
               fila.append(FRUTA[admiteColores][0]);
               break;

            case '0':
               fila.append(" ");
               break;
            default:
               break;
         }
      }
      // Borde derecho
      fila.append("=");

      return fila.toString();
   }

   /**
    * Muestra el tablero del juego en la consola. La funcion genera
    * cada fila del tablero y la imprime en la consola.
    * 
    * @param admiteColores Es un boolean que indica si la consola admite colores
    */
   public void mostrarTablero(int admiteColores) {
      for (int fila = 0; fila < coordenadas.length; fila++) {
         System.out.println(generarFila(fila, admiteColores));
      }
   }

   /**
    * Detecta si la serpiente ha comido una fruta. Segun la direccion en la que
    * se esta moviendo la serpiente, se evalua la casilla que esta en la pared
    * opuesta
    * para ver si es una fruta. Si es una fruta se marca que ha comido y se
    * incrementa la longitud de la serpiente en 1.
    * 
    * @param direcion La direccion en la que se esta moviendo la serpiente
    * @return Un boolean que indica si la serpiente ha comido una fruta
    */
   public boolean detectarFruta(String direcion) {
      boolean frutaDetectada = false;
      switch (direcion) {
         case "W":
            if ('2' == coordenadas[coordsCabeza[1] - 1].charAt(coordsCabeza[0] - 1)) {
               frutaDetectada = true;
            }
            break;

         case "A":
            if ('2' == coordenadas[coordsCabeza[1]].charAt(coordsCabeza[0] - 2)) {
               frutaDetectada = true;
            }
            break;

         case "S":
            if ('2' == coordenadas[coordsCabeza[1] + 1].charAt(coordsCabeza[0] - 1)) {
               frutaDetectada = true;
            }
            break;

         case "D":
            if ('2' == coordenadas[coordsCabeza[1]].charAt(coordsCabeza[0])) {
               frutaDetectada = true;
            }
            break;
         default:
            break;
      }
      return frutaDetectada;
   }

   /**
    * Crea la cabeza de la serpiente en el tablero según la dirección de
    * movimiento.
    * Actualiza la posición de la cabeza y verifica si la serpiente ha colisionado
    * consigo misma.
    *
    * @param alive    Un booleano que indica si la serpiente está viva antes del
    *                 movimiento.
    * @param direcion La dirección en la que se está moviendo la serpiente: "W",
    *                 "A", "S", o "D".
    * @return Un booleano que indica si la serpiente sigue viva después del
    *         movimiento.
    */

   public boolean crearCabeza(boolean alive, String direcion) {
      switch (direcion) {
         case "W":
            if ('1' == coordenadas[coordsCabeza[1] - 1].charAt(coordsCabeza[0] - 1))
               alive = false;

            /* ----- Parte de la cabeza ----- */
            coordenadas[coordsCabeza[1] - 1].replace(coordsCabeza[0] - 1, coordsCabeza[0], "1");

            coordsCabeza[1] -= 1;
            break;

         case "A":
            if ('1' == coordenadas[coordsCabeza[1]].charAt(coordsCabeza[0] - 2))
               alive = false;

            /* ----- Parte de la cabeza ----- */
            coordenadas[coordsCabeza[1]].replace(coordsCabeza[0] - 2, coordsCabeza[0] - 1, "1");

            coordsCabeza[0] -= 1;
            break;

         case "S":
            if ('1' == coordenadas[coordsCabeza[1] + 1].charAt(coordsCabeza[0] - 1))
               alive = false;

            /* ----- Parte de la cabeza ----- */
            coordenadas[coordsCabeza[1] + 1].replace(coordsCabeza[0] - 1, coordsCabeza[0], "1");

            coordsCabeza[1] += 1;
            break;

         case "D":
            if ('1' == coordenadas[coordsCabeza[1]].charAt(coordsCabeza[0]))
               alive = false;

            /* ----- Parte de la cabeza ----- */
            coordenadas[coordsCabeza[1]].replace(coordsCabeza[0], coordsCabeza[0] + 1, "1");
            coordsCabeza[0] += 1;
            break;

         default:
            break;
      }
      return alive;
   }

   /**
    * 
    * @param longitudSerpiente La longitud de la serpiente en cuadros. Este valor
    *                          representa la cantidad de casillas ocupadas por la
    *                          serpiente en el tablero.
    * @param filasTablero      La cantidad de filas del tablero del juego.
    * @param columnasTablero   La cantidad de columnas del tablero del juego.
    * @param velocidadLa       velocidad de actualización del juego en
    *                          milisegundos.
    * 
    * @return El puntaje calculado en base a la longitud de la serpiente, las
    *         dimensiones del tablero y la velocidad del juego.
    * 
    */
   public static double calcularPuntaje(int longitudSerpiente, int filasTablero, int columnasTablero, int velocidad) {
      // Normalizar las dimensiones del tablero: cuantas más celdas tenga el tablero,
      // más puntos se pueden ganar
      int totalCeldas = filasTablero * columnasTablero;

      // Ajustar el puntaje según la velocidad (la velocidad más baja tiene un puntaje
      // más alto)
      double velocidadFactor = 1000.0 / velocidad; // Hacer que la velocidad más alta sea más baja en el cálculo

      // Calcular el puntaje final con un ponderado de cada aspecto
      double puntaje = (longitudSerpiente * velocidadFactor * ((double) ((longitudSerpiente * 100) / totalCeldas)));
      System.out.println("El puntaje final es de " + puntaje);

      return puntaje;
   }

   /**
    * Muestra 30 lineas vacias para separar de la otra informacion
    * Tambien intenta borrar la pantalla con un comando ANSI, pero no funciona en
    * todas las consolas
    */
   public static void separacion() {
      for (int i = 0; i < 30; i++) {
         System.out.println("\n");
      }
      // Esto hace que se borre, pero no funciona en todas las consolas
      // System.out.print("\033[H\033[2J");
      // System.out.flush();
   }
}
