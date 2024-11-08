package model;

import java.util.Comparator;
import java.util.LinkedList;
import model.ComparadorPrecio;
import model.ComparadorPrecioXHora;

public class Solver {

	private static LinkedList<Reserva> _ofertas;
	
	public static LinkedList<Reserva> solucionPrecioXHora(LinkedList<Reserva> reservas){
		_ofertas = new LinkedList<Reserva>(reservas);
		return solucionGolosaGenerica(new ComparadorPrecioXHora());
	}
	
	public static LinkedList<Reserva> solucionPrecioTotal(LinkedList<Reserva> reservas){
		_ofertas = new LinkedList<Reserva>(reservas);
		return solucionGolosaGenerica(new ComparadorPrecio());
	}
	
	public static LinkedList<Reserva> solucionOPT(LinkedList<Reserva> reservas){
		return OPT.solucionOptima(reservas);
	}
	
	public static int precioTotal(LinkedList<Reserva> reservas) {
		int res = 0;
		for(Reserva r : reservas) {
			res += r.getPrecioOfrecido();
		}
		return res;
	}
	
	private static LinkedList<Reserva> solucionGolosaGenerica(Comparator<Reserva> comparador){
		LinkedList<Reserva> res = new LinkedList<Reserva>();
		_ofertas.sort(comparador);

		for (Reserva oferta : _ofertas) {
			boolean ofertaSuperpuesta = false;
			for (Reserva r : res) {
				if (Reserva.superponen(oferta, r)) {
					ofertaSuperpuesta = true;
					break;
				}
			}
			if (!ofertaSuperpuesta) {
				res.add(oferta);
			}
		}
		res.sort(new Comparator<Reserva>() {
			@Override
			public int compare(Reserva o1, Reserva o2) {
				return Integer.compare(o1.getHoraInicio(), o2.getHoraInicio());
			}
		});

		return res;
	}
	
}
