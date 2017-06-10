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
	 * Lista con atributos del personaje. <br>
	 */
	private HashMap<String, Number> mapPersonaje = new HashMap<String, Number>();
	/**
	 * Lista con atributos del enemigo. <br>
	 */
	private HashMap<String, Number> mapEnemigo = new HashMap<String, Number>();

	/**
	 * Crea las nuevas valores para un personaje y el enemigo. <br>
	 * 
	 * @param id
	 *            ID personaje. <br>
	 * @param idEnemigo
	 *            ID enemigo. <br>
	 * @param nuevaSalud
	 *            Nueva salud personaje. <br>
	 * @param nuevaEnergia
	 *            Nueva energía personaje. <br>
	 * @param nuevaSaludEnemigo
	 *            Nueva salud enemigo. <br>
	 * @param nuevaEnergiaEnemigo
	 *            Nueva energía enemigo. <br>
	 * @param nuevaDefensa
	 *            Nueva defensa personaje. <br>
	 * @param nuevaDefensaEnemigo
	 *            Nueva defensa enemigo. <br>
	 * @param probEvitarDano
	 *            Probabilidad de evitar daño personaje. <br>
	 * @param probEvitarDanoEnemgio
	 *            Probabilidad de evitar daño enemigo. <br>
	 */
	public PaqueteAtacar(final int id, final int idEnemigo, final int nuevaSalud, final int nuevaEnergia,
			final int nuevaSaludEnemigo, final int nuevaEnergiaEnemigo, final int nuevaDefensa,
			final int nuevaDefensaEnemigo, final double probEvitarDano, final double probEvitarDanoEnemgio) {
		setComando(Comando.ATACAR);
		this.id = id;
		this.idEnemigo = idEnemigo;
		this.nuevaSaludPersonaje = nuevaSalud;
		this.nuevaEnergiaPersonaje = nuevaEnergia;
		this.nuevaSaludEnemigo = nuevaSaludEnemigo;
		this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
		mapPersonaje.put("salud", nuevaSalud);
		mapPersonaje.put("energia", nuevaEnergia);
		mapPersonaje.put("defensa", nuevaDefensa);
		mapPersonaje.put("probEvitarDanio", probEvitarDano);
		mapEnemigo.put("salud", nuevaSaludEnemigo);
		mapEnemigo.put("energia", nuevaEnergiaEnemigo);
		mapEnemigo.put("defensa", nuevaDefensaEnemigo);
		mapEnemigo.put("probEvitarDanio", probEvitarDanoEnemgio);
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

	/**
	 * Devuelve la nueva salud del personaje. <br>
	 * 
	 * @return Nueva salud personaje. <br>
	 */
	public int getNuevaSaludPersonaje() {
		return nuevaSaludPersonaje;
	}

	/**
	 * Establece la nueva salud del personaje. <br>
	 * 
	 * @param nuevaSaludPersonaje
	 *            Nueva salud personaje. <br>
	 */
	public void setNuevaSaludPersonaje(final int nuevaSaludPersonaje) {
		this.nuevaSaludPersonaje = nuevaSaludPersonaje;
	}

	/**
	 * Devuelve la nueva energía del personaje. <br>
	 * 
	 * @return Nueva energía personaje. <br>
	 */
	public int getNuevaEnergiaPersonaje() {
		return nuevaEnergiaPersonaje;
	}

	/**
	 * Establece la energía del personaje. <br>
	 * 
	 * @param nuevaEnergiaPersonaje
	 *            Nueva energía personaje. <br>
	 */
	public void setNuevaEnergiaPersonaje(final int nuevaEnergiaPersonaje) {
		this.nuevaEnergiaPersonaje = nuevaEnergiaPersonaje;
	}

	/**
	 * Devuelve la nueva salud del enemigo. <br>
	 * 
	 * @return Nueva salud enemigo. <br>
	 */
	public int getNuevaSaludEnemigo() {
		return nuevaSaludEnemigo;
	}

	/**
	 * Establece la nueva salud del enemigo. <br>
	 * 
	 * @param nuevaSaludEnemigo
	 *            Nueva salud enemigo. <br>
	 */
	public void setNuevaSaludEnemigo(final int nuevaSaludEnemigo) {
		this.nuevaSaludEnemigo = nuevaSaludEnemigo;
	}

	/**
	 * Devuelve la nueva energía del enemigo. <br>
	 * 
	 * @return Nueva energía enemigo. <br>
	 */
	public int getNuevaEnergiaEnemigo() {
		return nuevaEnergiaEnemigo;
	}

	/**
	 * Establece la nueva energía del enemigo. <br>
	 * 
	 * @param nuevaEnergiaEnemigo
	 *            Nueva energía enemigo. <br>
	 */
	public void setNuevaEnergiaEnemigo(final int nuevaEnergiaEnemigo) {
		this.nuevaEnergiaEnemigo = nuevaEnergiaEnemigo;
	}

	/**
	 * Devuelve los atributos del personaje. <br>
	 * 
	 * @return Atributos personaje. <br>
	 */
	public HashMap<String, Number> getMapPersonaje() {
		return mapPersonaje;
	}

	/**
	 * Devuelve los atributos del enemigo. <br>
	 * 
	 * @return Atributos enemigo. <br>
	 */
	public HashMap<String, Number> getMapEnemigo() {
		return mapEnemigo;
	}
}