package com.senai.simulado.dto;

import com.senai.simulado.entity.Usuario;
import com.senai.simulado.enums.Perfil;

public record UsuarioResponse(Long id, String nome, String email, Perfil perfil) {

    public static UsuarioResponse de(Usuario u) {
        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail(), u.getPerfil());
    }
}
