package presenter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import model.Reserva;

import java.io.IOException;

public class ReservaAdapter extends TypeAdapter<Reserva> {
	@Override
	public void write(JsonWriter out, Reserva reserva) throws IOException {
		out.beginObject();
		out.name("horaInicio").value(reserva.getHoraInicio());
		out.name("horaFin").value(reserva.getHoraFin());
		out.name("precioOfrecido").value(reserva.getPrecioOfrecido());
		out.name("nombre").value(reserva.getNombre());
		out.name("ID").value(reserva.getID());
		out.endObject();
	}

	@Override
	public Reserva read(JsonReader in) throws IOException {
		in.beginObject();
		int horaInicio = 0, horaFin = 0, precioOfrecido = 0;
		String nombre = "";
		int id = 0;
		
		while (in.hasNext()) {
			switch (in.nextName()) {
			case "horaInicio":
				horaInicio = in.nextInt();
				break;
			case "horaFin":
				horaFin = in.nextInt();
				break;
			case "precioOfrecido":
				precioOfrecido = in.nextInt();
				break;
			case "nombre":
				nombre = in.nextString();
				break;
			case "ID": // Agregado para leer el ID
                id = in.nextInt();
                break;
			}
		}
		in.endObject();
		return new Reserva(horaInicio, horaFin, precioOfrecido, nombre);
	}
}