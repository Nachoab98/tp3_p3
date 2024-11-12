package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import presenter.VentanaReservasPresentador;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaReservas {
	private JFrame frameReservas;
	private JTable tablaOfertas;
	private JPanel panelBotonMenu;
	private JButton botonVolverMenu;
	private JPanel panelSuperior;
	private VentanaReservasPresentador presenter;

	public VentanaReservas() {
		ventanaReservas();
		presenter = new VentanaReservasPresentador(this);
		presenter.iniciar();

	}

	public void ventanaReservas() {
		frameReservas = new JFrame("Reservas");
		frameReservas.setTitle("Reservas");
		frameReservas.setBounds(100, 100, 500, 450);
		frameReservas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		String[] columnNames = { "ID", "Nombre del Cliente", "Horario", "Monto" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		tablaOfertas = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(tablaOfertas);
		tablaOfertas.getColumnModel().getColumn(0).setPreferredWidth(10);

		for (int i = 0; i < tablaOfertas.getColumnModel().getColumnCount(); i++) {
			tablaOfertas.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		frameReservas.getContentPane().add(scrollPane, BorderLayout.CENTER);

		panelBotonMenu = new JPanel();
		frameReservas.getContentPane().add(panelBotonMenu, BorderLayout.SOUTH);

		botonVolverMenu = new JButton("Volver al Menú");
		botonVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameReservas.dispose();
			}
		});
		panelBotonMenu.add(botonVolverMenu);

		panelSuperior = new JPanel();
		frameReservas.getContentPane().add(panelSuperior, BorderLayout.NORTH);

		frameReservas.setVisible(true);
	}

	// ESTO IRIA EN EL CONTROLADOR
	public void agregarOferta(int id, String nombre, String horario, int monto) {
		DefaultTableModel model = (DefaultTableModel) tablaOfertas.getModel();
		model.addRow(new Object[] { id, nombre, horario, "$ " + monto });
	}
}