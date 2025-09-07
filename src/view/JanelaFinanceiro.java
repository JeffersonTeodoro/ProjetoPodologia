package view;

import java.awt.*;
import javax.swing.*;
import service.AtendimentoService;

public class JanelaFinanceiro extends JFrame {
    @SuppressWarnings({"unused", "FieldMayBeFinal"})
    private AtendimentoService service;

    public JanelaFinanceiro(AtendimentoService service) {
        this.service = service;

        setTitle("💰 Relatório Financeiro");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("Financeiro", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(70, 130, 180));

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(area);

        JButton btnAtualizar = new JButton("🔄 Atualizar Relatório");
        estilizarBotao(btnAtualizar);

        btnAtualizar.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            double total = 0, comissao = 0;

            for (var a : service.getAtendimentos()) {
                sb.append(a).append("\n");
                total += a.getValorTotal();
                comissao += a.getComissao();
            }

            sb.append("\n=== RESUMO ===\n");
            sb.append("Total Recebido: R$ ").append(total).append("\n");
            sb.append("Comissão (50%): R$ ").append(comissao).append("\n");

            area.setText(sb.toString());
        });

        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painel.add(scroll, BorderLayout.CENTER);
        painel.add(btnAtualizar, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(titulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void estilizarBotao(JButton b) {
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(new Color(100, 149, 237));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
    }
}
