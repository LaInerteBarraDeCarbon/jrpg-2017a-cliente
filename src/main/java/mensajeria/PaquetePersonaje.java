package mensajeria;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import dominio.Inventario;
import estados.Estado;

/**
 * Paquete que administra al personaje. <br>
 */
@SuppressWarnings("serial")
public class PaquetePersonaje extends Paquete implements Serializable, Cloneable {

	/**
	 * ID del jugador. <br>
	 */
	private int id;
	/**
	 * ID del mapa. <br>
	 */
	private int idMapa;
	/**
	 * Estado del personaje. <br>
	 */
	private int estado;
	/**
	 * Casta del personaje. <br>
	 */
	private String casta;
	/**
	 * Nombre del personaje. <br>
	 */
	private String nombre;
	/**
	 * Raza del personaje. <br>
	 */
	private String raza;
	/**
	 * Máxima salud del personaje. >br>
	 */
	private int saludTope;
	/**
	 * Máxima energía del personaje. <br>
	 */
	private int energiaTope;
	/**
	 * Fuerza del personaje. <br>
	 */
	private int fuerza;
	/**
	 * Destreza del personaje. <br>
	 */
	private int destreza;
	/**
	 * Inteligencia del personaje. <br>
	 */
	private int inteligencia;
	/**
	 * Nivel del personaje. <br>
	 */
	private int nivel;
	/**
	 * Experiencia total del personaje. <br>
	 */
	private int experiencia;
	/**
	 * Salud del personaje. <br>
	 */
	private int salud;
	/**
	 * Inventario del personaje. <br>
	 */
	private List<Inventario> inventario = new ArrayList<Inventario>();

	/**
	 * 
	 */
	public PaquetePersonaje() {
		estado = Estado.estadoOffline;
	}

	/**
	 * Devuelve el estado del personaje. <br>
	 * 
	 * @return Estado. <br>
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * Establece el estado del personaje. <br>
	 * 
	 * @param estado
	 *            Estado actual. <br>
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * Devuelve el mapa del jugador. <br>
	 * 
	 * @return Mapa. <br>
	 */
	public int getMapa() {
		return idMapa;
	}

	/**
	 * Establece el mapa del jugador. <br>
	 * 
	 * @param mapa
	 *            Mapa del jugador. <br>
	 */
	public void setMapa(int mapa) {
		idMapa = mapa;
	}

	/**
	 * Devuelve el nivel del personaje. <br>
	 * 
	 * @return Nivel. <br>
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Establece el nivel del personaje. <br>
	 * 
	 * @param nivel
	 *            Nivel del personaje. <br>
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	/**
	 * Devuelve la experiencia del personaje. <br>
	 * 
	 * @return Experiencia. <br>
	 */
	public int getExperiencia() {
		return experiencia;
	}

	/**
	 * Establece la experiencia total del jugador. <br>
	 * 
	 * @param experiencia
	 *            Experiencia del jugador. <br>
	 */
	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	/**
	 * Devuelve el ID del personaje. <br>
	 * 
	 * @return ID. <br>
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
	 * Devuelve la casta del personaje. <br>
	 * 
	 * @return Casta. <br>
	 */
	public String getCasta() {
		return casta;
	}

	/**
	 * Establece la casta del personaje. <br>
	 * 
	 * @param casta
	 *            Casta del personaje. <br>
	 */
	public void setCasta(String casta) {
		this.casta = casta;
	}

	/**
	 * Devuelve el nombre del personaje. <br>
	 * 
	 * @return Nombre. <br>
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del personaje. <br>
	 * 
	 * @param nombre
	 *            Nombre del personaje. <br>
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la raza del personaje. <br>
	 * 
	 * @return Raza. <br>
	 */
	public String getRaza() {
		return raza;
	}

	/**
	 * Establece la raza del personaje. <br>
	 * 
	 * @param raza
	 *            Raza del personaje. <br>
	 */
	public void setRaza(String raza) {
		this.raza = raza;
	}

	/**
	 * Establece la salud máxima del personaje. <br>
	 * 
	 * @return Salud máxima. <br>
	 */
	public int getSaludTope() {
		return saludTope;
	}

	/**
	 * Establece la salud máxima del personaje. <br>
	 * 
	 * @param saludTope
	 *            Salud máxima del personaje. <br>
	 */
	public void setSaludTope(int saludTope) {
		this.saludTope = saludTope;
	}

	/**
	 * Devuelve la energía máxima del personaje. <br>
	 * 
	 * @return Energía máxima. <br>
	 */
	public int getEnergiaTope() {
		return energiaTope;
	}

	/**
	 * Establece la energía máxima del personaje. <br>
	 * 
	 * @param energiaTope
	 *            Energía máxima del personaje. <br>
	 */
	public void setEnergiaTope(int energiaTope) {
		this.energiaTope = energiaTope;
	}

	/**
	 * Devuelve la fuerza del personaje. <br>
	 * 
	 * @return Fuerza. <br>
	 */
	public int getFuerza() {
		return fuerza;
	}

	/**
	 * Establece la fuerza del personaje. <br>
	 * 
	 * @param fuerza
	 *            Fuerza del personaje. <br>
	 */
	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	/**
	 * Devuelve la destreza del personaje. <br>
	 * 
	 * @return Destreza. <br>
	 */
	public int getDestreza() {
		return destreza;
	}

	/**
	 * Establece la destreza del personaje. <br>
	 * 
	 * @param destreza
	 *            Destreza del personaje. <br>
	 */
	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}

	/**
	 * Devuelve la inteligencia del personaje. <br>
	 * 
	 * @return Inteligencia. <br>
	 */
	public int getInteligencia() {
		return inteligencia;
	}

	/**
	 * Establece la inteligencia del personaje. <br>
	 * 
	 * @param inteligencia
	 *            Inteligencia del personaje. <br>
	 */
	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	/**
	 * Clona un personaje con todos sus stats actuales.<br>
	 */
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

	/**
	 * Devuelve la salud del personaje. <br>
	 * 
	 * @return Salud. <br>
	 */
	public int getSalud() {
		return this.salud;
	}

	/**
	 * Establece la salud del personaje. <br>
	 * 
	 * @param salud
	 *            Salud del personaje. <br>
	 */
	public void setSalud(int salud) {
		this.salud = salud;
	}

	/**
	 * Añade un item al inventario. <br>
	 * 
	 * @param item
	 *            Item obtenido. <br>
	 */
	public void añadirItem(final Inventario item) {
		this.inventario.add(item);
	}

	/**
	 * Añade un item por ID al inventario. <br>
	 * 
	 * @param idItem
	 *            ID del item. <br>
	 */
	public void añadirItem(int idItem) {
		try {
			this.inventario.add(new Inventario(idItem, null, 0, 0, 0, 0, 0, null));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Añade un item con sus modificadores al inventario. <br>
	 * 
	 * @param idItem
	 *            ID del item. <br>
	 * @param nombre
	 *            Nombre del item. <br>
	 * @param bonusSalud
	 *            Modificador discreto de salud. <br>
	 * @param bonusEnergia
	 *            Modificador discreto de energía. <br>
	 * @param bonusFuerza
	 *            Modificador discreto de fuerza. <br>
	 * @param bonusDestreza
	 *            Modificador discreto de destreza. <br>
	 * @param bonusInteligencia
	 *            Modificador discreto de inteligencia. <br>
	 * @param foto
	 *            Foto del item. <br>
	 */
	public final void añadirItem(int idItem, String nombre, int bonusSalud, int bonusEnergia, int bonusAtaque,
			int bonusDefensa, int bonusMagia, String foto) {
		try {
			this.inventario.add(new Inventario(idItem, nombre, bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa,
					bonusMagia, foto));
			asignarBonus(bonusSalud, bonusEnergia, bonusAtaque, bonusDefensa, bonusMagia);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Devuelve los items que se encuentran en el inventario. <br>
	 * 
	 * @return Lista de items. <br>
	 */
	public List<Inventario> getItems() {
		return new ArrayList<Inventario>(inventario);
	}

	/**
	 * Obtiene los modificadores de atributos de los items en el inventario.
	 * <br>
	 */
	public void obtenerBonus() {
		for (ListIterator<Inventario> iterator = inventario.listIterator(); iterator.hasNext();) {
			Inventario actual = iterator.next();
			asignarBonus(actual.getBonusSalud(), actual.getBonusEnergia(), actual.getBonusFuerza(),
					actual.getBonusDestreza(), actual.getBonusInteligencia());
		}
	}

	/**
	 * Asigna los bonuses del item correspondiente al personaje. <br>
	 * 
	 * @param bonusSalud
	 *            Modificador discreto de salud. <br>
	 * @param bonusEnergia
	 *            Modificador discreto de energía. <br>
	 * @param bonusFuerza
	 *            Modificador discreto de fuerza. <br>
	 * @param bonusDestreza
	 *            Modificador discreto de destreza. <br>
	 * @param bonusInteligencia
	 *            Modificador discreto de inteligencia. <br>
	 */
	public void asignarBonus(final int bonusSalud, final int bonusEnergia, final int bonusFuerza,
			final int bonusDestreza, final int bonusInteligencia) {
		this.saludTope += bonusSalud;
		this.energiaTope += bonusEnergia;
		this.fuerza += bonusFuerza;
		this.destreza += bonusDestreza;
		this.inteligencia += bonusInteligencia;
	}

	public final void removerBonus() {
		// Intente usar un iterator y por alguna razón no andaba..
		for (ListIterator<Inventario> iterator = inventario.listIterator(); iterator.hasNext();) {
			Inventario actual = iterator.next();
			quitarBonus(actual.getBonusSalud(), actual.getBonusEnergia(), actual.getBonusFuerza(),
					actual.getBonusDestreza(), actual.getBonusInteligencia());
		}
	}

	/**
	 * Quita los bonuses del item correspondiente al personaje. <br>
	 * 
	 * @param bonusSalud
	 *            Modificador discreto de salud. <br>
	 * @param bonusEnergia
	 *            Modificador discreto de energía. <br>
	 * @param bonusFuerza
	 *            Modificador discreto de fuerza. <br>
	 * @param bonusDestreza
	 *            Modificador discreto de destreza. <br>
	 * @param bonusInteligencia
	 *            Modificador discreto de inteligencia. <br>
	 */
	public final void quitarBonus(final int bonusSalud, final int bonusEnergia, final int bonusAtaque,
			final int bonusDefensa, final int bonusInteligencia) {
		this.saludTope -= bonusSalud;
		this.energiaTope -= bonusEnergia;
		this.fuerza -= bonusAtaque;
		this.destreza -= bonusDefensa;
		this.inteligencia -= bonusInteligencia;
	}

	/**
	 * Devuelve la cantidad de objetos en el inventario. <br>
	 * 
	 * @return Cantidad de objetos. <br>
	 */
	public int getCantidadObjetosInventario() {
		return inventario.size();
	}

	/**
	 * Devuelve el ID del item. <br>
	 * 
	 * @param id
	 *            ID del item. <br>
	 * @return ID. <br>
	 */
	public final int getIdItem(final int id) {
		return inventario.get(id).getIdItem();
	}

	/**
	 * 
	 * @return
	 */
	public boolean nuevoItem() {
		return inventario.get(inventario.size() - 1).getNombre() == null;
	}

	/**
	 * Quita un item del inventario. <br>
	 * 
	 * @param id
	 *            ID del item a sacar. <br>
	 */
	public final void removerItem(final Inventario id) {
		inventario.remove(id);
	}

	/**
	 * Quita el último ítem del inventario. <br>
	 */
	public void removerUltimoItem() {
		inventario.remove(inventario.size() - 1);
	}
}
