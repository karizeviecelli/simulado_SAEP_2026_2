package com.gestao_de_estoque.tainaS.entities;


import com.gestao_de_estoque.tainaS.enums.UsuarioCargo;
import com.gestao_de_estoque.tainaS.enums.UsuarioStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "nome", nullable = false, length = 45)
        private String nome;

        @Column(name = "senha", nullable = false, length = 45)
        private String senha;

        @Column(name = "email", nullable = false, length = 45, unique = true)
        private String email;

        @Column(name = "cpf", nullable = false, length = 45, unique = true)
        private String cpf;

        @Enumerated(EnumType.STRING)
        @Column(name = "cargo", nullable = false, length = 45)
        private UsuarioCargo cargo;

        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false, length = 45)
        private UsuarioStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public UsuarioCargo getCargo() {
        return cargo;
    }

    public void setCargo(UsuarioCargo cargo) {
        this.cargo = cargo;
    }

    public UsuarioStatus getStatus() {
        return status;
    }

    public void setStatus(UsuarioStatus status) {
        this.status = status;
    }
}
