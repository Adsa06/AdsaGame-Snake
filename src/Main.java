/**
 * @author Aitor de Santos Amoros
 * Fecha: 17/10/2024
 * Este archivo muestra el menu y llama a las distintas funciones
 * 
 */

//Importo las clases de java.io i la carpeta de src
import java.io.*;

import clases.Player;
import utils.ControladorJuego;
import utils.Configuracion;

public class Main {

    /**
     * Muestra el menu principal del juego Snake y llama a las distintas funciones
     * 
     * @param args argumentos que se pasan al programa al ejecutarlo
     * @throws IOException          si hay un error de lectura o escritura
     * @throws InterruptedException si se interrumpe el thread principal
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        /* ----- Parte declarativa ----- */

        // Declaro el BufferedReader para leer datos por consola
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Player player = new Player();

        Boolean continuar = true;
        int option = 0;

        String mensajeSaludo = "\r\n" + //
                "  ___                                _  _                 _   ___                    _                              _        _  _                         ___ \r\n"
                + //
                " | _ ) _  _  ___  _ _   ___  ___  __| |(_) __ _  ___     (_) / __| ___  _ __   ___  | |_  ___   _ __  _  _  ___  __| | ___  | || | __ _  _ __   __ _  _ _|__ \\\r\n"
                + //
                " | _ \\| || |/ -_)| ' \\ / _ \\(_-< / _` || |/ _` |(_-< _  / /_| (__ / _ \\| '  \\ / _ \\ |  _|/ -_) | '_ \\| || |/ -_)/ _` |/ _ \\ | || |/ _` || '  \\ / _` || '_| /_/\r\n"
                + //
                " |___/ \\_,_|\\___||_||_|\\___//__/ \\__,_||_|\\__,_|/__/( ) \\___|\\___|\\___/|_|_|_|\\___/  \\__|\\___| | .__/ \\_,_|\\___|\\__,_|\\___/ |_||_|\\__,_||_|_|_|\\__,_||_|  (_) \r\n"
                + //
                "                                                    |/                                         |_|                                                            \r\n"
                + //
                "";
        // https://patorjk.com/software/taag/
        // Font: small, default y default
        String mensajeMenu = "\r\n" + //
                "   _    _                        _                         _                   ___ \r\n" + //
                "  (_)  /_\\    __ _ _  _ ___   __| |___ ___ ___ __ _ ___   (_)_  _ __ _ __ _ _ |__ \\\r\n" + //
                " / /_ / _ \\  / _` | || / -_) / _` / -_|_-</ -_) _` (_-<   | | || / _` / _` | '_|/_/\r\n" + //
                " \\___/_/ \\_\\ \\__, |\\_,_\\___| \\__,_\\___/__/\\___\\__,_/__/  _/ |\\_,_\\__, \\__,_|_| (_) \r\n" + //
                "                |_|                                     |__/     |___/                     \r\n" + //
                "  _            _                                                                           \r\n" + //
                " / |  ___   _ | |_  _ __ _ __ _ _ _                                                        \r\n" + //
                " | | |___| | || | || / _` / _` | '_|                                                       \r\n" + //
                " |_| |___|  \\__/ \\_,_\\__, \\__,_|_|                                                     \r\n" + //
                "                     |___/                                                                 \r\n" + //
                "                                                                                           \r\n" + //
                "  ___          ___           __ _                                                          \r\n" + //
                " |_  )  ___   / __|___ _ _  / _(_)                                                         \r\n" + //
                "  / /  |___| | (__/ _ \\ ' \\|  _| |                                                       \r\n" + //
                " /___| |___|  \\___\\___/_||_|_|_|_|                                                       \r\n" + //
                "                                                                                           \r\n" + //
                "  _ __        ___          __ _ _                                                          \r\n" + //
                " |__ /  ___  | _ \\___ _ _ / _(_) |                                                        \r\n" + //
                "  |_ \\ |___| |  _/ -_) '_|  _| | |                                                        \r\n" + //
                " |___/ |___| |_| \\___|_| |_| |_|_|                                                        \r\n" + //
                "                                                                                           \r\n" + //
                "  _ __        ___      _ _                                                                 \r\n" + //
                " | | |  ___  / __|__ _| (_)_ _                                                             \r\n" + //
                " |_  _||___| \\__ / _` | | | '_|                                                           \r\n" + //
                "   |_| |___| |___\\__,_|_|_|_|                                                             \r\n" + //
                "                                                                                           \r\n" + //
                "";

        /* ----- Parte principal ----- */

        // Aqui faltaria limpiar la pantalla por consola
        // Evito el error que aparece al introducir un nombre demasiado largo repetiendo
        // que me pida un nombre hasta que sea adecuado
        do {
            System.out.println(mensajeSaludo);
            player.setName(br.readLine());
            // Este es un if que detecta si es mayor a cuarenta la longitud de la frase si
            // es asi ejecuta el sigueinte linea de codigo
            if (player.getName().length() > 40)
                System.out.println("Introduzca un nombre menor a 40 caracteres");
        } while (player.getName().length() > 40);
        // Actualizamos el contenido del array con el nombre correcto

        do {
            System.out.println(mensajeMenu);
            // Conversion de tipo de dato
            try { // Este try impide que el usuario escriba una letra y pete el programa, como
                  // opcion es = 0, se ira al default en el switch
                option = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Porfavor, escriba una opcion valida");
                option = 0;
            }
            switch (option) {
                case 1:

                    /* ----- Esta zona hara que se reinicie el archivo txt ----- */ // Estas lineas estan explicadas en
                                                                                    // el ControladorPrincipal.java
                    BufferedWriter fr = new BufferedWriter(new FileWriter("./content.txt", false));
                    fr.close();
                    /* ----- */
                    double scoreProvisional = ControladorJuego.iniciarJuego(player.getCongiguration(), player);
                    if (scoreProvisional > player.getScore())
                        player.setScore(scoreProvisional);
                    break;
                case 2:
                    player.setCongiguration(Configuracion.cambiarConfiguracion(player.getCongiguration()));
                    break;
                case 3:
                    player.mostrarPerfil();
                    System.out.println("Presiona enter para salir");
                    br.readLine();
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (continuar);
        System.out.println("Hasta otra");
    }
}