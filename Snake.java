public class Snake {
    public static void main(String[] args) throws InterruptedException {
        
        /* ----- Parte declarativa ----- */
        
        boolean alive = true;
        char[] snake = { '#' };
        int snakeLongitud = 2;
        int dimensiones[] = { 
            20, 
            20
        };
        int 
        int posicion = 4;
        /* ----- Parte principal ----- */
        while (alive) {

            //System.out.printf("%" + cords[0] +"s", snake[0]);


            for(int i = 0; i < snakeLongitud; i++) {
                System.out.printf("%" + posicion + "s", snake[0]);
            }



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
