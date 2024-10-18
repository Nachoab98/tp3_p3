package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Dia;
import model.Reserva;
import model.ComparadorPrecio;
import model.ComparadorPrecioXHora;

import java.util.Comparator;
import java.util.LinkedList;

public class DiaTest {

    private Dia _dia;
    private Comparator<Reserva> _comp;

    // NOTA: Generados con ChatGPT
    
    @BeforeEach
    public void setUp() {
        _dia = new Dia();
        
        // solucion temporal: cambiar el tipo de comparador por el que se quiera aca
        _comp = new ComparadorPrecioXHora();
    }

    @Test
    public void testOfertar() {
        _dia.ofertar(10, 12, 100);
        LinkedList<Reserva> ofertas = _dia.cerrarDia(_comp);
        assertEquals(1, ofertas.size(), "Debería haber una oferta");
    }

    @Test
    public void testCierreSinSuperposicion() {
        _dia.ofertar(10, 12, 100);
        _dia.ofertar(12, 14, 150);
        LinkedList<Reserva> resultado = _dia.cerrarDia(_comp);
        assertEquals(2, resultado.size(), "Debería haber 2 ofertas, ya que no se superponen.");
    }

    @Test
    public void testCierreConSuperposicion() {
        _dia.ofertar(10, 12, 100);
        _dia.ofertar(11, 13, 200); // Se superpone con la anterior
        LinkedList<Reserva> resultado = _dia.cerrarDia(_comp);
        assertEquals(1, resultado.size(), "Debería haber solo 1 oferta ya que se superponen.");
    }

    @Test
    public void testCierreConSuperposicionYComparador() {
        _dia.ofertar(10, 12, 100);
        _dia.ofertar(11, 13, 50); // Se superpone con la anterior, pero es más barata
        LinkedList<Reserva> resultado = _dia.cerrarDia(_comp);
        assertEquals(1, resultado.size(), "Debería haberse seleccionado la oferta más barata.");
        assertEquals(50, resultado.get(0).precio(), "La oferta seleccionada debería ser la más barata.");
    }

    @Test
    public void testCierreVariasOfertasSuperpuestas() {
        _dia.ofertar(10, 12, 100);
        _dia.ofertar(11, 13, 150); // Superpuesta
        _dia.ofertar(12, 14, 200); // No superpuesta
        LinkedList<Reserva> resultado = _dia.cerrarDia(_comp);
        assertEquals(2, resultado.size(), "Debería haber 2 ofertas, solo 1 se descarta por superposición.");
    }

    @Test
    public void testCierreConOfertasIguales() {
        _dia.ofertar(10, 12, 100);
        _dia.ofertar(10, 12, 100); // Misma oferta
        LinkedList<Reserva> resultado = _dia.cerrarDia(_comp);
        assertEquals(1, resultado.size(), "Debería haber solo 1 oferta, ya que son idénticas.");
    }
}
