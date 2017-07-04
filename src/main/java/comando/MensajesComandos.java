package comando;

import juego.Juego;
import mensajeria.Comando;

/**
 * Clase que administra los comandos de mensajería. <br>
 */
public abstract class MensajesComandos extends Comando {
	/**
	 * Juego. <br>
	 */
	protected Juego juego;

	/**
	 * Establece el juego. <br>
	 * 
	 * @param juego
	 *            Juego. <br>
	 */
	public void setJuego(final Juego juego) {
		this.juego = juego;
	}
}