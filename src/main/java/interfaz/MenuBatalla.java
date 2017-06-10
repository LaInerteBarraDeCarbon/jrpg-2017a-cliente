package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import dominio.Personaje;
import juego.Pantalla;
import recursos.Recursos;

/**
 * Clase que dibuja el menú de batalla. <br>
 */
public class MenuBatalla {
	/**
	 * Máximo valor de X. <br>
	 */
	private static final int X = 100;
	/**
	 * Máximo valor de Y. <br>
	 */
	private static final int Y = 380;
	/**
	 * Ancho de los botones. <br>
	 */
	private static final int ANCHOBOTON = 40;
	/**
	 * Posición de los botones. <br>
	 */
	private static final int[][] BOTONES = { { X + 48, Y + 72 }, { X + 48, Y + 146 }, { X + 221, Y + 72 },
			{ X + 221, Y + 146 }, { X + 394, Y + 72 }, { X + 394, Y + 146 } };
	/**
	 * Indicador de si esta habilitado para realizar alguna acción. <br>
	 */
	private boolean habilitado;
	/**
	 * Personaje. <br>
	 */
	private Personaje personaje;

	/**
	 * Crea el menú de batalla. <br>
	 * 
	 * @param habilitado
	 *            Indicador si esta habilitado. <br>
	 * @param personaje
	 *            Personaje. <br>
	 */
	public MenuBatalla(final boolean habilitado, final Personaje personaje) {
		this.habilitado = habilitado;
		this.personaje = personaje;
	}

	/**
	 * Grafica el menú de batalla. <br>
	 * 
	 * @param g
	 *            Graficador. <br>
	 */
	public void graficar(final Graphics g) {
		if (habilitado) {
			g.drawImage(Recursos.menuBatalla, X, Y, null);
		} else {
			g.drawImage(Recursos.menuBatallaDeshabilitado, X, Y, null);
		}
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesRaza()[0]), BOTONES[0][0], BOTONES[0][1],
				ANCHOBOTON, ANCHOBOTON, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesRaza()[1]), BOTONES[1][0], BOTONES[1][1],
				ANCHOBOTON, ANCHOBOTON, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[0]), BOTONES[2][0], BOTONES[2][1],
				ANCHOBOTON, ANCHOBOTON, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[1]), BOTONES[3][0], BOTONES[3][1],
				ANCHOBOTON, ANCHOBOTON, null);
		g.drawImage(Recursos.habilidades.get(personaje.getHabilidadesCasta()[2]), BOTONES[4][0], BOTONES[4][1],
				ANCHOBOTON, ANCHOBOTON, null);
		g.drawImage(Recursos.habilidades.get("Ser Energizado"), BOTONES[5][0], BOTONES[5][1], ANCHOBOTON, ANCHOBOTON,
				null);
		g.setFont(new Font("Book Antiqua", 1, 14));
		g.drawString(personaje.getHabilidadesRaza()[0], X + 95, Y + 94);
		g.drawString(personaje.getHabilidadesRaza()[1], X + 95, Y + 168);
		g.drawString(personaje.getHabilidadesCasta()[0], X + 268, Y + 94);
		g.drawString(personaje.getHabilidadesCasta()[1], X + 268, Y + 168);
		g.drawString(personaje.getHabilidadesCasta()[2], X + 442, Y + 94);
		g.drawString("Ser energizado", X + 442, Y + 168);
		g.setColor(Color.WHITE);
		if (habilitado) {
			Pantalla.centerString(g, new Rectangle(X, Y + 5, Recursos.menuBatalla.getWidth(), 20), "Mi Turno");
		} else {
			Pantalla.centerString(g, new Rectangle(X, Y + 5, Recursos.menuBatalla.getWidth(), 20), "Turno Rival");
		}
	}

	/**
	 * Devuelve el botón clickeado. <br>
	 * 
	 * @param mouseX
	 *            Posición X mouse. <br>
	 * @param mouseY
	 *            Posición Y mouse. <br>
	 * @return Botón clickeado. <br>
	 */
	public int getBotonClickeado(final int mouseX, final int mouseY) {
		if (!habilitado) {
			return 0;
		}
		for (int i = 0; i < BOTONES.length; i++) {
			if (mouseX >= BOTONES[i][0] && mouseX <= BOTONES[i][0] + ANCHOBOTON && mouseY >= BOTONES[i][1]
					&& mouseY <= BOTONES[i][1] + ANCHOBOTON) {
				return i + 1;
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param mouseX
	 *            Posición X mouse. <br>
	 * @param mouseY
	 *            Posición Y mouse. <br>
	 * @return true si esta en un botón, false de lo contrario. <br>
	 */
	public boolean clickEnMenu(final int mouseX, final int mouseY) {
		if (mouseX >= X && mouseX <= X + Recursos.menuBatalla.getWidth() && mouseY >= Y
				&& mouseY <= Y + Recursos.menuBatalla.getHeight()) {
			return habilitado;
		}
		return false;
	}

	/**
	 * Indica la habilitación. <br>
	 * 
	 * @param b
	 *            Indicador. <br>
	 */
	public void setHabilitado(final boolean b) {
		habilitado = b;
	}
}
