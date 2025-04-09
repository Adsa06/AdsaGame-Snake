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
    private static final String URL = "jdbc:mysql://localhost:3306/playerssnake";
    private static final String USER = "root";
    private static final String PASSWORD = "Password1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}