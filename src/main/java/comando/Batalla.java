package comando;

import com.google.gson.Gson;

import estados.Estado;
import estados.EstadoBatalla;
import mensajeria.PaqueteBatalla;

/**
 * Clase que aministra el estado de batalla del personajes. <br>
 */
public class Batalla extends MensajesComandos {
	/**
	 * Gson. <br>
	 */
	private final Gson gson = new Gson();

	/**
	 * Ejecuta la batalla entre personajes. <br>
	 */
	@Override
	public void ejecutar() {
		PaqueteBatalla paqueteBatalla = (PaqueteBatalla) gson.fromJson(super.string, PaqueteBatalla.class);
		juego.getPersonaje().setEstado(Estado.ESTADOBATALLA);
		Estado.setEstado(null);
		juego.setEstadoBatalla(new EstadoBatalla(juego, paqueteBatalla));
		Estado.setEstado(juego.getEstadoBatalla());
	}
}
