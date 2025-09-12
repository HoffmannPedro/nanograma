package logica;

public class Juego {
	
	// Ejemplo de solución fija (5x5)
	// 1 = NEGRO, 0 = BLANCO
	private static final int [][] SOLUCION = {
			{1 ,0 ,0 ,0 ,1},
			{0, 1, 0, 1, 0},
	        {0, 0, 1, 0, 0},
	        {0, 1, 0, 1, 0},
	        {1, 0, 0, 0, 1}
	};
	
	public static int[][] getSolucion() {
		return SOLUCION;
	}
}
