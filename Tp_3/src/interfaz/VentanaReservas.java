package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaReservas {
	private JFrame frameReservas;
	private JTable tablaOfertas;
	private JPanel panel;
	private JButton botonVolverMenu;
	private JPanel panelSuperior;

	public VentanaReservas() {
		ventanaReservas();
	}

	public void ventanaReservas() {
		frameReservas = new JFrame("Reservas");
		frameReservas.setTitle("Reservas");
		frameReservas.setBounds(100, 100, 500, 450);
		frameReservas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		String[] columnNames = { "ID", "Nombre del Cliente", "Horario", "Monto" };
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);

		tablaOfertas = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(tablaOfertas);
		tablaOfertas.getColumnModel().getColumn(0).setPreferredWidth(10);

		frameReservas.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		frameReservas.getContentPane().add(panel, BorderLayout.SOUTH);
		
		botonVolverMenu = new JButton("Volver al Menú");
		botonVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameReservas.dispose();
			}
		});
		panel.add(botonVolverMenu);
		
		panelSuperior = new JPanel();
		frameReservas.getContentPane().add(panelSuperior, BorderLayout.NORTH);

		frameReservas.setVisible(true);
	}
	
	
	//ESTO IRIA EN EL CONTROLADOR
	public void agregarOferta(double id, String nombre, String horario, double monto) {
        DefaultTableModel model = (DefaultTableModel) tablaOfertas.getModel();
        model.addRow(new Object[]{id, nombre, horario, "$" + String.format("%.2f", monto)});
    }
}
