package com.example.projeto_ds2.model.aviso;

import java.io.Serializable;
import java.util.ArrayList;


public class Aviso implements Serializable {

    private static final long serialVersionUID = -5938145431131500078L;

    private String texto;

    public Aviso(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
