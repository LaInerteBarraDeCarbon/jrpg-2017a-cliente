package entidades;

/**
 * Clase que administra los nodos de pila de tiles. <br>
 */
public class NodoDePila {
	/**
	 * Posición X del tile. <br>
	 */
	private int x;
	/**
	 * Posición Y del tile. <br>
	 */
	private int y;
	/**
	 * Puntero a nodo siguiente. <br>
	 */
	private NodoDePila ptrSiguiente;

	/**
	 * Crea un nodo de tile con su posición en el mapa. <br>
	 * 
	 * @param x
	 *            Posición X. <br>
	 * @param y
	 *            Posición Y. <br>
	 */
	public NodoDePila(int x, int y) {
		this.x = x;
		this.y = y;
		ptrSiguiente = null;
	}

	/**
	 * Devuelve el siguiente nodo. <br>
	 * 
	 * @return Siguiente nodo. <br>
	 */
	public NodoDePila obtenerSiguiente() {
		return ptrSiguiente;
	}

	/**
	 * Establece el siguiente nodo de la pila. <br>
	 * 
	 * @param nodo
	 *            Siguiente nodo. <br>
	 */
	public void establecerSiguiente(NodoDePila nodo) {
		ptrSiguiente = nodo;
	}

	/**
	 * Obtiene la posición X del nodo. <br>
	 * 
	 * @return Posición X. <br>
	 */
	public int obtenerX() {
		return x;
	}

	/**
	 * Obtiene la posición Y del nodo. <br>
	 * 
	 * @return Posición Y. <br>
	 */
	public int obtenerY() {
		return y;
	}
}
