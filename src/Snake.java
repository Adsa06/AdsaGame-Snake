package src;

import java.io.*;

public class Snake {
    public static void main(int[] dimensiones) throws IOException, InterruptedException {
        
        /* ----- Parte declarativa ----- */
        //Creo una variable BufferedReader para leer datos y hago que sea un objeto de la clase FileReader para leer el archivo
        BufferedReader br = new BufferedReader(new FileReader("./content.txt"));
        String guardarDireccion;
        //El snake y si esta vivo
        boolean alive = true;
        String[] snake = { ColoresConsola.ANSI_GREEN() + "#" + ColoresConsola.ANSI_RESET()};
        
        //longitud de la serpiente
        int snakeLongitud = 3;

        //Literalmente el mapa
        String[] cordenadas = new String[dimensiones[0]];

        //Cordenadas de la cabeza y de la cola
        //Primera cordenada la "x", posicion de una frase (empieza en 1) 
        //la segunda cordenada es una "y" la que indica que frase es (empieze en 0)
        int[] cordsCabeza = {3,0};
        int[] cordsCola = {1,0};
        String movs = "DD"; //Se cuencia de movimientos para saber la continuacion de la cola

        //Direccion
        String direcion = "D";

        //La sub cadenas para que se mueva
        String subCadenaCabeza1;
        String subCadenaCabeza2;
        String subCadenaCabeza3;
        String subCadenaCola1;
        String subCadenaCola2;
        String subCadenaCola3;


        /* ----- Parte principal ----- */

        //Creacion del mapa segun las variables de dimensiones
        for (int i = 0; i < dimensiones[0]; i++) {
            cordenadas[i] = "";
            for(int j = 0; j < dimensiones[1]; j++) {
                cordenadas[i] = cordenadas[i] + "0";
            }
        }
        //Aqui creo la serpiente arriba izquierda en el mapa
        cordenadas[0] = (cordenadas[0].concat("111") + cordenadas[0].concat(cordenadas[0].substring(0, dimensiones[1] - 3))).substring(dimensiones[1], dimensiones[1] * 2);
        
        while (alive) {
            
            //" bucles for uno dentro de otro para que recorra el mapa de las cordenadas 1 por 1"
            for (int filas = 0; filas < cordenadas.length; filas++) {
                System.out.printf("=");
                for (int columnas = 0; columnas < cordenadas[filas].length(); columnas++) {
                    
                    Character character = cordenadas[filas].charAt(columnas);
                   
                    System.out.printf("%s", character.equals('0') ? " " : snake[0]);

                }
                System.out.printf("=");
                System.out.printf("%n");
            }
            
            //Esto se tendra que hacer despues para que un espacio en blanco no de fallo
            guardarDireccion = br.readLine();
            direcion = guardarDireccion != null ? ((guardarDireccion.equals("W") || guardarDireccion.equals("A") || guardarDireccion.equals("S") || guardarDireccion.equals("D")) ? guardarDireccion : direcion) : direcion;

            //Detecta si es un movimiento valido con una condicion ternaria y guarda el movimiento
            movs = movs.concat(direcion);

            /*
             * 
             *  Switch para crear la cabeza
             * 
             */
            switch (direcion) {
                case "W":
                    /* ----- Parte de la cabeza ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cabeza mas adelaneta gracias a la posicion de la cadena
                    subCadenaCabeza1 = cordenadas[cordsCabeza[1]-1].substring(0,cordsCabeza[0]-1);
                    subCadenaCabeza2 = cordenadas[cordsCabeza[1]-1].substring(cordsCabeza[0]-1, cordsCabeza[0]);

                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCabeza2 = subCadenaCabeza2.replace("0","1");

                    subCadenaCabeza3 = cordenadas[cordsCabeza[1]-1].substring(cordsCabeza[0]);
                    //Renuevo la cadena utilizando la particion
                    cordenadas[cordsCabeza[1]-1] = subCadenaCabeza1 + subCadenaCabeza2 + subCadenaCabeza3;

                    cordsCabeza[1] -= 1;
                    break;

                case "A":
                    /* ----- Parte de la cabeza ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cabeza mas adelaneta gracias a la posicion de la cadena
                    subCadenaCabeza1 = cordenadas[cordsCabeza[1]].substring(0, cordsCabeza[0]-2);
                    subCadenaCabeza2 = cordenadas[cordsCabeza[1]].substring(cordsCabeza[0]-2, cordsCabeza[0]-1);
                
                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCabeza2 = subCadenaCabeza2.replace("0","1");
                                
                    subCadenaCabeza3 = cordenadas[cordsCabeza[1]].substring(cordsCabeza[0]-1);
                    //Renuevo la cadena utilizando la particion
                    cordenadas[cordsCabeza[1]] = subCadenaCabeza1 + subCadenaCabeza2 + subCadenaCabeza3;
                                
                    cordsCabeza[0] -= 1;
                    break;

                case "S":
                    /* ----- Parte de la cabeza ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cabeza mas adelaneta gracias a la posicion de la cadena
                    subCadenaCabeza1 = cordenadas[cordsCabeza[1]+1].substring(0, cordsCabeza[0]-1);
                    subCadenaCabeza2 = cordenadas[cordsCabeza[1]+1].substring(cordsCabeza[0]-1, cordsCabeza[0]);
                
                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCabeza2 = subCadenaCabeza2.replace("0","1");

                    subCadenaCabeza3 = cordenadas[cordsCabeza[1]+1].substring(cordsCabeza[0]);
                    //Renuevo la cadena utilizando la particion
                    cordenadas[cordsCabeza[1]+1] = subCadenaCabeza1 + subCadenaCabeza2 + subCadenaCabeza3;

                    cordsCabeza[1] += 1;
                    break;

                case "D":

                    /* ----- Parte de la cabeza ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cabeza mas adelaneta gracias a la posicion de la cadena
                    subCadenaCabeza1 = cordenadas[cordsCabeza[1]].substring(0, cordsCabeza[0]);
                    subCadenaCabeza2 = cordenadas[cordsCabeza[1]].substring(cordsCabeza[0], cordsCabeza[0]+1);

                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCabeza2 = subCadenaCabeza2.replace("0","1");

                    subCadenaCabeza3 = cordenadas[cordsCabeza[1]].substring(cordsCabeza[0]+1);
                    //Renuevo la cadena utilizando la particion
                    cordenadas[cordsCabeza[1]] = subCadenaCabeza1 + subCadenaCabeza2 + subCadenaCabeza3;
                    
                    //Y le sumo 1 a la posicion 1 para q se actualiza
                    cordsCabeza[0] += 1;
                    break;
            
                default:
                    break;
            }

            /*
             * 
             *  Switch para eliminar la cola
             * 
             */
            switch (movs.charAt(0)) {
                case 'W':
                    /* ----- Parte de la cola ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cola mas adelanete
                    subCadenaCola1 = cordenadas[cordsCola[1]].substring(0,cordsCola[0]-1);
                    subCadenaCola2 = cordenadas[cordsCola[1]].substring(cordsCola[0]-1, cordsCola[0]);

                    subCadenaCola2 = subCadenaCola2.replace("1","0");

                    subCadenaCola3 = cordenadas[cordsCola[1]].substring(cordsCola[0]);
                    //Renuevo la cadena utilizando la particion
                    cordenadas[cordsCola[1]] = subCadenaCola1 + subCadenaCola2 + subCadenaCola3;

                    cordsCola[1] -= 1;
                    break;

                case 'A':
                    /* ----- Parte de la cola ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cola mas adelanete
                    subCadenaCola1 = cordenadas[cordsCola[1]].substring(0,cordsCola[0]-1);
                    subCadenaCola2 = cordenadas[cordsCola[1]].substring(cordsCola[0]-1, cordsCola[0]);

                    subCadenaCola2 = subCadenaCola2.replace("1","0");

                    subCadenaCola3 = cordenadas[cordsCola[1]].substring(cordsCola[0]);
                    //Renuevo la cadena utilizando la particion
                    cordenadas[cordsCola[1]] = subCadenaCola1 + subCadenaCola2 + subCadenaCola3;


                    cordsCola[0] -= 1;
                    break;

                case 'S':
                    /* ----- Parte de la cola ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cola mas adelanete
                    subCadenaCola1 = cordenadas[cordsCola[1]].substring(0, cordsCola[0]-1);
                    subCadenaCola2 = cordenadas[cordsCola[1]].substring(cordsCola[0]-1, cordsCola[0]);

                    subCadenaCola2 = subCadenaCola2.replace("1","0");

                    subCadenaCola3 = cordenadas[cordsCola[1]].substring(cordsCola[0]);
                    //Renuevo la cadena utilizando la particion
                    cordenadas[cordsCola[1]] = subCadenaCola1 + subCadenaCola2 + subCadenaCola3;
                    
                    
                    cordsCola[1] += 1;
                    break;

                case 'D':
                    /* ----- Parte de la cola ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cola mas adelanete
                    subCadenaCola1 = cordenadas[cordsCola[1]].substring(0,cordsCola[0]-1);
                    subCadenaCola2 = cordenadas[cordsCola[1]].substring(cordsCola[0]-1, cordsCola[0]);

                    subCadenaCola2 = subCadenaCola2.replace("1","0");

                    subCadenaCola3 = cordenadas[cordsCola[1]].substring(cordsCola[0]);
                    //Renuevo la cadena utilizando la particion
                    cordenadas[cordsCola[1]] = subCadenaCola1 + subCadenaCola2 + subCadenaCola3;

                    //Y le sumo 1 a la posicion 1 para q se actualiza
                    cordsCola[0] += 1;
                    break;

                default:
                    break;
            }
            
            //Elimina el primer movimiento ya que deberia ya haberse ejecutado
            movs = movs.substring(1);

            //Tiempo de espera con hilos
            Thread.sleep(2000);
            separacion();
        }
    }
    public static void separacion() {
        for(int i = 0; i < 30; i++) {
            System.out.println("\n");
        }
    }
    public static void sueloTecho(int longitud) {
        System.out.printf("%n");
        for(int i = longitud; i > 0; i--) {
            System.out.print("-");
        }
        System.out.printf("%n");
    }
}
