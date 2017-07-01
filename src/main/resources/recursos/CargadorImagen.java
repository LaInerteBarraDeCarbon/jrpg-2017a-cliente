package recursos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 * Clase que administra la carga de imágenes. <br>
 */
public class CargadorImagen {

	/**
	 * Carga una imagen desde el repositorio de imagenes. <br>
	 * 
	 * @param path
	 *            Nombre del archivo. <br>
	 * @return Imágen;
	 */
	public static BufferedImage cargarImagen(String path) {
		try {
			return ImageIO.read(CargadorImagen.class.getResource(path));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar el archivo " + path);
		}
		return null;
	}
}
