package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import inventario.Item;

@SuppressWarnings("serial")
public class PaqueteComercio extends Paquete implements Serializable, Cloneable {
	/**
	 * ID del personaje. <br>
	 */
	private int idPersonaje;
	/**
	 * ID del enemigo. <br>
	 */
	private int idEnemigo;
	private int listo = 0;
	/**
	 * Lista de items a dar. <br>
	 */
	private List<Item> itemsADar = new ArrayList<Item>();
	/**
	 * Lista de items a obtener. <br>
	 */
	private List<Item> itemsAObtener = new ArrayList<Item>();
	/**
	 * Indicador de solicitud de comercio. <br>
	 */
	private boolean solicitudDeComercio;

	/**
	 * Crea un comercio entre personajes. <br>
	 */
	public PaqueteComercio() {
		setComando(Comando.COMERCIO);
		solicitudDeComercio = true;
	}

	/**
	 * Indica si hay solicitud de comercio entre los personajes. <br>
	 * 
	 * @return true si hay solicitud de comercio, false de lo contrario. <br>
	 */
	public boolean isSolicitudDeComercio() {
		return solicitudDeComercio;
	}

	/**
	 * Establece la solicitud de comercio entre los personajes. <br>
	 * 
	 * @param solicitudDeComercio
	 *            true si se desea tener una solicitud de comercio, false de lo
	 *            contrario. <br>
	 */
	public void setSolicitudDeComercio(boolean solicitudDeComercio) {
		this.solicitudDeComercio = solicitudDeComercio;
	}

	/**
	 * Devuelve la lista de items a dar. <br>
	 * 
	 * @return Lista de items a dar. <br>
	 */
	public List<Item> getItemsADar() {
		return itemsADar;
	}

	/**
	 * Establece la lista de items a dar. <br>
	 * 
	 * @param itemsADar
	 *            Lista de items a dar. <br>
	 */
	public void setItemsADar(List<Item> itemsADar) {
		this.itemsADar = itemsADar;
	}

	/**
	 * Devuelve la lista de items a obtener. <br>
	 * 
	 * @return Lista de items a obtener. <br>
	 */
	public List<Item> getItemsAObtener() {
		return itemsAObtener;
	}

	/**
	 * Establece la lista de items a obtener. <br>
	 * 
	 * @param itemsAObtener
	 *            Lista de items a obtener. <br>
	 */
	public void setItemsAObtener(List<Item> itemsAObtener) {
		this.itemsAObtener = itemsAObtener;
	}

	/**
	 * Devuelve el ID del personaje. <br>
	 * 
	 * @return ID del personaje. <br>
	 */
	public int getIdPersonaje() {
		return idPersonaje;
	}

	/**
	 * Establece el ID del personaje. <br>
	 * 
	 * @param idPersonaje
	 *            ID del personaje. <br>
	 */
	public void setId(int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	/**
	 * Devuelve el ID del enemigo. <br>
	 * 
	 * @return ID del enemigo. <br>
	 */
	public int getIdEnemigo() {
		return idEnemigo;
	}

	/**
	 * Establece el ID del personaje. <br>
	 * 
	 * @param idEnemigo
	 *            ID del enemigo. <br>
	 */
	public void setIdEnemigo(int idEnemigo) {
		this.idEnemigo = idEnemigo;
	}

	public int getListo() {
		return listo;
	}

	public void aumentarListo() {
		this.listo++;
	}

	public void disminuirListo() {
		this.listo--;
	}
}
