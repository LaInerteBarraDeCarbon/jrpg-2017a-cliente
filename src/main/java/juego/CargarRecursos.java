package juego;

import java.io.FileNotFoundException;
import java.io.IOException;

import cliente.Cliente;
import mensajeria.Comando;
import recursos.Recursos;

/**
 * Clase que carga los recursos. <br>
 */
public class CargarRecursos extends Thread {
	/**
	 * Cliente. <br>
	 */
	private Cliente cliente;

	/**
	 * Carga los recursos del cliente. <br>
	 * 
	 * @param cliente
	 *            Cliente. <br>
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
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			cliente.setAccion(Comando.SALIR);
			cliente.notify();
		}
	}
}
