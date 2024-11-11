package PruebasClases;

public class InstanciamientoClases {
    public static void main(String[] args) {
        /* ----- Creacion del persoinaje con la clase Player */
        Player jugador = new Player("Adsa", 10, 2, 4);
        Player mostruo = new Player("Barbaro", 1, 2, 4);

        jugador.setHealth(4);

        System.out.println(jugador.getName());

    }
}
