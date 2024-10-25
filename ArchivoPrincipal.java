import java.io.*;

public class ArchivoPrincipal {
    public static void main(String[] args) throws IOException, InterruptedException{
        
        BufferedReader reader = new BufferedReader(new FileReader("content.txt"));
        while(true) {
            if (reader.readLine() != null) {
                System.out.println("------------");
                System.out.println(reader.readLine());
                System.out.println("------------");
            }
            Thread.sleep(1000);
        }
    }
}
