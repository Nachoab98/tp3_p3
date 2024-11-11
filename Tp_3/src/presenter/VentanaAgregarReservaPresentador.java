
package presenter;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
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
            throw ex;
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

    public LinkedList<Reserva> cargarReservas() {
    	LinkedList<Reserva> reservas = new LinkedList<Reserva>();
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
        return reservas;
    }
}
