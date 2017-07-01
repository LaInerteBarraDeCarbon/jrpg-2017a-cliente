package mundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Clase con funciones utilitarias. <br>
 */
public class Utilitarias {
	/**
	 * Convierte un archivo a un String. <br>
	 * 
	 * @param path
	 *            Dirección del archivo. <br>
	 * @return String del path. <br>
	 */
	public static String archivoAString(final String path) {
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String linea;
			while ((linea = br.readLine()) != null) {
				builder.append(linea + System.lineSeparator());
			}
			br.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo al intentar cargar el mapa " + path);
		}
		return builder.toString();
	}

	/**
	 * Convierte un string de número en un int. <br>
	 * 
	 * @param numero
	 *            Número (String). <br>
	 * @return Número (int). <br>
	 */
	public static int parseInt(final String numero) {
		try {
			return Integer.parseInt(numero);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Fallo conversión de enteros.");
			return 0;
		}
	}
}
