package comando;

import java.io.IOException;

import javax.swing.JOptionPane;

import mensajeria.Comando;
import mensajeria.Paquete;

/**
 * Clase que administra salida del juego. <br>
 */
public class Salir extends ClienteComandos {

	/**
	 * Ejecuta la salida del juego. <br>
	 */
	@Override
	public void ejecutar() {
		super.cliente.getPaqueteUsuario().setInicioSesion(false);
		try {
			super.cliente.getSalida().writeObject(new Paquete(Comando.DESCONECTAR));
			super.cliente.getSocket().close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Notará que salir del juego es practicamente imposible.\nMUAJAJAJAJA.");
		}
	}
}
