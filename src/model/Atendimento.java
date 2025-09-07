package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Atendimento {

    private final Cliente cliente;
    private final LocalDateTime dataHora;
    private final String descricao;
    private final double valorTotal;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Atendimento(Cliente cliente, LocalDateTime dataHora, String descricao, double valorTotal) {
        this.cliente = cliente;
        this.dataHora = dataHora;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
    }

    // === GETTERS ===
    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    // Comissão sempre calculada: 50% do valor
    public double getComissao() {
        return valorTotal * 0.5;
    }

    // === REPRESENTAÇÃO PARA LISTAGEM E RELATÓRIO ===
    @Override
    public String toString() {
        return String.format("[%s] Cliente: %s | Serviço: %s | Valor: R$ %.2f | Comissão: R$ %.2f",
                dataHora.format(FORMATTER),
                cliente.getNome(),
                descricao,
                valorTotal,
                getComissao());
    }
}
