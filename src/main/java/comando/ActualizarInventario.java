package comando;

import com.google.gson.Gson;

import mensajeria.PaquetePersonaje;

/**
 * Clase que administra la actualización del inventario. <br>
 */
public class ActualizarInventario extends ClienteComandos {
	/**
	 * Gson. <br>
	 */
	private final Gson gson = new Gson();

	/**
	 * Ejecuta la actualización del inventario. <br>
	 */
	@Override
	public void ejecutar() {
		PaquetePersonaje paquetePersonaje = (PaquetePersonaje) gson.fromJson(super.string, PaquetePersonaje.class);
		super.juego.getPersonajesConectados().remove(paquetePersonaje.getId());
		super.juego.getPersonajesConectados().put(paquetePersonaje.getId(), paquetePersonaje);
		if (super.juego.getPersonaje().getId() == paquetePersonaje.getId()) {
			super.juego.actualizarPersonaje();
			super.juego.getEstadoJuego().actualizarPersonaje();
			super.juego.getCliente().actualizarItems(paquetePersonaje);
			super.juego.getCliente()
					.actualizarPersonaje(super.juego.getPersonajesConectados().get(paquetePersonaje.getId()));

		}
	}
}