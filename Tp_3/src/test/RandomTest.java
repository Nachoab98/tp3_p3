package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ComparadorPrecioXHora;
import model.Dia;
import model.Reserva;

class RandomTest {

	private Dia _dia;
	private int _cantTurnos;
	private Comparator<Reserva> _comp;

	@BeforeEach
	public void setUp() {
		_dia = new Dia();
		_cantTurnos = 100;

		// solucion temporal: cambiar el tipo de comparador por el que se quiera aca
		_comp = new ComparadorPrecioXHora();

		for (int i = 0; i < _cantTurnos; i++) {
			this.reservarTurnoRandom();
		}
	}

	@Test
	void test() {
		this._dia.cerrarDia(_comp);
	}

	private void reservarTurnoRandom() {

		// generamos horas
		Random rand = new Random();
		int inicio;
		int fin;
		int precio;

		do {
			inicio = rand.nextInt(25);
			fin = rand.nextInt(25);
		} while (inicio == fin);

		if (inicio > fin) {
			int temp = inicio;
			inicio = fin;
			fin = temp;
		}

		// generamos precio
		int promedioEsperado = 10000;
		int desvioEstandar = 2000;

		do {
			precio = (int) rand.nextGaussian(promedioEsperado, desvioEstandar);
		} while (precio < 0);

		precio = precio * (fin - inicio);

		this._dia.ofertar(inicio, fin, precio);
	}
}
