package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dominio.Personaje;
import juego.Pantalla;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

/**
 * Clase que administra los mensajes a mostrar según el momento. <br>
 */
public class MenuInfoPersonaje {
	/**
	 * Ancho del personaje. <br>
	 */
	private static final int ANCHOPERSONAJE = 128;
	/**
	 * Menú. <br>
	 */
	private static final BufferedImage MENU = Recursos.menuEnemigo;
	/**
	 * Menú de batalla. <br>
	 */
	public static final int MENUBATALLAR = 0;
	/**
	 * Menú de información. <br>
	 */
	public static final int MENUINFORMACION = 1;
	/**
	 * Menú de subir de nivel. <br>
	 */
	public static final int MENUSUBIRNIVEL = 2;
	/**
	 * Menú de ganar batalla. <br>
	 */
	public static final int MENUGANARBATALLA = 3;
	/**
	 * Menú de perder batalla. <br>
	 */
	public static final int MENUPERDERBATALLA = 4;
	/**
	 * Leyendas de botones. <br>
	 */
	private static final String[] LEYENDABOTON = { "Batallar", "Volver", "Aceptar", "Aceptar", "Aceptar" };
	/**
	 * Posición X. <br>
	 */
	private int x;
	/**
	 * Posición Y. <br>
	 */
	private int y;
	/**
	 * Personaje. <br>
	 */
	private PaquetePersonaje personaje;

	/**
	 * Crea un menú de información para el personaje. <br>
	 * 
	 * @param x
	 *            Posición X. <br>
	 * @param y
	 *            Posición Y. <br>
	 * @param personaje
	 *            Personaje. <br>
	 */
	public MenuInfoPersonaje(int x, int y, PaquetePersonaje personaje) {
		this.x = x;
		this.y = y;
		this.personaje = personaje;
	}

	/**
	 * Grafica un menú. <br>
	 * 
	 * @param g
	 *            Graficador. <br>
	 * @param tipoMenu
	 *            Tipo de menú a graficar. <br>
	 */
	public void graficar(Graphics g, int tipoMenu) {
		g.drawImage(MENU, x, y, null);
		g.drawImage(Recursos.personaje.get(personaje.getRaza()).get(6)[0], x + MENU.getWidth() / 2 - ANCHOPERSONAJE / 2,
				y + 70, 128, 128, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Book Antiqua", 1, 20));
		Pantalla.centerString(g, new Rectangle(x, y + 15, MENU.getWidth(), 0), personaje.getNombre());
		switch (tipoMenu) {
		case MENUBATALLAR:
			graficarMenuInformacion(g);
			break;
		case MENUINFORMACION:
			graficarMenuInformacion(g);
			break;
		case MENUSUBIRNIVEL:
			graficarMenuSubirNivel(g);
			break;
		case MENUGANARBATALLA:
			graficarMenuGanarBatalla(g);
			break;
		case MENUPERDERBATALLA:
			graficarMenuPerderBatalla(g);
			break;
		}
		g.setFont(new Font("Book Antiqua", 1, 20));
		g.drawImage(Recursos.botonMenu, x + 50, y + 380, 200, 25, null);
		g.setColor(Color.WHITE);
		Pantalla.centerString(g, new Rectangle(x + 50, y + 380, 200, 25), LEYENDABOTON[tipoMenu]);
	}

	/**
	 * Muestra el menú de derrota. <br>
	 * 
	 * @param g
	 *            Graficador. <br>
	 */
	private void graficarMenuPerderBatalla(Graphics g) {
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, MENU.getWidth(), 0), "¡Has sido derrotado!");
		g.setFont(new Font("Book Antiqua", 0, 14));
		Pantalla.centerString(g, new Rectangle(x, y + 250, MENU.getWidth(), 0), "¡No te rindas! Sigue luchando");
		Pantalla.centerString(g, new Rectangle(x, y + 270, MENU.getWidth(), 0), "contra los demás personajes");
		Pantalla.centerString(g, new Rectangle(x, y + 290, MENU.getWidth(), 0), "para aumentar tu nivel y");
		Pantalla.centerString(g, new Rectangle(x, y + 310, MENU.getWidth(), 0), "mejorar tus atributos.");
	}

	/**
	 * Muestra el menú de victoria. <br>
	 * 
	 * @param g
	 *            Graficador. <br>
	 */
	private void graficarMenuGanarBatalla(Graphics g) {
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, MENU.getWidth(), 0), "¡Has derrotado");
		Pantalla.centerString(g, new Rectangle(x, y + 230, MENU.getWidth(), 0), "a tu enemigo!");
		g.setFont(new Font("Book Antiqua", 0, 14));
		Pantalla.centerString(g, new Rectangle(x, y + 270, MENU.getWidth(), 0), "¡Felicitaciones! Has derrotado");
		Pantalla.centerString(g, new Rectangle(x, y + 290, MENU.getWidth(), 0), "a tu oponente, sigue así");
		Pantalla.centerString(g, new Rectangle(x, y + 310, MENU.getWidth(), 0), "para lograr subir de nivel");
		Pantalla.centerString(g, new Rectangle(x, y + 330, MENU.getWidth(), 0), "y mejorar tus atributos.");
	}

	/**
	 * Muestra el menú de subió de nivel. <br>
	 * 
	 * @param g
	 *            Graficador. <br>
	 */
	private void graficarMenuSubirNivel(Graphics g) {
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, MENU.getWidth(), 0), "?Has subido de nivel!");
		g.setFont(new Font("Book Antiqua", 0, 18));
		Pantalla.centerString(g, new Rectangle(x, y + 240, MENU.getWidth(), 0), "¡Felicitaciones!");
		Pantalla.centerString(g, new Rectangle(x, y + 270, MENU.getWidth(), 0), "Nuevo Nivel");
		g.setFont(new Font("Book Antiqua", 1, 62));
		Pantalla.centerString(g, new Rectangle(x, y + 325, MENU.getWidth(), 0), String.valueOf(personaje.getNivel()));

	}

	/**
	 * Muestra el menú de información de los atributos. <br>
	 * 
	 * @param g
	 *            Graficador. <br>
	 */
	public void graficarMenuInformacion(Graphics g) {
		g.setColor(Color.BLACK);
		Pantalla.centerString(g, new Rectangle(x, y + 200, MENU.getWidth(), 0), personaje.getRaza());
		g.drawString("Casta: ", x + 30, y + 260);
		g.drawString("Nivel: ", x + 30, y + 290);
		g.drawString("Experiencia: ", x + 30, y + 320);
		g.setFont(new Font("Book Antiqua", 0, 20));
		g.drawString(personaje.getCasta(), x + 100, y + 260);
		g.drawString(personaje.getNivel() + " ", x + 100, y + 290);
		g.drawString(personaje.getExperiencia() + " / " + Personaje.tablaDeNiveles[personaje.getNivel() + 1], x + 150,
				y + 320);
	}

	/**
	 * Devuelve si se presionó el botón. <br>
	 * 
	 * @param mouseX
	 *            Posición X mouse. <br>
	 * @param mouseY
	 *            Posición Y mouse. <br>
	 * @return true si preionó el botón, false de lo contrario. <br>
	 */
	public boolean clickEnBoton(int mouseX, int mouseY) {
		if (mouseX >= x + 50 && mouseX <= x + 250 && mouseY >= y + 380 && mouseY <= y + 405) {
			return true;
		}
		return false;
	}

	/**
	 * Devuelve si se presionó el botón para cerrar. <br>
	 * 
	 * @param mouseX
	 *            Posición X mouse. <br>
	 * @param mouseY
	 *            Posición Y mouse. <br>
	 * @return true si preionó el botón, false de lo contrario. <br>
	 */
	public boolean clickEnCerrar(int mouseX, int mouseY) {
		if (mouseX >= x + MENU.getWidth() - 24 && mouseX <= x + MENU.getWidth() + 4 && mouseY >= y + 12
				&& mouseY <= y + 36) {
			return true;
		}
		return false;
	}

	/**
	 * Devuelve si se presionó el botón de menú. <br>
	 * 
	 * @param mouseX
	 *            Posición X mouse. <br>
	 * @param mouseY
	 *            Posición Y mouse. <br>
	 * @return true si preionó el botón, false de lo contrario. <br>
	 */
	public boolean clickEnMenu(int mouseX, int mouseY) {
		if (mouseX >= x && mouseX <= x + MENU.getWidth() && mouseY >= y && mouseY <= y + MENU.getHeight()) {
			return true;
		}
		return false;
	}
}
