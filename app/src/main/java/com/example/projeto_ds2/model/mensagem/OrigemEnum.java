package com.example.projeto_ds2.model.mensagem;

public enum OrigemEnum {

    Remetente("remetente", 1),
    Destinatario("destinatario", 2);

    private String origem;
    private int id;

    OrigemEnum(String origem, int id) {
        this.origem = origem;
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public int getId() {
        return id;
    }
}
