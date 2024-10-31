package src;

public class Snake {
    public static void main(String[] args) throws InterruptedException {
        
        /* ----- Parte declarativa ----- */
        
        boolean alive = true;
        String[] snake = { ColoresConsola.ANSI_GREEN() + "#" + ColoresConsola.ANSI_RESET()};
        
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
            "00001110000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
            "00000000000000000000",
        };
        int CordsCabeza[] = {7,6};
        int CordsCola[] = {5,6};
        String direcion = "D";


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

            
            separacion();
            //Tiempo de espera con hilos
            Thread.sleep(1000);
            switch (direcion) {
                case "W":
                    //Faltaria cambiar el string
                    CordsCabeza[1] += 1;
                    CordsCola[1] += 1;
                    break;

                case "A":
                    CordsCabeza[0] -= 1;
                    CordsCola[0] -= 1;
                    break;

                case "S":
                    CordsCabeza[1] -= 1;
                    CordsCola[1] -= 1;
                    break;

                case "D":
                    //MAL, MAAAAAAAAAAAL
                    String subCadena1 = cordenadas2[CordsCabeza[0]].substring(0,CordsCabeza[1]-1);
                    String subCadena2 = cordenadas2[CordsCabeza[0]].substring(CordsCabeza[1], CordsCabeza[1] );
                    subCadena2 = subCadena2.replace("1","0");
                    String subCadena3 = cordenadas2[CordsCabeza[0]].substring(CordsCabeza[1]+1);
                    
                    cordenadas2[CordsCabeza[0]] = subCadena1 + subCadena2 + subCadena3;

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
