package entidades;

import java.awt.image.BufferedImage;

import utilitarias.Constantes;

/**
 * Clase que administra la animación. <br>
 */
public class Animacion {
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
	public Animacion(final int velocidad, final BufferedImage[] frames) {
		this.velocidad = velocidad;
		this.frames = frames;
		indice = Constantes.CERO;
		timer = Constantes.CERO;
		ultimoTiempo = System.currentTimeMillis();
	}

	/**
	 * Actualiza la animación. <br>
	 */
	public void actualizar() {
		timer += System.currentTimeMillis() - ultimoTiempo;
		ultimoTiempo = System.currentTimeMillis();
		if (timer > velocidad) {
			indice++;
			timer = Constantes.CERO;
			if (indice >= frames.length) {
				indice = Constantes.CERO;
			}
		}
	}

	/**
	 * Resetea el ínice. <br>
	 */
	public void reset() {
		indice = Constantes.CERO;
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
