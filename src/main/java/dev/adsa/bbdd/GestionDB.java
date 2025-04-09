/**
 * @author Aitor de Santos Amoros
 * Fecha: 09/04/2025
 * Este archivo tiene las funciones para la gestion de la bases de datos
 * 
 */
package dev.adsa.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import dev.adsa.clases.Partida;
import dev.adsa.clases.Player;

import java.sql.SQLException;

public class GestionDB {
    public static void main(String[] args) {
        creacionTablas();
        Player player = new Player();
        player.setName("null");
        guardarJugadorDB(player);
    }
    public static boolean creacionTablas() {
        boolean tablasCreadas = false;
        String sqlTablaPlayer = "CREATE TABLE if not exists Player (\n" + //
                                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" + //
                                "    userName VARCHAR(40) NOT NULL,\n" + //
                                "    maxScore DOUBLE NOT NULL\n" + //
                                ");";

        String sqlTablaPartidas = "CREATE TABLE if not exists Partida (\n" + //
                                    "    id INT AUTO_INCREMENT PRIMARY KEY,\n" + //
                                    "    player_id INT,  -- Relaciona la partida con un jugador\n" + //
                                    "    fechaInicio DATETIME NOT NULL,\n" + //
                                    "    fechaFinal DATETIME NOT NULL,\n" + //
                                    "    puntuacion DOUBLE NOT NULL,\n" + //
                                    "    longitudSerpiente INT NOT NULL,\n" + //
                                    "    velocidad INT NOT NULL,\n" + //
                                    "    ganado BOOLEAN NOT NULL,\n" + //
                                    "    modoJuego ENUM('MODO_NORMAL', 'MODO_ATRAVESAR_PAREDES') NOT NULL,\n" + //
                                    "    filas INT NOT NULL,\n" + //
                                    "    columnas INT NOT NULL,\n" + //
                                    "    FOREIGN KEY (player_id) REFERENCES Player(id) ON DELETE CASCADE\n" + //
                                    ");";

        try {
            Connection conexion = ConexionDB.getConnection();
            Statement sentencia = conexion.createStatement();
            
            // Crear la tabla Player
            sentencia.executeUpdate(sqlTablaPlayer);
                    
            // Crear la tabla Partida
            sentencia.executeUpdate(sqlTablaPartidas);
                    
            tablasCreadas = true;
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return tablasCreadas;
    }

    public static boolean guardarJugadorDB(Player player) {
        boolean datosCreados = false;

        String sqlNewPlayer = "INSERT INTO player(userName, maxScore) VALUES (?, 0);";
        try {
            Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sqlNewPlayer);

            sentencia.setString(1, player.getName());

            sentencia.executeUpdate();
                    
            datosCreados = true;
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return datosCreados;
    }

    public static boolean guardarPartidaDB(Partida partida, Player player) {
        boolean datosCreados = false;

        String sqlNewPlayer = "INSERT INTO partida(player_id, fechaInicio, fechaFinal, puntuacion, longitudSerpiente, velocidad, ganado, modoJuego, filas, columnas) " + 
                                "VALUES ((SELECT p.id from player p where p.username = ?), ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sqlNewPlayer);

            sentencia.setString(1, player.getName());
            sentencia.setTimestamp(2)
            sentencia.setString(3, player.getName());
            sentencia.setString(4, player.getName());
            sentencia.setString(5, player.getName());
            sentencia.setString(6, player.getName());
            sentencia.setString(7, player.getName());
            sentencia.setString(8, player.getName());
            sentencia.setString(9, player.getName());
            sentencia.setString(10, player.getName());

            sentencia.executeUpdate();
                    
            datosCreados = true;
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return datosCreados;
    }
    
    
}
