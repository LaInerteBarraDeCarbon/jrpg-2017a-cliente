package mensajeria;

import java.io.Serializable;

/**
 * Administra la condición de batalla entre el personaje y su adversario. <br>
 */
@SuppressWarnings("serial")
public class PaqueteBatalla extends Paquete implements Serializable, Cloneable {

	/**
	 * ID del personaje. <br>
	 */
	private int id;
	/**
	 * ID del enemigo. <br>
	 */
	private int idEnemigo;
	/**
	 * Condición de turno actual. <br>
	 */
	private boolean miTurno;

	/**
	 * Establece los comandos de batalla. <br>
	 */
	public PaqueteBatalla() {
		setComando(Comando.BATALLA);
	}

	/**
	 * Devuelve el ID del personaje. <br>
	 * 
	 * @return ID personaje. <br>
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el ID del personaje. <br>
	 * 
	 * @param id
	 *            ID del personaje. <br>
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Devuelve el ID del enemigo. <br>
	 * 
	 * @return ID enemigo. <br>
	 */
	public int getIdEnemigo() {
		return idEnemigo;
	}

	/**
	 * Establece el ID del enemigo. <br>
	 * 
	 * @param idEnemigo
	 *            ID del enemigo. <br>
	 */
	public void setIdEnemigo(int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}

	/**
	 * Devuelve si el turno siguiente puede realizar una acción. <br>
	 * 
	 * @return True si es su turno, false de lo contrario. <br>
	 */
	public boolean isMiTurno() {
		return miTurno;
	}

	/**
	 * Establece el turno del jugador. <br>
	 * 
	 * @param miTurno
	 *            Condición del turno. <br>
	 */
	public void setMiTurno(boolean miTurno) {
		this.miTurno = miTurno;
	}
}
