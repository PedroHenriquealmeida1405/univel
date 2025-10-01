package aulas;

import javax.swing.*;
import java.io.*;

public class ExemploArquivos {
    public static void main(String[] args) {
        try {
            String valor = JOptionPane.showInputDialog("digite um numero ");
            int numero = Integer.parseInt(valor);

            int resultado = 10 / numero;

            JOptionPane.showMessageDialog(null, "resultado: "+ resultado);

            FileReader fr = new FileReader("arquivo.txt");
            fr.close();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro: Digite apenas números!");

        } catch (ArithmeticException e) {
            JOptionPane.showMessageDialog(null, "ERRO: Não é possivel dividir por zero!");
        }catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Erro: Arquivo não encontrado!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro de leitura no arquivo!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado: " + e.getMessage());
        }
        
    }
}

