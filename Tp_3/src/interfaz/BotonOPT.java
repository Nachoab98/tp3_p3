
//package interfaz;
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import model.Dia;
//
//public class BotonOPT {
//    private JFrame frame;
//    private JTextArea textAreaResultado;
//    private Dia dia;
//
//    public BotonOPT(Dia dia) {
//        this.dia = dia;
//        initialize();
//    }
//
//    private void initialize() {
//        frame = new JFrame();
//        frame.setBounds(100, 100, 450, 300);
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.getContentPane().setLayout(null);
//
//        textAreaResultado = new JTextArea();
//        textAreaResultado.setBounds(10, 10, 400, 200);
//        textAreaResultado.setEditable(false);
//        frame.getContentPane().add(textAreaResultado);
//
//        JButton btnCalcular = new JButton("Calcular Óptimo");
//        btnCalcular.setBounds(10, 220, 150, 25);
//        frame.getContentPane().add(btnCalcular);
//
//        btnCalcular.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int gananciaOptima = dia.solucionOptima();
//                textAreaResultado.setText("Ganancia óptima: " + gananciaOptima);
//            }
//        });
//    }
//
//    public void show() {
//        frame.setVisible(true);
//    }
//}
