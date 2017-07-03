package cliente;

import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import estados.Estado;
import estados.EstadoBatalla;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.Paquete;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteDeMovimientos;
import mensajeria.PaqueteDePersonajes;
import mensajeria.PaqueteFinalizarBatalla;
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
	 * Constructor de EsuchaMensaje
	 * 
	 * @param juego
	 *            juego del que se escucha el mensaje
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
			PaquetePersonaje paquetePersonaje;
			@SuppressWarnings("unused")
			PaqueteMovimiento personaje;
			PaqueteBatalla paqueteBatalla;
			PaqueteAtacar paqueteAtacar;
			@SuppressWarnings("unused")
			PaqueteFinalizarBatalla paqueteFinalizarBatalla;
			personajesConectados = new HashMap<>();
			ubicacionPersonajes = new HashMap<>();
			while (true) {
				String objetoLeido = (String) entrada.readObject();
				paquete = gson.fromJson(objetoLeido, Paquete.class);
				switch (paquete.getComando()) {
				case Comando.CONEXION:
					personajesConectados = gson.fromJson(objetoLeido, PaqueteDePersonajes.class).getPersonajes();
					break;
				case Comando.MOVIMIENTO:
					ubicacionPersonajes = gson.fromJson(objetoLeido, PaqueteDeMovimientos.class).getPersonajes();
					break;
				case Comando.BATALLA:
					paqueteBatalla = gson.fromJson(objetoLeido, PaqueteBatalla.class);
					juego.getPersonaje().setEstado(Estado.ESTADOBATALLA);
					Estado.setEstado(null);
					juego.setEstadoBatalla(new EstadoBatalla(juego, paqueteBatalla));
					Estado.setEstado(juego.getEstadoBatalla());
					break;
				case Comando.ATACAR:
					paqueteAtacar = gson.fromJson(objetoLeido, PaqueteAtacar.class);
					juego.getEstadoBatalla().getEnemigo().actualizarAtributos(paqueteAtacar.getMapPersonaje());
					juego.getEstadoBatalla().getPersonaje().actualizarAtributos(paqueteAtacar.getMapEnemigo());
					juego.getEstadoBatalla().setMiTurno(true);
					break;
				case Comando.FINALIZARBATALLA:
					paqueteFinalizarBatalla = gson.fromJson(objetoLeido, PaqueteFinalizarBatalla.class);
					juego.getPersonaje().setEstado(Estado.ESTADOJUEGO);
					Estado.setEstado(juego.getEstadoJuego());
					break;
				case Comando.ACTUALIZARPERSONAJE:
					paquetePersonaje = gson.fromJson(objetoLeido, PaquetePersonaje.class);
					personajesConectados.remove(paquetePersonaje.getId());
					personajesConectados.put(paquetePersonaje.getId(), paquetePersonaje);
					if (juego.getPersonaje().getId() == paquetePersonaje.getId()) {
						juego.actualizarPersonaje();
						juego.getEstadoJuego().actualizarPersonaje();
						cliente.actualizarItems(paquetePersonaje);
					}
				}
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