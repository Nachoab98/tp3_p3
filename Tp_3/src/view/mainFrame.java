package view;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class mainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	final static String S_VENTANAMENU = "Ventana Menu";
	final static String S_VENTANAADJUDICACION = "Ventana Adjudicacion";
	private VentanaMenu ventanaMenu;
	private VentanaAdjudicacion ventanaJuego;
	private CardLayout cardLayout;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame window = new mainFrame();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public mainFrame() {
		setTitle("Al Mejor Postor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		getContentPane().setLayout(new CardLayout(0, 0));
		this.cardLayout = new CardLayout();
		getContentPane().setLayout(cardLayout);

		this.ventanaMenu = new VentanaMenu(this);
		this.ventanaJuego = new VentanaAdjudicacion(this);

		getContentPane().add(ventanaMenu, S_VENTANAMENU);
		getContentPane().add(ventanaJuego, S_VENTANAADJUDICACION);
		AgregarMenuBar();

		revalidate();
		repaint();

	}

	public void cambiarVentana(String ventana) {
		cardLayout.show(getContentPane(), ventana);
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	public void AgregarMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu desplegableOpciones = new JMenu("Opciones");
		menuBar.add(desplegableOpciones);

		JMenuItem acercaDe = new JMenuItem("Acerca de");
		desplegableOpciones.add(acercaDe);
		acercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] option = { "Volver" };
				String mensaje = "Proyecto creado para Programación III por: \n     -Dante Melhado,  \n     -Ignacio Aranda Bao,  \n     -Emanuel Suarez.";
				JOptionPane.showOptionDialog(ventanaJuego, mensaje, "Información del Proyecto",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
			}

		});

		JMenuItem salirJuego = new JMenuItem("Salir del Proyecto");
		desplegableOpciones.add(salirJuego);
		salirJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = { "Si", "No" };
				int opcion = JOptionPane.showOptionDialog(ventanaJuego, "¿Estas seguro que deseas salir del proyecto?",
						"Advertencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
						null);
				if (JOptionPane.YES_OPTION == opcion) {
					dispose();
				}
			}
		});
	}

}
