package juego;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import cliente.Cliente;
import mensajeria.Comando;
import recursos.Recursos;

/**
 * Clase que carga los recursos. <br>
 */
public class CargarRecursos extends Thread {
	/**
	 * ClienteComandos. <br>
	 */
	private Cliente cliente;

	/**
	 * Carga los recursos del cliente. <br>
	 * 
	 * @param cliente
	 *            ClienteComandos. <br>
	 */
	public CargarRecursos(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Corre los recursos del cliente. <br>
	 */
	@Override
	public void run() {
		synchronized (cliente) {
			try {
				Recursos.cargar(cliente.getMenuCarga());
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Fallo al abrir el archivo imagen.");
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Fallo el formato de imagen/distribucion.");
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Fallo importante de archivos de imagen.");
			}
			cliente.setAccion(Comando.SALIR);
			cliente.notify();
		}
	}
}
