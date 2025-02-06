package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public abstract class JuegoBase {

   // private BufferedReader fr = new BufferedReader(new FileReader("./content.txt"));
   private String guardarDireccion;
   private boolean alive = true;
   private boolean haComido = true;
   private boolean win = false;
   private int snakeLongitud = 3;
   private StringBuilder[] cordenadas = new StringBuilder[0];

   private int[] cordsCabeza = {3,0};
   private int[] cordsCola = {1,0};
   private String movs = "DD"; //Secuencia de movimientos para saber la continuacion de la cola

   private String direcion = "D";

   /* ----- GETTERS Y SETTERS ----- */
   public StringBuilder[] getCordenadas() {
      return cordenadas;
   }

   /* ----- Metodos abstractos ----- */
   public abstract double iniciarJuego(int[] configuracionSnake) throws IOException, InterruptedException;

   /* ----- Metodos ----- */
   /**
    * 
    * @param tablero Es la variable que genera el tablero
    * @param dimensiones Son las dimensiones que debe tener el tablero
    */
    public static void inicializarTablero(StringBuilder[] tablero, int[] dimensiones) {
      tablero = new StringBuilder[dimensiones[0]];
      //Creacion del mapa segun las variables de dimensiones
      for (int filas = 0; filas < dimensiones[0]; filas++) {
          //Como comienza con el valor "null" tengo que crearlo vacio
          tablero[filas] = new StringBuilder("");
          for(int columnas = 0; columnas < dimensiones[1]; columnas++) {
              tablero[filas].append("0");
          }
      }
      //Remplazo de la primera linAea las 3 primeras letras para hacer la serpiente
      tablero[0].replace(0, 3, "111");
      System.out.println("Conseguido");
  }
   /**
    * Muestra 30 lineas vacias para separar de la otra informacion
    * Tambien intenta borrar la pantalla con un comando ANSI, pero no funciona en todas las consolas
    */
   public static void separacion() {
      for(int i = 0; i < 30; i++) {
          System.out.println("\n");
      }
      //Esto hace que se borre, pero no funciona en todas las consolas
      //System.out.print("\033[H\033[2J");
      //System.out.flush();
  }

   public static void iniciarJuego(Object configuracionSnake) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'iniciarJuego'");
   }
}
