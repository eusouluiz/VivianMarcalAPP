package br.edu.up.vivianmarcal.model.usuario;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 5500378251488245594L;

    private int id;
    private String nome;
    private String tipoUsuario;

    public Usuario(int id, String nome, String tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
