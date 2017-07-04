package comando;

import com.google.gson.Gson;

import estados.Estado;
import mensajeria.PaqueteFinalizarBatalla;

public class FinalizarBatalla extends MensajesComandos {
	/**
	 * Gson. <br>
	 */
	private final Gson gson = new Gson();

	@Override
	public void ejecutar() {
		PaqueteFinalizarBatalla paqueteFinalizarBatalla = (PaqueteFinalizarBatalla) gson.fromJson(super.string,
				PaqueteFinalizarBatalla.class);
		juego.getPersonaje().setEstado(Estado.ESTADOJUEGO);
		Estado.setEstado(super.juego.getEstadoJuego());
	}
}
