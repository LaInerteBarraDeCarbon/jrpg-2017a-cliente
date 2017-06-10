package mundo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Clase que dibuja los tiles. <br>
 */
public class Tile {
	/**
	 * Tiles. <br>
	 */
	public static Tile[] tiles = new Tile[256];
	/**
	 * Tile especial. <br>
	 */
	public static Tile[] aubenor;
	/**
	 * Base especial. <br>
	 */
	public static int aubenorBase = 3;
	/**
	 * Ancho del tile. <br>
	 */
	public static final int ANCHO = 64;
	/**
	 * Alto del tile. <br>
	 */
	public static final int ALTO = 32;
	/**
	 * Imágen del tile. <br>
	 */
	protected BufferedImage textura;
	/**
	 * ID del tile. <br>
	 */
	protected final int id;
	/**
	 * Indicador de si es un tile sólido. <br>
	 */
	private boolean esSolido;
	/**
	 * Ancho. <br>
	 */
	protected int ancho;
	/**
	 * Alto. <br>
	 */
	protected int alto;

	/**
	 * Crea un tile con sus condiciones. <br>
	 * 
	 * @param textura
	 *            Imágen del tile. <br>
	 * @param id
	 *            ID del tile. <br>
	 * @param esSolido
	 *            Indicador tile sólido. <br>
	 */
	public Tile(BufferedImage textura, int id, boolean esSolido) {
		this.textura = textura;
		this.id = id;
		tiles[id] = this;
		this.esSolido = esSolido;
	}

	/**
	 * Crea un tile con sus condiciones, alto y ancho. <br>
	 * 
	 * @param textura
	 *            Imágen del tile. <br>
	 * @param id
	 *            ID del tile. <br>
	 * @param esSolido
	 *            Indicador tile sólido. <br>
	 * @param ancho
	 *            Ancho de tile. <br>
	 * @param alto
	 *            Alto de tile. <br>
	 */
	public Tile(BufferedImage textura, int id, boolean esSolido, int ancho, int alto) {
		this.textura = textura;
		this.id = id;
		tiles[id] = this;
		this.ancho = ancho;
		this.alto = alto;
		this.esSolido = esSolido;
	}

	/**
	 * Actualiza el tile. <br>
	 */
	public void actualizar() {

	}

	/**
	 * Gradica el tile. <br>
	 * 
	 * @param g
	 *            Graficador. <br>
	 * @param x
	 *            Posición X. <br>
	 * @param y
	 *            Posición Y. <br>
	 */
	public void graficar(Graphics g, int x, int y) {
		g.drawImage(textura, x, y, ANCHO, ALTO, null);
	}

	/**
	 * Grafica el tile. <br>
	 * 
	 * @param g
	 *            Graficador. <br>
	 * @param x
	 *            Posición X. <br>
	 * @param y
	 *            Posición Y. <br>
	 * @param width
	 *            Ancho del tile. <br>
	 * @param height
	 *            Alto del tile. <br>
	 */
	public void graficar(Graphics g, int x, int y, int width, int height) {
		g.drawImage(textura, x, y, width, height, null);
	}

	/**
	 * Establece la solidez de un tile. <br>
	 * 
	 * @param solidez
	 *            Solidez. <br>
	 */
	public void setSolido(boolean solidez) {
		esSolido = solidez;
	}

	/**
	 * Devuelve si el tile es sólido. <br>
	 * 
	 * @return true si es sólido, false de lo contrario. <br>
	 */
	public boolean esSolido() {
		return esSolido;
	}

	/**
	 * Devuelve el ID del tile. <br>
	 * 
	 * @return ID. <br>
	 */
	public int getId() {
		return id;
	}

	/**
	 * Devuelve el ancho del tile. <br>
	 * 
	 * @return Ancho. <br>
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Devuelve el alto del tile. <br>
	 * 
	 * @return Alto. <br>
	 */
	public int getAlto() {
		return alto;
	}
}