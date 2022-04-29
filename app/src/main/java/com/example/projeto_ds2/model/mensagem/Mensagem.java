package com.example.projeto_ds2.model.mensagem;

import java.io.Serializable;

public class Mensagem implements Serializable {

    private static final long serialVersionUID = -4740438769141831046L;

    private OrigemEnum origem;
    private String texto;

    public Mensagem(OrigemEnum origem, String texto, String hora) {
        this.origem = origem;
        this.texto = texto;
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    private String hora;

    public Mensagem(OrigemEnum origem, String texto) {
        this.origem = origem;
        this.texto = texto;
    }

    public OrigemEnum getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemEnum origem) {
        this.origem = origem;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
