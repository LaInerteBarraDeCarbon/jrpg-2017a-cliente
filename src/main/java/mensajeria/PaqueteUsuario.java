package mensajeria;

import java.io.Serializable;

/**
 * Crea el usuario. <br>
 */
@SuppressWarnings("serial")
public class PaqueteUsuario extends Paquete implements Serializable, Cloneable {
	/**
	 * ID del personaje. <br>
	 */
	private int idPj;
	/**
	 * Nombre de usuario. <br>
	 */
	private String username;
	/**
	 * Contraseña del usuario. <br>
	 */
	private String password;
	/**
	 * Indicador de si entra. <br>
	 */
	private boolean inicioSesion;

	/**
	 * Crea el paquete de usuario. <br>
	 */
	public PaqueteUsuario() {

	}

	/**
	 * Crea un usuario. <br>
	 * 
	 * @param pj
	 *            ID del personaje. <br>
	 * @param user
	 *            Nombre de usuario. <br>
	 * @param pw
	 *            Contraseña de usuario. <br>
	 */
	public PaqueteUsuario(final int pj, final String user, final String pw) {
		idPj = pj;
		username = user;
		password = pw;
		inicioSesion = false;
	}

	/**
	 * Devuelve el ID del personaje. <br>
	 * 
	 * @return ID personaje. <br>
	 */
	public int getIdPj() {
		return idPj;
	}

	/**
	 * Establece el ID del personaje. <br>
	 * 
	 * @param idPj
	 *            ID del personaje. <br>
	 */
	public void setIdPj(final int idPj) {
		this.idPj = idPj;
	}

	/**
	 * Devuelve el nombre del usuario. <br>
	 * 
	 * @return Nombre de usuario. <br>
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Establece el nombre de usuario. <br>
	 * 
	 * @param username
	 *            Nombre de usuario. <br>
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * Establece la contraseña del usuario. <br>
	 * 
	 * @param password
	 *            Contraseña. <br>
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Devuelve si el usuario puede iniciar sesión. <br>
	 * 
	 * @return true si puede, false de lo contrario. <br>
	 */
	public boolean isInicioSesion() {
		return inicioSesion;
	}

	/**
	 * Establece el indicador de inicio de sesión. <br>
	 * 
	 * @param inicioSesion
	 *            Indicador. <br>
	 */
	public void setInicioSesion(final boolean inicioSesion) {
		this.inicioSesion = inicioSesion;
	}

	/**
	 * Clona a un usuario. <br>
	 */
	public Object clone() {
		Object obj = null;
		obj = super.clone();
		return obj;
	}
}
