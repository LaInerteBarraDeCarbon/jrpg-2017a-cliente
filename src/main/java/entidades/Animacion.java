package entidades;

import java.awt.image.BufferedImage;

/**
 * Clase que administra la animación. <br>
 */
public class Animacion {
	/**
	 * El inicio de todo. <br>
	 */
	private static final int CERO = 0;
	/**
	 * Velocidad de la animación. <br>
	 */
	private int velocidad;
	/**
	 * Índice. <br>
	 */
	private int indice;
	/**
	 * Último tiempo. <br>
	 */
	private long ultimoTiempo;
	/**
	 * Timer. <br>
	 */
	private long timer;
	/**
	 * Frames. <br>
	 */
	private BufferedImage[] frames;

	/**
	 * Crea la animación. <br>
	 * 
	 * @param velocidad
	 *            Velocidad de animación. <br>
	 * @param frames
	 *            Frames a realizar. <br>
	 */
	public Animacion(int velocidad, BufferedImage[] frames) {
		this.velocidad = velocidad;
		this.frames = frames;
		indice = CERO;
		timer = CERO;
		ultimoTiempo = System.currentTimeMillis();
	}

	public void actualizar() {
		timer += System.currentTimeMillis() - ultimoTiempo;
		ultimoTiempo = System.currentTimeMillis();
		if (timer > velocidad) {
			indice++;
			timer = CERO;
			if (indice >= frames.length) {
				indice = CERO;
			}
		}
	}

	/**
	 * Resetea el ínice. <br>
	 */
	public void reset() {
		indice = CERO;
	}

	/**
	 * Devuelve el frame actual. <br>
	 * 
	 * @return Frame actual. <br>
	 */
	public BufferedImage getFrameActual() {
		return frames[indice];
	}

	/**
	 * Devuelve el índice. <br>
	 * 
	 * @return Índice. <br>
	 */
	public int getFrame() {
		return indice;
	}
}
