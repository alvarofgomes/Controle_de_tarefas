package com.tarefas.Controle_de_Tarefas.exception;

import java.time.LocalDateTime;

public class ErroPadronizado {

    private LocalDateTime timestamp;
    private String mensagem;
    private String caminho;

    public ErroPadronizado(LocalDateTime timestamp, String mensagem, String caminho) {
        this.timestamp = timestamp;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getCaminho() {
        return caminho;
    }
}
