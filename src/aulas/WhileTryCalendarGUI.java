package aulas;

import java.util.Calendar;
import javax.swing.JOptionPane;

public class WhileTryCalendarGUI {
    public static void main(String[] args ) {
        String nome = JOptionPane.showInputDialog("Digite seu nome: ");
        
        int idade = -1;

        while (idade < 0) {
            try {
                String idadeStr = JOptionPane.showInputDialog("Digite sua idade: ");
                idade = Integer.parseInt(idadeStr);


                if (idade < 0) {
                    JOptionPane.showMessageDialog(null,  "Idade não pode ser nula");
                }
        
            }  catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Valor Inválido! Digite um número inteiro ");
            }
        }
        Calendar calendario = Calendar.getInstance();
        int anoAtual = calendario.get(calendario.YEAR);
        int anoNascimento = anoAtual - idade;


        String mensagem = "Olá, " + nome + "!\nVocê nasceu em " + anoNascimento + ".";
        JOptionPane.showMessageDialog(null, mensagem);
    }
}