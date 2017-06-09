package inventario;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dominio.Inventario;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

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

	public ImagenItem() {
		label = new JLabel(new ImageIcon(Recursos.noItem.getScaledInstance(49, 49, Image.SCALE_DEFAULT)));
		add(label);
	}

	public ImagenItem(Inventario inventario) throws IOException {
		this.imagen = inventario.getFoto();
		label = new JLabel(new ImageIcon(this.imagen.getScaledInstance(49, 49, Image.SCALE_DEFAULT)));
		actionListenersYLabel(inventario);
	}

	public ImagenItem(Inventario inventario, PaquetePersonaje paquetePersonaje) throws IOException {
		this.imagen = inventario.getFoto();
		this.inventario = inventario;
		this.paquetePersonaje = paquetePersonaje;
		this.label = new JLabel(new ImageIcon(this.imagen.getScaledInstance(49, 49, Image.SCALE_DEFAULT)));
		actionListenersYLabel(inventario);
	}

	private void actionListenersYLabel(Inventario inventario) {
		StringBuilder s = new StringBuilder();

		s.append("<html>" + inventario.getNombre() + "<br>");
		if (inventario.getBonusSalud() != 0) {
			s.append("+" + inventario.getBonusSalud() + " Salud " + "<br>");
		}
		if (inventario.getBonusEnergia() != 0) {
			s.append("+" + inventario.getBonusEnergia() + " Energia " + "<br>");
		}
		if (inventario.getBonusFuerza() != 0) {
			s.append("+" + inventario.getBonusFuerza() + " Fuerza " + "<br>");
		}
		if (inventario.getBonusDestreza() != 0) {
			s.append("+" + inventario.getBonusDestreza() + " Destreza " + "<br>");
		}
		if (inventario.getBonusInteligencia() != 0) {
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

	protected void resetLabel() {
		label.setIcon(new ImageIcon(Recursos.noItem.getScaledInstance(49, 49, Image.SCALE_DEFAULT)));
		label.setToolTipText(null);
		paquetePersonaje.removerItem(inventario);
		label.removeMouseListener(mouseListener);
		removeMouseListener(mouseListener);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(60, 60);
	}

	public JLabel getLabel() {
		return label;
	}

	MouseListener mouseListener = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			Object[] options = { "Equipar", "Tirar", "Cancelar" };
			if (e.getClickCount() == 2) {
				int answer = JOptionPane.showOptionDialog(getParent(), "¿Qué desea hacer?",
						"Item: " + inventario.getNombre(), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, options, options[2]);
				// Tirar
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
