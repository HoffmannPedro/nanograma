package logica;

import java.util.List;

public class Main {
	public static void main(String[] args) {
        int[][] solucion = Juego.getSolucion();
        Tablero jugador = new Tablero(5);

        // Jugador hace algo
        jugador.setCelda(0, 0, Celda.NEGRO);
        jugador.setCelda(0, 1, Celda.NEGRO);
        jugador.setCelda(1, 1, Celda.NEGRO);
        jugador.setCelda(1, 2, Celda.NEGRO);
        jugador.setCelda(2, 3, Celda.NEGRO);
        jugador.setCelda(3, 0, Celda.NEGRO);
        jugador.setCelda(3, 1, Celda.NEGRO);
        jugador.setCelda(3, 2, Celda.NEGRO);
        jugador.setCelda(4, 2, Celda.NEGRO);
        jugador.setCelda(4, 3, Celda.NEGRO);

        jugador.imprimir();

        boolean gano = Validador.esCorrecto(jugador, solucion);
        System.out.println("\n¿Ganó el jugador?: " + gano);
    }
}
