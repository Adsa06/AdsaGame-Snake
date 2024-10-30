public class Snake {
    public static void main(String[] args) throws InterruptedException {
        
        /* ----- Parte declarativa ----- */
        
        boolean alive = true;
        String[] snake = { "#" };
        int snakeLongitud = 2;
        int dimensiones[] = { 
            20, 
            20
        };
        Long cordenadas2[] = {
            11000000000L,
            11111111111L,
            11111011111L,
            11111111111L,
            11111111111L,
            11110001111L,
            11111111111L,
            11001111111L,
            11111111111L,
            11101111011L,
        };
        long cordenadas[] = {
            00000000000000000000L,
            00000000000000000000L,
            00000000000010000000L,
            00000000000000000000L,
            00000000000000000000L,
            00000000000000000000L,
            00000000000000000000L,
            00000001000000000000L,
            00000000000000000000L,
            00000000000000000000L,
        };
        int posicion = 4;
        /* ----- Parte principal ----- */
        //while (alive) {

            //System.out.printf("%" + cords[0] +"s", snake[0]);
            for (int i = 0; i < cordenadas2.length; i++) {
                for (int j = 1; j < String.valueOf(cordenadas2).length() - 3; j++) {
                    String linea = Long.toString(cordenadas2[i]);
                    
                    Character character = linea.charAt(j);
                   
                    System.out.printf("%s", character.equals('0') ? " " : snake[0]);

                }
                System.out.printf("%n");
            };

            /*
            for(int i = 0; i < snakeLongitud; i++) {
                System.out.printf("%" + posicion + "s", snake[0]);
            }
            */


            //Tiempo de espera con hilos
            separacion();
            Thread.sleep(1000);
        //}
    }
    public static void separacion() {
        for(int i = 0; i < 10; i++) {
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
