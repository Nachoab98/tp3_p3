package model;

import java.util.LinkedList;
import java.util.Arrays;
import java.util.Comparator;

import model.Reserva;

public class Dia {

	private LinkedList<Reserva> _ofertas;

	public Dia() {
		this._ofertas = new LinkedList<Reserva>();
	}

	public void ofertar(int horaInicio, int horaFin, int precio, String nombre) {
		this._ofertas.add(new Reserva(horaInicio, horaFin, precio, nombre));
	}

	public LinkedList<Reserva> cerrarDia(Comparator<Reserva> comparador) {

		// creamos una copia ordenada de las ofertas
		LinkedList<Reserva> ofertasOrdenadas = new LinkedList<Reserva>(this._ofertas);
		LinkedList<Reserva> res = new LinkedList<Reserva>();

		ofertasOrdenadas.sort(comparador);

		for (Reserva oferta : ofertasOrdenadas) {
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
				return Integer.compare(o1.inicio(), o2.inicio());
			}
		});

		return res;
	}
	
	public int solucionOptima() {
		return OPT.maxGanancia(_ofertas);
	}
}
