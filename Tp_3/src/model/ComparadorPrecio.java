package model;

import java.util.Comparator;

public class ComparadorPrecio implements Comparator<Reserva>{

	@Override
	public int compare(Reserva o1, Reserva o2) {
		if(o1.precio() < o2.precio()) {
			return 1;
		}
		if(o1.precio() > o2.precio()) {
			return -1;
		}
		else {
			return 0;
		}
	}
}
