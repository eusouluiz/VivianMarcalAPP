package com.example.projeto_ds2.model.aviso;

import java.io.Serializable;
import java.util.ArrayList;


public class Aviso implements Serializable {

    private static final long serialVersionUID = -5938145431131500078L;

    private String texto;

    private String hora;

    public Aviso(String texto, String hora) {
        this.texto = texto;
        this.hora = hora;
    }

    public Aviso(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
