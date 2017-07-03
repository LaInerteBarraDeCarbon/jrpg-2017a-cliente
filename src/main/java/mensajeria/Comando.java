package mensajeria;

/**
 * Clase que maneja las distintas opciones de comandos. <br>
 */
public abstract class Comando {
	/**
	 * Paquete del comando. <br>
	 */
	protected Paquete paquete;
	/**
	 * Nombre de las clases que utilizan los comandos. <br>
	 */
	public static final String[] CLASES = { "ActualizarPersonaje", "Atacar", "Batalla", "Conexion", "CreacionPJ",
			"Desconectar", "FinalizarBatalla", "InicioSesion", "MostrarMapas", "Movimiento", "Registro", "Salir" };
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
	/**
	 * Atacar a otro personaje. <br>
	 */
	public static final int ATACAR = 9;
	/**
	 * Entrar en una batalla. <br>
	 */
	public static final int BATALLA = 8;
	/**
	 * Finalizar la batalla. <br>
	 */
	public static final int FINALIZARBATALLA = 10;
	/**
	 * Actualizar el personaje. <br>
	 */
	public static final int ACTUALIZARPERSONAJE = 11;
	/**
	 * Actualizar el inventario. <br>
	 */
	public static final int ACTUALIZARINVENTARIO = 12;

	/**
	 * 
	 */
	public abstract void ejecutar();

	/**
	 * Establece el paquete a utilizar. <br>
	 * 
	 * @param paquete
	 *            Paquete a establecer. <br>
	 */
	public void setPaquete(final Paquete paquete) {
		this.paquete = paquete;
	}
}
