
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import java.awt.Font;

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
		panelInferior.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panelInferiorIzquierda = new JPanel();
		panelInferior.add(panelInferiorIzquierda);
		panelInferiorIzquierda.setLayout(new BorderLayout(0, 0));

		JLabel label_heuristica = new JLabel("Heuristica:");
		label_heuristica.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_heuristica.setHorizontalAlignment(SwingConstants.CENTER);
		panelInferiorIzquierda.add(label_heuristica, BorderLayout.NORTH);

		JPanel panelOeste = new JPanel();
		panelInferiorIzquierda.add(panelOeste, BorderLayout.WEST);
		panelOeste.setPreferredSize(new Dimension(45, 0));

		JPanel panelEste = new JPanel();
		panelInferiorIzquierda.add(panelEste, BorderLayout.EAST);
		panelEste.setPreferredSize(new Dimension(45, 0));

		JLabel label_gananciaTotal = new JLabel("Ganancia Total: ");
		label_gananciaTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_gananciaTotal.setHorizontalAlignment(SwingConstants.CENTER);
		panelInferiorIzquierda.add(label_gananciaTotal, BorderLayout.SOUTH);

		JPanel PanelComboBoton = new JPanel();
		panelInferiorIzquierda.add(PanelComboBoton, BorderLayout.CENTER);
		PanelComboBoton.setLayout(new GridLayout(3, 0, 0, 0));

		JComboBox<String> cmbx_heuristica = new JComboBox<String>();
		PanelComboBoton.add(cmbx_heuristica);
		cmbx_heuristica.addItem("Según precio total de reserva");
		cmbx_heuristica.addItem("Según relación precio/Hora");
		cmbx_heuristica.addItem("OPT (Mejor selección posible de ofertas)");

		JPanel panelBlanco = new JPanel();
		PanelComboBoton.add(panelBlanco);

		JButton botonCalcular_1 = new JButton("Calcular Adjudicacion");
		PanelComboBoton.add(botonCalcular_1);
		botonCalcular_1.setFont(new Font("Tahoma", Font.PLAIN, 11));

		botonCalcular_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int heuristica = cmbx_heuristica.getSelectedIndex();
				int precioTotal = 0;
				switch (heuristica) {
				case (0):
					precioTotal = presentador.calcularAdjudicacion("precioTotal");
					break;
				case (1):
					precioTotal = presentador.calcularAdjudicacion("precioXHora");
					break;
				case (2):
					precioTotal = presentador.calcularAdjudicacion("opt");
					break;
				}
				label_gananciaTotal.setText("Ganancia Total: $" + precioTotal);
			}
		});

		JPanel panelInferiorDerecha = new JPanel();
		panelInferior.add(panelInferiorDerecha);

		JButton botonMenu = new JButton("Volver al Menu");
		botonMenu.setFont(new Font("Tahoma", Font.PLAIN, 11));
		botonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaPrincipal.cambiarVentana("Ventana Menu");
			}
		});
		panelInferiorDerecha.setLayout(new BorderLayout(0, 0));
		panelInferiorDerecha.add(botonMenu);

		JPanel panelBlanco_1 = new JPanel();
		panelInferiorDerecha.add(panelBlanco_1, BorderLayout.NORTH);
		panelBlanco_1.setPreferredSize(new Dimension(0, 50));

		JPanel panelBlanco_2 = new JPanel();
		panelInferiorDerecha.add(panelBlanco_2, BorderLayout.SOUTH);
		panelBlanco_2.setPreferredSize(new Dimension(0, 15));

		JPanel panelBlanco_3 = new JPanel();
		panelInferiorDerecha.add(panelBlanco_3, BorderLayout.WEST);
		panelBlanco_3.setPreferredSize(new Dimension(60, 0));

		JPanel panelBlanco_4 = new JPanel();
		panelInferiorDerecha.add(panelBlanco_4, BorderLayout.EAST);
		panelBlanco_4.setPreferredSize(new Dimension(50, 0));

		JPanel panelIzquierda = new JPanel();
		add(panelIzquierda, BorderLayout.WEST);

		JPanel panelDerecha = new JPanel();
		add(panelDerecha, BorderLayout.EAST);
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
