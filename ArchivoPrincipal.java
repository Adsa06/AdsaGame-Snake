import java.io.*;

public class ArchivoPrincipal {
    public static void main(String[] args) throws IOException, InterruptedException{
        
        File file = new File("content.txt");
        FileReader fr = new FileReader(file.getAbsolutePath());

        while(true) {
            System.out.println("Hola");
            System.out.println(fr.read());
            Thread.sleep(1000);
        }
    }
}
