package juego;

import java.io.FileNotFoundException;
import java.io.IOException;

import cliente.Cliente;
import mensajeria.Comando;
import recursos.Recursos;

public class CargarRecursos extends Thread {

	private Cliente cliente;

	public CargarRecursos(Cliente cliente) {
		this.cliente = cliente;
	}

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
