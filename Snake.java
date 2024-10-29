public class Snake {
    public static void main(String[] args) throws InterruptedException {
        
        /* ----- Parte declarativa ----- */
        char[] snake = {
            '#',
            '#'
        };
        int[] cords = {
            10,
            10
        };
        final int[] POSICION_INICIAL = {
            10,
            10
        };
        int posicion = 4;
        boolean alive = true;
        /* ----- Parte principal ----- */
        //while (alive) {
            sueloTecho(120);

            System.out.printf("%" + cords[0] +"s", snake[0]);
            for(int i = 1; i < snake.length; i++) {
                System.out.printf("%s", snake[i] , posicion);
            }
            sueloTecho(120);

            //Tiempo de espera con hilos
            Thread.sleep(1000);
        //}
    }

    public static void sueloTecho(int longitud) {
        System.out.printf("%n");
        for(int i = longitud; i > 0; i--) {
            System.out.print("-");
        }
        System.out.printf("%n");
    }
}
