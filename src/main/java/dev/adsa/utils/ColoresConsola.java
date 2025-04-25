/**
 * @author: Aitor de Santos Amoros
 * Fecha: 17/10/2024
 * Este archivo es el que da los colores a todos los demas archivos
 * 
 */

package dev.adsa.utils;

/**
 * Esta clase define códigos de color ANSI para la consola.
 */
public class ColoresConsola {

    /**
     * Devuelve el codigo para resetear el color de la consola a sus valores por
     * defecto.
     * 
     * @return El string con el codigo para resetear el color de la consola.
     */
    public static String ANSI_RESET() {
        return "\u001B[0m";
    }

    /**
     * Devuelve el codigo para poner el texto en rojo.
     * 
     * @return El string con el codigo para poner el texto en rojo.
     */
    public static String ANSI_RED() {
        return "\u001B[31m";
    }

    /**
     * Devuelve el codigo para poner el texto en verde.
     * 
     * @return El string con el codigo para poner el texto en verde.
     */
    public static String ANSI_GREEN() {
        return "\u001B[32m";
    }

    /**
     * Devuelve el codigo para poner el texto en verde claro.
     * 
     * @return El string con el codigo para poner el texto en verde claro.
     */
    public static String ANSI_LIGHT_GREEN() {
        return "\u001B[92m";
    }

    /**
     * Devuelve el codigo para poner el texto en verde oscuro.
     * 
     * @return El string con el codigo para poner el texto en verde oscuro.
     */
    public static String ANSI_DARK_GREEN() {
        return "\u001B[32m";
    }

    /**
     * Devuelve el codigo para poner el texto en amarillo.
     * 
     * @return El string con el codigo para poner el texto en amarillo.
     */
    public static String ANSI_YELLOW() {
        return "\u001B[33m";
    }

    /**
     * Devuelve el codigo para poner el texto en azul.
     * 
     * @return El string con el codigo para poner el texto en azul.
     */
    public static String ANSI_BLUE() {
        return "\u001B[34m";
    }

    /**
     * Devuelve el codigo para poner el texto en morado.
     * 
     * @return El string con el codigo para poner el texto en morado.
     */
    public static String ANSI_PURPLE() {
        return "\u001B[35m";
    }

    /**
     * Devuelve el codigo para poner el texto en cian.
     * 
     * @return El string con el codigo para poner el texto en cian.
     */
    public static String ANSI_CYAN() {
        return "\u001B[36m";
    }

    /**
     * Devuelve el codigo para poner el texto en blanco.
     * 
     * @return El string con el codigo para poner el texto en blanco.
     */
    public static String ANSI_WHITE() {
        return "\u001B[37m";
    }

    /**
     * Devuelve el codigo para poner el texto en negrita.
     * 
     * @return El string con el codigo para poner el texto en negrita.
     */

    public static String ANSI_BOLD() {
        return "\u001B[1m";
    }

    /**
     * Devuelve el codigo para subrayar el texto en la consola.
     * 
     * @return El string con el codigo ANSI para subrayar el texto.
     */

    public static String ANSI_UNDERLINE() {
        return "\u001B[4m";
    }

    /**
     * Devuelve el codigo para invertir el color de fondo y texto en la consola.
     * 
     * @return El string con el codigo ANSI para invertir el color de fondo y texto.
     */
    public static String ANSI_INVERT() {
        return "\u001B[7m";
    }

    /**
     * Devuelve el código para poner el texto en marrón.
     * 
     * @return El string con el código para poner el texto en marrón.
     */
    public static String ANSI_BROWN() {
        return "\u001B[38;2;165;42;42m"; // RGB para color marrón
    }

    /**
     * Devuelve el codigo para poner el texto en un color RGB especifico.
     * 
     * @param r el valor rojo (0-255)
     * @param g el valor verde (0-255)
     * @param b el valor azul (0-255)
     * @return El string con el codigo ANSI para el color RGB solicitado
     */
    public static String ANSI_RGB(int r, int g, int b) {
        return "\u001B[38;2;" + r + ";" + g + ";" + b + "m";
    }
}