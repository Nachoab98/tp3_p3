package presenter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Reserva;
import view.VentanaAgregarReserva;

public class VentanaAgregarReservaPresentador {
	private VentanaAgregarReserva vista;

	public VentanaAgregarReservaPresentador(VentanaAgregarReserva vista) {
		this.vista = vista;
	}

	public void guardarOferta(String nombre, int horaInicio, int horaFin, int montoOfertado) {
		try {
			Reserva reserva = new Reserva(horaInicio, horaFin, montoOfertado, nombre);
			guardarTextoEnArchivo(reserva);
		} catch (RuntimeException ex) {
			ex.printStackTrace();
		}

	}

	private void guardarTextoEnArchivo(Reserva reserva) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Reserva.class, new ReservaAdapter()).create();
		String jsonReserva = gson.toJson(reserva);
		System.out.println("JSON generado: " + jsonReserva);
		try (FileOutputStream archivo = new FileOutputStream("Docs/reservas.json", true);
				OutputStreamWriter lineaEscrita = new OutputStreamWriter(archivo)) {

			lineaEscrita.write(jsonReserva);
			lineaEscrita.write(System.lineSeparator());
			lineaEscrita.flush();
			System.out.println("Reserva guardada en archivo");

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error al guardar la reserva en el archivo");
		}
	}

}

