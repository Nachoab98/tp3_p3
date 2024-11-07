package presenter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Reserva;
import view.VentanaReservas;

public class VentanaReservasPresentador {
	private VentanaReservas vista;
	
	public VentanaReservasPresentador(VentanaReservas vista)
	{
		this.vista = vista;
	}
	
	public void iniciar() {
        cargarReservas(); 
    }
	
	private void cargarReservas()
	{
		List<Reserva> reservas = new ArrayList<>();
		Reserva.resetearContador();
		String rutaArchivo = "Docs/reservas.json";
		Gson gson = new GsonBuilder()
		        .registerTypeAdapter(Reserva.class, new ReservaAdapter()) 
		        .create();
		
		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Reserva reserva = gson.fromJson(linea, Reserva.class);
                if (reserva != null) {
                    reservas.add(reserva);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Agregar reservas a vista
        for (Reserva reserva : reservas) {
            String horario = reserva.getHoraInicio() + ":00 - " + reserva.getHoraFin() + ":00"; 
            vista.agregarOferta(reserva.getID(), reserva.getNombre(), horario, reserva.getPrecioOfrecido());
        }
    }

	}