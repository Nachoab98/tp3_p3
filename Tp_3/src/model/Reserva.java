
package model;

public class Reserva {
	private int horaInicio;
	private int horaFin;
	private int precioOfrecido;
	private String nombre;

	public Reserva(int horaInicio, int horaFin, int precioOfrecido, String nombre) {
		if (!(horaInicio >= 0 && horaFin <= 24 && horaInicio < horaFin)) {
			throw new RuntimeException("Horarios invalidos");
		}
		if (precioOfrecido < 0) {
			throw new RuntimeException("Precio ofrecido invalido");
		}
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.precioOfrecido = precioOfrecido;
		this.nombre = nombre;
	}

	public double precioXHora() {
		return (this.precioOfrecido / (this.horaFin - this.horaInicio));
	}

	public static boolean superponen(Reserva a, Reserva b) {
		return a.getHoraInicio() < b.getHoraFin() && b.getHoraInicio() < a.getHoraFin();
	}

	// GETTERS
	public int getHoraInicio() {
		return this.horaInicio;
	}

	public int getHoraFin() {
		return this.horaFin;
	}

	public int getPrecioOfrecido() {
		return this.precioOfrecido;
	}

	public String getNombre() {
		return this.nombre;
	}

}
