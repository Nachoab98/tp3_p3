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
		menuPrincipal();
	}

	public void menuPrincipal() {
		removeAll();
		repaint();
		setBounds(100, 100, 640, 480);
		setLayout(new BorderLayout(0, 0));

		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(6, 3, 0, 0));

		JLabel espacioVacio2 = new JLabel("");
		panelCentro.add(espacioVacio2);

		JButton botonRegistrarReserva = new JButton("Registrar Reserva");
		botonRegistrarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaAgregarReserva ventanaAgregarReserva = new VentanaAgregarReserva();
				ventanaAgregarReserva.setVisible(true);

			}
		});
		panelCentro.add(botonRegistrarReserva);

		JLabel espacioVacio = new JLabel("");
		panelCentro.add(espacioVacio);

		JButton botonVerReservas = new JButton("Ver Reservas");
		botonVerReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaReservas ventanaReservas = new VentanaReservas();
				ventanaReservas.setVisible(true);
			}
		});
		panelCentro.add(botonVerReservas);

		JLabel espacioVacio1 = new JLabel("");
		panelCentro.add(espacioVacio1);

		JButton BotonAdjudicacion = new JButton("Resolver Adjudicación");
		BotonAdjudicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pantallaPrincipal.cambiarVentana("Ventana Adjudicacion");
			}
		});
		panelCentro.add(BotonAdjudicacion);

		JPanel panelSuperior = new JPanel();
		add(panelSuperior, BorderLayout.NORTH);

		JLabel labelTitulo = new JLabel("Al Mejor Postor");
		labelTitulo.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		panelSuperior.add(labelTitulo);

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