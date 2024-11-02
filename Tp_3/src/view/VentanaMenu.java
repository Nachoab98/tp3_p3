package view;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private mainFrame pantallaPrincipal;

	public VentanaMenu(mainFrame pantallaPrincipal) {
		this.pantallaPrincipal = pantallaPrincipal;
		menuJuego();
	}

	public void menuJuego() {
		removeAll();
		repaint();
		setBounds(100, 100, 640, 480);
		setLayout(new BorderLayout(0, 0));

		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(6, 3, 0, 0));

		JLabel espacioVacio2 = new JLabel("");
		panelCentro.add(espacioVacio2);

		JButton btnNewButton = new JButton("Registrar Reserva");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAgregarReserva ventanaAgregarReserva = new VentanaAgregarReserva();

			}
		});
		panelCentro.add(btnNewButton);

		JLabel espacioVacio = new JLabel("");
		panelCentro.add(espacioVacio);

		JButton btnNewButton_2 = new JButton("Ver Reservas");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservas ventanaReservas = new VentanaReservas();
			}
		});
		panelCentro.add(btnNewButton_2);

		JLabel espacioVacio1 = new JLabel("");
		panelCentro.add(espacioVacio1);

		JButton btnNewButton_1 = new JButton("Resolver Adjudicación");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaPrincipal.cambiarVentana("Ventana Juego");
			}
		});
		panelCentro.add(btnNewButton_1);

		JPanel panelSuperior = new JPanel();
		add(panelSuperior, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Al Mejor Postor");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		panelSuperior.add(lblNewLabel);

		JPanel panelInferior = new JPanel();
		add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setPreferredSize(new Dimension(160, 70));

		JPanel panelIzquierda = new JPanel();
		add(panelIzquierda, BorderLayout.WEST);
		panelIzquierda.setPreferredSize(new Dimension(160, 80));
		;

		JPanel panelDerecha = new JPanel();
		add(panelDerecha, BorderLayout.EAST);
		panelDerecha.setPreferredSize(new Dimension(160, 80));
	}

}
