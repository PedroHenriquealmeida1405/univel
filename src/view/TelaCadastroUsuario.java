package view;

import controller.UsuarioController;
import model.Usuario;
import util.Mensagem;
import util.ResultadoCadastro;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroUsuario extends JFrame {

    private JTextField txtLogin;
    private JPasswordField txtSenha;
    private JButton btnSalvar;

    private final UsuarioController controller = new UsuarioController();

    public TelaCadastroUsuario() {
        setTitle("Cadastrar usuario");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,2,10,10));

        add(new JLabel("novo usuario"));
        txtLogin = new JTextField();
        add(txtLogin);

        add(new JLabel("senha:"));
        txtSenha = new JPasswordField();
        add(txtSenha);


        btnSalvar = new JButton("salvar");
        add(new JLabel());
        add(btnSalvar);

        btnSalvar.addActionListener(e -> {
            String login = txtLogin.getText();
            String senha = new String(txtSenha.getPassword());
            ResultadoCadastro resultado = controller.cadastrar(login, senha);

            switch(resultado) {
                case SUCESSO -> {
                    Mensagem.info("Usuario cadastrado com sucesso");
                    dispose();;
                }
                case USUARIO_EXISTE -> Mensagem.erro("Ja existe um usuario com esse nome. escolha outro nome");
                case ERRO_BANCO -> Mensagem.erro("erro ao salvar no banco de dados");

            }
        });


    }

    
}

