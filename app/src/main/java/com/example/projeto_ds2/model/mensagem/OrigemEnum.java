package com.example.projeto_ds2.model.mensagem;

public enum OrigemEnum {

    Remetente("remetente"),
    Destinatario("destinatario");

    private String origem;

    OrigemEnum(String origem) {
        this.origem = origem;
    }

    public String getOrigem() {
        return origem;
    }
}
