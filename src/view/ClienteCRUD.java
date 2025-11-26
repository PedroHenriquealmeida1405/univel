package view;

import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.ClienteDAO;

import java.awt.*;
import java.util.List;

public class ClienteCRUD extends JFrame {
    private JTable tabelaClientes;
    private DefaultTableModel model;
    private JButton btnNovo, btnEditar, btnExcluir;
    private ClienteDAO clienteDAO = new ClienteDAO();

    public ClienteCRUD() {
        setTitle("Gerenciamento de Clientes");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        model = new DefaultTableModel(new String[] {"ID", "Nome", "Idade", "Telefone" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaClientes = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabelaClientes);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        btnNovo = new JButton("Novo Cliente");
        btnEditar = new JButton("Editar Cliente");
        btnExcluir = new JButton("Excluir Cliente");

        painelBotoes.add(btnNovo);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnExcluir);
        add(painelBotoes, BorderLayout.SOUTH);

        btnNovo.addActionListener(e -> abrirFormularioCliente(null));
        btnEditar.addActionListener(e -> editarCliente());
        btnExcluir.addActionListener(e -> excluirCliente());
        
        carregarTabela();
    }
    
    public void carregarTabela() {
        model.setRowCount(0);
        List<Cliente> clientes = clienteDAO.listarTodos();
        for (Cliente c : clientes) {
            model.addRow(new Object[] {
                c.getId(), 
                c.getNome(), 
                c.getIdade(), 
                c.getTelefone()
            });
        }
    }

    private void abrirFormularioCliente(Cliente clienteParaEditar) {
        
        TelaCadastroCliente tela = new TelaCadastroCliente();
        tela.setVisible(true);

    }
    
    private void editarCliente() {
        int linhaSelecionada = tabelaClientes.getSelectedRow();
        
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idCliente = (int) model.getValueAt(linhaSelecionada, 0);
        Cliente cliente = clienteDAO.buscarPorId(idCliente);

        if (cliente != null) {
             
             JOptionPane.showMessageDialog(this, "Funcionalidade de Edição (método 'gravar' no DAO) está definida, mas a View (TelaCadastroCliente) precisa ser adaptada para receber um objeto Cliente para edição.", "Ação pendente", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void excluirCliente() {
        int linhaSelecionada = tabelaClientes.getSelectedRow();
        
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idCliente = (int) model.getValueAt(linhaSelecionada, 0);
        String nomeCliente = (String) model.getValueAt(linhaSelecionada, 1);

        int confirmacao = JOptionPane.showConfirmDialog(this, 
            "Tem certeza que deseja excluir o cliente: " + nomeCliente + "?", 
            "Confirmação de Exclusão", JOptionPane.YES_NO_OPTION);

        if (confirmacao == JOptionPane.YES_OPTION) {
            if (clienteDAO.excluir(idCliente)) {
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                carregarTabela();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir cliente no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}