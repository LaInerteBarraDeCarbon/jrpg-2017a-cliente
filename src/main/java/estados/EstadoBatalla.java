package estados;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import dominio.Asesino;
import dominio.Casta;
import dominio.Elfo;
import dominio.Guerrero;
import dominio.Hechicero;
import dominio.Humano;
import dominio.Orco;
import dominio.Personaje;
import interfaz.EstadoDePersonaje;
import interfaz.MenuBatalla;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import mensajeria.Comando;
import mensajeria.PaqueteAtacar;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteFinalizarBatalla;
import mensajeria.PaquetePersonaje;
import mundo.Mundo;
import recursos.Recursos;

/**
 * Clase que administra el estado de batalla del personaje. <br>
 */
public class EstadoBatalla extends Estado {
	/**
	 * Mundo. <br>
	 */
	private Mundo mundo;
	/**
	 * Personaje. <br>
	 */
	private Personaje personaje;
	/**
	 * Personaje enemigo. <br>
	 */
	private Personaje enemigo;
	/**
	 * Posición del mouse. <br>
	 */
	private int[] posMouse;
	/**
	 * Personaje del cliente. <br>
	 */
	private PaquetePersonaje paquetePersonaje;
	/**
	 * Personaje del otro cliente. <br>
	 */
	private PaquetePersonaje paqueteEnemigo;
	/**
	 * Opciones de ataque. <br>
	 */
	private PaqueteAtacar paqueteAtacar;
	/**
	 * Paquete post batalla. <br>
	 */
	private PaqueteFinalizarBatalla paqueteFinalizarBatalla;
	/**
	 * Indicador de turno. <br>
	 */
	private boolean miTurno;
	/**
	 * Indicador de habilidad. <br>
	 */
	private boolean haySpellSeleccionada;
	/**
	 * Indicador de acción realizada. <br>
	 */
	private boolean seRealizoAccion;
	/**
	 * Gson. <br>
	 */
	private Gson gson = new Gson();
	/**
	 * Imágen miniatura del personaje. <br>
	 */
	private BufferedImage miniaturaPersonaje;
	/**
	 * Miniatura del enemigo. <br>
	 */
	private BufferedImage miniaturaEnemigo;
	/**
	 * Menú de batalla. <br>
	 */
	private MenuBatalla menuBatalla;

	/**
	 * Crea un estado de batalla entre un personaje y su contrincante. <br>
	 * 
	 * @param juego
	 *            Juego. <br>
	 * @param paqueteBatalla
	 *            Batalla. <br>
	 */
	public EstadoBatalla(final Juego juego, final PaqueteBatalla paqueteBatalla) {
		super(juego);
		mundo = new Mundo(juego, "recursos/mundoBatalla.txt", "recursos/mundoBatallaCapaDos.txt");
		miTurno = paqueteBatalla.isMiTurno();
		paquetePersonaje = juego.getEscuchaMensajes().getPersonajesConectados().get(paqueteBatalla.getId());
		paqueteEnemigo = juego.getEscuchaMensajes().getPersonajesConectados().get(paqueteBatalla.getIdEnemigo());
		crearPersonajes();
		menuBatalla = new MenuBatalla(miTurno, personaje);
		miniaturaEnemigo = Recursos.personaje.get(enemigo.getNombreRaza()).get(5)[0];
		miniaturaPersonaje = Recursos.personaje.get(personaje.getNombreRaza()).get(5)[0];
		paqueteFinalizarBatalla = new PaqueteFinalizarBatalla();
		paqueteFinalizarBatalla.setId(personaje.getIdPersonaje());
		paqueteFinalizarBatalla.setIdEnemigo(enemigo.getIdPersonaje());
		juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(), MenuInfoPersonaje.MENUPERDERBATALLA);
		juego.getHandlerMouse().setNuevoClick(false);
	}

	/**
	 * Actualiza el estado de la batalla. <br>
	 */
	@Override
	public void actualizar() {
		juego.getCamara().setxOffset(-350);
		juego.getCamara().setyOffset(150);
		seRealizoAccion = false;
		haySpellSeleccionada = false;
		if (miTurno) {
			if (juego.getHandlerMouse().getNuevoClick()) {
				posMouse = juego.getHandlerMouse().getPosMouse();
				if (menuBatalla.clickEnMenu(posMouse[0], posMouse[1])) {
					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 1) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadRaza1(enemigo);
						}
						haySpellSeleccionada = true;
					}
					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 2) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadRaza2(enemigo);
						}
						haySpellSeleccionada = true;
					}
					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 3) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadCasta1(enemigo);
						}
						haySpellSeleccionada = true;
					}
					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 4) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadCasta2(enemigo);
						}
						haySpellSeleccionada = true;
					}
					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 5) {
						if (personaje.puedeAtacar()) {
							seRealizoAccion = true;
							personaje.habilidadCasta3(enemigo);
						}
						haySpellSeleccionada = true;
					}
					if (menuBatalla.getBotonClickeado(posMouse[0], posMouse[1]) == 6) {
						seRealizoAccion = true;
						personaje.serEnergizado(10);
						haySpellSeleccionada = true;
					}
				}
				if (haySpellSeleccionada && seRealizoAccion) {
					if (!enemigo.estaVivo()) {
						juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(),
								MenuInfoPersonaje.MENUGANARBATALLA);
						if (personaje.ganarExperiencia(enemigo.getNivel() * 40)) {
							juego.getPersonaje().setNivel(personaje.getNivel());
							juego.getEstadoJuego().setHaySolicitud(true, juego.getPersonaje(),
									MenuInfoPersonaje.MENUSUBIRNIVEL);
						}
						ganarItem();
						finalizarBatalla();
						Estado.setEstado(juego.getEstadoJuego());
					} else {
						paqueteAtacar = new PaqueteAtacar(paquetePersonaje.getId(), paqueteEnemigo.getId(),
								personaje.getSalud(), personaje.getEnergia(), enemigo.getSalud(), enemigo.getEnergia(),
								personaje.getDefensa(), enemigo.getDefensa(),
								personaje.getCasta().getProbabilidadEvitarDaño(),
								enemigo.getCasta().getProbabilidadEvitarDaño());
						enviarAtaque(paqueteAtacar);
						miTurno = false;
						menuBatalla.setHabilitado(false);
					}
				} else if (haySpellSeleccionada && !seRealizoAccion) {
					JOptionPane.showMessageDialog(null,
							"No posees la energía suficiente para realizar esta habilidad.");
				}
				juego.getHandlerMouse().setNuevoClick(false);
			}
		}
	}

	/**
	 * Entrega un item al ganador. <br>
	 */
	private void ganarItem() {
		if (paquetePersonaje.getCantidadObjetosInventario() < 9) {
			int itemGanado = new Random().nextInt(19);
			itemGanado += 1;
			paquetePersonaje.añadirItem(itemGanado);
		}
	}

	/**
	 * Grafica la batalla. <br>
	 */
	@Override
	public void graficar(final Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, juego.getAncho(), juego.getAlto());
		mundo.graficar(g);
		g.drawImage(Recursos.personaje.get(paquetePersonaje.getRaza()).get(3)[0], 0, 175, 256, 256, null);
		g.drawImage(Recursos.personaje.get(paqueteEnemigo.getRaza()).get(7)[0], 550, 75, 256, 256, null);
		mundo.graficarObstaculos(g);
		menuBatalla.graficar(g);
		g.setColor(Color.GREEN);
		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 25, 5, personaje, miniaturaPersonaje);
		EstadoDePersonaje.dibujarEstadoDePersonaje(g, 550, 5, enemigo, miniaturaEnemigo);

	}

	/**
	 * Crea a los personajes. <br>
	 */
	private void crearPersonajes() {
		String nombre = paquetePersonaje.getNombre();
		int salud = paquetePersonaje.getSaludTope();
		int energia = paquetePersonaje.getEnergiaTope();
		int fuerza = paquetePersonaje.getFuerza();
		int destreza = paquetePersonaje.getDestreza();
		int inteligencia = paquetePersonaje.getInteligencia();
		int experiencia = paquetePersonaje.getExperiencia();
		int nivel = paquetePersonaje.getNivel();
		int id = paquetePersonaje.getId();
		Casta casta = null;
		if (paquetePersonaje.getCasta().equals("Guerrero")) {
			casta = new Guerrero();
		} else {
			if (paquetePersonaje.getCasta().equals("Hechicero")) {
				casta = new Hechicero();
			} else {
				if (paquetePersonaje.getCasta().equals("Asesino")) {
					casta = new Asesino();
				}
			}
		}
		if (paquetePersonaje.getRaza().equals("Humano")) {
			personaje = new Humano(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel,
					id);
		} else {
			if (paquetePersonaje.getRaza().equals("Orco")) {
				personaje = new Orco(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel,
						id);
			} else {
				if (paquetePersonaje.getRaza().equals("Elfo")) {
					personaje = new Elfo(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia,
							nivel, id);
				}
			}
		}
		nombre = paqueteEnemigo.getNombre();
		salud = paqueteEnemigo.getSaludTope();
		energia = paqueteEnemigo.getEnergiaTope();
		fuerza = paqueteEnemigo.getFuerza();
		destreza = paqueteEnemigo.getDestreza();
		inteligencia = paqueteEnemigo.getInteligencia();
		experiencia = paqueteEnemigo.getExperiencia();
		nivel = paqueteEnemigo.getNivel();
		id = paqueteEnemigo.getId();
		casta = null;
		if (paqueteEnemigo.getCasta().equals("Guerrero")) {
			casta = new Guerrero();
		} else {
			if (paqueteEnemigo.getCasta().equals("Hechicero")) {
				casta = new Hechicero();
			} else {
				if (paqueteEnemigo.getCasta().equals("Asesino")) {
					casta = new Asesino();
				}
			}
		}
		if (paqueteEnemigo.getRaza().equals("Humano")) {
			enemigo = new Humano(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel, id);
		} else {
			if (paqueteEnemigo.getRaza().equals("Orco")) {
				enemigo = new Orco(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia, nivel,
						id);
			} else {
				if (paqueteEnemigo.getRaza().equals("Elfo")) {
					enemigo = new Elfo(nombre, salud, energia, fuerza, destreza, inteligencia, casta, experiencia,
							nivel, id);
				}
			}
		}
	}

	/**
	 * Envía un ataque al servidor. <br>
	 * 
	 * @param paqueteAtacar
	 *            Atacar. <br>
	 */
	public void enviarAtaque(final PaqueteAtacar paqueteAtacar) {
		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteAtacar));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexion con el servidor.");
		}
	}

	/**
	 * Finaliza la batalla. <br>
	 */
	private void finalizarBatalla() {
		try {
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteFinalizarBatalla));
			paquetePersonaje.setSaludTope(personaje.getSaludTope());
			paquetePersonaje.setEnergiaTope(personaje.getEnergiaTope());
			paquetePersonaje.setNivel(personaje.getNivel());
			paquetePersonaje.setExperiencia(personaje.getExperiencia());
			paquetePersonaje.setDestreza(personaje.getDestreza());
			paquetePersonaje.setFuerza(personaje.getFuerza());
			paquetePersonaje.setInteligencia(personaje.getInteligencia());
			paquetePersonaje.removerBonus();
			paqueteEnemigo.setSaludTope(enemigo.getSaludTope());
			paqueteEnemigo.setEnergiaTope(enemigo.getEnergiaTope());
			paqueteEnemigo.setNivel(enemigo.getNivel());
			paqueteEnemigo.setExperiencia(enemigo.getExperiencia());
			paqueteEnemigo.setDestreza(enemigo.getDestreza());
			paqueteEnemigo.setFuerza(enemigo.getFuerza());
			paqueteEnemigo.setInteligencia(enemigo.getInteligencia());
			paqueteEnemigo.removerBonus();
			paquetePersonaje.setComando(Comando.ACTUALIZARPERSONAJE);
			paqueteEnemigo.setComando(Comando.ACTUALIZARPERSONAJE);
			juego.getCliente().getSalida().writeObject(gson.toJson(paquetePersonaje));
			juego.getCliente().getSalida().writeObject(gson.toJson(paqueteEnemigo));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor");
		}
	}

	/**
	 * Devuelve al personaje. <br>
	 * 
	 * @return Personaje. <br>
	 */
	public PaquetePersonaje getPaquetePersonaje() {
		return paquetePersonaje;
	}

	/**
	 * Devuelve al personaje enemigo. <br>
	 * 
	 * @return Personaje enemigo. <br>
	 */
	public PaquetePersonaje getPaqueteEnemigo() {
		return paqueteEnemigo;
	}

	/**
	 * Establece el turno del personaje. <br>
	 * 
	 * @param b
	 *            Indicador de turno. <br>
	 */
	public void setMiTurno(final boolean b) {
		miTurno = b;
		menuBatalla.setHabilitado(b);
		juego.getHandlerMouse().setNuevoClick(false);
	}

	/**
	 * Devuelve el estado del personaje. <br>
	 * 
	 * @return Personaje. <br>
	 */
	public Personaje getPersonaje() {
		return personaje;
	}

	/**
	 * Devuelve el estado del personaje enemigo. <br>
	 * 
	 * @return Enemigo. <br>
	 */
	public Personaje getEnemigo() {
		return enemigo;
	}

	/**
	 * Indica si se encuentra en estado de juego. <br>
	 */
	@Override
	public boolean esEstadoDeJuego() {
		return false;
	}
}