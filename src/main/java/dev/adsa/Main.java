package dev.adsa;

/**
 * @author Aitor de Santos Amoros
 * Fecha: 17/10/2024
 * Este archivo muestra el menu y llama a las distintas funciones
 * 
 */

//Importo las clases de java.io i la carpeta de src
import java.io.*;

import dev.adsa.clases.Partida;
import dev.adsa.clases.Player;
import dev.adsa.utils.Configuracion;
import dev.adsa.utils.ControladorJuego;
import dev.adsa.utils.Utilidades;

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

        Player player = null;

        int option = 0;

        // https://patorjk.com/software/taag/
        // Font: small, default y default
        String mensajeMenu = "\n" + //
                        "   _    _                        _                         _                   ___ \n" + //
                        "  (_)  /_\\    __ _ _  _ ___   __| |___ ___ ___ __ _ ___   (_)_  _ __ _ __ _ _ |__ \\\n" + //
                        " / /_ / _ \\  / _` | || / -_) / _` / -_|_-</ -_) _` (_-<   | | || / _` / _` | '_|/_/\n" + //
                        " \\___/_/ \\_\\ \\__, |\\_,_\\___| \\__,_\\___/__/\\___\\__,_/__/  _/ |\\_,_\\__, \\__,_|_| (_) \n" + //
                        "  _            _|_|                                     |__/     |___/             \n" + //
                        " / |  ___   _ | |_  _ __ _ __ _ _ _                                                \n" + //
                        " | | |___| | || | || / _` / _` | '_|                                               \n" + //
                        " |_| |___|  \\__/ \\_,_\\__, \\__,_|_|                                                 \n" + //
                        "  ___          ___   |___/   __ _                                                  \n" + //
                        " |_  )  ___   / __|___ _ _  / _(_)__ _                                             \n" + //
                        "  / /  |___| | (__/ _ \\ ' \\|  _| / _` |                                            \n" + //
                        " /___| |___|  \\___\\___/_||_|_| |_\\__, |                                            \n" + //
                        "  ____        ___          __ _ _|___/                                             \n" + //
                        " |__ /  ___  | _ \\___ _ _ / _(_) |                                                 \n" + //
                        "  |_ \\ |___| |  _/ -_) '_|  _| | |                                                 \n" + //
                        " |___/ |___| |_| \\___|_| |_| |_|_|                                                 \n" + //
                        "  _ _           ___                                   _                            \n" + //
                        " | | |   ___   / __|___ _ _ _ _ __ _ _ _   ___ ___ __(_)___ _ _                    \n" + //
                        " |_  _| |___| | (__/ -_) '_| '_/ _` | '_| (_-</ -_|_-< / _ \\ ' \\                   \n" + //
                        "   |_|  |___|  \\___\\___|_| |_| \\__,_|_|   /__/\\___/__/_\\___/_||_|                  \n" + //
                        "  ___         ___ _ _       _                                __ _ _  \n" + //
                        " | __|  ___  | __| (_)_ __ (_)_ _  __ _ _ _   _ __  ___ _ _ / _(_) | \n" + //
                        " |__ \\ |___| | _|| | | '  \\| | ' \\/ _` | '_| | '_ \\/ -_) '_|  _| | | \n" + //
                        " |___/ |___| |___|_|_|_|_|_|_|_||_\\__,_|_|   | .__/\\___|_| |_| |_|_| \n" + //
                        "   __          ___                  _        |_|        ___       _ _              \n" + //
                        "  / /   ___   / __|_  _ __ _ _ _ __| |__ _ _ _   _  _  / __| __ _| (_)_ _          \n" + //
                        " / _ \\ |___| | (_ | || / _` | '_/ _` / _` | '_| | || | \\__ \\/ _` | | | '_|         \n" + //
                        " \\___/ |___|  \\___|\\_,_\\__,_|_| \\__,_\\__,_|_|    \\_, | |___/\\__,_|_|_|_|           \n" + //
                        "                                                 |__/                              \n" + //
                        "";

        /* ----- Parte principal ----- */
        player = Utilidades.iniciarJugador();

        do {
            System.out.println(mensajeMenu);
            option = Utilidades.pedirNumeroEntero("Introduce una opcion entre", 1, 6);
            switch (option) {
                case 1 -> {
                    /* ----- Esta zona hara que se reinicie el archivo txt ----- */ 
                    // Estas lineas estan explicadas en
                    // el ControladorPrincipal.java
                    BufferedWriter fr = new BufferedWriter(new FileWriter("./content.txt", false));
                    fr.close();
                    /* ----- */
                    Partida partida = new Partida();
                    partida.actualizarFechaInicio();
                    double scoreProvisional = ControladorJuego.iniciarJuego(player, partida);
                    partida.anadirPartidaTerminada(scoreProvisional, player.getCongiguration()[2], player.getCongiguration()[0], player.getCongiguration()[1]);
                    if (scoreProvisional > player.getMaxScore())
                        player.setMaxScore(scoreProvisional);
                    player.addPartida(partida);
                }
                case 2 -> {
                    player.setCongiguration(Configuracion.cambiarConfiguracion(player.getCongiguration()));
                }

                case 3 -> {
                    player.mostrarPerfil();
                    System.out.println("Presiona enter para salir");
                    Utilidades.presionarEnter();
                }

                case 4 -> {
                    player = Utilidades.cerrarSesion(player);
                }

                case 5 -> {
                    player = Utilidades.eliminarPerfil(player);
                }

                default -> {
                    if (option != 6)
                        System.out.println("Opcion incorrecta");
                }
            }
        } while (option != 6);
        Player.guardarJugador(player);
        System.out.println("Hasta otra");
    }
}