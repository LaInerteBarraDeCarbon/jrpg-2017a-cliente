package estados;

import java.awt.Graphics;

import juego.Juego;

/**
 * Clase que indica el estado del jugador. <br>
 */
public abstract class Estado {

	/**
	 * Estado del jugador. <br>
	 */
	private static Estado estadoActual = null;

	// Tipo de estados
	/**
	 * Estado fuera de linea. <br>
	 */
	public static int estadoOffline = 0;
	/**
	 * Estado de conectado. <br>
	 */
	public static int estadoJuego = 1;
	/**
	 * Estado dentro de batalla. <br>
	 */
	public static int estadoBatalla = 2;

	/**
	 * Juego del usuario. <br>
	 */
	protected Juego juego;

	/**
	 * Establece el juego del usuario. <br>
	 * 
	 * @param juego
	 *            Juego. <br>
	 */
	public Estado(Juego juego) {
		this.juego = juego;
	}

	/**
	 * Actualiza el estado del jugador al momento actual. <br>
	 */
	public abstract void actualizar();

	public abstract void graficar(Graphics g);

	/**
	 * Establece el estado del jugador. <br>
	 * 
	 * @param estado
	 *            Estado actual. <br>
	 */
	public static void setEstado(Estado estado) {
		estadoActual = estado;
	}

	/**
	 * Devuelve es estado actual del jugador. <br>
	 * 
	 * @return Estado actual. <br>
	 */
	public static Estado getEstado() {
		return estadoActual;
	}

	/**
	 * Indica si es estado de juegos. <br>
	 * 
	 * @return true si es estado de juego, false de lo contrario. <br>
	 */
	public abstract boolean esEstadoDeJuego();
}
