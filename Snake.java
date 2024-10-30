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
        String cordenadas2[] = {
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00001100000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
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
        while (alive) {

            for (int i = 0; i < cordenadas2.length; i++) {
                for (int j = 0; j < cordenadas2[i].length(); j++) {
                    
                    Character character = cordenadas2[i].charAt(j);
                   
                    System.out.printf("%s", character.equals('0') ? " " : snake[0]);

                }
                System.out.printf("%n");
            };

            //Tiempo de espera con hilos
            separacion();
            Thread.sleep(1000);
        }
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
