package model;

import java.util.Comparator;

public class ComparadorPrecioXHora implements Comparator<Reserva>{

	@Override
	public int compare(Reserva o1, Reserva o2) {
		if(o1.precioXHora() < o2.precioXHora()) {
			return 1;
		}
		if(o1.precioXHora() > o2.precioXHora()) {
			return -1;
		}
		else {
			return 0;
		}
	}
}