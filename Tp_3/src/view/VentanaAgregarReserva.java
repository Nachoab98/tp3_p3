package view;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import presenter.VentanaAgregarReservaPresentador;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAgregarReserva {
	private JFrame frameReserva;
	private JComboBox<String> comboHoraInicio;
	private JComboBox<String> comboHoraFin;
	private JTextField textField;
	private JTextField textField_1;
	private VentanaAgregarReservaPresentador presenter;

	public VentanaAgregarReserva() {
		this.presenter = new VentanaAgregarReservaPresentador(this);
		ventanaReserva();
	}

	public void ventanaReserva() {
		frameReserva = new JFrame("Agregar Reserva");
		frameReserva.setTitle("Agregar Reserva");
		frameReserva.setBounds(100, 100, 400, 450);
		frameReserva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel panelSuperior = new JPanel();
		frameReserva.getContentPane().add(panelSuperior, BorderLayout.NORTH);
		JLabel labelReserva = new JLabel("Agrega tu Reserva");
		labelReserva.setFont(new Font("OCR A Extended", Font.BOLD, 22));
		panelSuperior.add(labelReserva);
		JPanel panelInferior = new JPanel();
		frameReserva.getContentPane().add(panelInferior, BorderLayout.SOUTH);
		JButton botonMenu = new JButton("Volver al Menú");
		botonMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameReserva.dispose();
			}
		});
		panelInferior.add(botonMenu);
		JPanel panelIzquierda = new JPanel();
		frameReserva.getContentPane().add(panelIzquierda, BorderLayout.WEST);
		JPanel panelDerecha = new JPanel();
		frameReserva.getContentPane().add(panelDerecha, BorderLayout.EAST);
		JPanel panelCentral = new JPanel();
		frameReserva.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(6, 0, 0, 0));
		JPanel panelLabelNombre = new JPanel();
		panelCentral.add(panelLabelNombre);
		panelLabelNombre.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel lblNewLabel = new JLabel("Ingresa tu nombre:");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabelNombre.add(lblNewLabel);
		JPanel panelFieldNombre = new JPanel();
		panelCentral.add(panelFieldNombre);
		panelFieldNombre.setLayout(new BorderLayout(0, 0));
		textField = new JTextField();
		panelFieldNombre.add(textField);
		textField.setColumns(10);
		JPanel panel_6 = new JPanel();
		panelFieldNombre.add(panel_6, BorderLayout.SOUTH);
		JPanel panel_7 = new JPanel();
		panelFieldNombre.add(panel_7, BorderLayout.NORTH);
		JPanel panel_8 = new JPanel();
		panelFieldNombre.add(panel_8, BorderLayout.WEST);
		panel_8.setPreferredSize(new Dimension(70, 20));
		JPanel panel_9 = new JPanel();
		panelFieldNombre.add(panel_9, BorderLayout.EAST);
		panel_9.setPreferredSize(new Dimension(70, 20));
		JPanel panelComboHoras = new JPanel();
		panelCentral.add(panelComboHoras);
		panelComboHoras.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel labelHorarioInicio = new JLabel("Hora Inicio");
		labelHorarioInicio.setHorizontalAlignment(SwingConstants.CENTER);
		panelComboHoras.add(labelHorarioInicio);
		JLabel labelHoraFin = new JLabel("Hora Fin");
		labelHoraFin.setHorizontalAlignment(SwingConstants.CENTER);
		panelComboHoras.add(labelHoraFin);
		comboHoraInicio = new JComboBox<String>();
		comboHoraFin = new JComboBox<String>();
		for (int i = 0; i <= 23; i++) {
			comboHoraInicio.addItem(String.format("%02d:00", i));
			comboHoraFin.addItem(String.format("%02d:00", i + 1));
		}
		((JLabel) comboHoraInicio.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) comboHoraFin.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panelComboHoras.add(comboHoraInicio);
		panelComboHoras.add(comboHoraFin);
		JPanel panelLabelOfertar = new JPanel();
		panelCentral.add(panelLabelOfertar);
		panelLabelOfertar.setLayout(new GridLayout(1, 0, 0, 0));
		JLabel lblNewLabel_1 = new JLabel("Monto a Ofertar:");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panelLabelOfertar.add(lblNewLabel_1);
		JPanel panelTextFieldOferta = new JPanel();
		panelCentral.add(panelTextFieldOferta);
		panelTextFieldOferta.setLayout(new BorderLayout(0, 0));
		textField_1 = new JTextField();
		panelTextFieldOferta.add(textField_1);
		textField_1.setColumns(10);
		JPanel panel_10 = new JPanel();
		panelTextFieldOferta.add(panel_10, BorderLayout.NORTH);
		JPanel panel_11 = new JPanel();
		panelTextFieldOferta.add(panel_11, BorderLayout.SOUTH);
		JPanel panel_12 = new JPanel();
		panelTextFieldOferta.add(panel_12, BorderLayout.WEST);
		panel_12.setPreferredSize(new Dimension(70, 20));
		JPanel panel_13 = new JPanel();
		panelTextFieldOferta.add(panel_13, BorderLayout.EAST);
		panel_13.setPreferredSize(new Dimension(70, 20));
		JPanel panelBotonReserva = new JPanel();
		panelCentral.add(panelBotonReserva);
		panelBotonReserva.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JButton botonReservar = new JButton("Reservar");
		botonReservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textField.getText();
				int horaInicio = comboHoraInicio.getSelectedIndex();
				int horaFin = comboHoraFin.getSelectedIndex() + 1;
				try {
					int precioOfrecido = Integer.parseInt(textField_1.getText());
					presenter.guardarOferta(nombre, horaInicio, horaFin, precioOfrecido);
					limpiarCampos();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(frameReserva, "Error: Ingrese un número válido.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} catch (RuntimeException ex) {
					if (ex.getMessage().equals("Precio ofrecido invalido")) {
						JOptionPane.showMessageDialog(frameReserva, "Error: Precio ofrecido inválido.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else if (ex.getMessage().equals("Horarios invalidos")) {
						JOptionPane.showMessageDialog(frameReserva, "Error: Horarios inválidos.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else if (ex.getMessage().equals("Ingrese un nombre")) {
						JOptionPane.showMessageDialog(frameReserva, "Error: Ingrese un nombre.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frameReserva, "Error: " + ex.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panelBotonReserva.add(botonReservar);
		frameReserva.setVisible(true);
	}

	private void limpiarCampos() {
		textField.setText("");
		textField_1.setText("");
		comboHoraInicio.setSelectedIndex(0);
		comboHoraFin.setSelectedIndex(0);

	}

	public void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(frameReserva, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}
}