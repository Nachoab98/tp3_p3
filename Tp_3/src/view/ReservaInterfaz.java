//
//package interfaz;
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.LinkedList;
//import model.Dia;
//import model.Reserva;
//
//public class ReservaInterfaz {
//    private JFrame frame;
//    private JTextField textFieldHoraInicio;
//    private JTextField textFieldHoraFin;
//    private JTextField textFieldPrecio;
//    private JTextField textFieldNombre;
//    private Dia dia;
//    private LinkedList<Reserva> listaOfertas;
//
//    public ReservaInterfaz() {
//        dia = new Dia();
//        listaOfertas = new LinkedList<>();
//        initialize();
//    }
//
//    private void initialize() {
//        frame = new JFrame();
//        frame.setBounds(100, 100, 450, 400);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().setLayout(null);
//
//        JLabel lblHoraInicio = new JLabel("Hora Inicio:");
//        lblHoraInicio.setBounds(10, 30, 80, 25);
//        frame.getContentPane().add(lblHoraInicio);
//
//        textFieldHoraInicio = new JTextField();
//        textFieldHoraInicio.setBounds(100, 30, 160, 25);
//        frame.getContentPane().add(textFieldHoraInicio);
//        textFieldHoraInicio.setColumns(10);
//
//        JLabel lblHoraFin = new JLabel("Hora Fin:");
//        lblHoraFin.setBounds(10, 70, 80, 25);
//        frame.getContentPane().add(lblHoraFin);
//
//        textFieldHoraFin = new JTextField();
//        textFieldHoraFin.setBounds(100, 70, 160, 25);
//        frame.getContentPane().add(textFieldHoraFin);
//        textFieldHoraFin.setColumns(10);
//
//        JLabel lblPrecio = new JLabel("Precio:");
//        lblPrecio.setBounds(10, 110, 80, 25);
//        frame.getContentPane().add(lblPrecio);
//
//        textFieldPrecio = new JTextField();
//        textFieldPrecio.setBounds(100, 110, 160, 25);
//        frame.getContentPane().add(textFieldPrecio);
//        textFieldPrecio.setColumns(10);
//
//        JLabel lblNombre = new JLabel("Nombre:");
//        lblNombre.setBounds(10, 150, 80, 25);
//        frame.getContentPane().add(lblNombre);
//
//        textFieldNombre = new JTextField();
//        textFieldNombre.setBounds(100, 150, 160, 25);
//        frame.getContentPane().add(textFieldNombre);
//        textFieldNombre.setColumns(10);
//
//        JButton btnAgregarOferta = new JButton("Agregar Oferta");
//        btnAgregarOferta.setBounds(10, 190, 150, 25);
//        frame.getContentPane().add(btnAgregarOferta);
//
//        btnAgregarOferta.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    int horaInicio = Integer.parseInt(textFieldHoraInicio.getText());
//                    int horaFin = Integer.parseInt(textFieldHoraFin.getText());
//                    int precio = Integer.parseInt(textFieldPrecio.getText());
//                    String nombre = textFieldNombre.getText();
//                    Reserva nuevaReserva = new Reserva(horaInicio, horaFin, precio, nombre);
//                    dia.ofertar(horaInicio, horaFin, precio, nombre);
//                    listaOfertas.add(nuevaReserva);
//                    JOptionPane.showMessageDialog(frame, "Oferta agregada exitosamente!");
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(frame, "Por favor ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
//                } catch (RuntimeException ex) {
//                    JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//                } 
//            }
//        });
//
//        JButton btnMostrarLista = new JButton("Lista de ofertantes");
//        btnMostrarLista.setBounds(10, 230, 150, 25);
//        frame.getContentPane().add(btnMostrarLista);
//
//        btnMostrarLista.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                ListaOfertantes listaOfertantes = new ListaOfertantes(listaOfertas);
//                listaOfertantes.show();
//            }
//        });
//
//        JButton btnCalcularOptimo = new JButton("Calcular Óptimo");
//        btnCalcularOptimo.setBounds(10, 270, 150, 25);
//        frame.getContentPane().add(btnCalcularOptimo);
//
//        btnCalcularOptimo.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                BotonOPT calcularOptimo = new BotonOPT(dia);
//                calcularOptimo.show();
//            }
//        });
//    }
//
//    public void show() {
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        ReservaInterfaz window = new ReservaInterfaz();
//        window.show();
//    }
//}
package view;
