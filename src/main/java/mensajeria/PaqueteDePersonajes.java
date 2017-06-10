package mensajeria;

import java.io.Serializable;
import java.util.Map;

/**
 * Clase que administra los distintos personajes. <br>
 */
@SuppressWarnings("serial")
public class PaqueteDePersonajes extends Paquete implements Serializable, Cloneable {

	/**
	 * Personajes del juego. <br>
	 */
	private Map<Integer, PaquetePersonaje> personajes;

	/**
	 * Paquete básico de personajes. <br>
	 */
	public PaqueteDePersonajes() {

	}

	/**
	 * Crea la lista con los personajes. <br>
	 * 
	 * @param personajes
	 *            Lista de los personajes. <br>
	 */
	public PaqueteDePersonajes(final Map<Integer, PaquetePersonaje> personajes) {
		this.personajes = personajes;
	}

	/**
	 * Devuelve los distintos personajes. <br>
	 * 
	 * @return Personajes. <br>
	 */
	public Map<Integer, PaquetePersonaje> getPersonajes() {
		return personajes;
	}

	/**
	 * Clona los personajes existentes. <br>
	 */
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}