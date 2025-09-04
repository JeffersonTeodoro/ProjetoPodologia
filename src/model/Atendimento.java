package model;

import java.time.LocalDateTime;

public class Atendimento {
    private final Cliente cliente;
    private final LocalDateTime dataHora;
    private final String descricao;

    public Atendimento(Cliente cliente, LocalDateTime dataHora, String descricao) {
        this.cliente = cliente;
        this.dataHora = dataHora;
        this.descricao = descricao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "Atendimento de " + cliente.getNome() +
               " em " + dataHora +
               " | " + descricao;
    }
}

