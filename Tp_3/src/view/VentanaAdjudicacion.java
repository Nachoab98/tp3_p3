
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
import model.Solver;
import presenter.VentanaJuegoPresentador;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;

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
		FlowLayout fl_panelInferior = (FlowLayout) panelInferior.getLayout();
		add(panelInferior, BorderLayout.SOUTH);
		
		JPanel panelInferiorIzquierda = new JPanel();
		panelInferior.add(panelInferiorIzquierda);
		panelInferiorIzquierda.setLayout(new BoxLayout(panelInferiorIzquierda, BoxLayout.Y_AXIS));
		
		JLabel label_heuristica = new JLabel("Heuristica:");
		label_heuristica.setHorizontalAlignment(SwingConstants.CENTER);
		panelInferiorIzquierda.add(label_heuristica);
		
		JComboBox<String> cmbx_heuristica = new JComboBox<String>();
		cmbx_heuristica.addItem("Según precio total de reserva");
		cmbx_heuristica.addItem("Según relación precio/Hora");
		cmbx_heuristica.addItem("OPT (Mejor selección posible de ofertas)");
		
		panelInferiorIzquierda.add(cmbx_heuristica);
		
		JPanel panel_1_1 = new JPanel();
		panelInferiorIzquierda.add(panel_1_1);
		
		JButton botonCalcular_1 = new JButton("Calcular Adjudicacion");
		panelInferiorIzquierda.add(botonCalcular_1);
		
		JPanel panel_1_2 = new JPanel();
		panelInferiorIzquierda.add(panel_1_2);
		
		JLabel label_gananciaTotal = new JLabel("Ganancia Total: ");
		label_gananciaTotal.setHorizontalAlignment(SwingConstants.CENTER);
		panelInferiorIzquierda.add(label_gananciaTotal);
		
		JPanel panel_1_3 = new JPanel();
		panelInferiorIzquierda.add(panel_1_3);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setHgap(50);
		panelInferior.add(panel_1);

		JPanel panelInferiorDerecha = new JPanel();
		FlowLayout fl_panelInferiorDerecha = (FlowLayout) panelInferiorDerecha.getLayout();
		fl_panelInferiorDerecha.setAlignment(FlowLayout.RIGHT);
		panelInferior.add(panelInferiorDerecha);
		
		JButton botonMenu = new JButton("Volver al Menu");
		botonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaPrincipal.cambiarVentana("Ventana Menu");
			}
		});
		panelInferiorDerecha.add(botonMenu);

		JPanel panelIzquierda = new JPanel();
		add(panelIzquierda, BorderLayout.WEST);

		JPanel panelDerecha = new JPanel();
		add(panelDerecha, BorderLayout.EAST);
		
		botonCalcular_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int heuristica = cmbx_heuristica.getSelectedIndex();
				int precioTotal = 0;
				switch(heuristica) {
				case(0):
					precioTotal = presentador.calcularAdjudicacion("precioTotal");
					break;
				case(1):
					precioTotal = presentador.calcularAdjudicacion("precioXHora");
					break;
				case(2):
					precioTotal = presentador.calcularAdjudicacion("opt");
					break;
				}
				label_gananciaTotal.setText("Ganancia Total: $" + precioTotal);
			}
		});
	}

	public void actualizarTablaAdjudicacion(LinkedList<Reserva> reservas) {
		DefaultTableModel model = (DefaultTableModel) tablaAdjudicacion.getModel();
		model.setRowCount(0); // Limpiar la tabla
		
		for (Reserva reserva : reservas) {
			String horario = String.format("%02d:00 - %02d:00", reserva.getHoraInicio(), reserva.getHoraFin());
			String montoOfrecido = String.format("$ " + reserva.getPrecioOfrecido());
			model.addRow(new Object[] { horario, reserva.getID(), reserva.getNombre(), montoOfrecido });
		}
		
	}
}
