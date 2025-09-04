package service;

import java.time.LocalDateTime;
import java.util.*;
import model.Atendimento;
import model.Cliente;
import util.FileManager;

public class AtendimentoService {
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Atendimento> atendimentos = new ArrayList<>();

    private final String CLIENTES_FILE = "data/clientes.txt";
    private final String ATENDIMENTOS_FILE = "data/atendimentos.txt";

    public AtendimentoService() {
        carregarDados();
    }

    // === CLIENTES ===
    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        salvarClientes();
        System.out.println("✅ Cliente cadastrado com sucesso!");
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("⚠ Nenhum cliente cadastrado.");
        } else {
            System.out.println("=== Lista de Clientes ===");
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println(i + " - " + clientes.get(i));
            }
        }
    }

    // === ATENDIMENTOS ===
    public void registrarAtendimento(Scanner sc) {
        if (clientes.isEmpty()) {
            System.out.println("⚠ Nenhum cliente cadastrado.");
            return;
        }
        listarClientes();
        System.out.print("Escolha o cliente (número): ");
        int idx = sc.nextInt(); sc.nextLine();

        if (idx < 0 || idx >= clientes.size()) {
            System.out.println("❌ Cliente inválido!");
            return;
        }

        System.out.print("Descrição do atendimento: ");
        String desc = sc.nextLine();

        Atendimento atendimento = new Atendimento(clientes.get(idx), LocalDateTime.now(), desc);
        atendimentos.add(atendimento);
        salvarAtendimentos();

        System.out.println("✅ Atendimento registrado!");
    }

    public void listarAtendimentos() {
        if (atendimentos.isEmpty()) {
            System.out.println("⚠ Nenhum atendimento registrado.");
        } else {
            System.out.println("=== Lista de Atendimentos ===");
            atendimentos.forEach(System.out::println);
        }
    }

    // === PERSISTÊNCIA ===
    private void salvarClientes() {
        List<String> linhas = new ArrayList<>();
        for (Cliente c : clientes) {
           linhas.add(c.getEmail() + c.getNome() + ";" + c.getTelefone() + ";");
            
        }
        FileManager.salvar(CLIENTES_FILE, linhas);
    }

    private void salvarAtendimentos() {
        List<String> linhas = new ArrayList<>();
        for (Atendimento a : atendimentos) {
            linhas.add(a.getCliente().getNome() + ";" + a.getDataHora() + ";" + a.getDescricao());
        }
        FileManager.salvar(ATENDIMENTOS_FILE, linhas);
    }

    private void carregarDados() {
        // Carregar clientes
        List<String> clientesArq = FileManager.carregar(CLIENTES_FILE);
        for (String linha : clientesArq) {
            String[] p = linha.split(";");
            if (p.length == 3) {
                clientes.add(new Cliente(p[0], p[1], p[2]));
            }
        }

        // Carregar atendimentos
        List<String> atendArq = FileManager.carregar(ATENDIMENTOS_FILE);
        for (String linha : atendArq) {
            String[] p = linha.split(";");
            if (p.length == 3) {
                Cliente cli = buscarClientePorNome(p[0]);
                if (cli != null) {
                    atendimentos.add(new Atendimento(cli, LocalDateTime.parse(p[1]), p[2]));
                }
            }
        }
    }

    private Cliente buscarClientePorNome(String nome) {
        for (Cliente c : clientes) {
            if (c.getNome().equals(nome)) return c;
        }
        return null;
    }
}

