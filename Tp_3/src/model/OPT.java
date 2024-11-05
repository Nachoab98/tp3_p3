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

	public static LinkedList<Reserva> solucionOptima(LinkedList<Reserva> ofertas) {

		ordenarOfertas(ofertas);
		calcular_wTable();
		calcular_pTable();
		calcular_optTable();
		
		return backtrackSolucion();
	}

	private static void ordenarOfertas(LinkedList<Reserva> ofertas) {
		
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
		
	}
	
	// calcular w
	// simplemente deja el precio en un array para acceso mas facil
	private static void calcular_wTable() {
		_wTable = new int[_ofertas.size()];
		for (int i = 0; i < _ofertas.size(); i++) {
			_wTable[i] = _ofertas.get(i).getPrecioOfrecido();
		}
	}
	
	// calcular p
	// devuelve en la posicion i cual es el trabajo anterior que termina mas cerca
	// del inicio de i
	// si _pTable[i] = -1, no hay trabajo que termine antes que empiece i
	// requiere las ofertas ordenadas por hora de fin
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
	
	// calculamos opt. devuelvue en la posicion i 
	// cual es la mayor ganancia posible hasta la posicion i
	private static void calcular_optTable() {
		_optTable = new int[_ofertas.size()];
		Arrays.fill(_optTable, -1);
		opt(_ofertas.size() - 1);
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
		// aca sucede la magia
		// evaluamos que pasa si incluimos o excluimos a i de la solucion
		int incluye_i = _wTable[i] + opt(_pTable[i]);
		int excluye_i = opt(i - 1);
		
		// nos quedamos con la mejor
		int res = Math.max(incluye_i, excluye_i);
		
		// prog dinamica: guardamos el resultado para futuros usos
		_optTable[i] = res;
		
		return res;
	}
	
	private static LinkedList<Reserva> backtrackSolucion(){
		LinkedList<Reserva> res = new LinkedList<Reserva>();
		
		int j = _ofertas.size() - 1;
		while(j >= 0) {
			int incluye_j = _wTable[j] + ( _pTable[j] != -1 ? _optTable[_pTable[j]] : 0 );
			if(_optTable[j] == incluye_j) {
				res.addFirst(_ofertas.get(j));
				j = _pTable[j];
			}else {
				j -= 1;
			}
		}
		return res;
	}
}