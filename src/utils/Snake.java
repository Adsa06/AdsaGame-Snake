/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 17/10/2024
 * Este archivo es el juego principal, que se ejecuta cuando se llama desde Main.java
 * 
 */
package utils;

import java.io.*;

public class Snake {

    public static void separacion() {
        for(int i = 0; i < 30; i++) {
            System.out.println("\n");
        }
        //Esto hace que se borre, pero no funciona en todas las consolas
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
    }

    /**
     * 
     * @param tablero Es la variable que genera el tablero
     * @param dimensiones Son las dimensiones que debe tener el tablero
     */
    public static void inicializarTablero(StringBuilder[] tablero, int[] dimensiones) {
        
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
    }

    /**
     * 
     * @param tablero Es la variable que genera el tablero
     * @param dimensiones Son las dimensiones que debe tener el tablero
     */
    public static void generarFruta(StringBuilder[] tablero, int[] dimensiones) {
        int[] cordsComida =  {0,0};

        cordsComida[0] = (int) (Math.random() * (dimensiones[0]-1));
        cordsComida[1] = (int) (Math.random() * (dimensiones[1]-1));
        
        Character comprobarPosicionTablero = tablero[cordsComida[0]].charAt(cordsComida[1]);
        while (comprobarPosicionTablero.equals('1')) {
            if(cordsComida[1] == tablero[0].length()-1) {
                cordsComida[1] = 0;
                if(cordsComida[0] == tablero.length-1) {
                    cordsComida[0] = 0;
                } else {
                    cordsComida[0] += 1;
                }
            } else {
                cordsComida[1] += 1;
            }
            comprobarPosicionTablero = tablero[cordsComida[0]].charAt(cordsComida[1]);
        }
        tablero[cordsComida[0]].replace(cordsComida[1], cordsComida[1]+1, "2");

    }

    /**
     * 
     * @param tablero Es la variable en la que genera la serpiente
     * @param cordsCabeza Son las cordenadas de la cabeza de la serpiente
     * @param cordsCola Son las cordenadas de la cola de la serpiente
     * @param admiteColores Es la variable que almacena si admite o no colores
     */
    public static void mostrarTablero(StringBuilder[] tablero, int[] cordsCabeza, int[] cordsCola, int admiteColores) {
        final String[][] FRUTA = {
            {"@", "l"},
            {ColoresConsola.ANSI_RED() + "@" + ColoresConsola.ANSI_RESET(), ColoresConsola.ANSI_YELLOW() + "l" + ColoresConsola.ANSI_RESET()},
        };

        final String[][] SNAKE = {
            {"*", "#", "O"},
            {ColoresConsola.ANSI_RGB(116, 198, 157) + "*" + ColoresConsola.ANSI_RESET(), ColoresConsola.ANSI_GREEN() + "#" + ColoresConsola.ANSI_RESET(), ColoresConsola.ANSI_RGB(45, 106, 79) + "O" + ColoresConsola.ANSI_RESET()},
        
        };

        //Bucles for uno dentro de otro para que recorra el mapa de las cordenadas 1 por 1
        for (int filas = 0; filas < tablero.length; filas++) {
            System.out.printf("=");
            for (int columnas = 0; columnas < tablero[filas].length(); columnas++) {
                
                Character character = tablero[filas].charAt(columnas);
                switch (character) {
                    case '1':
                        System.out.printf("%s", SNAKE[admiteColores][filas == cordsCabeza[1] && columnas == cordsCabeza[0]-1 ? 2 : filas == cordsCola[1] && columnas == cordsCola[0]-1 ? 0 : 1]);
                        break;

                    case '2':
                        System.out.printf("%s",FRUTA[admiteColores][0]);
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
     * 
     * @param tablero Es la variable en la que elimina la cola
     * @param cordsCola Es la posicion de la cola actual
     * @param movs Es la lista de movimientos
     */
    public static void eliminarCola(StringBuilder[] tablero, int[] cordsCola, String movs) {
        //Elimino la cola
        tablero[cordsCola[1]].replace(cordsCola[0]-1, cordsCola[0], "0");
        
        //Puedo hacer con 2 dobles operadortes ternarios, pero creo que me decanto mas por el switch, q es mas facil de ver
        /*
        cordsCola[0] += (movs.charAt(0) == 'A' ? -1 : (movs.charAt(0) == 'D' ? 1 : 0));
        cordsCola[1] += (movs.charAt(0) == 'W' ? -1 : (movs.charAt(0) == 'S' ? 1 : 0));
        */
    
        switch (movs.charAt(0)) {
            case 'W':
                cordsCola[1] -= 1;
                break;
        
            case 'A':
                cordsCola[0] -= 1;
                break;
        
            case 'S':
                cordsCola[1] += 1;
                break;
        
            case 'D':
                cordsCola[0] += 1;
                break;
        
            default:
                break;
        }
    }

    /**
     * 
     * @param tablero Es la variable en la que genera la cabeza
     * @param cordsCabeza Es la posicion de la cabeza
     * @param direcion Es la direccion de la cabeza
     * @return Devuelve un booleano que dice si esta vivo o muerto
     */
    public static boolean crearCabeza(StringBuilder[] tablero, int[] cordsCabeza, String direcion) {
        /*
         * 
         *  Switch para crear la cabeza
         *  En este Switch lo que hago es detectar hacia donde va la cabeza y remplazo lo que haya por la cabeza y luego actualizo la posicion de ella
         *  Con el if detecta si la posicion a la que quiere haceder esta el cuerpo de la serpiente, si es asi muere
         */
        boolean alive = true;
        switch (direcion) {
            case "W":
                if('1' == tablero[cordsCabeza[1]-1].charAt(cordsCabeza[0]-1)) alive = false;

                /* ----- Parte de la cabeza ----- */
                tablero[cordsCabeza[1]-1].replace(cordsCabeza[0]-1, cordsCabeza[0], "1");

                cordsCabeza[1] -= 1;
                break;
        
            case "A":
                if('1' == tablero[cordsCabeza[1]].charAt(cordsCabeza[0]-2)) alive = false;

                /* ----- Parte de la cabeza ----- */
                tablero[cordsCabeza[1]].replace(cordsCabeza[0]-2, cordsCabeza[0]-1, "1");

                cordsCabeza[0] -= 1;
                break;
        
            case "S":
                if('1' == tablero[cordsCabeza[1]+1].charAt(cordsCabeza[0]-1)) alive = false;

                /* ----- Parte de la cabeza ----- */
                tablero[cordsCabeza[1]+1].replace(cordsCabeza[0]-1, cordsCabeza[0], "1");
        
                cordsCabeza[1] += 1;
                break;
        
            case "D":
                if('1' == tablero[cordsCabeza[1]].charAt(cordsCabeza[0])) alive = false;
                
                /* ----- Parte de la cabeza ----- */
                tablero[cordsCabeza[1]].replace(cordsCabeza[0], cordsCabeza[0]+1, "1");
                cordsCabeza[0] += 1;
                break;
        
            default:
                break;
        }

        return alive;
    }

    public static void juegoPrincipal(int[] configuracionSnake) throws IOException, InterruptedException {
        /* ----- Parte declarativa ----- */
        //Desempaqueto el array de configuracion
        final int[] DIMENSIONES = {configuracionSnake[0], configuracionSnake[1]};
        final int TIEMPOMILISEGUNDOS = configuracionSnake[2];
        final int ADMITECOLORES = configuracionSnake[3];

        //Creo una variable BufferedReader para leer datos y hago que sea un objeto de la clase FileReader para leer el archivo
        BufferedReader fr = new BufferedReader(new FileReader("./content.txt"));
        String guardarDireccion;
        //El snake y si esta vivo
        boolean alive = true;

        
        boolean haComido = true;


        //longitud de la serpiente
        int snakeLongitud = 3; //de momento inserbible
        boolean win = false;

        //Literalmente el mapa
        //String[] cordenadas = new String[dimensiones[0]];
        //Tengo que actualizar el programa para sustituir las cordenadas anteriores por esta, cambios en el switch
        StringBuilder[] cordenadas = new StringBuilder[DIMENSIONES[0]];

        //Cordenadas de la cabeza y de la cola
        //Primera cordenada la "x", posicion de una frase (empieza en 1) 
        //la segunda cordenada es una "y" la que indica que frase es (empieze en 0)
        int[] cordsCabeza = {3,0};
        int[] cordsCola = {1,0};
        String movs = "DD"; //Secuencia de movimientos para saber la continuacion de la cola

        //Direccion
        String direcion = "D";

        /* ----- Parte principal ----- */
        
        // Gracias a que los arrays se pasan por referencia no tengo que igualarlo, simplemente poner la funcion
        inicializarTablero(cordenadas, DIMENSIONES);

        do {
            //Generador de la manzana el cual lo genera en un lugar aleatorio y dospues lo mueve a la derecha si lo necesita
            if(haComido) {
                generarFruta(cordenadas, DIMENSIONES);
                haComido = false;
            }

            // Funcione que muestra el tablero
            mostrarTablero(cordenadas, cordsCabeza, cordsCola, ADMITECOLORES);

            //Tiempo de espera con hilos
            Thread.sleep(TIEMPOMILISEGUNDOS);
            separacion();

            //Esto se tendra que hacer despues para que un espacio en blanco no de fallo
            guardarDireccion = fr.readLine();
            //1 condicion ternarias para validar si no es nulo y si es W, A, S o D
            direcion = ((guardarDireccion != null && !guardarDireccion.equals("")) && ("WASD".contains(guardarDireccion) || "wasd".contains(guardarDireccion))) ? guardarDireccion : direcion;
            direcion = direcion.toUpperCase();
            //Detecta si es un movimiento valido con una condicion ternaria y guarda el movimiento para crear la cola

            try { //Este try lo que esta haciendo es para que en el switch de la cabeza me pille el error de que se ha salido del array
                
                
                switch (direcion) {
                    case "W":
                        if('2' == cordenadas[cordsCabeza[1]-1].charAt(cordsCabeza[0]-1)) {
                            haComido = true;
                            snakeLongitud++;
                        }
                        break;
                
                    case "A":
                        if('2' == cordenadas[cordsCabeza[1]].charAt(cordsCabeza[0]-2)) {
                            haComido = true;
                            snakeLongitud++;
                        }
                        break;
                
                    case "S":
                        if('2' == cordenadas[cordsCabeza[1]+1].charAt(cordsCabeza[0]-1)) {
                            haComido = true;
                            snakeLongitud++;
                        }
                        break;
                
                    case "D":
                        if('2' == cordenadas[cordsCabeza[1]].charAt(cordsCabeza[0])) {
                            haComido = true;
                            snakeLongitud++;
                        }
                        break;
                    default:
                        break;
                }


                /*
                 * 
                 *  Parte para la eliminacion y actualizacion de la cola
                 * 
                 */
                movs = movs.concat(direcion);
                if(!haComido){

                    eliminarCola(cordenadas, cordsCola, movs);

                    //Elimina el primer movimiento ya que deberia ya haberse ejecutado
                    movs = movs.substring(1);
                }    

                alive = crearCabeza(cordenadas, cordsCabeza, direcion);

            } catch (StringIndexOutOfBoundsException e) {// Aqui capta el error de que el snake se ha salido de la pantalla, por lo tanto pasa de vivo a muerto 
                alive = false;
            } catch (Exception e) { //Por si acaso que no me fio xD
                alive = false;
            }

            if(snakeLongitud == DIMENSIONES[0]*DIMENSIONES[1]) win = true;

        } while (alive && !win);

        System.out.println(alive ? "Enhorabuena, has ganado" : "Has perdido");
        fr.close();
    }
}