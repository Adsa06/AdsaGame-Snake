import java.io.*;

public class ArchivoControlador {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        File file = new File("content.txt");

        FileWriter fw = new FileWriter(File file);

        BufferedWriter bw = new BufferedWriter(fw);

        //FileReader fr = new FileReader(file.getAbsolutePath());
       

        /* ----- Parte principal ----- */
        while (true) {
            System.out.printf("Dime un numero: ");
            fw.append(br.readLine());
        }
    }
}
