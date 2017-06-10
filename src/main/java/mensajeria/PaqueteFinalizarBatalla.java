package mensajeria;

import java.io.Serializable;

/**
 * Clase que indica el fin de batalla. <br>
 */
@SuppressWarnings("serial")
public class PaqueteFinalizarBatalla extends Paquete implements Serializable, Cloneable {
	/**
	 * ID del personaje. <br>
	 */
	private int id;
	/**
	 * ID del enemigo. <br>
	 */
	private int idEnemigo;

	/**
	 * Crea los comandos de finalizar batalla. <br>
	 */
	public PaqueteFinalizarBatalla() {
		setComando(Comando.FINALIZARBATALLA);
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
	 *            ID personaje. <br>
	 */
	public void setId(final int id) {
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
	 *            ID enemigo. <br>
	 */
	public void setIdEnemigo(final int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}
}
