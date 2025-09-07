package view;

import java.awt.*;
import java.time.LocalDateTime;
import javax.swing.*;
import model.Cliente;
import service.AtendimentoService;

@SuppressWarnings("unused")
public class TelaAtendimentos extends JFrame {
    @SuppressWarnings("FieldMayBeFinal")
    private AtendimentoService service;

    public TelaAtendimentos(AtendimentoService service) {
        this.service = service;

        setTitle("📅 Gerenciar Atendimentos");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Registro de Atendimentos", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(70, 130, 180));

        JComboBox<Cliente> cbClientes = new JComboBox<>(service.getClientes().toArray(Cliente[]::new));
        JTextField txtDescricao = new JTextField();
        JTextField txtValor = new JTextField();

        JButton btnSalvar = new JButton("💾 Registrar Atendimento");
        JButton btnListar = new JButton("📋 Listar Atendimentos");

        estilizarBotao(btnSalvar);
        estilizarBotao(btnListar);

        btnSalvar.addActionListener(e -> {
            Cliente cliente = (Cliente) cbClientes.getSelectedItem();
            String desc = txtDescricao.getText();
            String valorStr = txtValor.getText();

            if (cliente != null && !desc.isEmpty() && !valorStr.isEmpty()) {
                try {
                    double valor = Double.parseDouble(valorStr);
                    service.registrarAtendimento(cliente, desc, LocalDateTime.now(), valor);
                    JOptionPane.showMessageDialog(this, "✅ Atendimento registrado!");
                    txtDescricao.setText("");
                    txtValor.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "⚠ Valor inválido.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "⚠ Preencha todos os campos.");
            }
        });

        btnListar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, service.getAtendimentosTexto(), 
                                          "Lista de Atendimentos", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel painelForm = new JPanel(new GridLayout(6, 1, 10, 10));
        painelForm.setBackground(new Color(245, 245, 245));
        painelForm.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        painelForm.add(new JLabel("Cliente:"));
        painelForm.add(cbClientes);
        painelForm.add(new JLabel("Descrição:"));
        painelForm.add(txtDescricao);
        painelForm.add(new JLabel("Valor:"));
        painelForm.add(txtValor);

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
