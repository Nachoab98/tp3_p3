package presenter;

import java.util.LinkedList;
import java.util.List;

import model.OPT;
import model.Reserva;
import view.VentanaAdjudicacion;

public class VentanaJuegoPresentador {
    private VentanaAdjudicacion vista;
    private VentanaAgregarReservaPresentador agregarReservaPresentador;

    public VentanaJuegoPresentador(VentanaAdjudicacion vista) {
        this.vista = vista;
        this.agregarReservaPresentador = new VentanaAgregarReservaPresentador(null);
    }

    public void calcularAdjudicacion() {
    	Reserva.resetearContador();
        List<Reserva> reservas = agregarReservaPresentador.cargarReservas();
        LinkedList<Reserva> solucionOptima = OPT.solucionOptima(new LinkedList<>(reservas));
        vista.actualizarTablaAdjudicacion(solucionOptima);
    }
}