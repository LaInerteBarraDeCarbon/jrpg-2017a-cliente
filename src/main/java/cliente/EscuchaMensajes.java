package cliente;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import comando.ClienteComandos;
import juego.Juego;
import mensajeria.Paquete;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;

/**
 * Clase que administra los distintos llamados del juego y sus jugadores. <br>
 */
public class EscuchaMensajes extends Thread {
	/**
	 * Juego. <br>
	 */
	private Juego juego;
	/**
	 * Comandos. <br>
	 */
	private Cliente cliente;
	/**
	 * Entrada. <br>
	 */
	private ObjectInputStream entrada;
	/**
	 * Gson. <br>
	 */
	private final Gson gson = new Gson();
	/**
	 * Lista con la ubicación de los personajes. <br>
	 */
	private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
	/**
	 * Lista con los pesonajes conectados. <br>
	 */
	private Map<Integer, PaquetePersonaje> personajesConectados;

	/**
	 * Constructor de EsuchaMensaje. <br>
	 * 
	 * @param juego
	 *            juego del que se escucha el mensaje. <br>
	 */
	public EscuchaMensajes(final Juego juego) {
		this.juego = juego;
		cliente = juego.getCliente();
		entrada = cliente.getEntrada();
	}

	/**
	 * Corre el escucha. <br>
	 */
	@Override
	public void run() {
		try {
			Paquete paquete;
			personajesConectados = new HashMap<>();
			ubicacionPersonajes = new HashMap<>();
			while (true) {
				String objetoLeido = (String) entrada.readObject();
				paquete = gson.fromJson(objetoLeido, Paquete.class);
				ClienteComandos comandos = (ClienteComandos) paquete.getComandoPaquete(ClienteComandos.PACKAGE);
				comandos.setJuego(this.juego);
				comandos.ejecutar();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor.");
		}
	}

	/**
	 * Pide la ubicacion de los personajes
	 * 
	 * @return devuelve el mapa con la ubicacion de los personajes
	 */
	public Map<Integer, PaqueteMovimiento> getUbicacionPersonajes() {
		return ubicacionPersonajes;
	}

	/**
	 * Pide los personajes conectados
	 * 
	 * @return devuelve el mapa con los personajes conectados
	 */
	public Map<Integer, PaquetePersonaje> getPersonajesConectados() {
		return personajesConectados;
	}
}