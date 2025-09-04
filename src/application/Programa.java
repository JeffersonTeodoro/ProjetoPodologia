package application;

import java.util.*;
import model.Cliente;
import service.AtendimentoService;

public class Programa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AtendimentoService service = new AtendimentoService();

        while (true) {
            System.out.println("\n==== SISTEMA DE PODOLOGIA ====");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Registrar Atendimento");
            System.out.println("4. Listar Atendimentos");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int opc = sc.nextInt(); sc.nextLine();

            switch (opc) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    service.cadastrarCliente(new Cliente(nome, telefone, email));
                }

                case 2 -> service.listarClientes();

                case 3 -> service.registrarAtendimento(sc);

                case 4 -> service.listarAtendimentos();

                case 0 -> {
                    System.out.println("Encerrando o sistema...");
                    sc.close();
                    return;
                }

                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
