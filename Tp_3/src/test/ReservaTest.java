package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Reserva;

public class ReservaTest {

	@BeforeEach
	public void setUp() {
		Reserva.resetearContador();
	}

	@AfterEach
	public void cleanUp() {
		File archivo = new File("Docs/ultimoID.txt");
		if (archivo.exists()) {
			archivo.delete();
		}
	}

	@Test
	public void testCrearReservaValida() {
		Reserva reserva = new Reserva(2, 5, 5000, "Manolo");
		assertEquals(02, reserva.getHoraInicio());
		assertEquals(05, reserva.getHoraFin());
		assertEquals(5000, reserva.getPrecioOfrecido());
		assertEquals("Manolo", reserva.getNombre());
		assertEquals(0, reserva.getID());
	}

	@Test
	public void testPrecioXHora() {
		Reserva reserva = new Reserva(2, 5, 6000, "Ignacio");
		assertEquals(2000.00, reserva.precioXHora());
	}

	@Test
	public void TestIDIncrementaYResetea() {
		Reserva reserva1 = new Reserva(6, 9, 7000, "Matias");
		Reserva reserva2 = new Reserva(10, 12, 12000, "Rolando");
		assertEquals(0, reserva1.getID());
		assertEquals(1, reserva2.getID());

		Reserva.resetearContador();
		Reserva reserva3 = new Reserva(10, 11, 4000, "Ayrton");
		assertEquals(0, reserva3.getID());
	}

	@Test
	public void TestSeSuperponen() {
		Reserva reserva1 = new Reserva(6, 9, 4000, "Roberto");
		Reserva reserva2 = new Reserva(8, 12, 15000, "Joaquin");
		assertTrue(Reserva.superponen(reserva1, reserva2));

		Reserva reserva3 = new Reserva(12, 15, 15000, "Adriana");
		assertFalse(Reserva.superponen(reserva2, reserva3));

	}

	@Test
	public void TestReservaAnonimo() {
		Reserva reserva1 = new Reserva(10, 12, 300, null);
		Reserva reserva2 = new Reserva(14, 15, 200, "");

		assertEquals("Anonimo", reserva1.getNombre());
		assertEquals("Anonimo", reserva2.getNombre());
	}

	@Test
	public void testHorarioInvalidoExcepcion() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			new Reserva(20, 17, 100, "Analia");
		});
		assertEquals("Horarios invalidos", exception.getMessage());

		Exception exception2 = assertThrows(RuntimeException.class, () -> {
			new Reserva(-1, 26, 100, "Analia");
		});
		assertEquals("Horarios invalidos", exception2.getMessage());

	}

}
