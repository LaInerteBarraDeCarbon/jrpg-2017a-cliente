package comando;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import mensajeria.PaquetePersonaje;

/**
 * Clase que administra la creación de un personaje. <br>
 */
public class CreacionPJ extends ClienteComandos {
	/**
	 * Gson. <br>
	 */
	private final Gson gson = new Gson();

	/**
	 * Ejecuta la creación de un personaje. <br>
	 */
	@Override
	public void ejecutar() {
		JOptionPane.showMessageDialog(null, "Registro exitoso.");
		super.cliente.setPaquetePersonaje((PaquetePersonaje) gson.fromJson(super.string, PaquetePersonaje.class));
		super.cliente.getPaqueteUsuario().setInicioSesion(true);
	}
}