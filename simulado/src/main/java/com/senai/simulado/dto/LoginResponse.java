package com.senai.simulado.dto;

import com.senai.simulado.enums.Perfil;

public record LoginResponse(String token, String tipo, String nome, String email, Perfil perfil) {
}
