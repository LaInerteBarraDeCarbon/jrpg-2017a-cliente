package mensajeria;

/**
 * Clase que maneja las distintas opciones de comandos. <br>
 */
public class Comando {
	/**
	 * Actualizar el inventario. <br>
	 */
	public static final int ACTUALIZARINVENTARIO = 12;
	/**
	 * Actualizar el personaje. <br>
	 */
	public static final int ACTUALIZARPERSONAJE = 11;
	/**
	 * Atacar a otro personaje. <br>
	 */
	public static final int ATACAR = 9;
	/**
	 * Entrar en una batalla. <br>
	 */
	public static final int BATALLA = 8;
	/**
	 * Conectar al usuario al juego. <br>
	 */
	public static final int CONEXION = 0;
	/**
	 * Crear un personaje. <br>
	 */
	public static final int CREACIONPJ = 1;
	/**
	 * Desconectarse del servidor. <br>
	 */
	public static final int DESCONECTAR = 2;
	/**
	 * Finalizar la batalla. <br>
	 */
	public static final int FINALIZARBATALLA = 10;
	/**
	 * Iniciar sesión. <br>
	 */
	public static final int INICIOSESION = 3;
	/**
	 * Mostrar los mapas disponibles. <br>
	 */
	public static final int MOSTRARMAPAS = 4;
	/**
	 * Mover al personaje. <br>
	 */
	public static final int MOVIMIENTO = 5;
	/**
	 * Registrar un jugador. <br>
	 */
	public static final int REGISTRO = 6;
	/**
	 * Salir. <br>
	 */
	public static final int SALIR = 7;
}
