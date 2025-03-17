/*
 * Autor: Aitor de Santos Amoros
 * Fecha: 15/12/2024
 * Este archivo es la clase de cada partida, en esta se almacena toda la informacion cada partida
 * 
 */
package clases;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Partida {

    /** Fecha inicio de la partida */
    private LocalDateTime fechaInicio;
    /** Fecha final de la partida */
    private LocalDateTime fechaFinal;
    /** Puntuacion de la partida */
    private double puntuacion;
    /** Longitud de la serpiente */
    private int longitudSerpiente;
    /** Velocidad de la partida */
    private int velocidad;
    /** Dimensiones del tablero */
    private int[] dimensionesTablero = new int[2];
    /** Ganado de la partida */
    public boolean ganado;

    /**
     * Actualiza la fecha de inicio de la partida al momento actual.
     */
    public void actualizarFechaInicio() {
        this.fechaInicio = LocalDateTime.now();
    }

    /**
     * Actualiza la fecha de final de la partida al momento actual.
     */
    public void actualizarFechaFinal() {
        this.fechaFinal = LocalDateTime.now();
    }

    /**
     * Establece la puntuacion de la partida.
     * 
     * @param puntuacion La nueva puntuacion de la partida.
     */
    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }

    /**
     * Establece la longitud de la serpiente en la partida.
     * 
     * @param longitud La nueva longitud de la serpiente en la partida.
     */
    public void setLongitudSerpiente(int longitud) {
        this.longitudSerpiente = longitud;
    }

    /**
     * Establece la velocidad de la partida.
     * 
     * @param velocidad La nueva velocidad de la partida.
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Establece las dimensiones del tablero en la partida.
     * 
     * @param dimensiones Los nuevos dimensiones del tablero en la partida.
     */
    public void setDimensionesTablero(int[] dimensiones) {
        this.dimensionesTablero = dimensiones;
    }

    /**
     * Establece las dimensiones del tablero en la partida.
     * 
     * @param dimensiones Los nuevos dimensiones del tablero en la partida.
     */
    public void setDimensionesTablero(int filas, int columnas) {
        this.dimensionesTablero[0] = filas;
        this.dimensionesTablero[1] = columnas;
    }

    /**
     * Establece si la partida ha sido ganada o no.
     * 
     * @param ganado True si la partida ha sido ganada, false en caso contrario.
     */
    public void setGanado(boolean ganado) {
        this.ganado = ganado;
    }

    @Override
    public String toString() {
        return "Partida [fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal
                + ", puntuacion=" + puntuacion + ", longitudSerpiente=" + longitudSerpiente + ", velocidad=" + velocidad
                + ", dimensionesTablero=" + Arrays.toString(dimensionesTablero) + ", ganado=" + ganado + "]";
    }
}
