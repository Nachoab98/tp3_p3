package model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

// clase encargada de encontrar la solucion optima del problema
// NOTA: todo lo que se ve a continuacion esta sacado de
// https://www.youtube.com/watch?v=iIX1YvbLbvc
public class OPT {

	private static LinkedList<Reserva> _ofertas;
	private static int[] _wTable;
	private static int[] _pTable;
	private static int[] _optTable;

	public static int maxGanancia(LinkedList<Reserva> ofertas) {

		// ordenamos segun hora fin
		_ofertas = new LinkedList<Reserva>(ofertas);
		_ofertas.sort(new Comparator<Reserva>() {
			@Override
			public int compare(Reserva o1, Reserva o2) {
				int res = Integer.compare(o1.getHoraFin(), o2.getHoraFin());
				if (res != 0) {
					return res;
				}
				res = Integer.compare(o1.getHoraInicio(), o2.getHoraInicio());
				if (res != 0) {
					return res;
				}
				return Integer.compare(o1.getPrecioOfrecido(), o2.getPrecioOfrecido());
			}
		});

		// calcular w
		// simplemente deja el precio en un array para acceso mas facil
		_wTable = new int[_ofertas.size()];
		for (int i = 0; i < _ofertas.size(); i++) {
			_wTable[i] = _ofertas.get(i).getPrecioOfrecido();
		}

		// calcular p
		// devuelve en la posicion i cual es el trabajo anterior que termina mas cerca
		// del inicio de i
		// si _pTable[i] = -1, no hay trabajo que termine antes que empiece i
		// requiere las ofertas ordenadas por hora de fin
		_pTable = new int[_ofertas.size()];
		for (int i = 0; i < _ofertas.size(); i++) {
			_pTable[i] = -1;
			for (int j = 0; j < i; j++) {
				if (_ofertas.get(j).getHoraFin() <= _ofertas.get(i).getHoraInicio()) {
					_pTable[i] = j;
				}
			}
		}

		// aca empieza lo jodido
		// calculamos opt(n), que es la mayor ganancia posible
		// esto despues nos sirve para determinar cuales ofertas meter al dia
		_optTable = new int[_ofertas.size()];
		Arrays.fill(_optTable, -1);
		int maximaGanancia = opt(_ofertas.size() - 1);
		return maximaGanancia;
	}

	private static int opt(int i) {
		// caso base
		if (i == -1) {
			return 0;
		}
		// prog dinamica: vemos si fue calculado anteriormente
		if (_optTable[i] > -1) {
			return _optTable[i];
		}
		int res = Math.max(_wTable[i] + opt(_pTable[i]), opt(i - 1));
		_optTable[i] = res;
		return res;
	}
}
