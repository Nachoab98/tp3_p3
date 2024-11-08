package presenter;

import java.util.LinkedList;
import java.util.List;

import model.OPT;
import model.Reserva;
import model.Solver;
import view.VentanaAdjudicacion;

public class VentanaJuegoPresentador {
    private VentanaAdjudicacion vista;
    private VentanaAgregarReservaPresentador agregarReservaPresentador;

    public VentanaJuegoPresentador(VentanaAdjudicacion vista) {
        this.vista = vista;
        this.agregarReservaPresentador = new VentanaAgregarReservaPresentador(null);
    }

    public int calcularAdjudicacion(String heuristica) {
    	Reserva.resetearContador();
        LinkedList<Reserva> reservas = agregarReservaPresentador.cargarReservas();
        LinkedList<Reserva> solucion = null;
        switch(heuristica) {
        	case("precioTotal"):
        	solucion = Solver.solucionPrecioTotal(reservas);
        	break;
        case("precioXHora"):
        	solucion = Solver.solucionPrecioXHora(reservas);
    		break;
        case("opt"):
        	solucion = Solver.solucionOPT(reservas);
    		break;
        }
        int res = Solver.precioTotal(solucion);
        vista.actualizarTablaAdjudicacion(solucion);
        return res;
    }
}
