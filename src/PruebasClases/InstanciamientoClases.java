package PruebasClases;

public class InstanciamientoClases {
    public static void main(String[] args) {
        /* ----- Creacion del persoinaje con la clase Player */
        Player jugador = new Player("", 0);

        jugador.setName("Adsa");
        jugador.setScore(100);

        System.out.println(jugador.getName() + " tiene una puntuacion de " + jugador.getScore() + " puntos.");

    }
}
