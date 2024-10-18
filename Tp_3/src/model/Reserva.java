package model;

public class Reserva{

	private int _horaInicio;
	private int _horaFin;
	private int _precioOfrecido;
	
	public Reserva(int horaInicio, int horaFin, int precioOfrecido) {
		if(!(horaInicio >= 0 && horaFin <= 24 && horaInicio < horaFin)) {
			throw new RuntimeException("Horarios invalidos");
		}
		if(precioOfrecido < 0) {
			throw new RuntimeException("Precio ofrecido invalido");
		}
		this._horaInicio = horaInicio;
		this._horaFin = horaFin;
		this._precioOfrecido = precioOfrecido;
	}
	
	public double precioXHora() {
		return (this._precioOfrecido / (this._horaFin - this._horaInicio) );
	}
	
	public static boolean superponen(Reserva a, Reserva b) {
		return a.inicio() < b.fin() && b.inicio() < a.fin();
	}
	
	// GETTERS
	public int inicio() {
		return this._horaInicio;
	}
	
	public int fin() {
		return this._horaFin;
	}
	
	public int precio() {
		return this._precioOfrecido;
	}
	
	
	
}
