/**
 * @author Aitor de Santos Amoros
 * Fecha: 09/04/2025
 * Este archivo sirve para la conexion con la base de datos, para que funcione tiene que tener una database llamada playerssnake
 * 
 */
package dev.adsa.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    /** URL de la base de datos a la que se conecta */
    private static final String URL = "jdbc:mysql://localhost:3306/playerssnake";
    /** Usuario con el cual se conecta */
    private static final String USER = "root";
    /** Contraseña con la que se conecta */
    private static final String PASSWORD = "Password1234";

    /**
     * Metodo que devuelve la conexion a la base de datos
     * @return Connection, la conexion a la base de datos
     * @throws SQLException si no se puede conectar a la base de datos
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}