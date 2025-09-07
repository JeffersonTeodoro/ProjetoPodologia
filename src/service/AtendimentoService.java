package service;

import java.time.LocalDateTime;
import java.util.*;
import model.Atendimento;
import model.Cliente;
import util.FileManager;

public class AtendimentoService {
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Atendimento> atendimentos = new ArrayList<>();

    public AtendimentoService() {
        carregarDados();
    }

    // === CLIENTES ===
    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        FileManager.salvarClientes(clientes); // agora chama direto
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

    // === ATENDIMENTOS (modo console) ===
    public void registrarAtendimento(Scanner sc) {
        if (clientes.isEmpty()) {
            System.out.println("⚠ Nenhum cliente cadastrado.");
            return;
        }
        listarClientes();
        System.out.print("Escolha o cliente (número): ");
        int idx = sc.nextInt();
        sc.nextLine();

        if (idx < 0 || idx >= clientes.size()) {
            System.out.println("❌ Cliente inválido!");
            return;
        }

        System.out.print("Descrição do atendimento: ");
        String desc = sc.nextLine();

        System.out.print("Valor do atendimento: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        Atendimento atendimento = new Atendimento(clientes.get(idx), LocalDateTime.now(), desc, valor);
        atendimentos.add(atendimento);
        FileManager.salvarAtendimentos(atendimentos); // salva direto

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
    private void carregarDados() {
        clientes.addAll(FileManager.carregarClientes());
        atendimentos.addAll(FileManager.carregarAtendimentos(clientes));
    }

    // === GETTERS ===
    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public String getClientesTexto() {
        if (clientes.isEmpty()) return "Nenhum cliente cadastrado.";
        StringBuilder sb = new StringBuilder();
        for (Cliente c : clientes) {
            sb.append(c).append("\n");
        }
        return sb.toString();
    }

    public String getAtendimentosTexto() {
        if (atendimentos.isEmpty()) return "Nenhum atendimento registrado.";
        StringBuilder sb = new StringBuilder();
        for (Atendimento a : atendimentos) {
            sb.append(a).append("\n");
        }
        return sb.toString();
    }

    // usado nas telas Swing
    public void registrarAtendimento(Cliente cliente, String descricao, LocalDateTime dataHora, double valor) {
        Atendimento atendimento = new Atendimento(cliente, dataHora, descricao, valor);
        atendimentos.add(atendimento);
        FileManager.salvarAtendimentos(atendimentos); // salva direto
    }
}
