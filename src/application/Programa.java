package application;

import java.util.Scanner;
import model.Cliente;
import service.AtendimentoService;
import view.MainView;

public class Programa {
    @SuppressWarnings("unused")
    public static void main(String[] args) {

        AtendimentoService service = new AtendimentoService();
        MainView mainView = new MainView(service); // abre a interface gráfica
        try (Scanner sc = new Scanner(System.in)) {
            int opcao;
            do {
                System.out.println("\n=== SISTEMA DE PODOLOGIA ===");
                System.out.println("1 - Cadastrar Cliente");
                System.out.println("2 - Listar Clientes");
                System.out.println("3 - Registrar Atendimento");
                System.out.println("4 - Listar Atendimentos + Relatório Financeiro");
                System.out.println("0 - Sair");
                System.out.print("Escolha: ");
                opcao = sc.nextInt();
                sc.nextLine();
                
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = sc.nextLine();
                        service.cadastrarCliente(new Cliente(nome, telefone));
                    }
                    case 2 -> service.listarClientes();
                    case 3 -> service.registrarAtendimento(sc);
                    case 4 -> {
                        service.listarAtendimentos();
                        mostrarResumoFinanceiro(service);
                    }
                    case 0 -> System.out.println("Encerrando o sistema...");
                    default -> System.out.println("❌ Opção inválida!");
                }
            } while (opcao != 0);
        }
    }

    private static void mostrarResumoFinanceiro(AtendimentoService service) {
        double totalRecebido = service.getAtendimentos().stream()
                .mapToDouble(a -> a.getValorTotal())
                .sum();

        double totalComissao = service.getAtendimentos().stream()
                .mapToDouble(a -> a.getComissao())
                .sum();

        System.out.println("\n=== RELATÓRIO FINANCEIRO ===");
        System.out.printf("💰 Total Recebido: R$ %.2f%n", totalRecebido);
        System.out.printf("🏦 Comissão (50%%): R$ %.2f%n", totalComissao);
        System.out.printf("👩‍⚕️ Lucro da Podóloga: R$ %.2f%n", (totalRecebido - totalComissao));
    }
}
