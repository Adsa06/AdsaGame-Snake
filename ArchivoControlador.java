import java.io.*;

public class ArchivoControlador {
    public static void main(String[] args) throws IOException{
        
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter fr = new BufferedWriter(new FileWriter("content.txt"));
        
        String name;
        /* ----- Parte principal ----- */
            while (true) {
                System.out.printf("Dime un numero: ");
                name = br.readLine();
                fr.write(name + "\n");
                fr.flush();
            }
    }
}
