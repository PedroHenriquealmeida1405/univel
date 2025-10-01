package aulas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RepedirNome extends JFrame {

    private JTextField txtNome, txtQuantidade;
    private JTextArea txtResultado;
    private JButton btnExecutar;

    public RepedirNome() {
        setTitle("Repetir Nome com FOR");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel lblNome = new JLabel("Digite seu nome:");
        txtNome = new JTextField(20);

        JLabel lblQtd = new JLabel("Quantas vezes repetir:");
        txtQuantidade = new JTextField(5);

        btnExecutar = new JButton("Repetir Nome:");
        txtResultado = new JTextArea(10, 35);
        txtResultado.setEditable(false);

        JScrollPane scroll = new JScrollPane(txtResultado);

        add(lblNome);
        add(txtNome);
        add(lblQtd);
        add(txtQuantidade);
        add(btnExecutar);
        add(scroll);

        //poo - botao executar está encapsulado no repetirnome
        //poo - actionlistener é interface
        btnExecutar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtResultado.setText(""); // limpa antes
                String nome = txtNome.getText();
                String qtdStr = txtQuantidade.getText();

                // Validação
                if (nome.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Digite um nome!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int qtd=0;
                try {
                    qtd = Integer.parseInt(qtdStr);
                    if (qtd <= 0) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Digite um número inteiro positivo!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }

                // Laço for usando a quantidade digitada
                for (int i = 1; i <= qtd; i++) {
                    txtResultado.append(i + " - " + nome + "\n");
                }
            }
        });
    }

    public static void main(String[] args) { //poo - polimorfismo setvisible
        SwingUtilities.invokeLater(() -> new RepedirNome().setVisible(true));
    }
}
