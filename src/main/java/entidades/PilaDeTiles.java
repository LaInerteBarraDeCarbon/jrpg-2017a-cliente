package entidades;

/**
 * Clase que administra la pila de los tiles. <br>
 */
public class PilaDeTiles {
	/**
	 * Nodo de pilas de tiles. <br>
	 */
	NodoDePila ptrPila;

	/**
	 * Crea un nodo de pila de tiles. <br>
	 */
	public PilaDeTiles() {
		ptrPila = null;
	}

	/**
	 * Establece el siguiente nodo de la pila. <br>
	 * 
	 * @param nodo
	 *            Nodo de pila. <br>
	 */
	public void push(final NodoDePila nodo) {
		nodo.establecerSiguiente(ptrPila);
		ptrPila = nodo;
	}

	/**
	 * Quita un nodo de la pila. <br>
	 * 
	 * @return Primer nodo. <br>
	 */
	public NodoDePila pop() {
		NodoDePila tope = ptrPila;
		if (tope == null) {
			return null;
		}
		ptrPila = ptrPila.obtenerSiguiente();
		return tope;
	}

	/**
	 * Indica si la pila se encuentra vacía. <br>
	 * 
	 * @return true si esta vacía, false de lo contrario. <br>
	 */
	public boolean estaVacia() {
		return ptrPila == null;
	}
}
