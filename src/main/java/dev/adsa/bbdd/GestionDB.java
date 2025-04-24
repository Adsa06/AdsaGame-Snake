/**
 * @author Aitor de Santos Amoros
 * Fecha: 09/04/2025
 * Este archivo tiene las funciones para la gestion de la bases de datos
 * 
 */
package dev.adsa.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import dev.adsa.clases.Partida;
import dev.adsa.clases.Player;

import java.sql.SQLException;

public class GestionDB {

    /**
     * Crea las tablas necesarias en la base de datos si no existen.
     * Las tablas creadas son "Player" y "Partida".
     * 
     * @return true si las tablas se crean correctamente o ya existen, false en caso
     *         de error.
     */
    public static boolean creacionTablas() {
        boolean tablasCreadas = false;
        String sqlTablaPlayer = "CREATE TABLE if not exists Player (\n" + //
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" + //
                "    username VARCHAR(40) NOT NULL UNIQUE,\n" + //
                "    maxScore DOUBLE NOT NULL\n" + //
                ");";

        String sqlTablaPartidas = "CREATE TABLE if not exists Partida (\n" + //
                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" + //
                "    player_id INT, \n" + //
                "    fechaInicio DATETIME NOT NULL,\n" + //
                "    fechaFinal DATETIME NOT NULL,\n" + //
                "    puntuacion DOUBLE NOT NULL,\n" + //
                "    longitudSerpiente INT NOT NULL,\n" + //
                "    velocidad INT NOT NULL,\n" + //
                "    ganado BOOLEAN NOT NULL,\n" + //
                "    modoJuego ENUM('MODO_NORMAL', 'MODO_ATRAVESAR_PAREDES') NOT NULL,\n" + //
                "    filas INT NOT NULL,\n" + //
                "    columnas INT NOT NULL,\n" + //
                "    CONSTRAINT FK_idPlayer FOREIGN KEY (player_id) REFERENCES Player(id) ON DELETE CASCADE ON UPDATE CASCADE\n"
                + //
                ");";

        try {
            Connection conexion = ConexionDB.getConnection();
            Statement sentencia = conexion.createStatement();

            // Crear la tabla Player
            sentencia.executeUpdate(sqlTablaPlayer);

            // Crear la tabla Partida
            sentencia.executeUpdate(sqlTablaPartidas);

            tablasCreadas = true;

            sentencia.close();
            conexion.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return tablasCreadas;
    }

    /**
     * Guarda un nuevo jugador en la base de datos.
     * 
     * @param player El jugador que se desea guardar.
     * @return true si el jugador se guarda correctamente, false en caso de error.
     */
    public static boolean guardarJugadorDB(Player player) {
        boolean datosCreados = false;

        String sqlNewPlayer = "INSERT INTO player(username, maxScore) VALUES (?, 0);";
        try {
            Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sqlNewPlayer);

            sentencia.setString(1, player.getName());

            sentencia.executeUpdate();

            datosCreados = true;

            sentencia.close();
            conexion.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return datosCreados;
    }

    /**
     * Guarda una partida asociada a un jugador en la base de datos.
     * 
     * @param partida La partida que se desea guardar.
     * @param player  El jugador al que pertenece la partida.
     * @return true si la partida se guarda correctamente, false en caso de error.
     */
    public static boolean guardarPartidaDB(Partida partida, Player player) {
        boolean datosCreados = false;

        String sqlNewPlayer = "INSERT INTO partida(player_id, fechaInicio, fechaFinal, puntuacion, longitudSerpiente, velocidad, ganado, modoJuego, filas, columnas) "
                +
                "VALUES ((SELECT p.id from player p where p.username = ?), ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sqlNewPlayer);

            sentencia.setString(1, player.getName());
            sentencia.setTimestamp(2, Timestamp.valueOf(partida.getFechaInicio()));
            sentencia.setTimestamp(3, Timestamp.valueOf(partida.getFechaFinal()));
            sentencia.setDouble(4, partida.getPuntuacion());
            sentencia.setInt(5, partida.getLongitudSerpiente());
            sentencia.setInt(6, partida.getVelocidad());
            sentencia.setBoolean(7, partida.hasGanado());
            sentencia.setString(8, partida.getModoDeJuego());
            sentencia.setInt(9, partida.getFilasTablero());
            sentencia.setInt(10, partida.getColumnasTablero());

            sentencia.executeUpdate();

            datosCreados = true;

            sentencia.close();
            conexion.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return datosCreados;
    }

    /**
     * Actualiza el puntaje máximo de un jugador en la base de datos.
     * 
     * @param player El jugador cuyo puntaje máximo se desea actualizar.
     * @return true si el puntaje se actualiza correctamente, false en caso de
     *         error.
     */
    public static boolean actualizarMaxScore(Player player) {
        boolean datosModificados = false;

        String sqlUpdatePlayer = "UPDATE player SET maxScore = ? WHERE username = ?;";

        try {
            Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sqlUpdatePlayer);

            sentencia.setDouble(1, player.getMaxScore());
            sentencia.setString(2, player.getName());

            sentencia.executeUpdate();

            sentencia.close();
            conexion.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return datosModificados;
    }

    /**
     * Verifica si un jugador existe en la base de datos.
     * 
     * @param name El nombre del jugador que se desea verificar.
     * @return true si el jugador existe, false en caso contrario.
     */
    public static boolean detectarJugadorExistente(String name) {
        boolean jugadorDetectado = false;

        String sqlDetectarJugador = "SELECT username FROM player WHERE username = ?;";

        try {
            Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sqlDetectarJugador);

            sentencia.setString(1, name);

            ResultSet resultado = sentencia.executeQuery();

            jugadorDetectado = resultado.next();

            sentencia.close();
            resultado.close();
            conexion.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return jugadorDetectado;
    }

    /**
     * Recupera los datos de un jugador y sus partidas desde la base de datos.
     * 
     * @param name El nombre del jugador cuyos datos se desean recuperar.
     * @return Un objeto Player con los datos del jugador y sus partidas, o null si
     *         ocurre un error.
     */
    public static Player leerDatos(String name) {
        String sqlPedirDatosJugador = "SELECT username, maxScore FROM player WHERE username = ?;";
        String sqlPedirDatosPartidas = "SELECT fechaInicio, fechaFinal, puntuacion, longitudSerpiente, velocidad, filas, columnas, ganado, modoJuego \n"
                + //
                "FROM partida \n" + //
                "WHERE player_id = \n" + //
                "\t(SELECT id \n" + //
                "    FROM player WHERE username = ?);";
        Player player = null;
        try {
            Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sqlPedirDatosJugador);

            sentencia.setString(1, name);

            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next())
                player = new Player(resultado.getString("username"), resultado.getDouble("maxScore"));

            sentencia.close();
            resultado.close();
            conexion.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        try {
            Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sqlPedirDatosPartidas);

            sentencia.setString(1, name);

            ResultSet res = sentencia.executeQuery();
            while (res.next()) {
                player.addPartida(new Partida(
                        res.getTimestamp("fechaInicio").toLocalDateTime(),
                        res.getTimestamp("fechaFinal").toLocalDateTime(),
                        res.getDouble("puntuacion"),
                        res.getInt("longitudSerpiente"),
                        res.getInt("velocidad"),
                        res.getInt("filas"),
                        res.getInt("columnas"),
                        res.getBoolean("ganado"),
                        res.getString("modoJuego")));
            }

            sentencia.close();
            res.close();
            conexion.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return player;
    }

    /**
     * Elimina los datos de un jugador y sus partidas en la base de datos.
     * 
     * @param player El jugador que se desea eliminar.
     */
    public static void eliminarPerfil(Player player) {
        String sqlEliminarPerfil = "DELETE FROM player WHERE username = ?;";

        try {
            Connection conexion = ConexionDB.getConnection();
            PreparedStatement sentencia = conexion.prepareStatement(sqlEliminarPerfil);

            sentencia.setString(1, player.getName());

            sentencia.executeUpdate();

            sentencia.close();
            conexion.close();

            System.out.println("Cuenta eliminada en la base de datos");
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
}
