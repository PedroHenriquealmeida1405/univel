package view;

import controller.ClienteController; 
import util.Mensagem;
import util.ResultadoCadastro;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroCliente extends JFrame {

    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextField txtTelefone;
    private JButton btnSalvar;

    private final ClienteController controller = new ClienteController();

    public TelaCadastroCliente() {
        setTitle("Cadastrar Cliente");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Idade:"));
        txtIdade = new JTextField();
        add(txtIdade);

        add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        add(txtTelefone);

        btnSalvar = new JButton("Salvar");
        add(new JLabel());
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            cadastrarCliente();
        });
    }

    private void cadastrarCliente() {
        try {
            String nome = txtNome.getText().trim();
            String idadeStr = txtIdade.getText().trim();
            String telefone = txtTelefone.getText().trim();

            if (nome.isEmpty() || idadeStr.isEmpty() || telefone.isEmpty()) {
                 Mensagem.erro("Todos os campos devem ser preenchidos.");
                 return;
            }

            int idade = Integer.parseInt(idadeStr);
            
            ResultadoCadastro resultado = controller.cadastrar(nome, idade, telefone); 

            switch(resultado) {
                case SUCESSO -> {
                    Mensagem.info("Cliente cadastrado com sucesso!");
                    dispose();
                }
                case JA_EXISTE -> Mensagem.erro("Já existe um cliente com esse nome. Escolha outro nome.");
                case ERRO_BANCO -> Mensagem.erro("Erro ao salvar no banco de dados.");
                default -> Mensagem.erro("Ocorreu um erro desconhecido.");
            }
        } catch (NumberFormatException ex) {
            Mensagem.erro("A idade deve ser um número inteiro válido.");
        } catch (Exception ex) {
            Mensagem.erro("Erro inesperado durante o cadastro: " + ex.getMessage());
        }
    }
}
