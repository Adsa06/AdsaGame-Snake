/*
 * Tipos de variables que se modifican:
 *  - Clases personalizadas (Se puede comprobar en InstanciamientoClases.java)
 *  - Arrays
 *  - StringBuilder
 * 
 * Tipos de variables que no se modifican:
 *  - Primitivos
 *  - String
 */

package PruebasAlocadas.PruebasInmutabilidad;

import java.util.Arrays;

public class PruebasInmutabilidad {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        StringBuilder sb = new StringBuilder("Hola");

        System.out.println("Array original: " + Arrays.toString(array));

        Recursividad(array); // Un tipo de variable que no devuelve nada pero aun asi se ve modificada

        System.out.println("Array modificado: " + Arrays.toString(array));
        

        System.out.println("String original: " + sb);

        Recursividad2(sb);// Un tipo de variable que no devuelve nada pero aun asi se ve modificada

        System.out.println("String modificado: " + sb);
    }

    public static void Recursividad(int[] arr) {
        arr[2] = arr[2] * 2;
    }

    public static void Recursividad2(StringBuilder sb) {
        sb.append(" Mundo!"); // Modifica el contenido del objeto
    }
}
