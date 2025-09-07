package util;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Atendimento;
import model.Cliente;

public class FileManager {
    private static final String CLIENTES_FILE = "data/clientes.txt";
    private static final String ATENDIMENTOS_FILE = "data/atendimentos.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    // ===== CLIENTES =====
    public static void salvarClientes(List<Cliente> clientes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CLIENTES_FILE))) {
            for (Cliente c : clientes) {
                bw.write(c.getNome() + ";" + c.getTelefone());
                bw.newLine();
            }
        } catch (IOException e) {
        }
    }

    public static List<Cliente> carregarClientes() {
        List<Cliente> lista = new ArrayList<>();
        File file = new File(CLIENTES_FILE);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 2) {
                    lista.add(new Cliente(dados[0], dados[1]));
                }
            }
        } catch (IOException e) {
        }
        return lista;
    }

    // ===== ATENDIMENTOS =====
    public static void salvarAtendimentos(List<Atendimento> atendimentos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ATENDIMENTOS_FILE))) {
            for (Atendimento a : atendimentos) {
                bw.write(
                        a.getCliente().getNome() + ";" +
                        a.getCliente().getTelefone() + ";" +
                        a.getDataHora().format(FORMATTER) + ";" +
                        a.getDescricao() + ";" +
                        a.getValorTotal()
                );
                bw.newLine();
            }
        } catch (IOException e) {
        }
    }

    public static List<Atendimento> carregarAtendimentos(List<Cliente> clientes) {
        List<Atendimento> lista = new ArrayList<>();
        File file = new File(ATENDIMENTOS_FILE);
        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5) {
                    String nome = dados[0];
                    String telefone = dados[1];
                    LocalDateTime dataHora = LocalDateTime.parse(dados[2], FORMATTER);
                    String descricao = dados[3];
                    double valor = Double.parseDouble(dados[4]);

                    // procura cliente correspondente
                    Cliente cliente = clientes.stream()
                            .filter(c -> c.getNome().equals(nome) && c.getTelefone().equals(telefone))
                            .findFirst()
                            .orElse(new Cliente(nome, telefone));

                    lista.add(new Atendimento(cliente, dataHora, descricao, valor));
                }
            }
        } catch (IOException e) {
        }
        return lista;
    }

    // ===== RELATÓRIO FINANCEIRO =====
    public static void salvarRelatorioFinanceiro(List<Atendimento> atendimentos) {
        File file = new File("data/relatorio.txt");
        double totalRecebido = 0.0;
        double totalComissao = 0.0;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("=== RELATÓRIO FINANCEIRO ===");
            bw.newLine();
            bw.newLine();

            for (Atendimento a : atendimentos) {
                bw.write(a.toString());
                bw.newLine();

                totalRecebido += a.getValorTotal();
                totalComissao += a.getComissao(); // calculada automaticamente
            }

            bw.newLine();
            bw.write("=== RESUMO ===");
            bw.newLine();
            bw.write("Total Recebido: R$ " + totalRecebido);
            bw.newLine();
            bw.write("Comissão (50%): R$ " + totalComissao);
            bw.newLine();

        } catch (IOException e) {
        }
    }
}
