package comando;

import cliente.Cliente;
import juego.Juego;
import mensajeria.Comando;

/**
 * Clase que administra los comandos de cliente. <br>
 */
public abstract class ClienteComandos extends Comando {
	/**
	 * Paquete de comandos del cliente. <br>
	 */
	public static final String PACKAGE = "comandos";

	/**
	 * Juego del usuario. <br>
	 */
	protected Juego juego;
	/**
	 * Cliente del usuario. <br>
	 */
	protected Cliente cliente;
	/**
	 * Cadena. <br>
	 */
	protected String string;

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