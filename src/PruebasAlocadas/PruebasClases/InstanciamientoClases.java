package PruebasAlocadas.PruebasClases;

public class InstanciamientoClases {
    public static void main(String[] args) {
        /* ----- Creacion del persoinaje con la clase Player */
        Player jugador = new Player("", 0, null);

        jugador.setName("Adsa");
        jugador.setScore(100);

        System.out.println(jugador.getName() + " tiene una puntuacion de " + jugador.getScore() + " puntos.");

        jugador.setCongiguration(new int[]{1, 2, 3, 4});

        System.out.println("La configuracion es: " + jugador.getCongiguration()[0] + " " + jugador.getCongiguration()[1] + " " + jugador.getCongiguration()[2] + " " + jugador.getCongiguration()[3]);

        //Si quiero cambiar la segunda configuracion del jugador
        jugador.getCongiguration()[1] = 5;

        System.out.println("La configuracion es: " + jugador.getCongiguration()[0] + " " + jugador.getCongiguration()[1] + " " + jugador.getCongiguration()[2] + " " + jugador.getCongiguration()[3]);
    
        System.out.println("---------------- Prueba de inmutabilidad ---------------");
        System.out.println("Antes de la prueba: " + jugador.getScore());
        PruebaInmutabilidad(jugador);
        System.out.println("Despues de la prueba: " + jugador.getScore());
    }

    public static void PruebaInmutabilidad(Player player) {
        player.setScore(player.getScore() + 1);
    }
}
