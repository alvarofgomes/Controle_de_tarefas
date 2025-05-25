package com.tarefas.Controle_de_Tarefas.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TarefaDTO {

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotNull(message = "Data de entrega é obrigatória")
    @FutureOrPresent(message = "A data de entrega não pode ser no passado")
    private LocalDate dataEntrega;

    @NotNull(message = "Informe se a tarefa está concluída ou não")
    private Boolean concluida;

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Boolean getConcluida() {
        return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
