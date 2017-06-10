package mensajeria;

import java.io.Serializable;

/**
 * Controla el movimiento del personaje. <br>
 */
@SuppressWarnings("serial")
public class PaqueteMovimiento extends Paquete implements Serializable, Cloneable {
	/**
	 * ID del personaje. <br>
	 */
	private int id;
	/**
	 * Posición X en el mapa. <br>
	 */
	private float posX;
	/**
	 * Posición Y en el mapa. <br>
	 */
	private float posY;
	/**
	 * Dirección. <br>
	 */
	private int direccion;
	/**
	 * Frame del juego. <br>
	 */
	private int frame;

	/**
	 * Crea los comandos de movimiento. <br>
	 */
	public PaqueteMovimiento() {
		setComando(Comando.MOVIMIENTO);
	}

	/**
	 * Crea los comandos de movimiento de un personaje. <br>
	 * 
	 * @param idPersonaje
	 *            ID del personaje. <br>
	 */
	public PaqueteMovimiento(final int idPersonaje) {
		id = idPersonaje;
		setComando(Comando.MOVIMIENTO);
	}

	/**
	 * Crea los comandos de movimiento de un personaje. <br>
	 * 
	 * @param idPersonaje
	 *            ID del personaje. <br>
	 * @param posX
	 *            Posición X. <br>
	 * @param posY
	 *            Posición Y. <br>
	 */
	public PaqueteMovimiento(final int idPersonaje, final float posX, final float posY) {
		this.id = idPersonaje;
		this.posX = posX;
		this.posY = posY;
		setComando(Comando.MOVIMIENTO);
	}

	/**
	 * Devuelve el ID del personaje. <br>
	 * 
	 * @return ID personaje. <br>
	 */
	public int getIdPersonaje() {
		return id;
	}

	/**
	 * Establece el ID del personaje. <br>
	 * 
	 * @param idPersonaje
	 *            ID del personaje. <br>
	 */
	public void setIdPersonaje(final int idPersonaje) {
		this.id = idPersonaje;
	}

	/**
	 * Devuelve la posición X. <br>
	 * 
	 * @return Posición X. <br>
	 */
	public float getPosX() {
		return posX;
	}

	/**
	 * Establece la posición X. <br>
	 * 
	 * @param posX
	 *            Posición X. <br>
	 */
	public void setPosX(final float posX) {
		this.posX = posX;
	}

	/**
	 * Devuelve la posición Y. <br>
	 * 
	 * @return Posición Y. <br>
	 */
	public float getPosY() {
		return posY;
	}

	/**
	 * Establece la posición Y. <br>
	 * 
	 * @param posY
	 *            Posición Y. <br>
	 */
	public void setPosY(final float posY) {
		this.posY = posY;
	}

	/**
	 * Devuelve la dirección. <br>
	 * 
	 * @return Dirección. <br>
	 */
	public int getDireccion() {
		return direccion;
	}

	/**
	 * Establece la dirección. <br>
	 * 
	 * @param direccion
	 *            Dirección. <br>
	 */
	public void setDireccion(final int direccion) {
		this.direccion = direccion;
	}

	/**
	 * Devuelve el frame. <br>
	 * 
	 * @return Frame. <br>
	 */
	public int getFrame() {
		return frame;
	}

	/**
	 * Establece el frame. <br>
	 * 
	 * @param frame
	 *            Frame. <br>
	 */
	public void setFrame(final int frame) {
		this.frame = frame;
	}

	/**
	 * Clona el movimiento. <br>
	 */
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}