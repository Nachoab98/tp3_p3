package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.ComparadorPrecioXHora;
import model.OPT;
import model.Reserva;
import model.Solver;

class RandomTest {

	private LinkedList<Reserva> _dia;
	private int _cantTurnos;

	@BeforeEach
	public void setUp() {
		_dia = new LinkedList<Reserva>();
		_cantTurnos = 1000;

		for (int i = 0; i < _cantTurnos; i++) {
			this.reservarTurnoRandom();
		}
	}

	@Test
	void test() {
		boolean rompio = false;
		for (int i = 0; !rompio && i < 10000; i++) {

			_dia = new LinkedList<Reserva>();
			for (int t = 0; t < _cantTurnos; t++) {
				this.reservarTurnoRandom();
			}

			LinkedList<Reserva> subOptim = Solver.solucionPrecioXHora(_dia);
			LinkedList<Reserva> optim = Solver.solucionOPT(_dia);

			if (Solver.precioTotal(subOptim) > Solver.precioTotal(optim)) {
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
			int a = rand.nextInt(25);
			int b = rand.nextInt(25);
			inicio = Math.min(a, b);
			fin = Math.max(a, b);
		} while (inicio == fin);

		// generamos precio
		int horas = fin - inicio;
		double promedioEsperado = 10000 * horas;
		double desvioEstandar = 2000 * Math.sqrt(horas);

		precio = Math.max(1, (int) rand.nextGaussian(promedioEsperado, desvioEstandar));

		_dia.add(new Reserva(inicio, fin, precio, "Random"));
	}

}
