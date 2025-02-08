package clases;

import java.io.IOException;

import utils.ColoresConsola;

public abstract class JuegoBase {
   private static String guardarDireccion;
   private static boolean alive = true;
   private static boolean haComido = true;
   private static boolean win = false;
   private static int snakeLongitud = 3;
   private static StringBuilder[] cordenadas;

   private static int[] cordsCabeza = { 3, 0 };
   private static int[] cordsCola = { 1, 0 };
   private static String movs = "DD"; // Secuencia de movimientos para saber la continuacion de la cola

   private static String direcion = "D";

   /* ----- GETTERS Y SETTERS ----- */

   /**
    * @return Un array de StringBuilder que representa el tablero del juego. Cada
    *         StringBuilder es una fila del tablero.
    */
   public StringBuilder[] getCordenadas() {
      return cordenadas;
   }

   /**
    * @return La longitud de la serpiente
    */
   public int getSnakeLongitud() {
      return snakeLongitud;
   }

   /**
    * @return Un array con dos enteros que representan las coordenadas x e y de la
    *         cabeza de la serpiente en el tablero.
    */
   public int[] getCordsCabeza() {
      return cordsCabeza;
   }

   /**
    * @return Un array con dos enteros que representan las coordenadas x e y de la
    *         cola de la serpiente en el tablero.
    */
   public int[] getCordsCola() {
      return cordsCola;
   }

   /**
    * @return La ultima direccion guardada por el usuario. Sirve para
    *         controlar la serpiente en el juego.
    */
   public String getGuardarDireccion() {
      return guardarDireccion;
   }

   /**
    * @return La ultima direccion guardada por el usuario. Sirve para
    *         controlar la serpiente en el juego.
    */
   public String getDirecion() {
      return direcion;
   }

   /**
    * @return La secuencia de movimientos que representa la continuación de la cola
    *         de la serpiente en el juego.
    */

   public String getMovs() {
      return movs;
   }

   /**
    * @return Un boolean que representa si el juego esta en curso o no. Si el
    *         jugador ha perdido, este valor sera false.
    */
   public boolean isAlive() {
      return alive;
   }

   /**
    * @return Un boolean que representa si la serpiente ha comido una fruta en el
    *         juego. Si el jugador ha comido una fruta, este valor sera true.
    */
   public boolean isHaComido() {
      return haComido;
   }

   /**
    * @return Un boolean que representa si el jugador ha ganado el juego o no.
    *         Si el jugador ha ganado, este valor sera true.
    */
   public boolean isWin() {
      return win;
   }

   /**
    * Establece si la serpiente ha comido una fruta en el juego o no.
    * 
    * @param haComido Un boolean que representa si la serpiente ha comido una
    *                 fruta.
    */
   public void setHaComido(boolean haComido) {
      JuegoBase.haComido = haComido;
   }

   /**
    * Establece la ultima direccion guardada por el usuario. Esta variable es
    * utilizada para controlar la serpiente en el juego.
    * 
    * @param guardarDireccion La ultima direccion guardada por el usuario.
    */
   public void setGuardarDireccion(String guardarDireccion) {
      JuegoBase.guardarDireccion = guardarDireccion;
   }

   /**
    * Establece la dirección actual de la serpiente en el juego.
    * 
    * @param direcion La nueva dirección de la serpiente. Debe ser uno de los
    *                 valores válidos que representan las direcciones del juego.
    */

   public void setDirecion(String direcion) {
      JuegoBase.direcion = direcion;
   }

   /**
    * Establece si el jugador ha ganado el juego o no.
    * 
    * @param win Un boolean que representa si el jugador ha ganado el juego.
    */
   public void setWin(boolean win) {
      JuegoBase.win = win;
   }

   /**
    * Establece si el jugador esta vivo o no.
    * 
    * @param alive Un boolean que representa si el jugador esta vivo o no.
    */
   public void setAlive(boolean alive) {
      JuegoBase.alive = alive;
   }

   /**
    * Establece la longitud de la serpiente en el juego.
    * 
    * @param snakeLongitud La nueva longitud de la serpiente.
    */

   public void setSnakeLongitud(int snakeLongitud) {
      JuegoBase.snakeLongitud = snakeLongitud;
   }

   /**
    * Establece la lista de movimientos en el juego. La lista de movimientos
    * es un String que representa los movimientos que se van a realizar en el
    * juego.
    * 
    * @param movs La lista de movimientos que se van a realizar en el juego.
    */
   public void setMovs(String movs) {
      JuegoBase.movs = movs;
   }

   /**
    * Establece las cordenadas de la cabeza de la serpiente.
    * 
    * @param primeraCords La primera cordenada de la cabeza de la serpiente.
    * @param segundaCords La segunda cordenada de la cabeza de la serpiente.
    */
   public static void setCordsCabeza(int primeraCords, int segundaCords) {
      JuegoBase.cordsCabeza[0] = primeraCords;
      JuegoBase.cordsCabeza[1] = segundaCords;
   }

   public static void setCordsCabeza(int[] cordsCabeza) {
      JuegoBase.cordsCabeza = cordsCabeza;
   }
   /**
    * Establece las cordenadas de la cola de la serpiente.
    * 
    * @param primeraCords La primera cordenada de la cola de la serpiente.
    * @param segundaCords La segunda cordenada de la cola de la serpiente.
    */
   public static void setCordsCola(int primeraCords, int segundaCords) {
      JuegoBase.cordsCola[0] = primeraCords;
      JuegoBase.cordsCola[1] = segundaCords;
   }

   public static void setCordsCola(int[] cordsCola) {
      JuegoBase.cordsCola = cordsCola;
   }
   /* ----- Metodos abstractos ----- */
   public abstract double iniciarJuego(int[] configuracionSnake) throws IOException, InterruptedException;

   public abstract void eliminarCola();

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
   public static void reemplazarCasilla(int fila, int inicioColumna, int finalColumna, String nuevoCaracter) {
      cordenadas[fila].replace(inicioColumna, finalColumna, nuevoCaracter);
   }

   /**
    * Reinicia las variables del juego a sus valores predeterminados.
    * Establece que el jugador esta vivo, que no ha comido, que no ha ganado,
    * la longitud de la serpiente en 3, los movimientos en "DD" y la
    * direccion en "D".
    */
   public static void resetearVariables() {
      alive = true;
      haComido = true;
      win = false;
      snakeLongitud = 3;
      movs = "DD";
      direcion = "D";
   }

   /**
    * 
    * @param tablero     Es la variable que genera el tablero
    * @param dimensiones Son las dimensiones que debe tener el tablero
    */
   public static void inicializarTablero(int[] dimensiones) {
      cordsCola = new int[] { 1, 0 };
      cordsCabeza = new int[] { 3, 0 };
      cordenadas = new StringBuilder[dimensiones[0]];

      // Creacion del mapa segun las variables de dimensiones
      for (int filas = 0; filas < dimensiones[0]; filas++) {
         // Como comienza con el valor "null" tengo que crearlo vacio
         cordenadas[filas] = new StringBuilder("");
         for (int columnas = 0; columnas < dimensiones[1]; columnas++) {
            cordenadas[filas].append("0");
         }
      }
      // Remplazo de la primera linAea las 3 primeras letras para hacer la serpiente
      cordenadas[0].replace(0, 3, "111");
   }

   /**
    * 
    * @param tablero     Es la variable que genera el tablero
    * @param dimensiones Son las dimensiones que debe tener el tablero
    */
   public static void generarFruta(int[] dimensiones) {
      int[] cordsComida = { 0, 0 };

      cordsComida[0] = (int) (Math.random() * (dimensiones[0] - 1));
      cordsComida[1] = (int) (Math.random() * (dimensiones[1] - 1));

      Character comprobarPosicionTablero = cordenadas[cordsComida[0]].charAt(cordsComida[1]);
      while (comprobarPosicionTablero.equals('1')) {
         if (cordsComida[1] == cordenadas[0].length() - 1) {
            cordsComida[1] = 0;
            if (cordsComida[0] == cordenadas.length - 1) {
               cordsComida[0] = 0;
            } else {
               cordsComida[0] += 1;
            }
         } else {
            cordsComida[1] += 1;
         }
         comprobarPosicionTablero = cordenadas[cordsComida[0]].charAt(cordsComida[1]);
      }
      cordenadas[cordsComida[0]].replace(cordsComida[1], cordsComida[1] + 1, "2");
   }

   /**
    * 
    * @param tablero       Es la variable en la que genera la serpiente
    * @param cordsCabeza   Son las cordenadas de la cabeza de la serpiente
    * @param cordsCola     Son las cordenadas de la cola de la serpiente
    * @param admiteColores Es la variable que almacena si admite o no colores
    */
   public static void mostrarTablero(int admiteColores) {
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

      // Bucles for uno dentro de otro para que recorra el mapa de las cordenadas 1
      // por 1
      for (int filas = 0; filas < cordenadas.length; filas++) {
         System.out.printf("=");
         for (int columnas = 0; columnas < cordenadas[filas].length(); columnas++) {

            Character character = cordenadas[filas].charAt(columnas);
            switch (character) {
               case '1':
                  System.out.printf("%s",
                        SNAKE[admiteColores][filas == cordsCabeza[1] && columnas == cordsCabeza[0] - 1 ? 2
                              : filas == cordsCola[1] && columnas == cordsCola[0] - 1 ? 0 : 1]);
                  break;

               case '2':
                  System.out.printf("%s", FRUTA[admiteColores][0]);
                  break;

               case '0':
                  System.out.printf("%s", " ");
                  break;
               default:
                  break;
            }
         }
         System.out.printf("=");
         System.out.printf("%n");
      }

   }

   /**
    * Detecta si la serpiente ha comido una fruta. Segun la direccion en la que
    * se esta moviendo la serpiente, se evalua la casilla que esta en frente
    * para ver si es una fruta. Si es una fruta se marca que ha comido y se
    * incrementa la longitud de la serpiente en 1.
    */
   public static void detectarFruta() {
      switch (direcion) {
         case "W":
            if ('2' == cordenadas[cordsCabeza[1] - 1].charAt(cordsCabeza[0] - 1)) {
               haComido = true;
               snakeLongitud++;
            }
            break;

         case "A":
            if ('2' == cordenadas[cordsCabeza[1]].charAt(cordsCabeza[0] - 2)) {
               haComido = true;
               snakeLongitud++;
            }
            break;

         case "S":
            if ('2' == cordenadas[cordsCabeza[1] + 1].charAt(cordsCabeza[0] - 1)) {
               haComido = true;
               snakeLongitud++;
            }
            break;

         case "D":
            if ('2' == cordenadas[cordsCabeza[1]].charAt(cordsCabeza[0])) {
               haComido = true;
               snakeLongitud++;
            }
            break;
         default:
            break;
      }
   }

   /**
    * 
    * Switch para crear la cabeza
    * En este Switch lo que hago es detectar hacia donde va la cabeza y remplazo lo
    * que haya por la cabeza y luego actualizo la posicion de ella
    * Con el if detecta si la posicion a la que quiere haceder esta el cuerpo de la
    * serpiente, si es asi muere
    */
   public static void crearCabeza() {
      switch (direcion) {
         case "W":
            if ('1' == cordenadas[cordsCabeza[1] - 1].charAt(cordsCabeza[0] - 1))
               alive = false;

            /* ----- Parte de la cabeza ----- */
            cordenadas[cordsCabeza[1] - 1].replace(cordsCabeza[0] - 1, cordsCabeza[0], "1");

            cordsCabeza[1] -= 1;
            break;

         case "A":
            if ('1' == cordenadas[cordsCabeza[1]].charAt(cordsCabeza[0] - 2))
               alive = false;

            /* ----- Parte de la cabeza ----- */
            cordenadas[cordsCabeza[1]].replace(cordsCabeza[0] - 2, cordsCabeza[0] - 1, "1");

            cordsCabeza[0] -= 1;
            break;

         case "S":
            if ('1' == cordenadas[cordsCabeza[1] + 1].charAt(cordsCabeza[0] - 1))
               alive = false;

            /* ----- Parte de la cabeza ----- */
            cordenadas[cordsCabeza[1] + 1].replace(cordsCabeza[0] - 1, cordsCabeza[0], "1");

            cordsCabeza[1] += 1;
            break;

         case "D":
            if ('1' == cordenadas[cordsCabeza[1]].charAt(cordsCabeza[0]))
               alive = false;

            /* ----- Parte de la cabeza ----- */
            cordenadas[cordsCabeza[1]].replace(cordsCabeza[0], cordsCabeza[0] + 1, "1");
            cordsCabeza[0] += 1;
            break;

         default:
            break;
      }

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
      System.out.println("El tablero tiene " + filasTablero + " filas y " + columnasTablero + " columnas.");
      int totalCeldas = filasTablero * columnasTablero;
      System.out.println("El tablero tiene " + totalCeldas + " celdas.");

      // Ajustar el puntaje según la velocidad (la velocidad más baja tiene un puntaje
      // más alto)
      System.out.println("La velocidad del juego es de " + velocidad + " milisegundos.");
      double velocidadFactor = 1.0 / (velocidad / 1000.0); // Hacer que la velocidad más alta sea más baja en el cálculo
      System.out.println("El factor de velocidad es de " + velocidadFactor);

      // Calcular el puntaje final con un ponderado de cada aspecto
      System.out.println("La longitud de la serpiente es de " + longitudSerpiente);
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
