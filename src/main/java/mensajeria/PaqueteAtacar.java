package mensajeria;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Paquete que se encarga de los estados de vida y energía del personaje y su
 * enemigo al momento de entrar en batalla. <br>
 */
@SuppressWarnings("serial")
public class PaqueteAtacar extends Paquete implements Serializable, Cloneable {

	/**
	 * ID del personaje. <br>
	 */
	private int id;
	/**
	 * ID del enemigo. <br>
	 */
	private int idEnemigo;
	/**
	 * Nueva salud del personaje. <br>
	 */
	private int nuevaSaludPersonaje;
	/**
	 * Nueva energía del personaje. <br>
	 */
	private int nuevaEnergiaPersonaje;
	/**
	 * Nueva salud del enemigo. <br>
	 */
	private int nuevaSaludEnemigo;
	/**
	 * Nueva energía del enemigo. <br>
	 */
	private int nuevaEnergiaEnemigo;
	/**
	 * Atributos del personaje. <br>
	 */
	private HashMap<String, Integer> atributos;

	/**
	 * Establece la condición actual del personaje y su enemigo antes de entrar
	 * a la batalla. <br>
	 * 
	 * @param id
	 *            ID del personaje. <br>
	 * @param idEnemigo
	 *            ID del enemigo. <br>
	 * @param nuevaSalud
	 *            Nueva salud del personaje. <br>
	 * @param nuevaEnergia
	 *            Nueva energía del personaje. <br>
	 * @param nuevaSaludEnemigo
	 *            Nueva salud del enemigo. <br>
	 * @param nuevaEnergiaEnemigo
	 *            Nueva energía del enemigo. <br>
	 */
	public PaqueteAtacar(int id, int idEnemigo, int nuevaSalud, int nuevaEnergia, int nuevaSaludEnemigo,
			int nuevaEnergiaEnemigo) {
		setComando(Comando.ATACAR);
		this.id = id;
		this.idEnemigo = idEnemigo;
		this.nuevaSaludPersonaje = nuevaSalud;
		this.nuevaEnergiaPersonaje = nuevaEnergia;
		this.nuevaSaludEnemigo = nuevaSaludEnemigo;
		this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
		this.atributos = new HashMap<String, Integer>();
	}

	/**
	 * Devuelve la ID del personaje. <br>
	 * 
	 * @return ID personaje. <br>
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece la ID del personaje. <br>
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
	 * Devuelve la nueva salud del personaje. <br>
	 * 
	 * @return Nueva salud. <br>
	 */
	public int getNuevaSaludPersonaje() {
		return nuevaSaludPersonaje;
	}

	/**
	 * Establece la salud del personaje. <br>
	 * 
	 * @param nuevaSaludPersonaje
	 *            Nueva salud del personaje. <br>
	 */
	public void setNuevaSaludPersonaje(int nuevaSaludPersonaje) {
		this.nuevaSaludPersonaje = nuevaSaludPersonaje;
	}

	/**
	 * Devuelve la nueva energía del personaje. <br>
	 * 
	 * @return Nueva energía del personaje. <br>
	 */
	public int getNuevaEnergiaPersonaje() {
		return nuevaEnergiaPersonaje;
	}

	/**
	 * Establece la nueva energía del personaje. <br>
	 * 
	 * @param nuevaEnergiaPersonaje
	 *            Nueva energía del personaje. <br>
	 */
	public void setNuevaEnergiaPersonaje(int nuevaEnergiaPersonaje) {
		this.nuevaEnergiaPersonaje = nuevaEnergiaPersonaje;
	}

	/**
	 * Devuelve la nueva vida del enemigo. <br>
	 * 
	 * @return Nueva vida del enemigo. <br>
	 */
	public int getNuevaSaludEnemigo() {
		return nuevaSaludEnemigo;
	}

	/**
	 * Establece la nueva salud del enemigo. <br>
	 * 
	 * @param nuevaSaludEnemigo
	 *            Nueva salud del enemigo. <br>
	 */
	public void setNuevaSaludEnemigo(int nuevaSaludEnemigo) {
		this.nuevaSaludEnemigo = nuevaSaludEnemigo;
	}

	/**
	 * Devuelve la nueva energía del enemigo. <br>
	 * 
	 * @return Nueva energía del enemigo. <br>
	 */
	public int getNuevaEnergiaEnemigo() {
		return nuevaEnergiaEnemigo;
	}

	/**
	 * Establece la nueva energía del enemigo. <br>
	 * 
	 * @param nuevaEnergiaEnemigo
	 *            Nueva energía del enemigo. <br>
	 */
	public void setNuevaEnergiaEnemigo(int nuevaEnergiaEnemigo) {
		this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
	}

	/**
	 * Devuelve los atributos de los personajes. <br>
	 * 
	 * @return Atributos. <br>
	 */
	public HashMap<String, Integer> getAtributos() {
		return atributos;
	}

	/**
	 * Encapsula los distintos atributos del personaje. <br>
	 */
	public void encapsularAtributos() {
		this.atributos.put("salud" + this.getId(), this.getNuevaSaludPersonaje());
		this.atributos.put("energia" + this.getId(), this.getNuevaEnergiaPersonaje());
		this.atributos.put("salud" + this.getIdEnemigo(), this.getNuevaSaludEnemigo());
		this.atributos.put("energia" + this.getIdEnemigo(), this.getNuevaEnergiaEnemigo());
	}
}
