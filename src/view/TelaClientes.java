package view;

import java.awt.*;
import javax.swing.*;
import model.Cliente;
import service.AtendimentoService;

public class TelaClientes extends JFrame {
    @SuppressWarnings({"unused", "FieldMayBeFinal"})
    private AtendimentoService service;

    public TelaClientes(AtendimentoService service) {
        this.service = service;

        setTitle("👤 Gerenciar Clientes");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Cadastro de Clientes", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(70, 130, 180));

        JTextField txtNome = new JTextField();
        JTextField txtTelefone = new JTextField();

        JButton btnSalvar = new JButton("💾 Salvar Cliente");
        JButton btnListar = new JButton("📋 Listar Clientes");

        estilizarBotao(btnSalvar);
        estilizarBotao(btnListar);

        btnSalvar.addActionListener(e -> {
            String nome = txtNome.getText();
            String telefone = txtTelefone.getText();

            if (!nome.isEmpty() && !telefone.isEmpty()) {
                service.cadastrarCliente(new Cliente(nome, telefone));
                JOptionPane.showMessageDialog(this, "✅ Cliente salvo!");
                txtNome.setText("");
                txtTelefone.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "⚠ Preencha todos os campos.");
            }
        });

        btnListar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, service.getClientesTexto(), 
                                          "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel painelForm = new JPanel(new GridLayout(4, 1, 10, 10));
        painelForm.setBackground(new Color(245, 245, 245));
        painelForm.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        painelForm.add(new JLabel("Nome:"));
        painelForm.add(txtNome);
        painelForm.add(new JLabel("Telefone:"));
        painelForm.add(txtTelefone);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 2, 10, 10));
        painelBotoes.setBackground(new Color(245, 245, 245));
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnListar);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(painelForm, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void estilizarBotao(JButton b) {
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(new Color(100, 149, 237));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
    }
}
