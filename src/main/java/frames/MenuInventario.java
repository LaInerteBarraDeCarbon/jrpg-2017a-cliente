package frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.google.gson.Gson;

import cliente.Cliente;
import inventario.Item;
import mensajeria.Comando;

/**
 * Clase que se encarga del menú del inventario. <br>
 */
@SuppressWarnings("serial")
public class MenuInventario extends JFrame {

	/**
	 * Botón de salir. <br>
	 */
	private JButton cancelar = new JButton("Salir");

	/**
	 * Muestra el menú de inventario del jugador. <br>
	 * 
	 * @param cliente
	 *            Cliente que lo invoca. <br>
	 */
	public MenuInventario(final Cliente cliente) {
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Gson gson = new Gson();
					cliente.getPaquetePersonaje().setComando(Comando.ACTUALIZARINVENTARIO);
					cliente.getSalida().writeObject(gson.toJson(cliente.getPaquetePersonaje()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		this.setTitle("Inventario");
		this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try {
			this.setLayout(new BorderLayout());
			this.add(new Item(cliente.getPaquetePersonaje()));
			this.add(cancelar, BorderLayout.AFTER_LAST_LINE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setBounds(600, 600, 600, 600);
		this.pack();
		this.setLocationRelativeTo(null);

		this.setLocation(900, 140);
		this.setResizable(false);
		this.setVisible(true);
	}
}