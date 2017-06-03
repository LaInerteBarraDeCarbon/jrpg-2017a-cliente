package mensajeria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import dominio.Inventario;
import estados.Estado;

public class PaquetePersonaje extends Paquete implements Serializable, Cloneable {

	private int id;
	private int idMapa;
	private int estado;
	private String casta;
	private String nombre;
	private String raza;
	private int saludTope;
	private int energiaTope;
	private int fuerza;
	private int destreza;
	private int inteligencia;
	private int nivel;
	private int experiencia;
	private int salud;
	private List<Inventario> inventario = new ArrayList<Inventario>();

	public PaquetePersonaje() {
		estado = Estado.estadoOffline;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getMapa() {
		return idMapa;
	}

	public void setMapa(int mapa) {
		idMapa = mapa;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCasta() {
		return casta;
	}

	public void setCasta(String casta) {
		this.casta = casta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public int getSaludTope() {
		return saludTope;
	}

	public void setSaludTope(int saludTope) {
		this.saludTope = saludTope;
	}

	public int getEnergiaTope() {
		return energiaTope;
	}

	public void setEnergiaTope(int energiaTope) {
		this.energiaTope = energiaTope;
	}

	public int getFuerza() {
		return fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getDestreza() {
		return destreza;
	}

	public void setDestreza(int destreza) {
		this.destreza = destreza;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}

	public int getSalud() {
		return this.salud;
	}

	public void setSalud(int salud) {
		this.salud = salud;
	}

	/**
	 * Añade un item al inventario. <br>
	 * 
	 * @param item
	 *            Item obtenido. <br>
	 */
	public void añadirItem(Inventario item) {
		this.inventario.add(item);
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
					actual.getBonusDestreza(), actual.getBonusInteligencia(), actual.getModificadorSalud(),
					actual.getModificadorEnergia(), actual.getModificadorFuerza(), actual.getModificadorDestreza(),
					actual.getModificadorInteligencia());
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
	 * @param porcSalud
	 *            Bonus Modificador gradual de salud. <br>
	 * @param porcEnergia
	 *            Modificador gradual de energía. <br>
	 * @param porcFuerza
	 *            Modificador gradual de fuerza. <br>
	 * @param porcDestreza
	 *            Modificador gradual de destreza. <br>
	 * @param porcInteligencia
	 *            Modificador gradual de inteligencia. <br>
	 */
	public void asignarBonus(final int bonusSalud, final int bonusEnergia, final int bonusFuerza,
			final int bonusDestreza, final int bonusInteligencia, final double porcSalud, final double porcEnergia,
			final double porcFuerza, final double porcDestreza, final double porcInteligencia) {
		this.saludTope += bonusSalud;
		this.energiaTope += bonusEnergia;
		this.fuerza += bonusFuerza;
		this.destreza += bonusDestreza;
		this.inteligencia += bonusInteligencia;
		this.saludTope += this.saludTope * porcSalud;
		this.energiaTope += this.energiaTope * porcEnergia;
		this.fuerza += this.fuerza * porcFuerza;
		this.destreza += this.destreza * porcDestreza;
		this.inteligencia += this.inteligencia * porcInteligencia;
	}

	/**
	 * Devuelve la cantidad de objetos en el inventario. <br>
	 * 
	 * @return Cantidad de objetos. <br>
	 */
	public int getCantidadObjetosInventario() {
		return inventario.size();
	}

	public void obtenerItem(int id) {

	}
}
