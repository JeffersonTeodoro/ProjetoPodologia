package view;

import java.awt.*;
import java.time.LocalDateTime;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Atendimento;
import model.Cliente;
import service.AtendimentoService;

public class MainView extends JFrame {

    private final AtendimentoService service;
    private final JTable tabelaClientes;
    private final JTable tabelaAtendimentos;

    public MainView(AtendimentoService service) {
        this.service = service;

        // Configurações da janela
        setTitle("Sistema de Podologia");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza na tela

        setLayout(new BorderLayout());

        // === Painel de botões ===
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
        JButton btnRegistrarAtendimento = new JButton("Registrar Atendimento");
        JButton btnRelatorio = new JButton("Relatório Financeiro");

        painelBotoes.add(btnCadastrarCliente);
        painelBotoes.add(btnRegistrarAtendimento);
        painelBotoes.add(btnRelatorio);

        add(painelBotoes, BorderLayout.NORTH);

        // === Tabelas de Clientes e Atendimentos ===
        tabelaClientes = new JTable();
        tabelaAtendimentos = new JTable();

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(tabelaClientes), new JScrollPane(tabelaAtendimentos));
        split.setDividerLocation(250);
        add(split, BorderLayout.CENTER);

        atualizarTabelas();

        // === Ações dos botões ===
        btnCadastrarCliente.addActionListener(e -> cadastrarCliente());
        btnRegistrarAtendimento.addActionListener(e -> registrarAtendimento());
        btnRelatorio.addActionListener(e -> mostrarRelatorio());

        setVisible(true);
    }

    private void atualizarTabelas() {
        // Tabela de clientes
        String[] colClientes = {"Nome", "Telefone"};
        DefaultTableModel modelClientes = new DefaultTableModel(colClientes, 0);
        for (Cliente c : service.getClientes()) {
            modelClientes.addRow(new Object[]{c.getNome(), c.getTelefone()});
        }
        tabelaClientes.setModel(modelClientes);

        // Tabela de atendimentos
        String[] colAtend = {"Cliente", "Data", "Serviço", "Valor", "Comissão"};
        DefaultTableModel modelAtend = new DefaultTableModel(colAtend, 0);
        for (Atendimento a : service.getAtendimentos()) {
            modelAtend.addRow(new Object[]{
                    a.getCliente().getNome(),
                    a.getDataHora(),
                    a.getDescricao(),
                    a.getValorTotal(),
                    a.getComissao()
            });
        }
        tabelaAtendimentos.setModel(modelAtend);
    }

    private void cadastrarCliente() {
        String nome = JOptionPane.showInputDialog(this, "Nome do cliente:");
        if (nome == null || nome.isBlank()) return;
        String telefone = JOptionPane.showInputDialog(this, "Telefone:");
        if (telefone == null || telefone.isBlank()) return;

        service.cadastrarCliente(new Cliente(nome, telefone));
        atualizarTabelas();
        JOptionPane.showMessageDialog(this, "✅ Cliente cadastrado!");
    }

    private void registrarAtendimento() {
        if (service.getClientes().isEmpty()) {
            JOptionPane.showMessageDialog(this, "⚠ Nenhum cliente cadastrado!");
            return;
        }

        String[] nomes = service.getClientes().stream().map(Cliente::getNome).toArray(String[]::new);
        String clienteEscolhido = (String) JOptionPane.showInputDialog(
                this, "Escolha o cliente:", "Cliente",
                JOptionPane.PLAIN_MESSAGE, null, nomes, nomes[0]);

        if (clienteEscolhido == null) return;
        Cliente cliente = service.getClientes().stream()
                .filter(c -> c.getNome().equals(clienteEscolhido))
                .findFirst().orElse(null);

        String descricao = JOptionPane.showInputDialog(this, "Descrição do atendimento:");
        if (descricao == null || descricao.isBlank()) return;

        String valorStr = JOptionPane.showInputDialog(this, "Valor do atendimento:");
        double valor = Double.parseDouble(valorStr);

        service.registrarAtendimento(cliente, descricao, LocalDateTime.now(), valor);
        atualizarTabelas();
        JOptionPane.showMessageDialog(this, "✅ Atendimento registrado!");
    }

    private void mostrarRelatorio() {
        StringBuilder sb = new StringBuilder();
        double totalRecebido = 0, totalComissao = 0;

        for (Atendimento a : service.getAtendimentos()) {
            sb.append(a).append("\n");
            totalRecebido += a.getValorTotal();
            totalComissao += a.getComissao();
        }

        sb.append("\n=== RESUMO ===\n");
        sb.append(String.format("Total Recebido: R$ %.2f%n", totalRecebido));
        sb.append(String.format("Comissão (50%%): R$ %.2f%n", totalComissao));

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "Relatório Financeiro",
                JOptionPane.INFORMATION_MESSAGE);
    }
}

