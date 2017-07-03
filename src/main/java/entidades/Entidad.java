package entidades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import estados.Estado;
import interfaz.MenuInfoPersonaje;
import juego.Juego;
import juego.Pantalla;
import mensajeria.PaqueteBatalla;
import mensajeria.PaqueteMovimiento;
import mundo.Grafo;
import mundo.Mundo;
import mundo.Nodo;
import recursos.Recursos;

/**
 * Clase que administra la personaje desde lo visual, a cómo se ve, cómo se
 * llama, a dónde se mueve, y más.
 */
public class Entidad {
	/**
	 * Juego. <br>
	 */
	Juego juego;
	/**
	 * Ancho entidad. <br>
	 */
	private int ancho;
	/**
	 * Alto entidad. <br>
	 */
	private int alto;
	/**
	 * Posición X. <br>
	 * 
	 * 
	 */
	private float x;
	/**
	 * Posición Y. <br>
	 */
	private float y;
	/**
	 * DX. <br>
	 */
	private float dx;
	/**
	 * DY. <br>
	 */
	private float dy;
	/**
	 * X Inicial. <br>
	 */
	@SuppressWarnings("unused")
	private float xInicio;
	/**
	 * Y Inicial. <br>
	 */
	@SuppressWarnings("unused")
	private float yInicio;
	/**
	 * X Final. <br>
	 */
	private float xFinal;
	/**
	 * Y Final. <br>
	 */
	private float yFinal;
	/**
	 * Offset X. <br>
	 */
	private int xOffset;
	/**
	 * Offset Y. <br>
	 */
	private int yOffset;
	/**
	 * Draw X. <br>
	 */
	private int drawX;
	/**
	 * Draw Y. <br>
	 */
	private int drawY;
	/**
	 * Posición mouse recorrido. <br>
	 */
	private int posMouseRecorrido[];
	/**
	 * Posición mouse. <br>
	 */
	private int posMouse[];
	/**
	 * Tile. <br>
	 */
	@SuppressWarnings("unused")
	private int[] tile;
	/**
	 * Movimiento horizontal derecha. <br>
	 */
	private static final int HORIZONTALDER = 4;
	/**
	 * Movimiento horizontal izquierda. <br>
	 */
	private static final int HORIZONTALIZQ = 0;
	/**
	 * Movimiento vertical superior. <br>
	 */
	private static final int VERTICALSUP = 2;
	/**
	 * Movimiento vertical inferior. <br>
	 */
	private static final int VERTICALINF = 6;
	/**
	 * Movimiento diagonal inferior izquierda. <br>
	 */
	private static final int DIAGONALINFIZQ = 7;
	/**
	 * Movimiento diagonal inferior derecha. <br>
	 */
	private static final int DIAGONALINFDER = 5;
	/**
	 * Movimiento diagonal superior derecha. <br>
	 */
	private static final int DIAGONALSUPDER = 3;
	/**
	 * Movimiento diagonal superior izquierda. <br>
	 */
	private static final int DIAGONALSUPIZQ = 1;
	/**
	 * Movimiento hacia. <br>
	 */
	private int movimientoHacia = 6;
	/**
	 * Indicador de movimiento. <br>
	 */
	private boolean enMovimiento;
	/**
	 * Animación de movimiento a izquierda. <br>
	 */
	private final Animacion moverIzq;
	/**
	 * Animación de movimiento a izquierda arriba. <br>
	 */
	private final Animacion moverArribaIzq;
	/**
	 * Animación de movimiento a arriba. <br>
	 */
	private final Animacion moverArriba;
	/**
	 * Animación de movimiento a derecha arriba . <br>
	 */
	private final Animacion moverArribaDer;
	/**
	 * Animación de movimiento a derecha. <br>
	 */
	private final Animacion moverDer;
	/**
	 * Animación de movimiento a derecha abajo. <br>
	 */
	private final Animacion moverAbajoDer;
	/**
	 * Animación de movimiento a abajo. <br>
	 */
	private final Animacion moverAbajo;
	/**
	 * Animación de movimiento a abajo izquierda. <br>
	 */
	private final Animacion moverAbajoIzq;
	/**
	 * Gson. <br>
	 */
	private final Gson gson = new Gson();
	/**
	 * Intervalo de envio. <br>
	 */
	private int intervaloEnvio = 0;

	// pila de movimiento
	/**
	 * Pila de movimiento de tiles. <br>
	 */
	private PilaDeTiles pilaMovimiento;
	/**
	 * Tile actual. <br>
	 */
	private int[] tileActual;
	/**
	 * Tile final. <br>
	 */
	private int[] tileFinal;
	/**
	 * Tile a moverme. <br>
	 */
	private int[] tileMoverme;
	/**
	 * Mundo. <br>
	 */
	private Mundo mundo;
	/**
	 * Nombre. <br>
	 */
	private String nombre;
	/**
	 * Tile de los personajes. <br>
	 */
	private int[] tilePersonajes;
	/**
	 * Id del personaje enemigo. <br>
	 */
	private int idEnemigo;

	/**
	 * Crea al personaje por pantalla. <br>
	 * 
	 * @param juego
	 *            Juego. <br>
	 * @param mundo
	 *            Mundo. <br>
	 * @param ancho
	 *            Ancho. <br>
	 * @param alto
	 *            Alto. <br>
	 * @param nombre
	 *            Nombre. <br>
	 * @param spawnX
	 *            Punto X origen. <br>
	 * @param spawnY
	 *            Punto Y origen. <br>
	 * @param animaciones
	 *            Animaciones. <br>
	 * @param velAnimacion
	 *            Velocidad de animación. <br>
	 */
	public Entidad(final Juego juego, final Mundo mundo, final int ancho, final int alto, final String nombre,
			final float spawnX, final float spawnY, final LinkedList<BufferedImage[]> animaciones,
			final int velAnimacion) {
		this.juego = juego;
		this.ancho = ancho;
		this.alto = alto;
		this.nombre = nombre;
		this.mundo = mundo;
		xOffset = ancho / 2;
		yOffset = alto / 2;
		x = (int) (spawnX / 64) * 64;
		y = (int) (spawnY / 32) * 32;
		moverIzq = new Animacion(velAnimacion, animaciones.get(0));
		moverArribaIzq = new Animacion(velAnimacion, animaciones.get(1));
		moverArriba = new Animacion(velAnimacion, animaciones.get(2));
		moverArribaDer = new Animacion(velAnimacion, animaciones.get(3));
		moverDer = new Animacion(velAnimacion, animaciones.get(4));
		moverAbajoDer = new Animacion(velAnimacion, animaciones.get(5));
		moverAbajo = new Animacion(velAnimacion, animaciones.get(6));
		moverAbajoIzq = new Animacion(velAnimacion, animaciones.get(7));
		juego.getUbicacionPersonaje().setPosX(x);
		juego.getUbicacionPersonaje().setPosY(y);
		juego.getUbicacionPersonaje().setDireccion(getDireccion());
		juego.getUbicacionPersonaje().setFrame(getFrame());
	}

	/**
	 * ACtualiza la posición del personaje. <br>
	 */
	public void actualizar() {
		if (enMovimiento) {
			moverIzq.actualizar();
			moverArribaIzq.actualizar();
			moverArriba.actualizar();
			moverArribaDer.actualizar();
			moverDer.actualizar();
			moverAbajoDer.actualizar();
			moverAbajo.actualizar();
			moverAbajoIzq.actualizar();
		} else {
			moverIzq.reset();
			moverArribaIzq.reset();
			moverArriba.reset();
			moverArribaDer.reset();
			moverDer.reset();
			moverAbajoDer.reset();
			moverAbajo.reset();
			moverAbajoIzq.reset();
		}
		getEntrada();
		mover();
		juego.getCamara().Centrar(this);
	}

	/**
	 * Obtiene las selecciones del mouse. <br>
	 */
	public void getEntrada() {
		posMouseRecorrido = juego.getHandlerMouse().getPosMouseRecorrido();
		posMouse = juego.getHandlerMouse().getPosMouse();
		// Tomo el click izquierdo
		if (juego.getHandlerMouse().getNuevoClick()) {
			if (juego.getEstadoJuego().getHaySolicitud()) {
				if (juego.getEstadoJuego().getMenuEnemigo().clickEnMenu(posMouse[0], posMouse[1])) {
					if (juego.getEstadoJuego().getMenuEnemigo().clickEnBoton(posMouse[0], posMouse[1])) {
						// pregunto si el menu emergente es de tipo batalla
						if (juego.getEstadoJuego().getTipoSolicitud() == MenuInfoPersonaje.MENUBATALLAR) {
							PaqueteBatalla pBatalla = new PaqueteBatalla();
							pBatalla.setId(juego.getPersonaje().getId());
							pBatalla.setIdEnemigo(idEnemigo);
							juego.getEstadoJuego().setHaySolicitud(false, null, 0);
							try {
								juego.getCliente().getSalida().writeObject(gson.toJson(pBatalla));
							} catch (IOException e) {
								JOptionPane.showMessageDialog(null, "Fallo la conexi�n con el servidor");
								e.printStackTrace();
							}
						} else {
							juego.getEstadoJuego().setHaySolicitud(false, null, 0);
						}
					} else {
						if (juego.getEstadoJuego().getMenuEnemigo().clickEnCerrar(posMouse[0], posMouse[1])) {
							juego.getEstadoJuego().setHaySolicitud(false, null, 0);
						}
					}
				} else {
					juego.getEstadoJuego().setHaySolicitud(false, null, 0);
				}
			} else {
				Iterator<Integer> it = juego.getEscuchaMensajes().getUbicacionPersonajes().keySet().iterator();
				int key;
				int tileMoverme[] = Mundo.mouseATile(posMouse[0] + juego.getCamara().getxOffset() - xOffset,
						posMouse[1] + juego.getCamara().getyOffset() - yOffset);
				PaqueteMovimiento actual;
				while (it.hasNext()) {
					key = (int) it.next();
					actual = juego.getEscuchaMensajes().getUbicacionPersonajes().get(key);
					tilePersonajes = Mundo.mouseATile(actual.getPosX(), actual.getPosY());
					if (actual != null && actual.getIdPersonaje() != juego.getPersonaje().getId()
							&& juego.getEscuchaMensajes().getPersonajesConectados().get(actual.getIdPersonaje()) != null
							&& juego.getEscuchaMensajes().getPersonajesConectados().get(actual.getIdPersonaje())
									.getEstado() == Estado.ESTADOJUEGO) {
						if (tileMoverme[0] == tilePersonajes[0] && tileMoverme[1] == tilePersonajes[1]) {
							idEnemigo = actual.getIdPersonaje();
							juego.getEstadoJuego().setHaySolicitud(true,
									juego.getEscuchaMensajes().getPersonajesConectados().get(idEnemigo),
									MenuInfoPersonaje.MENUBATALLAR);
							juego.getHandlerMouse().setNuevoClick(false);
						}
					}
				}
			}
		}
		if (juego.getHandlerMouse().getNuevoRecorrido() && !juego.getEstadoJuego().getHaySolicitud()) {
			tileMoverme = Mundo.mouseATile(posMouseRecorrido[0] + juego.getCamara().getxOffset() - xOffset,
					posMouseRecorrido[1] + juego.getCamara().getyOffset() - yOffset);
			juego.getHandlerMouse().setNuevoRecorrido(false);
			pilaMovimiento = null;
			juego.getEstadoJuego().setHaySolicitud(false, null, 0);
		}
		if (!enMovimiento && tileMoverme != null) {
			enMovimiento = false;
			xInicio = x;
			yInicio = y;
			tileActual = Mundo.mouseATile(x, y);
			if (tileMoverme[0] < 0 || tileMoverme[1] < 0 || tileMoverme[0] >= mundo.obtenerAncho()
					|| tileMoverme[1] >= mundo.obtenerAlto()) {
				enMovimiento = false;
				juego.getHandlerMouse().setNuevoRecorrido(false);
				pilaMovimiento = null;
				tileMoverme = null;
				return;
			}
			if (tileMoverme[0] == tileActual[0] && tileMoverme[1] == tileActual[1]
					|| mundo.getTile(tileMoverme[0], tileMoverme[1]).esSolido()) {
				tileMoverme = null;
				enMovimiento = false;
				juego.getHandlerMouse().setNuevoRecorrido(false);
				pilaMovimiento = null;
				return;
			}
			if (pilaMovimiento == null)
				pilaMovimiento = caminoMasCorto(tileActual[0], tileActual[1], tileMoverme[0], tileMoverme[1]);
			// Me muevo al primero de la pila
			NodoDePila nodoActualTile = pilaMovimiento.pop();
			if (nodoActualTile == null) {
				enMovimiento = false;
				juego.getHandlerMouse().setNuevoRecorrido(false);
				pilaMovimiento = null;
				tileMoverme = null;
				return;
			}
			tileFinal = new int[2];
			tileFinal[0] = nodoActualTile.obtenerX();
			tileFinal[1] = nodoActualTile.obtenerY();
			xFinal = Mundo.dosDaIso(tileFinal[0], tileFinal[1])[0];
			yFinal = Mundo.dosDaIso(tileFinal[0], tileFinal[1])[1];
			if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] - 1) {
				movimientoHacia = VERTICALSUP;
			}
			if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] + 1) {
				movimientoHacia = VERTICALINF;
			}
			if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1] + 1) {
				movimientoHacia = HORIZONTALIZQ;
			}
			if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1] - 1) {
				movimientoHacia = HORIZONTALDER;
			}
			if (tileFinal[0] == tileActual[0] - 1 && tileFinal[1] == tileActual[1]) {
				movimientoHacia = DIAGONALSUPIZQ;
			}
			if (tileFinal[0] == tileActual[0] + 1 && tileFinal[1] == tileActual[1]) {
				movimientoHacia = DIAGONALINFDER;
			}
			if (tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] - 1) {
				movimientoHacia = DIAGONALSUPDER;
			}
			if (tileFinal[0] == tileActual[0] && tileFinal[1] == tileActual[1] + 1) {
				movimientoHacia = DIAGONALINFIZQ;
			}
			enMovimiento = true;
		}
	}

	/**
	 * Mueve al personaje. <br>
	 */
	public void mover() {
		dx = 0;
		dy = 0;
		double paso = 1;
		if (enMovimiento && !(x == xFinal && y == yFinal - 32)) {
			if (movimientoHacia == VERTICALSUP) {
				dy -= paso;
			} else {
				if (movimientoHacia == VERTICALINF) {
					dy += paso;
				} else {
					if (movimientoHacia == HORIZONTALDER) {
						dx += paso;
					} else {
						if (movimientoHacia == HORIZONTALIZQ) {
							dx -= paso;
						} else {
							if (movimientoHacia == DIAGONALINFDER) {
								dx += paso;
								dy += paso / 2;
							} else {
								if (movimientoHacia == DIAGONALINFIZQ) {
									dx -= paso;
									dy += paso / 2;
								} else {
									if (movimientoHacia == DIAGONALSUPDER) {
										dx += paso;
										dy -= paso / 2;
									} else {
										if (movimientoHacia == DIAGONALSUPIZQ) {
											dx -= paso;
											dy -= paso / 2;
										}
									}
								}
							}
						}
					}
				}
			}
		}
		x += dx;
		y += dy;
		if (intervaloEnvio == 2) {
			enviarPosicion();
			intervaloEnvio = 0;
		}
		intervaloEnvio++;
		if (x == xFinal && y == yFinal - 32) {
			enMovimiento = false;
		}
	}

	/**
	 * Grafica. <br>
	 * 
	 * @param g
	 *            Graficador. <br>
	 */
	public void graficar(final Graphics g) {
		drawX = (int) (x - juego.getCamara().getxOffset());
		drawY = (int) (y - juego.getCamara().getyOffset());
		g.drawImage(getFrameAnimacionActual(), drawX, drawY + 4, ancho, alto, null);
	}

	/**
	 * Muestra el nombre del personaje. <br>
	 * 
	 * @param g
	 *            Graficador. <br>
	 */
	public void graficarNombre(final Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Book Antiqua", Font.BOLD, 15));
		Pantalla.centerString(g, new java.awt.Rectangle(drawX + 32, drawY - 20, 0, 10), nombre);
	}

	/**
	 * Devuelve la animación de frame del personaje al momento de moverse. <br>
	 * 
	 * @return Animación del frame. <br>
	 */
	private BufferedImage getFrameAnimacionActual() {
		if (movimientoHacia == HORIZONTALIZQ) {
			return moverIzq.getFrameActual();
		} else {
			if (movimientoHacia == HORIZONTALDER) {
				return moverDer.getFrameActual();
			} else {
				if (movimientoHacia == VERTICALSUP) {
					return moverArriba.getFrameActual();
				} else {
					if (movimientoHacia == VERTICALINF) {
						return moverAbajo.getFrameActual();
					} else {
						if (movimientoHacia == DIAGONALINFIZQ) {
							return moverAbajoIzq.getFrameActual();
						} else {
							if (movimientoHacia == DIAGONALINFDER) {
								return moverAbajoDer.getFrameActual();
							} else {
								if (movimientoHacia == DIAGONALSUPIZQ) {
									return moverArribaIzq.getFrameActual();
								} else {
									if (movimientoHacia == DIAGONALSUPDER) {
										return moverArribaDer.getFrameActual();
									}
								}
							}
						}
					}
				}
			}
		}
		return Recursos.orco.get(6)[0];
	}

	/**
	 * Devuelve la orientación a la que va. <br>
	 * 
	 * @return Orientación. <br>
	 */
	private int getDireccion() {
		return movimientoHacia;
	}

	/**
	 * Devuelve el frame de movimiento. <br>
	 * 
	 * @return Frame movimiento. <br>
	 */
	private int getFrame() {
		if (movimientoHacia == HORIZONTALIZQ) {
			return moverIzq.getFrame();
		} else {
			if (movimientoHacia == HORIZONTALDER) {
				return moverDer.getFrame();
			} else {
				if (movimientoHacia == VERTICALSUP) {
					return moverArriba.getFrame();
				} else {
					if (movimientoHacia == VERTICALINF) {
						return moverAbajo.getFrame();
					} else {
						if (movimientoHacia == DIAGONALINFIZQ) {
							return moverAbajoIzq.getFrame();
						} else {
							if (movimientoHacia == DIAGONALINFDER) {
								return moverAbajoDer.getFrame();
							} else {
								if (movimientoHacia == DIAGONALSUPIZQ) {
									return moverArribaIzq.getFrame();
								} else {
									if (movimientoHacia == DIAGONALSUPDER) {
										return moverArribaDer.getFrame();
									}
								}
							}
						}
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Envia la posición. <br>
	 */
	private void enviarPosicion() {
		juego.getUbicacionPersonaje().setPosX(x);
		juego.getUbicacionPersonaje().setPosY(y);
		juego.getUbicacionPersonaje().setDireccion(getDireccion());
		juego.getUbicacionPersonaje().setFrame(getFrame());
		try {
			juego.getCliente().getSalida()
					.writeObject(gson.toJson(juego.getUbicacionPersonaje(), PaqueteMovimiento.class));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Fallo la conexión con el servidor.");
		}
	}

	/**
	 * Devuelve el camino más corto para moverse entre tiles. <br>
	 * 
	 * @param xInicial
	 *            Posición X inicial. <br>
	 * @param yInicial
	 *            Posición Y inicial. <br>
	 * @param xFinal
	 *            Posición X final. <br>
	 * @param yFinal
	 *            Posición Y final. <br>
	 * @return Camino más corto. <br>
	 */
	private PilaDeTiles caminoMasCorto(final int xInicial, final int yInicial, final int xFinal, final int yFinal) {
		Grafo grafoLibres = mundo.obtenerGrafoDeTilesNoSolidos();
		// Transformo las coordenadas iniciales y finales en indices
		int nodoInicial = (yInicial - grafoLibres.obtenerNodos()[0].obtenerY())
				* (int) Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xInicial
				- grafoLibres.obtenerNodos()[0].obtenerX();
		int nodoFinal = (yFinal - grafoLibres.obtenerNodos()[0].obtenerY())
				* (int) Math.sqrt(grafoLibres.obtenerCantidadDeNodosTotal()) + xFinal
				- grafoLibres.obtenerNodos()[0].obtenerX();
		// Hago todo
		double[] vecCostos = new double[grafoLibres.obtenerCantidadDeNodosTotal()];
		int[] vecPredecesores = new int[grafoLibres.obtenerCantidadDeNodosTotal()];
		boolean[] conjSolucion = new boolean[grafoLibres.obtenerCantidadDeNodosTotal()];
		int cantSolucion = 0;
		// Lleno la matriz de costos de numeros grandes
		for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
			vecCostos[i] = Double.MAX_VALUE;
		}
		// Adyacentes al nodo inicial
		conjSolucion[nodoInicial] = true;
		cantSolucion++;
		vecCostos[nodoInicial] = 0;
		Nodo[] adyacentes = grafoLibres.obtenerNodos()[nodoInicial].obtenerNodosAdyacentes();
		for (int i = 0; i < grafoLibres.obtenerNodos()[nodoInicial].obtenerCantidadDeAdyacentes(); i++) {
			if (estanEnDiagonal(grafoLibres.obtenerNodos()[nodoInicial],
					grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()])) {
				vecCostos[adyacentes[i].obtenerIndice()] = 1.5;
			} else {
				vecCostos[adyacentes[i].obtenerIndice()] = 1;
			}
			vecPredecesores[adyacentes[i].obtenerIndice()] = nodoInicial;
		}
		// Aplico Dijkstra
		while (cantSolucion < grafoLibres.obtenerCantidadDeNodosTotal()) {
			// Elijo W perteneciente al conjunto restante tal que el costo de W
			// sea minimo
			double minimo = Double.MAX_VALUE;
			int indiceMinimo = 0;
			@SuppressWarnings("unused")
			Nodo nodoW = null;
			for (int i = 0; i < grafoLibres.obtenerCantidadDeNodosTotal(); i++) {
				if (!conjSolucion[i] && vecCostos[i] < minimo) {
					nodoW = grafoLibres.obtenerNodos()[i];
					minimo = vecCostos[i];
					indiceMinimo = i;
				}
			}
			// Pongo a W en el conj solucion
			conjSolucion[indiceMinimo] = true;
			cantSolucion++;
			// Por cada nodo I adyacente a W del conj restante
			// Le sumo 1 al costo de ir hasta W y luego ir hasta su adyacente
			adyacentes = grafoLibres.obtenerNodos()[indiceMinimo].obtenerNodosAdyacentes();
			for (int i = 0; i < grafoLibres.obtenerNodos()[indiceMinimo].obtenerCantidadDeAdyacentes(); i++) {
				double valorASumar = 1;
				if (estanEnDiagonal(grafoLibres.obtenerNodos()[indiceMinimo],
						grafoLibres.obtenerNodos()[adyacentes[i].obtenerIndice()])) {
					valorASumar = 1.5;
				}
				if (vecCostos[indiceMinimo] + valorASumar < vecCostos[adyacentes[i].obtenerIndice()]) {
					vecCostos[adyacentes[i].obtenerIndice()] = vecCostos[indiceMinimo] + valorASumar;
					vecPredecesores[adyacentes[i].obtenerIndice()] = indiceMinimo;
				}
			}
		}
		// Creo el vector de nodos hasta donde quiere llegar
		PilaDeTiles camino = new PilaDeTiles();
		while (nodoFinal != nodoInicial) {
			camino.push(new NodoDePila(grafoLibres.obtenerNodos()[nodoFinal].obtenerX(),
					grafoLibres.obtenerNodos()[nodoFinal].obtenerY()));
			nodoFinal = vecPredecesores[nodoFinal];
		}
		return camino;
	}

	/**
	 * Indica si esta en diagonal. <br>
	 * 
	 * @param nodoUno
	 *            Nodo uno. <br>
	 * @param nodoDos
	 *            Nodo dos. <br>
	 * @return true si están en diagonal, false de lo contrario. <br>
	 */
	private boolean estanEnDiagonal(final Nodo nodoUno, final Nodo nodoDos) {
		if (nodoUno.obtenerX() == nodoDos.obtenerX() || nodoUno.obtenerY() == nodoDos.obtenerY()) {
			return false;
		}
		return true;
	}

	/**
	 * Devuelve la posición X. <br>
	 * 
	 * @return Posición X. <br>
	 */
	public float getX() {
		return x;
	}

	/**
	 * Establece la posición X. <br>
	 * 
	 * @param x
	 *            Posición X. <br>
	 */
	public void setX(final float x) {
		this.x = x;
	}

	/**
	 * Devuelve la posición Y. <br>
	 * 
	 * @return Posición Y. <br>
	 */
	public float getY() {
		return y;
	}

	/**
	 * Establece la posición Y. <br>
	 * 
	 * @param y
	 *            Posición Y. <br>
	 */
	public void setY(final float y) {
		this.y = y;
	}

	/**
	 * Devuelve el ancho. <br>
	 * 
	 * @return Ancho. <br>
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Establece el ancho. <br>
	 * 
	 * @param ancho
	 *            Ancho. <br>
	 */
	public void setAncho(final int ancho) {
		this.ancho = ancho;
	}

	/**
	 * Devuelve el alto. <br>
	 * 
	 * @return Alto. <br>
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Establece el alto. <br>
	 * 
	 * @param alto
	 *            Alto. <br>
	 */
	public void setAlto(final int alto) {
		this.alto = alto;
	}

	/**
	 * Devuelve el offset de X. <br>
	 * 
	 * @return Offset X. <br>
	 */
	public int getxOffset() {
		return xOffset;
	}

	/**
	 * Devuelve el offset de Y. <br>
	 * 
	 * @return Offset Y. <br>
	 */
	public int getYOffset() {
		return yOffset;
	}
}
