package comando;

import cliente.Cliente;
import juego.Juego;
import mensajeria.Comando;

/**
 *
 */
public abstract class ClienteComandos extends Comando {
	public static final String ALGO = "HH";

	/**
	 * Juego del usuario. <br>
	 */
	protected Juego juego;
	/**
	 * Cliente del usuario. <br>
	 */
	protected Cliente cliente;

	/**
	 * Establece el juego. <br>
	 * 
	 * @param juego
	 *            Juego. <br>
	 */
	public void setJuego(final Juego juego) {
		this.juego = juego;
	}

	/**
	 * Establece el cliente. <br>
	 * 
	 * @param cliente
	 *            Cliente. <br>
	 */
	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}
}
