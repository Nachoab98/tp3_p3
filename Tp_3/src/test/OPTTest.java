package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.OPT;
import model.Reserva;
import model.Solver;

import java.util.LinkedList;

class OPTTest {

	private LinkedList<Reserva> reservas;

	@BeforeEach
	void setUp() {
		reservas = new LinkedList<>();
	}

	@Test
	void test_sinOfertas() {
		// Caso borde: Sin ofertas
		LinkedList<Reserva> result = OPT.solucionOptima(reservas);
		assertTrue(result.isEmpty(), "Se espera un arreglo vacio si no hay ofertas");
	}

	@Test
	void test_unaSolaOferta() {
		// Caso borde: Una sola oferta
		reservas.add(new Reserva(0, 3, 100, "Anon"));
		LinkedList<Reserva> result = OPT.solucionOptima(reservas);
		assertEquals(1, result.size(), "Se espera una sola reserva en el resultado");
		assertEquals(100, Solver.precioTotal(result), "Se espera que la ganancia total coincida con la unica oferta");
	}

	@Test
	void test_ofertasTodasCompatibles() {
		// Caso borde: Reservas todas compatibles entre si
		reservas.add(new Reserva(0, 2, 50, "Anon"));
		reservas.add(new Reserva(3, 5, 70, "Anon"));
		reservas.add(new Reserva(6, 8, 90, "Anon"));

		LinkedList<Reserva> result = OPT.solucionOptima(reservas);
		assertEquals(3, result.size(), "Se esperan todas las ofertas en el resultado");
		assertEquals(210, Solver.precioTotal(result), "Se espera que la suma sea 210");
	}

	@Test
	void test_incompatiblesConMayorGanancia() {
		// Caso borde: Ofertas incompatibles donde una sola da la mejor ganancia
		reservas.add(new Reserva(0, 4, 100, "Anon")); // La mejor. Deberia elegirse esta
		reservas.add(new Reserva(1, 3, 70, "Anon"));
		reservas.add(new Reserva(2, 5, 80, "Anon"));

		LinkedList<Reserva> result = OPT.solucionOptima(reservas);
		assertEquals(1, result.size(), "Solo se espera una oferta en el resultado");
		assertEquals(100, Solver.precioTotal(result), "Se espera la reserva con mejor precio (100)");
	}

	@Test
	void test_incompatiblesConCombinacion() {
		// Caso borde: Incompatibles donde cierta combinacion maximiza ganancias
		reservas.add(new Reserva(0, 2, 30, "Anon"));
		reservas.add(new Reserva(3, 5, 70, "Anon"));
		reservas.add(new Reserva(1, 6, 50, "Anon")); // No deberia seleccionarse
		reservas.add(new Reserva(6, 8, 60, "Anon"));

		LinkedList<Reserva> result = OPT.solucionOptima(reservas);
		assertEquals(3, result.size(), "Se esperan 3 reservas en la solucion");
		assertEquals(160, Solver.precioTotal(result), "La ganancia total deberia ser 160");
	}

	@Test
	void test_ofertasConMismoHorario() {
		// Caso borde: Multiples ofertas con misma hora de inicio y fin pero distintos
		// precios
		reservas.add(new Reserva(0, 3, 40, "Anon"));
		reservas.add(new Reserva(0, 3, 60, "Anon")); // Mejor precio, deberia elegirse
		reservas.add(new Reserva(0, 3, 50, "Anon"));

		LinkedList<Reserva> result = OPT.solucionOptima(reservas);
		assertEquals(1, result.size(), "Solo se espera una reserva en la solucion");
		assertEquals(60, Solver.precioTotal(result), "Se espera la oferta con mejor ganancia");
	}

	@Test
	void test_ofertasSecuencialesEIncompatibles() {
		// Caso Borde: Mezcla de ofertas secuenciales y ofertas incompatibles
		reservas.add(new Reserva(0, 3, 50, "Anon"));
		reservas.add(new Reserva(2, 6, 60, "Anon")); // Se superpone con la primera
		reservas.add(new Reserva(6, 10, 70, "Anon")); // Compatible con ambas

		LinkedList<Reserva> result = OPT.solucionOptima(reservas);
		assertEquals(2, result.size(), "Se eperan dos reservas en la solucion");
		assertEquals(130, Solver.precioTotal(result), "El precio total deberia ser 130");
	}

	@Test
	void test_mejorQuePrecioXHora() {
		// Rendimiento: Un algoritmo goloso elegiria la primer y la tercera reserva
		reservas.add(new Reserva(1, 2, 50, "Anon")); // 50/1 = 50
		reservas.add(new Reserva(1, 4, 60, "Anon")); // 60/3 = 20 (menor que 50, pero mejor ganancia en total)
		reservas.add(new Reserva(5, 7, 120, "Anon")); // 120/2 = 60 

		LinkedList<Reserva> result = OPT.solucionOptima(reservas);

		// La solucion optima elegiria la segunda y la tercera reserva
		assertEquals(2, result.size(), "Se espera que la solucion tenga 2 reservas");
		assertEquals(180, Solver.precioTotal(result), "La ganancia total deberia ser 180");
	}

	@Test
	void test_mejorQuePrecioTotalReserva() {
		// Rendimiento: Un algoritmo goloso elegiria la tercer reserva
		reservas.add(new Reserva(0, 6, 60, "Anon"));
		reservas.add(new Reserva(6, 12, 50, "Anon"));
		reservas.add(new Reserva(0, 12, 90, "Anon")); // incompatible con las otras dos y su precio es menor a la suma
														// de ellos

		LinkedList<Reserva> result = OPT.solucionOptima(reservas);

		// La solucion optima elegiria las primeras dos reservas pues suman mas que la
		// tercera
		assertEquals(2, result.size(), "Se esperan 2 reservas en la solucion");
		assertEquals(110, Solver.precioTotal(result), "La ganancia optima deberia ser 110");
	}

}
