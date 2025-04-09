/**
 * @author: Aitor de Santos Amoros
 * Fecha: 09/12/2024
 * Este archivo es el apartado de configuracion del juego, este devuelve un array con la configuracion establecida
 * 
 */
package utils;

public class Configuracion {

    /**
     * Metodo que enseña una interfaz y llama a las distintas funciones para cambiar
     * la configuracion
     * 
     * Array de configuracion:
     * El primer dato es el numero de filas que hay en el tablero
     * El segundo dato es el numero de columnas que hay en el tablero
     * El tercero es el tiempo de descanso que hay entre cada actualizacion del
     * tablero
     * El cuarto dato es si admite o no colores la consola que estas utilizando (un
     * 0 es que admite, un 1 no admite)
     * 
     * @param valoresAnteriores Es el array de configuracion anterior
     * @return Devuelve el array de configuracion
     */
    public static int[] cambiarConfiguracion(int[] valoresAnteriores) {
        /* ----- Parte declarativa ----- */
        int[] dimensiones = { valoresAnteriores[0], valoresAnteriores[1] };
        int tiempoMilisegundos = valoresAnteriores[2];
        int admiteColores = valoresAnteriores[3];

        int option = 0;

        /* ----- Parte principal ----- */
        do {
            System.out.println("\n\n\n");
            System.out.println("Apartado de configuracion, escriba la opcion que desee:");
            System.out.println("1. Cambiar dimensiones, los valores actuales son: " + dimensiones[0] + " filas y "
                    + dimensiones[1] + " columnas");
            System.out.println(
                    "2. Cambiar velocidad del juego, el valor actual es: " + tiempoMilisegundos + " milisegundos");
            System.out.println("3. Habilitar colores, el valor actual es: " + (admiteColores == 1 ? "Si" : "No")
                    + " admite colores");
            System.out.println("4. Salir al menu principal");

            option = Utilidades.pedirNumeroEntero("Introduce una opcion entre", 1, 4);
            switch (option) {
                case 1:
                    dimensiones = cambioDimensiones(dimensiones);
                    break;
                case 2:
                    tiempoMilisegundos = cambioTiempo(tiempoMilisegundos);
                    break;
                case 3:
                    admiteColores = cambioColores(admiteColores);
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (option != 4);
        /* ----- Envio de datos ----- */
        int[] array = { dimensiones[0], dimensiones[1], tiempoMilisegundos, admiteColores };
        return array;
    }

    /**
     * Modifica las dimensiones actuales del tablero si el usuario decide
     * cambiarlas.
     * Muestra las dimensiones actuales y permite al usuario ingresar nuevas
     * dimensiones dentro de un rango especificado.
     * 
     * @param valoresAnteriores Array que contiene las dimensiones anteriores:
     *                          [numero de filas, numero de columnas].
     * @return Array con las nuevas dimensiones: [numero de filas, numero de
     *         columnas].
     */

    public static int[] cambioDimensiones(int[] valoresAnteriores) {
        /* ----- Parte declarativa ----- */
        int[] nuevasDimensiones = valoresAnteriores;

        String opcion;
        /* ----- Parte principal ----- */
        System.out.println("\n\n\n");
        System.out.println(
                "Los valores actuales son: " + valoresAnteriores[0] + " filas y " + valoresAnteriores[1] + " columnas");
        System.out.println("¿Quieres cambiarlos? (S o N)");

        do {
            opcion = Utilidades.pedirString();
            if (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N"))
                System.out.println(
                        "Porfavor, escriba una opcion valida (\"S\" para cambiar el valor, \"N\" para no cambiar el valor): ");
        } while (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N"));

        if (!opcion.equalsIgnoreCase("S")) {
            System.out.println("No se ha cambiado el valor");
        } else {
            System.out.println("Escribe el nuevo numero de filas: ");
            System.out.println("Escriba un numero entre 3 y 40");
            nuevasDimensiones[0] = Utilidades.pedirNumeroEntero("Escribe el nuevo numero de filas entre: ", 3, 40);

            System.out.println("Escribe el nuevo numero de columnas: ");
            System.out.println("Escriba un numero entre 3 y 80");
            nuevasDimensiones[1] = Utilidades.pedirNumeroEntero("Escribe el nuevo numero de columnas entre: ", 3, 80);

            System.out.println("Los nuevos valores son: " + nuevasDimensiones[0] + " filas y " + nuevasDimensiones[1]
                    + " columnas");
        }

        System.out.println("Presiona enter para continuar");
        Utilidades.presionarEnter();
        /* ----- Envio de datos ----- */
        return nuevasDimensiones;
    }

    /**
     * Cambia el tiempo de actualización del tablero de juego.
     * Permite al usuario ajustar la velocidad del juego especificando
     * un nuevo tiempo de espera en milisegundos.
     *
     * @param valorAnteriores El tiempo de espera actual en milisegundos.
     * @return El nuevo tiempo de espera en milisegundos, ajustado por el usuario.
     */

    public static int cambioTiempo(int valorAnteriores) {
        /* ----- Parte declarativa ----- */
        int nivelDificultad = valorAnteriores;

        String opcion;
        /* ----- Parte principal ----- */
        System.out.println("\n\n\n");
        System.out.println("El valor actual es: " + valorAnteriores + " milisegundos");
        System.out.println("¿Quieres cambiarlo? (S o N)");

        do {
            opcion = Utilidades.pedirString();
            if (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N"))
                System.out.println(
                        "Porfavor, escriba una opcion valida (\"S\" para cambiar el valor, \"N\" para no cambiar el valor): ");
        } while (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N"));

        if (opcion.equalsIgnoreCase("S")) {

            System.out.println("Escribe el nuevo valor: ");
            System.out.println("Escriba un numero entre 375 y 5000 milisegundos");

            System.out.println("375 milisegundos es la dificultad extrema");
            System.out.println("500 milisegundos es la dificultad dificil");
            System.out.println("750 milisegundos es la dificultad normal");
            System.out.println("1000 milisegundos es la dificultad facil");
            System.out.println("5000 milisegundos es el tiempo maximo");
            nivelDificultad = Utilidades.pedirNumeroEntero("Escribe el nuevo valor entre: ", 375, 5000);

            System.out.println("El nuevo valor es: " + nivelDificultad + " milisegundos");
        }
        System.out.println("Presiona enter para continuar");
        Utilidades.presionarEnter();
        /* ----- Envio de datos ----- */
        return nivelDificultad;
    }

    /**
     * Cambia la configuración de colores de la consola.
     * Permite al usuario habilitar o deshabilitar los colores
     * en la interfaz de consola mediante una entrada manual.
     *
     * @param valorAnteriores El valor actual indicando si la consola
     *                        admite colores (0 para admite, 1 para no admite).
     * @return El nuevo valor indicando si la consola admite colores
     *         después de la modificación.
     */

    public static int cambioColores(int valorAnteriores) {
        /* ----- Parte declarativa ----- */
        int admiteColores = valorAnteriores;

        String opcion;
        /* ----- Parte principal ----- */
        System.out.println("\n\n\n");
        System.out.println("Si tu consola admite colores a continuacion se tendria que ver la letra \"R\" en rojo: "
                + ColoresConsola.ANSI_RED() + "R" + ColoresConsola.ANSI_RESET());
        System.out.println("Si no es asi, tu consola no admite colores");
        System.out.println("El valor actual es: " + (valorAnteriores == 1 ? "Si" : "No") + " admite colores");
        System.out.println("¿Quieres cambiarlos? (S o N)");

        do {
            opcion = Utilidades.pedirString();
            if (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N"))
                System.out.println(
                        "Porfavor, escriba una opcion valida (\"S\" para cambiar el valor, \"N\" para no cambiar el valor): ");

            if (opcion.equalsIgnoreCase("S")) {
                admiteColores = admiteColores == 1 ? 0 : 1;
            }
        } while (!opcion.equalsIgnoreCase("S") && !opcion.equalsIgnoreCase("N"));

        System.out.println(
                opcion.equalsIgnoreCase("S") ? admiteColores == 1 ? "Colores habilitados" : "Colores deshabilitados"
                        : "No se ha cambiado el valor");
        System.out.println("Presiona enter para continuar");
        Utilidades.presionarEnter();
        /* ----- Envio de datos ----- */
        return admiteColores;
    }
}
