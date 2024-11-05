package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ComparadorPrecioXHora;
import model.Dia;
import model.OPT;
import model.Reserva;

class RandomTest {

	private Dia _dia;
	private int _cantTurnos;
	private Comparator<Reserva> _comp;

	@BeforeEach
	public void setUp() {
		_dia = new Dia();
		_cantTurnos = 1000;

		// solucion temporal: cambiar el tipo de comparador por el que se quiera aca
		_comp = new ComparadorPrecioXHora();

		for (int i = 0; i < _cantTurnos; i++) {
			this.reservarTurnoRandom();
		}
	}

	@Test
	void test() {
		boolean rompio = false;
		for (int i = 0; !rompio && i < 10000; i++) {

			_dia = new Dia();
			for (int t = 0; t < _cantTurnos; t++) {
				this.reservarTurnoRandom();
			}

			LinkedList<Reserva> subOptim = this._dia.cerrarDia(_comp);
			LinkedList<Reserva> optim  = this._dia.cerrarDiaOptimo();

			if (calcularGanancia(subOptim) > calcularGanancia(optim)) {
				rompio = true;
			}
		}
		System.out.println("finish");
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
		Integer promedioEsperado = 10000;
		Integer desvioEstandar = 2000;

		do {
			precio = (int) rand.nextGaussian(promedioEsperado, desvioEstandar);
		} while (precio < 0);

		precio = precio * (fin - inicio);

		this._dia.ofertar(inicio, fin, precio, "Random");
	}

	private int calcularGanancia(LinkedList<Reserva> reservas) {
		int res = 0;
		for (Reserva r : reservas) {
			res += r.getPrecioOfrecido();
		}
		return res;
	}
}
