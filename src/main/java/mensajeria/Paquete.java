package mensajeria;

import java.io.Serializable;

/**
 * Administra los distintos paquetes. <br>
 */
@SuppressWarnings("serial")
public class Paquete implements Serializable, Cloneable {

	/**
	 * Mensaje para indicar que salió todo bien. <br>
	 */
	public static String msjExito = "1";
	/**
	 * Mensaje para indicar que algo salió mal. <br>
	 */
	public static String msjFracaso = "0";

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

	public Paquete() {

	}

	public Paquete(String mensaje, String nick, String ip, int comando) {
		this.mensaje = mensaje;
		this.ip = ip;
		this.comando = comando;
	}

	public Paquete(String mensaje, int comando) {
		this.mensaje = mensaje;
		this.comando = comando;
	}

	public Paquete(int comando) {
		this.comando = comando;
	}

	/**
	 * Establece el mensaje. <br>
	 * 
	 * @param mensaje
	 *            Mensaje. <br>
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Establece la IP. <br>
	 * 
	 * @param ip
	 *            IP. <br>
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Establece el comando a realizar. <br>
	 * 
	 * @param comando
	 *            Comando. <br>
	 */
	public void setComando(int comando) {
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

	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return obj;
	}

}
