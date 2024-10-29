package ConectarProgramas;
import java.io.*;

public class ArchivoPrincipal {
    public static void main(String[] args) throws IOException, InterruptedException /* Capto las excepciones del timer y de la entrada de datos*/{
        
        /* ----- Parte declarativa ----- */

        //Creo una variable BufferedReader para leer datos y hago que sea un objeto de la clase FileReader para leer el archivo
        BufferedReader br = new BufferedReader(new FileReader("content.txt"));

        /* ----- Parte principal ----- */
        while(true) {
            //Operador ternario sencillo que identifica q no es nulo y lo escribe
            System.out.printf(br.readLine() != null ? "------------%n" + br.readLine() + "%n------------%n" : "");

            //Tiempo de espera de medio segundo (Con hilos)
            Thread.sleep(500);
        }
    }
}