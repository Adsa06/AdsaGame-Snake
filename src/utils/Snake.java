/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 17/10/2024
 * Este archivo es el juego principal, que se ejecuta cuando se llama desde Main.java
 * 
 */
package utils;

import java.io.*;

public class Snake {
    public static void main(int[] dimensiones) throws IOException, InterruptedException {
        
        /* ----- Parte declarativa ----- */
        //Creo una variable BufferedReader para leer datos y hago que sea un objeto de la clase FileReader para leer el archivo
        BufferedReader br = new BufferedReader(new FileReader("./content.txt"));
        String guardarDireccion;
        //El snake y si esta vivo
        boolean alive = true;
        final String[] SNAKE = { ColoresConsola.ANSI_GREEN() + "#" + ColoresConsola.ANSI_RESET()};
        
        boolean haComido = true;
        int[] cordsComida =  {0,0};
        final String[] FRUTA = { ColoresConsola.ANSI_RED() + "@" + ColoresConsola.ANSI_RESET()};

        //longitud de la serpiente
        //int snakeLongitud = 3; de momento inserbible

        //Literalmente el mapa
        //String[] cordenadas = new String[dimensiones[0]];
        //Tengo que actualizar el programa para sustituir las cordenadas anteriores por esta, cambios en el switch
        StringBuilder[] cordenadas = new StringBuilder[dimensiones[0]];

        //Cordenadas de la cabeza y de la cola
        //Primera cordenada la "x", posicion de una frase (empieza en 1) 
        //la segunda cordenada es una "y" la que indica que frase es (empieze en 0)
        int[] cordsCabeza = {3,0};
        int[] cordsCola = {1,0};
        String movs = "DD"; //Se cuencia de movimientos para saber la continuacion de la cola

        //Direccion
        String direcion = "D";

        /* ----- Parte principal ----- */
        
        //Creacion del mapa segun las variables de dimensiones
        for (int filas = 0; filas < dimensiones[0]; filas++) {
            //Como comienza con el valor "null" tengo que crearlo vacio
            cordenadas[filas] = new StringBuilder("");
            for(int columnas = 0; columnas < dimensiones[1]; columnas++) {
                cordenadas[filas].append("0");
            }
        }
        //Remplazo de la primera linAea las 3 primeras letras para hacer la serpiente
        cordenadas[0].replace(0, 3, "111");

        do {
            if(haComido) {
                cordsComida[0] = (int) (Math.random() * (dimensiones[0]-1));
                cordsComida[1] = (int) (Math.random() * (dimensiones[1]-1));
                
                Character comprobarPosicionTablero = cordenadas[cordsComida[0]].charAt(cordsComida[1]);
                while (comprobarPosicionTablero.equals('1')) {
                    if(cordsComida[0] == cordenadas[0].length()) {
                        cordsComida[0] = 0;
                        if(cordsComida[1] == cordenadas.length) {
                            cordsComida[1] = 0;
                        } else {
                            cordsComida[1] += 1;
                        }
                    } else {
                        cordsComida[0] += 1;
                    }
                
                    comprobarPosicionTablero = cordenadas[cordsComida[0]].charAt(cordsComida[1]);
                }
                haComido = false;
                cordenadas[cordsComida[0]].replace(cordsComida[1], cordsComida[1]+1, "2");
            }
            //Bucles for uno dentro de otro para que recorra el mapa de las cordenadas 1 por 1
            for (int filas = 0; filas < cordenadas.length; filas++) {
                System.out.printf("=");
                for (int columnas = 0; columnas < cordenadas[filas].length(); columnas++) {
                    
                    Character character = cordenadas[filas].charAt(columnas);
                    switch (character) {
                        case '1':
                            System.out.printf("%s", SNAKE[0]);
                            break;

                        case '2':
                            System.out.printf("%s",FRUTA[0]);
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

            //Tiempo de espera con hilos
            Thread.sleep(750);
            separacion();
            
            //Esto se tendra que hacer despues para que un espacio en blanco no de fallo
            guardarDireccion = br.readLine();
            //1 condicion ternarias para validar si no es nulo y si es W, A, S o D
            direcion = ((guardarDireccion != null && !guardarDireccion.equals("")) && ("WASD".contains(guardarDireccion) || "wasd".contains(guardarDireccion))) ? guardarDireccion : direcion;
            direcion = direcion.toUpperCase();
            //Detecta si es un movimiento valido con una condicion ternaria y guarda el movimiento para crear la cola
            movs = movs.concat(direcion);

            try { //Este try lo que esta haciendo es para que en el switch de la cabeza me pille el error de que se ha salido del array
                switch (direcion) {
                    case "W":
                        if('2' == cordenadas[cordsCabeza[1]-1].charAt(cordsCabeza[0]-1)) {
                            haComido = true;
                        }
                        break;
                
                    case "A":
                        if('2' == cordenadas[cordsCabeza[1]].charAt(cordsCabeza[0]-2)) {
                            haComido = true;
                        }
                        break;
                
                    case "S":
                        if('2' == cordenadas[cordsCabeza[1]+1].charAt(cordsCabeza[0]-1)) {
                            haComido = true;
                        }
                        break;
                
                    case "D":
                        if('2' == cordenadas[cordsCabeza[1]].charAt(cordsCabeza[0])) {
                            haComido = true;
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
                if(!haComido){
                    //Elimino la cola
                    cordenadas[cordsCola[1]].replace(cordsCola[0]-1, cordsCola[0], "0");
                    
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
                    //Elimina el primer movimiento ya que deberia ya haberse ejecutado
                    movs = movs.substring(1);
                }    
                    

                /*
                 * 
                 *  Switch para crear la cabeza
                 *  En este Switch lo que hago es detectar hacia donde va la cabeza y remplazo lo que haya por la cabeza y luego actualizo la posicion de ella
                 *  Con el if detecta si la posicion a la que quiere haceder esta el cuerpo de la serpiente, si es asi muere
                 */
                switch (direcion) {
                    case "W":
                        if('1' == cordenadas[cordsCabeza[1]-1].charAt(cordsCabeza[0]-1)) {
                            alive = false;
                        }

                        /* ----- Parte de la cabeza ----- */
                        cordenadas[cordsCabeza[1]-1].replace(cordsCabeza[0]-1, cordsCabeza[0], "1");

                        cordsCabeza[1] -= 1;
                        break;
                
                    case "A":
                        if('1' == cordenadas[cordsCabeza[1]].charAt(cordsCabeza[0]-2)) {
                            alive = false;
                        }

                        /* ----- Parte de la cabeza ----- */
                        cordenadas[cordsCabeza[1]].replace(cordsCabeza[0]-2, cordsCabeza[0]-1, "1");

                        cordsCabeza[0] -= 1;
                        break;
                
                    case "S":
                        if('1' == cordenadas[cordsCabeza[1]+1].charAt(cordsCabeza[0]-1)) {
                            alive = false;
                        }

                        /* ----- Parte de la cabeza ----- */
                        cordenadas[cordsCabeza[1]+1].replace(cordsCabeza[0]-1, cordsCabeza[0], "1");
                
                        cordsCabeza[1] += 1;
                        break;
                
                    case "D":
                        if('1' == cordenadas[cordsCabeza[1]].charAt(cordsCabeza[0])) {
                            alive = false;
                        }
                        /* ----- Parte de la cabeza ----- */
                        cordenadas[cordsCabeza[1]].replace(cordsCabeza[0], cordsCabeza[0]+1, "1");
                        cordsCabeza[0] += 1;
                        break;
                
                    default:
                        break;
                }
            } catch (StringIndexOutOfBoundsException e) {// Aqui capta el error de que el snake se ha salido de la pantalla, por lo tanto pasa de vivo a muerto 
                alive = false;
            } catch (Exception e) { //Por si acaso que no me fio xD
                alive = false;
            }
        } while (alive);
        br.close();
        System.out.println("Has perdido");
    }
    public static void separacion() {
        for(int i = 0; i < 30; i++) {
            System.out.println("\n");
        }
        //Esto hace que se borre
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
    }
}
