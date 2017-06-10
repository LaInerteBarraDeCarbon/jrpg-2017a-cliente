package mensajeria;

import java.io.Serializable;
import java.util.Map;

/**
 * Clase que lleva constancia de la posición de los personajes en el mapa. <br>
 */
@SuppressWarnings("serial")
public class PaqueteDeMovimientos extends Paquete implements Serializable, Cloneable {
	/**
	 * Lista con los movimientos de los personajes. <br>
	 */
	private Map<Integer, PaqueteMovimiento> personajes;

	/**
	 * Crea una lista con la ubicación de los personajes. <br>
	 */
	public PaqueteDeMovimientos() {

	}

	/**
	 * Crea una lista con la ubicación de los personajes. <br>
	 * 
	 * @param personajes
	 *            Personajes. <br>
	 */
	public PaqueteDeMovimientos(final Map<Integer, PaqueteMovimiento> personajes) {
		this.personajes = personajes;
	}

	/**
	 * Devuelve la ubicación de los personajes. <br>
	 * 
	 * @return Ubicación personajes. <br>
	 */
	public Map<Integer, PaqueteMovimiento> getPersonajes() {
		return personajes;
	}

	/**
	 * Clona la ubicación de los personajes. <br>
	 */
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}