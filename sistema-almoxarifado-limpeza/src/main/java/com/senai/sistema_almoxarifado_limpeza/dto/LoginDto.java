package com.senai.sistema_almoxarifado_limpeza.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(

        @NotBlank
        String login,

        @NotBlank
        String senha
) {
}
