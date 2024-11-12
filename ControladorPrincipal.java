

import java.io.*;
import src.ColoresConsola;

public class ControladorPrincipal {
    public static void main(String[] args) throws IOException /* Capto las excepciones de la entrada de datos*/{
        
        /* ----- Parte declarativa ----- */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Creo una variable BufferedWriter, y hago que sea un objeto de la clase FileWriter para que pueda escribir archivos por consola
        //Al poner false el archivo se sobreescribira por completo
        BufferedWriter fr = new BufferedWriter(new FileWriter("./content.txt", false));
        
        String instrucion;
        boolean quit = false;
        /* ----- Parte principal ----- */
        while (!quit) {
            //Pergunto y leo una linea por consola
            System.out.printf("Escribe \"" + ColoresConsola.ANSI_CYAN() + "W" + ColoresConsola.ANSI_RESET() + "\",\"" + ColoresConsola.ANSI_CYAN() + "A" + ColoresConsola.ANSI_RESET() + "\",\"" + ColoresConsola.ANSI_CYAN() + "S" + ColoresConsola.ANSI_RESET() + "\",\"" + ColoresConsola.ANSI_CYAN() + "D" + ColoresConsola.ANSI_RESET() + "\": ");
            instrucion = br.readLine();
            //write escribe en el archivo
            fr.write(instrucion + "\n");
            //flush fuerza a escribir los fatos en el archivo
            fr.flush();
            //Ahora tiene una forma de salir
            quit = instrucion.equals("Salir") ? true : false;
        }
        fr.close();

    }
}
