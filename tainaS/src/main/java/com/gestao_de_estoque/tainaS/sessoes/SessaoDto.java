package com.gestao_de_estoque.tainaS.sessoes;

import com.gestao_de_estoque.tainaS.enums.UsuarioCargo;
import com.gestao_de_estoque.tainaS.enums.UsuarioStatus;

public class SessaoDto {

    private Long id;
    private String email;
    private String nome;
    private UsuarioCargo cargo;
    private UsuarioStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UsuarioStatus getStatus() {
        return status;
    }

    public void setStatus(UsuarioStatus status) {
        this.status = status;
    }

    public UsuarioCargo getCargo() {
        return cargo;
    }

    public void setCargo(UsuarioCargo cargo) {
        this.cargo = cargo;
    }
}
