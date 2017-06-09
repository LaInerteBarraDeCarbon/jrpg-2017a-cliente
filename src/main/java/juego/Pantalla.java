package juego;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.gson.Gson;

import cliente.Cliente;
import estados.Estado;
import frames.MenuInventario;
import frames.MenuJugar;
import mensajeria.Comando;
import mensajeria.Paquete;

/**
 * Clase que administra la pantalla. <br>
 */
public class Pantalla {
	/**
	 * Pantalla. <br>
	 */
	private JFrame pantalla;
	/**
	 * Canvas en el que dibuja. <br>
	 */
	private Canvas canvas;
	/**
	 * Gson Pantalla. <br>
	 */
	private final Gson gson = new Gson();

	/**
	 * Construye la pantalla del juego. <br>
	 * Se encarga de mostrar desde el mundo hasta el inventario. <br>
	 * 
	 * @param NOMBRE
	 *            Nombre del juego. <br>
	 * @param ANCHO
	 *            Ancho de pantalla. <br>
	 * @param ALTO
	 *            Alto de pantalla. <br>
	 * @param cliente
	 *            Usuario. <br>
	 */
	public Pantalla(final String NOMBRE, final int ANCHO, final int ALTO, final Cliente cliente) {
		pantalla = new JFrame(NOMBRE);
		pantalla.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon(MenuJugar.class.getResource("/cursor.png")).getImage(), new Point(0, 0),
				"custom cursor"));
		pantalla.setSize(ANCHO, ALTO);
		pantalla.setResizable(false);
		pantalla.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pantalla.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				try {
					Paquete p = new Paquete();
					p.setComando(Comando.DESCONECTAR);
					p.setIp(cliente.getMiIp());
					cliente.getSalida().writeObject(gson.toJson(p));
					cliente.getEntrada().close();
					cliente.getSalida().close();
					cliente.getSocket().close();
					System.exit(0);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Fallo al intentar cerrar la aplicación.");
					System.exit(1);
					e.printStackTrace();
				}
			}
		});
		pantalla.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_I) {
					if (Estado.getEstado().esEstadoDeJuego()) {
						MenuInventario menu = new MenuInventario(cliente);
						menu.setVisible(true);
					}
				}
			}
		});
		pantalla.setLocationRelativeTo(null);
		pantalla.setVisible(false);
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(ANCHO, ALTO));
		canvas.setMaximumSize(new Dimension(ANCHO, ALTO));
		canvas.setMinimumSize(new Dimension(ANCHO, ALTO));
		canvas.setFocusable(false);
		pantalla.add(canvas);
		pantalla.pack();
	}

	/**
	 * Devuelve el canvas. <br>
	 * 
	 * @return Canvas. <br>
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Muestra la pantalla. <br>
	 * 
	 * @return Pantalla. <br>
	 */
	public JFrame getFrame() {
		return pantalla;
	}

	/**
	 * Hace la pantalla visible. <br>
	 */
	public void mostrar() {
		pantalla.setVisible(true);
	}

	/**
	 * Centraliza la pantalla con el movimiento del jugador. <br>
	 * 
	 * @param g
	 *            Grafico. <br>
	 * @param r
	 *            Rectangulo. <br>
	 * @param s
	 *            String. <br>
	 */
	public static void centerString(Graphics g, Rectangle r, String s) {
		FontRenderContext frc = new FontRenderContext(null, true, true);
		Rectangle2D r2D = g.getFont().getStringBounds(s, frc);
		int rWidth = (int) Math.round(r2D.getWidth());
		int rHeight = (int) Math.round(r2D.getHeight());
		int rX = (int) Math.round(r2D.getX());
		int rY = (int) Math.round(r2D.getY());
		int a = (r.width / 2) - (rWidth / 2) - rX;
		int b = (r.height / 2) - (rHeight / 2) - rY;
		g.drawString(s, r.x + a, r.y + b);
	}
}