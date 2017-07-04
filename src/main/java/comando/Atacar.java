package comando;

import com.google.gson.Gson;

import mensajeria.PaqueteAtacar;

/**
 * Clase que administra el ataque entre personajes, actualizando los stats del
 * combate. <br>
 */
public class Atacar extends ClienteComandos {
	/**
	 * Gson. <br>
	 */
	private final Gson gson = new Gson();

	/**
	 * Ejecuta el ataque en el combate entre personajes. <br>
	 */
	@Override
	public void ejecutar() {
		PaqueteAtacar paqueteAtacar = (PaqueteAtacar) gson.fromJson(super.string, PaqueteAtacar.class);
		juego.getEstadoBatalla().getEnemigo().actualizarAtributos(paqueteAtacar.getMapPersonaje());
		juego.getEstadoBatalla().getPersonaje().actualizarAtributos(paqueteAtacar.getMapEnemigo());
		juego.getEstadoBatalla().setMiTurno(true);
	}
}