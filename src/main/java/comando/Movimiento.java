package comando;

import com.google.gson.Gson;

import mensajeria.PaqueteDeMovimientos;

/**
 * Clase que admninistra el movimiento de los personajes. <br>
 */
public class Movimiento extends MensajesComandos {
	/**
	 * Gson. <br>
	 */
	private final Gson gson = new Gson();

	/**
	 * Ejecuta el movimiento de los personajes y actualiza su ubicación en el
	 * mapa. <br>
	 */
	@Override
	public void ejecutar() {
		PaqueteDeMovimientos paqueteDeMovimientos = (PaqueteDeMovimientos) gson.fromJson(super.string,
				PaqueteDeMovimientos.class);
		super.juego.setUbicacionPersonajes(paqueteDeMovimientos.getPersonajes());
	}
}