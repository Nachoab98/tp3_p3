
package presenter;

import java.io.IOException;

import java.util.LinkedList;

import model.RepositorioReservas;
import model.Reserva;
import view.VentanaAgregarReserva;

public class VentanaAgregarReservaPresentador {
	private VentanaAgregarReserva vista;
	private RepositorioReservas repoReservas;

	public VentanaAgregarReservaPresentador(VentanaAgregarReserva vista) {
		this.vista = vista;
		this.repoReservas = new RepositorioReservas();
	}

	public void guardarOferta(String nombre, int horaInicio, int horaFin, int montoOfertado) {
		try {
			Reserva reserva = new Reserva(horaInicio, horaFin, montoOfertado, nombre);
			repoReservas.guardarReservaEnArchivo(reserva);
		} catch (RuntimeException ex) {
			throw ex;
		} catch (IOException ex) {
			vista.mostrarError("Error al guardar la reserva en el archivo.");
		}
	}

	public LinkedList<Reserva> cargarReservas() {
		try {
			return repoReservas.cargarReservas();
		} catch (IOException e) {
			vista.mostrarError("Error al cargar las reservas.");
			return new LinkedList<>();
		}
	}
}
