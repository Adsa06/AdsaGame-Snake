/**
 * @author: Aitor de Santos Amoros
 * Fecha: 15/12/2024
 * Este archivo es la clase de cada partida, en esta se almacena toda la informacion cada partida
 * 
 */
package dev.adsa.clases;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

import dev.adsa.utils.ColoresConsola;
import dev.adsa.utils.Utilidades;

public class Partida implements Serializable{
    /** Constante para la Serializacion */
    private static final long serialVersionUID = 1L;

    private enum ModoDeJuego {
        MODO_NORMAL("Modo Normal"),
        MODO_ATRAVESAR_PAREDES("Modo Atravesar Paredes");
    
        private final String descripcion;
    
        ModoDeJuego(String descripcion) {
            this.descripcion = descripcion;
        }
    
        public String getDescripcion() {
            return descripcion;
        }
    }
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
    private boolean ganado;
    /** Modo de juego */
    private ModoDeJuego modoDeJuego;

    /* ----- Metodos constructores ----- */
    public Partida() {

    }

    public Partida(LocalDateTime fechaInicio, LocalDateTime fechaFinal,  double puntuacion, int longitudSerpiente, int velocidad, int filas, int columnas,  boolean ganado, ModoDeJuego modoDeJuego) {
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.puntuacion = puntuacion;
        this.longitudSerpiente = longitudSerpiente;
        this.velocidad = velocidad;
        this.dimensionesTablero[0] = filas;
        this.dimensionesTablero[1] = columnas;
        this.ganado = ganado;
        this.modoDeJuego = modoDeJuego;
    }

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
     * Utilizo array copy para no copiar la referencia si no el contenido
     * @param dimensiones Los nuevos dimensiones del tablero en la partida.
     */
    public void setDimensionesTablero(int[] dimensiones) {
        this.dimensionesTablero = Arrays.copyOf(dimensiones, dimensiones.length);
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

    /**
     * Establece el modo de juego en la partida.
     * 
     * @param modoDeJuego El nuevo modo de juego en la partida.
     */
    public void setModoDeJuego(int numModoDeJuego) {
        switch (numModoDeJuego) {
            case 1:
                modoDeJuego = ModoDeJuego.MODO_NORMAL;
                break;

            case 2:
                modoDeJuego = ModoDeJuego.MODO_ATRAVESAR_PAREDES;
                break;
            default:
                
                break;
        }
    }

    /**
     * Aade una partida terminada a la lista de partidas.
     * 
     * @param puntuacion La puntuacion de la partida terminada.
     * @param velocidad  La velocidad de la partida terminada.
     * @param filas      El numero de filas del tablero de la partida terminada.
     * @param columnas   El numero de columnas del tablero de la partida terminada.
     */
    public void anadirPartidaTerminada(double puntuacion, int velocidad, int filas, int columnas) {
        actualizarFechaFinal();
        setPuntuacion(puntuacion);
        setVelocidad(velocidad);
        setDimensionesTablero(filas, columnas);
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFinal() {
        return fechaFinal;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    public int getLongitudSerpiente() {
        return longitudSerpiente;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getFilasTablero() {
        return dimensionesTablero[0];
    }

    public int getColumnasTablero() {
        return dimensionesTablero[1];
    }

    public boolean hasGanado() {
        return ganado;
    }

    public String getModoDeJuego() {
        return modoDeJuego.toString();
    }

    /**
     * Devuelve un mensaje de informacion de la partida, con o sin color,
     * dependiendo del parametro admiteColores.
     * 
     * @param admiteColores Un boolean que indica si la consola admite colores.
     * @return Un String con la informacion de la partida.
     */
    public String mostrarInfo(boolean admiteColores) {

        StringBuilder info = new StringBuilder();

        if (admiteColores) {
            // Encabezado con negrita y color cian
            info.append(ColoresConsola.ANSI_BOLD())
                .append(ColoresConsola.ANSI_CYAN())
                .append("=== Información de la Partida ===\n")
                .append(ColoresConsola.ANSI_RESET());
            
            // Fecha de inicio
            info.append(ColoresConsola.ANSI_BROWN())
                .append("Fecha de inicio: ")
                .append(ColoresConsola.ANSI_RESET())
                .append(fechaInicio.toString())
                .append("\n");

            // Tiempo transcurrido
            info.append(ColoresConsola.ANSI_BROWN())
                .append("Tiempo transcurrido: ")
                .append(ColoresConsola.ANSI_RESET())
                .append(Utilidades.formatearFecha(fechaInicio, fechaFinal))
                .append("\n");

            // Puntuación
            info.append(ColoresConsola.ANSI_BROWN())
                .append("Puntuación: ")
                .append(ColoresConsola.ANSI_RESET())
                .append(String.format("%.3f",puntuacion))
                .append("\n");

            // Longitud de la serpiente
            info.append(ColoresConsola.ANSI_BROWN())
                .append("Longitud de la serpiente: ")
                .append(ColoresConsola.ANSI_RESET())
                .append(longitudSerpiente)
                .append("\n");

            // Velocidad
            info.append(ColoresConsola.ANSI_BROWN())
                .append("Velocidad: ")
                .append(ColoresConsola.ANSI_RESET())
                .append(velocidad)
                .append("\n");

            // Dimensiones del tablero
            info.append(ColoresConsola.ANSI_BROWN())
                .append("Dimensiones del tablero: ")
                .append(ColoresConsola.ANSI_RESET())
                .append(Arrays.toString(dimensionesTablero))
                .append("\n");

            // Ganado
            info.append(ColoresConsola.ANSI_BROWN())
                .append("Ganado: ")
                .append(ColoresConsola.ANSI_RESET())
                .append(ganado ? "Sí" : "No")
                .append("\n");

            // Modo de juego (usando el método getDescripcion())
            info.append(ColoresConsola.ANSI_BROWN())
                .append("Modo de juego: ")
                .append(ColoresConsola.ANSI_RESET())
                .append(modoDeJuego.getDescripcion())
                .append("\n");
        } else {
            // Sin colores, formato simple
            info.append("=== Información de la Partida ===\n");
            info.append("Fecha de inicio: ").append(fechaInicio.toString()).append("\n");
            info.append("Tiempo transcurrido: ").append(Utilidades.formatearFecha(fechaInicio, fechaFinal)).append("\n");
            info.append("Puntuación: ").append(String.format("%.3f",puntuacion)).append("\n");
            info.append("Longitud de la serpiente: ").append(longitudSerpiente).append("\n");
            info.append("Velocidad: ").append(velocidad).append("\n");
            info.append("Dimensiones del tablero: ").append(Arrays.toString(dimensionesTablero)).append("\n");
            info.append("Ganado: ").append(ganado ? "Sí" : "No").append("\n");
            info.append("Modo de juego: ").append(modoDeJuego.getDescripcion()).append("\n");
        }

        return info.toString();
    }


    /**
     * Convierte la partida en una cadena que describe su informacion.
     * 
     * @return Un String que describe la partida.
     */
    @Override
    public String toString() {
        return "Partida [fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal
                + ", puntuacion=" + puntuacion + ", longitudSerpiente=" + longitudSerpiente + ", velocidad=" + velocidad
                + ", dimensionesTablero=" + Arrays.toString(dimensionesTablero) + ", ganado=" + ganado
                + ", modoDeJuego="
                + modoDeJuego.getDescripcion() + "]";
    }
}
