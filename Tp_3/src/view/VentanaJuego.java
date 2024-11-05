
package view;

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.OPT;
import model.Reserva;
import presenter.VentanaAgregarReservaPresentador;

public class VentanaJuego extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable tablaAdjudicacion;
    private mainFrame pantallaPrincipal;
    private VentanaAgregarReservaPresentador presentador;

    public VentanaJuego(mainFrame pantallaPrincipal) {
        this.pantallaPrincipal = pantallaPrincipal;
        this.presentador = new VentanaAgregarReservaPresentador(null);
        iniciarJuego();
    }

    public void iniciarJuego() {
        removeAll();
        setName("Al Mejor Postor");
        setBounds(100, 100, 640, 480);
        setLayout(new BorderLayout());

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableModel model = new DefaultTableModel(new Object[] { "Hora", "ID", "Nombre", "Monto Ofertado" }, 0);
        tablaAdjudicacion = new JTable(model);
        tablaAdjudicacion.getColumnModel().getColumn(1).setPreferredWidth(5);
        tablaAdjudicacion.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        for (int i = 0; i < 24; i++) {
            String horaInicio = String.format("%02d:00", i);
            String horaFin = String.format("%02d:00", (i + 1) % 24);
            model.addRow(new Object[] { horaInicio + " - " + horaFin, "", "", "" });
        }

        JScrollPane scrollPane = new JScrollPane(tablaAdjudicacion);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelSuperior = new JPanel();
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelInferior = new JPanel();
        add(panelInferior, BorderLayout.SOUTH);

        JButton botonCalcular = new JButton("Calcular Adjudicacion");
        panelInferior.add(botonCalcular);

        JPanel panel = new JPanel();
        panelInferior.add(panel);

        JButton botonMenu = new JButton("Volver al Menu");
        botonMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pantallaPrincipal.cambiarVentana("Ventana Menu");
            }
        });
        panelInferior.add(botonMenu);

        JPanel panelIzquierda = new JPanel();
        add(panelIzquierda, BorderLayout.WEST);

        JPanel panelDerecha = new JPanel();
        add(panelDerecha, BorderLayout.EAST);

        botonCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularAdjudicacion();
            }
        });
    }

    private void calcularAdjudicacion() {
        List<Reserva> reservas = presentador.cargarReservas();
        LinkedList<Reserva> solucionOptima = OPT.solucionOptima(new LinkedList<>(reservas));

        DefaultTableModel model = (DefaultTableModel) tablaAdjudicacion.getModel();
        model.setRowCount(0); // Limpiar la tabla

        for (Reserva reserva : solucionOptima) {
            String horario = String.format("%02d:00 - %02d:00", reserva.getHoraInicio(), reserva.getHoraFin());
            model.addRow(new Object[] { horario, reserva.getID(), reserva.getNombre(), reserva.getPrecioOfrecido() });
        }
    }
}
