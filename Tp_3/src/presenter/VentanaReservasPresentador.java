package presenter;

import java.io.IOException;
import java.util.List;

import model.RepositorioReservas;
import model.Reserva;

import view.VentanaReservas;

public class VentanaReservasPresentador {
	private VentanaReservas vista;
	private RepositorioReservas repoReservas;

	public VentanaReservasPresentador(VentanaReservas vista) {
		this.vista = vista;
		this.repoReservas = new RepositorioReservas();
	}

	public void iniciar() {
		cargarReservas();
	}

	private void cargarReservas() {
		try {
			List<Reserva> reservas = repoReservas.cargarReservas();
			Reserva.resetearContador();

			// Agregar reservas a vista
			for (Reserva reserva : reservas) {
				String horario = reserva.getHoraInicio() + ":00 - " + reserva.getHoraFin() + ":00";
				vista.agregarOferta(reserva.getID(), reserva.getNombre(), horario, reserva.getPrecioOfrecido());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}