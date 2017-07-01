package mensajeria;

import java.io.Serializable;

import javax.swing.JOptionPane;

/**
 * Administra los distintos paquetes. <br>
 */
@SuppressWarnings("serial")
public class Paquete implements Serializable, Cloneable {
	/**
	 * Mensaje para indicar que salió todo bien. <br>
	 */
	public static String MSJEXITO = "1";
	/**
	 * Mensaje para indicar que algo salió mal. <br>
	 */
	public static String MSJFRACASO = "0";
	/**
	 * Mensaje a devolver. <br>
	 */
	private String mensaje;
	/**
	 * IP del cliente. <br>
	 */
	private String ip;
	/**
	 * Comando.<br>
	 */
	private int comando;

	/**
	 * Crea un paquete. <br>
	 */
	public Paquete() {

	}

	/**
	 * Crea un paquete. <br>
	 * 
	 * @param mensaje
	 *            Mensaje. <br>
	 * @param nick
	 *            Nick. <br>
	 * @param ip
	 *            IP. <br>
	 * @param comando
	 *            Comando. <br>
	 */
	public Paquete(final String mensaje, final String nick, final String ip, final int comando) {
		this.mensaje = mensaje;
		this.ip = ip;
		this.comando = comando;
	}

	/**
	 * Crea un paquete. <br>
	 * 
	 * @param mensaje
	 *            Mensaje. <br>
	 * @param comando
	 *            Comando. <br>
	 */
	public Paquete(final String mensaje, final int comando) {
		this.mensaje = mensaje;
		this.comando = comando;
	}

	/**
	 * Crea un paquete. <br>
	 * 
	 * @param comando
	 *            Comando. <br>
	 */
	public Paquete(final int comando) {
		this.comando = comando;
	}

	/**
	 * Establece el mensaje. <br>
	 * 
	 * @param mensaje
	 *            Mensaje. <br>
	 */
	public void setMensaje(final String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Establece la IP. <br>
	 * 
	 * @param ip
	 *            IP. <br>
	 */
	public void setIp(final String ip) {
		this.ip = ip;
	}

	/**
	 * Establece el comando a realizar. <br>
	 * 
	 * @param comando
	 *            Comando. <br>
	 */
	public void setComando(final int comando) {
		this.comando = comando;
	}

	/**
	 * Devuelve el mensaje. <br>
	 * 
	 * @return Mensaje. <br>
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Devuelve la IP. <br>
	 * 
	 * @return IP. <br>
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Devuelve el comando. <br>
	 * 
	 * @return Comando. <br>
	 */
	public int getComando() {
		return comando;
	}

	/**
	 * Clona un paquete. <br>
	 */
	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			JOptionPane.showMessageDialog(null, "Fallo creacion de paquete.");
		}
		return obj;
	}
}
