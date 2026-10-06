package com.senai.simulado.dto;

import com.senai.simulado.enums.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/** Atualizacao de usuario: a senha e opcional (se nula/vazia, permanece a atual). */
public record UsuarioAtualizacaoRequest(
        @NotBlank(message = "O nome é obrigatório.")
        @Size(max = 120, message = "O nome deve ter no máximo 120 caracteres.")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres.")
        String email,

        @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres.")
        String senha,

        @NotNull(message = "O perfil é obrigatório (OPERADOR ou ADMINISTRADOR).")
        Perfil perfil) {
}
