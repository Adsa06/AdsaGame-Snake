package dev.adsa.bbdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GestionDB {
    public static boolean creacionTablas() {
        boolean tablasCreadas = false;
        String sqlTablaPlayer = "CREATE TABLE Player (\n" + //
                                "    id INT AUTO_INCREMENT PRIMARY KEY,\n" + //
                                "    name VARCHAR(40) NOT NULL,\n" + //
                                "    maxScore DOUBLE NOT NULL\n" + //
                                ");";

        String sqlTablaPartidas = "CREATE TABLE Partida (\n" + //
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
            PreparedStatement sentencia = conexion.prepareStatement(sqlTablaPartidas);
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        return tablasCreadas;
    }
}
