package estados;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import entidades.Entidad;
import interfaz.EstadoDePersonaje;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.Comando;
import mensajeria.PaqueteMovimiento;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

public class EstadoJuego extends Estado {

	/**
	 * Personaje visual. <br>
	 */
	private Entidad entidadPersonaje;
	/**
	 * Personaje. <br>
	 */
	private PaquetePersonaje paquetePersonaje;
	/**
	 * Mundo actual. <br>
	 */
	private Mundo mundo;
	/**
	 * Ubicaciones de los personajes en el mundo. <br>
	 */
	private Map<Integer, PaqueteMovimiento> ubicacionPersonajes;
	/**
	 * Personajes conectados. <br>
	 */
	private Map<Integer, PaquetePersonaje> personajesConectados;
	/**
	 * Indicador de si hay solicitud. <br>
	 */
	private boolean haySolicitud;
	/**
	 * Tipo de solicitud. <br>
	 */
	private int tipoSolicitud;
	/**
	 * Gson. <br>
	 */
	private final Gson gson = new Gson();
	/**
	 * Imagen del personaje. <br>
	 */
	private BufferedImage miniaturaPersonaje;
	/**
	 * Menú del enemigo. <br>
	 */
	MenuInfoPersonaje menuEnemigo;

	/**
	 * 
	 * @param juego
	 *            Juego. <br>
	 */
	public EstadoJuego(final Juego juego) {
		super(juego);
		mundo = new Mundo(juego, "recursos/" + getMundo() + ".txt", "recursos/" + getMundo() + ".txt");
		paquetePersonaje = juego.getPersonaje();
		entidadPersonaje = new Entidad(juego, mundo, 64, 64, juego.getPersonaje().getNombre(), 0, 0,
				Recursos.personaje.get(juego.getPersonaje().getRaza()), 150);
		miniaturaPersonaje = Recursos.personaje.get(paquetePersonaje.getRaza()).get(5)[0];
		try {
			juego.getPersonaje().setComando(Comando.CONEXION);
			juego.getPersonaje().setEstado(Estado.ESTADOJUEGO);
			juego.getCliente().getSalida().writeObject(gson.toJson(juego.getPersonaje(), PaquetePersonaje.class));
			juego.getCliente().getSalida()
					.writeObject(gson.toJson(juego.getUbicacionPersonaje(), PaqueteMovimiento.class));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor al ingresar al mundo");
		}
	}

	/**
	 * Actualiza el mundo y los personajes. <br>
	 */
	@Override
	public void actualizar() {
		mundo.actualizar();
		entidadPersonaje.actualizar();
	}

	/**
	 * Grafica el juego. <br>
	 */
	@Override
	public void graficar(final Graphics g) {
		g.drawImage(Recursos.background, 0, 0, juego.getAncho(), juego.getAlto(), null);
		mundo.graficar(g);
		// entidadPersonaje.graficar(g);
		graficarPersonajes(g);
		mundo.graficarObstaculos(g);
		entidadPersonaje.graficarNombre(g);
		g.drawImage(Recursos.marco, 0, 0, juego.getAncho(), juego.getAlto(), null);
		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 5, 5, paquetePersonaje, miniaturaPersonaje);
		if (haySolicitud) {
			menuEnemigo.graficar(g, tipoSolicitud);
		}
	}

	/**
	 * Grafica al personaje. <br>
	 * 
	 * @param g
	 *            Graficador. <br>
	 */
	public void graficarPersonajes(final Graphics g) {
		if (juego.getEscuchaMensajes().getPersonajesConectados() != null) {
			personajesConectados = new HashMap<Integer, PaquetePersonaje>(
					juego.getEscuchaMensajes().getPersonajesConectados());
			ubicacionPersonajes = new HashMap<Integer, PaqueteMovimiento>(
					juego.getEscuchaMensajes().getUbicacionPersonajes());
			Iterator<Integer> it = personajesConectados.keySet().iterator();
			int key;
			PaqueteMovimiento actual;
			g.setColor(Color.WHITE);
			g.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
			while (it.hasNext()) {
				key = it.next();
				actual = ubicacionPersonajes.get(key);
				if (actual != null && actual.getIdPersonaje() != juego.getPersonaje().getId()
						&& personajesConectados.get(actual.getIdPersonaje()).getEstado() == Estado.ESTADOJUEGO) {
					Pantalla.centerString(g,
							new Rectangle((int) (actual.getPosX() - juego.getCamara().getxOffset() + 32),
									(int) (actual.getPosY() - juego.getCamara().getyOffset() - 20), 0, 10),
							personajesConectados.get(actual.getIdPersonaje()).getNombre());
					g.drawImage(
							Recursos.personaje.get(personajesConectados.get(actual.getIdPersonaje()).getRaza())
									.get(actual.getDireccion())[actual.getFrame()],
							(int) (actual.getPosX() - juego.getCamara().getxOffset()),
							(int) (actual.getPosY() - juego.getCamara().getyOffset()), 64, 64, null);
				}
			}
		}
	}

	/**
	 * Devuelve al personaje. <br>
	 * 
	 * @return Personaje. <br>
	 */
	public Entidad getPersonaje() {
		return entidadPersonaje;
	}

	/**
	 * Devuelve el nombre del mundo. <br>
	 * 
	 * @return Nombre mundo. <br>
	 */
	private String getMundo() {
		int mundo = juego.getPersonaje().getMapa();
		if (mundo == 1) {
			return "Aubenor";
		} else {
			if (mundo == 2) {
				return "Aris";
			} else {
				if (mundo == 3) {
					return "Eodrim";
				}
			}
		}
		return null;
	}

	/**
	 * Establece el tipo de solicitud. <br>
	 * 
	 * @param b
	 *            Indicador de solicitud. <br>
	 * @param enemigo
	 *            Enemigo. <br>
	 * @param tipoSolicitud
	 *            Tipo de solicitud. <br>
	 */
	public void setHaySolicitud(final boolean b, final PaquetePersonaje enemigo, final int tipoSolicitud) {
		haySolicitud = b;
		// menu que mostrara al enemigo
		menuEnemigo = new MenuInfoPersonaje(300, 50, enemigo);
		this.tipoSolicitud = tipoSolicitud;
	}

	/**
	 * Devuelve si hay una solicitud. <br>
	 * 
	 * @return true si la hay, false de lo contrario. <br>
	 */
	public boolean getHaySolicitud() {
		return haySolicitud;
	}

	/**
	 * Actualiza al personaje. <br>
	 */
	public void actualizarPersonaje() {
		paquetePersonaje = juego.getPersonaje();
	}

	/**
	 * Devuelve el menú del enemigo. <br>
	 * 
	 * @return Menú enemigo. <br>
	 */
	public MenuInfoPersonaje getMenuEnemigo() {
		return menuEnemigo;
	}

	/**
	 * Devuelve el tipo de solicitud. <br>
	 * 
	 * @return Tipo de solicitud. <br>
	 */
	public int getTipoSolicitud() {
		return tipoSolicitud;
	}

	/**
	 * Indica el estado de juego. <br>
	 */
	@Override
	public boolean esEstadoDeJuego() {
		return true;
	}
}