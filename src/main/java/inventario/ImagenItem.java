package inventario;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dominio.Inventario;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;
import utilitarias.Constantes;

/**
 * Clase que administra los icónos de los items. <br>
 */
@SuppressWarnings("serial")
public class ImagenItem extends JPanel {

	/**
	 * Imagen del item. <br>
	 */
	private Image imagen;
	/**
	 * Personaje. <br>
	 */
	private PaquetePersonaje paquetePersonaje;
	/**
	 * Label. <br>
	 */
	private JLabel label;
	/**
	 * Inventario del personaje. <br>
	 */
	private Inventario inventario;

	/**
	 * Crea el inventario básico. <br>
	 */
	public ImagenItem() {
		label = new JLabel(new ImageIcon(Recursos.noItem.getScaledInstance(Constantes.TAMAÑOLABELITEM,
				Constantes.TAMAÑOLABELITEM, Image.SCALE_DEFAULT)));
		add(label);
	}

	/**
	 * Carga el inventario del personaje. <br>
	 * 
	 * @param inventario
	 *            Inventario. <br>
	 * @param paquetePersonaje
	 *            Personaje. <br>
	 * @throws IOException
	 *             Error al abrir archivo. <br>
	 */
	public ImagenItem(final Inventario inventario, final PaquetePersonaje paquetePersonaje) throws IOException {
		this.imagen = inventario.getFoto();
		this.inventario = inventario;
		this.paquetePersonaje = paquetePersonaje;
		this.label = new JLabel(new ImageIcon(this.imagen.getScaledInstance(Constantes.TAMAÑOLABELITEM,
				Constantes.TAMAÑOLABELITEM, Image.SCALE_DEFAULT)));
		actionListenersYLabel(inventario);
	}

	/**
	 * Muestra los bonus del item. <br>
	 * 
	 * @param inventario
	 *            Inventario. <br>
	 */
	private void actionListenersYLabel(final Inventario inventario) {
		StringBuilder s = new StringBuilder();
		s.append("<html>" + inventario.getNombre() + "<br>");
		if (inventario.getBonusSalud() != Constantes.CERO) {
			s.append("+" + inventario.getBonusSalud() + " Salud " + "<br>");
		}
		if (inventario.getBonusEnergia() != Constantes.CERO) {
			s.append("+" + inventario.getBonusEnergia() + " Energia " + "<br>");
		}
		if (inventario.getBonusFuerza() != Constantes.CERO) {
			s.append("+" + inventario.getBonusFuerza() + " Fuerza " + "<br>");
		}
		if (inventario.getBonusDestreza() != Constantes.CERO) {
			s.append("+" + inventario.getBonusDestreza() + " Destreza " + "<br>");
		}
		if (inventario.getBonusInteligencia() != Constantes.CERO) {
			s.append("+" + inventario.getBonusInteligencia() + " Inteligencia");
		}
		s.append("</html>");
		label.setToolTipText(s.toString());
		label.addMouseListener(mouseListener);
		addMouseListener(mouseListener);
		add(label);
		this.validate();
		this.repaint();
	}

	/**
	 * Resetea el label de inventario. <br>
	 */
	protected void resetLabel() {
		label.setIcon(new ImageIcon(Recursos.noItem.getScaledInstance(Constantes.TAMAÑOLABELITEM,
				Constantes.TAMAÑOLABELITEM, Image.SCALE_DEFAULT)));
		label.setToolTipText(null);
		paquetePersonaje.removerItem(inventario);
		label.removeMouseListener(mouseListener);
		removeMouseListener(mouseListener);
	}

	/**
	 * Crea el tamaño de inventario. <br>
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Constantes.TAMAÑOCUADROITEM, Constantes.TAMAÑOCUADROITEM);
	}

	/**
	 * Muestra la pantalla de inventario. <br>
	 * 
	 * @return Inventario. <br>
	 */
	public JLabel getLabel() {
		return label;
	}

	/**
	 * Opción de mouse para decidir qué hacer con el item. <br>
	 */
	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(final MouseEvent e) {
			Object[] options = { "Equipar", "Tirar", "Cancelar" };
			if (e.getClickCount() == 2) {
				int answer = JOptionPane.showOptionDialog(getParent(), null, "Item: " + inventario.getNombre(),
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
				if (answer == 1) {
					paquetePersonaje.quitarBonus(inventario.getBonusSalud(), inventario.getBonusEnergia(),
							inventario.getBonusFuerza(), inventario.getBonusDestreza(),
							inventario.getBonusInteligencia());
					resetLabel();
				}
			}
		}
	};
}
