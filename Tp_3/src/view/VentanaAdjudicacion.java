
package view;

import java.awt.BorderLayout;
import java.util.LinkedList;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


import model.Reserva;
import presenter.VentanaJuegoPresentador;

public class VentanaAdjudicacion extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tablaAdjudicacion;
	private mainFrame pantallaPrincipal;
	private VentanaJuegoPresentador presentador;

	public VentanaAdjudicacion(mainFrame pantallaPrincipal) {
		this.pantallaPrincipal = pantallaPrincipal;
		this.presentador = new VentanaJuegoPresentador(this);
		iniciarAdjudicacion();
	}

	public void iniciarAdjudicacion() {
		removeAll();
		setName("Al Mejor Postor");
		setBounds(100, 100, 640, 480);
		setLayout(new BorderLayout());

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		DefaultTableModel model = new DefaultTableModel(new Object[] { "Hora", "ID", "Nombre", "Monto Ofertado" }, 0);
		tablaAdjudicacion = new JTable(model);
		tablaAdjudicacion.getColumnModel().getColumn(1).setPreferredWidth(5);
		
		for (int i = 0; i < tablaAdjudicacion.getColumnModel().getColumnCount(); i++) {
		    tablaAdjudicacion.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
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
				presentador.calcularAdjudicacion();
			}
		});
	}

	public void actualizarTablaAdjudicacion(LinkedList<Reserva> solucionOptima) {
		DefaultTableModel model = (DefaultTableModel) tablaAdjudicacion.getModel();
		model.setRowCount(0); // Limpiar la tabla

		for (Reserva reserva : solucionOptima) {
			String horario = String.format("%02d:00 - %02d:00", reserva.getHoraInicio(), reserva.getHoraFin());
			String montoOfrecido = String.format("$ " + reserva.getPrecioOfrecido());
			model.addRow(new Object[] { horario, reserva.getID(), reserva.getNombre(), montoOfrecido });
		}
	}
}
