package br.edu.up.vivianmarcal.mensagem.model;

public enum OrigemEnum {

    Remetente("remetente", 0),
    Destinatario("destinatario", 1);

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
