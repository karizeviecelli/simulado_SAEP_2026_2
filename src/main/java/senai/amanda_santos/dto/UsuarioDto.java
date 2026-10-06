package senai.amanda_santos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import senai.amanda_santos.model.Perfil;

public class UsuarioDto {

    private Long id;

    @NotBlank(message = "Informe o nome")
    @Size(max = 100, message = "Use no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "Informe o usuário de acesso")
    @Size(min = 3, max = 50, message = "Use de 3 a 50 caracteres")
    private String username;

    private String senha;

    @NotNull(message = "Escolha o perfil")
    private Perfil perfil = Perfil.ALMOXARIFE;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
