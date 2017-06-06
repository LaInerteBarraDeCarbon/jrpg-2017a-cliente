package inventario;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dominio.Inventario;
import mensajeria.PaquetePersonaje;
import recursos.Recursos;

public class ImagenItem extends JPanel {

	private BufferedImage imagen;
	private PaquetePersonaje paquetePersonaje;
	private JLabel label;
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
		label = new JLabel(new ImageIcon(imagen.getScaledInstance(49, 49, Image.SCALE_DEFAULT)));
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

		add(label);
		this.validate();
		this.repaint();

	}
}
