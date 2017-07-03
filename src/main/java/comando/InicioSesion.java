package comando;

import javax.swing.JOptionPane;

import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;

/**
 * Clase que administra el inicio de sesión del usuario. <br>
 */
public class InicioSesion extends ClienteComandos {

	/**
	 * Ejecuta el inicio de sesión del usuario. <br>
	 */
	@Override
	public void ejecutar() {
		PaqueteUsuario paqueteUsuario = super.cliente.getPaqueteUsuario();
		if (super.paquete.getMensaje().equals(Paquete.MSJEXITO)) {
			// El usuario ya inicio sesión
			paqueteUsuario.setInicioSesion(true);
			// Recibo el paquete personaje con los datos
			super.cliente.setPaquetePersonaje((PaquetePersonaje) super.paquete);
		} else {
			if (super.paquete.getMensaje().equals(Paquete.MSJFRACASO)) {
				JOptionPane.showMessageDialog(null, "Error al iniciar sesión. Revise el usuario y la contraseña");
			}
			// El usuario no pudo iniciar sesión
			paqueteUsuario.setInicioSesion(false);
		}
	}
}
