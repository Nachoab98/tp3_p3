package model;

import java.util.Arrays;
import java.util.LinkedList;

// clase encargada de encontrar la solucion optima del problema
// NOTA: todo lo que se ve a continuacion esta sacado de
// https://www.youtube.com/watch?v=iIX1YvbLbvc
public class OPT {

	private static LinkedList<Reserva> _ofertas;
	private static int[] _wTable;
	private static int[] _pTable;
	private static int[] _optTable;

	public static LinkedList<Reserva> solucionOptima(LinkedList<Reserva> ofertas) {

		ordenarOfertas(ofertas);
		calcular_wTable();
		calcular_pTable();
		calcular_optTable();

		return backtrackSolucion();
	}

	private static void ordenarOfertas(LinkedList<Reserva> ofertas) {
		_ofertas = new LinkedList<Reserva>(ofertas);
		_ofertas.sort(new ComparadorOPT());
	}

	// calcular w
	// simplemente deja el precio en un array para acceso mas facil
	private static void calcular_wTable() {
		_wTable = new int[_ofertas.size()];
		for (int i = 0; i < _ofertas.size(); i++) {
			_wTable[i] = _ofertas.get(i).getPrecioOfrecido();
		}
	}

	// calcular p, devuelve en la posicion i cual es la oferta anterior que termina
	// mas cerca del inicio la oferta i (oferta compatible previa)
	// si _pTable[i] = -1, no hay oferta compatible previa para i
	private static void calcular_pTable() {
		_pTable = new int[_ofertas.size()];
		for (int i = 0; i < _ofertas.size(); i++) {
			_pTable[i] = -1;
			for (int j = 0; j < i; j++) {
				if (_ofertas.get(j).getHoraFin() <= _ofertas.get(i).getHoraInicio()) {
					_pTable[i] = j;
				}
			}
		}
	}

	// calculamos opt. devuelvue en la posicion i cual es la mayor ganancia posible
	// hasta la posicion i
	private static void calcular_optTable() {
		_optTable = new int[_ofertas.size()];
		Arrays.fill(_optTable, -1);
		opt(_ofertas.size() - 1);
	}

	private static int opt(int i) {
		if (i == -1) {
			return 0;
		}
		// prog dinamica: vemos si fue calculado anteriormente
		if (_optTable[i] > -1) {
			return _optTable[i];
		}
		// evaluamos que pasa si incluimos o excluimos a i de la solucion
		int incluye_i = _wTable[i] + opt(_pTable[i]);
		int excluye_i = opt(i - 1);

		// nos quedamos con la mejor
		int res = Math.max(incluye_i, excluye_i);

		// prog dinamica: guardamos el resultado para futuros usos
		_optTable[i] = res;
		return res;
	}

	private static LinkedList<Reserva> backtrackSolucion() {
		LinkedList<Reserva> res = new LinkedList<Reserva>();

		// Recorremos hacia atrás para determinar las ofertas que forman la solución
		// óptima
		int i = _ofertas.size() - 1;
		while (i >= 0) {
			// Calculamos la ganancia al incluir la oferta i
			int incluye_i = _wTable[i];
			if (_pTable[i] != -1) { // Tiene una oferta compatible previa?
				incluye_i += _optTable[_pTable[i]];
			}
			// Comparamos la ganancia incluyendo la oferta actual vs. no incluirla
			if (_optTable[i] == incluye_i) {
				// Si la inclusión maximiza la ganancia, agregamos la oferta a la solución
				res.addFirst(_ofertas.get(i));
				i = _pTable[i]; // Avanzamos al siguiente índice compatible
			} else {
				i -= 1; // No incluimos esta oferta, avanzamos a la anterior
			}
		}
		return res;
	}
}