package ui;

import service.AtendimentoService;
import model.Atendimento;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JanelaFinanceiro extends JFrame {

    public JanelaFinanceiro(AtendimentoService service) {
        setTitle("Relatório Financeiro");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Área de texto para listar atendimentos
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        StringBuilder sb = new StringBuilder();
        List<Atendimento> atendimentos = service.getAtendimentos();
        double totalRecebido = 0.0;
        double totalComissao = 0.0;

        sb.append("=== LISTA DE ATENDIMENTOS ===\n\n");
        for (Atendimento a : atendimentos) {
            sb.append(a.toString()).append("\n");
            totalRecebido += a.getValorTotal();
            totalComissao += a.getComissao();
        }

        sb.append("\n=== RESUMO FINANCEIRO ===\n");
        sb.append(String.format("💰 Total Recebido: R$ %.2f\n", totalRecebido));
        sb.append(String.format("🏦 Comissão (50%%): R$ %.2f\n", totalComissao));
        sb.append(String.format("👩‍⚕️ Lucro da Podóloga: R$ %.2f\n", totalRecebido - totalComissao));

        textArea.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botão de fechar
        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        panel.add(btnFechar, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
