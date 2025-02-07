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
   public StringBuilder[] getCordenadas() {
      return cordenadas;
   }

   public int getSnakeLongitud() {
      return snakeLongitud;
   }

   public int[] getCordsCabeza() {
      return cordsCabeza;
   }

   public int[] getCordsCola() {
      return cordsCola;
   }

   public String getGuardarDireccion() {
      return guardarDireccion;
   }

   public String getDirecion() {
      return direcion;
   }

   public String getMovs() {
      return movs;
   }

   public boolean isAlive() {
      return alive;
   }

   public boolean isHaComido() {
      return haComido;
   }

   public boolean isWin() {
      return win;
   }

   public void setHaComido(boolean haComido) {
      JuegoBase.haComido = haComido;
   }

   public void setGuardarDireccion(String guardarDireccion) {
      JuegoBase.guardarDireccion = guardarDireccion;
   }

   public void setDirecion(String direcion) {
      JuegoBase.direcion = direcion;
   }

   public void setWin(boolean win) {
      JuegoBase.win = win;
   }

   public void setAlive(boolean alive) {
      JuegoBase.alive = alive;
   }

   public void setSnakeLongitud(int snakeLongitud) {
      JuegoBase.snakeLongitud = snakeLongitud;
   }

   public void setMovs(String movs) {
      JuegoBase.movs = movs;
   }

   public static void setCordsCabeza(int primeraCords, int segundaCords) {
      JuegoBase.cordsCabeza[0] = primeraCords;
      JuegoBase.cordsCabeza[1] = segundaCords;
   }

   public static void setCordsCola(int primeraCords, int segundaCords) {
      JuegoBase.cordsCola[0] = primeraCords;
      JuegoBase.cordsCola[1] = segundaCords;
   }

   /* ----- Metodos abstractos ----- */
   public abstract double iniciarJuego(int[] configuracionSnake) throws IOException, InterruptedException;
   public abstract void crearCabeza();
   public abstract void eliminarCola();
   /* ----- Metodos ----- */
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
