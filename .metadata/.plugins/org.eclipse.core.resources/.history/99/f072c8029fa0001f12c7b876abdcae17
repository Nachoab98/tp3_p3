package model;

import java.util.Comparator;

public class ComparadorOPT implements Comparator<Reserva> {

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
}