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
		JButton btnNewButton = new JButton("Volver al Menú");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameReserva.dispose();
			}
		});
		panelInferior.add(btnNewButton);
		JPanel panelIzquierda = new JPanel();
		frameReserva.getContentPane().add(panelIzquierda, BorderLayout.WEST);
		JPanel panelDerecha = new JPanel();
		frameReserva.getContentPane().add(panelDerecha, BorderLayout.EAST);
		JPanel panelCentral = new JPanel();
		frameReserva.getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new GridLayout(6, 0, 0, 0));
		JPanel panel_1 = new JPanel();
		panelCentral.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel lblNewLabel = new JLabel("Ingresa tu nombre:");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel);
		JPanel panel_2 = new JPanel();
		panelCentral.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		JPanel panel_6 = new JPanel();
		panel_2.add(panel_6, BorderLayout.SOUTH);
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7, BorderLayout.NORTH);
		JPanel panel_8 = new JPanel();
		panel_2.add(panel_8, BorderLayout.WEST);
		panel_8.setPreferredSize(new Dimension(70, 20));
		JPanel panel_9 = new JPanel();
		panel_2.add(panel_9, BorderLayout.EAST);
		panel_9.setPreferredSize(new Dimension(70, 20));
		JPanel panel_3 = new JPanel();
		panelCentral.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel labelHorarioInicio = new JLabel("Hora Inicio");
		labelHorarioInicio.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(labelHorarioInicio);
		JLabel labelHoraFin = new JLabel("Hora Fin");
		labelHoraFin.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(labelHoraFin);
		comboHoraInicio = new JComboBox<String>();
		comboHoraFin = new JComboBox<String>();
		for (int i = 0; i <= 23; i++) {
			comboHoraInicio.addItem(String.format("%02d:00", i));
			comboHoraFin.addItem(String.format("%02d:00", i + 1));
		}
		((JLabel) comboHoraInicio.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) comboHoraFin.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(comboHoraInicio);
		panel_3.add(comboHoraFin);
		JPanel panel_4 = new JPanel();
		panelCentral.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));
		JLabel lblNewLabel_1 = new JLabel("Monto a Ofertar:");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_1);
		JPanel panel_5 = new JPanel();
		panelCentral.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		textField_1 = new JTextField();
		panel_5.add(textField_1);
		textField_1.setColumns(10);
		JPanel panel_10 = new JPanel();
		panel_5.add(panel_10, BorderLayout.NORTH);
		JPanel panel_11 = new JPanel();
		panel_5.add(panel_11, BorderLayout.SOUTH);
		JPanel panel_12 = new JPanel();
		panel_5.add(panel_12, BorderLayout.WEST);
		panel_12.setPreferredSize(new Dimension(70, 20));
		JPanel panel_13 = new JPanel();
		panel_5.add(panel_13, BorderLayout.EAST);
		panel_13.setPreferredSize(new Dimension(70, 20));
		JPanel panel = new JPanel();
		panelCentral.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
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
		            JOptionPane.showMessageDialog(frameReserva, "Error: Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
		        } catch (RuntimeException ex) {
		            if (ex.getMessage().equals("Precio ofrecido invalido")) {
		                JOptionPane.showMessageDialog(frameReserva, "Error: Precio ofrecido inválido.", "Error", JOptionPane.ERROR_MESSAGE);
		            } else if (ex.getMessage().equals("Horarios invalidos")) {
		                JOptionPane.showMessageDialog(frameReserva, "Error: Horarios inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
		            } else if (ex.getMessage().equals("Ingrese un nombre")) {
		                JOptionPane.showMessageDialog(frameReserva, "Error: Ingrese un nombre.", "Error", JOptionPane.ERROR_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(frameReserva, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});
		panel.add(botonReservar);
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