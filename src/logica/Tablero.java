package logica;

public class Tablero {
	
	private Celda[][] grilla;
	private int size;
	
	public Tablero(int size) {
		this.size = size;
		this.grilla = new Celda[size][size];
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				grilla[i][j] = new Celda();
			}
		}
	}
	
	public void setCelda(int fila, int col, int estado) {
		grilla[fila][col].setEstado(estado);
	}
	
	public int getCelda(int fila, int col) {
		return grilla[fila][col].getEstado();
	}
	
	public int getSize() {
		return size;
	}
	
	public void imprimir() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int estado = grilla[i][j].getEstado();
				char simbolo;
				if (estado == Celda.BLANCO) simbolo = '.';
				else if (estado == Celda.NEGRO) simbolo = ' ';
				else simbolo = 'X';
				System.out.print(simbolo + " ");
			}
			System.out.println();
		}
	}

}
