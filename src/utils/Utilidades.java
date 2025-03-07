package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Utilidades {
        public static int pedirNumeroEntero(String frase, int min, int max) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion = -1;
        do {
            System.out.println(frase + " " + min + " y " + max);
            try {
                opcion = Integer.parseInt(br.readLine());
                if(opcion > max || opcion < min)
                    System.out.println("Numero fuera de rango");
            } catch (Exception e) {
                System.out.println("Entrada no valida, Introduce un numero entero");
                opcion = -1;
            }
        } while (opcion > max || opcion < min);
        return opcion;
    }

    public static String pedirString() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String opcion = "";
        do {
            try {
                opcion = br.readLine();
                if(opcion.equals(""))
                    System.out.println("Introduzca algo");
            } catch (Exception e) {
                System.out.println("Entrada no valida");
                opcion = "";
            }
        } while (opcion.equals(""));
        return opcion;
    }
}
