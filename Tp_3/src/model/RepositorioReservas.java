package model;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RepositorioReservas {
	private static final String ARCHIVO_PATH = "Docs/reservas.json";
	private Gson gson;

	public RepositorioReservas() {
		this.gson = new GsonBuilder().registerTypeAdapter(Reserva.class, new ReservaAdapter()).create();
	}

	public void guardarReservaEnArchivo(Reserva reserva) throws IOException {
		String jsonReserva = gson.toJson(reserva);
		try (FileOutputStream archivo = new FileOutputStream(ARCHIVO_PATH, true);
				OutputStreamWriter lineaEscrita = new OutputStreamWriter(archivo)) {
			lineaEscrita.write(jsonReserva);
			lineaEscrita.write(System.lineSeparator());
			lineaEscrita.flush();
		}
	}

	public LinkedList<Reserva> cargarReservas() throws IOException {
		LinkedList<Reserva> reservas = new LinkedList<Reserva>();
		try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_PATH))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				Reserva reserva = gson.fromJson(linea, Reserva.class);
				if (reserva != null) {
					reservas.add(reserva);
				}
			}
			return reservas;
		}

	}
}
