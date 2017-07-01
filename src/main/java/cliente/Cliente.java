package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import frames.MenuCarga;
import frames.MenuCreacionPj;
import frames.MenuJugar;
import frames.MenuMapas;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaquetePersonaje;
import mensajeria.PaqueteUsuario;
import utilitarias.Constantes;

/**
 * Clase que administra al jugador con su personaje. <br>
 */
public class Cliente extends Thread {
	/**
	 * Jugador. <br>
	 */
	private Socket cliente;
	/**
	 * IP del jugador. <br>
	 */
	private String miIp;
	/**
	 * Entrada. <br>
	 */
	private ObjectInputStream entrada;
	/**
	 * Salida. <br>
	 */
	private ObjectOutputStream salida;
	/**
	 * Gson. <br>
	 */
	private final Gson gson = new Gson();
	/**
	 * Usuario. <br>
	 */
	private PaqueteUsuario paqueteUsuario;
	/**
	 * Personaje. <br>
	 */
	private PaquetePersonaje paquetePersonaje;
	/**
	 * Acción que realiza el jugador.<br>
	 */
	private int accion;
	/**
	 * Dirección IP. <br>
	 */
	private String ip;
	/**
	 * Puerto a utilizar. <br>
	 */
	private static final int PUERTO = 9999;

	/**
	 * Devuelve la acción. <br>
	 * 
	 * @return Ación. <br>
	 */
	public int getAccion() {
		return accion;
	}

	/**
	 * Establece la acción. <br>
	 * 
	 * @param accion
	 *            Acción.<br>
	 */
	public void setAccion(final int accion) {
		this.accion = accion;
	}

	/**
	 * Juego del cliente. <br>
	 */
	private Juego wome;
	/**
	 * Menú del cliente. <br>
	 */
	private MenuCarga menuCarga;

	/**
	 * Genera un cliente. <br>
	 */
	public Cliente() {
		this.cargarIP();
		if (ip == null) {
			ip = "localhost";
		}
		try {
			cliente = new Socket(ip, PUERTO);
			miIp = cliente.getInetAddress().getHostAddress();
			entrada = new ObjectInputStream(cliente.getInputStream());
			salida = new ObjectOutputStream(cliente.getOutputStream());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo al iniciar la aplicación. Revise la conexión con el servidor.");
			System.exit(1);
		}
	}

	/**
	 * Carga la IP del usuario. <br>
	 */
	public void cargarIP() {
		int reply = JOptionPane.showConfirmDialog(null, "¿Ingresar por localhost?", "WoME - Servidor",
				JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			return;
		} else {
			this.ip = JOptionPane.showInputDialog("Ingrese IP del servidor (localhost por defecro): ");
		}
	}

	/**
	 * Función madre de Cliente. <br>
	 */
	public void run() {
		synchronized (this) {
			try {
				// Creo el paquete que le voy a enviar al servidor
				paqueteUsuario = new PaqueteUsuario();
				while (!paqueteUsuario.isInicioSesion()) {
					// Muestro el menú principal
					new MenuJugar(this).setVisible(true);
					// Creo los paquetes que le voy a enviar al servidor
					paqueteUsuario = new PaqueteUsuario();
					paquetePersonaje = new PaquetePersonaje();
					// Espero a que el usuario seleccione alguna accion
					wait();
					switch (getAccion()) {
					case Comando.REGISTRO:
						paqueteUsuario.setComando(Comando.REGISTRO);
						break;
					case Comando.INICIOSESION:
						paqueteUsuario.setComando(Comando.INICIOSESION);
						break;
					case Comando.SALIR:
						paqueteUsuario.setIp(getMiIp());
						paqueteUsuario.setComando(Comando.SALIR);
						break;
					}
					// Le envio el paquete al servidor
					salida.writeObject(gson.toJson(paqueteUsuario));
					// Recibo el paquete desde el servidor
					String cadenaLeida = (String) entrada.readObject();
					Paquete paquete = gson.fromJson(cadenaLeida, Paquete.class);
					switch (paquete.getComando()) {
					case Comando.REGISTRO:
						if (paquete.getMensaje().equals(Paquete.MSJEXITO)) {
							// Abro el menu para la creaci�n del personaje
							MenuCreacionPj menuCreacionPJ = new MenuCreacionPj(this, paquetePersonaje);
							menuCreacionPJ.setVisible(true);
							// Espero a que el usuario cree el personaje
							wait();
							// Le envio los datos al servidor
							paquetePersonaje.setComando(Comando.CREACIONPJ);
							salida.writeObject(gson.toJson(paquetePersonaje));
							JOptionPane.showMessageDialog(null, "Registro exitoso.");
							// Recibo el paquete personaje con los datos (la id
							// incluida)
							paquetePersonaje = (PaquetePersonaje) gson.fromJson((String) entrada.readObject(),
									PaquetePersonaje.class);
							// Indico que el usuario ya inicio sesion
							paqueteUsuario.setInicioSesion(true);
						} else {
							if (paquete.getMensaje().equals(Paquete.MSJFRACASO)) {
								JOptionPane.showMessageDialog(null, "No se pudo registrar.");
							}
							// El usuario no pudo iniciar sesión
							paqueteUsuario.setInicioSesion(false);
						}
						break;
					case Comando.INICIOSESION:
						if (paquete.getMensaje().equals(Paquete.MSJEXITO)) {
							// El usuario ya inicio sesión
							paqueteUsuario.setInicioSesion(true);
							// Recibo el paquete personaje con los datos
							paquetePersonaje = (PaquetePersonaje) gson.fromJson(cadenaLeida, PaquetePersonaje.class);
						} else {
							if (paquete.getMensaje().equals(Paquete.MSJFRACASO)) {
								JOptionPane.showMessageDialog(null,
										"Error al iniciar sesión. Revise el usuario y la contraseña");
							}
							// El usuario no pudo iniciar sesión
							paqueteUsuario.setInicioSesion(false);
						}
						break;
					case Comando.SALIR:
						// El usuario no pudo iniciar sesión
						paqueteUsuario.setInicioSesion(false);
						salida.writeObject(gson.toJson(new Paquete(Comando.DESCONECTAR), Paquete.class));
						cliente.close();
						break;
					default:
						break;
					}
				}
				// Creo un paquete con el comando mostrar mapas
				paquetePersonaje.setComando(Comando.MOSTRARMAPAS);
				// Abro el menu de eleccion del mapa
				MenuMapas menuElegirMapa = new MenuMapas(this);
				menuElegirMapa.setVisible(true);
				// Espero a que el usuario elija el mapa
				wait();
				// Establezco el mapa en el paquete personaje
				paquetePersonaje.setIp(miIp);
				// Le envio el paquete con el mapa seleccionado
				salida.writeObject(gson.toJson(paquetePersonaje));
				// Instancio el juego y cargo los recursos
				wome = new Juego("World Of the Middle Earth", Constantes.ANCHOPANTALLA, Constantes.ALTOPANTALLA, this,
						paquetePersonaje);
				// Muestro el menu de carga
				menuCarga = new MenuCarga(this);
				menuCarga.setVisible(true);
				// Espero que se carguen todos los recursos
				wait();
				// Inicio el juego
				wome.start();
				// Finalizo el menu de carga
				menuCarga.dispose();
			} catch (IOException | InterruptedException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor durante el inicio de sesión.");
				System.exit(1);
			}
		}
	}

	/**
	 * Devuelve el socket del cliente. <br>
	 * 
	 * @return Socket. <br>
	 */
	public Socket getSocket() {
		return cliente;
	}

	/**
	 * Establece el socket del cliente. <br>
	 * 
	 * @param cliente
	 *            Socket del cliente. <br>
	 */
	public void setSocket(final Socket cliente) {
		this.cliente = cliente;
	}

	/**
	 * Devuelve la IP del jugador. <br>
	 * 
	 * @return IP del jugador. <br>
	 */
	public String getMiIp() {
		return miIp;
	}

	/**
	 * Establece la IP del jugador. <br>
	 * 
	 * @param miIp
	 *            IP del jugador. <br>
	 */
	public void setMiIp(final String miIp) {
		this.miIp = miIp;
	}

	/**
	 * Devuelve la entrada. <br>
	 * 
	 * @return Entrada. <br>
	 */
	public ObjectInputStream getEntrada() {
		return entrada;
	}

	/**
	 * Establece la entrada
	 * 
	 * @param entrada
	 *            Entrada. <br>
	 */
	public void setEntrada(final ObjectInputStream entrada) {
		this.entrada = entrada;
	}

	/**
	 * Devuelve la salida. <br>
	 * 
	 * @return Salida. <br>
	 */
	public ObjectOutputStream getSalida() {
		return salida;
	}

	/**
	 * Establece la salida. <br>
	 * 
	 * @param salida
	 *            Salida. <br>
	 */
	public void setSalida(final ObjectOutputStream salida) {
		this.salida = salida;
	}

	/**
	 * Devuelve la condición actual del usuario. <br>
	 * 
	 * @return Usuario. <br>
	 */
	public PaqueteUsuario getPaqueteUsuario() {
		return paqueteUsuario;
	}

	/**
	 * Devuelve la condición actual del personaje del jugador. <br>
	 * 
	 * @return Personaje. <br>
	 */
	public PaquetePersonaje getPaquetePersonaje() {
		return paquetePersonaje;
	}

	/**
	 * Devuelve el juego del usuario. <br>
	 * 
	 * @return Juego. <br>
	 */
	public Juego getJuego() {
		return wome;
	}

	/**
	 * Devuelve el menú de carga del usuario. <br>
	 * 
	 * @return Menú de Carga. <br>
	 */
	public MenuCarga getMenuCarga() {
		return menuCarga;
	}

	/**
	 * Actualiza los items del jugador. <br>
	 * 
	 * @param paqueteActualizado
	 *            Valores actualizados del jugador. <br>
	 */
	public void actualizarItems(final PaquetePersonaje paqueteActualizado) {
		if (paquetePersonaje.getCantidadObjetosInventario() != paqueteActualizado.getCantidadObjetosInventario()) {
			paquetePersonaje.añadirItem(paqueteActualizado.getItems().get(paqueteActualizado.getItems().size() - 1));
		}
	}
}
