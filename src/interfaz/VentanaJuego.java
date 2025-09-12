package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logica.Celda;
import logica.Juego;
import logica.Pistas;
import logica.Tablero;
import logica.Validador;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class VentanaJuego extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaJuego frame = new VentanaJuego();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JButton[][] botones = new JButton[5][5];
	private Tablero tablero = new Tablero(5); // Lógica
	private int[][] solucion = Juego.getSolucion();
	
	private JPanel panelFilas;
	private JPanel panelColumnas;

	/**
	 * Create the frame.
	 */
	public VentanaJuego() {
		setTitle("Nanograma");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		// Se crean paneles de ordenamiento
		JPanel panelGrilla = new JPanel();
		getContentPane().add(panelGrilla);
		panelGrilla.setLayout(new GridLayout(5, 5, 0, 0));
		
		panelFilas = new JPanel();
		getContentPane().add(panelFilas, BorderLayout.WEST);
		panelFilas.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelColumnas = new JPanel();
		getContentPane().add(panelColumnas, BorderLayout.NORTH);
		panelColumnas.setLayout(new GridLayout(1, 6, 0, 0));
		
		JPanel panelSur = new JPanel();
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		
		// Se crean layouts para las pistas
		
		// Pistas de Columnas
		JLabel pistaCol00 = new JLabel("-");
		panelColumnas.add(pistaCol00);
		
		JLabel pistaCol01 = new JLabel("-");
		panelColumnas.add(pistaCol01);
		
		JLabel pistaCol02 = new JLabel("-");
		panelColumnas.add(pistaCol02);
		
		JLabel pistaCol03 = new JLabel("-");
		panelColumnas.add(pistaCol03);
		
		JLabel pistaCol04 = new JLabel("-");
		panelColumnas.add(pistaCol04);
		
		// Pistas de Filas
		JLabel pistaFila10 = new JLabel("-");
		panelFilas.add(pistaFila10);
		
		JLabel pistaFila20 = new JLabel("-");
		panelFilas.add(pistaFila20);
		
		JLabel pistaFila30 = new JLabel("-");
		panelFilas.add(pistaFila30);
		
		JLabel pistaFila40 = new JLabel("-");
		panelFilas.add(pistaFila40);
		
		JLabel pistaFila50 = new JLabel("-");
		panelFilas.add(pistaFila50);
		
		// Botón de validación
		JButton btnComprobar = new JButton("Comprobar solución");
		panelSur.add(btnComprobar);
		
		btnComprobar.addActionListener(e -> {
			if (Validador.esCorrecto(tablero, solucion)) {
				JOptionPane.showMessageDialog(this, 
						"Felicidades, has completado el Nanograma",
						"Victoria", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, 
						"La solución aún no es correcta. Sigue intentando.", 
			            "Error", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		actualizarPistas(solucion);
		
		// Crea los botones para cada casillero de la grilla y los dristribuye en cada una de las celdas
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				JButton btn = new JButton();
				botones[i][j] = btn;
				
				// Arranca en blanco
				actualizarBoton(btn, tablero.getCelda(i,j));
				
				// Cuando se clickea
				final int fila = i;
				final int col = j;
				btn.addActionListener(e -> {
					tablero.cambiarEstado(fila,col); // Lógica
					actualizarBoton(btn, tablero.getCelda(fila, col)); // Vista
				});
				
				panelGrilla.add(btn);
			}
		}
		
	}
	
	// Actualiza el estado de las celdas
	private void actualizarBoton(JButton boton, Celda celda) {
		if (celda.getEstado() == Celda.BLANCO) {
			boton.setBackground(Color.LIGHT_GRAY);
			boton.setText("");
		} else if (celda.getEstado() == Celda.NEGRO) {
			boton.setBackground(Color.BLACK);
			boton.setText("");
		} else if (celda.getEstado() == Celda.X) {
			boton.setBackground(Color.WHITE);
			boton.setText("X");
		}
	}
	
	private void actualizarPistas(int[][] solucion) {
		panelFilas.removeAll();
		panelColumnas.removeAll();
		
		List<List<Integer>> pistasFilas = Pistas.generarPistasFilas(solucion);
		List<List<Integer>> pistasColumnas = Pistas.generarPistasColumnas(solucion);
		
		// Panel de filas
		for (List<Integer> fila : pistasFilas) {
			String texto = fila.stream()
								.map(String::valueOf)
								.reduce((a,b) -> a + " " + b)
								.orElse("0");
			JLabel lblFila = new JLabel(texto, SwingConstants.CENTER);
			panelFilas.add(lblFila);
		}
		
		// Panel de columnas
	    for (List<Integer> columna : pistasColumnas) {
	        String texto = columna.stream()
	                              .map(String::valueOf)
	                              .reduce((a,b) -> a + " " + b)
	                              .orElse("0");
	        JLabel lblCol = new JLabel(texto, SwingConstants.CENTER);
	        panelColumnas.add(lblCol);
	    }
	    
	    panelFilas.revalidate();
	    panelFilas.repaint();
	    panelColumnas.revalidate();
	    panelColumnas.repaint();

	}
}
