package src;

public class Snake {
    public static void main(String[] args) throws InterruptedException {
        
        /* ----- Parte declarativa ----- */
        
        //El snake y si esta vivo
        boolean alive = true;
        String[] snake = { ColoresConsola.ANSI_GREEN() + "#" + ColoresConsola.ANSI_RESET()};
        
        //longitud de la serpiente
        int snakeLongitud = 3;

        //Dimension del nivel
        int dimensiones[] = { 
            20, 
            10
        };

        //Literalmente el mapa
        String cordenadas2[] = {
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00001110000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
        };

        //Cordenadas de la cabeza y de la cola
        //Primera cordenada la "x", posicion de una frase (empieza en 1) 
        //la segunda cordenada es una "y" la que indica que frase es (empieze en 0)
        

        int CordsCabeza[] = {5,5};
        int CordsCola[] = {7,5};
        /*  Pa el "D"
        int CordsCabeza[] = {7,5};
        int CordsCola[] = {5,5};
        */

        //Direccion
        String direcion = "A";

        //La sub cadenas para que se mueva
        String subCadenaCabeza1;
        String subCadenaCabeza2;
        String subCadenaCabeza3;
        String subCadenaCola1;
        String subCadenaCola2;
        String subCadenaCola3;


        /* ----- Parte principal ----- */
        
        while (alive) {
            
            for (int i = 0; i < cordenadas2.length; i++) {
                for (int j = 0; j < cordenadas2[i].length(); j++) {
                    
                    Character character = cordenadas2[i].charAt(j);
                   
                    System.out.printf("%s", character.equals('0') ? " " : snake[0]);

                }
                System.out.printf("%n");
            }

            
            separacion();
            //Tiempo de espera con hilos
            Thread.sleep(1000);
            switch (direcion) {
                case "W":

                    /* ----- Parte de la cabeza ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cabeza mas adelaneta gracias a la posicion de la cadena
                    subCadenaCabeza1 = cordenadas2[CordsCabeza[1]].substring(0,CordsCabeza[0]);
                    subCadenaCabeza2 = cordenadas2[CordsCabeza[1]].substring(CordsCabeza[0], CordsCabeza[0]+1);

                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCabeza2 = subCadenaCabeza2.replace("0","1");

                    subCadenaCabeza3 = cordenadas2[CordsCabeza[1]].substring(CordsCabeza[0]+1);
                    //Renuevo la cadena utilizando la particion
                    cordenadas2[CordsCabeza[1]] = subCadenaCabeza1 + subCadenaCabeza2 + subCadenaCabeza3;

                    /* ----- Parte de la cola ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cola mas adelanete
                    subCadenaCola1 = cordenadas2[CordsCola[1]].substring(0,CordsCola[0]-1);
                    subCadenaCola2 = cordenadas2[CordsCola[1]].substring(CordsCola[0]-1, CordsCola[0]);

                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCola2 = subCadenaCola2.replace("1","0");

                    subCadenaCola3 = cordenadas2[CordsCola[1]].substring(CordsCola[0]);
                    //Renuevo la cadena utilizando la particion
                    cordenadas2[CordsCola[1]] = subCadenaCola1 + subCadenaCola2 + subCadenaCola3;
                    CordsCabeza[1] += 1;
                    CordsCola[1] += 1;
                    break;

                case "A":
                    /* ----- Parte de la cabeza ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cabeza mas adelaneta gracias a la posicion de la cadena
                    subCadenaCabeza1 = cordenadas2[CordsCabeza[1]].substring(0,CordsCabeza[0]-2);
                    subCadenaCabeza2 = cordenadas2[CordsCabeza[1]].substring(CordsCabeza[0]-2, CordsCabeza[0]-1);
                
                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCabeza2 = subCadenaCabeza2.replace("0","1");
                                
                    subCadenaCabeza3 = cordenadas2[CordsCabeza[1]].substring(CordsCabeza[0]-1);
                    //Renuevo la cadena utilizando la particion
                    cordenadas2[CordsCabeza[1]] = subCadenaCabeza1 + subCadenaCabeza2 + subCadenaCabeza3;
                                
                    /* ----- Parte de la cola ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cola mas adelanete
                    subCadenaCola1 = cordenadas2[CordsCola[1]].substring(0,CordsCola[0]-1);
                    subCadenaCola2 = cordenadas2[CordsCola[1]].substring(CordsCola[0]-1, CordsCola[0]);
                                
                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCola2 = subCadenaCola2.replace("1","0");
                                
                    subCadenaCola3 = cordenadas2[CordsCola[1]].substring(CordsCola[0]);
                    //Renuevo la cadena utilizando la particion
                    cordenadas2[CordsCola[1]] = subCadenaCola1 + subCadenaCola2 + subCadenaCola3;
                    CordsCabeza[0] -= 1;
                    CordsCola[0] -= 1;
                    break;

                case "S":
                    /* ----- Parte de la cabeza ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cabeza mas adelaneta gracias a la posicion de la cadena
                    subCadenaCabeza1 = cordenadas2[CordsCabeza[1]].substring(0,CordsCabeza[0]);
                    subCadenaCabeza2 = cordenadas2[CordsCabeza[1]].substring(CordsCabeza[0], CordsCabeza[0]+1);
                
                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCabeza2 = subCadenaCabeza2.replace("0","1");

                    subCadenaCabeza3 = cordenadas2[CordsCabeza[1]].substring(CordsCabeza[0]+1);
                    //Renuevo la cadena utilizando la particion
                    cordenadas2[CordsCabeza[1]] = subCadenaCabeza1 + subCadenaCabeza2 + subCadenaCabeza3;

                    /* ----- Parte de la cola ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cola mas adelanete
                    subCadenaCola1 = cordenadas2[CordsCola[1]].substring(0,CordsCola[0]-1);
                    subCadenaCola2 = cordenadas2[CordsCola[1]].substring(CordsCola[0]-1, CordsCola[0]);

                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCola2 = subCadenaCola2.replace("1","0");

                    subCadenaCola3 = cordenadas2[CordsCola[1]].substring(CordsCola[0]);
                    //Renuevo la cadena utilizando la particion
                    cordenadas2[CordsCola[1]] = subCadenaCola1 + subCadenaCola2 + subCadenaCola3;
                    CordsCabeza[1] -= 1;
                    CordsCola[1] -= 1;
                    break;

                case "D":

                    /* ----- Parte de la cabeza ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cabeza mas adelaneta gracias a la posicion de la cadena
                    subCadenaCabeza1 = cordenadas2[CordsCabeza[1]].substring(0,CordsCabeza[0]);
                    subCadenaCabeza2 = cordenadas2[CordsCabeza[1]].substring(CordsCabeza[0], CordsCabeza[0]+1);

                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCabeza2 = subCadenaCabeza2.replace("0","1");

                    subCadenaCabeza3 = cordenadas2[CordsCabeza[1]].substring(CordsCabeza[0]+1);
                    //Renuevo la cadena utilizando la particion
                    cordenadas2[CordsCabeza[1]] = subCadenaCabeza1 + subCadenaCabeza2 + subCadenaCabeza3;

                    /* ----- Parte de la cola ----- */
                    //Parto la cadena en 3 para sustituir un 0 en un 1, concretamente creo una nueva cola mas adelanete
                    subCadenaCola1 = cordenadas2[CordsCola[1]].substring(0,CordsCola[0]-1);
                    subCadenaCola2 = cordenadas2[CordsCola[1]].substring(CordsCola[0]-1, CordsCola[0]);

                    /* ------------------------------ Aqui para saber si se ha chocado tendria q detectar si es un 1 lo q se quiere remplazar ------------------------------*/
                    subCadenaCola2 = subCadenaCola2.replace("1","0");

                    subCadenaCola3 = cordenadas2[CordsCola[1]].substring(CordsCola[0]);
                    //Renuevo la cadena utilizando la particion
                    cordenadas2[CordsCola[1]] = subCadenaCola1 + subCadenaCola2 + subCadenaCola3;
                    
                    //Y le sumo 1 a la posicion de cada 1 para q se actualiza
                    CordsCabeza[0] += 1;
                    CordsCola[0] += 1;
                    break;
            
                default:
                    break;
            }
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
