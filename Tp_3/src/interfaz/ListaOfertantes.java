//
//package interfaz;
//import javax.swing.*;
//import java.util.LinkedList;
//import model.Reserva;
//
//public class ListaOfertantes {
//    private JFrame frame;
//    private JTextArea textAreaListaOfertas;
//    private LinkedList<Reserva> listaOfertas;
//
//    public ListaOfertantes(LinkedList<Reserva> listaOfertas) {
//        this.listaOfertas = listaOfertas;
//        initialize();
//    }
//
//    private void initialize() {
//        frame = new JFrame();
//        frame.setBounds(100, 100, 450, 300);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.getContentPane().setLayout(null);
//
//        textAreaListaOfertas = new JTextArea();
//        textAreaListaOfertas.setBounds(10, 10, 400, 240);
//        textAreaListaOfertas.setEditable(false);
//        frame.getContentPane().add(textAreaListaOfertas);
//
//        StringBuilder listaTexto = new StringBuilder();
//        for (Reserva reserva : listaOfertas) {
//            listaTexto.append("Nombre: ").append(reserva.nombre())
//                      .append(", Inicio: ").append(reserva.inicio())
//                      .append(", Fin: ").append(reserva.fin())
//                      .append(", Precio: ").append(reserva.precio())
//                      .append("\n");
//        }
//        textAreaListaOfertas.setText(listaTexto.toString());
//    }
//
//    public void show() {
//        frame.setVisible(true);
//    }
//}
