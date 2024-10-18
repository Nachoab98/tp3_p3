package model;

import java.util.LinkedList;

import model.Reserva;

public class Dia {

	private LinkedList<Reserva> _ofertas;
	
	public Dia() {
		this._ofertas = new LinkedList<Reserva>();
	}
	
	public void ofertar(int horaInicio, int horaFin, int precio) {
		this._ofertas.add(new Reserva(horaInicio,horaFin,precio));
	}
	
}
