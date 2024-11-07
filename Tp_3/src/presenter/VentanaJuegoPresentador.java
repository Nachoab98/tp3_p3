package presenter;

import java.util.LinkedList;
import java.util.List;

import model.OPT;
import model.Reserva;
import view.VentanaJuego;

public class VentanaJuegoPresentador {
    private VentanaJuego vista;
    private VentanaAgregarReservaPresentador agregarReservaPresentador;

    public VentanaJuegoPresentador(VentanaJuego vista) {
        this.vista = vista;
        this.agregarReservaPresentador = new VentanaAgregarReservaPresentador(null);
    }

    public void calcularAdjudicacion() {
        List<Reserva> reservas = agregarReservaPresentador.cargarReservas();
        LinkedList<Reserva> solucionOptima = OPT.solucionOptima(new LinkedList<>(reservas));
        vista.actualizarTablaAdjudicacion(solucionOptima);
    }
}