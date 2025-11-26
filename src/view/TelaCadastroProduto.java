package view;

import controller.ProdutoController;
import util.Mensagem;
import util.ResultadoCadastro;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroProduto extends JFrame {

    private JTextField txtNome;
    private JTextField txtMarca;
    private JTextField txtPreco;
    private JButton btnSalvar;

    private final ProdutoController controller = new ProdutoController();

    public TelaCadastroProduto() {
        setTitle("Cadastrar produto");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,2,10,10));

        add(new JLabel("Novo produto"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Marca"));
        txtMarca = new JPasswordField();
        add(txtMarca);

        add(new JLabel("Preco:"));
        txtPreco= new JPasswordField();
        add(txtPreco);

        btnSalvar = new JButton("Salvar");
        add(new JLabel());
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText();
            String marca = txtMarca.getText();
            Double preco = Double.parseDouble(txtPreco.getText());
            ResultadoCadastro resultado = controller.cadastrar(nome, preco, marca);

            switch(resultado) {
                case SUCESSO -> {
                    Mensagem.info("Produto cadastrado com sucesso");
                    dispose();;
                }
                case JA_EXISTE -> Mensagem.erro("Ja existe um produto com esse nome. Escolha outro nome");
                case ERRO_BANCO -> Mensagem.erro("Erro ao salvar no banco de dados");

            }
        });


    }

    
}


