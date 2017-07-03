package comando;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import frames.MenuCreacionPj;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;

/**
 * Clase que administra el registro de un personaje. <br>
 */
public class Registro extends ClienteComandos {
	/**
	 * Gson. <br>
	 */
	private final Gson gson = new Gson();

	/**
	 * Ejecuta el registro de creación de un personaje. <br>
	 */
	@Override
	public void ejecutar() {
		synchronized (this) {
			PaquetePersonaje paquetePersonaje = super.cliente.getPaquetePersonaje();
			PaqueteUsuario paqueteUsuario = super.cliente.getPaqueteUsuario();
			if (paquete.getMensaje().equals(Paquete.MSJEXITO)) {
				// Abro el menu para la creación del personaje
				MenuCreacionPj menuCreacionPJ = new MenuCreacionPj(this, paquetePersonaje);
				menuCreacionPJ.setVisible(true);
				// Espero a que el usuario cree el personaje
				try {
					wait();
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null, "Error de creación de personaje.");
					paqueteUsuario.setInicioSesion(false);
				}
				// Le envio los datos al servidor
				paquetePersonaje.setComando(Comando.CREACIONPJ);
				try {
					super.cliente.getSalida().writeObject(gson.toJson(paquetePersonaje));
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Fallo la creación del personaje.");
					paqueteUsuario.setInicioSesion(false);
				}
				JOptionPane.showMessageDialog(null, "Registro exitoso.");
				// Recibo el paquete personaje con los datos (la id
				// incluida)
				try {
					paquetePersonaje = (PaquetePersonaje) (PaquetePersonaje) gson
							.fromJson((String) super.cliente.getEntrada().readObject(), PaquetePersonaje.class);
				} catch (ClassNotFoundException | IOException e) {
					JOptionPane.showMessageDialog(null, "Fallo la creación del personaje.");
					paqueteUsuario.setInicioSesion(false);
				}
				// Indico que el usuario ya inicio sesion
				paqueteUsuario.setInicioSesion(true);
			} else {
				if (paquete.getMensaje().equals(Paquete.MSJFRACASO)) {
					JOptionPane.showMessageDialog(null, "No se pudo registrar.");
				}
				// El usuario no pudo iniciar sesión
				paqueteUsuario.setInicioSesion(false);
			}
		}
	}
}
