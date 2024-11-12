
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Reserva {
	private int horaInicio;
	private int horaFin;
	private int precioOfrecido;
	private String nombre;
	private static int contadorIDs = cargarUltimoID();
	private int ID;

	public Reserva(int horaInicio, int horaFin, int precioOfrecido, String nombre) {
		if (precioOfrecido < 0) {
			throw new RuntimeException("Precio ofrecido invalido");
		}
		if (!(horaInicio >= 0 && horaFin <= 24 && horaInicio < horaFin)) {
			throw new RuntimeException("Horarios invalidos");
		}
		if (nombre == null || nombre.isEmpty()) {
			nombre = "Anonimo";
		}
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.precioOfrecido = precioOfrecido;
		this.nombre = nombre;
		this.ID = contadorIDs++;
	}

	public double precioXHora() {
		return (this.precioOfrecido / (this.horaFin - this.horaInicio));
	}

	public static boolean superponen(Reserva a, Reserva b) {
		return a.getHoraInicio() < b.getHoraFin() && b.getHoraInicio() < a.getHoraFin();
	}

	public int getHoraInicio() {
		return this.horaInicio;
	}

	public int getHoraFin() {
		return this.horaFin;
	}

	private static int cargarUltimoID() {
		File archivo = new File("Docs/ultimoID.txt");
		if (!archivo.exists()) {
			return 0;
		}
		try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
			return Integer.parseInt(br.readLine());
		} catch (IOException | NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	private static void guardarUltimoID() {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("Docs/ultimoID.txt"))) {
			bw.write(String.valueOf(contadorIDs));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void resetearContador() {
		contadorIDs = 0;
		guardarUltimoID();
	}

	public int getPrecioOfrecido() {
		return this.precioOfrecido;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getID() {
		return this.ID;
	}
}
