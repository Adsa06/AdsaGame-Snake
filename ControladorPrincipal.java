

import java.io.*;

public class ControladorPrincipal {
    public static void main(String[] args) throws IOException /* Capto las excepciones de la entrada de datos*/{
        
        /* ----- Parte declarativa ----- */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //Creo una variable BufferedWriter, y hago que sea un objeto de la clase FileWriter para que pueda escribir archivos por consola
        BufferedWriter fr = new BufferedWriter(new FileWriter("./content.txt"));
        
        String instrucion;
        /* ----- Parte principal ----- */
            while (true) {
                //Pergunto y leo una linea por consola
                System.out.printf("Escribe: ");
                instrucion = br.readLine();

                //write escribe en el archivo (hago que se escriba 2 veces ya que al leerlo en el otro programa primero identifica si no es nulo y despues lo escribe)
                fr.write(instrucion + "\n" + instrucion + "\n");
                //flush fuerza a escribir los fatos en el archivo
                fr.flush();
            }
    }
}
