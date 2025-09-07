package view;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import service.AtendimentoService;

public class TelaPrincipal extends JFrame {
    @SuppressWarnings({"unused", "FieldMayBeFinal"})
    private AtendimentoService service;

    public TelaPrincipal(AtendimentoService service) {
        this.service = service;

        setTitle("📌 Sistema de Atendimentos");
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // === MENU SUPERIOR ===
        JMenuBar menuBar = new JMenuBar();

        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemSair = new JMenuItem("Sair", new ImageIcon(getClass().getResource("/icons/sair.png")));
        itemSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        itemSair.addActionListener(e -> System.exit(0));
        menuArquivo.add(itemSair);

        JMenu menuCadastro = new JMenu("Cadastro");
        JMenuItem itemClientes = new JMenuItem("Clientes", new ImageIcon(getClass().getResource("/icons/cliente.png")));
        itemClientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));

        JMenuItem itemAtendimentos = new JMenuItem("Atendimentos", new ImageIcon(getClass().getResource("/icons/atendimento.png")));
        itemAtendimentos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));

        menuCadastro.add(itemClientes);
        menuCadastro.add(itemAtendimentos);

        JMenu menuFinanceiro = new JMenu("Financeiro");
        JMenuItem itemRelatorio = new JMenuItem("Relatório", new ImageIcon(getClass().getResource("/icons/financeiro.png")));
        itemRelatorio.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        menuFinanceiro.add(itemRelatorio);

        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemSobre = new JMenuItem("Sobre o Sistema", new ImageIcon(getClass().getResource("/icons/help.png")));
        itemSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        itemSobre.addActionListener(e -> JOptionPane.showMessageDialog(this,
            "📌 Sistema de Atendimentos\nVersão 1.0\nDesenvolvido por Jefferson França",
            "Sobre", JOptionPane.INFORMATION_MESSAGE));
        menuAjuda.add(itemSobre);

        menuBar.add(menuArquivo);
        menuBar.add(menuCadastro);
        menuBar.add(menuFinanceiro);
        menuBar.add(menuAjuda);
        setJMenuBar(menuBar);

        // === BOTÕES GRANDES COM ÍCONES ===
        JButton btnClientes = new JButton("Clientes", new ImageIcon(getClass().getResource("/icons/cliente.png")));
        JButton btnAtendimentos = new JButton("Atendimentos", new ImageIcon(getClass().getResource("/icons/atendimento.png")));
        JButton btnFinanceiro = new JButton("Financeiro", new ImageIcon(getClass().getResource("/icons/financeiro.png")));

        estilizarBotao(btnClientes);
        estilizarBotao(btnAtendimentos);
        estilizarBotao(btnFinanceiro);

        itemClientes.addActionListener(e -> new TelaClientes(service));
        btnClientes.addActionListener(e -> new TelaClientes(service));

        itemAtendimentos.addActionListener(e -> new TelaAtendimentos(service));
        btnAtendimentos.addActionListener(e -> new TelaAtendimentos(service));

        itemRelatorio.addActionListener(e -> new JanelaFinanceiro(service));
        btnFinanceiro.addActionListener(e -> new JanelaFinanceiro(service));

        JPanel painelCentral = new JPanel(new GridLayout(1, 3, 30, 30));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        painelCentral.add(btnClientes);
        painelCentral.add(btnAtendimentos);
        painelCentral.add(btnFinanceiro);

        add(painelCentral);
        setVisible(true);
    }

    private void estilizarBotao(JButton b) {
        b.setFont(new Font("Arial", Font.BOLD, 16));
        b.setBackground(new Color(70, 130, 180));
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        b.setHorizontalTextPosition(SwingConstants.CENTER);
        b.setVerticalTextPosition(SwingConstants.BOTTOM);
    }
}

