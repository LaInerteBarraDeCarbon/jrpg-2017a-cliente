package frames;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
/*import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;*/
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cliente.Cliente;
import mensajeria.Comando;

/**
 * Clase que administra el menú de registro de cliente. <br>
 */
@SuppressWarnings("serial")
public class MenuRegistro extends JFrame {
	/**
	 * Usuario del cliente. <br>
	 */
	private JTextField txtUsuario;
	/**
	 * Contraseña del cliente. <br>
	 */
	private JPasswordField pwPassword;

	/**
	 * Crea el menú de registro de usuario. <br>
	 * 
	 * @param cliente
	 *            ClienteComandos. <br>
	 */
	public MenuRegistro(final Cliente cliente) {
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				synchronized (cliente) {
					cliente.setAccion(Comando.SALIR);
					cliente.notify();
				}
				dispose();
			}
		});
		setTitle("WOME - Registrarse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		//
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 444, 271);
		getContentPane().add(layeredPane);
		//
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(113, 70, 57, 19);
		layeredPane.add(lblUsuario, new Integer(1));
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		//
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(113, 121, 65, 17);
		layeredPane.add(lblPassword, new Integer(1));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		//
		JLabel lblRegistrarse = new JLabel("Registrarse");
		lblRegistrarse.setBounds(186, 182, 82, 23);
		layeredPane.add(lblRegistrarse, new Integer(2));
		lblRegistrarse.setForeground(Color.WHITE);
		lblRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		//
		JButton btnRegistrarse = new JButton("");
		btnRegistrarse.setBounds(143, 182, 153, 23);
		layeredPane.add(btnRegistrarse, new Integer(1));
		btnRegistrarse.setFocusable(false);
		btnRegistrarse.setIcon(new ImageIcon(MenuRegistro.class.getResource("/frames/BotonMenu.png")));
		//
		pwPassword = new JPasswordField();
		pwPassword.setBounds(199, 120, 118, 20);
		layeredPane.add(pwPassword, new Integer(1));
		//
		txtUsuario = new JTextField();
		txtUsuario.setBounds(199, 69, 118, 20);
		layeredPane.add(txtUsuario, new Integer(1));
		txtUsuario.setColumns(10);
		//
		JLabel labelBackground = new JLabel("");
		labelBackground.setBounds(0, 0, 444, 271);
		layeredPane.add(labelBackground, new Integer(0));
		labelBackground.setIcon(new ImageIcon(MenuRegistro.class.getResource("/frames/menuBackground.jpg")));
		btnRegistrarse.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				synchronized (cliente) {
					cliente.getPaqueteUsuario().setUsername(txtUsuario.getText());
					cliente.getPaqueteUsuario().setPassword(pwPassword.getText());
					cliente.setAccion(Comando.REGISTRO);
					cliente.notify();
				}
				dispose();
			}
		});
	}

	/**
	 * Devuelve el nombre de usuario del cliente. <br>
	 * 
	 * @return Usuario. <br>
	 */
	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	/**
	 * Establece el nombre de usuario del cliente. <br>
	 * 
	 * @param txtUsuario
	 *            Usuario. <br>
	 */
	public void seTtxtUsuario(final JTextField txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	/**
	 * Devuelve la contraseña del cliente. <br>
	 * 
	 * @return Contraseña. <br>
	 */
	public JPasswordField getPasswordField() {
		return pwPassword;
	}

	/**
	 * Estavlece la contraseña del cliente. <br>
	 * 
	 * @param pwPassword
	 *            Contraseña. <br>
	 */
	public void setPasswordField(final JPasswordField pwPassword) {
		this.pwPassword = pwPassword;
	}
}